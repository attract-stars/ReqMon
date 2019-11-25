package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic;


import java.util.Observable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.swt.widgets.Display;

import com.google.inject.Injector;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingParser;


/**
 * This class manages the MappingParser extensions for the mapping text field. 
 * The MappingParser is selected in dependency of the mapping language.  
 * 
 * @author sgraf
 *
 */
public class MappingParserController extends Observable {
	
	private static final String IPARSER_ID =
			"de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.mappingparser";
	
	private IExtensionRegistry registry;
	private IConfigurationElement[] configPars;
	private boolean isPars;
	private boolean isRegistry;
	private IMappingParser parser;
	private Injector dslInjector;
	
	
	/**
	 * This constructor checks if a registry exists and if MappingParsers are registered.
	 */
	public MappingParserController() {
		registry = Platform.getExtensionRegistry();
		isPars = false;
		
		if (registry == null) {
			System.out.println("No registry!");
			isRegistry = false;
		}
		else {
			isRegistry = true;
			configPars = registry.getConfigurationElementsFor(IPARSER_ID);
			
			if (configPars.length == 0) {
				System.out.println("No Parser registered!");
			}
		}
	}
	
	/**
	 * Selects a MappingParser for the given language from the registered parsers.
	 * If no suitable parser exists no parser will be selected.
	 * @param language the mapping language
	 */
	public void selectMappingParser(String language) {
		if (!isRegistry) {
			return;
		}
		try {
			if (configPars.length == 0) {
				return;
			}
			for (IConfigurationElement e : configPars) {
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IMappingParser && e.getAttribute("lang").equals(language)) {
					parser = (IMappingParser) o;
					isPars = true;
					break;
				}
			}
		} catch (CoreException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Checks if the registered MappingParsers are executable.
	 */
	public void checkExtensions() {
		if (!isRegistry) {
			return;
		}
		try {
			for (IConfigurationElement e : configPars) {
				System.out.println("\nEvaluating parser extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IMappingParser) {
					testParsExtension(o);
				}
			}
		} catch (CoreException ex) {
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * Returns the mapping language injector.
	 * @return the mapping language injector
	 */
	public synchronized Injector getDslInjector() {
		return this.dslInjector;
	}
	
	public void setDslInjector(Injector dslInjector) {
		synchronized (dslInjector) {
			this.dslInjector = dslInjector;
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Creates the mapping language injector.
	 * @param systemModelUri the URI of the selected system model
	 */
	public void createDslInjector(URI systemModelUri, Display display) {
		if (!isPars) {
			return;
		}
		
		// subMonitor.split for avoiding the case that the job is still running after closing of the application
		Job job = Job.create("Create Parser", monitor -> {
			SubMonitor subMonitor = SubMonitor.convert(monitor, 10);
			
			try {
				display.asyncExec(new Runnable() {
					
					@Override
					public void run() {
						setDslInjector(parser.getDslInjector(systemModelUri));
					}
					
				});
				
				
			} catch (Exception ex) {
				System.out.println("Exception in parser client:");
				ex.printStackTrace();
			} finally {
				subMonitor.split(9);
			}
			
		});
		
		job.setPriority(Job.SHORT);
		job.schedule();
	}
	
		/*Job job = new Job("Parse input") { 
			protected IStatus run(IProgressMonitor monitor) {
				try {
					mpc.setDsl(parser.getDslInjector(systemModelUri));
					if (mpc.dslInjector == null) {
						mpc.isInjectorCreationFailed = true;
					}
				} 
				catch (Exception ex) {
					System.out.println("Exception in parser client:");
					ex.printStackTrace();
					mpc.isInjectorCreationFailed = true;
				}
				return Status.OK_STATUS;
			}
		};*/
		
		

	/**
	 * Checks if the MappingParser is executable.
	 * @param o an object of the type IMappingParser
	 */
	private void testParsExtension(Object o) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				System.out.println("Parser exists.");
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in parser client");
				e.printStackTrace();
			}
		};
		SafeRunner.run(runnable);
		
	}

	
	
	
}
