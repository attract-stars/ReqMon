package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.logic;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.InMemoryFileSystemAccess;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.ExampleCHeaderTemplate;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.FilterCppTemplate;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.FilterHeaderTemplate;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.FilterType;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.AbstractModelInformationHelper;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.logic.ModelInformationHelperImpl;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.RequirementDataTypesTemplate;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.StandardAndTypesTemplate;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.SystemDataTypesTemplate;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IGenerator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel;

/**
 * This class generates a header file and a c++ file (.cpp) with the informations given in the resources.
 * To do that it implements the IGenerator of the mapping.ui.definitions and uses the InMemoryFileSystemAccess from xtext.
 * 
 * @author sgraf
 *
 */
public class FilterGenerator implements IGenerator {

	private List<String> contentList;
	private List<String> files;
	private String filePath;
	private IFileSystemAccess2 fsa;
	private FileDirectoryCreator creator;

	@Override
	public void generate(IMappingModel model) {
		System.out.println("FilterGenerator called!");
		AbstractModelInformationHelper infoHelper = new ModelInformationHelperImpl(model);
		setup(model.getProjectName());
		
		System.out.println("\n\nGenerated content:\n");
		
		// generate filter header and cpp code
		FilterHeaderTemplate filterHeaderTemp = new FilterHeaderTemplate(infoHelper);
		FilterCppTemplate filterCppTemp = new FilterCppTemplate(infoHelper);
		StandardAndTypesTemplate stdTypesTemp = new StandardAndTypesTemplate(infoHelper);
		ExampleCHeaderTemplate exampleTemp = new ExampleCHeaderTemplate();
		
		SystemDataTypesTemplate sysDataTemp = new SystemDataTypesTemplate(infoHelper);
		RequirementDataTypesTemplate reqDataTemp = new RequirementDataTypesTemplate(infoHelper);
		
		generateFile(stdTypesTemp.generateTypesTemplate(), "dtypes");
		generateFile(stdTypesTemp.generateStdTemplate(), "stdafx");
		
		generateAndAddToContentList(exampleTemp.generateExampleTemplate(model));
		generateFile(sysDataTemp.generateTemplate(), "system_types");
		generateFile(reqDataTemp.generateTemplate(), "requirement_types");
		
		generateFile(filterHeaderTemp.generateTemplate(FilterType.ABSTRACT_FUNCTION), FilterType.ABSTRACT_FUNCTION);
		generateFile(filterCppTemp.generateTemplate(FilterType.ABSTRACT_FUNCTION), FilterType.ABSTRACT_FUNCTION);
		generateFile(filterHeaderTemp.generateTemplate(FilterType.FUNCTIONAL_CORRECTNESS_ORACLE), FilterType.FUNCTIONAL_CORRECTNESS_ORACLE);
		generateFile(filterCppTemp.generateTemplate(FilterType.FUNCTIONAL_CORRECTNESS_ORACLE), FilterType.FUNCTIONAL_CORRECTNESS_ORACLE);
		generateFile(filterHeaderTemp.generateTemplate(FilterType.SCENE_ABSTRACTION), FilterType.SCENE_ABSTRACTION);
		generateFile(filterCppTemp.generateTemplate(FilterType.SCENE_ABSTRACTION), FilterType.SCENE_ABSTRACTION);
		
		// write in files
		write();
	}
	
	private void setup(String projectName) {
		// setup
		filePath = "../stars/de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator/filter-gen/";
		fsa = new InMemoryFileSystemAccess();
		
		contentList = new ArrayList<String>();
		files = new ArrayList<String>();
		
		//files.add("dtypes.h");
		//files.add("stdafx.h");
		
		files.add("mapped.h");
		/*files.add("system-types.h");
		files.add("requirement_types.h");
		
		files.add("abstract_function_filter.h");
		files.add("abstract_function_filter.cpp");
		files.add("functional_correctness_oracle_filter.h");
		files.add("functional_correctness_oracle_filter.cpp");
		files.add("scene_abstraction_filter.h");
		files.add("scene_abstraction_filter.cpp");*/
		
		//files.add("test_coverage_monitor_filter.h");
		//files.add("test_coverage_monitor_filter.cpp");
		
		creator = new FileDirectoryCreator(projectName);
		creator.createFileStructure();
	}
	
	/**
	 * Sets the file path to "filter/gen", creates the InMemoryFileSystemAccess 
	 * and a new content list and sets the file array to the given files.
	 * @param filesNames
	 */
	public void setup(List<String> filesNames) {
		// setup
		filePath = "filter-gen/";
		fsa = new InMemoryFileSystemAccess();
		contentList = new ArrayList<String>();
		files = filesNames;
	}
	
	
	/**
	 * Generates a file with InMemoryFileSystemAccess and adds the generated content to the content list. 
	 */
	private void generateAndAddToContentList(CharSequence generated) {
		fsa.generateFile("", generated);
		for (Entry<String, CharSequence> file : ((InMemoryFileSystemAccess) fsa).getTextFiles().entrySet()) {
			//System.out.println("Generated file path : "+file.getKey());
			//System.out.print("\n\nFile value:\n\n" + file.getValue());
			String text = file.getValue().toString();
			contentList.add(text);
		}
	}
	
	private void generateFile(CharSequence generated, FilterType type) {
		fsa.generateFile("", generated);
		for (Entry<String, CharSequence> file : ((InMemoryFileSystemAccess) fsa).getTextFiles().entrySet()) {
			String text = file.getValue().toString();
			creator.writeInFolder(type, text);
		}
	}
	
	private void generateFile(CharSequence generated, String name) {
		fsa.generateFile("", generated);
		for (Entry<String, CharSequence> file : ((InMemoryFileSystemAccess) fsa).getTextFiles().entrySet()) {
			String text = file.getValue().toString();
			creator.writeInFolder(name, text);
		}
	}
	
	/**
	 * Writes the header content and the cpp content to the mapped.h and the mapped.cpp file.
	 */
	private void write() {
		BufferedWriter writer = null;
		
		try {
			
			if (contentList.size() != files.size()) {
				System.out.println("Error: the size of the generated filter contents is not equal with the size of the file names!");
				return;
			}
			
			for (int i = 0; i < contentList.size(); i++) {
				writer = new BufferedWriter(new FileWriter(filePath.concat(files.get(i))));
				String text = contentList.get(i);
				writer.write(text);
				writer.close();
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void generate(AbstractModelInformationHelper infoHelper) {
		FilterHeaderTemplate filterHeaderTemp = new FilterHeaderTemplate(infoHelper);
		FilterCppTemplate filterCppTemp = new FilterCppTemplate(infoHelper);
		StandardAndTypesTemplate stdTypesTemp = new StandardAndTypesTemplate(infoHelper);
		
		generateAndAddToContentList(filterHeaderTemp.generateTemplate(FilterType.ABSTRACT_FUNCTION));
		generateAndAddToContentList(filterCppTemp.generateTemplate(FilterType.ABSTRACT_FUNCTION));
		generateAndAddToContentList(filterHeaderTemp.generateTemplate(FilterType.FUNCTIONAL_CORRECTNESS_ORACLE));
		generateAndAddToContentList(filterCppTemp.generateTemplate(FilterType.FUNCTIONAL_CORRECTNESS_ORACLE));
		generateAndAddToContentList(filterHeaderTemp.generateTemplate(FilterType.SCENE_ABSTRACTION));
		generateAndAddToContentList(filterCppTemp.generateTemplate(FilterType.SCENE_ABSTRACTION));
		
		generateAndAddToContentList(stdTypesTemp.generateTypesTemplate());
		
		// write in files
		write();
	}
	

}
