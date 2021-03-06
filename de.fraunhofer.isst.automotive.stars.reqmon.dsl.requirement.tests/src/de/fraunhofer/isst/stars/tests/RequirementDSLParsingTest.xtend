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
package de.fraunhofer.isst.stars.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import de.fraunhofer.isst.stars.requirementDSL.RequirementModel

@RunWith(XtextRunner)
@InjectWith(RequirementDSLInjectorProvider)
class RequirementDSLParsingTest {
	@Inject
	ParseHelper<RequirementModel> parseHelper
	
	@Test
	def void loadModel() {
		
		/*
		 * Test of the given requirement examples
		 */ 
		testSequence('''
			Req 1: The system must not perform a lane change to any lane if a vehicle is on that lane and the vehicle is behind the ego vehicle and the vehicle´s relative velocity is more than 5 m/s.
			Req 2: The system must not perform a lane change to any lane if a vehicle is on that lane and the vehicle is in front to the ego vehicle and the vehicle´s relative velocity is less than 5 m/s.
			Req Req3a: The system must not perform a lane change to any lane if a vehicle is on the ego lane and the vehicle is in_front_of the ego-vehicle and the vehicle´s distance in relation to the ego-vehicle is less than 1m.
			Req Req3b: The system must not perform a lane change to any lane if a vehicle is on the ego-lane and the vehicle is in_front_of the ego-vehicle and the vehicle´s distance is less than 1 m in relation to the ego-vehicle.
			Req 4: The system must not perform a lane change to any lane if a vehicle is on the ego lane and the vehicle is behind the ego vehicle  and the vehicle´s relative velocity is larger than 10 m/s.
			Req 5: The system must not perform a lane change to any lane if a vehicle is on that lane and the vehicle is next to the ego vehicle and the vehicle´s relative velocity is less than 2m/s.
			Req 5: The system must not perform a lane change to any lane if the lane´s markings are solid.
			Req 6: The system must not perform a lane change to any lane if the lane´s markings is unknown.
			Req 7: The system must not perform a lane change to any lane if the lane is unknown.
			Req 8: The system must not perform a lane change to any lane if the lane is not existing.
			Req 9: The system must not perform a lane change to any lane if the  lane is a restricted lane.
			Req 10: The system must not perform a lane change to any lane if the lane is a emergency lane.
			Req 12: The system must not perform a lane change to any lane if the lane is next to a highway on-ramp.
			Req 13: The system must not perform a lane change to any lane if the lane´s curvature is higher than 25 rad/m.
			Req 14: The system must not perform a lane change to any lane if ego-vehicle´s velocity is less than 10 m/s.
			Req 15: The system must not perform a lane change to any lane if domain is not handled.
			Req 16: The system must not perform a lane change to any lane if the driver overturns the system with more than 10 nm.
			Req Req17a: The system must not perform a lane change to any lane if the ego-vehicle´s lateral-offset in relation to the ego-lane´s center is more than 0.4 m. 
			Req Req17b: The system must not perform a lane change to any lane if the ego-vehicle´s lateral-offset in relation to the ego-lane´s center is more than 0.4 m and a lane-change is not in progress.
		''')
		
		/*
		 * Test of the Requirement rule 
		 * 
		 * 'Req': RequirementText;
		 * 'Req' ReqID: RequirementText;
		 */ 
		testSequence('''
			Req: x x.
			Req: x x;
			Req x4: x x.
			Req x4: x x;
			
			Req : The test must be successful.
			Req : The test must be successful;
			Req: The test must be successful.
			Req: The test must be successful;
			Req id123: The test must be successful.
			Req id123: The test must be successful;
			
		''')
		
		/*
		 * Test of the RequirementText rule
		 * 
		 * Req 1: ConditionalClause then MainClause
		 * Req 2: ConditionalClause, then MainClause
		 * Req 3: ConditionalClause then MainClause ConditionalClause
		 * Req 4: ConditionalClause, then MainClause ConditionalClause
		 * Req 5: ConditionalClause, then MainClause, ConditionalClause
		 * Req 6: MainClause ConditionalClause
		 * Req 7: MainClause ConditionalClause
		 * Req 8: ConditionalClause, then ExistenceClause
		 */ 
		testSequence('''
			Req 1: When x x then x x.
			Req 2: When x x, then x x.
			Req 3: When x x then x x if x x.
			Req 4: When x x, then x x if x x.
			Req 5: When x x, then x x, if x x.
			Req 6: x x if x x.
			Req 7: x x, if x x.
			
			Req 8: If x x, then there exist x, who must x.
			
			Req: When the precondition is correct then the test must be successful.
			Req: When the precondition is correct, then the test must be successful.
			Req: When the precondition is correct then the test must be successful if the postcondition is true.
			Req: When the precondition is correct, then the test must be successful if the postcondition is true.
			Req: When the precondition is correct, then the test must be successful, if the postcondition is true.
			Req: The test must be successful if the postcondition is true.
			Req: The test must be successful, if the postcondition is true.
			
		''')
		
		/*
		 * Test of the ConditionalClause rule
		 * 
		 * Req 1: ClauseOrdinator Clauses MainClause
		 * Req 2: MainClause ClauseOrdinator Clauses
		 * Req 3: ClauseOrdinator ExistenceClause MainClause
		 * Req 3: MainClause ClauseOrdinator ExistenceClause 
		 */ 
		testSequence('''
			Req 1: If x x then x x.
			Req 1: After x x then x x.
			Req 1: Once x x then x x.
			Req 1: When x x then x x.
			Req 1: Whenever x x then x x.
			Req 1: While x x then x x.
			Req 1: Before x x then x x.
			Req 1: Until x x then x x.
			Req 2: x x if x x.
			Req 2: x x after x x.
			Req 2: x x once x x.
			Req 2: x x when x x.
			Req 2: x x whenever x x.
			Req 2: x x while x x.
			Req 2: x x before x x.
			Req 2: x x until x x.
			
			Req 3: If there exist x, who must x then x x.
			Req 3: x x if there exist x, who must x.
			
			Req: The test must be successful If the precondition is correct.
			Req: The test must be successful if the precondition is correct.
			Req: The test must be successful After the precondition is correct.
			Req: The test must be successful after the precondition is correct.
			Req: The test must be sucessful Once the precondition has been correct.
			Req: The test must be sucessful once the precondition has been correct.
			Req: The test must be successful When the precondition is correct.
			Req: The test must be successful when the precondition is correct.
			Req: The test must be sucessful Whenever the precondition has been correct.
			Req: The test must be sucessful whenever the precondition has been correct.
			Req: The test must be sucessful While the system is build.
			Req: The test must be sucessful while the system is build.
			Req: The test must be sucessful Before the system is ready.
			Req: The test must be sucessful before the system is ready.
			Req: The test must be sucessful Until the system is changed.
			Req: The test must be sucessful until the system is changed.
			
		''')
		
		/*
		 * Test of the MainClause rule
		 * 
		 * Req 1: Modifier Clauses
		 * Req 2: ConditionalClause Modifier Clauses
		 */
		testSequence('''
			Req 1: Globally x x.
			Req 1: Always x x.
			Req 1: Sometimes x x.
			Req 1: Eventually x x.
			Req 2: If x x then globally x x.
			Req 2: If x x then always x x.
			Req 2: If x x then sometimes x x.
			Req 2: If x x then eventually x x.
			
			Req: The test must be successful.
			Req: the test must be successful.
			Req: Globally the test must be successful.
			Req: globally the test must be successful.
			Req: Always the test must be successful.
			Req: always the test must be successful.
			Req: Sometimes the test must be successful.
			Req: sometimes the test must be successful.
			Req: Eventually the test must be successful.
			Req: eventually the test must be successful.
			
		''')
		
		/*
		 * Test of the ExistenceClause rule
		 * modifier+=Modifier? exist+=ExistenceSentence (',' conjunction+=Conjunction modifier+=Modifier? (exist+=ExistenceSentence | clause+=Clause))*
		 * Req 1: ExistenceSentence.
		 * Req 2: Modifier ExistenceSentence.
		 * Req 3: ExistenceSentence, Conjunction ExistenceSentence.
		 * Req 3: Modifier ExistenceSentence, Conjunction ExistenceSentence.
		 * Req 4: ExistenceSentence, Conjunction Modifier ExistenceSentence.
		 * Req 4: Modifier ExistenceSentence, Conjunction Modifier ExistenceSentence.
		 * Req 5: ExistenceSentence, Conjunction Clause.
		 * Req 5: Modifier ExistenceSentence, Conjunction Clause.
		 * Req 5: ExistenceSentence, Conjunction Modifier Clause.
		 * Req 5: Modifier ExistenceSentence, Conjunction Modifier Clause.
		 * Req 6: Modifier ExistenceSentence, Conjunction Modifier Clause, Conjunction Modifier ExistenceSentence.
		 */
		testSequence('''
			Req 1: There exist x, who must x.
			Req 2: Always there exist x, who must x.
			Req 3: There exist x, who must x, and there exist x, who must x.
			Req 3: Sometimes there exist x, who must x, and there exist x, who must x.
			Req 4: There exist x, who must x, and globally there exist x, who must x.
			Req 4: Sometimes there exist x, who must x, and globally there exist x, who must x.
			Req 5: There exist x, who must x, and x x.
			Req 5: Sometimes there exist x, who must x, and x x.
			Req 5: There exist x, who must x, and globally x x.
			Req 5: Sometimes there exist x, who must x, and globally x x.
			Req 6: Sometimes there exist x, who must x or who shall x, and globally x x, or eventually there exist x, who shall x.
		''')
		
		/*
		 * Test of the Clauses rule
		 * 
		 * Req 1: MainClause Conjunction MainClause
		 * Req 2: ConditionalClause Conjunction ConditionalClause MainClause
		 * Req 3: MainClause ConditionalClause Conjunction ConditionalClause
		 * Req 4: ConditionalClause Conjunction ConditionalClause MainClause Conjunction MainClause ConditionalClause Conjunction ConditionalClause
		 */ 
		testSequence('''
			Req 1: x x and x x.
			Req 1: x x or x x.
			Req 2: if x x and x x then x x.
			Req 2: if x x or x x then x x.
			Req 3: x x if x x and x x.
			Req 3: x x if x x or x x.
			Req 4: if x x and x x then x x and x x if x x and x x.
			Req 4: if x x or x x then x x or x x if x x or x x.
			
			Req: The test must be successful if the precondition is correct and the system is important.
			Req: The test must be successful if the precondition is correct or the system is complex.
			Req: The test must be successful if the precondition is correct and the system is important or the system is complex.
			Req: The test must be successful and the precondition must be correct and the system should be important.
			Req: The test must be successful and the precondition must be correct or it should be nice.
			Req: The test must be successful and the precondition must be correct and the system should be important or it should be nice.
			
		''')
		
		/*
		 * Test of the ModalitySentence rule
		 * 
		 * Req 1: Actors Modality Predicate
		 * Req 2: Actors Modality Negation Predicate
		 * Req 3: SentenceBegin Actors Modality Predicate
		 * Req 4: SentenceBegin Actors Modality Negation Predicate
		 * Req 5: Actors Modality AuxiliaryVerb Predicate
		 * Req 6: Actors Modality Negation AuxiliaryVerb Predicate
		 * Req 7: SentenceBegin Actors Modality AuxiliaryVerb Predicate
		 * Req 8: SentenceBegin Actors Modality Negation AuxiliaryVerb Predicate
		 * Req 9: Actors Modality Predicate SentenceEnding
		 * Req 10: Actors Modality Negation Predicate SentenceEnding
		 * Req 11: Actors Modality AuxiliaryVerb Predicate SentenceEnding
		 * Req 12: Actors Modality Negation AuxiliaryVerb Predicate SentenceEnding
		 * Req 13: SentenceBegin Actors Modality Negation AuxiliaryVerb Predicate SentenceEnding
		 */ 
		testSequence('''
			Req 1: x shall x.
			Req 1: x should x.
			Req 1: x will x.
			Req 1: x would x.
			Req 1: x can x.
			Req 1: x could x.
			Req 1: x must x.
			
			Req 2: x shall not x.
			Req 2: x should not x.
			Req 2: x will not x.
			Req 2: x would not x.
			Req 2: x can not x.
			Req 2: x could not x.
			Req 2: x must not x.
			
			Req 3: in relation to x, x shall x.
			Req 4: in relation to x, x shall not x.
			
			Req 5: x shall is x.
			Req 5: x shall are x.
			Req 5: x shall be x.
			Req 5: x shall been x.
			Req 5: x shall has x.
			Req 5: x shall have x.
			Req 5: x shall do x.
			Req 5: x shall does x.
			
			Req 6: x shall not do x.
			Req 7: in relation to x, x shall do x.
			Req 8: in relation to x, x shall not do x.
			
			Req 9: x shall x in x.
			Req 10: x shall not x in x.
			Req 11: x shall do x in x.
			Req 12: x shall not do x in x.
			Req 13: in relation to x, x shall not do x in x.
			
		''')
		
		/*
		 * Test of the PredicateSentence rule
		 * 
		 * Req 1: Actors AuxNeg
		 * Req 2: SentenceBegin Actors AuxNeg
		 * Req 3: Actors AuxNeg AuxiliaryVerb
		 * Req 4: SentenceBegin Actors AuxNeg AuxiliaryVerb
		 * Req 5: Actors AuxNeg Preds
		 * Req 6: SentenceBegin Actors AuxNeg Preds
		 * Req 7: Actors AuxNeg AuxiliaryVerb Preds
		 * Req 8: SentenceBegin Actors AuxNeg AuxiliaryVerb Preds
		 * Req 9: Actors AuxNeg SentenceEnding
		 * Req 10: SentenceBegin Actors AuxNeg SentenceEnding
		 * Req 11: Actors AuxNeg AuxiliaryVerb SentenceEnding
		 * Req 12: SentenceBegin Actors AuxNeg AuxiliaryVerb SentenceEnding
		 * Req 13: Actors AuxNeg Preds SentenceEnding
		 * Req 14: SentenceBegin Actors AuxNeg Preds SentenceEnding
		 * Req 15: Actors AuxNeg AuxiliaryVerb Preds SentenceEnding
		 * Req 16: SentenceBegin Actors AuxNeg AuxiliaryVerb Preds SentenceEnding
		 * Req 17: Actors Preds
		 * Req 18: SentenceBegin Actors Preds
		 * Req 19: Actors Preds SentenceEnding
		 * Req 20: SentenceBegin Actors Preds SentenceEnding
		 */ 
		testSequence('''
			Req 1: x is.
			Req 2: in relation to x, x is.
			Req 3: x doesn't is.
			Req 4: in relation to x, x doesn't is.
			Req 5: x is x.
			Req 6: in relation to x, x is x.
			Req 7: x doesn't is x.
			Req 8: in relation to x, x doesn't is x.
			Req 9: x is in x.
			Req 10: in relation to x, x is in x.
			Req 11: x doesn't is in x.
			Req 12: in relation to x, x doesn't is in x.
			Req 13: x is x in x.
			Req 14: in relation to x, x is x in x.
			Req 15: x doesn't is x in x.
			Req 16: in relation to x, x doesn't is x in x.
			Req 17: x x.
			Req 18: in relation to x, x x.
			Req 19: x x in x.
			Req 20: in relation to x, x x in x.
			
		''')
		
		/*
		 * Test of the ExistenceSentence rule
		 * 
		 * Req 1: ExistencePreface Actors, relativeClause.
		 * Req 2: ExistencePreface Actors, relativeClause, Conjunction MainClause.
		 */
		 //TODO THIS SEQUENCE THROWS ERROS
		testSequence('''
			Req 1: There exist x, who must x.
			Req 1: There exists x, who must x.
			Req 2: There exist x, who must x, and x x.
			Req 2: There exists x, who must x, and x x.	
		''')
		
		/*
		 * Test of the PropertySentence rule
		 * 
		 * Req 1: ActorProperties Modality PredOrObject
		 * Req 2: ActorProperties Relation Modality PredOrObject
		 * Req 3: ActorProperties Modality Negation PredOrObject
		 * Req 4: ActorProperties Relation Modality Negation PredOrObject
		 * Req 5: ActorProperties Modality AuxiliaryVerb PredOrObject
		 * Req 6: ActorProperties Relation Modality AuxiliaryVerb PredOrObject
		 * Req 7: ActorProperties Modality Negation AuxiliaryVerb PredOrObject
		 * Req 8: ActorProperties Relation Modality Negation AuxiliaryVerb PredOrObject
		 * Req 9: ActorProperties Modality PredOrObject SentenceEnding
		 * Req 10: ActorProperties Relation Modality PredOrObject SentenceEnding
		 * Req 11: ActorProperties Modality Negation PredOrObject SentenceEnding
		 * Req 12: ActorProperties Relation Modality Negation PredOrObject SentenceEnding
		 * Req 13: ActorProperties Modality AuxiliaryVerb PredOrObject SentenceEnding
		 * Req 14: ActorProperties Relation Modality AuxiliaryVerb PredOrObject SentenceEnding
		 * Req 15: ActorProperties Modality Negation AuxiliaryVerb PredOrObject SentenceEnding
		 * Req 16: ActorProperties Relation Modality Negation AuxiliaryVerb PredOrObject SentenceEnding
		 * Req 17: ActorProperties AuxNeg PredOrObject
		 * Req 18: ActorProperties AuxNeg Constraints
		 * Req 19: ActorProperties Relation AuxNeg PredOrObject
		 * Req 20: ActorProperties Relation AuxNeg constraints
		 * Req 21: ActorProperties AuxNeg PredOrObject SentenceEnding
		 * Req 22: ActorProperties AuxNeg Constraints SentenceEnding
		 * Req 23: ActorProperties Relation AuxNeg PredOrObject SentenceEnding
		 * Req 24: ActorProperties Relation AuxNeg constraints SentenceEnding
		 */ 
		testSequence('''
			Req 1: x's x shall x.
			Req 2: x's x in relation to x shall x.
			Req 3: x's x shall not x.
			Req 4: x's x in relation to x shall not x.
			Req 5: x's x shall do x.
			Req 6: x's x in relation to x shall do x.
			Req 7: x's x shall not do x.
			Req 8: x's x in relation to x shall not do x.
			Req 9: x's x shall x in x.
			Req 10: x`s x in relation to x shall x in x.
			Req 11: x`s x shall not x in x.
			Req 12: x`s x in relation to x shall not x in x.
			Req 13: x`s x shall do x in x.
			Req 14: x`s x in relation to x shall do x in x.
			Req 15: x`s x shall not do x in x.
			Req 16: x`s x in relation to x shall not do x in x.
			Req 17: x`s x is x.
			Req 18: x`s x is in x.
			Req 19: x`s x in relation to x is x.
			Req 20: x`s x in relation to x is in x.
			Req 21: x´s x is x in x.
			Req 22: x´s x is in x in relation to x.
			Req 23: x´s x in relation to x is x in x.
			Req 24: x´s x in relation to x is x in relation to x.
			
		''')
		
		/*
		 * Test of the relativeClause rule
		 * 
		 * Req 1: ExistencePreface Actors, relativeSentence.
		 * Req 2: ExistencePreface Actors, relativeSentence Conjunction relativeSentence.
		 * Req 3: ExistencePreface Actors, relativeSentence, ConditionalClause.
		 * Req 3: ExistencePreface Actors, relativeSentence Conjunction relativeSentence, ConditionalClause.
		 */ 
		testSequence('''
			Req 1: There exists x, who must x.
			Req 2: There exists x, who must x or who must x.
			Req 3: There exists x, who must x, if x x.
			Req 3: There exists x, who must x if x x.
			Req 3: There exists x, who must x or who must x, when x x.
			
		''')
		
		/*
		 * Test of the relativeSentence rule
		 * 
		 * Req 1: ExistencePreface Actors, RelativePronounsSubject Modality Predicate,
		 * Req 2: ExistencePreface Actors, RelativePronounsSubject Modality Negation Predicate,
		 * Req 3: ExistencePreface Actors, RelativePronounsSubject Modality Predicate Constraints,
		 * Req 4: ExistencePreface Actors, RelativePronounsSubject Modality Negation Predicate Constraints,
		 * Req 5: ExistencePreface Actors, RelativePronounsSubject Predicate,
		 * Req 6: ExistencePreface Actors, RelativePronounsSubject WORD Negation Predicate,
		 * Req 7: ExistencePreface Actors, RelativePronounsSubject Predicate Constraints,
		 * Req 8: ExistencePreface Actors, RelativePronounsSubject WORD Negation Predicate Constraints,
		 * Req 9: ExistencePreface Actors, RelativePronounsObject ModalitySentence,
		 * Req 10: ExistencePreface Actors, RelativePronounsObject PredicateSentence,
		 */ 
		testSequence('''
			Req 1: There exists x, which must x.
			Req 1: There exists x, who must x.
			Req 1: There exists x, that must x.
			Req 2: There exists x, which must not x.
			Req 3: There exists x, who must x in x.
			Req 4: There exists x, that must not x in x.
			Req 5: There exists x, which x.
			Req 6: There exists x, who is not x.
			Req 7: There exists x, that x in x.
			Req 8: There exists x, who are not x in x.
			Req 9: There exists x, whose x must x.
			Req 9: There exists x, whom x must x.
			Req 10: There exists x, whose x x.
			Req 11: There exists x, whom x x.
			Req 12: There exists x, who be not x.
			Req 13: There exists x, who does not x.
			Req 14: There exists x, who has not x.
			
		''')
		
		/*
		 * Test of the Actors rule
		 * 
		 * Req 1: Actor Conjunction Actor Predicate
		 * Req 2: Actor Conjunction Actor Predicate Conjunction MainClause
		 */ 
		testSequence('''
			Req 1: x and x x.
			Req 1: x or x x.
			Req 2: x and x x and x x.
			
		''')
		
		/*
		 * Test of the Actor rule
		 * 
		 * Req 1: Quantification WORD Predicate
		 * Req 2: Articles WORD Predicate
		 * Req 3: RefArticles WORD Predicate
		 * Req 4: PreNominative STRING Predicate
		 */ 
		testSequence('''
			Req 1: all x x.
			Req 1: every x x.
			Req 1: each x x.
			Req 1: whole x x.
			Req 1: any x x.
			Req 1: several x x.
			Req 1: either x x.
			Req 1: All x x.
			Req 1: Every x x.
			Req 1: Each x x.
			Req 1: Whole x x.
			Req 1: Any x x.
			Req 1: Several x x.
			Req 1: Either x x.
			
			Req 2: The x x.
			Req 2: the x x.
			Req 2: A x x.
			Req 2: a x x.
			Req 2: An x x.
			Req 2: an x x.
			
			Req 3: That x x.
			Req 3: that x x.
			Req 3: This x x.
			Req 3: this x x.
			
			Req 4: The "x" x.
			//Req 4: the 'x' x.
			
		''')
		
		/*
		 * Test of the Predicate rule
		 * 
		 * Req 1: Actor WORD WORD
		 * Req 2: Actor STRING
		 * Req 3: Actor WORD PredicateObject
		 * Req 4: Actor WORD WORD PredicateObject
		 */ 
		testSequence('''
			Req 1: the x x x.
			Req 2: any x "x".
			//Req 2: a x 'x'.
			Req 3: That x x a x.
			Req 4: an x x x a x.
			
		''')
		
		/*
		 * Test of the PredicateObject rule
		 * 
		 * Req 1: Actor Predicate Quantification WORD
		 * Req 2: Actor Predicate Articles WORD
		 * Req 3: Actor Predicate RefArticles WORD
		 * Req 4: Actor Predicate PreNominative WORD WORD
		 * Req 5: Actor Predicate PreNominative STRING
		 */ 
		testSequence('''
			Req 1: x x all x.
			Req 2: x x the x.
			Req 3: x x this x.
			Req 4: x x a x x.
			Req 5: x x a "x".
			//Req 5: x x a 'x'.
			
		''')
		
		/*
		 * Test of the ActorProperties rule
		 * property+=ObjectProperty rela+=Relation? (conjunction+=Conjunction property+=ObjectProperty rela+=Relation?)* 
		 * Req 1: ObjectProperty 
		 * Req 2: ObjectProperty Relation
		 * Req 3: ObjectProperty Conjunction ObjectProperty
		 * Req 3: ObjectProperty Relation Conjunction ObjectProperty
		 * Req 4: ObjectProperty Conjunction ObjectProperty Relation
		 * Req 4: ObjectProperty Relation Conjunction ObjectProperty Relation
		 */ 
		testSequence('''
			Req 1: x´s x must x.
			Req 2: x´s x in relation to x must x.
			Req 3: x´s x and x´s x must x.
			Req 3: x´s x in relation to x or x´s x must x.
			Req 4: x´s x and x´s x in relation to x must x.
			Req 4: x´s x in relation to x or x´s x in relation to x must x.
		''')
		
		/*
		 * Test of the Property rule
		 * 
		 * Req 1: Actors PROPERTY_TERM WORD WORD Modality Predicate
		 * Req 2: Actors PROPERTY_TERM STRING Modality Predicate
		 */ 
		testSequence('''
			Req 1: x´s x x must x.
			Req 1: x´ x x must x.
			Req 1: x' x x must x.
			Req 1: x`x x must x.
			Req 2: x´s "x" must x.
			Req 2: x´ "x" must x.
			Req 2: x' "x" must x.
			Req 2: x`"x" must x.
			//Req 2: x´s 'x' must x.
			
		''')
		
		/*
		 * Test of the SentenceEnding rule
		 * 
		 * Req 1: Actors Predicate Constraints Constraints
		 * Req 2: Actors Predicate Constraints Relation
		 * Req 3: Actors Predicate Constraints Constraints Relation
		 * Req 4: Actors Predicate Relation
		 * Req 6: Actors Predicate Relation Constraints Constraints
		 */ 
		testSequence('''
			Req 1: x x in x to x.
			Req 2: x x in x in relation to x.
			Req 3: x x in x to x in relation to x.
			Req 4: x x in relation to x.
			Req 6: x x in relation to x in x to x.
			
		''')
		
		/*
		 * Test of the Object rule
		 * 
		 * Req 1: Actor Predicate WORD WORD
		 * Req 2: Actor Predicate Quantification WORD WORD
		 * Req 3: Actor Predicate Articles WORD WORD
		 * Req 4: Actor Predicate RefArticles WORD WORD
		 * Req 5: Actor Predicate PreNominative STRING
		 */ 
		testSequence('''
			Req 1: x x x x.
			Req 2: x x all x x.
			Req 3: x x the x x.
			Req 4: x x this x x.
			Req 5: x x a "x".
			//Req 5: x x a 'x'.
			
		''')
		
		/*
		 * Test of the SentenceBegin rule
		 * 
		 * Req : Relation, Actor Predicate
		 */ 
		testSequence('''
			Req : in relation to x, x x.
			//: In relation to x, x x.
			
		''')
		
		/*
		 * Test of the Object rule
		 * Req 1: Actor Predicate Adverbial PreNominative WORD
		 * Req 2: Actor Predicate Adverbial WORD
		 * Req 3: Actor Predicate Adverbial PreNominative STRING
		 * Req 4: Actor Predicate Adverbial STRING
		 * Req 5: PositionAdverbial RelationDelimiter Comparators Object, Actor Predicate
		 */
		testSequence('''
			Req 1: x x in the x.
			Req 2: x x in x.
			Req 3: x x in the "x".
			//Req 3: x x in the 'x'.
			Req 4: x x in "x".
			//Req 4: x x in 'x'.
			Req 5: in relation to the x, x x.
			Req 5: in relation to x, x x.
			Req 5: in relation to the "x", x x.
			//Req 5: in relation to the 'x', x x.
			Req 5: in relation to "x", x x.
			//Req 5: in relation to 'x', x x.
			
		''')
		
		/*
		 * Test of the ExistencePreface rule
		 * 
		 * Req 1: 'There' 'exist' Actors, relativeClause
		 * Req 2: 'there' 'exist' Actors, relativeClause
		 * Req 3: 'There' 'exists' Actors, relativeClause
		 * Req 4: 'there' 'exists' Actors, relativeClause
		 * Req 5: 'T/there' Modifier 'exist/s' Actors, relativeClause
		 */ 
		testSequence('''
			Req 1: There exist x, who x.
			Req 2: there exist x, who x.
			Req 3: There exists x, who x.
			Req 4: there exists x, who x.
			Req 5: There always exist x, who x.
			Req 5: there always exist x, who x.
			Req 5: There always exists x, who x.
			Req 5: there always exists x, who x.
			
		''')
		
		/*
		 * Test of the AuxNeg rule
		 * 
		 * Req 1: Actor AuxiliaryVerb Predicate
		 * Req 2: Actor AuxiliaryVerb Negation Predicate
		 * Req 3: Actor AuxiliaryVerbNegation Predicate
		 * Req 4: Actors Property AuxNeg
		 */ 
		testSequence('''
			Req 1: x is x.
			Req 2: x is not x.
			Req 3: x doesn't x.
			Req 3: x don't x.
			Req 3: x isn't x.
			Req 3: x aren't x.
			Req 4: x's x is x.
			Req 4: x's x is not x.
			Req 4: x's x doesn't x.
			
		''')
		
		/*
		 * Test of the Relation rule
		 * 
		 * Req : Actor Predicate PositionAdverbial RelationDelimiter Comparators RelObjects
		 */ 
		testSequence('''
			Req : x x in relation to x.
			
		''')
		
		
		/*
		 * Test of the RelObjects rule
		 * 
		 * Req 1: Actor Predicate PositionAdverbial RelationDelimiter Comparators Object
		 * Req 2: Actor Predicate PositionAdverbial RelationDelimiter Comparators Object Property
		 * Req 3: Actor Predicate PositionAdverbial RelationDelimiter Comparators Object Rel_Conjunction Object
		 * Req 4: Actor Predicate PositionAdverbial RelationDelimiter Comparators Object Property Rel_Conjunction Object
		 * Req 5: Actor Predicate PositionAdverbial RelationDelimiter Comparators Object Rel_Conjunction Object Property
		 * Req 6: Actor Predicate PositionAdverbial RelationDelimiter Comparators Object Property Rel_Conjunction Object Property
		 * Req 7: Actor Predicate PositionAdverbial RelationDelimiter Comparators Object Rel_Conjunction Object Rel_Conjunction Object
		 */
		testSequence('''
			Req 1: x x in relation to x.
			Req 2: x x in relation to x´s x.
			Req 3: x x in relation to x and to x.
			Req 3: x x in relation to x or to x.
			Req 4: x x in relation to x´s x and to x.
			Req 4: x x in relation to x´s x or to x.
			Req 5: x x in relation to x and to x´s x.
			Req 5: x x in relation to x or to x´s x.
			Req 6: x x in relation to x´s x and to x´s x.
			Req 6: x x in relation to x´s x or to x´s x.
			Req 7: x x in relation to x and to x or to x.
			
		''')
		
		/*
		 * Test of the Constraints rule
		 * 
		 * Req 1: ObjectConstraint and ObjectConstraint
		 * Req 2: UnitConstraints and ObjectConstraint
		 * Req 3: SetConstraint and ObjectConstraint
		 * Req 3: TimeConstraint and ObjectConstraint
		 */ 
		testSequence('''
			Req 1: x x in x and in x.
			Req 2: x x in 1.0 and in x.
			Req 3: x x in {x} and in x.
			Req 4: x x in 1 sec and in x to x.
			
		''')
		
		/*
		 * Test of the Constraint rule
		 * 
		 * Req 1: Actor Predicate ConstraintOrdinators ObjectConstraint
		 * Req 2: Actor Predicate ConstraintOrdinators UnitConstraints
		 * Req 3: Actor Predicate ConstraintOrdinators SetConstraint
		 */ 
		testSequence('''
			Req 1: x x in x.
			Req 2: x x in 1.0.
			Req 3: x x in {x}.
			
		''')
		
		/*
		 * Test of the ConstraintOrdinator rule
		 * 
		 * Req 1: Actor Predicate SizeAdverbial ObjectConstraint
		 * Req 2: Actor Predicate PositionAdverbial ObjectConstraint
		 * Req 3: Actor Predicate ComparisonAdverbial ObjectConstraint
		 * Req 4: Actor Predicate Adverbial Comparators ObjectConstraint 
		 * Req 5: Actor Predicate SuffWord Adverbial ObjectConstraint 
		 * Req 6: Actor Predicate SuffWord Adverbial Comparators ObjectConstraint 
		 */  
		testSequence('''
			Req 1: x x higher x.
			Req 1: x x less x.
			Req 1: x x more x.
			Req 1: x x larger x.
			Req 1: x x smaller x.
			Req 1: x x as_long_as x.
			
			Req 2: x x between x.
			Req 2: x x next x.
			Req 2: x x on x.
			Req 2: x x above x.
			Req 2: x x in x.
			Req 2: x x within x.
			Req 2: x x in_front_of x.
			Req 2: x x behind x.
			Req 2: x x out x.
			Req 2: x x under x.
			
			Req 3: x x equal x.
			Req 3: x x faster x.
			Req 3: x x slower x.
			Req 3: x x better x.
			Req 3: x x by x.
			Req 3: x x to x.
			
			Req 4: x x higher than x.
			Req 4: x x higher as x.
			Req 4: x x higher to x.
			Req 4: x x higher of x.
			
			Req 5: x x with equal x.
			Req 6: x x with more than x.
			
		''')
		
		/*
		 * Test of the SetConstraint rule
		 * 
		 * Req 1: Actor Predicate ConstraintOrdinators {Actor}
		 * Req 2: Actor Predicate ConstraintOrdinators {Actor; Actor}
		 * Req 3: Actor Predicate ConstraintOrdinators {Actor; Actor; Actor}
		 * Req 4: Actor Predicate ConstraintOrdinators {PreNominative Actor; ...}
		 * Req 5: Actor Predicate ConstraintOrdinators {IntValue; ...}
		 * Req 6: Actor Predicate ConstraintOrdinators {FloatValue; ...}
		 * Req 7: Actor Predicate ConstraintOrdinators {INT/FLOAT Unit; ...}
		 */ 
		testSequence('''
			Req 1: x x in {x}.
			Req 2: x x in {x; x}.
			Req 3: x x in {x; x; x}.
			Req 4: x x in {a x}.
			Req 4: x x in {the x; x}.
			Req 4: x x in {all x; a x; the x}.
			Req 5: x x in {1}.
			Req 5: x x in {1; 1}.
			Req 5: x x in {1; 1; 1}.
			Req 6: x x in {1.1}.
			Req 6: x x in {1.1; 1.1}.
			Req 6: x x in {1.1; 1.0; 1}.
			Req 7: x x in {1m}.
			Req 7: x x in {1m; 1m}.
			Req 7: x x in {1m; 10m; 100m}.
			Req 7: x x in {1.1m}.
			Req 7: x x in {1.1m; 1.1m}.
			Req 7: x x in {1.1m; 10.0m; 100m}.
			
		''')
		
		/*
		 * Test of the TimeConstraint rule
		 * Req : Actor Predicate ConstraintOrdinators INT TimeUnits
		 */
		testSequence('''
			Req : x x in 1 ns.
			Req : x x in 1 ms.
			Req : x x in 1 s.
			Req : x x in 1 sec.
			Req : x x in 1 second.
			Req : x x in 1 minute.
			Req : x x in 1 minutes.
			Req : x x in 1 min.
			Req : x x in 1 hour.
			Req : x x in 1 h.
			Req : x x in 1 day.
			Req : x x in 1 days.
			Req : x x in 1 d.
			Req : x x in 1 month.
			Req : x x in 1 months.
			Req : x x in 1 mon.
			Req : x x in 1 year.
			Req : x x in 1 years.
			Req : x x in 1 y.
			Req : x x in 10 ns.
			
		''')
		
		/* 
		 * Test of the IntervallConstraints rule
		 * 
		 * Req : Actor Predicate ConstraintOrdinators [Value, Value]
		 */ 
		testSequence('''
			Req : x x in [1,2].
			Req : x x in [1m, 2m].
			Req : x x in [1m, 2].
			Req : x x in [1,2m].
			Req : x x in [1.1,2.2].
			Req : x x in [1.0m, 2m].
			Req : x x in [1.4m, 2].
			Req : x x in [1,2.5m].
			
		''')
		
		/* 
		 * Test of the SingleValueConstraints rule
		 * 
		 * Req 1: Actor Predicate ConstraintOrdinators IntValue
		 * Req 2: Actor Predicate ConstraintOrdinators FloatValue
		 */
		testSequence('''
			Req 1: x x in 1.
			Req 1: x x in 1 .
			Req 1: x x in 1m.
			Req 1: x x in 1 m.
			Req 1: x x in 1.1.
			Req 2: x x in 1.1 .
			Req 2: x x in 1.1m.
			Req 2: x x in 1.1 m.
			Req 2: x x in 10.11.
			
		''')
		
		/* 
		 * Test of the Value rule
		 * 
		 * Req 1: Actor Predicate ConstraintOrdinators INT LengthUnits
		 * Req 2: Actor Predicate ConstraintOrdinators INT PresureUnits
		 * Req 3: Actor Predicate ConstraintOrdinators INT HeatUnits
		 * Req 4: Actor Predicate ConstraintOrdinators INT MassUnits
		 * Req 5: Actor Predicate ConstraintOrdinators INT VelocityUnits
		 * Req 6: Actor Predicate ConstraintOrdinators INT Cuvature
		 * Req 7: Actor Predicate ConstraintOrdinators INT/FLOAT WORD
		 * Req 8: Actor Predicate ConstraintOrdinators INT/FLOAT Unit WORD
		 * Req 9: Actor Predicate ConstraintOrdinators INT/FLOAT WORD WORD WORD
		 * Req 10: Actor Predicate ConstraintOrdinators INT/FLOAT Unit WORD WORD WORD
		 */
		testSequence('''
			Req 1: x x in 1 m.
			Req 1: x x in 1 f.
			Req 1: x x in 1 km.
			Req 1: x x in 1 cm.
			Req 1: x x in 1 mm.
			Req 1: x x in 1 nm.
			
			Req 2: x x in 1 bar.
			Req 2: x x in 1 Pa.
			Req 2: x x in 1 hPa.
			
			Req 3: x x in 1 C.
			Req 3: x x in 1 F.
			
			Req 4: x x in 1 kg.
			Req 4: x x in 1 g.
			Req 4: x x in 1 mg.
			Req 4: x x in 1 t.
			
			Req 5: x x in 1 m/s.
			Req 5: x x in 1 knots.
			Req 5: x x in 1 km/h.
			Req 5: x x in 1 m/min.
			
			Req 6: x x in 1 rad/m.
			Req 6: x x in 1°.
			Req 6: x x in 1 rad.
			Req 6: x x in 1 °/m.
			
			Req 7: x x in 1 x.
			Req 7: x x in 1.2 x.
			Req 8: x x in 1 m x.
			Req 8: x x in 1.2 m x.
			Req 9: x x in 1 x x x.
			Req 9: x x in 1.1 x x x.
			Req 10: x x in 1 m x x x.
			Req 10: x x in 1.2 m x x x.
			
		''')
		
		/* 
		 * Test of the ReqID rule
		 * 
		 * Req ID: Actor Predicate.
		 * Req ID.: Actor Predicate.
		 * Req ID INT: Actor Predicate.
		 * Req ID.INT: Actor Predicate.
		 * Req ID.INT INT: Actor Predicate.
		 * Req ID.INT INT INT: Actor Predicate.
		 * Req ID.INT.INT: Actor Predicate.
		 * Req INT: Actor Predicate.
		 * Req INT.INT: Actor Predicate.
		 * Req INT INT INT: Actor Predicate.
		 * Req INT INT.INT INT: Actor Predicate.
		 */
		testSequence('''
			Req x: x x.
			Req x.: x x.
			Req x1: x x.
			Req x 1 : x x.
			Req x.1: x x.
			Req x.11: x x.
			Req x.111: x x.
			Req x.1.1: x x.
			Req x.1 .1: x x.
			Req 1: x x.
			Req 1.1: x x.
			Req 1 .1: x x.
			Req 111: x x.
			Req 11 .11: x x.
			
		''')
		
		/*
		 * Test of the WORD rule
		 */ 
		testSequence('''
			Req x-x: x x.
			
			Req b: x x.
			Req B: x x.
			Req _: x x.
			
			Req aa: x x.
			Req aA: x x.
			Req a_: x x.
			Req a-: x x.
			Req a1: x x.
			
			Req Aa: x x.
			Req AA: x x.
			Req A_: x x.
			Req A-: x x.
			Req A1: x x.
			
			Req _a: x x.
			Req _A: x x.
			Req __: x x.
			Req _-: x x.
			Req _0: x x.
			
			Req ^a: x x.
			Req ^A: x x.
			Req ^_: x x.
			
		''')
		
		/*
		 * Test of the x rule
		 */ 
		testSequence('''
			Req : x x.
			
		''')
		
	}
	
	def void testSequence(CharSequence seq) {
		val result = parseHelper.parse(seq)
		Assert.assertNotNull(result)
		val errors = result.eResource.errors
		Assert.assertTrue('''Unexpected errors: «errors.join(", ")»''', errors.isEmpty)
	}
}
