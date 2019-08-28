package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.InMemoryFileSystemAccess;

import com.google.inject.Injector;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.generator.MappingGenerator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.ui.internal.LanguageActivator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IGenerator;


public class CGenerator implements IGenerator {

	private List<String> content;

	@Override
	public void generate(List<Resource> resourceList) {
		System.out.println("CGenerator called!");
		
		Injector injector = LanguageActivator.getInstance() 
				.getInjector(LanguageActivator.DE_FRAUNHOFER_ISST_AUTOMOTIVE_STARS_REQMON_DSL_MAPPING_LANGUAGE_MAPPING);
		MappingGenerator generator = injector.getInstance(MappingGenerator.class);
		
		IFileSystemAccess2 fsa = new InMemoryFileSystemAccess();
		
		CGeneratedTextHeader genHeader = new CGeneratedTextHeader();
		
		content = new ArrayList<String>();
		
		for (Resource resource : resourceList) {
			//System.out.println("Resource: " + resource);
			
			CharSequence seq = genHeader.compiler(resource);
			generator.setCharSequence(seq);
			
			generator.doGenerate(resource, fsa, null);
			for (Entry<String, CharSequence> file : ((InMemoryFileSystemAccess) fsa).getTextFiles().entrySet()) {
				//System.out.println("Generated file path : "+file.getKey());
				System.out.println("Generated file contents: " + file.getValue());
				String text = file.getValue().toString();
				content.add(text);
			}
		}
		
		write();
		
		System.out.println("Code generated");
	}
	
	public void write() {
		String filename = "mapped.h";
		
		BufferedWriter writer = null;
		
		try {
			/*if (filename == null) {
				System.out.println("No filename exists! Can not save model!");
				return;
			}*/
			
			writer = new BufferedWriter(new FileWriter(filename));
			for (String text : content) {
				//System.out.println(text);
				writer.write(text);
			}
			
			writer.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

}
