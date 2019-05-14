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

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.ISystemImporter;

public class SystemController {
	
	private static final String ISYSTEM_ID =
			"de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.systemimporter";
	
	private IExtensionRegistry registry;
	private IConfigurationElement[] configSys;
	private boolean isRegistry;
	private ISystemImporter sysImporter;
	
	public SystemController() {
		registry = Platform.getExtensionRegistry();
		if (registry == null) {
			System.out.println("No registry!");
			isRegistry = false;
		}
		else {
			isRegistry = true;
		}
		if (isRegistry) {
			configSys = registry.getConfigurationElementsFor(ISYSTEM_ID);
			
			if (configSys.length == 0) {
				System.out.println("No SystemImporter registered!");
			}
		}
	}
	
	public void checkExtensions() {
		if (!isRegistry) {
			return;
		}
		try {
			for (IConfigurationElement e : configSys) {
				System.out.println("Evaluating SystemImporter extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof ISystemImporter) {
					testSysExtension(o);
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
	public void execute(String path) {
		if (!isRegistry) {
			return;
		}
		
		Job job = new Job("Parse file") { 
			protected IStatus run(IProgressMonitor monitor) {
				try {
					//TimeUnit.SECONDS.sleep(5);
					sysImporter = null;
					/** select the importer that can read the file of the given path */
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
					if (sysImporter != null) {
						sysImporter.execute(path);
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
	
	
	
	
}
