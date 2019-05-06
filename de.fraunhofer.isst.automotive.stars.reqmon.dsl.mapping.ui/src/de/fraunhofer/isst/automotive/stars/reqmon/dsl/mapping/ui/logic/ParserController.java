package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic;

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

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.ILanguageParser;

public class ParserController {
	
	private static final String IPARSER_ID =
			"de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.parser";
	
	private IExtensionRegistry registry;
	private IConfigurationElement[] configPars;
	private boolean isExtPars;
	private boolean isRegistry;
	private ReqDSLParser reqParser;
	private ILanguageParser parserExt;
	
	public ParserController(boolean isApp) {
		registry = Platform.getExtensionRegistry();
		if (registry == null) {
			System.out.println("No registry!");
			isRegistry = false;
		}
		else {
			isRegistry = true;
		}
		if (isRegistry) {
			configPars = registry.getConfigurationElementsFor(IPARSER_ID);
			
			if (configPars.length == 0) {
				isExtPars = false;
				System.out.println("No Parser registered!");
			}
			else {
				isExtPars = true;
				createParser();
				System.out.println("Parser registered");
			}
		}
		else {	
			isExtPars = false;
		}
		
		if (!isExtPars) {
			reqParser = new ReqDSLParser(isApp);
		}
	}
	
	public void checkExtensions() {
		if (!isRegistry) {
			return;
		}
		try {
			for (IConfigurationElement e : configPars) {
				System.out.println("Evaluating parser extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof ILanguageParser) {
					testParsExtension(o);
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void parserInput(String input) {
		if (isExtPars) {
			parseInputExt(input);
		}
		else {
			reqParser.parserInput(input);
		}
	}
	
	private void testParsExtension(Object o) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				System.out.println("Parser exists.");
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in parser client");
			}
		};
		SafeRunner.run(runnable);
		
	}
	
	private void createParser() {
		if (!isRegistry) {
			return;
		}
		try {
			if (configPars.length == 0) {
				return;
			}
			final Object o = configPars[0].createExecutableExtension("class");
			if (o instanceof ILanguageParser) {
				parserExt = (ILanguageParser) o;
			}
			
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private void parseInputExt(String input) {
		Job job = new Job("Parse input") { 
			protected IStatus run(IProgressMonitor monitor) {
				try {
					parserExt.parserInput(input);
				} 
				catch (Exception ex) {
					System.out.println("Exception in parser client: can not parse input!");
				}
				return Status.OK_STATUS;
			}
		};
		
		job.setPriority(Job.SHORT);
		job.schedule();
	}

}
