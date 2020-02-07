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
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.IParser;
import org.eclipse.xtext.parser.ParseException;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.CancelIndicator;

import com.google.inject.Inject;
import com.google.inject.Injector;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.ui.internal.LanguageActivator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingParser;
//import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.ui.internal.RequirementActivator;
//import de.fraunhofer.isst.stars.RequirementDSLRuntimeModule;

public class TestMappingParser implements IMappingParser {

	@Inject
	private IParser parser;
	
	@Inject
	private XtextResourceSet resourceSet;
	
	public TestMappingParser() {
		setupParser();
		//setupParserWithAllReferences();
	}

	
	private void setupParser() {
		Injector injector = LanguageActivator.getInstance() 
				.getInjector(LanguageActivator.DE_FRAUNHOFER_ISST_AUTOMOTIVE_STARS_REQMON_DSL_MAPPING_LANGUAGE_MAPPING);       
		injector.injectMembers(this);
	}
	
	
	/*private void setupParserWithAllReferences() {
		Injector injector = LanguageActivator.getInstance() 
				.getInjector(LanguageActivator.DE_FRAUNHOFER_ISST_AUTOMOTIVE_STARS_REQMON_DSL_MAPPING_LANGUAGE_MAPPING);       
		injector.injectMembers(this);
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
	}*/

	
	public EObject parse(Reader reader) throws ParseException {
		IParseResult result = parser.parse(reader);
		URI uri = URI.createURI("http://www.fraunhofer.de/isst/automotive/stars/reqmon/dsl/mapping/language/Mapping");
		Resource res = resourceSet.getResource(uri, false); // XtextResource ?
		EcoreUtil2.resolveLazyCrossReferences(res, null);
		if (result.hasSyntaxErrors()) {
			throw new ParseException("Provided inputs contains syntax errors.");
		}
		return result.getRootASTElement();
	}
	
	public EObject parse(InputStream in) throws IOException {
		boolean isResource = false;
		Resource resource = null;
		URI uri = URI.createURI("dummy:/inmemory.mapping");
		System.out.println("Size of the resources: "+resourceSet.getResources().size());
		/*for (Resource r : resourceSet.getResources()) {
			System.out.println("uri:" + r.getURI());
			if (r.getURI().equals(uri)) {
				resource = r;
				isResource = true;
			}
		}*/
		if (resourceSet.getURIResourceMap().containsKey(uri)) {
			resource = resourceSet.getResource(uri, false);
			isResource = true;
		}
		if (isResource) {
			System.out.println("resource still exists");
			resource.load(in, resourceSet.getLoadOptions());
		}
		else {
			resource = resourceSet.createResource(uri);
		}
		/*URI uri = URI.createURI("dummy:/inmemory.mapping");
		Resource resource = resourceSet.createResource(uri); //getResource(uri, true);
		resource.load(in, resourceSet.getLoadOptions());*/
		
		resource.load(in, resourceSet.getLoadOptions());
		
		if (resource.getContents() == null) {
			System.out.println("No resource contents!");
			return null;
		}
		return resource.getContents().get(0);
	}
	
	
	public void parserInput(String text) {
		if(parser == null) {
			System.out.println("No parser!");
			return;
		}
		Reader reader = new StringReader(text);
		//String element = "";
		EObject result;
		try {
			result = parse(reader);
			//write(text);
			//result = parse(new FileInputStream("writeTest.mapping") );
			if (result == null) {
				System.out.println("No result!");
				return;
			}
			//element = result.toString();
			System.out.println("correct!");
		} catch (ParseException e) {
			System.out.println("Syntaxerror");
		} catch (Exception e) {
			System.out.println("Parser Exception");
			e.printStackTrace();
		}
	}
	
	/*private void write(String text) {
		Writer fw = null;

		try
		{
		  fw = new FileWriter( "writeTest.mapping" );
		  fw.write( text );
		  //fw.append( System.getProperty("line.separator") ); // e.g. "\n"
		}
		catch ( IOException e ) {
		  System.err.println( "Konnte Datei nicht erstellen" );
		}
		finally {
		  if ( fw != null )
		    try { fw.close(); } catch ( IOException e ) { e.printStackTrace(); }
		}
	}
	
	private InputStream getInput() {
		File file = new File("writeTest.mapping");
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/


	
	public void parserInput(String text, IProgressMonitor monitor) {
		if(parser == null) {
			System.out.println("No parser!");
			return;
		}
		Reader reader = new StringReader(text);
		String element = "";
		EObject result;
		try {
			result = parse(reader, monitor);
			//write(text);
			//result = parse(new FileInputStream("writeTest.mapping") );
			if (result == null) {
				System.out.println("No result!");
				return;
			}
			element = result.toString();
			System.out.println("correct! " + element);
		} catch (ParseException e) {
			System.out.println("Syntaxerror");
		} catch (Exception e) {
			System.out.println("Parser Exception");
			e.printStackTrace();
		}
	}


	private EObject parse(Reader reader, IProgressMonitor monitor) {
		URI uri = URI.createURI("http://www.fraunhofer.de/isst/automotive/stars/reqmon/dsl/mapping/language/Mapping");
		Resource res = resourceSet.getResource(uri, false); // XtextResource ?
		CancelIndicator ci = new CancelIndicator() {
			
			@Override
			public boolean isCanceled() {
				return monitor.isCanceled();
			}
		};
		EcoreUtil2.resolveLazyCrossReferences(res, ci);
		IParseResult result = parser.parse(reader);
		if (result.hasSyntaxErrors()) {
			throw new ParseException("Provided inputs contains syntax errors.");
		}
		return result.getRootASTElement();
	}


	@Override
	public Injector getDslInjector(URI systemModelUri) {
		// TODO Auto-generated method stub
		return null;
	}

}
