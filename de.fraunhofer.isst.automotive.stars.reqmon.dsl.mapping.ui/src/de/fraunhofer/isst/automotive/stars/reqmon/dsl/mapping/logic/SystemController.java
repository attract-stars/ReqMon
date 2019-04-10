package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.logic;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.definitions.SystemElement;

public class SystemController {
	
	private static final String ISYSTEM_ID =
			"de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.system";
	
	private IExtensionRegistry registry;
	private IConfigurationElement[] configSys;
	private boolean isRegistry;
	private SystemElement sysElem;
	private SystemElement sysElemExt;
	private String[] filter;
	private boolean isExtSys;
	
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
				isExtSys = false;
				System.out.println("No System registered!");
			}
			else {
				isExtSys = true;
				createSystemElement();
				System.out.println("System registered");
			}
		}
		else {
			isExtSys = false;
		}
		
		if (!isExtSys) {
			this.sysElem = new SystemElement();
		}
	}
	
	public void checkExtensions() {
		if (!isRegistry) {
			return;
		}
		try {
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
	
	public void setPath(String path) {
		if (isExtSys) {
			setSystemPath(path);
		}
		else {
			sysElem.setPath(path);
		}
	}
	
	public void setFilterExt(String[] filterExt) {
		this.filter = filterExt;
	}
	
	public String[] getFilterExt() {
		if (isExtSys) {
			setSystemFilterExt();
			System.out.println("filter: " + filter[0]);
			return this.filter;
		}
		else {
			System.out.println("Default-Filter");
			return sysElem.getFilterExt();
		}
	}
	
	public void executeSystem() {
		if (isExtSys) {
			System.out.println("Extern System executed");
			executeExtSystem();
		}
		else {
			sysElem.execute();
		}
	}
	
	public void setSystemPath(String path) {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				sysElemExt.setPath(path);
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in system client: can not set path!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	public void setSystemFilterExt() {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				setFilterExt(sysElemExt.getFilterExt());
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in system client: can not get filter!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	public void executeExtSystem() {
		if (!isRegistry) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				sysElemExt.execute();
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in system client: can not be executed!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	private void testSysExtension(Object o) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				System.out.println("System exists.");
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in system client");
			}
		};
		SafeRunner.run(runnable);
		
	}
	
	private void createSystemElement() {
		if (!isRegistry) {
			return;
		}
		try {
			if (configSys.length == 0) {
				return;
			}
			final Object o = configSys[0].createExecutableExtension("class");
			if (o instanceof SystemElement) {
				sysElemExt = (SystemElement) o;
			}
			
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}

	
	
}
