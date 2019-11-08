package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic;

import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.ISystemImporter;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.MappingPage;

/**
 * This class manages the SystemImporter extensions.
 * 
 * @author sgraf
 *
 */
public class SystemController {
	
	private static final String ISYSTEM_ID =
			"de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.systemimporter";
	
	private IExtensionRegistry registry;
	private IConfigurationElement[] configSys;
	private boolean isRegistry;
	private ISystemImporter sysImporter;
	private EObject sysModel;
	
	
	/**
	 * This constructor checks if a registry exists and if SystemImporters are registered.
	 */
	public SystemController() {
		registry = Platform.getExtensionRegistry();
		
		if (registry == null) {
			System.out.println("No registry!");
			isRegistry = false;
		}
		else {
			isRegistry = true;
			configSys = registry.getConfigurationElementsFor(ISYSTEM_ID);
			
			if (configSys.length == 0) {
				System.out.println("No SystemImporter registered!");
			}
			
		}
	}
	
	/**
	 * Checks if the registered SystemImporters are executable.
	 */
	public void checkExtensions() {
		if (!isRegistry) {
			return;
		}
		try {
			for (IConfigurationElement e : configSys) {
				System.out.println("\nEvaluating SystemImporter extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof ISystemImporter) {
					testSysExtension(o);
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
	/**
	 * Selects the SystemImporter in dependence of the file extension.
	 * If an appropriate SystemImporter exists it
	 * checks if the system model of the given path has syntax errors. 
	 * After the validation it calls the createDslInjector method of the MappingPage.
	 * @param path the path of the system file
	 * @param mp the MappingPage
	 */
	public void checkFileAndCreateInjectorAndUpdateList(String path, MappingPage mp) {
		Job job = new Job("Check file") { 
			protected IStatus run(IProgressMonitor monitor) {
				try {
					sysImporter = null;
					
					// select the importer that can read the file of the given path 
					for (IConfigurationElement e : configSys) {
						final Object o = e.createExecutableExtension("class");
						if (o instanceof ISystemImporter) {
							System.out.println("Filter: " + e.getAttribute("filter"));
							String filter = e.getAttribute("filter");
							String[] words = path.split(Pattern.quote("."));
							if (words.length > 0 && filter.contains(words[words.length-1])) {
								sysImporter = (ISystemImporter) o;
								break;
							}
						}
					}
					
					// validate the system model, create Injector, show the system model path 
					// and update the mapping list
					if (sysImporter != null) {
						boolean isValid = sysImporter.check(path);
						
						if (isValid) {
							sysModel = sysImporter.getSystemModel();
						}
						
						mp.getDisplay().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								mp.createDslInjectorAndUpdateList(isValid);
							}
							
						});
						
					}
					else {
						System.out.println("There is no SystemImporter for this file!");
					}
				} 
				catch (Exception ex) {
					System.out.println("Exception in system importer client:");
					ex.printStackTrace();
				}
				return Status.OK_STATUS;
			}
		};
		
		job.setPriority(Job.SHORT);
		job.schedule();
	}
	

	/**
	 * Checks if the SystemImporter is executable.
	 * @param o an object of the type ISystemImporter
	 */
	private void testSysExtension(Object o) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				System.out.println("SystemImporter exists.");
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in system importer client");
			}
		};
		SafeRunner.run(runnable);
		
	}

	public EObject getSysModel() {
		return sysModel;
	}

	
	
	
	
}
