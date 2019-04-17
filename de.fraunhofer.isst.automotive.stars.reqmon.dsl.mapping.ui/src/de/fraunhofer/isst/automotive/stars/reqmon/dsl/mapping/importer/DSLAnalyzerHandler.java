/**
 * 
 */
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.importer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.ecore.EObject;

/**
 * @author mmauritz
 *
 */
public class DSLAnalyzerHandler {

	private static final String DSLANALYZER_ID = "de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.DslAnalyzer";

	@Execute
	public void execute(final EObject obj) {
		System.out.println("DSL Analyzer Handler inititated");
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint ep = registry.getExtensionPoint(DSLANALYZER_ID);
		IExtension[] extensions = ep.getExtensions();
		
		try {
			for (IExtension ext : extensions) {
				IConfigurationElement[] configs = ext.getConfigurationElements();
				for (int i = 0; i < configs.length; i++) {
					Object o = configs[i].createExecutableExtension("class");
					System.out.println(obj.eClass().getEPackage().getNsURI());
					String lang_attr = configs[i].getAttribute("lang");
					if (lang_attr!=null && lang_attr.equals(obj.eClass().getEPackage().getNsURI())
							&& o instanceof IDslAnalyzer) {
						System.out.println("Starting DSL Analyzer");
						executeAnalyzer((IDslAnalyzer) o, obj);
						return;//Jump out if found to not hit error
					}
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
		throw new UnknownError("Language is not known for Analysis!");
	}

	private void executeAnalyzer(IDslAnalyzer analyzer, final EObject model) {
		//TODO Think about Eclipse Job API
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
