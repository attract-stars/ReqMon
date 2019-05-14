package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp;

import java.io.Reader;
import java.io.StringReader;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.IParser;
import org.eclipse.xtext.parser.ParseException;

import com.google.inject.Inject;
import com.google.inject.Injector;

import de.fraunhofer.isst.stars.RequirementDSLStandaloneSetup;

public class TestAppMappingParser {
	
	@Inject
	private IParser parser;
	
	public TestAppMappingParser() {
		setupAppParser();
	}
	
	private void setupAppParser() {
		Injector injector = new RequirementDSLStandaloneSetup().createInjectorAndDoEMFRegistration();
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
