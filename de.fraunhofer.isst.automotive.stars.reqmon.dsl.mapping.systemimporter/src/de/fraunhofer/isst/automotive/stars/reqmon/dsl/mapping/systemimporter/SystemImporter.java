package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.systemimporter;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.IParser;

import com.google.inject.Injector;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.systemdefinition.ui.internal.SystemdefinitionActivator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.ISystemImporter;

/**
 * This class implements ISystemImporter and checks, if the file content of the given path has syntax errors regarding
 * the "Systemdefinition" Language: DE_FRAUNHOFER_ISST_AUTOMOTIVE_STARS_REQMON_DSL_MAPPING_SYSDEF.
 * 
 * @author sgraf
 *
 */
public class SystemImporter implements ISystemImporter {
	
	
	@Override
	public boolean check(String path) {
		Injector injector = SystemdefinitionActivator.getInstance()
				.getInjector(SystemdefinitionActivator.DE_FRAUNHOFER_ISST_AUTOMOTIVE_STARS_REQMON_DSL_MAPPING_SYSDEF);
		
		IParser parser = injector.getInstance(IParser.class);
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
		if (reader == null) {
			System.out.println("Reader is null!");
			return false;
		}
		
		IParseResult result = parser.parse(reader);
		
		if (result == null) {
			System.out.println("Result is empty!");
			return false;
		}
		
		else if (result.hasSyntaxErrors()) {
			System.out.println("Result has syntax errors!");
			return false;
		}
		
		System.out.println("Result is correct");
		return true;
	}
	
	
	


}
