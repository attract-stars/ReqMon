package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic;

import java.util.ArrayList;
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

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IGenerator;

public class GeneratorController {
	
	private static final String IGENERATOR_ID =
			"de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.generator";
	private IExtensionRegistry registry;
	private IConfigurationElement[] configGen;
	private boolean isRegistry;
	
	private int index;
	private List<String> generators;
	private List<String> generateLabels;
	
	public GeneratorController() {
		registry = Platform.getExtensionRegistry();
		if (registry == null) {
			System.out.println("No registry!");
			isRegistry = false;
		}
		else {
			isRegistry = true;
		}
		if (isRegistry) {
			configGen = registry.getConfigurationElementsFor(IGENERATOR_ID);
			
			if (configGen.length == 0) {
				System.out.println("No Generator registered!");
			}
			else {
				System.out.println("Generator registered");
			}
		}
		
		this.index = 0;
		this.generators = new ArrayList<String>();
		this.generateLabels = new ArrayList<String>();
		
		generateGeneratorList();
		if (getGenerators().isEmpty()) {
			generateSampleList();
		}
	}
	
	public void checkExtensions() {
		if (!isRegistry) {
			return;
		}
		try {
			for (IConfigurationElement e : configGen) {
				System.out.println("Evaluating generator extension");
				final Object o = e.createExecutableExtension("class");
				System.out.println("Attribute name: " + e.getAttribute("lang"));
				if (o instanceof IGenerator) {
					testGenExtension(o);
				}
			}
		}
		catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void generateGeneratorList() {
		if (!isRegistry) {
			return;
		}
		try {
			for (IConfigurationElement e : configGen) {
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IGenerator) {
					generateList(o);
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

	public int getIndex() {
		return index;
	}
	
	public List<String> getGenerators() {
		return generators;
	}

	public void setGenerators(List<String> generators) {
		this.generators = generators;
	}
	
	public List<String> getGenerateLabels() {
		return generateLabels;
	}

	public void setGenerateLabels(List<String> generateLabels) {
		this.generateLabels = generateLabels;
	}

	public boolean addGenerator(String name, String label) {
		if (generators.contains(name)) {
			return false;
		}
		generators.add(name);
		generateLabels.add(label);
		return true;
	}
	
	public boolean deleteGenerator(String name, String label) {
		if (generators.contains(name)) {
			generators.remove(name);
			generateLabels.remove(label);
			return true;
		}
		return false;
	}
	
	public void generateSampleList() {
		addGenerator("Generator 1", "Generate (G1)");
		addGenerator("Generator 2", "Generate (G2)");
		addGenerator("Generator 3", "Generate (G3)");
	}
	
	public String getLabel(String name) {
		for (int i = 0; i < generators.size(); i++) {
			if (name.contains(generators.get(i))) {
				index = i;
				break;
			}
		}
		if (index < generateLabels.size()) {
			return generateLabels.get(index);
		}
		return "";
	}
	
	public void executeSelectedGenerator() {
		executeSelectedGenerator(getActiveGenerator());
	}
	
	private String getActiveGenerator() {
		System.out.println("Call the " + generateLabels.get(index));
		return generators.get(index);
	}
	
	private void testGenExtension(final Object o) {
		Job job = new Job("Test Generator") { 
			protected IStatus run(IProgressMonitor monitor) {
				try {
					//TimeUnit.SECONDS.sleep(2);
					System.out.println("Generator: " + ((IGenerator) o).getName() + " exists.");
				//} catch (InterruptedException e) {
				//System.out.println("InterruptedException!");
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
	}
	
	private void generateList(final Object o) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				String name = ((IGenerator) o).getName();
				String label = ((IGenerator) o).getLabel();
				addGenerator(name, label);
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in generator client: can not generate List!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	
}
