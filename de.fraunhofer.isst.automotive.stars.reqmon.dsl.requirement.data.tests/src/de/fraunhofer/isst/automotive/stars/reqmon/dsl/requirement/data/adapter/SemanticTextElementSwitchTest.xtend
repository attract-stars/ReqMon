package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLFactory
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.*
import static org.junit.jupiter.api.Assertions.fail

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
		fail("Not yet implemented")
	}

	@Test def package void testCasePredicateSentencePredicateSentence() {
//		begin=SentenceBegin? actors=Actors  auxNeg=AuxNeg auxiliarVerb=AuxiliaryVerb? preds=Preds?  ending=SentenceEnding? |
//		begin=SentenceBegin? actors=Actors  preds=Preds  ending=SentenceEnding? 
		fail("Not yet implemented")
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
