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
package de.fraunhofer.isst.stars.ui.contentassist

import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.util.Strings
import org.eclipse.jface.text.contentassist.ICompletionProposal
import org.eclipse.xtext.GrammarUtil
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal

/**
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#content-assist
 * on how to customize the content assistant.
 */
class RequirementDSLProposalProvider extends AbstractRequirementDSLProposalProvider {
	
	override void completeRequirementModel_Requirements(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeRequirementModel_Requirements(model, assignment, context, acceptor)
		
		val String featureType = "Requirement"
		val String type_1 = "ReqID"
		val String type_2 = "RequirementText"
		val String allTypes_1 = type_1 + " : " + featureType
		val String allTypes_2 = type_2 + " : " + featureType
		//val String allTypes_3 = type_1 + " " + type_2 + " : " + featureType
		
		helpComplete("Req:", "", context, acceptor, 0, 0, false)
		helpComplete("Req.", "", context, acceptor, 0, 0, false)
		helpComplete("Req ReqID", allTypes_1, context, acceptor, 4, 0, true)
		//helpComplete("ReqID", allTypes_1, context, acceptor, 0, 0, true)
		helpComplete("Req ReqID:", allTypes_1, context, acceptor, 4, 1, true)
		helpComplete("Req ReqID.", allTypes_1, context, acceptor, 4, 1, true)
		helpComplete("ReqID:", allTypes_1, context, acceptor, 0, 1, true)
		helpComplete("ReqID.", allTypes_1, context, acceptor, 0, 1, true)
		
		helpComplete(": RequirementText", allTypes_2, context, acceptor, 2, 0, true)
		helpComplete(". RequirementText", allTypes_2, context, acceptor, 2, 0, true)
		helpComplete("ReqID:", "ReqID: RequirementText : " + featureType, context, acceptor, 0, 1, true)
		helpComplete("ReqID.", "ReqID. RequirementText : " + featureType, context, acceptor, 0, 1, true)
		helpComplete("Req: RequirementText", allTypes_2, context, acceptor, 5, 0, true)
		helpComplete("Req. RequirementText", allTypes_2, context, acceptor, 5, 0, true)
		helpComplete("Req ReqID:", "Req ReqID: RequirementText : " + featureType, context, acceptor, 4, 1, true)
		helpComplete("Req ReqID.", "Req ReqID. RequirementText : " + featureType, context, acceptor, 4, 1, true)
		
		helpComplete(": RequirementText.", allTypes_2, context, acceptor, 2, 1, true)
		helpComplete(". RequirementText.", allTypes_2, context, acceptor, 2, 1, true)
		helpComplete("ReqID:", "ReqID: RequirementText. : " + featureType, context, acceptor, 0, 1, true)
		helpComplete("ReqID.", "ReqID. RequirementText. : " + featureType, context, acceptor, 0, 1, true)
		helpComplete("Req: RequirementText.", allTypes_2, context, acceptor, 5, 1, true)
		helpComplete("Req. RequirementText.", allTypes_2, context, acceptor, 5, 1, true)
		helpComplete("Req ReqID:", "Req ReqID: RequirementText. : " + featureType, context, acceptor, 4, 1, true)
		helpComplete("Req ReqID.", "Req ReqID. RequirementText. : " + featureType, context, acceptor, 4, 1, true)
		
		helpComplete(": RequirementText;", allTypes_2, context, acceptor, 2, 1, true)
		helpComplete(". RequirementText;", allTypes_2, context, acceptor, 2, 1, true)
		helpComplete("ReqID:", "ReqID: RequirementText; : " + featureType, context, acceptor, 0, 1, true)
		helpComplete("ReqID.", "ReqID. RequirementText; : " + featureType, context, acceptor, 0, 1, true)
		helpComplete("Req: RequirementText;", allTypes_2, context, acceptor, 5, 1, true)
		helpComplete("Req. RequirementText;", allTypes_2, context, acceptor, 5, 1, true)
		helpComplete("Req ReqID:", "Req ReqID: RequirementText; : " + featureType, context, acceptor, 4, 1, true)
		helpComplete("Req ReqID.", "Req ReqID. RequirementText; : " + featureType, context, acceptor, 4, 1, true)
		
	}
	
