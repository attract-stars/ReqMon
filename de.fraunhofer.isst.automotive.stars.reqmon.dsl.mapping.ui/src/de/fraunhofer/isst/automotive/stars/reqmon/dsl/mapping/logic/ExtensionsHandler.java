package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.logic;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.definitions.IGenerator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.definitions.IRequirement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.definitions.ISystem;

public class ExtensionsHandler {
	
	private static final String IGENERATOR_ID =
			"de.fraunhofer.isst.automotive.stars.reqmon.sdl.mapping.generator";
	private static final String IREQUIREMENT_ID =
			"de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.requirement";
	private static final String ISYSTEM_ID =
			"de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.system";

	private IExtensionRegistry registry;
	private IConfigurationElement[] configGen;
	private IConfigurationElement[] configReq;
	private IConfigurationElement[] configSys;
	private boolean isRegistry;
	
	public ExtensionsHandler() {
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
			configGen = registry.getConfigurationElementsFor(IGENERATOR_ID);
			configReq = registry.getConfigurationElementsFor(IREQUIREMENT_ID);
			configSys = registry.getConfigurationElementsFor(ISYSTEM_ID);
		}
	}
	
	public void checkExtensions() {
		if (!isRegistry) {
			return;
		}
		try {
			for (IConfigurationElement e : configGen) {
				System.out.println("Evaluating generation extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IGenerator) {
					testGenExtension(o);
				}
			}
			for (IConfigurationElement e : configReq) {
				System.out.println("Evaluating requirement extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IRequirement) {
					testReqExtension(o);
				}
			}
			for (IConfigurationElement e : configSys) {
				System.out.println("Evaluating system extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof ISystem) {
					testSysExtension(o);
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
			for (IConfigurationElement e : configGen) {
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
			for (IConfigurationElement e : configGen) {
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
	
	private void testGenExtension(final Object o) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				System.out.println("Generator: " + ((IGenerator) o).getName() + " exists.");
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in generator client");
			}
		};
		SafeRunner.run(runnable);
	}

	private void testReqExtension(Object o) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				System.out.println("Requirement for files with the extensions: " + ((IRequirement) o).getFileExtension() + " exists.");
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in requirement client");
			}
		};
		SafeRunner.run(runnable);
		
	}
	
	private void testSysExtension(Object o) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				System.out.println("System for files with the extensions: " + ((ISystem) o).getFileExtension() + " exists.");
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in system client");
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
