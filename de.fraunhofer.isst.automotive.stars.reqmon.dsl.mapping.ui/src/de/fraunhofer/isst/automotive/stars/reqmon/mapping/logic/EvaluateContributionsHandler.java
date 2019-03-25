package de.fraunhofer.isst.automotive.stars.reqmon.mapping.logic;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.e4.core.di.annotations.Execute;

import de.fraunhofer.isst.automotive.stars.reqmon.mapping.definitions.IGenerator;

public class EvaluateContributionsHandler {
	private static final String IGENERATOR_ID =
			"de.fraunhofer.isst.automotive.stars.reqmon.sdl.mapping.generator";

	@Execute
	public void execute(IExtensionRegistry registry) {
		IConfigurationElement[] config = 
				registry.getConfigurationElementsFor(IGENERATOR_ID);
		try {
			for (IConfigurationElement e : config) {
				System.out.println("Evaluating extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IGenerator) {
					executeExtension(o);
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private void executeExtension(final Object o) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				((IGenerator) o).generate();
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in client");
			}
		};
		SafeRunner.run(runnable);
	}
}
