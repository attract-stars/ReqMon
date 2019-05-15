package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic;

import java.util.List;
//import java.util.concurrent.TimeUnit;
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
import org.eclipse.swt.widgets.Display;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementImporter;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.MappingPage;

/**
 * This class implements the IRequirementController interface.
 * It manages the RequirementImporter extensions.
 * 
 * @author sgraf
 *
 */
public class RequirementController implements IRequirementController {
	
	private static final String IREQUIREMENT_ID =
			"de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.requirementimporter";
	private IExtensionRegistry registry;
	private IConfigurationElement[] configReq;
	private boolean isRegistry;
	private IRequirementImporter reqImp;
	private List<IRequirementElement> requirements;
	private MappingPage mp;
	
	/**
	 * This constructor checks if a registry exists and if RequirementImporters are registered.
	 */
	public RequirementController(MappingPage mp) {
		registry = Platform.getExtensionRegistry();
		this.mp = mp;
		if (registry == null) {
			System.out.println("No registry!");
			isRegistry = false;
		}
		else {
			isRegistry = true;
			configReq = registry.getConfigurationElementsFor(IREQUIREMENT_ID);
			
			if (configReq.length == 0) {
				System.out.println("No RequirementImporter registered!");
			}
		}
	}
	
	/**
	 * Returns the list of requirement elements.
	 * @return the list of requirement elements
	 */
	public List<IRequirementElement> getRequirements() {
		return requirements;
	}

	/**
	 * Checks if the registered RequirementImporters are executable.
	 */
	public void checkExtensions() {
		if (!isRegistry) {
			return;
		}
		try {
			for (IConfigurationElement e : configReq) {
				System.out.println("Evaluating RequirementImporter extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IRequirementImporter) {
					testReqExtension(o);
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Executes the RequirementImporter for the given requirement file path.
	 * @param display the Display (to make an asynchronous update of the GUI possible)
	 * @param path the path of the requirement file
	 */
	public void execute(Display display, String path) {
		exectuteRequirementImporter(this, display, path);
	}
	
	@Override
	public void updateList(List<? extends IRequirementElement> requirements) {
		this.requirements = (List<IRequirementElement>) requirements;
		mp.updateList();
	}
	
	/**
	 * Executes the RequirementImporter. The RequirementImporter is selected in dependence of the file extension.
	 * @param rc this
	 * @param display the Display
	 * @param path the path of the requirement file
	 */
	private void exectuteRequirementImporter(IRequirementController rc, Display display, String path) {
		if (!isRegistry) {
			return;
		}
		
		Job job = new Job("Parse file") { 
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					//TimeUnit.SECONDS.sleep(5);
					
					reqImp = null;
					/** select the importer that can read the file of the given path */
					for (IConfigurationElement e : configReq) {
						final Object o = e.createExecutableExtension("class");
						if (o instanceof IRequirementImporter) {
							System.out.println("Filter: " + e.getAttribute("filter"));
							String filter = e.getAttribute("filter");
							String[] words = path.split(Pattern.quote("."));
							if (words.length > 0 && filter.contains(words[words.length-1])) {
								reqImp = (IRequirementImporter) o;
								break;
							}
						}
					}
					
					if (reqImp == null) {
						System.out.println("There is no RequirementImporter for this file!");
					}
					else {
						display.asyncExec(new Runnable() {
							
							@Override
							public void run() {
								reqImp.execute(rc, path);
							}
							
						});
					}
					
				} 
				catch (Exception ex) {
					System.out.println("Exception in requirement importer client:");
					ex.printStackTrace();
				}
				return Status.OK_STATUS;
			}
		};
		
		job.setPriority(Job.SHORT);
		job.schedule();
		
	}
	
	/**
	 * Checks if the RequirementImporter is executable.
	 * @param o an object of the type IRequirementImporter
	 */
	private void testReqExtension(Object o) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				System.out.println("RequirementImporter exists.");
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in requirement importer client");
			}
		};
		SafeRunner.run(runnable);
		
	}
	
	
}
