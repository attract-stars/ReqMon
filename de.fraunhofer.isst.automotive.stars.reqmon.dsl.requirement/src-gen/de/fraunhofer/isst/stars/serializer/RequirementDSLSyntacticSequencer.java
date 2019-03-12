/*
 * generated by Xtext 2.16.0
 */
package de.fraunhofer.isst.stars.serializer;

import com.google.inject.Inject;
import de.fraunhofer.isst.stars.services.RequirementDSLGrammarAccess;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AlternativeAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public class RequirementDSLSyntacticSequencer extends AbstractSyntacticSequencer {

	protected RequirementDSLGrammarAccess grammarAccess;
	protected AbstractElementAlias match_ExistencePreface_ExistKeyword_3_0_or_ExistsKeyword_3_1;
	protected AbstractElementAlias match_ExistencePreface_ThereKeyword_1_0_or_ThereKeyword_1_1;
	protected AbstractElementAlias match_RequirementText_CommaKeyword_0_1_q;
	protected AbstractElementAlias match_RequirementText_CommaKeyword_2_0_q;
	protected AbstractElementAlias match_Requirement_FullStopKeyword_4_0_or_SemicolonKeyword_4_1;
	protected AbstractElementAlias match_Requirement_LineFeedKeyword_5_p;
	protected AbstractElementAlias match_Requirement_ReqKeyword_0_q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (RequirementDSLGrammarAccess) access;
		match_ExistencePreface_ExistKeyword_3_0_or_ExistsKeyword_3_1 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getExistencePrefaceAccess().getExistKeyword_3_0()), new TokenAlias(false, false, grammarAccess.getExistencePrefaceAccess().getExistsKeyword_3_1()));
		match_ExistencePreface_ThereKeyword_1_0_or_ThereKeyword_1_1 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getExistencePrefaceAccess().getThereKeyword_1_0()), new TokenAlias(false, false, grammarAccess.getExistencePrefaceAccess().getThereKeyword_1_1()));
		match_RequirementText_CommaKeyword_0_1_q = new TokenAlias(false, true, grammarAccess.getRequirementTextAccess().getCommaKeyword_0_1());
		match_RequirementText_CommaKeyword_2_0_q = new TokenAlias(false, true, grammarAccess.getRequirementTextAccess().getCommaKeyword_2_0());
		match_Requirement_FullStopKeyword_4_0_or_SemicolonKeyword_4_1 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getRequirementAccess().getFullStopKeyword_4_0()), new TokenAlias(false, false, grammarAccess.getRequirementAccess().getSemicolonKeyword_4_1()));
		match_Requirement_LineFeedKeyword_5_p = new TokenAlias(true, false, grammarAccess.getRequirementAccess().getLineFeedKeyword_5());
		match_Requirement_ReqKeyword_0_q = new TokenAlias(false, true, grammarAccess.getRequirementAccess().getReqKeyword_0());
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (ruleCall.getRule() == grammarAccess.getNegationRule())
			return getNegationToken(semanticObject, ruleCall, node);
		else if (ruleCall.getRule() == grammarAccess.getPROPERTY_TERMRule())
			return getPROPERTY_TERMToken(semanticObject, ruleCall, node);
		return "";
	}
	
	/**
	 * Negation:
	 * 	'not' ;
	 */
	protected String getNegationToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "not";
	}
	
	/**
	 * terminal PROPERTY_TERM returns ecore::EBoolean:
	 * 		'´s'
	 * ;
	 */
	protected String getPROPERTY_TERMToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "\u00B4s";
	}
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if (match_ExistencePreface_ExistKeyword_3_0_or_ExistsKeyword_3_1.equals(syntax))
				emit_ExistencePreface_ExistKeyword_3_0_or_ExistsKeyword_3_1(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ExistencePreface_ThereKeyword_1_0_or_ThereKeyword_1_1.equals(syntax))
				emit_ExistencePreface_ThereKeyword_1_0_or_ThereKeyword_1_1(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_RequirementText_CommaKeyword_0_1_q.equals(syntax))
				emit_RequirementText_CommaKeyword_0_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_RequirementText_CommaKeyword_2_0_q.equals(syntax))
				emit_RequirementText_CommaKeyword_2_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_Requirement_FullStopKeyword_4_0_or_SemicolonKeyword_4_1.equals(syntax))
				emit_Requirement_FullStopKeyword_4_0_or_SemicolonKeyword_4_1(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_Requirement_LineFeedKeyword_5_p.equals(syntax))
				emit_Requirement_LineFeedKeyword_5_p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_Requirement_ReqKeyword_0_q.equals(syntax))
				emit_Requirement_ReqKeyword_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Ambiguous syntax:
	 *     'exist' | 'exists'
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) ('there' | 'There') (ambiguity) (rule start)
	 *     (rule start) ('there' | 'There') (ambiguity) actors=Actors
	 *     modifier=Modifier (ambiguity) (rule end)
	 *     modifier=Modifier (ambiguity) actors=Actors
	 */
	protected void emit_ExistencePreface_ExistKeyword_3_0_or_ExistsKeyword_3_1(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     'there' | 'There'
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) ('exist' | 'exists') (rule start)
	 *     (rule start) (ambiguity) ('exist' | 'exists') actors=Actors
	 *     (rule start) (ambiguity) modifier=Modifier
	 */
	protected void emit_ExistencePreface_ThereKeyword_1_0_or_ThereKeyword_1_1(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ','?
	 *
	 * This ambiguous syntax occurs at:
	 *     condClauses+=ConditionalClause (ambiguity) 'then' mainclauses=MainClause
	 */
	protected void emit_RequirementText_CommaKeyword_0_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ','?
	 *
	 * This ambiguous syntax occurs at:
	 *     mainclauses=MainClause (ambiguity) condClauses+=ConditionalClause
	 */
	protected void emit_RequirementText_CommaKeyword_2_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     '.' | ';'
	 *
	 * This ambiguous syntax occurs at:
	 *     (
	 *         text=RequirementText 
	 *         (ambiguity) 
	 *         '
	 *         '+ 
	 *         (rule end)
	 *     )
	 */
	protected void emit_Requirement_FullStopKeyword_4_0_or_SemicolonKeyword_4_1(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     '
	  *     '+
	 *
	 * This ambiguous syntax occurs at:
	 *     text=RequirementText ('.' | ';') (ambiguity) (rule end)
	 */
	protected void emit_Requirement_LineFeedKeyword_5_p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     'Req'?
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) ':' text=RequirementText
	 *     (rule start) (ambiguity) reqID=ReqID
	 */
	protected void emit_Requirement_ReqKeyword_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
