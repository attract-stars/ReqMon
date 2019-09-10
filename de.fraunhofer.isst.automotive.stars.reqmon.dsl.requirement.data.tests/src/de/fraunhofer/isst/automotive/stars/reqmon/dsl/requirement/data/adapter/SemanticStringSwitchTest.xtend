/** 
 */
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter

import com.google.inject.Inject
import com.google.inject.Injector
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLFactory
import de.fraunhofer.isst.stars.tests.RequirementDSLInjectorProvider
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.^extension.ExtendWith

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail


/** 
 * @author mmauritz
 */
@ExtendWith(typeof(InjectionExtension)) 
@InjectWith(typeof(RequirementDSLInjectorProvider)) 
package class SemanticStringSwitchTest {
	
	@Inject Injector injector

	SemanticStringSwitch sw
	/** 
	 * @throws java.lang.Exception
	 */
	@BeforeEach def package void setUp() throws Exception {
		sw = new SemanticStringSwitch()// SUT
	}

	/** 
	 * @throws java.lang.Exception
	 */
	@AfterEach def package void tearDown() throws Exception {
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseValue(de.fraunhofer.isst.stars.requirementDSL.Value)}.
	 */
	@Test def package void testCaseValueValue() {
		val value1 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value1.unit = "sec"
		value1.value = 10
		assertThat(sw.caseValue(value1)).^as("Matching String Representation of Value:").contains("10","sec");
//		assertThat("Output does not match the Input for Value.",sw.caseValue(value1),allOf(containsStringIgnoringCase("sec"),containsStringIgnoringCase("10")));
		val value2 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value2.unit = "m"
		value2.value = 2.2f
		assertThat(sw.caseValue(value2)).^as("Matching String Representation of Value:").contains("2.2","m");
//		assertThat("Output does not match the Input for Value.",sw.caseValue(value2),allOf(containsStringIgnoringCase("sec"),containsStringIgnoringCase("10")));
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseIntValue(de.fraunhofer.isst.stars.requirementDSL.IntValue)}.
	 */
	@Test def package void testCaseIntValueIntValue() {
		val value1 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value1.unit = "sec"
		value1.value = 10
		assertThat(sw.caseValue(value1)).^as("Matching String Representation of Value:").contains("10","sec");
//		assertThat("Output does not match the Input for Value.",sw.caseValue(value1),allOf(containsStringIgnoringCase("sec"),containsStringIgnoringCase("10")));
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseFloatValue(de.fraunhofer.isst.stars.requirementDSL.FloatValue)}.
	 */
	@Test def package void testCaseFloatValueFloatValue() {
		val value2 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value2.unit = "m"
		value2.value = 2.2f
		assertThat(sw.caseValue(value2)).^as("Matching String Representation of Value:").contains("2.2","m");
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseSingleValueConstraints(de.fraunhofer.isst.stars.requirementDSL.SingleValueConstraints)}.
	 */
	@Test def package void testCaseSingleValueConstraintsSingleValueConstraints() {
		val constr1 = RequirementDSLFactory.eINSTANCE.createSingleValueConstraints();
		val value1 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value1.unit = "sec";
		value1.value = 5; 
		constr1.value = value1;
		assertThat(sw.caseSingleValueConstraints(constr1)).^as("Matching String Representation of Single Value Constraint:").contains("5","sec");
		val constr2 = RequirementDSLFactory.eINSTANCE.createSingleValueConstraints();
		val value2 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value2.unit = "km";
		value2.value = 44.23f; 
		constr2.value = value2;
		assertThat(sw.caseSingleValueConstraints(constr2)).^as("Matching String Representation of Single Value Constraint:").contains("44.23","km");
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseIntervallConstraints(de.fraunhofer.isst.stars.requirementDSL.IntervallConstraints)}.
	 */
	@Test def package void testCaseIntervallConstraintsIntervallConstraints() {
		val constr1 = RequirementDSLFactory.eINSTANCE.createIntervallConstraints();
		val value11 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value11.unit = "sec";
		value11.value = 5;
		val value12 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value12.unit = "sec";
		value12.value = 8;  
		constr1.lower = value11;
		constr1.higher = value12;
		assertThat(sw.caseIntervallConstraints(constr1)).^as("Matching String Representation of Interval Constraint:").contains("5","sec","8","sec");
		
		val constr2 = RequirementDSLFactory.eINSTANCE.createIntervallConstraints();
		val value21 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value21.unit = "m";
		value21.value = 5.5f;
		val value22 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value22.unit = "m";
		value22.value = 8.2f;  
		constr2.lower = value21;
		constr2.higher = value22;
		assertThat(sw.caseIntervallConstraints(constr2)).^as("Matching String Representation of Interval Constraint:").contains("5.5","m","8.2","m");
		
		val constr3 = RequirementDSLFactory.eINSTANCE.createIntervallConstraints();
		val value31 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value31.unit = "m";
		value31.value = 2;
		val value32 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value32.unit = "sec";
		value32.value = 135;  
		constr3.lower = value31;
		constr3.higher = value32;
		assertThat(sw.caseIntervallConstraints(constr3)).^as("Matching String Representation of Interval Constraint:").contains("2","m","135","sec");
		
		val constr4 = RequirementDSLFactory.eINSTANCE.createIntervallConstraints();
		val value41 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value41.unit = "km";
		value41.value = 2;
		val value42 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value42.unit = "sec";
		value42.value = 14.23f;  
		constr4.lower = value41;
		constr4.higher = value42;
		assertThat(sw.caseIntervallConstraints(constr4)).^as("Matching String Representation of Interval Constraint:").contains("2","km","14.23","sec");
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseConstraintOrdinators(de.fraunhofer.isst.stars.requirementDSL.ConstraintOrdinators)}.
	 */
	@Test def package void testCaseConstraintOrdinatorsConstraintOrdinators() {
		val ord1 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
		//TODO DEFINE CORRECT TERMS
		ord1.adverbial = "aff";
		ord1.comperator = "aff";
		ord1.stuffing = "Awas";
		assertThat(sw.caseConstraintOrdinators(ord1)).^as("Matching String Representation of Constraint Ordinators:").isEqualToIgnoringWhitespace("WAH");
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseUnitConstraints(de.fraunhofer.isst.stars.requirementDSL.UnitConstraints)}.
	 */
	@Test def package void testCaseUnitConstraintsUnitConstraints() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#casePredicateObject(de.fraunhofer.isst.stars.requirementDSL.PredicateObject)}.
	 */
	@Test def package void testCasePredicateObjectPredicateObject() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#casePredicate(de.fraunhofer.isst.stars.requirementDSL.Predicate)}.
	 */
	@Test def package void testCasePredicatePredicate() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseAuxNeg(de.fraunhofer.isst.stars.requirementDSL.AuxNeg)}.
	 */
	@Test def package void testCaseAuxNegAuxNeg() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseSentenceEnding(de.fraunhofer.isst.stars.requirementDSL.SentenceEnding)}.
	 */
	@Test def package void testCaseSentenceEndingSentenceEnding() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseConstraint(de.fraunhofer.isst.stars.requirementDSL.Constraint)}.
	 */
	@Test def package void testCaseConstraintConstraint() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseObjectConstraint(de.fraunhofer.isst.stars.requirementDSL.ObjectConstraint)}.
	 */
	@Test def package void testCaseObjectConstraintObjectConstraint() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseSetConstraint(de.fraunhofer.isst.stars.requirementDSL.SetConstraint)}.
	 */
	@Test def package void testCaseSetConstraintSetConstraint() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseValueSet(de.fraunhofer.isst.stars.requirementDSL.ValueSet)}.
	 */
	@Test def package void testCaseValueSetValueSet() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseActors(de.fraunhofer.isst.stars.requirementDSL.Actors)}.
	 */
	@Test def package void testCaseActorsActors() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#casePreds(de.fraunhofer.isst.stars.requirementDSL.Preds)}.
	 */
	@Test def package void testCasePredsPreds() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseRelation(de.fraunhofer.isst.stars.requirementDSL.Relation)}.
	 */
	@Test def package void testCaseRelationRelation() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseRelObjects(de.fraunhofer.isst.stars.requirementDSL.RelObjects)}.
	 */
	@Test def package void testCaseRelObjectsRelObjects() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseObject(de.fraunhofer.isst.stars.requirementDSL.Object)}.
	 */
	@Test def package void testCaseObjectObject() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseProperty(de.fraunhofer.isst.stars.requirementDSL.Property)}.
	 */
	@Test def package void testCasePropertyProperty() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#casePredOrObject(de.fraunhofer.isst.stars.requirementDSL.PredOrObject)}.
	 */
	@Test def package void testCasePredOrObjectPredOrObject() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseConstraints(de.fraunhofer.isst.stars.requirementDSL.Constraints)}.
	 */
	@Test def package void testCaseConstraintsConstraints() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseSentenceBegin(de.fraunhofer.isst.stars.requirementDSL.SentenceBegin)}.
	 */
	@Test def package void testCaseSentenceBeginSentenceBegin() {
		fail("Not yet implemented")
	}
}
