package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp.TestAppRequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp.TestAppRequirementImporter;

public class RequirementController implements IRequirementController {
	
	private static final String IREQUIREMENT_ID =
			"de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.requirementimporter";
	private IExtensionRegistry registry;
	private IConfigurationElement[] configReq;
	private boolean isRegistry;
	private boolean isExtReq;
	private IRequirementImporter reqImp;
	private List<IRequirementElement> requirements;
	private String[] filter;
	private MappingPage mp;
	
	public RequirementController(MappingPage mp) {
		registry = Platform.getExtensionRegistry();
		this.mp = mp;
		if (registry == null) {
			System.out.println("No registry!");
			isRegistry = false;
		}
		else {
			isRegistry = true;
		}
		if (isRegistry) {
			configReq = registry.getConfigurationElementsFor(IREQUIREMENT_ID);
			
			if (configReq.length == 0) {
				isExtReq = false;
				System.out.println("No Requirement registered!");
			}
			else {
				isExtReq = true;
				createRequirementImporter();
				System.out.println("Requirement registered");
			}
		}
		else {
			isExtReq = false;
		}
	}
	
	
	
	public List<IRequirementElement> getRequirements() {
		return requirements;
	}


	@Override
	public void setRequirements(List<IRequirementElement> requirements) {
		this.requirements = requirements;
	}



	public void checkExtensions() {
		if (!isRegistry) {
			return;
		}
		try {
			for (IConfigurationElement e : configReq) {
				System.out.println("Evaluating requirement extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof TestAppRequirementElement) {
					testReqExtension(o);
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void setPath(String path) {
		if (isExtReq) {
			setRequirementPath(path);
		}
	}
	
	public void setFilterExt(String[] filter) {
		this.filter = filter;
	}
	
	public String[] getFilterExt() {
		if (isExtReq) {
			setRequirementFilterExt();
			return filter;
		}
		else {
			return null;
		}
	}

	public void execute(Display display) {
		if (isExtReq) {
			exectuteRequirementImporter(this, display);
			System.out.println(requirements.size());
		}
	}
	
	@Override
	public void updateList() {
		mp.updateList();
	}
	
	
	private void setRequirementPath(String path) {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				reqImp.setPath(path);
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in requirement client: can not set path!");
				e.printStackTrace();
			}
		};
		SafeRunner.run(runnable);
	}
	
	private void setRequirementFilterExt() {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				setFilterExt(reqImp.getFilterExt());
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in requirement client: can not get filter!");
				e.printStackTrace();
			}
		};
		SafeRunner.run(runnable);
	}
	
	
	private void exectuteRequirementImporter(IRequirementController rc, Display display) {
		if (!isRegistry) {
			return;
		}
		
		Job job = new Job("Parse file") { 
			protected IStatus run(IProgressMonitor monitor) {
				try {
					//TimeUnit.SECONDS.sleep(5);
					reqImp.execute(rc);
					rc.setRequirements(reqImp.getRequirements());
					
					display.asyncExec(new Runnable() {
					
					@Override
					public void run() {
						rc.updateList();
					}
				});
					
				} 
				catch (Exception ex) {
					System.out.println("Exception in requirement client: can not read file!");
					ex.printStackTrace();
				}
				return Status.OK_STATUS;
			}
		};
		
		job.setPriority(Job.SHORT);
		job.schedule();
		
		
		
	}
	
	
	private void testReqExtension(Object o) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				System.out.println("Requirement exists.");
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in requirement client");
			}
		};
		SafeRunner.run(runnable);
		
	}
	
	private void createRequirementImporter() {
		if (!isRegistry) {
			return;
		}
		try {
			if (configReq.length == 0) {
				return;
			}
			final Object o = configReq[0].createExecutableExtension("class");
			if (o instanceof IRequirementImporter) {
				reqImp = (IRequirementImporter) o;
			}
			
		} catch (CoreException ex) {
			ex.printStackTrace();
		}
	}
	
	/*private void setRequirementElemSize() {
	if (!isRegistry) {
		return;
	}
	ISafeRunnable runnable = new ISafeRunnable() {
		
		@Override
		public void run() throws Exception {
			setElementSize(reqElem.getElementSize());
		}
		
		@Override
		public void handleException(Throwable e) {
			System.out.println("Exception in requirement client: can not get element size!");
		}
	};
	SafeRunner.run(runnable);
}

private void setRequirementElement(int index) {
	if (!isRegistry) {
		return;
	}
	ISafeRunnable runnable = new ISafeRunnable() {
		
		@Override
		public void run() throws Exception {
			setElement(reqElem.getElement(index));
		}
		
		@Override
		public void handleException(Throwable e) {
			System.out.println("Exception in requirement client: can not get element!");
		}
	};
	SafeRunner.run(runnable);
}

private void setRequirementType(int index) {
	if (!isRegistry) {
		return;
	}
	ISafeRunnable runnable = new ISafeRunnable() {
		
		@Override
		public void run() throws Exception {
			setType(reqElem.getType(index));
		}
		
		@Override
		public void handleException(Throwable e) {
			System.out.println("Exception in requirement client: can not get type!");
		}
	};
	SafeRunner.run(runnable);
}*/
	

}
