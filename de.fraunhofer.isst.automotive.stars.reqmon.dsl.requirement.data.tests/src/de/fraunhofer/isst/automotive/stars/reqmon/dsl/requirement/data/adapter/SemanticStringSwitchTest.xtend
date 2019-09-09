/** 
 */
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter

import static org.junit.jupiter.api.Assertions.fail
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.^extension.ExtendWith
import com.google.inject.Inject
import com.google.inject.Injector
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLFactory
import de.fraunhofer.isst.stars.tests.RequirementDSLInjectorProvider
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import de.fraunhofer.isst.stars.requirementDSL.IntValue
import de.fraunhofer.isst.stars.requirementDSL.FloatValue

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
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseValue(de.fraunhofer.isst.stars.requirementDSL.Value)}.
	 */
	@Test def package void testCaseValueValue() {
		val value1 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value1.unit = "sec"
		value1.value = 10
		val value2 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value2.unit = "sec"
		value2.value = 2.2
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseIntValue(de.fraunhofer.isst.stars.requirementDSL.IntValue)}.
	 */
	@Test def package void testCaseIntValueIntValue() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseFloatValue(de.fraunhofer.isst.stars.requirementDSL.FloatValue)}.
	 */
	@Test def package void testCaseFloatValueFloatValue() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseSingleValueConstraints(de.fraunhofer.isst.stars.requirementDSL.SingleValueConstraints)}.
	 */
	@Test def package void testCaseSingleValueConstraintsSingleValueConstraints() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseIntervallConstraints(de.fraunhofer.isst.stars.requirementDSL.IntervallConstraints)}.
	 */
	@Test def package void testCaseIntervallConstraintsIntervallConstraints() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseConstraintOrdinators(de.fraunhofer.isst.stars.requirementDSL.ConstraintOrdinators)}.
	 */
	@Test def package void testCaseConstraintOrdinatorsConstraintOrdinators() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseUnitConstraints(de.fraunhofer.isst.stars.requirementDSL.UnitConstraints)}.
	 */
	@Test def package void testCaseUnitConstraintsUnitConstraints() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#casePredicateObject(de.fraunhofer.isst.stars.requirementDSL.PredicateObject)}.
	 */
	@Test def package void testCasePredicateObjectPredicateObject() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#casePredicate(de.fraunhofer.isst.stars.requirementDSL.Predicate)}.
	 */
	@Test def package void testCasePredicatePredicate() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseAuxNeg(de.fraunhofer.isst.stars.requirementDSL.AuxNeg)}.
	 */
	@Test def package void testCaseAuxNegAuxNeg() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseSentenceEnding(de.fraunhofer.isst.stars.requirementDSL.SentenceEnding)}.
	 */
	@Test def package void testCaseSentenceEndingSentenceEnding() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseConstraint(de.fraunhofer.isst.stars.requirementDSL.Constraint)}.
	 */
	@Test def package void testCaseConstraintConstraint() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseObjectConstraint(de.fraunhofer.isst.stars.requirementDSL.ObjectConstraint)}.
	 */
	@Test def package void testCaseObjectConstraintObjectConstraint() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseSetConstraint(de.fraunhofer.isst.stars.requirementDSL.SetConstraint)}.
	 */
	@Test def package void testCaseSetConstraintSetConstraint() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseValueSet(de.fraunhofer.isst.stars.requirementDSL.ValueSet)}.
	 */
	@Test def package void testCaseValueSetValueSet() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseActors(de.fraunhofer.isst.stars.requirementDSL.Actors)}.
	 */
	@Test def package void testCaseActorsActors() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#casePreds(de.fraunhofer.isst.stars.requirementDSL.Preds)}.
	 */
	@Test def package void testCasePredsPreds() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseRelation(de.fraunhofer.isst.stars.requirementDSL.Relation)}.
	 */
	@Test def package void testCaseRelationRelation() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseRelObjects(de.fraunhofer.isst.stars.requirementDSL.RelObjects)}.
	 */
	@Test def package void testCaseRelObjectsRelObjects() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseObject(de.fraunhofer.isst.stars.requirementDSL.Object)}.
	 */
	@Test def package void testCaseObjectObject() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseProperty(de.fraunhofer.isst.stars.requirementDSL.Property)}.
	 */
	@Test def package void testCasePropertyProperty() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#casePredOrObject(de.fraunhofer.isst.stars.requirementDSL.PredOrObject)}.
	 */
	@Test def package void testCasePredOrObjectPredOrObject() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseConstraints(de.fraunhofer.isst.stars.requirementDSL.Constraints)}.
	 */
	@Test def package void testCaseConstraintsConstraints() {
		fail("Not yet implemented")
	}

	/** 
	 * Test method for {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticStringSwitch#caseSentenceBegin(de.fraunhofer.isst.stars.requirementDSL.SentenceBegin)}.
	 */
	@Test def package void testCaseSentenceBeginSentenceBegin() {
		fail("Not yet implemented")
	}
}
