package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLFactory
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.*

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
		val softly = new SoftAssertions();
		// TEST 1 Predicate
		val sent11 = RequirementDSLFactory.eINSTANCE.createModalitySentence();
		val actors11 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor11 = RequirementDSLFactory.eINSTANCE.createActor();
		actor11.actor = "Barnie"
		actors11.actors.addAll(actor11)
		sent11.actors = actors11
		val pred11 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred11.predicates.addAll("trinking", "tea");
		sent11.predicate = pred11
		sw.caseModalitySentence(sent11);
		val resElement1 = new SemanticTextElement("<Barnie> trinking tea", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> trinking tea", resElement1);
		val sent12 = RequirementDSLFactory.eINSTANCE.createModalitySentence();
		val actors12 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor12 = RequirementDSLFactory.eINSTANCE.createActor();
		actor12.actor = "Barnie"
		actors12.actors.addAll(actor12)
		sent12.actors = actors12
		val pred12 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred12.predicates.addAll("trinking", "tea");
		sent12.predicate = pred12
		sw.caseModalitySentence(sent12);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> trinking tea", resElement1);
		sw.lookup.clear()

		// TEST 2 Predicate PredicateObject
		val sent21 = RequirementDSLFactory.eINSTANCE.createModalitySentence();
		val actors21 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor21 = RequirementDSLFactory.eINSTANCE.createActor();
		actor21.actor = "Barnie"
		actors21.actors.addAll(actor21)
		sent21.actors = actors21
		val pred21 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred21.predicates.addAll("trinking");
		val obj21 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre21 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre21.article = "the";
		obj21.article = pre21;
		obj21.object.addAll("tea");
		pred21.object = obj21;
		sent21.predicate = pred21
		sw.caseModalitySentence(sent21);
		val resElement2 = new SemanticTextElement("<Barnie> trinking tea", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> trinking tea", resElement2);
		val sent22 = RequirementDSLFactory.eINSTANCE.createModalitySentence();
		val actors22 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor22 = RequirementDSLFactory.eINSTANCE.createActor();
		actor22.actor = "Barnie"
		actors22.actors.addAll(actor22)
		sent22.actors = actors22
		val pred22 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred22.predicates.addAll("trinking");
		val obj22 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre22 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre22.article = "the";
		obj22.article = pre22;
		obj22.object.addAll("tea");
		pred22.object = obj22;
		sent22.predicate = pred22
		sw.caseModalitySentence(sent22);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> trinking tea", resElement2);
		sw.lookup.clear()

		// TEST 4 Auxiliar Predicate
		val sent41 = RequirementDSLFactory.eINSTANCE.createModalitySentence();
		val actors41 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor41 = RequirementDSLFactory.eINSTANCE.createActor();
		actor41.actor = "Barnie"
		actors41.actors.addAll(actor41)
		sent41.actors = actors41
		sent41.modelity = "must"
		sent41.auxiliarVerb = "gun"
		val pred41 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred41.predicates.addAll("trinking");
		val obj41 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre41 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre41.article = "the";
		obj41.article = pre41;
		obj41.object.addAll("tea");
		pred41.object = obj41;
		sent41.predicate = pred41
		sw.caseModalitySentence(sent41);
		val resElement4 = new SemanticTextElement("<Barnie> gun trinking tea", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> gun trinking tea", resElement4);
		val sent42 = RequirementDSLFactory.eINSTANCE.createModalitySentence();
		val actors42 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor42 = RequirementDSLFactory.eINSTANCE.createActor();
		actor42.actor = "Barnie"
		actors42.actors.addAll(actor42)
		sent42.actors = actors42
		sent41.modelity = "must"
		sent42.auxiliarVerb = "gun"
		val pred42 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred42.predicates.addAll("trinking");
		val obj42 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre42 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre42.article = "the";
		obj42.article = pre42;
		obj42.object.addAll("tea");
		pred42.object = obj42;
		sent42.predicate = pred42
		sw.caseModalitySentence(sent42);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> gun trinking tea", resElement4);
		sw.lookup.clear()

		// TEST 4 Auxiliar Predicate
		val sent51 = RequirementDSLFactory.eINSTANCE.createModalitySentence();
		val actors51 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor51 = RequirementDSLFactory.eINSTANCE.createActor();
		actor51.actor = "Barnie"
		actors51.actors.addAll(actor51)
		sent51.actors = actors51
		sent51.modelity = "must"
		sent51.negation = true
		sent51.auxiliarVerb = "gun"
		val pred51 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred51.predicates.addAll("trinking");
		val obj51 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre51 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre51.article = "the";
		obj51.article = pre51;
		obj51.object.addAll("tea");
		pred51.object = obj51;
		sent51.predicate = pred51
		sw.caseModalitySentence(sent51);
		val resElement5 = new SemanticTextElement("<Barnie> gun trinking tea", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> gun trinking tea", resElement5);
		val sent52 = RequirementDSLFactory.eINSTANCE.createModalitySentence();
		val actors52 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor52 = RequirementDSLFactory.eINSTANCE.createActor();
		actor52.actor = "Barnie"
		actors52.actors.addAll(actor52)
		sent52.actors = actors52
		sent41.modelity = "must"
		sent51.negation = true
		sent52.auxiliarVerb = "gun"
		val pred52 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred52.predicates.addAll("trinking");
		val obj52 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre52 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre52.article = "the";
		obj52.article = pre52;
		obj52.object.addAll("tea");
		pred52.object = obj52;
		sent52.predicate = pred52
		sw.caseModalitySentence(sent52);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> gun trinking tea", resElement5);
		sw.lookup.clear()

		// TEST 6 - Sentence Ending
		val sent61 = RequirementDSLFactory.eINSTANCE.createModalitySentence();
		val actors61 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor61 = RequirementDSLFactory.eINSTANCE.createActor();
		actor61.actor = "Barnie"
		actors61.actors.addAll(actor61)
		sent61.actors = actors61
		val pred61 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred61.predicates.addAll("trinking", "tea");
		sent61.predicate = pred61
		val end61 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
//		rela=Relation const+=Constraints+
		val rel61 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel61.relDel = "on"
		val relObj61 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj61 = RequirementDSLFactory.eINSTANCE.createObject();
		obj61.object.addAll("house", "boot")
		relObj61.object.addAll(obj61);
		rel61.relElements = relObj61
		end61.rela = rel61
		sent61.ending = end61
		sw.caseModalitySentence(sent61);
		val resElement6 = new SemanticTextElement("<Barnie> trinking tea on house boot", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> trinking tea on house boot", resElement6);
		val sent62 = RequirementDSLFactory.eINSTANCE.createModalitySentence();
		val actors62 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor62 = RequirementDSLFactory.eINSTANCE.createActor();
		actor62.actor = "Barnie"
		actors62.actors.addAll(actor62)
		sent62.actors = actors62
		val pred62 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred62.predicates.addAll("trinking", "tea");
		sent62.predicate = pred62
		val end62 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
//		rela=Relation const+=Constraints+
		val rel62 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel62.relDel = "on"
		val relObj62 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj62 = RequirementDSLFactory.eINSTANCE.createObject();
		obj62.object.addAll("house", "boot")
		relObj62.object.addAll(obj62);
		rel62.relElements = relObj62
		end62.rela = rel62
		sent62.ending = end62
		sw.caseModalitySentence(sent62);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> trinking tea on house boot", resElement6);
		sw.lookup.clear()

		// TEST 7 - Sentence Beginning
		val sent71 = RequirementDSLFactory.eINSTANCE.createModalitySentence();
		val begin71 = RequirementDSLFactory.eINSTANCE.createSentenceBegin
		val rel71 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel71.relDel = "on"
		val relObj71 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj71 = RequirementDSLFactory.eINSTANCE.createObject();
		obj71.object.addAll("house", "boot")
		relObj71.object.addAll(obj71);
		rel71.relElements = relObj71
		relObj71.object.addAll(obj71);
		rel71.relElements = relObj71
		begin71.rela = rel71
		sent71.begin = begin71
		val actors71 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor71 = RequirementDSLFactory.eINSTANCE.createActor();
		actor71.actor = "Barnie"
		actors71.actors.addAll(actor71)
		sent71.actors = actors71
		val pred71 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred71.predicates.addAll("trinking", "tea");
		sent71.predicate = pred71
		sw.caseModalitySentence(sent71);
		val resElement7 = new SemanticTextElement("<Barnie> trinking tea on house boot", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> trinking tea on house boot", resElement7);
		val sent72 = RequirementDSLFactory.eINSTANCE.createModalitySentence();
		val begin72 = RequirementDSLFactory.eINSTANCE.createSentenceBegin
		val rel72 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel72.relDel = "on"
		val relObj72 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj72 = RequirementDSLFactory.eINSTANCE.createObject();
		obj72.object.addAll("house", "boot")
		relObj72.object.addAll(obj72);
		rel72.relElements = relObj72
		begin72.rela = rel72
		sent72.begin = begin72
		val actors72 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor72 = RequirementDSLFactory.eINSTANCE.createActor();
		actor72.actor = "Barnie"
		actors72.actors.addAll(actor72)
		sent72.actors = actors72
		val pred72 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred72.predicates.addAll("trinking", "tea");
		sent72.predicate = pred72
		sw.caseModalitySentence(sent72);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> trinking tea on house boot", resElement7);
		sw.lookup.clear()

		softly.assertAll();
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
		sent11.modelity = "must";
		sent11.negation = false
		val pred11 = RequirementDSLFactory.eINSTANCE.createPredicate();
		// predicates+=WORD+ | predicates+=STRING | predicates+=WORD+ object=PredicateObject
		pred11.predicates.addAll("is", "working");
		sent11.predicate = pred11
		relClause11.sentences.add(sent11);
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
		sent12.modelity = "must";
		sent12.negation = false
		val pred12 = RequirementDSLFactory.eINSTANCE.createPredicate();
		// predicates+=WORD+ | predicates+=STRING | predicates+=WORD+ object=PredicateObject
		pred12.predicates.addAll("is", "working");
		sent12.predicate = pred12
		relClause12.sentences.add(sent12);
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
		sent21.modelity = "must";
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
		consts21.constraint.add(const21);
		sent21.constraints = consts21;
		relClause21.sentences.add(sent21);
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
		sent22.modelity = "must";
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
		consts22.constraint.add(const22);
		sent22.constraints = consts22;
		relClause22.sentences.add(sent22);
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
		relClause31.sentences.add(sent31);
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
		relClause32.sentences.add(sent32);
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
		sent41.modelity = "must";
		sent41.negation = false
		val pred41 = RequirementDSLFactory.eINSTANCE.createPredicate();
		// predicates+=WORD+ | predicates+=STRING | predicates+=WORD+ object=PredicateObject
		pred41.predicates.addAll("is", "working");
		sent41.predicate = pred41
		relClause41.sentences.add(sent41);
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
		sent42.modelity = "must";
		sent42.negation = false
		val pred42 = RequirementDSLFactory.eINSTANCE.createPredicate();
		// predicates+=WORD+ | predicates+=STRING | predicates+=WORD+ object=PredicateObject
		pred42.predicates.addAll("be", "bramming");
		sent42.predicate = pred42
		relClause42.sentences.add(sent42);
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
		val pred11 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred11.predicates.addAll("trinking", "tea");
		sent11.predicate = pred11
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
		val pred12 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred12.predicates.addAll("trinking", "tea");
		sent12.predicate = pred12
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
		val pred21 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred21.predicates.addAll("trinking");
		val obj21 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre21 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre21.article = "the";
		obj21.article = pre21;
		obj21.object.addAll("tea");
		pred21.object = obj21;
		sent21.predicate = pred21
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
		val pred22 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred22.predicates.addAll("trinking");
		val obj22 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre22 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre22.article = "the";
		obj22.article = pre22;
		obj22.object.addAll("tea");
		pred22.object = obj22;
		sent22.predicate = pred22
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
		val pred31 = RequirementDSLFactory.eINSTANCE.createPredicate();
		val obj31 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre31 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre31.article = "the";
		obj31.article = pre31;
		obj31.relativ = "relative"
		obj31.object.addAll("gangster");
		pred31.object = obj31
		sent31.predicate = pred31
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
		val pred32 = RequirementDSLFactory.eINSTANCE.createPredicate();
		val obj32 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre32 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre32.article = "the";
		obj32.article = pre32;
		obj32.relativ = "relative"
		obj32.object.addAll("gangster");
		pred32.object = obj32
		sent32.predicate = pred32
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
		val pred41 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred41.predicates.addAll("trinking");
		val obj41 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre41 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre41.article = "the";
		obj41.article = pre41;
		obj41.object.addAll("tea");
		pred41.object = obj41;
		sent41.predicate = pred41
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
		val pred42 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred42.predicates.addAll("trinking");
		val obj42 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre42 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre42.article = "the";
		obj42.article = pre42;
		obj42.object.addAll("tea");
		pred42.object = obj42;
		sent42.predicate = pred42
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
		val pred51 = RequirementDSLFactory.eINSTANCE.createPredicate();
		val obj51 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre51 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre51.article = "the";
		obj51.article = pre51;
		obj51.relativ = "relative"
		obj51.object.addAll("gangster");
		pred51.object = obj51
		sent51.predicate = pred51
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
		val pred52 = RequirementDSLFactory.eINSTANCE.createPredicate();
		val obj52 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre52 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre52.article = "the";
		obj52.article = pre52;
		obj52.relativ = "relative"
		obj52.object.addAll("gangster");
		pred52.object = obj52
		sent52.predicate = pred52
		sw.casePredicateSentence(sent52);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie> be gun relative gangster", resElement5);
		sw.lookup.clear()

		// TEST 6 - Sentence Ending
		val sent61 = RequirementDSLFactory.eINSTANCE.createPredicateSentence();
		val actors61 = RequirementDSLFactory.eINSTANCE.createActors();
		val actor61 = RequirementDSLFactory.eINSTANCE.createActor();
		actor61.actor = "Barnie"
		actors61.actors.addAll(actor61)
		sent61.actors = actors61
		val pred61 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred61.predicates.addAll("trinking", "tea");
		sent61.predicate = pred61
		val end61 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
//		rela=Relation const+=Constraints+
		val rel61 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel61.relDel = "on"
		val relObj61 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj61 = RequirementDSLFactory.eINSTANCE.createObject();
		obj61.object.addAll("house", "boot")
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
		val pred62 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred62.predicates.addAll("trinking", "tea");
		sent62.predicate = pred62
		val end62 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
//		rela=Relation const+=Constraints+
		val rel62 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel62.relDel = "on"
		val relObj62 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj62 = RequirementDSLFactory.eINSTANCE.createObject();
		obj62.object.addAll("house", "boot")
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
	
	@Test def package void testCaseRelObjectPropertyRelObjectProperty() {
		// ObjectProperty:	object=Object property=Property
		// PROPERTY_TERM relativ='relative'? (property+=WORD+ | property+=STRING)
		val objProp1 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty;
		val obj1 = RequirementDSLFactory.eINSTANCE.createObject();
		obj1.object.addAll("car", "truck")
		objProp1.object = obj1
		val prop1 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop1.property.addAll("position");
		objProp1.property = prop1
		val resElement1 = new SemanticTextElement("car truck's position", RequirementType.FUNCTION);
		sw.caseRelObjectProperty(objProp1);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry("car truck's position",
			resElement1);
		val objProp12 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj12 = RequirementDSLFactory.eINSTANCE.createObject();
		obj12.object.addAll("car", "truck")
		objProp12.object = obj12
		val prop12 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop12.property.addAll("position");
		objProp12.property = prop12
		sw.caseRelObjectProperty(objProp12);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry("car truck's position",
			resElement1);
		sw.lookup.clear

		val objProp2 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj2 = RequirementDSLFactory.eINSTANCE.createObject();
		obj2.object.addAll("car", "truck")
		objProp2.object = obj2
		val prop2 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop2.relativ = "relative";
		prop2.property.addAll("position");
		objProp2.property = prop2
		val resElement2 = new SemanticTextElement("car truck's relative position", RequirementType.FUNCTION);
		sw.caseRelObjectProperty(objProp2);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"car truck's relative position", resElement2);
		val objProp22 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj22 = RequirementDSLFactory.eINSTANCE.createObject();
		obj22.object.addAll("car", "truck")
		objProp22.object = obj22
		val prop22 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop22.relativ = "relative";
		prop22.property.addAll("position");
		objProp22.property = prop22
		sw.caseRelObjectProperty(objProp22);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"car truck's relative position", resElement2);
		sw.lookup.clear

		val objProp3 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj3 = RequirementDSLFactory.eINSTANCE.createObject();
		obj3.object.addAll("car", "truck")
		objProp3.object = obj3
		val prop3 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop3.property.addAll("house", "spot");
		objProp3.property = prop3
		val resElement3 = new SemanticTextElement("car truck's house spot", RequirementType.FUNCTION);
		sw.caseRelObjectProperty(objProp3);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry("car truck's house spot",
			resElement3);
		val objProp32 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj32 = RequirementDSLFactory.eINSTANCE.createObject();
		obj32.object.addAll("car", "truck")
		objProp32.object = obj32
		val prop32 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop32.property.addAll("house", "spot");
		objProp32.property = prop32
		sw.caseRelObjectProperty(objProp32);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry("car truck's house spot",
			resElement3);
		sw.lookup.clear
	}

	@Test def package void testCaseActorPropertyActorProperty() {
		// ObjectProperty:	object=Object property=Property
		// PROPERTY_TERM relativ='relative'? (property+=WORD+ | property+=STRING)
		val actProp1 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val obj1 = RequirementDSLFactory.eINSTANCE.createObject();
		obj1.object.addAll("car", "truck")
		actProp1.object = obj1
		val prop1 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop1.property.addAll("position");
		actProp1.property = prop1
		val resElement1 = new SemanticTextElement("car truck's position", RequirementType.FUNCTION);
		sw.caseActorProperty(actProp1);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry("car truck's position",
			resElement1);
		val actProp12 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val obj12 = RequirementDSLFactory.eINSTANCE.createObject();
		obj12.object.addAll("car", "truck")
		actProp12.object = obj12
		val prop12 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop12.property.addAll("position");
		actProp12.property = prop12
		sw.caseActorProperty(actProp12);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry("car truck's position",
			resElement1);
		sw.lookup.clear

		val actProp2 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val obj2 = RequirementDSLFactory.eINSTANCE.createObject();
		obj2.object.addAll("car", "truck")
		actProp2.object = obj2
		val prop2 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop2.relativ = "relative";
		prop2.property.addAll("position");
		actProp2.property = prop2
		val resElement2 = new SemanticTextElement("car truck's relative position", RequirementType.FUNCTION);
		sw.caseActorProperty(actProp2);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"car truck's relative position", resElement2);
		val actProp22 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val obj22 = RequirementDSLFactory.eINSTANCE.createObject();
		obj22.object.addAll("car", "truck")
		actProp22.object = obj22
		val prop22 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop22.relativ = "relative";
		prop22.property.addAll("position");
		actProp22.property = prop22
		sw.caseActorProperty(actProp22);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"car truck's relative position", resElement2);
		sw.lookup.clear

		val actProp3 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val obj3 = RequirementDSLFactory.eINSTANCE.createObject();
		obj3.object.addAll("car", "truck")
		actProp3.object = obj3
		val prop3 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop3.property.addAll("house", "spot");
		actProp3.property = prop3
		val resElement3 = new SemanticTextElement("car truck's house spot", RequirementType.FUNCTION);
		sw.caseActorProperty(actProp3);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry("car truck's house spot",
			resElement3);
		val actProp32 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val obj32 = RequirementDSLFactory.eINSTANCE.createObject();
		obj32.object.addAll("car", "truck")
		actProp32.object = obj32
		val prop32 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop32.property.addAll("house", "spot");
		actProp32.property = prop32
		sw.caseActorProperty(actProp32);
		assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry("car truck's house spot",
			resElement3);
		sw.lookup.clear
	}


	@Test def package void testCasePropertySentencePropertySentence() {
//		actors=Actors property=Property rela=Relation? modality=Modality negation?=Negation? auxiliarVerb=AuxiliaryVerb? predObj=PredOrObject  ending=SentenceEnding? |
//	actors=Actors property=Property rela=Relation? auxNeg=AuxNeg (predObj=PredOrObject | constraints=Constraints) ending=SentenceEnding?
		val softly = new SoftAssertions();
		// TEST 1 Predicate
		val sent11 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp11 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp11 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object11 = RequirementDSLFactory.eINSTANCE.createObject();
		object11.object.addAll("Barnie")
		actProp11.object = object11
		val prop11 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop11.relativ = "relative"
		prop11.property.addAll("mum")
		actProp11.property = prop11;
		actorProp11.property.addAll(actProp11)
		sent11.properties = actorProp11
		val pred11 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred11.predicates.addAll("trinking", "tea");
		sent11.predicate = pred11
		sw.casePropertySentence(sent11);
		val resElement1 = new SemanticTextElement("<Barnie's mum> trinking tea", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's mum> trinking tea", resElement1);
		val sent12 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp12 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp12 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object12 = RequirementDSLFactory.eINSTANCE.createObject();
		object12.object.addAll("Barnie")
		actProp12.object = object12
		val prop12 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop12.relativ = "relative"
		prop12.property.addAll("mum")
		actProp12.property = prop12;
		actorProp12.property.addAll(actProp12)
		sent12.properties = actorProp11
		val pred12 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred12.predicates.addAll("trinking", "tea");
		sent12.predicate = pred12
		sw.casePropertySentence(sent12);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry("<Barnie's mum> trinking tea", resElement1);
		sw.lookup.clear()

		// TEST 2 Relative Property
		val sent21 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp21 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp21 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object21 = RequirementDSLFactory.eINSTANCE.createObject();
		object21.object.addAll("Barnie")
		actProp21.object = object21
		val prop21 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop21.relativ = "relative"
		prop21.property.addAll("mum")
		actProp21.property = prop21;
		actorProp21.property.addAll(actProp21)
		sent21.properties = actorProp21
		val pred21 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred21.predicates.addAll("trinking", "tea");
		sent21.predicate = pred21
		sw.casePropertySentence(sent21);
		val resElement2 = new SemanticTextElement("<Barnie's relative mum> trinking tea", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's relative mum> trinking tea", resElement2);
		val sent22 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp22 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp22 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object22 = RequirementDSLFactory.eINSTANCE.createObject();
		object22.object.addAll("Barnie")
		actProp22.object = object22
		val prop22 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop22.relativ = "relative"
		prop22.property.addAll("mum")
		actProp22.property = prop22;
		actorProp22.property.addAll(actProp22)
		sent22.properties = actorProp22
		val pred22 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred22.predicates.addAll("trinking", "tea");
		sent22.predicate = pred22
		sw.casePropertySentence(sent22);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's relative mum> trinking tea", resElement2);
		sw.lookup.clear()

		// TEST 3 Property with Relation
		val sent31 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp31 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp31 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object31 = RequirementDSLFactory.eINSTANCE.createObject();
		object31.object.addAll("Barnie")
		actProp31.object = object31
		val prop31 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop31.property.addAll("mum")
		actProp31.property = prop31;
		actorProp31.property.addAll(actProp31)
		sent31.properties = actorProp31
		val rel31 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel31.relDel = "in relation to"
		val relObj31 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj61 = RequirementDSLFactory.eINSTANCE.createObject();
		obj61.object.addAll("daddy")
		relObj31.object.addAll(obj61);
		rel31.relElements = relObj31
		actProp31.rela = rel31;
		val pred31 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred31.predicates.addAll("trinking", "tea");
		sent31.predicate = pred31
		sw.casePropertySentence(sent31);
		val resElement3 = new SemanticTextElement("<Barnie's mum in relation to daddy> trinking tea",
			RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's mum in relation to daddy> trinking tea", resElement3);
		val sent32 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp32 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp32 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object32 = RequirementDSLFactory.eINSTANCE.createObject();
		object32.object.addAll("Barnie")
		actProp32.object = object32
		val prop32 = RequirementDSLFactory.eINSTANCE.createProperty();
		prop32.property.addAll("mum")
		actProp32.property = prop32;
		actorProp32.property.addAll(actProp32)
		sent32.properties = actorProp32
		val rel32 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel32.relDel = "in relation to"
		val relObj32 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj62 = RequirementDSLFactory.eINSTANCE.createObject();
		obj62.object.addAll("daddy")
		relObj32.object.addAll(obj62);
		rel32.relElements = relObj32
		actProp32.rela = rel32
		val pred32 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred32.predicates.addAll("trinking", "tea");
		sent32.predicate = pred32
		sw.casePropertySentence(sent32);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's mum in relation to daddy> trinking tea", resElement3);
		sw.lookup.clear()

		// TEST 4 Predicate PredicateObject
		val sent41 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp41 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp41 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object41 = RequirementDSLFactory.eINSTANCE.createObject();
		object41.object.addAll("Barnie")
		actProp41.object = object41
		val prop41 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop41.relativ = "relative"
		prop41.property.addAll("mum")
		actProp41.property = prop41;
		actorProp41.property.addAll(actProp41)
		sent41.properties = actorProp41
		val pred41 = RequirementDSLFactory.eINSTANCE.createPredicate();
		val obj41 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre41 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre41.article = "the";
		obj41.article = pre41;
		obj41.object.addAll("tea");
		pred41.object = obj41;
		sent41.predicate = pred41
		sw.casePropertySentence(sent41);
		val resElement4 = new SemanticTextElement("<Barnie's mum> tea", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry("<Barnie's mum> tea",
			resElement4);
		val sent42 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp42 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp42 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object42 = RequirementDSLFactory.eINSTANCE.createObject();
		object42.object.addAll("Barnie")
		actProp42.object = object42
		val prop42 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop42.relativ = "relative"
		prop42.property.addAll("mum")
		actProp42.property = prop42;
		actorProp42.property.addAll(actProp42)
		sent42.properties = actorProp42
		val pred42 = RequirementDSLFactory.eINSTANCE.createPredicate();
		val obj42 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre42 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre42.article = "the";
		obj42.article = pre42;
		obj42.object.addAll("tea");
		pred42.object = obj42;
		sent42.predicate = pred42
		sw.casePropertySentence(sent42);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry("<Barnie's mum> tea",
			resElement4);
		sw.lookup.clear()

		// TEST 5 Auxiliar Predicate
		val sent51 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp51 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp51 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object51 = RequirementDSLFactory.eINSTANCE.createObject();
		object51.object.addAll("Barnie")
		actProp51.object = object51
		val prop51 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop51.relativ = "relative"
		prop51.property.addAll("mum")
		actProp51.property = prop51;
		actorProp51.property.addAll(actProp51)
		sent51.properties = actorProp51
		sent51.modality = "must"
//		sent51.negation = true;
		sent51.auxiliarVerb = "be"
		val pred51 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred51.predicates.addAll("trinking", "tea");
		sent51.predicate = pred51
		sw.casePropertySentence(sent51);
		val resElement5 = new SemanticTextElement("<Barnie's mum> be trinking tea", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's mum> be trinking tea", resElement5);
		val sent52 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp52 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp52 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object52 = RequirementDSLFactory.eINSTANCE.createObject();
		object52.object.addAll("Barnie")
		actProp52.object = object52
		val prop52 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop52.relativ = "relative"
		prop52.property.addAll("mum")
		actProp52.property = prop52;
		actorProp52.property.addAll(actProp52)
		sent52.properties = actorProp52
		sent52.modality = "must"
//		sent52.negation = true;
		sent52.auxiliarVerb = "be"
		val pred52 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred52.predicates.addAll("trinking", "tea");
		sent52.predicate = pred52
		sw.casePropertySentence(sent52);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's mum> be trinking tea", resElement5);
		sw.lookup.clear()

		// TEST 6 - NEGATION
		val sent61 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp61 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp61 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object61 = RequirementDSLFactory.eINSTANCE.createObject();
		object61.object.addAll("Barnie")
		actProp61.object = object61
		val prop61 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop61.relativ = "relative"
		prop61.property.addAll("mum")
		actProp61.property = prop61;
		actorProp61.property.addAll(actProp61)
		sent61.properties = actorProp61
		sent61.modality = "must"
		sent61.negation = true;
		sent61.auxiliarVerb = "be"
		val pred61 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred61.predicates.addAll("trinking", "tea");
		sent61.predicate = pred61
		sw.casePropertySentence(sent61);
		val resElement6 = new SemanticTextElement("<Barnie's mum> be trinking tea", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's mum> be trinking tea", resElement6);
		val sent62 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp62 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val objProp62 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object62 = RequirementDSLFactory.eINSTANCE.createObject();
		object62.object.addAll("Barnie")
		objProp62.object = object62
		val prop62 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop62.relativ = "relative"
		prop62.property.addAll("mum")
		objProp62.property = prop62;
		actorProp62.property.addAll(objProp62)
		sent62.properties = actorProp62
		sent62.modality = "must"
		sent62.negation = true;
		sent62.auxiliarVerb = "be"
		val pred62 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred62.predicates.addAll("trinking", "tea");
		sent62.predicate = pred62
		sw.casePropertySentence(sent62);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's mum> be trinking tea", resElement6);
		sw.lookup.clear()

		// TEST 7 Auxiliar PredicateObject
		val sent71 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp71 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp71 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object71 = RequirementDSLFactory.eINSTANCE.createObject();
		object71.object.addAll("Barnie")
		actProp71.object = object71
		val prop71 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop71.relativ = "relative"
		prop71.property.addAll("mum")
		actProp71.property = prop71;
		actorProp71.property.addAll(actProp71)
		sent71.properties = actorProp71
		sent71.modality = "must"
//		sent71.negation = true;
		sent71.auxiliarVerb = "be"
		val pred71 = RequirementDSLFactory.eINSTANCE.createPredicate
		val obj71 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre71 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre71.article = "the";
		obj71.article = pre71;
		obj71.object.addAll("tea");
		pred71.object = obj71;
		sent71.predicate = pred71
		sw.casePropertySentence(sent71);
		val resElement7 = new SemanticTextElement("<Barnie's mum> be tea", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's mum> be tea", resElement7);
		val sent72 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp72 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp72 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object72 = RequirementDSLFactory.eINSTANCE.createObject();
		object72.object.addAll("Barnie")
		actProp72.object = object72
		val prop72 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop72.relativ = "relative"
		prop72.property.addAll("mum")
		actProp72.property = prop72;
		actorProp72.property.addAll(actProp72)
		sent72.properties = actorProp72
		sent72.modality = "must"
//		sent72.negation = true;
		sent72.auxiliarVerb = "be"
		val pred72 = RequirementDSLFactory.eINSTANCE.createPredicate();
		val obj72 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre72 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre72.article = "the";
		obj72.article = pre72;
		obj72.object.addAll("tea");
		pred72.object = obj72;
		sent72.predicate = pred72
		sw.casePropertySentence(sent72);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's mum> be tea", resElement7);
		sw.lookup.clear()

		// TEST 8 AUXNEG	 PREDICATE
		// actors=Actors property=Property rela=Relation? auxNeg=AuxNeg (predObj=PredOrObject | constraints=Constraints) ending=SentenceEnding?
		val sent81 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp81 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp81 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object81 = RequirementDSLFactory.eINSTANCE.createObject();
		object81.object.addAll("Barnie")
		actProp81.object = object81
		val prop81 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop81.relativ = "relative"
		prop81.property.addAll("mum")
		actProp81.property = prop81;
		actorProp81.property.addAll(actProp81)
		sent81.properties = actorProp81
		val aux81 = RequirementDSLFactory.eINSTANCE.createAuxNeg();
		aux81.auxiliarVerbNeg = "doesn\'t"
		sent81.auxNeg = aux81
		val pred81 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred81.predicates.addAll("trinking", "tea");
		sent81.predicate = pred81
		sw.casePropertySentence(sent81);
		val resElement8 = new SemanticTextElement("<Barnie's mum> does trinking tea", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's mum> does trinking tea", resElement8);
		val sent82 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp82 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp82 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object82 = RequirementDSLFactory.eINSTANCE.createObject();
		object82.object.addAll("Barnie")
		actProp82.object = object82
		val prop82 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop82.relativ = "relative"
		prop82.property.addAll("mum")
		actProp82.property = prop82;
		actorProp82.property.addAll(actProp82)
		sent82.properties = actorProp82
		val aux82 = RequirementDSLFactory.eINSTANCE.createAuxNeg();
		aux82.auxiliarVerbNeg = "doesn\'t"
		sent82.auxNeg = aux82
		val pred82 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred82.predicates.addAll("trinking", "tea");
		sent82.predicate = pred82
		sw.casePropertySentence(sent82);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's mum> does trinking tea", resElement8);
		sw.lookup.clear()

		// TEST 9 AUXNEG	 PREDOBJ
		val sent91 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp91 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val objProp91 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object91 = RequirementDSLFactory.eINSTANCE.createObject();
		object91.object.addAll("Barnie")
		objProp91.object = object91
		val prop91 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop91.relativ = "relative"
		prop91.property.addAll("mum")
		objProp91.property = prop91;
		actorProp91.property.addAll(objProp91)
		sent91.properties = actorProp91
		val aux91 = RequirementDSLFactory.eINSTANCE.createAuxNeg();
		aux91.auxiliarVerbNeg = "don\'t"
		sent91.auxNeg = aux91
		val pred91 = RequirementDSLFactory.eINSTANCE.createPredicate();
		val obj91 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre91 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre91.article = "the";
		obj91.article = pre91;
		obj91.object.addAll("tea");
		pred91.object = obj91;
		sent91.predicate = pred91
		sw.casePropertySentence(sent91);
		val resElement9 = new SemanticTextElement("<Barnie's mum> do tea", RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's mum> do tea", resElement9);
		val sent92 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp92 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp92 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object92 = RequirementDSLFactory.eINSTANCE.createObject();
		object92.object.addAll("Barnie")
		actProp92.object = object92
		val prop92 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop92.relativ = "relative"
		prop92.property.addAll("mum")
		actProp92.property = prop92;
		actorProp92.property.addAll(actProp92)
		sent92.properties = actorProp92
		val aux92 = RequirementDSLFactory.eINSTANCE.createAuxNeg();
		aux92.auxiliarVerbNeg = "don\'t"
		sent92.auxNeg = aux92
		val pred92 = RequirementDSLFactory.eINSTANCE.createPredicate();
		val obj92 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre92 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre92.article = "the";
		obj92.article = pre92;
		obj92.object.addAll("tea");
		pred92.object = obj92;
		sent92.predicate = pred92
		sw.casePropertySentence(sent92);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's mum> do tea", resElement9);
		sw.lookup.clear()

		// TEST 11 - Sentence Ending
		val sent111 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp111 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp111 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object111 = RequirementDSLFactory.eINSTANCE.createObject();
		object111.object.addAll("Barnie")
		actProp111.object = object111
		val prop111 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop111.relativ = "relative"
		prop111.property.addAll("mum")
		actProp111.property = prop111;
		actorProp111.property.addAll(actProp111)
		sent111.properties = actorProp111
		val pred111 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred111.predicates.addAll("trinking", "tea");
		sent111.predicate = pred111
		val end111 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
//		rela=Relation const+=Constraints+
		val rel111 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel111.relDel = "on"
		val relObj111 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj111 = RequirementDSLFactory.eINSTANCE.createObject();
		obj111.object.addAll("house", "boot")
		relObj111.object.addAll(obj111);
		rel111.relElements = relObj111
		end111.rela = rel111
		sent111.ending = end111
		sw.casePropertySentence(sent111);
		val resElement11 = new SemanticTextElement("<Barnie's mum> trinking tea on house boot",
			RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's mum> trinking tea on house boot", resElement11);
		val sent112 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp112 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp112 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object112 = RequirementDSLFactory.eINSTANCE.createObject();
		object112.object.addAll("Barnie")
		actProp112.object = object112
		val prop112 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop112.relativ = "relative"
		prop112.property.addAll("mum")
		actProp112.property = prop112;
		actorProp112.property.addAll(actProp112)
		sent112.properties = actorProp112
		val pred112 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred112.predicates.addAll("trinking", "tea");
		sent112.predicate = pred112
		val end112 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
//		rela=Relation const+=Constraints+
		val rel112 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel112.relDel = "on"
		val relObj112 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj112 = RequirementDSLFactory.eINSTANCE.createObject();
		obj112.object.addAll("house", "boot")
		relObj112.object.addAll(obj112);
		rel112.relElements = relObj112
		end112.rela = rel112
		sent112.ending = end112
		sw.casePropertySentence(sent112);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's mum> trinking tea on house boot", resElement11);
		sw.lookup.clear()

		// TEST 12 AUXNEG	 Sentence Ending
		// actors=Actors property=Property rela=Relation? auxNeg=AuxNeg (predObj=PredOrObject | constraints=Constraints) ending=SentenceEnding?
		val sent121 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp121 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp121 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object121 = RequirementDSLFactory.eINSTANCE.createObject();
		object121.object.addAll("Barnie")
		actProp121.object = object121
		val prop121 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop121.relativ = "relative"
		prop121.property.addAll("mum")
		actProp121.property = prop121;
		actorProp121.property.addAll(actProp121)
		sent121.properties = actorProp121
		val aux121 = RequirementDSLFactory.eINSTANCE.createAuxNeg();
		aux121.auxiliarVerbNeg = "doesn\'t"
		sent121.auxNeg = aux121
		val pred121 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred121.predicates.addAll("trinking", "tea");
		sent121.predicate = pred121
		val end121 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
//		rela=Relation const+=Constraints+
		val rel121 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel121.relDel = "on"
		val relObj121 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj121 = RequirementDSLFactory.eINSTANCE.createObject();
		obj121.object.addAll("house", "boot")
		relObj121.object.addAll(obj121);
		rel121.relElements = relObj121
		end121.rela = rel121
		sent121.ending = end121
		sw.casePropertySentence(sent121);
		val resElement12 = new SemanticTextElement("<Barnie's mum> does trinking tea on house boot",
			RequirementType.RELATION);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's mum> does trinking tea on house boot", resElement12);
		val sent122 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
		val actorProp122 = RequirementDSLFactory.eINSTANCE.createActorProperties();
		val actProp122 = RequirementDSLFactory.eINSTANCE.createActorProperty();
		val object122 = RequirementDSLFactory.eINSTANCE.createObject();
		object122.object.addAll("Barnie")
		actProp122.object = object122
		val prop122 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop122.relativ = "relative"
		prop122.property.addAll("mum")
		actProp122.property = prop122;
		actorProp122.property.addAll(actProp122)
		sent122.properties = actorProp122
		val aux122 = RequirementDSLFactory.eINSTANCE.createAuxNeg();
		aux122.auxiliarVerbNeg = "doesn\'t"
		sent122.auxNeg = aux122
		val pred122 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred122.predicates.addAll("trinking", "tea");
		sent122.predicate = pred122
		val end122 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
//		rela=Relation const+=Constraints+
		val rel122 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel122.relDel = "on"
		val relObj122 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj122 = RequirementDSLFactory.eINSTANCE.createObject();
		obj122.object.addAll("house", "boot")
		relObj122.object.addAll(obj122);
		rel122.relElements = relObj122
		end122.rela = rel122
		sent122.ending = end122
		sw.casePropertySentence(sent122);
		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
		softly.assertThat(sw.lookup).^as("Analysis of Property Element in Lookup:").containsEntry(
			"<Barnie's mum> does trinking tea on house boot", resElement12);
		sw.lookup.clear()

		softly.assertAll();
	}

//	/**
//	 * Test to verify if mutiple actors are invidually mapped for the predicates
//	 */
// 	We dont Split actors in SemanticTextSwitch but before on the AST Flatten - so these wont work!
//	@Test
//	def package void testMutipleActorsForPredicate() {
//		val softly = new SoftAssertions();
//		// TEST 1 Predicate
//		val sent11 = RequirementDSLFactory.eINSTANCE.createPredicateSentence();
//		val actors11 = RequirementDSLFactory.eINSTANCE.createActors();
//		val actor11 = RequirementDSLFactory.eINSTANCE.createActor();
//		actor11.actor = "Barnie"
//		val actor12 = RequirementDSLFactory.eINSTANCE.createActor();
//		actor12.actor = "Damian"
//		actors11.actors.addAll(actor11, actor12)
//		sent11.actors = actors11
//		val pred11 = RequirementDSLFactory.eINSTANCE.createPredicate();
//		pred11.predicates.addAll("trinking", "tea");
//		sent11.predicate = pred11
//		sw.casePredicateSentence(sent11);
//		val resElement11 = new SemanticTextElement("<Barnie> trinking tea", RequirementType.RELATION);
//		val resElement12 = new SemanticTextElement("<Damian> trinking tea", RequirementType.RELATION);
//		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(2);
//		softly.assertThat(sw.lookup).^as("Analysis of splitting actors in prediates:").containsEntry(
//			"<Barnie> trinking tea", resElement11);
//		softly.assertThat(sw.lookup).^as("Analysis of splitting actors in prediates:").containsEntry(
//			"<Damian> trinking tea", resElement12);
//		sw.lookup.clear()
//
//		val existClause21 = RequirementDSLFactory.eINSTANCE.createExistence();
//		val relClause21 = RequirementDSLFactory.eINSTANCE.createRelativeClause();
//		val sent21 = RequirementDSLFactory.eINSTANCE.createRelativeSentence();
//		// 'which' | 'who' | 'that'
//		sent21.pronoun = "which";
//		sent21.modelity = "must";
//		sent21.negation = false
//		val pred21 = RequirementDSLFactory.eINSTANCE.createPredicate();
//		// predicates+=WORD+ | predicates+=STRING | predicates+=WORD+ object=PredicateObject
//		pred21.predicates.addAll("is", "working");
//		sent21.predicate = pred21
//		relClause21.sentence = sent21;
//		existClause21.relativeClause = relClause21;
//		val actors21 = RequirementDSLFactory.eINSTANCE.createActors();
//		val actor21 = RequirementDSLFactory.eINSTANCE.createActor();
//		actor21.actor = "David";
//		val actor22 = RequirementDSLFactory.eINSTANCE.createActor();
//		actor22.actor = "Brain";
//		actors21.actors.addAll(actor21, actor22)
//		existClause21.actors = actors21;
//		sw.caseRelativeSentence(sent21);
//		val resElement21 = new SemanticTextElement("<David> is working", RequirementType.RELATION);
//		val resElement22 = new SemanticTextElement("<Brain> is working", RequirementType.RELATION);
//		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
//		softly.assertThat(sw.lookup).^as("Analysis of splitting actors in prediates:").containsEntry("<David> is working",
//			resElement21);
//		softly.assertThat(sw.lookup).^as("Analysis of splitting actors in prediates:").containsEntry("<Braiin> is working",
//			resElement22);
//			sw.lookup.clear()
//			
//		val sent31 = RequirementDSLFactory.eINSTANCE.createPropertySentence();
//		val actorProp31 = RequirementDSLFactory.eINSTANCE.createActorProperties();
//		val objProp31 = RequirementDSLFactory.eINSTANCE.createObjectProperty();
//		val object31 = RequirementDSLFactory.eINSTANCE.createObject();
//		object31.object.addAll("Barnie")
//		objProp31.object = object31
//		val prop31 = RequirementDSLFactory.eINSTANCE.createProperty();
////		prop31.relativ = "relative"
//		prop31.property.addAll("mum")
//		objProp31.property = prop31;
//		val objProp32 = RequirementDSLFactory.eINSTANCE.createObjectProperty();
//		val object32 = RequirementDSLFactory.eINSTANCE.createObject();
//		object32.object.addAll("David")
//		objProp32.object = object32
//		val prop32 = RequirementDSLFactory.eINSTANCE.createProperty();
////		prop32.relativ = "relative"
//		prop32.property.addAll("mum")
//		objProp32.property = prop32
//		actorProp31.property.addAll(objProp31,objProp32)
//		sent31.properties = actorProp31
//		val pred31 = RequirementDSLFactory.eINSTANCE.createPredicate();
//		pred31.predicates.addAll("trinking", "tea");
//		sent31.predicate = pred31
//		sw.casePropertySentence(sent31);
//		val resElement31 = new SemanticTextElement("<Barnie's mum> trinking tea", RequirementType.RELATION);
//		val resElement32 = new SemanticTextElement("<David's mum> trinking tea", RequirementType.RELATION);
//		softly.assertThat(sw.lookup).^as("Analysis of Lookup Size:").hasSize(1);
//		softly.assertThat(sw.lookup).^as("Analysis of splitting actors in prediates:").containsEntry(
//			"<Barnie's mum> trinking tea", resElement31);
//		softly.assertThat(sw.lookup).^as("Analysis of splitting actors in prediates:").containsEntry(
//			"<David's mum> trinking tea", resElement32);
//		sw.lookup.clear()
//		
//		softly.assertAll()
//	}

	/**
	 * Test to verify if mutiple actors are invidually mapped for the constraints
	 */
	@Test
	def package void testMutipleActorsForConstraints() {
		// Is on bla bla and next to bla bla
	}

	/**
	 * Test to verify if multiple COnstraints are independently considered for the Mapping GUI
	 */
	@Test
	def package void testMutipleConstraints() {
	}
}
