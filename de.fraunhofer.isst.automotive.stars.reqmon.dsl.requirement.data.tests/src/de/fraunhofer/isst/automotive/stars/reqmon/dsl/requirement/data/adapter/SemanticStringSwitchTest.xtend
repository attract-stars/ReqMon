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
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter

import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLFactory
import de.fraunhofer.isst.stars.tests.RequirementDSLInjectorProvider
import org.assertj.core.api.SoftAssertions
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.^extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

import static org.assertj.core.api.Assertions.*

/** 
 * @author mmauritz
 */
@ExtendWith(typeof(InjectionExtension)) 
@InjectWith(typeof(RequirementDSLInjectorProvider)) 
package class SemanticStringSwitchTest {
	

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

		val value2 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value2.unit = "sec"
		value2.value = 20
		value2.object.add("delay");
		assertThat(sw.caseValue(value2)).^as("Matching String Representation of Value:").contains("20","sec","delay");
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseFloatValue(de.fraunhofer.isst.stars.requirementDSL.FloatValue)}.
	 */
	@Test def package void testCaseFloatValueFloatValue() {
		val value1 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value1.unit = "m"
		value1.value = 2.2f
		value1.object.addAll("street");
		assertThat(sw.caseValue(value1)).^as("Matching String Representation of Value:").contains("2.2","m");
		
		val value2 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value2.unit = "m"
		value2.value = 2.2f
		value2.object.addAll("street");
		assertThat(sw.caseValue(value2)).^as("Matching String Representation of Value:").contains("2.2","m", "street");
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseSingleValueConstraint(de.fraunhofer.isst.stars.requirementDSL.SingleValueConstraints)}.
	 */
	@Test def package void testcaseSingleValueConstraintSingleValueConstraints() {
		val constr1 = RequirementDSLFactory.eINSTANCE.createSingleValueConstraint();
		val value1 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value1.unit = "sec";
		value1.value = 5; 
		constr1.value = value1;
		assertThat(sw.caseSingleValueConstraint(constr1)).^as("Matching String Representation of Single Value Constraint:").contains("5","sec");
		val constr2 = RequirementDSLFactory.eINSTANCE.createSingleValueConstraint();
		val value2 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value2.unit = "km";
		value2.value = 44.23f; 
		constr2.value = value2;
		assertThat(sw.caseSingleValueConstraint(constr2)).^as("Matching String Representation of Single Value Constraint:").contains("44.23","km");
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseIntervallConstraint(de.fraunhofer.isst.stars.requirementDSL.IntervallConstraints)}.
	 */
	@Test def package void testcaseIntervallConstraintIntervallConstraints() {
		val constr1 = RequirementDSLFactory.eINSTANCE.createIntervallConstraint();
		val value11 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value11.unit = "sec";
		value11.value = 5;
		val value12 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value12.unit = "sec";
		value12.value = 8;  
		constr1.lower = value11;
		constr1.higher = value12;
		assertThat(sw.caseIntervallConstraint(constr1)).^as("Matching String Representation of Interval Constraint:").contains("5","sec","8","sec");
		
		val constr2 = RequirementDSLFactory.eINSTANCE.createIntervallConstraint();
		val value21 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value21.unit = "m";
		value21.value = 5.5f;
		val value22 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value22.unit = "m";
		value22.value = 8.2f;  
		constr2.lower = value21;
		constr2.higher = value22;
		assertThat(sw.caseIntervallConstraint(constr2)).^as("Matching String Representation of Interval Constraint:").contains("5.5","m","8.2","m");
		
		val constr3 = RequirementDSLFactory.eINSTANCE.createIntervallConstraint();
		val value31 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value31.unit = "m";
		value31.value = 2;
		val value32 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value32.unit = "sec";
		value32.value = 135;  
		constr3.lower = value31;
		constr3.higher = value32;
		assertThat(sw.caseIntervallConstraint(constr3)).^as("Matching String Representation of Interval Constraint:").contains("2","m","135","sec");
		
		val constr4 = RequirementDSLFactory.eINSTANCE.createIntervallConstraint();
		val value41 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value41.unit = "km";
		value41.value = 2;
		val value42 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value42.unit = "sec";
		value42.value = 14.23f;  
		constr4.lower = value41;
		constr4.higher = value42;
		assertThat(sw.caseIntervallConstraint(constr4)).^as("Matching String Representation of Interval Constraint:").contains("2","km","14.23","sec");
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseConstraintOrdinators(de.fraunhofer.isst.stars.requirementDSL.ConstraintOrdinators)}.
	 */
	@ParameterizedTest
	 @ValueSource(strings = #["higher", "less", "more" , "larger","smaller" , "as long as",
		"between","next" , "on" , "above" , "below" , "in" , "within" , "in front of" , 
		"in front to", "behind" , "out" , "under","equal" , "faster" , "slower" , "better" , "by" , "to"])
	def package void testCaseConstraintOrdinatorsConstraintOrdinators(String adverb) {
		val stuff = "with";
		val ord1 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
		ord1.adverbial = adverb;
		assertThat(sw.caseConstraintOrdinators(ord1)).^as("Matching String Representation of Constraint Ordinators:").contains(adverb);
		val ord2 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
		ord2.stuffing= stuff;
		ord2.adverbial = adverb;
		assertThat(sw.caseConstraintOrdinators(ord2)).^as("Matching String Representation of Constraint Ordinators:").contains(adverb,stuff);
		val ord3 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
		ord3.stuffing= stuff;
		ord3.adverbial = adverb;
		ord3.comperator = "than";
		assertThat(sw.caseConstraintOrdinators(ord3)).^as("Matching String Representation of Constraint Ordinators:").contains(adverb,stuff,"than");
		val ord4 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
		ord4.stuffing= stuff;
		ord4.adverbial = adverb;
		ord4.comperator = "'as";
		assertThat(sw.caseConstraintOrdinators(ord4)).^as("Matching String Representation of Constraint Ordinators:").contains(adverb,stuff,"as");
		val ord5 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
		ord5.stuffing= stuff;
		ord5.adverbial = adverb;
		ord5.comperator = "'of";
		assertThat(sw.caseConstraintOrdinators(ord5)).^as("Matching String Representation of Constraint Ordinators:").contains(adverb,stuff,"of");		
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseUnitConstraint(de.fraunhofer.isst.stars.requirementDSL.UnitConstraints)}.
	 */
	@Test def package void testCaseUnitConstraintUnitConstraint() {
		val con1 = RequirementDSLFactory.eINSTANCE.createSingleValueConstraint();
		val value1 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value1.unit = "sec";
		value1.value = 5; 
		con1.value = value1;
		assertThat(sw.caseUnitConstraint(con1)).^as("Matching String Representation of UnitConstraint:").contains("5","sec");
		val con2 = RequirementDSLFactory.eINSTANCE.createSingleValueConstraint();
		val value2 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value2.unit = "km";
		value2.value = 44.23f; 
		con2.value = value2;
		assertThat(sw.caseUnitConstraint(con2)).^as("Matching String Representation of UnitConstraint:").contains("44.23","km");
		val con3 = RequirementDSLFactory.eINSTANCE.createIntervallConstraint();
		val value3 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value3.unit = "sec";
		value3.value = 10; 
		con3.lower = value1;
		con3.higher = value3;
		assertThat(sw.caseUnitConstraint(con3)).^as("Matching String Representation of UnitConstraint:").contains("5","sec","10","sec");
		val con4 = RequirementDSLFactory.eINSTANCE.createIntervallConstraint();
		val value4 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value4.unit = "m";
		value4.value = 1.1f; 
		con4.lower = value2;
		con4.higher = value4;
		assertThat(sw.caseUnitConstraint(con4)).^as("Matching String Representation of UnitConstraint:").contains("44.23","km","1.1","m");
		val con5 = RequirementDSLFactory.eINSTANCE.createIntervallConstraint();
		con5.lower = value1;
		con5.higher = value2;
		assertThat(sw.caseUnitConstraint(con5)).^as("Matching String Representation of UnitConstraint:").contains("5","sec","44.23","km");
		val con6 = RequirementDSLFactory.eINSTANCE.createIntervallConstraint();
		con6.lower = value4;
		con6.higher = value3;
		assertThat(sw.caseUnitConstraint(con6)).^as("Matching String Representation of UnitConstraint:").contains("10","sec","1.1","m");
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#casePredicateObject(de.fraunhofer.isst.stars.requirementDSL.PredicateObject)}.
	 */
	@ParameterizedTest
	 @CsvSource(#[
	 "cars,all,",
	 "desk,every," ,
	 "football coach,each," ,
	 "sea,whole,",
	 "person,any,",
	 "ladders,several,",
	 "part,either,",
	 "world,,the",
	 "world,,The",
	 "manager,,a",
	 "man,,A",
	 "execution process,,an",
	 "execution,,An",
	 "picture,,that",
	"animal trainer,,this",
	"liver,,That",
	"team meeting,,This"])
	def package void testCasePredicateObjectPredicateObject(String objects, String quant, String art) {
		val pre1 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
		pre1.determiner = quant;
		pre1.article = art;
		val obj1 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		obj1.article = pre1;
		val String[] list = objects.split(" ");
		val EList<String> liste = new BasicEList<String>(list);
		obj1.object.clear();
		obj1.object.addAll(liste);
		val obj2 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		obj2.article = pre1;
		obj2.relativ = "relative";
		obj2.object.clear();
		obj2.object.addAll(liste);
		//Does only need to include the Object - not article etc
		assertThat(sw.casePredicateObject(obj1)).^as("Matching String Representation of PredicateObject:").contains(objects);
		assertThat(sw.casePredicateObject(obj2)).^as("Matching String Representation of PredicateObject:").contains(objects);
		
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#casePredicate(de.fraunhofer.isst.stars.requirementDSL.Predicate)}.
	 */
	@Test def package void testCasePredicatePredicate() {
		//predicates+=WORD+ | predicates+=STRING | predicates+=WORD+ object=PredicateObject
		val pred1 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred1.predicates.addAll("tree","house");
		assertThat(sw.casePredicate(pred1)).^as("Matching String Representation of Predicate:").contains("tree","house");
		
		val pred2 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred2.predicates.addAll("tree","house");
		val obj2 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre2 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre2.determiner = quant;
		pre2.article = "the";
		obj2.article = pre2;
		obj2.object.addAll("sky","weekend");
		pred2.object = obj2;
		assertThat(sw.casePredicate(pred2)).^as("Matching String Representation of Predicate:").contains("tree","house","sky","weekend");
		
		val pred3 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred3.predicates.addAll("tree","house");
		val obj3 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre3 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre3.determiner = quant;
		pre3.article = "the";
		obj3.article = pre3;
		obj3.relativ = "relative";
		obj3.object.addAll("sea","weekends");
		pred3.object = obj3;
		assertThat(sw.casePredicate(pred3)).^as("Matching String Representation of Predicate:").contains("tree","house","sea","weekends");
		
		val pred4 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred4.predicates.addAll("tree","house");
		assertThat(sw.casePredicate(pred4)).^as("Matching String Representation of Preds:").contains("tree","house");
		
		val pred5 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred5.predicates.addAll("tree","house");
		val obj5 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre5 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre5.determiner = quant;
		pre5.article = "the";
		obj5.article = pre5;
		obj5.object.addAll("sky","weekend");
		pred5.object = obj5;
		assertThat(sw.casePredicate(pred5)).^as("Matching String Representation of Preds:").contains("tree","house","sky","weekend");
		
		val pred6 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred6.predicates.addAll("tree","house");
		val obj6 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre6 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre6.determiner = quant;
		pre6.article = "the";
		obj6.article = pre6;
		obj6.relativ = "relative";
		obj6.object.addAll("sea","weekends");
		pred6.object = obj6;
		assertThat(sw.casePredicate(pred6)).^as("Matching String Representation of Preds:").contains("tree","house","sea","weekends");
		
		val pred7 = RequirementDSLFactory.eINSTANCE.createPredicate();
		val obj7 = RequirementDSLFactory.eINSTANCE.createPredicateObject();
		val pre7 = RequirementDSLFactory.eINSTANCE.createPreNominative();
//		article=PreNominative relativ='relative'? (object+=WORD+ | object+=STRING) 
//		pre7.determiner = quant;
		pre7.article = "the";
		obj7.article = pre7;
		obj7.relativ = "relative";
		obj7.object.addAll("bear","hug");
		pred7.object = obj7;
		assertThat(sw.casePredicate(pred7)).^as("Matching String Representation of Preds:").contains("bear","hug");
		
		val pred8 = RequirementDSLFactory.eINSTANCE.createPredicate;
		pred8.predicates.addAll("cook", "butter");
		assertThat(sw.casePredicate(pred8)).^as("Matching String Representation of PredOrObject:").contains("cook","butter");
		
		val pred9 = RequirementDSLFactory.eINSTANCE.createPredicate();
		pred9.predicates.addAll("cook", "butter");
		val obj9 = RequirementDSLFactory.eINSTANCE.createPredicateObject;
		obj9.object.addAll("honey","mustard");
		pred9.object = obj9;
		assertThat(sw.casePredicate(pred9)).^as("Matching String Representation of PredOrObject:").contains("cook","butter","honey","mustard");
		
		val pred10 = RequirementDSLFactory.eINSTANCE.createPredicate();
		val obj10 = RequirementDSLFactory.eINSTANCE.createPredicateObject;
		obj10.object.addAll("honey","mustard");
		pred10.object = obj10;
		assertThat(sw.casePredicate(pred10)).^as("Matching String Representation of PredOrObject:").contains("honey","mustard");
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseAuxNeg(de.fraunhofer.isst.stars.requirementDSL.AuxNeg)}.
	 */
	@ParameterizedTest
	 @CsvSource(#[
	 "is,",
	 "is,not",
	 "are,",
	 "are,not",
	 "be,",
	 "be,not",
	 "been,",
	 "been,not",
	 "has,",
	 "has,not",
	 "have,",
	 "have,not",
	 "do,",
	 "do,not",
	 "does,",
	 "does,not"])
	def package void testCaseAuxNegAuxNeg(String verb, String neg) {
		val aux1 = RequirementDSLFactory.eINSTANCE.createAuxNeg();
		aux1.auxiliarVerb = verb;
		aux1.negation = neg;
		assertThat(sw.caseAuxNeg(aux1)).^as("Matching String Representation of AuxNeg:").contains(verb).doesNotContain("not");
	}
	
		@ParameterizedTest
	    @CsvSource(#["doesn't,does", "don't,do", "isn't,is", "aren't,are","doesn´t,does", "don´t,do", "isn´t,is", "aren´t,are","doesn`t,does", "don`t,do", "isn`t,is", "aren`t,are"])
		def package void testCaseAuxNegAuxNegAuxiliarVerbNeg(String verbneg,String result) {
		val aux1 = RequirementDSLFactory.eINSTANCE.createAuxNeg();
		aux1.auxiliarVerbNeg = verbneg;
		//negation is reverted
		assertThat(sw.caseAuxNeg(aux1)).^as("Matching String Representation of AuxNeg:").doesNotContain(verbneg).contains(result);
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseSentenceEnding(de.fraunhofer.isst.stars.requirementDSL.SentenceEnding)}.
	 */
	@Test def package void testCaseSentenceEndingSentenceEnding() {

	//%TEST 1 single Contrains
		val end1 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
		//ordinator=ConstraintOrdinators time=INT unit=TimeUnits
		val tcon1 = RequirementDSLFactory.eINSTANCE.createTimeConstraint();
//		ConstraintOrdinators:	stuffing=StuffWord? adverbial=Adverbial comperator=Comperators?
		val conOrd1 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd1.stuffing = 
		conOrd1.adverbial = "in";
//		conOrd1.comperator = 
		tcon1.ordinator = conOrd1;
		tcon1.time = 5;
		tcon1.unit = "sec";
		val const1= RequirementDSLFactory.eINSTANCE.createConstraints();
		const1.timeConstraint.add(tcon1);
		end1.constraints = const1
//		/*relposAdv=PositionAdverbial*/ relDel=RelationDelimiter /*relComp=Comperators*/ relElements=RelObjects
//		elDel=RelationDelimiter /*relComp=Comperators*/ relElements=RelObjects
//		'in' 'relation' 'to'  | 'relative' 'to' |
//		'In' 'relation' 'to'  | 'Relative' 'to' 
		assertThat(sw.caseSentenceEnding(end1)).^as("Matching String Representation of SentenceEnding:").contains("5","sec");
		
		//%TEST 2 single Object Constraints
		val end2 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
		//timeConstraint=TimeConstraint | constraint=Constraint
		//ordinator=ConstraintOrdinators time=INT unit=TimeUnits
		val constr2 = RequirementDSLFactory.eINSTANCE.createConstraint();
		//ordinator=ConstraintOrdinators (constraint=ObjectConstraint | constraint=UnitConstraints | constraint= SetConstraint)
		val conOrd2 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd2.stuffing = 
		conOrd2.adverbial = "on"
//		conOrd2.comperator = value
		constr2.ordinator = conOrd2;
		val objConstr2 = RequirementDSLFactory.eINSTANCE.createObjectConstraint();
		val obj2 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre2 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre2.article = "the"
		obj2.article = pre2;
		obj2.object.addAll("park","spot");
		objConstr2.object = obj2;
		constr2.constraint = objConstr2;
		val const2= RequirementDSLFactory.eINSTANCE.createConstraints();
		const2.constraint.add(constr2);
		end2.constraints=const2;	
		assertThat(sw.caseSentenceEnding(end2)).^as("Matching String Representation of SentenceEnding:").contains("park","spot");


		//%TEST 3 single Constraints + Relation
		val end3 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
		//timeConstraint=TimeConstraint | constraint=Constraint
		//ordinator=ConstraintOrdinators time=INT unit=TimeUnits
		val tcon3 = RequirementDSLFactory.eINSTANCE.createTimeConstraint();
//		ConstraintOrdinators:	stuffing=StuffWord? adverbial=Adverbial comperator=Comperators?
		val conOrd3 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd3.stuffing = 
		conOrd3.adverbial = "in";
//		conOrd1.comperator = 
		tcon3.ordinator = conOrd1;
		tcon3.time = 5;
		tcon3.unit = "sec";
		val const3= RequirementDSLFactory.eINSTANCE.createConstraints();
		const3.timeConstraint.add(tcon3);
		end3.constraints=const3;
		val rel3 = RequirementDSLFactory.eINSTANCE.createRelation();
//		/*relposAdv=PositionAdverbial*/ relDel=RelationDelimiter /*relComp=Comperators*/ relElements=RelObjects
//		elDel=RelationDelimiter /*relComp=Comperators*/ relElements=RelObjects
//		'in' 'relation' 'to'  | 'relative' 'to' |
//		'In' 'relation' 'to'  | 'Relative' 'to' 
		rel3.relDel = "in relation to";
//		object+=Object property+=Property? (relConj+=RelConjunction object+=Object property+=Property?)*
		val relObj3 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val objProp3 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj3 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre3 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre3.article = "the"
		obj3.article = pre3;
		obj3.object.addAll("Papa", "Pizza", "Puffer");
		objProp3.object = obj3 ;
		//PROPERTY_TERM relativ='relative'? (property+=WORD+ | property+=STRING)
		val prop3 = RequirementDSLFactory.eINSTANCE.createProperty();
		//internal '\'s' |	'`s' | '´s'
		prop3.property.addAll("position", "height");
//		RelConjunction:	'and' 'to' | 'or' 'to';
		objProp3.property = prop3;
		relObj3.object.addAll(objProp3)
		rel3.relElements = relObj3;
		end3.rela = rel3;
		assertThat(sw.caseSentenceEnding(end3)).^as("Matching String Representation of SentenceEnding:").contains("5","sec", "Papa", "Pizza", "Puffer","position", "height", "in relation to");
		
		//%TEST 4 single Object Contraints +Relation
		val end4 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
		//timeConstraint=TimeConstraint | constraint=Constraint
		//ordinator=ConstraintOrdinators time=INT unit=TimeUnits
		val constr4 = RequirementDSLFactory.eINSTANCE.createConstraint();
		//ordinator=ConstraintOrdinators (constraint=ObjectConstraint | constraint=UnitConstraints | constraint= SetConstraint)
		val conOrd4 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd4.stuffing = 
		conOrd4.adverbial = "on"
//		conOrd4.comperator = value
		constr4.ordinator = conOrd4;
		val objConstr4 = RequirementDSLFactory.eINSTANCE.createObjectConstraint();
		val obj4 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre4 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre4.article = "the"
		obj4.article = pre4;
		obj4.object.addAll("park","spot");
		objConstr4.object = obj4;
		constr4.constraint = objConstr4;
		val const4= RequirementDSLFactory.eINSTANCE.createConstraints();
		const4.constraint.add(constr4);
		end4.constraints=const4;	
		val rel4 = RequirementDSLFactory.eINSTANCE.createRelation();
//		/*relposAdv=PositionAdverbial*/ relDel=RelationDelimiter /*relComp=Comperators*/ relElements=RelObjects
//		elDel=RelationDelimiter /*relComp=Comperators*/ relElements=RelObjects
//		'in' 'relation' 'to'  | 'relative' 'to' |
//		'In' 'relation' 'to'  | 'Relative' 'to' 
		rel4.relDel = "in relation to";
//		object+=Object property+=Property? (relConj+=RelConjunction object+=Object property+=Property?)*
		val relObj4 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val objProp4 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj44 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre44 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre44.article = "the"
		obj44.article = pre44;
		obj44.object.addAll("Papa", "Pizza", "Puffer");
		objProp4.object=obj44;
		//PROPERTY_TERM relativ='relative'? (property+=WORD+ | property+=STRING)
		val prop4 = RequirementDSLFactory.eINSTANCE.createProperty();
		//internal '\'s' |	'`s' | '´s'
		prop4.property.addAll("position", "height");
//		RelConjunction:	'and' 'to' | 'or' 'to';
		objProp4.property=prop4;
		relObj4.object.addAll(objProp4)
		rel4.relElements = relObj4;
		end4.rela = rel4;
		assertThat(sw.caseSentenceEnding(end4)).^as("Matching String Representation of SentenceEnding:").contains("park","spot", "Papa", "Pizza", "Puffer","position", "height", "in relation to");
		
		//%TEST 5  multiple Object Contraints
		val end5 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
		//timeConstraint=TimeConstraint | constraint=Constraint
		//ordinator=ConstraintOrdinators time=INT unit=TimeUnits
		val constr51 = RequirementDSLFactory.eINSTANCE.createConstraint();
		//ordinator=ConstraintOrdinators (constraint=ObjectConstraint | constraint=UnitConstraints | constraint= SetConstraint)
		val conOrd51 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd5.stuffing = 
		conOrd51.adverbial = "on"
//		conOrd51.comperator = value
		constr51.ordinator = conOrd51;
		val objConstr51 = RequirementDSLFactory.eINSTANCE.createObjectConstraint();
		val obj51 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre51 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre51.article = "the"
		obj51.article = pre51;
		obj51.object.addAll("park","spot");
		objConstr51.object = obj51;
		constr51.constraint = objConstr51;
		val const51= RequirementDSLFactory.eINSTANCE.createConstraints();
		const51.constraint.add(constr51);
		val constr52 = RequirementDSLFactory.eINSTANCE.createConstraint();
		//ordinator=ConstraintOrdinators (constraint=ObjectConstraint | constraint=UnitConstraints | constraint= SetConstraint)
		val conOrd52 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd52.stuffing = 
		conOrd52.adverbial = "on"
//		conOrd52.comperator = value
		constr52.ordinator = conOrd52;
		val objConstr52 = RequirementDSLFactory.eINSTANCE.createObjectConstraint();
		val obj52 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre52 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre52.article = "the"
		obj52.article = pre52;
		obj52.object.addAll("elma","hut");
		objConstr52.object = obj52;
		constr52.constraint = objConstr52;
		const51.constraint.add(constr52);
		end5.constraints=const51;	
		assertThat(sw.caseSentenceEnding(end5)).^as("Matching String Representation of SentenceEnding:").contains("park","spot", "elma","hut");
		
		//%TEST 6  Object Contraints + Time Constraint
		val end6 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
		//timeConstraint=TimeConstraint | constraint=Constraint
		//ordinator=ConstraintOrdinators time=INT unit=TimeUnits
		val constr61 = RequirementDSLFactory.eINSTANCE.createConstraint();
		//ordinator=ConstraintOrdinators (constraint=ObjectConstraint | constraint=UnitConstraints | constraint= SetConstraint)
		val conOrd61 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd6.stuffing = 
		conOrd61.adverbial = "on"
//		conOrd61.comperator = value
		constr61.ordinator = conOrd61;
		val objConstr61 = RequirementDSLFactory.eINSTANCE.createObjectConstraint();
		val obj61 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre61 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre61.article = "the"
		obj61.article = pre61;
		obj61.object.addAll("park","spot");
		objConstr61.object = obj61;
		constr61.constraint = objConstr61;
		val const61= RequirementDSLFactory.eINSTANCE.createConstraints();
		const61.constraint.add(constr61);
		val tcon6 = RequirementDSLFactory.eINSTANCE.createTimeConstraint();
//		ConstraintOrdinators:	stuffing=StuffWord? adverbial=Adverbial comperator=Comperators?
		val conOrd6 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd6.stuffing = 
		conOrd6.adverbial = "in";
//		conOrd6.comperator = 
		tcon6.ordinator = conOrd6;
		tcon6.time = 5;
		tcon6.unit = "sec";
		const61.timeConstraint.add(tcon6);
		end6.constraints =const61;	
		assertThat(sw.caseSentenceEnding(end6)).^as("Matching String Representation of SentenceEnding:").contains("park","spot", "5", "sec");
		
		//%TEST 7 Mutiple Contraints -Relation
		val end7 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
		val constr71 = RequirementDSLFactory.eINSTANCE.createConstraint();
		//ordinator=ConstraintOrdinators (constraint=ObjectConstraint | constraint=UnitConstraints | constraint= SetConstraint)
		val conOrd71 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd71.stuffing = 
		conOrd71.adverbial = "on"
//		conOrd71.comperator = value
		constr71.ordinator = conOrd71
		val objConstr71 = RequirementDSLFactory.eINSTANCE.createObjectConstraint();
		val obj71 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre71= RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre71.article = "the"
		obj71.article = pre71;
		obj71.object.addAll("park","spot");
		objConstr71.object = obj71;
		constr71.constraint = objConstr71;
		val const71= RequirementDSLFactory.eINSTANCE.createConstraints();
		const71.constraint.add(constr71);
		val constr72 = RequirementDSLFactory.eINSTANCE.createConstraint();
		//ordinator=ConstraintOrdinators (constraint=ObjectConstraint | constraint=UnitConstraints | constraint= SetConstraint)
		val conOrd72 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd72.stuffing = 
		conOrd72.adverbial = "on"
//		conOrd72.comperator = value
		constr72.ordinator = conOrd72
		val objConstr72 = RequirementDSLFactory.eINSTANCE.createObjectConstraint();
		val obj72 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre72= RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre72.article = "the"
		obj72.article = pre72;
		obj72.object.addAll("data","lake");
		objConstr72.object = obj72;
		constr72.constraint = objConstr72;
		const71.constraint.add(constr72);
		end7.constraints = const71;	
		
		val rel7 = RequirementDSLFactory.eINSTANCE.createRelation();
//		/*relposAdv=PositionAdverbial*/ relDel=RelationDelimiter /*relComp=Comperators*/ relElements=RelObjects
//		elDel=RelationDelimiter /*relComp=Comperators*/ relElements=RelObjects
//		'in' 'relation' 'to'  | 'relative' 'to' |
//		'In' 'relation' 'to'  | 'Relative' 'to' 
		rel7.relDel = "in relation to";
//		object+=Object property+=Property? (relConj+=RelConjunction object+=Object property+=Property?)*
		val relObj7 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val objProp7 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj77 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre77 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre77.article = "the"
		obj77.article = pre77;
		obj77.object.addAll("Papa", "Pizza", "Puffer");
		objProp7.object = obj77;
		//PROPERTY_TERM relativ='relative'? (property+=WORD+ | property+=STRING)
		val prop7 = RequirementDSLFactory.eINSTANCE.createProperty();
		//internal '\'s' |	'`s' | '´s'
		prop7.property.addAll("position", "height");
//		RelConjunction:	'and' 'to' | 'or' 'to';
		objProp7.property = prop7;
		relObj7.object.addAll(objProp7)
		rel7.relElements = relObj7;
		end7.rela = rel7;
		assertThat(sw.caseSentenceEnding(end7)).^as("Matching String Representation of SentenceEnding:").contains("park","spot", "data","lake", "Papa", "Pizza", "Puffer","position", "height", "in relation to");
	
		//%TEST 8 Object Contraints + Time Constraints + Relation
		val end8 = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
		val constr81 = RequirementDSLFactory.eINSTANCE.createConstraint();
		//ordinator=ConstraintOrdinators (constraint=ObjectConstraint | constraint=UnitConstraints | constraint= SetConstraint)
		val conOrd81 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd81.stuffing = 
		conOrd81.adverbial = "on"
//		conOrd81.comperator = value
		constr81.ordinator = conOrd81
		val objConstr81 = RequirementDSLFactory.eINSTANCE.createObjectConstraint();
		val obj81 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre81= RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre81.article = "the"
		obj81.article = pre81;
		obj81.object.addAll("park","spot");
		objConstr81.object = obj81;
		constr81.constraint = objConstr81;
		val const81= RequirementDSLFactory.eINSTANCE.createConstraints();
		const81.constraint.add(constr81);
	
		val tcon8 = RequirementDSLFactory.eINSTANCE.createTimeConstraint();
//		ConstraintOrdinators:	stuffing=StuffWord? adverbial=Adverbial comperator=Comperators?
		val conOrd8 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd8.stuffing = 
		conOrd8.adverbial = "in";
//		conOrd8.comperator = 
		tcon8.ordinator = conOrd1;
		tcon8.time = 5;
		tcon8.unit = "sec";
		const81.timeConstraint.add(tcon8);
		end8.constraints = const81;	
		
		val rel8 = RequirementDSLFactory.eINSTANCE.createRelation();
//		/*relposAdv=PositionAdverbial*/ relDel=RelationDelimiter /*relComp=Comperators*/ relElements=RelObjects
//		elDel=RelationDelimiter /*relComp=Comperators*/ relElements=RelObjects
//		'in' 'relation' 'to'  | 'relative' 'to' |
//		'In' 'relation' 'to'  | 'Relative' 'to' 
		rel8.relDel = "in relation to";
//		object+=Object property+=Property? (relConj+=RelConjunction object+=Object property+=Property?)*
		val relObj8 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val objProp8 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj88 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre88 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre88.article = "the"
		obj88.article = pre88;
		obj88.object.addAll("Papa", "Pizza", "Puffer");
		objProp8.object = obj88;
		//PROPERTY_TERM relativ='relative'? (property+=WORD+ | property+=STRING)
		val prop8 = RequirementDSLFactory.eINSTANCE.createProperty();
		//internal '\'s' |	'`s' | '´s'
		prop8.property.addAll("position", "height");
//		RelConjunction:	'and' 'to' | 'or' 'to';
		objProp8.property=prop8;
		relObj8.object.addAll(objProp8)
		rel8.relElements = relObj8;
		end8.rela = rel8;
		assertThat(sw.caseSentenceEnding(end8)).^as("Matching String Representation of SentenceEnding:").contains("park","spot", "5","sec", "Papa", "Pizza", "Puffer","position", "height", "in relation to");
		
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseConstraint(de.fraunhofer.isst.stars.requirementDSL.Constraint)}.
	 */
	@Test def package void testCaseConstraintConstraint() {
		//ordinator=ConstraintOrdinators (constraint=ObjectConstraint | constraint=UnitConstraints | constraint= SetConstraint)
		val softly = new SoftAssertions();
			
		//Test 1 ObjectConstraint
		val const1 = RequirementDSLFactory.eINSTANCE.createConstraint();
		val conOrd1 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd1.stuffing = 
		conOrd1.adverbial = "on"
//		conOrd1.comperator = value
		const1.ordinator = conOrd1;
		val objConstr1 = RequirementDSLFactory.eINSTANCE.createObjectConstraint();
		val obj1 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre1 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre1.article = "the"
		obj1.article = pre1;
		obj1.object.addAll("park","spot");
		objConstr1.object = obj1;
		const1.constraint = objConstr1;
		softly.assertThat(sw.caseConstraint(const1)).^as("Matching String Representation of Constraint:").contains("park","spot");
		
		//Test 2 SingleValueConstraints - INT
		val const2 = RequirementDSLFactory.eINSTANCE.createConstraint();
		val conOrd2 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd2.stuffing = 
		conOrd2.adverbial = "on"
//		conOrd2.comperator = value
		const2.ordinator = conOrd2;

		val valconst2 = RequirementDSLFactory.eINSTANCE.createSingleValueConstraint();
		val value2 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value2.unit = "sec";
		value2.value = 5; 
		valconst2.value = value2;
		const2.constraint = valconst2;
		softly.assertThat(sw.caseConstraint(const2)).^as("Matching String Representation of Constraint:").contains("5","sec");
		
		
		//Test 3 SingleValueConstraints - Float
		val const3 = RequirementDSLFactory.eINSTANCE.createConstraint();
		val conOrd3 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd3.stuffing = 
		conOrd3.adverbial = "on"
//		conOrd3.comperator = value
		const3.ordinator = conOrd3;
		val constr3 = RequirementDSLFactory.eINSTANCE.createSingleValueConstraint();
		val value3 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value3.unit = "km";
		value3.value = 44.33f; 
		constr3.value = value3;
		const3.constraint = constr3;
		softly.assertThat(sw.caseConstraint(const3)).^as("Matching String Representation of Constraint:").contains("44.33","km");		
		
		//Test 4 IntervallConstraints
		val const4 = RequirementDSLFactory.eINSTANCE.createConstraint();
		val conOrd4 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd4.stuffing = 
		conOrd4.adverbial = "on"
//		conOrd4.comperator = value
		const4.ordinator = conOrd4;
		val valConstr4 = RequirementDSLFactory.eINSTANCE.createIntervallConstraint();
		val value41 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value41.unit = "km";
		value41.value = 2;
		val value42 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value42.unit = "sec";
		value42.value = 14.23f;  
		valConstr4.lower = value41;
		valConstr4.higher = value42;
		const4.constraint = valConstr4;
		softly.assertThat(sw.caseConstraint(const4)).^as("Matching String Representation of Constraint:").contains("2","sec","14.23","km");
		
		//Test 5 Object SetConstraint
		val const5 = RequirementDSLFactory.eINSTANCE.createConstraint();
		val conOrd5 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd5.stuffing = 
		conOrd5.adverbial = "on"
//		conOrd5.comperator = value
		const5.ordinator = conOrd5;
		val setConstr5 = RequirementDSLFactory.eINSTANCE.createSetConstraint();
		val objSet5= RequirementDSLFactory.eINSTANCE.createObjectSet();
		val obj51= RequirementDSLFactory.eINSTANCE.createObject();
//		  PreNominative=PreNominative? relativ='relative'? (actor=WORD | actor=STRING) 
		val pre51= RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre51.article = "the";
		obj51.article = pre51;
		obj51.object.addAll("Paul");
		val obj52= RequirementDSLFactory.eINSTANCE.createObject();
//		  PreNominative=PreNominative? relativ='relative'? (actor=WORD | actor=STRING) 
		val pre52= RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre52.article = "the";
		obj52.article = pre52;
		obj52.object.addAll("Dan")
		objSet5.elements.addAll(obj51,obj52);
		setConstr5.set = objSet5;
		const5.constraint = setConstr5;
		softly.assertThat(sw.caseConstraint(const5)).^as("Matching String Representation of Constraint:").contains("Paul","Dan");
		
		//Test 6 Value SetConstraint
		val const6 = RequirementDSLFactory.eINSTANCE.createConstraint();
		val conOrd6 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd6.stuffing = 
		conOrd6.adverbial = "on"
//		conOrd6.comperator = value
		const6.ordinator = conOrd6;
		val setConstr6 = RequirementDSLFactory.eINSTANCE.createSetConstraint();
		val valCon6 = RequirementDSLFactory.eINSTANCE.createValueSet();
		val float6 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		float6.value = 2.2f;
		float6.unit = "m";
		val int6 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		int6.value = 20;
		int6.unit = "m";
		valCon6.elements.addAll(float6,int6)
		setConstr6.set=valCon6;
		const6.constraint = setConstr6;
		softly.assertThat(sw.caseConstraint(const6)).^as("Matching String Representation of Constraint:").contains("20","m","2.2");
		softly.assertAll();
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseObjectConstraint(de.fraunhofer.isst.stars.requirementDSL.ObjectConstraint)}.
	 */
	@Test def package void testCaseObjectConstraintObjectConstraint() {
		//Test 1 ObjectConstraint
		val objConstr1 = RequirementDSLFactory.eINSTANCE.createObjectConstraint();
		val obj1 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre1 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre1.article = "the"
		obj1.article = pre1;
		obj1.object.addAll("park","spot");
		objConstr1.object = obj1;
		assertThat(sw.caseObjectConstraint(objConstr1)).^as("Matching String Representation of Constraint:").contains("park","spot");
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseSetConstraint(de.fraunhofer.isst.stars.requirementDSL.SetConstraint)}.
	 */
	@Test def package void testCaseSetConstraintSetConstraint() {
		//Test 5 Object SetConstraint
		val setConstr5 = RequirementDSLFactory.eINSTANCE.createSetConstraint();
		val objSet5= RequirementDSLFactory.eINSTANCE.createObjectSet();
		val obj51= RequirementDSLFactory.eINSTANCE.createObject();
//		  PreNominative=PreNominative? relativ='relative'? (actor=WORD | actor=STRING) 
		val pre51= RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre51.article = "the";
		obj51.article = pre51;
		obj51.object.addAll("Paul");
		val obj52= RequirementDSLFactory.eINSTANCE.createObject();
//		  PreNominative=PreNominative? relativ='relative'? (actor=WORD | actor=STRING) 
		val pre52= RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre52.article = "the";
		obj52.article = pre52;
		obj52.object.addAll("Dan")
		objSet5.elements.addAll(obj51,obj52);
		setConstr5.set = objSet5;
		assertThat(sw.caseSetConstraint(setConstr5)).^as("Matching String Representation of SentenceEnding:").contains("Paul","Dan");
		
		//Test 6 Value SetConstraint
		val setConstr6 = RequirementDSLFactory.eINSTANCE.createSetConstraint();
		val valCon6 = RequirementDSLFactory.eINSTANCE.createValueSet();
		val float6 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		float6.value = 2.2f;
		float6.unit = "m";
		val int6 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		int6.value = 20;
		int6.unit = "m";
		valCon6.elements.addAll(float6,int6)
		setConstr6.set=valCon6;
		assertThat(sw.caseSetConstraint(setConstr6)).^as("Matching String Representation of SentenceEnding:").contains("20","m","2.2");
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseValueSet(de.fraunhofer.isst.stars.requirementDSL.ValueSet)}.
	 */
	@Test def package void testCaseValueSetValueSet() {
		val valCon6 = RequirementDSLFactory.eINSTANCE.createValueSet();
		val float6 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		float6.value = 2.2f;
		float6.unit = "m";
		val int6 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		int6.value = 20;
		int6.unit = "m";
		valCon6.elements.addAll(float6,int6)
		assertThat(sw.caseValueSet(valCon6)).^as("Matching String Representation of SentenceEnding:").contains("20","m","2.2");
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseActors(de.fraunhofer.isst.stars.requirementDSL.Actors)}.
	 */
	@Test def package void testCaseActorsActors() {
//		actors+=Actor (conjunction+=Conjunction actors+=Actor)* 
		val actors1 = RequirementDSLFactory.eINSTANCE.createActors();
		val act1 = RequirementDSLFactory.eINSTANCE.createActor();
		val pre1 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre1.article = "the"; 
		act1.preNominative = pre1;
		act1.actor = "Paul";
		actors1.actors.addAll(act1);
		assertThat(sw.caseActors(actors1)).^as("Matching String Representation of SentenceEnding:").contains("Paul");
		
		val actors2 = RequirementDSLFactory.eINSTANCE.createActors();
		val act2 = RequirementDSLFactory.eINSTANCE.createActor();
		val pre2 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre2.article = "the"; 
		act2.preNominative = pre2;
		act2.actor = "Dan";
		act2.relativ = "relative"
		actors2.actors.addAll(act2);
		assertThat(sw.caseActors(actors2)).^as("Matching String Representation of SentenceEnding:").contains("Dan");
		
		val actors3 = RequirementDSLFactory.eINSTANCE.createActors();
		val act3 = RequirementDSLFactory.eINSTANCE.createActor();
		val pre3 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre3.article = "the"; 
		act3.preNominative = pre3;
		act3.actor = "Paul";
		actors3.actors.addAll(act3);
		val act33 = RequirementDSLFactory.eINSTANCE.createActor();
		val pre33 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre33.article = "the"; 
		act33.preNominative = pre33;
		act33.actor = "Dan";
		actors3.actors.addAll(act3,act33);
		assertThat(sw.caseActors(actors3)).^as("Matching String Representation of SentenceEnding:").contains("Paul","Dan");
	}


	/** 
	 * Test method for {@link SemanticStringSwitch#caseRelation(de.fraunhofer.isst.stars.requirementDSL.Relation)}.
	 */
	@Test def package void testCaseRelationRelation() {
		///*relposAdv=PositionAdverbial*/ relDel=RelationDelimiter /*relComp=Comperators*/ relElements=RelObjects
		val rel1 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel1.relDel = "in relation to";
		val relObj1 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj1 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre1 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre1.article = "the";
		obj1.article = pre1;
//		obj1.relativ = value;
		obj1.object.addAll("build","scheme");
		relObj1.object.addAll(obj1);
		rel1.relElements = relObj1;
		assertThat(sw.caseRelation(rel1)).^as("Matching String Representation of Relation:").contains("build","scheme");
		
		val rel2 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel2.relDel = "in relation to";
		val relObj2 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj2 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre2 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre2.article = "the";
		obj2.article = pre2;
		obj2.relativ = "relative";
		obj2.object.addAll("build","scheme");
		relObj2.object.addAll(obj2);
		rel2.relElements = relObj2;
		assertThat(sw.caseRelation(rel2)).^as("Matching String Representation of Relation:").contains("relative","build","scheme");	
		
		val rel3 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel3.relDel = "relative to";
		val relObj3 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val objProp3 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj3 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre3 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre3.article = "the";
		obj3.article = pre3;
//		obj3.relativ = "relative";
		obj3.object.addAll("build","scheme");
		objProp3.object = obj3;
		val prop3 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop3.relativ="relative";
		prop3.property.addAll("bus","lane");
		objProp3.property=prop3
		relObj3.object.addAll(objProp3)
		rel3.relElements = relObj3;
		assertThat(sw.caseRelation(rel3)).^as("Matching String Representation of RelationObjectss:").contains("build","scheme","bus","lane");
		
		//Test 4: multiple Objects
		val rel4 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel4.relDel = "relative to";
		val relObj4 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj41 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre41 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre41.article = "the";
		obj41.article = pre41;
//		obj4.relativ = "relative";
		obj41.object.addAll("build","scheme");
		val obj42 = RequirementDSLFactory.eINSTANCE.createObject();
		
		val pre42 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre42.article = "the";
		obj42.article = pre42;
//		obj4.relativ = "relative";
		obj42.object.addAll("altar","mountain");
		
		val objProp43 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj43 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre43 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre43.article = "the";
		obj43.article = pre43;
//		obj4.relativ = "relative";
		obj43.object.addAll("brick","wall");
		objProp43.object = obj43
		val prop43 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop3.relativ="relative";
		prop43.property.addAll("car","park");
		objProp43.property = prop43
		relObj4.object.addAll(obj41,obj42);
		val con41 = RequirementDSLFactory.eINSTANCE.createRelConjunction();
		con41.text = "and"
		val con42 = RequirementDSLFactory.eINSTANCE.createRelConjunction();
		con42.text = "and"
		relObj4.relConj.addAll(con41,con42);
		relObj4.object.addAll(objProp43);
		rel4.relElements = relObj4;
		assertThat(sw.caseRelation(rel4)).^as("Matching String Representation of RelationObjectss:").contains("build","scheme","altar","mountain","brick","wall","car","park");
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseRelObjects(de.fraunhofer.isst.stars.requirementDSL.RelObjects)}.
	 */
	@Test def package void testCaseRelObjectsRelObjects() {
		//object+=Object property+=Property? (relConj+=RelConjunction object+=Object property+=Property?)*
		val softly = new SoftAssertions();
		//Test 1: Object
		val relObj1 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj1 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre1 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre1.article = "the";
		obj1.article = pre1;
//		obj1.relativ = value;
		obj1.object.addAll("build","scheme");
		relObj1.object.addAll(obj1);
		softly.assertThat(sw.caseRelObjects(relObj1)).^as("Matching String Representation of RelationObjects:").contains("build","scheme");
		
		//Test 2: relative Object
		val relObj2 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj2 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre2 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre2.article = "the";
		obj2.article = pre2;
		obj2.relativ = "relative";
		obj2.object.addAll("build","scheme");
		relObj2.object.addAll(obj2);
		softly.assertThat(sw.caseRelObjects(relObj2)).^as("Matching String Representation of RelationObjects:").contains("relative","build","scheme");	
		
		//Test 3: Object + Property
		val relObj3 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val objProp3 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj3 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre3 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre3.article = "the";
		obj3.article = pre3;
//		obj3.relativ = "relative";
		obj3.object.addAll("build","scheme");
		objProp3.object =obj3;
		val prop3 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop3.relativ="relative";
		prop3.property.addAll("bus","lane");
		objProp3.property =prop3
		relObj3.object.addAll(objProp3)
		softly.assertThat(sw.caseRelObjects(relObj3)).^as("Matching String Representation of RelationObjects:").contains("build","scheme","bus","lane");
		
		//Test 4: multiple Objects
		val relObj4 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj41 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre41 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre41.article = "the";
		obj41.article = pre41;
//		obj4.relativ = "relative";
		obj41.object.addAll("build","scheme");
		val obj42 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre42 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre42.article = "the";
		obj42.article = pre42;
//		obj4.relativ = "relative";
		obj42.object.addAll("altar","mountain");
		val obj43 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre43 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre43.article = "the";
		obj43.article = pre43;
//		obj4.relativ = "relative";
		obj43.object.addAll("brick","wall");
		relObj4.object.addAll(obj41,obj42,obj43);
		val con41 = RequirementDSLFactory.eINSTANCE.createRelConjunction();
		con41.text = "and"
		val con42 = RequirementDSLFactory.eINSTANCE.createRelConjunction();
		con42.text = "and"
		relObj4.relConj.addAll(con41,con42);
		softly.assertThat(sw.caseRelObjects(relObj4)).^as("Matching String Representation of RelationObjects:").contains("build","scheme","altar","mountain","brick","wall");
		
		//Test 5: multiple Objects with Properties
		val relObj5 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val objProp51 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj51 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre51 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre51.article = "the";
		obj51.article = pre51;
//		obj5.relativ = "relative";
		obj51.object.addAll("build","scheme");
		objProp51.object = obj51
		val prop51 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop3.relativ="relative";
		prop51.property.addAll("bus","lane");
		objProp51.property = prop51
		val objProp52 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj52 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre52 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre52.article = "the";
		obj52.article = pre52;
//		obj5.relativ = "relative";
		obj52.object.addAll("altar","mountain");
		objProp52.object = obj52
		val prop52 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop3.relativ="relative";
		prop52.property.addAll("hammer","lake");
		objProp52.property = prop52
		val objProp53 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj53 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre53 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre53.article = "the";
		obj53.article = pre53;
//		obj5.relativ = "relative";
		obj53.object.addAll("brick","wall");
		objProp53.object=obj53
		val prop53 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop3.relativ="relative";
		prop53.property.addAll("car","park");
		objProp53.property = prop53
		relObj5.object.addAll(objProp51,objProp52,objProp53);
		val con51 = RequirementDSLFactory.eINSTANCE.createRelConjunction();
		con51.text = "and"
		val con52 = RequirementDSLFactory.eINSTANCE.createRelConjunction();
		con52.text = "and"
		relObj5.relConj.addAll(con51,con52);
		softly.assertThat(sw.caseRelObjects(relObj5)).^as("Matching String Representation of RelationObjectss:").contains("build","scheme","bus","lane","altar","mountain","hammer","lake","brick","wall","car","park");
		
		
		//Test 6: mixed Objects with/without Properties
		val relObj6 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val objProp61 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj61 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre61 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre61.article = "the";
		obj61.article = pre61;
//		obj6.relativ = "relative";
		obj61.object.addAll("build","scheme");
		objProp61.object=obj61
		val prop61 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop3.relativ="relative";
		prop61.property.addAll("bus","lane");
		objProp61.property = prop61
		val obj62 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre62 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre62.article = "the";
		obj62.article = pre62;
//		obj6.relativ = "relative";
		obj62.object.addAll("altar","mountain");
		val objProp63 = RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj63 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre63 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre63.article = "the";
		obj63.article = pre63;
//		obj6.relativ = "relative";
		obj63.object.addAll("brick","wall");
		objProp63.object = obj63
		val prop63 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop3.relativ="relative";
		prop63.property.addAll("car","park");
		objProp63.property = prop63
		relObj6.object.addAll(obj62);
		relObj6.object.addAll(objProp61,objProp63);
		val con61 = RequirementDSLFactory.eINSTANCE.createRelConjunction();
		con61.text = "and"
		val con62 = RequirementDSLFactory.eINSTANCE.createRelConjunction();
		con62.text = "or"
		relObj4.relConj.addAll(con61,con62);//TODO PROBLEM WE CANNOT RECREATE THE CORRECT ORDER IN MIX CONJUNCTIONS!
		softly.assertThat(sw.caseRelObjects(relObj6)).^as("Matching String Representation of RelationObjectss:").contains("build","scheme","bus","lane","altar","mountain","brick","wall","car","park");
		softly.assertAll();
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseObject(de.fraunhofer.isst.stars.requirementDSL.Object)}.
	 */
	@ParameterizedTest
	@CsvSource(#["all,Man,,","every,dude,rock,","each,police,car,siren","whole,food,bread,sortiment","any,woman,cloth,shop","several,men,town,disc","eiter,motor,shop,manager",
		"All,Man,,","Every,dude,rock,","Each,police,car,siren","Whole,food,bread,sortiment","Any,woman,cloth,shop","Several,men,town,disc","Eiter,motor,shop,manager"])
	 def package void testCaseObjectObjectQuant(String quant, String ob1, String ob2, String ob3) {
//		article=PreNominative? relativ='relative'? (object+=WORD+ | object+=STRING) 
//Quantification:
//	'all' | 'every' | 'each' | 'whole' | 'any' | 'several' | 'either' |
//	'All' | 'Every' | 'Each' | 'Whole' | 'Any' | 'Several' | 'Either'
		val obj = RequirementDSLFactory.eINSTANCE.createObject();
		val pre33 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre33.determiner = quant; 
		obj.object.addAll(ob1,ob2===null?"":ob2,ob3===null? "":ob3);
		assertThat(sw.caseObject(obj)).^as("Matching String Representation of Object:").contains(ob1,ob2===null?"":ob2,ob3===null? "":ob3);
		obj.relativ = "relative";
		assertThat(sw.caseObject(obj)).^as("Matching String Representation of Object:").contains("relative",ob1,ob2===null?"":ob2,ob3===null? "":ob3);
	}
	
		/** 
	 * Test method for {@link SemanticStringSwitch#caseObject(de.fraunhofer.isst.stars.requirementDSL.Object)}.
	 */
	@ParameterizedTest
	@CsvSource(#["the,Man,,","a,dude,rock,","that,police,car,siren","this,food,bread,sortiment","an,emote,projector,lense",
		"The,Man,,","A,dude,rock,","That,police,car,siren","This,food,bread,sortiment","An,emote,projector,lense"])
	 def package void testCaseObjectObjectArticle(String art, String ob1, String ob2, String ob3) {
		val obj = RequirementDSLFactory.eINSTANCE.createObject();
		val pre33 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre33.article = art; 
		obj.object.addAll(ob1,ob2===null?"":ob2,ob3===null? "":ob3);
		assertThat(sw.caseObject(obj)).^as("Matching String Representation of Object:").contains(ob1,ob2===null?"":ob2,ob3===null? "":ob3);
		obj.relativ = "relative";
		assertThat(sw.caseObject(obj)).^as("Matching String Representation of Object:").contains("relative",ob1,ob2===null?"":ob2,ob3===null? "":ob3);
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseProperty(de.fraunhofer.isst.stars.requirementDSL.Property)}.
	 */
	@Test def package void testCasePropertyProperty() {
		//PROPERTY_TERM relativ='relative'? (property+=WORD+ | property+=STRING)
		val prop1 = RequirementDSLFactory.eINSTANCE.createProperty();
		//internal '\'s' |	'`s' | '´s'
		prop1.property.addAll("position");
//		RelConjunction:	'and' 'to' | 'or' 'to';
		assertThat(sw.caseProperty(prop1)).^as("Matching String Representation of Property:").contains("position");
		val prop2 = RequirementDSLFactory.eINSTANCE.createProperty();
		//internal '\'s' |	'`s' | '´s'
		prop2.relativ = "relative";
		prop2.property.addAll("position");
		assertThat(sw.caseProperty(prop2)).^as("Matching String Representation of Property:").contains("relative","position");
		val prop3 = RequirementDSLFactory.eINSTANCE.createProperty();
		//internal '\'s' |	'`s' | '´s'
		prop3.property.addAll("house","spot");
		assertThat(sw.caseProperty(prop3)).^as("Matching String Representation of Property:").contains("house","spot");
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseConstraints(de.fraunhofer.isst.stars.requirementDSL.Constraints)}.
	 */
	@Test def package void testCaseConstraintsConstraints() {
		//timeConstraint=TimeConstraint | constraint=Constraint


		val softly = new SoftAssertions();	
		//Test 1 ObjectConstraint
		val consts1 = RequirementDSLFactory.eINSTANCE.createConstraints();
		val const1 = RequirementDSLFactory.eINSTANCE.createConstraint();
		val conOrd1 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd1.stuffing = 
		conOrd1.adverbial = "on"
//		conOrd1.comperator = value
		const1.ordinator = conOrd1;
		val objConstr1 = RequirementDSLFactory.eINSTANCE.createObjectConstraint();
		val obj1 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre1 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre1.article = "the"
		obj1.article = pre1;
		obj1.object.addAll("park","spot");
		objConstr1.object = obj1;
		const1.constraint = objConstr1;
		consts1.constraint.add(const1);
		softly.assertThat(sw.caseConstraints(consts1)).^as("Matching String Representation of Constraints:").contains("park","spot");
		
		//Test 2 SingleValueConstraints - INT
		val consts2 = RequirementDSLFactory.eINSTANCE.createConstraints();
		val const2 = RequirementDSLFactory.eINSTANCE.createConstraint();
		val conOrd2 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd2.stuffing = 
		conOrd2.adverbial = "on"
//		conOrd2.comperator = value
		const2.ordinator = conOrd2;
		val valconst2 = RequirementDSLFactory.eINSTANCE.createSingleValueConstraint();
		val value2 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value2.unit = "sec";
		value2.value = 5; 
		valconst2.value = value2;
		const2.constraint = valconst2;
		consts2.constraint.add(const2);
		softly.assertThat(sw.caseConstraints(consts2)).^as("Matching String Representation of Constraints:").contains("5","sec");
		
		
		//Test 3 SingleValueConstraints - Float
		val consts3 = RequirementDSLFactory.eINSTANCE.createConstraints();
		val const3 = RequirementDSLFactory.eINSTANCE.createConstraint();
		val conOrd3 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd3.stuffing = 
		conOrd3.adverbial = "on"
//		conOrd3.comperator = value
		const3.ordinator = conOrd3;
		val constr3 = RequirementDSLFactory.eINSTANCE.createSingleValueConstraint();
		val value3 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value3.unit = "km";
		value3.value = 44.33f; 
		constr3.value = value3;
		const3.constraint = constr3;
		consts3.constraint.add(const3);
		softly.assertThat(sw.caseConstraints(consts3)).^as("Matching String Representation of Constraints:").contains("44.33","km");		
		
		//Test 4 IntervallConstraints
		val consts4 = RequirementDSLFactory.eINSTANCE.createConstraints();
		val const4 = RequirementDSLFactory.eINSTANCE.createConstraint();
		val conOrd4 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd4.stuffing = 
		conOrd4.adverbial = "on"
//		conOrd4.comperator = value
		const4.ordinator = conOrd4;
		val valConstr4 = RequirementDSLFactory.eINSTANCE.createIntervallConstraint();
		val value41 = RequirementDSLFactory.eINSTANCE.createIntValue();
		value41.unit = "km";
		value41.value = 2;
		val value42 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		value42.unit = "sec";
		value42.value = 14.23f;  
		valConstr4.lower = value41;
		valConstr4.higher = value42;
		const4.constraint = valConstr4;
		consts4.constraint.add(const4);
		softly.assertThat(sw.caseConstraints(consts4)).^as("Matching String Representation of Constraints:").contains("2","sec","14.23","km");
		
		//Test 5 Object SetConstraint
		val consts5 = RequirementDSLFactory.eINSTANCE.createConstraints();
		val const5 = RequirementDSLFactory.eINSTANCE.createConstraint();
		val conOrd5 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd5.stuffing = 
		conOrd5.adverbial = "on"
//		conOrd5.comperator = value
		const5.ordinator = conOrd5;
		val setConstr5 = RequirementDSLFactory.eINSTANCE.createSetConstraint();
		val objSet5= RequirementDSLFactory.eINSTANCE.createObjectSet();
		val obj51= RequirementDSLFactory.eINSTANCE.createObject();
//		  PreNominative=PreNominative? relativ='relative'? (actor=WORD | actor=STRING) 
		val pre51= RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre51.article = "the";
		obj51.article = pre51;
		obj51.object.addAll("Paul");
		val obj52= RequirementDSLFactory.eINSTANCE.createObject();
//		  PreNominative=PreNominative? relativ='relative'? (actor=WORD | actor=STRING) 
		val pre52= RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre52.article = "the";
		obj52.article = pre52;
		obj51.object.addAll("Dan");
		objSet5.elements.addAll(obj51,obj52);
		setConstr5.set = objSet5;
		const5.constraint = setConstr5;
		consts5.constraint.add(const5);
		softly.assertThat(sw.caseConstraints(consts5)).^as("Matching String Representation of Constraints:").contains("Paul","Dan");
		
		//Test 6 Value SetConstraint
		val consts6 = RequirementDSLFactory.eINSTANCE.createConstraints();
		val const6 = RequirementDSLFactory.eINSTANCE.createConstraint();
		val conOrd6 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd6.stuffing = 
		conOrd6.adverbial = "on"
//		conOrd6.comperator = value
		const6.ordinator = conOrd6;
		val setConstr6 = RequirementDSLFactory.eINSTANCE.createSetConstraint();
		val valCon6 = RequirementDSLFactory.eINSTANCE.createValueSet();
		val float6 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		float6.value = 2.2f;
		float6.unit = "m";
		val int6 = RequirementDSLFactory.eINSTANCE.createFloatValue();
		int6.value = 20;
		int6.unit = "m";
		valCon6.elements.addAll(float6,int6)
		setConstr6.set=valCon6;
		const6.constraint = setConstr6;
		consts6.constraint.add(const6);
		softly.assertThat(sw.caseConstraints(consts6)).^as("Matching String Representation of Constraints:").contains("20","m","2.2");
		softly.assertAll();
		
		val const7= RequirementDSLFactory.eINSTANCE.createConstraints();
		val tcon7 = RequirementDSLFactory.eINSTANCE.createTimeConstraint();
//		ConstraintOrdinators:	stuffing=StuffWord? adverbial=Adverbial comperator=Comperators?
		val conOrd7 = RequirementDSLFactory.eINSTANCE.createConstraintOrdinators();
//		conOrd7.stuffing = 
		conOrd7.adverbial = "in";
//		conOrd7.comperator = 
		tcon7.ordinator = conOrd7;
		tcon7.time = 5;
		tcon7.unit = "sec";
		const7.timeConstraint.add(tcon7);
		assertThat(sw.caseConstraints(const7)).^as("Matching String Representation of Constraints (TIME):").contains("in","5","sec");
	}

	/** 
	 * Test method for {@link SemanticStringSwitch#caseSentenceBegin(de.fraunhofer.isst.stars.requirementDSL.SentenceBegin)}.
	 */
	@Test def package void testCaseSentenceBeginSentenceBegin() {
//		(rela=Relation ',')
		val beg1= RequirementDSLFactory.eINSTANCE.createSentenceBegin();
		val rel1 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel1.relDel = "in relation to";
		val relObj1 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj1 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre1 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre1.article = "the";
		obj1.article = pre1;
//		obj1.relativ = value;
		obj1.object.addAll("build","scheme");
		relObj1.object.addAll(obj1);
		rel1.relElements = relObj1;
		beg1.rela = rel1;
		assertThat(sw.caseSentenceBegin(beg1)).^as("Matching String Representation of SentenceBeginning:").contains("build","scheme");
		
		
		val beg2= RequirementDSLFactory.eINSTANCE.createSentenceBegin();
		val rel2 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel2.relDel = "in relation to";
		val relObj2 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj2 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre2 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre2.article = "the";
		obj2.article = pre2;
		obj2.relativ = "relative";
		obj2.object.addAll("build","scheme");
		relObj2.object.addAll(obj2);
		rel2.relElements = relObj2;
		beg2.rela = rel2;
		assertThat(sw.caseSentenceBegin(beg2)).^as("Matching String Representation of SentenceBeginning:").contains("relative","build","scheme");	
		
		val beg3= RequirementDSLFactory.eINSTANCE.createSentenceBegin();
		val rel3 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel3.relDel = "relative to";
		val relObj3 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val objProp3 =RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj3 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre3 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre3.article = "the";
		obj3.article = pre3;
//		obj3.relativ = "relative";
		obj3.object.addAll("build","scheme");
		objProp3.object=obj3;
		val prop3 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop3.relativ="relative";
		prop3.property.addAll("bus","lane");
		objProp3.property=prop3
		relObj3.object.addAll(objProp3)
		rel3.relElements = relObj3;
		beg3.rela = rel3;
		assertThat(sw.caseSentenceBegin(beg3)).^as("Matching String Representation of SentenceBeginning:").contains("build","scheme","bus","lane");

		val beg4= RequirementDSLFactory.eINSTANCE.createSentenceBegin();		
		val rel4 = RequirementDSLFactory.eINSTANCE.createRelation();
		rel4.relDel = "relative to";
		val relObj4 = RequirementDSLFactory.eINSTANCE.createRelObjects();
		val obj41 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre41 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre41.article = "the";
		obj41.article = pre41;
//		obj4.relativ = "relative";
		obj41.object.addAll("build","scheme");
		val obj42 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre42 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre42.article = "the";
		obj42.article = pre42;
//		obj4.relativ = "relative";
		obj42.object.addAll("altar","mountain");
		
		val objProp43 =RequirementDSLFactory.eINSTANCE.createRelObjectProperty();
		val obj43 = RequirementDSLFactory.eINSTANCE.createObject();
		val pre43 = RequirementDSLFactory.eINSTANCE.createPreNominative();
		pre43.article = "the";
		obj43.article = pre43;
//		obj4.relativ = "relative";
		obj43.object.addAll("brick","wall");
		objProp43.object=obj43
		val prop43 = RequirementDSLFactory.eINSTANCE.createProperty();
//		prop3.relativ="relative";
		prop43.property.addAll("car","park");
		objProp43.property=prop43
		relObj4.object.addAll(obj41,obj42);
		relObj4.object.addAll(objProp43);
		val con41 = RequirementDSLFactory.eINSTANCE.createRelConjunction();
		con41.text = "and"
		val con42 = RequirementDSLFactory.eINSTANCE.createRelConjunction();
		con42.text = "and"
		relObj4.relConj.addAll(con41,con42);//TODO PROBLEM WE CANNOT RECREATE THE CORRECT ORDER IN MIX CONJUNCTIONS!
		rel4.relElements = relObj4;
		beg4.rela = rel4;
		assertThat(sw.caseSentenceBegin(beg4)).^as("Matching String Representation of SentenceBeginning:").contains("build","scheme","altar","mountain","brick","wall","car","park");
		
		
		
	}
}
