/**
 * 
 */
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.e4.core.di.annotations.Execute;

import de.fraunhofer.isst.stars.requirementDSL.Model;
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage;

/**
 * @author mmauritz
 *
 */
// TODO
public class AstAnalyzerHandler {

    private static final String IASTANALYZER_ID = "de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.analyzer";


    @Execute
    public void execute(final Model model) {
	IExtensionRegistry registry = Platform.getExtensionRegistry();
	IExtensionPoint ep = registry.getExtensionPoint(IASTANALYZER_ID);
	IExtension[] extensions = ep.getExtensions();
	try {
	    for (IExtension ext : extensions) {
		IConfigurationElement[] configs = ext.getConfigurationElements();
		for (int i = 0; i < configs.length; i++) {
		    Object o = configs[i].createExecutableExtension("class");
		    if (configs[i].getAttribute("lanng").equals(RequirementDSLPackage.eNAME)
			    & o instanceof IAstAnalyzer) {
			executeAnalyzer((IAstAnalyzer) o, model);
		    }
		}
	    }
	} catch (CoreException ex) {
	    System.out.println(ex.getMessage());
	}
    }

    private void executeAnalyzer(IAstAnalyzer analyzer, final Model model) {
	ISafeRunnable runnable = new ISafeRunnable() {
	    @Override
	    public void handleException(Throwable e) {
		System.out.println("Exception in client");
	    }

	    @Override
	    public void run() throws Exception {
		analyzer.analyze(model);
	    }
	};
	SafeRunner.run(runnable);
    }

}
