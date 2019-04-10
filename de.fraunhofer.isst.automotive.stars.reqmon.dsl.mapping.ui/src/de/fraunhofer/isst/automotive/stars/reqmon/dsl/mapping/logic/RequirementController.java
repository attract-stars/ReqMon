package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.logic;

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

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.definitions.RequirementElement;

public class RequirementController {
	
	private static final String IREQUIREMENT_ID =
			"de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.requirement";
	private IExtensionRegistry registry;
	private IConfigurationElement[] configReq;
	private RequirementElement reqElem;
	private boolean isRegistry;
	private boolean isExtReq;
	private RequirementElement reqElemExt;
	private String[] filter;
	private int elemSize;
	private String element;
	private String type;
	
	public RequirementController() {
		registry = Platform.getExtensionRegistry();
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
				createRequirementElement();
				createSampleElements();
				System.out.println("Requirement registered");
			}
		}
		else {
			isExtReq = false;
		}
		
		
		
		if (!isExtReq) {
			this.reqElem = new RequirementElement();
			reqElem.createSampleElements();
		}
	}
	
	public void checkExtensions() {
		if (!isRegistry) {
			return;
		}
		try {
			for (IConfigurationElement e : configReq) {
				System.out.println("Evaluating requirement extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof RequirementElement) {
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
		else {
			reqElem.setPath(path);
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
			return reqElem.getFilterExt();
		}
	}

	public void setElementSize(int elemSize) {
		this.elemSize = elemSize;
	}

	public int getElementSize() {
		if (isExtReq) {
			setRequirementElemSize();
			return elemSize;
		}
		else {
			return reqElem.getElementSize();
		}
	}
	
	public void setElement(String element) {
		this.element = element;
	}
	
	public String getElement(int index) {
		if (isExtReq ) {
			setRequirementElement(index);
			return element;
		}
		else {
			return reqElem.getElement(index);
		}
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getType(int index) {
		if (isExtReq) {
			setRequirementType(index);
			return type;
		}
		else {
			return reqElem.getType(index);
		}
	}

	public void executeRequirement() {
		if (isExtReq) {
			readRequirements();
		}
		else {
			reqElem.readFile();
		}
	}
	
	
	private void setRequirementPath(String path) {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				reqElemExt.setPath(path);
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in requirement client: can not set path!");
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
				setFilterExt(reqElemExt.getFilterExt());
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in requirement client: can not get filter!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	private void setRequirementElemSize() {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				setElementSize(reqElemExt.getElementSize());
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
				setElement(reqElemExt.getElement(index));
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
				setType(reqElemExt.getType(index));
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in requirement client: can not get type!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	private void readRequirements() {
		if (!isRegistry) {
			return;
		}
		Job job = new Job("Read file") { 
			protected IStatus run(IProgressMonitor monitor) {
				try {
					reqElemExt.readFile();
				} 
				catch (Exception ex) {
					System.out.println("Exception in requirement client: can not read file!");
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
	
	private void createRequirementElement() {
		if (!isRegistry) {
			return;
		}
		try {
			if (configReq.length == 0) {
				return;
			}
			final Object o = configReq[0].createExecutableExtension("class");
			if (o instanceof RequirementElement) {
				reqElemExt = (RequirementElement) o;
			}
			
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private void createSampleElements() {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				reqElemExt.createSampleElements();
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in requirement client: can not create samples!");
			}
		};
		SafeRunner.run(runnable);
	}

}
