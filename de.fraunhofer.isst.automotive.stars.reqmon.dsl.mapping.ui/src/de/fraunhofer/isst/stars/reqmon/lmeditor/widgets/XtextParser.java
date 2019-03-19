package de.fraunhofer.isst.stars.reqmon.lmeditor.widgets;

import java.io.Reader;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.IParser;
import org.eclipse.xtext.parser.ParseException;

import com.google.inject.Inject;
import com.google.inject.Injector;

import de.fraunhofer.isst.stars.RequirementDSLStandaloneSetup;

public class XtextParser {
	@Inject
	private IParser parser;
	
	public XtextParser() {
		setupParser();
	}
	
	private void setupParser() {
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
	

}
