package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.InMemoryFileSystemAccess;

import com.google.inject.Injector;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.CGeneratedTextHeader;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.generator.MappingGenerator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.ui.internal.LanguageActivator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IGenerator;

/**
 * This class generates a header file and a c++ file (.cpp) with the informations given in the resources.
 * To do that it implements the IGenerator of the mapping.ui.definitions and uses the doGenerate method
 * from Xtext.  
 * @author sgraf
 *
 */
public class CGenerator implements IGenerator {

	private List<String> headerContent;
//	private List<String> cppContent;
	//private String file = "../stars/de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.test/mapped.h";
	private String file = "mapped.h";
	//private String filename2 = "mapped.cpp";
	private IFileSystemAccess2 fsa;

	@Override
	public void generate(List<Resource> resourceList) {
		System.out.println("CGenerator called!");
		
		// setups
		Injector injector = LanguageActivator.getInstance() 
				.getInjector(LanguageActivator.DE_FRAUNHOFER_ISST_AUTOMOTIVE_STARS_REQMON_DSL_MAPPING_LANGUAGE_MAPPING);
		MappingGenerator generator = injector.getInstance(MappingGenerator.class);
		
		fsa = new InMemoryFileSystemAccess();
		
		CGeneratedTextHeader genHeader = new CGeneratedTextHeader();
	//	CGeneratedText genText = new CGeneratedText();
		
		headerContent = new ArrayList<String>();
	//	cppContent = new ArrayList<String>();
		
		System.out.println("\n\nGenerated content:\n");
		
		// get the file name without the file path
		String[] filenameSplit = file.split(Pattern.quote("/"));
		String filename = filenameSplit[filenameSplit.length-1];
		
		// generate the include guard for the header file
		String includeGardsBegin = genHeader.constructIncludeGardsBegin(filename).toString();
		String includeGardsEnd = genHeader.constructIncludeGardsEnd().toString();
		
		// add first the begin of the include guard for the header file
		headerContent.add(includeGardsBegin);
		
		// Generate the contents for the header and the c++ file
		for (Resource resource : resourceList) {
			//System.out.println("Resource: " + resource);
			
		//	CharSequence seqCpp = genText.compiler(resource);
			
			// generate the header content: structures
			CharSequence seqHeader = genHeader.compileClasses(resource);
			generator.setCharSequence(seqHeader);
			generator.doGenerate(resource, fsa, null);
			getGeneratedContent();
				
			// generate the cpp content
		/*	generator.setCharSequence(seqCpp);
			generator.doGenerate(resource, fsa, null);
			
			getGeneratedContent();
			*/
		}
		
		for (Resource resource: resourceList ) {
			// generate the header content: data types
			CharSequence seqHeader = genHeader.compileSignalsAndAttributes(resource);
			generator.setCharSequence(seqHeader);
			generator.doGenerate(resource, fsa, null);
			getGeneratedContent();
		}
		
		// add at least the ending of the include guard for the header file
		headerContent.add(includeGardsEnd);
		
		// write in files
		write();
	}
	
	/**
	 * Adds the generated content to the headerContent list. 
	 */
	private void getGeneratedContent() {
		for (Entry<String, CharSequence> file : ((InMemoryFileSystemAccess) fsa).getTextFiles().entrySet()) {
			//System.out.println("Generated file path : "+file.getKey());
			System.out.print(file.getValue());
			String text = file.getValue().toString();
			headerContent.add(text);
		}
	}
	
	/**
	 * Writes the header content and the cpp content to the mapped.h and the mapped.cpp file.
	 */
	public void write() {
		BufferedWriter writer = null;
		
		try {
			/*if (filename == null) {
				System.out.println("No filename exists! Can not save model!");
				return;
			}*/
			
			writer = new BufferedWriter(new FileWriter(file));
			for (String text : headerContent) {
				//System.out.println(text);
				writer.write(text);
			}
			
			writer.close();
			
		/*	writer = new BufferedWriter(new FileWriter(filename2));
			for (String text : cppContent) {
				//System.out.println(text);
				writer.write(text);
			}
			
			writer.close();*/
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

}
