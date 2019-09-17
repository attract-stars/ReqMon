package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLFactory
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.*
import static org.junit.jupiter.api.Assertions.fail
import de.fraunhofer.isst.stars.requirementDSL.Modality
import org.assertj.core.api.SoftAssertions

//TODO IMMER ZWEI MAL ADDRESSIEREN -> um 
package class SemanticTextElementSwitchTest {
	SemanticTextElementSwitch sw

	@BeforeEach def package void setUp() throws Exception {
		sw = new SemanticTextElementSwitch()
	}

	@AfterEach def package void tearDown() throws Exception {
		sw.getLookup().clear()
	}

	// Test access coordination
	@Test def package void testGetSingleSemanticTextElements() {
		val resElement1 = new SemanticTextElement("baby", RequirementType.OBJECT);
		sw.lookup.put("baby", resElement1);
		val resElement2 = new SemanticTextElement("position", RequirementType.FUNCTION);
		sw.lookup.put("position", resElement2);
		val resElement3 = new SemanticTextElement("is bad", RequirementType.RELATION);
		sw.lookup.put("is bad", resElement3);
		assertThat(sw.singleSemanticTextElements).^as("Analysis of Lookup Size:").hasSize(3);
		assertThat(sw.singleSemanticTextElements).^as("Analysis of Actor Element in Lookup:").contains(resElement1,
			resElement2, resElement3);
	}

	@Test def package void testGetLookup() {
		val resElement1 = new SemanticTextElement("baby", RequirementType.OBJECT);
		sw.lookup.put("baby", resElement1);
		val resElement2 = new SemanticTextElement("position", RequirementType.FUNCTION);
		sw.lookup.put("position", resElement2);
		val resElement3 = new SemanticTextElement("is bad", RequirementType.RELATION);
		sw.lookup.put("is bad", resElement3);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(3);
		assertThat(sw.lookup).^as("Analysis of Actor Element in Lookup:").containsEntry("baby", resElement1);
		assertThat(sw.lookup).^as("Analysis of Actor Element in Lookup:").containsEntry("position", resElement2);
		assertThat(sw.lookup).^as("Analysis of Actor Element in Lookup:").containsEntry("is bad", resElement3);
	}

	// HERE START THE case tests
	@Test def package void testCaseActorActor() {
		var act1 = RequirementDSLFactory.eINSTANCE.createActor()
		act1.actor = "baby";
//		PreNominative=PreNominative? relativ='relative'? (actor=WORD | actor=STRING) 
		var pre1 = RequirementDSLFactory.eINSTANCE.createPreNominative()
		pre1.article = "the";
		act1.preNominative = pre1;
		val resElement = new SemanticTextElement("baby", RequirementType.OBJECT);
		sw.caseActor(act1);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Actor Element in Lookup:").containsEntry("baby", resElement);
		var act2 = RequirementDSLFactory.eINSTANCE.createActor()
		act2.actor = "baby";
//		PreNominative=PreNominative? relativ='relative'? (actor=WORD | actor=STRING) 
		var pre2 = RequirementDSLFactory.eINSTANCE.createPreNominative()
		pre2.article = "the";
		act2.preNominative = pre2;
		sw.caseActor(act2);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Actor Element in Lookup:").containsEntry("baby", resElement);
	}

	@Test def package void testCaseModalitySentenceModalitySentence() {
//		begin=SentenceBegin? actors=Actors modelity=Modality negation?=Negation? auxiliarVerb=AuxiliaryVerb? predicate=Predicate  ending=SentenceEnding?
		fail("Not yet implemented")
	}

	@Test def package void testCaseObjectObject() {
		// article=PreNominative? relativ='relative'? (object+=WORD+ | object+=STRING) 
		var obj1 = RequirementDSLFactory.eINSTANCE.createObject()
		obj1.object.addAll("baby");
//		PreNominative=PreNominative? relativ='relative'? (actor=WORD | actor=STRING) 
		var pre1 = RequirementDSLFactory.eINSTANCE.createPreNominative()
		pre1.article = "the";
		obj1.article = pre1;
		val resElement = new SemanticTextElement("baby", RequirementType.OBJECT);
		sw.caseObject(obj1)
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Actor Element in Lookup:").containsEntry("baby", resElement);
		var obj2 = RequirementDSLFactory.eINSTANCE.createObject()
		obj1.object.addAll("baby");
//		PreNominative=PreNominative? relativ='relative'? (actor=WORD | actor=STRING) 
		var pre2 = RequirementDSLFactory.eINSTANCE.createPreNominative()
		pre2.article = "the";
		obj2.article = pre2;
		sw.caseObject(obj2)
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Actor Element in Lookup:").containsEntry("baby", resElement);
	}

	@Test def package void testCaseRelativeSentenceRelativeSentence() {
		// pronoun=RelativePronounsSubject modelity=Modality negation?=Negation? predicate=Predicate  constraints+=Constraints*  |
		// pronoun=RelativePronounsSubject (auxiliar=WORD negation?=Negation)? predicate=Predicate  constraints+=Constraints* |
		// pronoun=RelativePronounsObject  (clause=ModalitySentence | clause=PredicateSentence)
		val softly = new SoftAssertions();
		// TEST 1
		val existClause11 = RequirementDSLFactory.eINSTANCE.createExistence();
		val relClause11 = RequirementDSLFactory.eINSTANCE.createRelativeClause();
		val sent11 = RequirementDSLFactory.eINSTANCE.createRelativeSentence();
		// 'which' | 'who' | 'that'
		sent11.pronoun = "which";
		sent11.modelity = Modality.MUST;
		sent11.negation = false
		val pred11 = RequirementDSLFactory.eINSTANCE.createPredicate();
		// predicates+=WORD+ | predicates+=STRING | predicates+=WORD+ object=PredicateObject
		pred11.predicates.addAll("is", "working");
		sent11.predicate = pred11
		relClause11.sentence = sent11;
		existClause11.relativeClause = relClause11;
		val actors11 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor11 = RequirementDSLFactory.eINSTANCE.createActor();
		actor11.actor = "David";
		actors11.actors.addAll(actor11)
		existClause11.actors = actors11;
		sw.caseRelativeSentence(sent11);
		val resElement12 = new SemanticTextElement("<David> is working", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Actor Element in Lookup:").containsEntry("<David> is working",
			resElement12);
		val existClause12 = RequirementDSLFactory.eINSTANCE.createExistence();
		val relClause12 = RequirementDSLFactory.eINSTANCE.createRelativeClause();
		val sent12 = RequirementDSLFactory.eINSTANCE.createRelativeSentence();
		// 'which' | 'who' | 'that'
		sent12.pronoun = "which";
		sent12.modelity = Modality.MUST;
		sent12.negation = false
		val pred12 = RequirementDSLFactory.eINSTANCE.createPredicate();
		// predicates+=WORD+ | predicates+=STRING | predicates+=WORD+ object=PredicateObject
		pred12.predicates.addAll("is", "working");
		sent12.predicate = pred12
		relClause12.sentence = sent12;
		existClause12.relativeClause = relClause12;
		val actors12 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor12 = RequirementDSLFactory.eINSTANCE.createActor();
		actor12.actor = "David";
		actors12.actors.addAll(actor12)
		existClause12.actors = actors12;
		sw.caseRelativeSentence(sent12);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Actor Element in Lookup:").containsEntry("<David> is working",
			resElement12);
		sw.lookup.clear;

		// Test 2 - Constraints
		val existClause21 = RequirementDSLFactory.eINSTANCE.createExistence();
		val relClause21 = RequirementDSLFactory.eINSTANCE.createRelativeClause();
		val sent21 = RequirementDSLFactory.eINSTANCE.createRelativeSentence();
		// 'which' | 'who' | 'that'
		sent21.pronoun = "who";
		sent21.modelity = Modality.MUST;
		sent21.negation = false
		val pred21 = RequirementDSLFactory.eINSTANCE.createPredicate();
		// predicates+=WORD+ | predicates+=STRING | predicates+=WORD+ object=PredicateObject
		pred21.predicates.addAll("builds");
		sent21.predicate = pred21
		val consts21 = RequirementDSLFactory.eINSTANCE.createConstraints();
		val const21 = RequirementDSLFactory.eINSTANCE.createConstraint();
		val conOrd21 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd21.stuffing = 
		conOrd21.adverbial = "on"
//		conOrd21.comperator = value
		const21.ordinator = conOrd21;
		val objConstr21 = RequirementDSLFactory.eINSTANCE.createObjectConstraint();
		val obj21 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre21 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre21.article = "the"
		obj21.article = pre21;
		obj21.object.addAll("park", "spot");
		objConstr21.object = obj21;
		const21.constraint = objConstr21;
		consts21.constraint = const21;
		sent21.constraints.addAll(consts21);
		relClause21.sentence = sent21;
		existClause21.relativeClause = relClause21;
		val actors21 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor21 = RequirementDSLFactory.eINSTANCE.createActor();
		actor21.actor = "Manual";
		actors21.actors.addAll(actor21)
		existClause21.actors = actors21;
		sw.caseRelativeSentence(sent21);
		val resElement22 = new SemanticTextElement("<Manual> builds on park spot", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of predicate element in Lookup:").containsEntry(
			"<Manual> builds on park spot", resElement22);
		val existClause22 = RequirementDSLFactory.eINSTANCE.createExistence();
		val relClause22 = RequirementDSLFactory.eINSTANCE.createRelativeClause();
		val sent22 = RequirementDSLFactory.eINSTANCE.createRelativeSentence();
		// 'which' | 'who' | 'that'
		sent22.pronoun = "who";
		sent22.modelity = Modality.MUST;
		sent22.negation = false
		val pred22 = RequirementDSLFactory.eINSTANCE.createPredicate();
		// predicates+=WORD+ | predicates+=STRING | predicates+=WORD+ object=PredicateObject
		pred22.predicates.addAll("builds");
		sent22.predicate = pred22
		val consts22 = RequirementDSLFactory.eINSTANCE.createConstraints();
		val const22 = RequirementDSLFactory.eINSTANCE.createConstraint();
		val conOrd22 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd22.stuffing = 
		conOrd22.adverbial = "on"
//		conOrd22.comperator = value
		const22.ordinator = conOrd22;
		val objConstr22 = RequirementDSLFactory.eINSTANCE.createObjectConstraint();
		val obj22 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre22 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre22.article = "the"
		obj22.article = pre22;
		obj22.object.addAll("park", "spot");
		objConstr22.object = obj22;
		const22.constraint = objConstr22;
		consts22.constraint = const22;
		sent22.constraints.addAll(consts22);
		relClause22.sentence = sent22;
		existClause22.relativeClause = relClause22;
		val actors22 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor22 = RequirementDSLFactory.eINSTANCE.createActor();
		actor22.actor = "Manual";
		actors22.actors.addAll(actor22)
		existClause22.actors = actors22;
		sw.caseRelativeSentence(sent22);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of predicate element in Lookup:").containsEntry(
			"<Manual> builds on park spot", resElement22);
		sw.lookup.clear;

		// TEST 3
		val existClause31 = RequirementDSLFactory.eINSTANCE.createExistence();
		val relClause31 = RequirementDSLFactory.eINSTANCE.createRelativeClause();
		val sent31 = RequirementDSLFactory.eINSTANCE.createRelativeSentence();
		// 'which' | 'who' | 'that'
		sent31.pronoun = "who";
		sent31.auxiliar = "be"
		sent31.negation = true
		val pred31 = RequirementDSLFactory.eINSTANCE.createPredicate();
		// predicates+=WORD+ | predicates+=STRING | predicates+=WORD+ object=PredicateObject
		pred31.predicates.addAll("building");
		sent31.predicate = pred31
		relClause31.sentence = sent31;
		existClause31.relativeClause = relClause31;
		val actors31 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor31 = RequirementDSLFactory.eINSTANCE.createActor();
		actor31.actor = "David";
		actors31.actors.addAll(actor31)
		existClause31.actors = actors31;
		sw.caseRelativeSentence(sent31);
		val resElement32 = new SemanticTextElement("<David> be building", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Actor Element in Lookup:").containsEntry("<David> be building",
			resElement32);
		val existClause32 = RequirementDSLFactory.eINSTANCE.createExistence();
		val relClause32 = RequirementDSLFactory.eINSTANCE.createRelativeClause();
		val sent32 = RequirementDSLFactory.eINSTANCE.createRelativeSentence();
		// 'which' | 'who' | 'that'
		sent32.pronoun = "who";
		sent32.auxiliar = "be"
		sent32.negation = true
		val pred32 = RequirementDSLFactory.eINSTANCE.createPredicate();
		// predicates+=WORD+ | predicates+=STRING | predicates+=WORD+ object=PredicateObject
		pred32.predicates.addAll("building");
		sent32.predicate = pred32
		relClause32.sentence = sent32;
		existClause32.relativeClause = relClause32;
		val actors32 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor32 = RequirementDSLFactory.eINSTANCE.createActor();
		actor32.actor = "David";
		actors32.actors.addAll(actor32)
		existClause32.actors = actors32;
		sw.caseRelativeSentence(sent32);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Actor Element in Lookup:").containsEntry("<David> be building",
			resElement32);
		sw.lookup.clear;

		// Test 4 - Adding multiple
		val existClause41 = RequirementDSLFactory.eINSTANCE.createExistence();
		val relClause41 = RequirementDSLFactory.eINSTANCE.createRelativeClause();
		val sent41 = RequirementDSLFactory.eINSTANCE.createRelativeSentence();
		// 'which' | 'who' | 'that'
		sent41.pronoun = "which";
		sent41.modelity = Modality.MUST;
		sent41.negation = false
		val pred41 = RequirementDSLFactory.eINSTANCE.createPredicate();
		// predicates+=WORD+ | predicates+=STRING | predicates+=WORD+ object=PredicateObject
		pred41.predicates.addAll("is", "working");
		sent41.predicate = pred41
		relClause41.sentence = sent41;
		existClause41.relativeClause = relClause41;
		val actors41 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor41 = RequirementDSLFactory.eINSTANCE.createActor();
		actor41.actor = "David";
		actors41.actors.addAll(actor41)
		existClause41.actors = actors41;
		sw.caseRelativeSentence(sent41);
		val resElement41 = new SemanticTextElement("<David> is working", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Actor Element in Lookup:").containsEntry("<David> is working",
			resElement41);
		val existClause42 = RequirementDSLFactory.eINSTANCE.createExistence();
		val relClause42 = RequirementDSLFactory.eINSTANCE.createRelativeClause();
		val sent42 = RequirementDSLFactory.eINSTANCE.createRelativeSentence();
		// 'which' | 'who' | 'that'
		sent42.pronoun = "which";
		sent42.modelity = Modality.MUST;
		sent42.negation = false
		val pred42 = RequirementDSLFactory.eINSTANCE.createPredicate();
		// predicates+=WORD+ | predicates+=STRING | predicates+=WORD+ object=PredicateObject
		pred42.predicates.addAll("be", "bramming");
		sent42.predicate = pred42
		relClause42.sentence = sent42;
		existClause42.relativeClause = relClause42;
		val actors42 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor42 = RequirementDSLFactory.eINSTANCE.createActor();
		actor42.actor = "Lina";
		actors42.actors.addAll(actor42)
		existClause42.actors = actors42;
		sw.caseRelativeSentence(sent42);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(2);
		softly.assertThat(sw.lookup).^as("Analysis of Actor Element in Lookup:").containsEntry("<David> is working",
			resElement41);
		val resElement42 = new SemanticTextElement("<Lina> be bramming", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Actor Element in Lookup:").containsEntry("<Lina> be bramming",
			resElement42);
		sw.lookup.clear;

		// TEST 5 ModalitySentence
		// pronoun=RelativePronounsObject  (clause=ModalitySentence | clause=PredicateSentence)
		// Is analyzed by the specific test cases
		// Test 6 - PrediateSentence
		// pronoun=RelativePronounsObject  (clause=ModalitySentence | clause=PredicateSentence)
		// Is analyzed by the specific test cases
		softly.assertAll();
	}

	@Test def package void testCasePredicateSentencePredicateSentence() {
//		begin=SentenceBegin? actors=Actors  auxNeg=AuxNeg auxiliarVerb=AuxiliaryVerb? preds=Preds?  ending=SentenceEnding? |
//		begin=SentenceBegin? actors=Actors  preds=Preds  ending=SentenceEnding? 
		val softly = new SoftAssertions();
		// TEST 1 Predicate
		val sent11 = RequirementDSLFactory.eINSTANCE.createPredicateSentence();
		val actors11 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor11 = RequirementDSLFactory.eINSTANCE.createActor();
		actor11.actor = "Barnie"
		actors11.actors.addAll(actor11)
		sent11.actors = actors11
		val preds11 = RequirementDSLFactory.eINSTANCE.createPreds();
		// predicate=Predicate | predObj=PredicateObject
		val pred11 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred11.predicates.addAll("trinking", "tea");
		preds11.predicate = pred11
		sent11.preds = preds11
		sw.casePredicateSentence(sent11);
		val resElement1 = new SemanticTextElement("<Barnie> trinking tea", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> trinking tea", resElement1);
		val sent12 = RequirementDSLFactory.eINSTANCE.createPredicateSentence();
		val actors12 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor12 = RequirementDSLFactory.eINSTANCE.createActor();
		actor12.actor = "Barnie"
		actors12.actors.addAll(actor12)
		sent12.actors = actors12
		val preds12 = RequirementDSLFactory.eINSTANCE.createPreds();
		// predicate=Predicate | predObj=PredicateObject
		val pred12 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred12.predicates.addAll("trinking", "tea");
		preds12.predicate = pred12
		sent12.preds = preds12
		sw.casePredicateSentence(sent12);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> trinking tea", resElement1);
		sw.lookup.clear()

		// TEST 2 PredicateObject
		val sent21 = RequirementDSLFactory.eINSTANCE.createPredicateSentence();
		val actors21 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor21 = RequirementDSLFactory.eINSTANCE.createActor();
		actor21.actor = "Barnie"
		actors21.actors.addAll(actor21)
		sent21.actors = actors21
		val preds21 = RequirementDSLFactory.eINSTANCE.createPreds();
		// predicate=Predicate | predObj=PredicateObject
		val pred21 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred21.predicates.addAll("trinking");
		preds21.predicate = pred21
		val obj21 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre21 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre21.article = "the";
		obj21.article = pre21;
		obj21.object.addAll("tea");
		pred21.object = obj21;
		sent21.preds = preds21
		sw.casePredicateSentence(sent21);
		val resElement2 = new SemanticTextElement("<Barnie> trinking tea", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> trinking tea", resElement2);
		val sent22 = RequirementDSLFactory.eINSTANCE.createPredicateSentence();
		val actors22 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor22 = RequirementDSLFactory.eINSTANCE.createActor();
		actor22.actor = "Barnie"
		actors22.actors.addAll(actor22)
		sent22.actors = actors22
		val preds22 = RequirementDSLFactory.eINSTANCE.createPreds();
		// predicate=Predicate | predObj=PredicateObject
		val pred22 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred22.predicates.addAll("trinking");
		preds22.predicate = pred22
		val obj22 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre22 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre22.article = "the";
		obj22.article = pre22;
		obj22.object.addAll("tea");
		pred22.object = obj22;
		sent22.preds = preds22
		sw.casePredicateSentence(sent22);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> trinking tea", resElement2);
		sw.lookup.clear()

		// TEST 3 PREDS PRedicate OBJET
		val sent31 = RequirementDSLFactory.eINSTANCE.createPredicateSentence();
		val actors31 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor31 = RequirementDSLFactory.eINSTANCE.createActor();
		actor31.actor = "Barnie"
		actors31.actors.addAll(actor31)
		sent31.actors = actors31
		val preds31 = RequirementDSLFactory.eINSTANCE.createPreds();
		// predicate=Predicate | predObj=PredicateObject
		val obj31 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre31 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre31.article = "the";
		obj31.article = pre31;
		obj31.relativ = "relative"
		obj31.object.addAll("gangster");
		preds31.predObj = obj31
		sent31.preds = preds31
		sw.casePredicateSentence(sent31);
		val resElement3 = new SemanticTextElement("<Barnie> relative gangster", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> relative gangster", resElement3);
		val sent32 = RequirementDSLFactory.eINSTANCE.createPredicateSentence();
		val actors32 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor32 = RequirementDSLFactory.eINSTANCE.createActor();
		actor32.actor = "Barnie"
		actors32.actors.addAll(actor32)
		sent32.actors = actors32
		val preds32 = RequirementDSLFactory.eINSTANCE.createPreds();
		// predicate=Predicate | predObj=PredicateObject
		val obj32 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre32 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre32.article = "the";
		obj32.article = pre32;
		obj32.relativ = "relative"
		obj32.object.addAll("gangster");
		preds32.predObj = obj32
		sent32.preds = preds32
		sw.casePredicateSentence(sent32);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> relative gangster", resElement3);
		sw.lookup.clear()

		// TEST 4 Auxiliar Predicate
		val sent41 = RequirementDSLFactory.eINSTANCE.createPredicateSentence();
		val actors41 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor41 = RequirementDSLFactory.eINSTANCE.createActor();
		actor41.actor = "Barnie"
		actors41.actors.addAll(actor41)
		sent41.actors = actors41
//		 auxNeg=AuxNeg
		val auxNeg41 = RequirementDSLFactory.eINSTANCE.createAuxNeg();
		auxNeg41.auxiliarVerbNeg = "doesn\'t"
		auxNeg41.auxiliarVerb = "be";
		sent41.auxNeg = auxNeg41
		sent41.auxiliarVerb = "gun"
		val preds41 = RequirementDSLFactory.eINSTANCE.createPreds();
		// predicate=Predicate | predObj=PredicateObject
		val pred41 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred41.predicates.addAll("trinking");
		preds41.predicate = pred41
		val obj41 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre41 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre41.article = "the";
		obj41.article = pre41;
		obj41.object.addAll("tea");
		pred41.object = obj41;
		sent41.preds = preds41
		sw.casePredicateSentence(sent41);
		val resElement4 = new SemanticTextElement("<Barnie> be gun trinking tea", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> be gun trinking tea", resElement4);
		val sent42 = RequirementDSLFactory.eINSTANCE.createPredicateSentence();
		val actors42 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor42 = RequirementDSLFactory.eINSTANCE.createActor();
		actor42.actor = "Barnie"
		actors42.actors.addAll(actor42)
		sent42.actors = actors42
		val auxNeg42 = RequirementDSLFactory.eINSTANCE.createAuxNeg();
		auxNeg42.auxiliarVerbNeg = "doesn\'t"
		auxNeg42.auxiliarVerb = "be";
		sent42.auxNeg = auxNeg42
		sent42.auxiliarVerb = "gun"
		val preds42 = RequirementDSLFactory.eINSTANCE.createPreds();
		// predicate=Predicate | predObj=PredicateObject
		val pred42 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred42.predicates.addAll("trinking");
		preds42.predicate = pred42
		val obj42 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre42 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre42.article = "the";
		obj42.article = pre42;
		obj42.object.addAll("tea");
		pred42.object = obj42;
		sent42.preds = preds42
		sw.casePredicateSentence(sent42);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> be gun trinking tea", resElement4);
		sw.lookup.clear()

		// TEST 5 Auxiliar PredicateObject
		val sent51 = RequirementDSLFactory.eINSTANCE.createPredicateSentence();
		val actors51 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor51 = RequirementDSLFactory.eINSTANCE.createActor();
		actor51.actor = "Barnie"
		actors51.actors.addAll(actor51)
		sent51.actors = actors51
		val auxNeg51 = RequirementDSLFactory.eINSTANCE.createAuxNeg();
		auxNeg51.auxiliarVerbNeg = "doesn\'t"
		auxNeg51.auxiliarVerb = "be";
		sent51.auxNeg = auxNeg51
		sent51.auxiliarVerb = "gun"
		val preds51 = RequirementDSLFactory.eINSTANCE.createPreds();
		// predicate=Predicate | predObj=PredicateObject
		val obj51 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre51 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre51.article = "the";
		obj51.article = pre51;
		obj51.relativ = "relative"
		obj51.object.addAll("gangster");
		preds51.predObj = obj51
		sent51.preds = preds51
		sw.casePredicateSentence(sent51);
		val resElement5 = new SemanticTextElement("<Barnie> be gun relative gangster", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> be gun relative gangster", resElement5);
		val sent52 = RequirementDSLFactory.eINSTANCE.createPredicateSentence();
		val actors52 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor52 = RequirementDSLFactory.eINSTANCE.createActor();
		actor52.actor = "Barnie"
		actors52.actors.addAll(actor52)
		sent52.actors = actors52
		val auxNeg52 = RequirementDSLFactory.eINSTANCE.createAuxNeg();
		auxNeg52.auxiliarVerbNeg = "doesn\'t"
		auxNeg52.auxiliarVerb = "be";
		sent52.auxNeg = auxNeg52
		sent52.auxiliarVerb = "gun"
		val preds52 = RequirementDSLFactory.eINSTANCE.createPreds();
		// predicate=Predicate | predObj=PredicateObject
		val obj52 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre52 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre52.article = "the";
		obj52.article = pre52;
		obj52.relativ = "relative"
		obj52.object.addAll("gangster");
		preds52.predObj = obj52
		sent52.preds = preds52
		sw.casePredicateSentence(sent52);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> be gun relative gangster", resElement5);
		sw.lookup.clear()
		
		//TEST 6 - Sentence Ending
		val sent61 = RequirementDSLFactory.eINSTANCE.createPredicateSentence();
		val actors61 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor61 = RequirementDSLFactory.eINSTANCE.createActor();
		actor61.actor = "Barnie"
		actors61.actors.addAll(actor61)
		sent61.actors = actors61
		val preds61 = RequirementDSLFactory.eINSTANCE.createPreds();
		// predicate=Predicate | predObj=PredicateObject
		val pred61 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred61.predicates.addAll("trinking", "tea");
		preds61.predicate = pred61
		sent61.preds = preds61
		val end61 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
//		rela=Relation const+=Constraints+
		val rel61 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel61.relDel = "on"
		val relObj61 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj61 =RequirementDSLFactory.eINSTANCE.createObject();
		obj61.object.addAll("house","boot")
		relObj61.object.addAll(obj61);
		rel61.relElements = relObj61
		end61.rela = rel61
		sent61.ending = end61
		sw.casePredicateSentence(sent61);
		val resElement6 = new SemanticTextElement("<Barnie> trinking tea on house boot", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> trinking tea on house boot", resElement6);
		val sent62 = RequirementDSLFactory.eINSTANCE.createPredicateSentence();
		val actors62 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor62 = RequirementDSLFactory.eINSTANCE.createActor();
		actor62.actor = "Barnie"
		actors62.actors.addAll(actor62)
		sent62.actors = actors62
		val preds62 = RequirementDSLFactory.eINSTANCE.createPreds();
		// predicate=Predicate | predObj=PredicateObject
		val pred62 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred62.predicates.addAll("trinking", "tea");
		preds62.predicate = pred62
		sent62.preds = preds62
		val end62 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
//		rela=Relation const+=Constraints+
		val rel62 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel62.relDel = "on"
		val relObj62 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj62 =RequirementDSLFactory.eINSTANCE.createObject();
		obj62.object.addAll("house","boot")
		relObj62.object.addAll(obj62);
		rel62.relElements = relObj62
		end62.rela = rel62
		sent62.ending = end62
		sw.casePredicateSentence(sent62);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> trinking tea on house boot", resElement6);
		sw.lookup.clear()

		softly.assertAll();
	}

	@Test def package void testCasePropertyProperty1() {
		// PROPERTY_TERM relativ='relative'? (property+=WORD+ | property+=STRING)
		val prop1 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop1.property.addAll("position");
		val resElement1 = new SemanticTextElement("position", RequirementType.FUNCTION);
		sw.caseProperty(prop1);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry("position", resElement1);
		val prop12 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop12.property.addAll("position");
		sw.caseProperty(prop12);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry("position", resElement1);
	}

	@Test def package void testCasePropertyProperty2() {
		val prop2 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop2.relativ = "relative";
		prop2.property.addAll("position");
		val resElement2 = new SemanticTextElement("relative position", RequirementType.FUNCTION);
		sw.caseProperty(prop2);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry("relative position",
			resElement2);
		val prop22 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop22.relativ = "relative";
		prop22.property.addAll("position");
		sw.caseProperty(prop22);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry("relative position",
			resElement2);
	}

	@Test def package void testCasePropertyProperty3() {
		val prop3 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop3.property.addAll("house", "spot");
		val resElement3 = new SemanticTextElement("house spot", RequirementType.FUNCTION);
		sw.caseProperty(prop3);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry("house spot", resElement3);
		val prop32 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop32.property.addAll("house", "spot");
		sw.caseProperty(prop32);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry("house spot", resElement3);
	}

	@Test def package void testCasePropertySentencePropertySentence() {
//		actors=Actors property=Property rela=Relation? modality=Modality negation?=Negation? auxiliarVerb=AuxiliaryVerb? predObj=PredOrObject  ending=SentenceEnding? |
//	actors=Actors property=Property rela=Relation? auxNeg=AuxNeg (predObj=PredOrObject | constraints=Constraints) ending=SentenceEnding?
		fail("Not yet implemented")
	}

}