	override void completeRequirement_Text(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeRequirement_Text(model, assignment, context, acceptor)
		
		val String featureType = "RequirementText"
		val String type_1 = "ConditionalClause"
		//val String type_2 = "MainClause"
		val String allTypes_1 = type_1 + " : " + featureType
		//val String allTypes_2 = type_1 + " " + type_2 + " : " + featureType
		//val String allTypes_3 = type_1 + " " + type_2 + " " + type_1 + " : " + featureType
		//val String allTypes_4 = type_2 + " " + type_1 + " : " + featureType
		
		//helpComplete("ConditionalClause", "ConditionalClause", context, acceptor, 0, 0, true)
		helpComplete("ConditionalClause, then", allTypes_1, context, acceptor, 0, 6, true)
		helpComplete("ConditionalClause then", allTypes_1, context, acceptor, 0, 5, true)
		
		//helpComplete("MainClause", "MainClause", context, acceptor, 0, 0, true)
		helpComplete("ConditionalClause", "ConditionalClause MainClause : " + featureType, context, acceptor, 0, 0, true)
		helpComplete("ConditionalClause, then", "ConditionalClause, then MainClause : " + featureType, context, acceptor, 0, 6, true)
		helpComplete("ConditionalClause then", "ConditionalClause then MainClause : " + featureType, context, acceptor, 0, 5, true)
		
		helpComplete("MainClause,", "MainClause, ConditionalClause : " + featureType, context, acceptor, 0, 1, true)
		helpComplete("ConditionalClause", "ConditionalClause MainClause ConditionalClause : " + featureType, context, acceptor, 0, 0, true)
		helpComplete("ConditionalClause, then", "ConditionalClause, then MainClause ConditionalClause : " + featureType, context, acceptor, 0, 6, true)
		helpComplete("ConditionalClause then", "ConditionalClause then MainClause ConditionalClause : " + featureType, context, acceptor, 0, 5, true)
		
		helpComplete("MainClause", "MainClause, ConditionalClause : " + featureType, context, acceptor, 0, 0, true)
		helpComplete("ConditionalClause", "ConditionalClause MainClause, ConditionalClause : " + featureType, context, acceptor, 0, 0, true)
		helpComplete("ConditionalClause, then", "ConditionalClause, then MainClause, ConditionalClause : " + featureType, context, acceptor, 0, 6, true)
		helpComplete("ConditionalClause then", "ConditionalClause then MainClause, ConditionalClause : " + featureType, context, acceptor, 0, 5, true)
		
	}
	
	override void completeRequirementText_CondClauses(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeRequirementText_CondClauses(model, assignment, context, acceptor)
		
		val String featureType = "ConditionalClause"
		val String type_1 = "ClauseOrdinator"
		val String type_2 = "Clauses"
		val String allTypes_1 = type_1 + " : " + featureType
		val String allTypes_2 = type_1 + " " + type_2 + " : " + featureType
		
		helpComplete("ClauseOrdinator", allTypes_1, context, acceptor, 0, 0, true)
		helpComplete("ClauseOrdinator", "ClauseOrdinator Clauses : " + featureType, context, acceptor, 0, 0, true)
		
		helpComplete("if Clauses", allTypes_2, context, acceptor, 3, 0, true)
		helpComplete("after Clauses", allTypes_2, context, acceptor, 6, 0, true)
		helpComplete("once Clauses", allTypes_2, context, acceptor, 5, 0, true)
		helpComplete("when Clauses", allTypes_2, context, acceptor, 5, 0, true)
		helpComplete("whenever Clauses", allTypes_2, context, acceptor, 9, 0, true)
		helpComplete("before Clauses", allTypes_2, context, acceptor, 7, 0, true)
		helpComplete("until Clauses", allTypes_2, context, acceptor, 6, 0, true)
		
		helpComplete("If Clauses", allTypes_2, context, acceptor, 3, 0, true)
		helpComplete("After Clauses", allTypes_2, context, acceptor, 6, 0, true)
		helpComplete("Once Clauses", allTypes_2, context, acceptor, 5, 0, true)
		helpComplete("When Clauses", allTypes_2, context, acceptor, 5, 0, true)
		helpComplete("Whenever Clauses", allTypes_2, context, acceptor, 9, 0, true)
		helpComplete("Before Clauses", allTypes_2, context, acceptor, 7, 0, true)
		helpComplete("Until Clauses", allTypes_2, context, acceptor, 6, 0, true)
		
	}
	
