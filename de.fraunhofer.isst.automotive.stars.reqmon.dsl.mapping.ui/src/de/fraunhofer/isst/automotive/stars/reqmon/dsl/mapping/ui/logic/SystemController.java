package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic;

import java.util.regex.Pattern;

import javax.swing.event.EventListenerList;

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
import org.eclipse.swt.widgets.Display;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.ISystemImporter;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.ValidateListener;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.model.ValidateEvent;

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
	private EventListenerList listeners = new EventListenerList();

	
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
	 * Adds the given listener to the listener list.
	 * @param listener a ValidateListener
	 */
	public void addValidateListener(ValidateListener listener) {
		listeners.add(ValidateListener.class, listener);
	}
	
	/**
	 * Removes the given listener from the listener list.
	 * @param listener a ValidateListener
	 */
	public void removeValidateListener(ValidateListener listener) {
		listeners.remove(ValidateListener.class, listener);
	}
	
	/**
	 * Notifies the ValidateListener.
	 * @param event a ValidateEvent
	 */
	protected synchronized void notifyAdvertisment(ValidateEvent event) {
		for (ValidateListener lis : listeners.getListeners(ValidateListener.class)) {
			lis.advertisment(event);
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
	 * @param path the path of the system file
	 * @param display the Display of the MappingPage
	 */
	public void checkFile(String path, Display display) {
		Job job = new Job("Check file") { 
			protected IStatus run(IProgressMonitor monitor) {
				try {
					sysImporter = null;
					
					// select the importer that can read the file of the given path 
					for (IConfigurationElement e : configSys) {
						final Object o = e.createExecutableExtension("class");
						if (o instanceof ISystemImporter) {
							//System.out.println("Filter: " + e.getAttribute("filter"));
							String filter = e.getAttribute("filter");
							String[] words = path.split(Pattern.quote("."));
							if (words.length > 0 && filter.contains(words[words.length-1])) {
								sysImporter = (ISystemImporter) o;
								break;
							}
						}
					}
					
					// validate the system model
					if (sysImporter != null) {
							
						display.asyncExec(new Runnable() {
							
							@Override
							public void run() {
								boolean isValid = sysImporter.check(path);
								
								if (isValid) {
									sysModel = sysImporter.getSystemModel();
								}
								
								notifyAdvertisment(new ValidateEvent(this, isValid));
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
