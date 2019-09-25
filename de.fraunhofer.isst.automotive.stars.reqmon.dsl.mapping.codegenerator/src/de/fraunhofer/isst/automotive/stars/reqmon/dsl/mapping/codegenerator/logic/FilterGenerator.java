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
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.StandardAndTypesTemplate;
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
	private String filePath = "../stars/de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator/filter-gen/";
	private IFileSystemAccess2 fsa;
	

	@Override
	public void generate(IMappingModel model) {
		System.out.println("FilterGenerator called!");
		
		// setup
		fsa = new InMemoryFileSystemAccess();
		contentList = new ArrayList<String>();
		files = new ArrayList<String>();
		files.add(filePath.concat("mapped-filter.h"));
		files.add(filePath.concat("mapped-filter.cpp"));
		
		files.add(filePath.concat("dtypes.h"));
		files.add(filePath.concat("stdafx.h"));
		
		files.add(filePath.concat("mapped.h"));
		
		files.add(filePath.concat("type-one.h"));
		files.add(filePath.concat("type-one.cpp"));
		files.add(filePath.concat("type-two.h"));
		files.add(filePath.concat("type-two.cpp"));
		files.add(filePath.concat("type-three.h"));
		files.add(filePath.concat("type-three.cpp"));
		
		System.out.println("\n\nGenerated content:\n");
		
		// generate filter header and cpp code
		FilterHeaderTemplate filterHeaderTemp = new FilterHeaderTemplate();
		FilterCppTemplate filterCppTemp = new FilterCppTemplate();
		StandardAndTypesTemplate stdTypesTemp = new StandardAndTypesTemplate();
		ExampleCHeaderTemplate exampleTemp = new ExampleCHeaderTemplate();
		
		generateAndAddToContentList(filterHeaderTemp.generateTemplate(""));
		generateAndAddToContentList(filterCppTemp.generateTemplate(""));
		
		generateAndAddToContentList(stdTypesTemp.generateTypesTemplate());
		generateAndAddToContentList(stdTypesTemp.generateStdTemplate());
		
		generateAndAddToContentList(exampleTemp.generateExampleTemplate(model));
		
		generateAndAddToContentList(filterHeaderTemp.generateTemplate("one"));
		generateAndAddToContentList(filterCppTemp.generateTemplate("one"));
		generateAndAddToContentList(filterHeaderTemp.generateTemplate("two"));
		generateAndAddToContentList(filterCppTemp.generateTemplate("two"));
		generateAndAddToContentList(filterHeaderTemp.generateTemplate("three"));
		generateAndAddToContentList(filterCppTemp.generateTemplate("three"));
		
		
		// write in files
		write();
	}
	
	
	
	/**
	 * Generates a file with InMemoryFileSystemAccess and adds the generated content to the content list. 
	 */
	private void generateAndAddToContentList(CharSequence generated) {
		fsa.generateFile("", generated);
		for (Entry<String, CharSequence> file : ((InMemoryFileSystemAccess) fsa).getTextFiles().entrySet()) {
			//System.out.println("Generated file path : "+file.getKey());
			System.out.print("\n\nFile value:\n\n" + file.getValue());
			String text = file.getValue().toString();
			contentList.add(text);
		}
	}
	
	/**
	 * Writes the header content and the cpp content to the mapped.h and the mapped.cpp file.
	 */
	public void write() {
		BufferedWriter writer = null;
		
		try {
			
			if (contentList.size() != files.size()) {
				System.out.println("Error: the size of the generated filter contents is not equal with the size of the file names!");
				return;
			}
			
			for (int i = 0; i < contentList.size(); i++) {
				writer = new BufferedWriter(new FileWriter(files.get(i)));
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

	

}