	override void completeConditionalClause_Clauses(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeConditionalClause_Clauses(model, assignment, context, acceptor)
		
		val String featureType = "Clauses"
		val String type_1 = "Clause"
		val String type_2 = "Conjunction"
		val String allTypes_1 = type_1 + " " + type_2 + " : " + featureType
		//val String allTypes_2 = type_1 + " " + type_2 + " " + type_1 + " : " + featureType
		
		helpComplete("Clause and", allTypes_1, context, acceptor, 0, 4, true)
		helpComplete("Clause or", allTypes_1, context, acceptor, 0, 3, true)
		helpComplete("Clause and", "Clause and Clause : " + featureType, context, acceptor, 0, 4, true)
		helpComplete("Clause or", "Clause or Clause : " + featureType, context, acceptor, 0, 3, true)
		
	}
	
	override void completeConditionalClause_Ordinator(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeConditionalClause_Ordinator(model, assignment, context, acceptor)
		helpComplete("ClauseOrdinator", "ClauseOrdinator", context, acceptor, 0, 0, true)
	}
	override void completeRequirement_ReqID(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeRequirement_ReqID(model, assignment, context, acceptor)
		helpComplete("ReqID", "Examples: 'a', 'A', 'a_', 'a_a', 'a_1', 'ab-c', 'ab-1', '1', '1234', 'a1'", context, acceptor, 0, 0, true)
		// ReqID: (ID|INT) ('.'|INT)*;
		// terminal ID: '^'?('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'-'|'0'..'9')*;
	}
	
	override void completeRequirementText_Mainclauses(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeRequirementText_Mainclauses(model, assignment, context, acceptor)
		
		val String featureType = "MainClause"
		val String type_1 = "Modifier"
		val String type_2 = "Clauses"
		val String allTypes_1 = type_1 + " : " + featureType
		val String allTypes_2 = type_2 + " : " + featureType
		val String allTypes_3 = type_1 + " " + type_2 + " : " + featureType
		
		helpComplete("Modifier", allTypes_1, context, acceptor, 0, 0, true)
		helpComplete("Clauses", allTypes_2, context, acceptor, 0, 0, true)
		helpComplete("Modifier", "Modifier Clauses : " + featureType, context, acceptor, 0, 0, true)
		
		helpComplete("Globally Clauses", allTypes_3, context, acceptor, 9, 0, true)
		helpComplete("globally Clauses", allTypes_3, context, acceptor, 9, 0, true)
		helpComplete("Always Clauses", allTypes_3, context, acceptor, 7, 0, true)
		helpComplete("always Clauses", allTypes_3, context, acceptor, 7, 0, true)
		helpComplete("Sometimes Clauses", allTypes_3, context, acceptor, 10, 0, true)
		helpComplete("sometimes Clauses", allTypes_3, context, acceptor, 10, 0, true)
		helpComplete("Eventually Clauses", allTypes_3, context, acceptor, 11, 0, true)
		helpComplete("eventually Clauses", allTypes_3, context, acceptor, 11, 0, true)
	}
	
	override void completeMainClause_Modifier(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeMainClause_Modifier(model, assignment, context, acceptor)
		helpComplete("Modifier", "Modifier", context, acceptor, 0, 0, true)
	}
	
	override void completeMainClause_Clauses(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeMainClause_Clauses(model, assignment, context, acceptor)
		
		val String featureType = "Clauses"
		val String type_1 = "Clause"
		val String type_2 = "Conjunction"
		val String allTypes_1 = type_1 + " " + type_2 + " : " + featureType
		//val String allTypes_2 = type_1 + " " + type_2 + " " + type_1 + " : " + featureType
		
		helpComplete("Clause and", allTypes_1, context, acceptor, 0, 4, true)
		helpComplete("Clause or", allTypes_1, context, acceptor, 0, 3, true)
		helpComplete("Clause and", "Clause and Clause : " + featureType, context, acceptor, 0, 4, true)
		helpComplete("Clause or", "Clause or Clause : " + featureType, context, acceptor, 0, 3, true)
	}
	
