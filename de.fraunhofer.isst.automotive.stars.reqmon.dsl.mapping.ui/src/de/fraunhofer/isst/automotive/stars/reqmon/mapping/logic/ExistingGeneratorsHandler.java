package de.fraunhofer.isst.automotive.stars.reqmon.mapping.logic;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.e4.core.di.annotations.Execute;

import de.fraunhofer.isst.automotive.stars.reqmon.mapping.definitions.IGenerator;

public class ExistingGeneratorsHandler {
	private static final String IGENERATOR_ID =
			"de.fraunhofer.isst.automotive.stars.reqmon.sdl.mapping.generator";

	private IExtensionRegistry registry;
	private IConfigurationElement[] config;
	private boolean isRegistry;
	
	public ExistingGeneratorsHandler() {
		registry = Platform.getExtensionRegistry();
		if (registry == null) {
			System.out.println("No registry!");
			isRegistry = false;
		}
		else {
			System.out.println("Registry: " + registry);
			isRegistry = true;
		}
		if (isRegistry) {
			config = registry.getConfigurationElementsFor(IGENERATOR_ID);
		}
	}
	
	public void execute() {
		if (!isRegistry) {
			return;
		}
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
	
	public void generateGeneratorList(GeneratorController gc) {
		if (!isRegistry) {
			return;
		}
		try {
			for (IConfigurationElement e : config) {
				System.out.println("Generate Generator");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IGenerator) {
					generateList(o, gc);
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void executeSelectedGenerator(String name) {
		if (!isRegistry) {
			return;
		}
		try {
			for (IConfigurationElement e : config) {
				System.out.println("Execute Genrator: " + name);
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IGenerator) {
					executeGenerator(o, name);
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
				System.out.println("Generator: " + ((IGenerator) o).getName() + " exists.");
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in client");
			}
		};
		SafeRunner.run(runnable);
	}
	
	private void executeGenerator(Object o, String name) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				if (((IGenerator) o).getName().equals(name)) {
					((IGenerator) o).generate();
				}
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in client");
			}
		};
		SafeRunner.run(runnable);
	}
	
	private void generateList(final Object o, GeneratorController gc) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				String name = ((IGenerator) o).getName();
				String label = ((IGenerator) o).getLabel();
				gc.addGenerator(name, label);
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in client");
			}
		};
		SafeRunner.run(runnable);
	}
}
