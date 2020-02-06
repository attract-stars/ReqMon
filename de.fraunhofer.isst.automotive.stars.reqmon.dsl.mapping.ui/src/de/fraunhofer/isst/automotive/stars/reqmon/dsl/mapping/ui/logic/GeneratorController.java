package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic;

import java.util.ArrayList;
import java.util.List;
//import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IGenerator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel;

/**
 * This class manages the generator extensions. All registered generators want to be listed with their names in the Combo of the GUI.
 * The label of the selected generator in the Combo will be shown on the generate button. The name and the label are 
 * attributes of the generator extensions. 
 * 
 * @author sgraf
 *
 */
public class GeneratorController {
	
	private static final String IGENERATOR_ID =
			"de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.generator";
	private IExtensionRegistry registry;
	private IConfigurationElement[] configGen;
	private boolean isRegistry;
	
	private int index;
	private List<String> generators;
	private List<String> generateLabels;
	
	/**
	 * The constructor checks if a registry exists and if extensions are registered and 
	 * creates a list of all registered generators.
	 * If no generators exist a default text for the Combo and the generate button is added.
	 */
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
		}
		
		this.index = 0;
		this.generators = new ArrayList<String>();
		this.generateLabels = new ArrayList<String>();
		
		generateGeneratorList();
		if (generators.isEmpty()) {
			addGenerator("No generator!", "Can't generate code");
		}
		
	}
	
	/**
	 * Prints the generator attributes 'lang' (language), 'name' and 'label' for all registered generators.
	 */
	public void checkExtensions() {
		if (!isRegistry) {
			return;
		}
		try {
			for (IConfigurationElement e : configGen) {
				System.out.println("\nEvaluating generator extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IGenerator) {
					System.out.println("Attribute lang: " + e.getAttribute("lang") + ", name: " + e.getAttribute("name") +
					 ", label: " + e.getAttribute("label"));
				}
			}
		}
		catch (CoreException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Returns the index of the selected generator in the Combo. 
	 * The index is the position of the generator in the generator list.
	 * @return the index of the selected generator
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Returns the list of generator names.
	 * @return the list of generator names
	 */
	public List<String> getGenerators() {
		return generators;
	}

	/**
	 * Hands the given list to the generator names list.
	 * @param generators a list of generator names
	 */
	public void setGenerators(List<String> generators) {
		this.generators = generators;
	}
	
	/**
	 * Returns the list of generator labels.
	 * @return the list of generator labels
	 */
	public List<String> getGenerateLabels() {
		return generateLabels;
	}

	/**
	 * Hands the given list to the generator labels list.
	 * @param generateLabels a list of generator labels
	 */
	public void setGenerateLabels(List<String> generateLabels) {
		this.generateLabels = generateLabels;
	}
	
	/**
	 * Sets the generator index. The index points to the actual selected generator.
	 * @param generatorIndex the pointer to the actual generator.
	 */
	public void setIndex(int generatorIndex) {
		this.index = generatorIndex;
	}

	/**
	 * Deletes the generator which name and label match the given name and label. 
	 * @param name the name of the generator
	 * @param label the label of the generator
	 * @return if the deleting was successfully
	 */
	public boolean deleteGenerator(String name) {
		if (generators.contains(name)) {
			int i = generators.indexOf(name);
			generators.remove(name);
			generateLabels.remove(generateLabels.get(i));
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the label of the generator which matches the given name and 
	 * updates the index to remember the actual selected generator accordingly.
	 * @param name the name of the generator
	 * @return the label of the selected generator.
	 */
	public String getLabel(String name) {
		if (generators.contains(name)) {
			index = generators.indexOf(name);
		}
		return generateLabels.get(index);
	}
	
	
	/**
	 * Executes the generator which name is selected in the Combo.
	 * @param resource the resource of the mapping input
	 */
	public void executeSelectedGenerator(IMappingModel model, String projectName) {
		if (!isRegistry) {
			return;
		}
		try {
			String name = getActiveGenerator();
			for (IConfigurationElement e : configGen) {
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IGenerator && name.contains(e.getAttribute("name"))) {
					executeGenerator(o, model, projectName);
					break;
				}
			}
		} catch (CoreException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Creates the generator list. The names and labels of the registered generators are added to the 
	 * generator name list and to the generator label list.
	 */
	private void generateGeneratorList() {
		if (!isRegistry) {
			return;
		}
		try {
			for (IConfigurationElement e : configGen) {
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IGenerator) {
					String name = e.getAttribute("name");
					String label = e.getAttribute("label");
					addGenerator(name, label);
				}
			}
		} catch (CoreException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Adds the given name to the generator name list and the given label to the generator label list
	 * if given name does not already exist the given generator name list. 
	 * @param name of the generator
	 * @param label of the generator
	 * @return if the addition was successfully
	 */
	private boolean addGenerator(String name, String label) {
		if (generators.contains(name)) {
			return false;
		}
		generators.add(name);
		generateLabels.add(label);
		return true;
	}
	
	/**
	 * Returns the name of the generator which is selected in the Combo.
	 * @return the name of the selected generator
	 */
	private String getActiveGenerator() {
		System.out.println("Call the " + generateLabels.get(index));
		return generators.get(index);
	}
	
	/**
	 * Executes the given generator.
	 * @param o an Object of the type of an IGenerator
	 * @param resource the resource of the mapping input 
	 */
	private void executeGenerator(Object o, IMappingModel model, String projectName) {
		Job job = new Job("Execute Generator") { 
			protected IStatus run(IProgressMonitor monitor) {
				try {
					((IGenerator) o).generate(model, projectName);
				} 
				catch (Exception ex) {
					System.out.println("Exception in generator client:");
					ex.printStackTrace();
				}
				return Status.OK_STATUS;
			}
		};
		
		job.setPriority(Job.SHORT);
		job.schedule();
	}

	
	
	
}
