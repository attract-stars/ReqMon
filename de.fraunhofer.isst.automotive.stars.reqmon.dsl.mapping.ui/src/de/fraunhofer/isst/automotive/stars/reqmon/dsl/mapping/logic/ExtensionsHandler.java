package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.logic;

import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ICoreRunnable;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.ui.di.UISynchronize;

import com.google.inject.Inject;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.definitions.IGenerator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.definitions.RequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.definitions.SystemElement;

public class ExtensionsHandler {
	
	@Inject UISynchronize sync;
	
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
	private boolean isRequirement;
	private boolean isSystem;
	private RequirementElement reqElem;
	private SystemElement sysElem;
	
	public ExtensionsHandler() {
		registry = Platform.getExtensionRegistry();
		if (registry == null) {
			System.out.println("No registry!");
			isRegistry = false;
		}
		else {
			//System.out.println("Registry: " + registry);
			isRegistry = true;
		}
		if (isRegistry) {
			configGen = registry.getConfigurationElementsFor(IGENERATOR_ID);
			configReq = registry.getConfigurationElementsFor(IREQUIREMENT_ID);
			configSys = registry.getConfigurationElementsFor(ISYSTEM_ID);
			
			if (configReq.length == 0) {
				isRequirement = false;
				System.out.println("No Requirement registered!");
			}
			else {
				isRequirement = true;
				System.out.println("Requirement registered");
			}
			
			if (configSys.length == 0) {
				isSystem = false;
				System.out.println("No System registered!");
			}
			else {
				isSystem = true;
				System.out.println("System registered");
			}
		}
		else {
			isRequirement = false;
			isSystem = false;
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
				if (o instanceof RequirementElement) {
					testReqExtension(o);
				}
			}
			for (IConfigurationElement e : configSys) {
				System.out.println("Evaluating system extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof SystemElement) {
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
	
	public boolean isRequirement() {
		return isRequirement;
	}
	
	public void createRequirementElement() {
		if (!isRegistry) {
			return;
		}
		try {
			if (configReq.length == 0) {
				return;
			}
			final Object o = configReq[0].createExecutableExtension("class");
			if (o instanceof RequirementElement) {
				reqElem = (RequirementElement) o;
			}
			
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void setRequirementPath(String path) {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				reqElem.setPath(path);
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in requirement client: can not set path!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	public void setRequirementFilterExt(RequirementController rc) {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				rc.setFilterExt(reqElem.getFilterExt());
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in requirement client: can not get filter!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	public void setRequirementElemSize(RequirementController rc) {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				rc.setElementSize(reqElem.getElementSize());
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in requirement client: can not get element size!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	public void setRequirementElement(int index, RequirementController rc) {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				rc.setElement(reqElem.getElement(index));
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in requirement client: can not get element!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	public void setRequirementType(int index, RequirementController rc) {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				rc.setType(reqElem.getType(index));
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in requirement client: can not get type!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	public void readRequirements() {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				reqElem.readFile();
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in requirement client: can not read File!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	public void createSampleElements() {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				reqElem.createSampleElements();
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in requirement client: can not create samples!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	public boolean isSystem() {
		return isSystem;
	}
	
	public void createSystemElement() {
		if (!isRegistry) {
			return;
		}
		try {
			if (configSys.length == 0) {
				return;
			}
			final Object o = configSys[0].createExecutableExtension("class");
			if (o instanceof SystemElement) {
				sysElem = (SystemElement) o;
			}
			
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void setSystemPath(String path) {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				sysElem.setPath(path);
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in system client: can not set path!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	public void setSystemFilterExt(SystemController sc) {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				sc.setFilterExt(sysElem.getFilterExt());
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in system client: can not get filter!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	public void executeSystem() {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				sysElem.execute();
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in system client: can not be executed!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	private void testGenExtension(final Object o) {
		Job job = new Job("Test Generator") { 
			protected IStatus run(IProgressMonitor monitor) {
				try {
					TimeUnit.SECONDS.sleep(2);
					System.out.println("Generator: " + ((IGenerator) o).getName() + " exists.");
				} catch (InterruptedException e) {
				System.out.println("InterruptedException!");
				} 
				catch (Exception ex) {
					System.out.println("Exception in generator client");
				}
				return Status.OK_STATUS;
			}
		};
		
		job.setPriority(Job.SHORT);
		job.schedule();
		
		/*
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
		SafeRunner.run(runnable);*/
	}

	private void testReqExtension(Object o) {
		/*Job job = Job.create("Test Requirement", (ICoreRunnable) monitor -> {
			try {
				//TimeUnit.SECONDS.sleep(1);
				System.out.println("Requirement for files with the extensions: " + ((RequirementElement) o).getFilterExt() + " exists.");
			//} catch (InterruptedException e) {
			//	System.out.println("InterruptedException!");
			} 
			catch (Exception ex) {
				System.out.println("Exception in requirement client");
			}
		});
		
		job.schedule();*/
		
		
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				System.out.println("Requirement for files with the extensions: " + ((RequirementElement) o).getFilterExt() + " exists.");
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in requirement client");
			}
		};
		SafeRunner.run(runnable);
		
	}
	
	private void testSysExtension(Object o) {
		/*Job job = Job.create("Test System", (ICoreRunnable) monitor -> {
			try {
				//TimeUnit.SECONDS.sleep(1);
				System.out.println("System for files with the extensions: " + ((SystemElement) o).getFilterExt() + " exists.");
			//} catch (InterruptedException e) {
			//	System.out.println("InterruptedException!");
			} 
			catch (Exception ex) {
				System.out.println("Exception in system client");
			}
		});
		
		job.schedule();*/
		
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				System.out.println("System for files with the extensions: " + ((SystemElement) o).getFilterExt() + " exists.");
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in system client");
			}
		};
		SafeRunner.run(runnable);
		
	}
	
	private void executeGenerator(Object o, String name) {
		Job job = new Job("Execute Generator") { 
			protected IStatus run(IProgressMonitor monitor) {
				try {
					if (((IGenerator) o).getName().equals(name)) {
						((IGenerator) o).generate();
						//TimeUnit.SECONDS.sleep(3);
					}
				//} catch (InterruptedException e) {
				//System.out.println("InterruptedException!");
				} 
				catch (Exception ex) {
					System.out.println("Exception in generator client: can not generate code!");
				}
				return Status.OK_STATUS;
			}
		};
		
		job.setPriority(Job.SHORT);
		job.schedule();
		
		/*ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				if (((IGenerator) o).getName().equals(name)) {
					((IGenerator) o).generate();
				}
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in generator client: can not generate code!");
			}
		};
		SafeRunner.run(runnable);*/
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
				System.out.println("Exception in generator client: can not generate List!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	
}