	override void completeClauses_Clauses(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeMainClause_Clauses(model, assignment, context, acceptor)
		
		val String featureType = "Clauses"
		val String type_1 = "Clause"
		val String type_2 = "Conjunction"
		val String allTypes_1 = type_1 + " " + type_2 + " : " + featureType
		//val String allTypes_2 = type_1 + " " + type_2 + " " + type_1 + " : " + featureType
		
		helpComplete("Clause and", allTypes_1, context, acceptor, 0, 4, true)
		helpComplete("Clause or", allTypes_1, context, acceptor, 0, 3, true)
		helpComplete("Clause and", "Clause and Clause : " + featureType, context, acceptor, 0, 4, true)
		helpComplete("Clause or", "Clause or Clause : " + featureType, context, acceptor, 0, 3, true)
	}
	/* 
	override void completeClauses_Conjunction(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeModalitySentence_Begin(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeModalitySentence_Actors(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeModalitySentence_Modelity(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeModalitySentence_Negation(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeModalitySentence_AuxiliarVerb(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeModalitySentence_Predicate(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeModalitySentence_Ending(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePredicateSentence_Begin(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePredicateSentence_Actors(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePredicateSentence_AuxNeg(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePredicateSentence_AuxiliarVerb(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePredicateSentence_Preds(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePredicateSentence_Ending(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeExistenceSentence_Actors(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeExistenceSentence_RelativeClause(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePropertySentence_Actors(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePropertySentence_Property(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePropertySentence_Rela(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePropertySentence_Modality(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePropertySentence_Negation(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePropertySentence_AuxiliarVerb(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePropertySentence_PredObj(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePropertySentence_Ending(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePropertySentence_AuxNeg(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePropertySentence_Constraints(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeProperty_Property(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeSentenceBegin_Rela(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeSentenceEnding_Const(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeSentenceEnding_Rela(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeRelativeClause_Sentence(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeRelativeClause_Conjunction(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeRelativeClause_CondClauses(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeRelativeSentence_Pronoun(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeRelativeSentence_Modelity(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeRelativeSentence_Negation(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeRelativeSentence_Predicate(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeRelativeSentence_Constraints(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeRelativeSentence_Auxiliar(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeRelativeSentence_Clause(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeActors_Actors(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeActors_Conjunction(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}*/
	override void completeActor_Actor(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeActor_Actor(model, assignment, context, acceptor)
		
		val String featureType = "Actor"
		val String type_1 = "Articles|RefArticles|Quantification"
		val String type_2 = "WORD"
		val String type_3 = "Articles"
		val String type_4 = "RefArticles"
		val String type_5 = "Quantification"
		val String allTypes_1 = type_1 + " : " + featureType
		val String allTypes_2 = type_2 + " : " + featureType
		val String allTypes_3 = type_1 + " " + type_2 + " : " + featureType
		val String allTypes_4 = type_3 + " " + type_2 + " : " + featureType
		val String allTypes_5 = type_4 + " " + type_2 + " : " + featureType
		val String allTypes_6 = type_5 + " " + type_2 + " : " + featureType
		
		helpComplete("PreNominative", allTypes_1, context, acceptor, 0, 0, true)
		helpComplete("WORD", allTypes_2, context, acceptor, 0, 0, true)
		helpComplete("PreNominative", allTypes_3, context, acceptor, 0, 0, true)
		
		helpComplete("The WORD", allTypes_4, context, acceptor, 4, 0, true)
		helpComplete("the WORD", allTypes_4, context, acceptor, 4, 0, true)
		helpComplete("A WORD", allTypes_4, context, acceptor, 2, 0, true)
		helpComplete("a WORD", allTypes_4, context, acceptor, 2, 0, true)
		helpComplete("An WORD", allTypes_4, context, acceptor, 3, 0, true)
		helpComplete("an WORD", allTypes_4, context, acceptor, 3, 0, true)
		
		helpComplete("That WORD", allTypes_5, context, acceptor, 5, 0, true)
		helpComplete("that WORD", allTypes_5, context, acceptor, 5, 0, true)
		helpComplete("This WORD", allTypes_5, context, acceptor, 5, 0, true)
		helpComplete("this WORD", allTypes_5, context, acceptor, 5, 0, true)
		
		helpComplete("All WORD", allTypes_6, context, acceptor, 4, 0, true)
		helpComplete("all WORD", allTypes_6, context, acceptor, 4, 0, true)
		helpComplete("Every WORD", allTypes_6, context, acceptor, 6, 0, true)
		helpComplete("every WORD", allTypes_6, context, acceptor, 6, 0, true)
		helpComplete("Each WORD", allTypes_6, context, acceptor, 5, 0, true)
		helpComplete("each WORD", allTypes_6, context, acceptor, 5, 0, true)
		helpComplete("Whole WORD", allTypes_6, context, acceptor, 6, 0, true)
		helpComplete("whole WORD", allTypes_6, context, acceptor, 6, 0, true)
		helpComplete("Any WORD", allTypes_6, context, acceptor, 4, 0, true)
		helpComplete("any WORD", allTypes_6, context, acceptor, 4, 0, true)
		helpComplete("Several WORD", allTypes_6, context, acceptor, 8, 0, true)
		helpComplete("several WORD", allTypes_6, context, acceptor, 8, 0, true)
		helpComplete("Either WORD", allTypes_6, context, acceptor, 7, 0, true)
		helpComplete("either WORD", allTypes_6, context, acceptor, 7, 0, true)
	}
	/* 
	override void completePreds_Predicate(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePreds_PredObj(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePredicate_Predicates(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePredicate_Object(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePredicateObject_Article(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePredicateObject_Object(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeObject_Article(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeObject_Object(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePredOrObject_Predicate(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePredOrObject_PredObj(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeExistencePreface_Modifier(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeAuxNeg_AuxiliarVerb(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeAuxNeg_Negation(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeAuxNeg_AuxiliarVerbNeg(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePreNominative_Determiner(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completePreNominative_Article(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeRelation_RelposAdv(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeRelation_RelDel(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeRelation_RelComp(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeRelation_RelElements(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeRelObjects_Actor(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeRelObjects_Property(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeRelObjects_RelConj(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeConstraints_TimeConstraint(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeConstraints_Constraint(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeConstraint_Ordinator(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeConstraint_Constraint(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeConstraintOrdinators_Stuffing(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeConstraintOrdinators_Adverbial(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeConstraintOrdinators_Comperator(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeSetConstraint_Set(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeTimeConstraint_Ordinator(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeTimeConstraint_Time(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeTimeConstraint_Unit(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeObjectConstraint_Object(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeIntervallConstraints_Lower(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeIntervallConstraints_Higher(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeSingleValueConstraints_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeValueSet_Elements(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeObjectSet_Elements(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeIntValue_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeIntValue_Unit(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeFloatValue_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}
	override void completeFloatValue_Unit(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	}*/

	
	
