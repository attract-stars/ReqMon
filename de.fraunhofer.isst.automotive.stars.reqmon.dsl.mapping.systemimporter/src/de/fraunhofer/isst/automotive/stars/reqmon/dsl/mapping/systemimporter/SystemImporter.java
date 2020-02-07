/*******************************************************************************
 * Copyright (C) 2020 Fraunhofer ISST
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.systemimporter;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.eclipse.emf.ecore.EObject;
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
	
	private EObject model;
	
	
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
		
		System.out.println("Result is correct: " + result.getRootASTElement());
		model = result.getRootASTElement();
		return true;
	}


	public EObject getSystemModel() {
		return model;
	}




}
