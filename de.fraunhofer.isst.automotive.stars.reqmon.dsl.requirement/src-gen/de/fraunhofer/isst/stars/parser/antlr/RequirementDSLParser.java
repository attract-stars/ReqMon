/*
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars.parser.antlr;

import com.google.inject.Inject;
import de.fraunhofer.isst.stars.parser.antlr.internal.InternalRequirementDSLParser;
import de.fraunhofer.isst.stars.services.RequirementDSLGrammarAccess;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class RequirementDSLParser extends AbstractAntlrParser {

	@Inject
	private RequirementDSLGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS_HYPHEN");
	}
	

	@Override
	protected InternalRequirementDSLParser createParser(XtextTokenStream stream) {
		return new InternalRequirementDSLParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "Model";
	}

	public RequirementDSLGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(RequirementDSLGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
