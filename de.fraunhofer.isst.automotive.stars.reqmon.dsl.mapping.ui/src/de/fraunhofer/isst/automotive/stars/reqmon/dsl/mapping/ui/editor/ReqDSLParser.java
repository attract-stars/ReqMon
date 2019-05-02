package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor;

import java.io.Reader;
import java.io.StringReader;

import javax.inject.Inject;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.mwe.internal.core.ast.util.Injector;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.IParser;
import org.eclipse.xtext.parser.ParseException;
import de.fraunhofer.isst.stars.RequirementDSLRuntimeModule;
import de.fraunhofer.isst.stars.RequirementDSLStandaloneSetup;

public class ReqDSLParser {
	
	@Inject
	private IParser parser;
	
	public ReqDSLParser(boolean isApp) {
		if (isApp) {
			setupAppParser();
		}
		else {
			setupParser();
		}
	}
	
	private void setupParser() {
		RequirementDSLRuntimeModule module = new RequirementDSLRuntimeModule();
		//Injector injector = Guice.createInjector(module);
		//injector.injectMembers(this);
		System.out.println("\nModule: " + module.getClass().getName());
	}
	
	private void setupAppParser() {
//		Injector injector = new RequirementDSLStandaloneSetup().createInjectorAndDoEMFRegistration();
//		injector.injectMembers(this);
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