	override void complete_Requirement(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_Requirement(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_RequirementText(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_RequirementText(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_MainClause(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_MainClause(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_ConditionalClause(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_ConditionalClause(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_Clauses(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_Clauses(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_Clause(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_Clause(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_ModalitySentence(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_ModalitySentence(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_PredicateSentence(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_PredicateSentence(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_ExistenceSentence(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_ExistenceSentence(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_PropertySentence(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_PropertySentence(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_Property(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_Property(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override complete_RelativeClause(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_RelativeClause(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override complete_RelativeSentence(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_RelativeSentence(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_Actors(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_Actors(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_Actor(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_Actor(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_Predicate(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_Predicate(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_PredicateObject(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_PredicateObject(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_Object(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_Object(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
//	override void complete_ExistencePreface(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
//		super.complete_ExistencePreface(model, ruleCall, context, acceptor)
//		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
//	}
	
	override void complete_AuxNeg(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_AuxNeg(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_PreNominative(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_PreNominative(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_Adverbial(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_Adverbial(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_Relation(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_Relation(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_RelObjects(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_RelObjects(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_Constraints(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_Constraints(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_Constraint(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_Constraint(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_ConstraintOrdinators(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_ConstraintOrdinators(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_SetConstraint(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_SetConstraint(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_TimeConstraint(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_TimeConstraint(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_ObjectConstraint(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_ObjectConstraint(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_UnitConstraint(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_UnitConstraint(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_IntervallConstraint(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_IntervallConstraint(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_SingleValueConstraint(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_SingleValueConstraint(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_ValueSet(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_ValueSet(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_ObjectSet(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_ObjectSet(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_Value(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_Value(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_IntValue(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_IntValue(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_FloatValue(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_FloatValue(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	override void complete_ReqID(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_ReqID(model, ruleCall, context, acceptor)
		helpRuleComplete(ruleCall, context, acceptor, 0, 0, true)
	}
	
	
	def void helpComplete(String proposalText, String additionalText, ContentAssistContext context, ICompletionProposalAcceptor acceptor, int start, int end, Boolean box) {  
		val String displayText = if (additionalText.equals("")) proposalText else  proposalText + " - " + additionalText
		val ICompletionProposal proposal = createCompletionProposal(proposalText, displayText, null, context)
		
		if (box) {
			if (proposal instanceof ConfigurableCompletionProposal) {
				val ConfigurableCompletionProposal configurable = proposal;
				configurable.setSelectionStart(configurable.getReplacementOffset() + start);
				configurable.setSelectionLength(proposalText.length() - (start + end));
				configurable.setAutoInsertable(false);
				configurable.setSimpleLinkedMode(context.getViewer(), '\t', ' ');
			}
		}
		acceptor.accept(proposal)
	}
	
	def void helpRuleComplete(RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor, int start, int end, Boolean box) {
		val Assignment ass = GrammarUtil.containingAssignment(ruleCall);
		val Boolean notemptyFeature = ass !== null && ass.getFeature() !== null
		val String feature = if (notemptyFeature && ass.getFeature().equals(ass.getFeature().toLowerCase)) Strings.toFirstUpper(ass.getFeature()) 
								else if (notemptyFeature) ass.getFeature() 
								else ""
		val Boolean notemptyRule = ruleCall !== null && ruleCall.rule !== null && ruleCall.rule.name !== null
		val String proposalText = if (feature.equals("") && notemptyRule) Strings.toFirstUpper(ruleCall.getRule().getName().toLowerCase())
												else feature
		
		val String displayText = if (!feature.equals("") && notemptyRule) proposalText + " - " + ruleCall.getRule().getName() else ""
		
		val prop = if (notemptyRule) getValueConverter().toString(proposalText, ruleCall.getRule().getName()) else ""
		
		if (!displayText.equals("")) {
			val ICompletionProposal proposal = createCompletionProposal(prop, displayText, null, context)
		
			if (box) {
				if (proposal instanceof ConfigurableCompletionProposal) {
					val ConfigurableCompletionProposal configurable = proposal;
					configurable.setSelectionStart(configurable.getReplacementOffset() + start);
					configurable.setSelectionLength(proposalText.length() - (start + end));
					configurable.setAutoInsertable(false);
					configurable.setSimpleLinkedMode(context.getViewer(), '\t', ' ');
				}
			}
			acceptor.accept(proposal)
		}
	}
	
	/* 
		public void complete_ID(EObject model, RuleCall ruleCall, final ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
			String feature = getAssignedFeature(ruleCall);
			String proposalText = feature != null ? feature : Strings.toFirstUpper(ruleCall.getRule().getName().toLowerCase());
			String displayText = proposalText;
			if (feature != null)
				displayText = proposalText + " - " + ruleCall.getRule().getName();
			proposalText = getValueConverter().toString(proposalText, ruleCall.getRule().getName());
			ICompletionProposal proposal = createCompletionProposal(proposalText, displayText, null, context);
			if (proposal instanceof ConfigurableCompletionProposal) {
				ConfigurableCompletionProposal configurable = (ConfigurableCompletionProposal) proposal;
				configurable.setSelectionStart(configurable.getReplacementOffset());
				configurable.setSelectionLength(proposalText.length());
				configurable.setAutoInsertable(false);
				configurable.setSimpleLinkedMode(context.getViewer(), '\t', ' ');
			}
			acceptor.accept(proposal);
		}
			
		private String getAssignedFeature(RuleCall call) {
			Assignment ass = GrammarUtil.containingAssignment(call);
			if (ass != null) {
				String result = ass.getFeature();
				if (result.equals(result.toLowerCase()))
					result = Strings.toFirstUpper(result);
				return result;
			}
			return null;
		}	
	*/
	
	
}
