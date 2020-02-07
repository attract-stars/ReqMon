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
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp;

import java.io.Reader;
import java.io.StringReader;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.IParser;
import org.eclipse.xtext.parser.ParseException;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.MappingStandaloneSetup;
//import de.fraunhofer.isst.stars.RequirementDSLStandaloneSetup;

public class TestAppMappingParser {
	
	@Inject
	private IParser parser;
	
	public TestAppMappingParser() {
		setupAppParser();
	}
	
	private void setupAppParser() {
		//Injector injector = new RequirementDSLStandaloneSetup().createInjectorAndDoEMFRegistration();
		Injector injector = new MappingStandaloneSetup().createInjectorAndDoEMFRegistration();
		injector.injectMembers(this);
	}
	
	public EObject parse(Reader reader) throws ParseException {
		IParseResult result = parser.parse(reader);
		if (result.hasSyntaxErrors()) {
			throw new ParseException("Provided inputs contains syntax errors.");
		}
		return result.getRootASTElement();
	}
	
	public void parserInput(String text) {
		Reader reader = new StringReader(text);
		String element = "";
		EObject result;
		try {
			result = parse(reader);
			element = result.toString();
			System.out.println("Parser result: " + element);
		} catch (ParseException e) {
			System.out.println("Syntaxerror");
		} catch (Exception e) {
			System.out.println("Parser Exception");
		}
	}

}
