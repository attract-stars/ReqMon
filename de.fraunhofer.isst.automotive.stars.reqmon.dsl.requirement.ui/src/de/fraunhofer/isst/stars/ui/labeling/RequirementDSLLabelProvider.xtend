/*
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars.ui.labeling

import com.google.inject.Inject
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider
import de.fraunhofer.isst.stars.requirementDSL.Requirement
import de.fraunhofer.isst.stars.requirementDSL.RequirementText
import de.fraunhofer.isst.stars.requirementDSL.ConditionalClause
import de.fraunhofer.isst.stars.requirementDSL.MainClause
import de.fraunhofer.isst.stars.requirementDSL.Clauses
import de.fraunhofer.isst.stars.requirementDSL.Clause
import de.fraunhofer.isst.stars.requirementDSL.ModalitySentence
import de.fraunhofer.isst.stars.requirementDSL.PredicateSentence
import de.fraunhofer.isst.stars.requirementDSL.ExistenceSentence
import de.fraunhofer.isst.stars.requirementDSL.PropertySentence
import de.fraunhofer.isst.stars.requirementDSL.Actors
import de.fraunhofer.isst.stars.requirementDSL.Actor
import de.fraunhofer.isst.stars.requirementDSL.Object
import de.fraunhofer.isst.stars.requirementDSL.Predicate
import de.fraunhofer.isst.stars.requirementDSL.PredicateObject
import de.fraunhofer.isst.stars.requirementDSL.PreNominative
import de.fraunhofer.isst.stars.requirementDSL.Constraint
import de.fraunhofer.isst.stars.requirementDSL.ConstraintOrdinators
import de.fraunhofer.isst.stars.requirementDSL.SetConstraint
import de.fraunhofer.isst.stars.requirementDSL.TimeConstraint
import de.fraunhofer.isst.stars.requirementDSL.ObjectConstraint
import de.fraunhofer.isst.stars.requirementDSL.UnitConstraints
import de.fraunhofer.isst.stars.requirementDSL.IntervallConstraints
import de.fraunhofer.isst.stars.requirementDSL.SingleValueConstraints
import de.fraunhofer.isst.stars.requirementDSL.ValueSet
import de.fraunhofer.isst.stars.requirementDSL.ObjectSet
import de.fraunhofer.isst.stars.requirementDSL.IntValue
import de.fraunhofer.isst.stars.requirementDSL.FloatValue
import de.fraunhofer.isst.stars.requirementDSL.Constraints
import de.fraunhofer.isst.stars.requirementDSL.Property
import de.fraunhofer.isst.stars.requirementDSL.SentenceBegin
import de.fraunhofer.isst.stars.requirementDSL.SentenceEnding
import de.fraunhofer.isst.stars.requirementDSL.RelObjects
import de.fraunhofer.isst.stars.requirementDSL.AuxNeg
import de.fraunhofer.isst.stars.requirementDSL.Relation
import org.eclipse.emf.common.util.EList
import de.fraunhofer.isst.stars.requirementDSL.Conjunction
import de.fraunhofer.isst.stars.requirementDSL.ActorProperties
import de.fraunhofer.isst.stars.requirementDSL.ObjectProperty

/**
 * Provides labels for EObjects.
 * 
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#label-provider
 */
class RequirementDSLLabelProvider extends DefaultEObjectLabelProvider {

	@Inject
	new(AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}

	// Labels and icons can be computed like this:
	
//	def text(Greeting ele) {
//		'A greeting to ' + ele.name
//	}
//
//	def image(Greeting ele) {
//		'Greeting.gif'
//	}

	def text(Requirement ele) {
		if (ele.reqID !== null) {
			"Requirement: " + ele.reqID
		}
		else {
			"Requirement"
		}
	}
	
	def text(RequirementText ele) {
		"RequirementText"
	}
	
	def text(ConditionalClause ele) {
		if (ele.ordinator !== null) {
			"ConditionalClause: " + ele.ordinator.toString 
		}
		else {
			"ConditionalClause"
		}
	}
	
	def text(MainClause ele) {
		if (ele.modifier !== null) {  
			"MainClause: " + ele.modifier
		}
		else {
			"MainClause"
		}
	}
	
	def text(Clauses ele) {
		if (!ele.conjunction.empty) {
			"Clauses: " + ele.conjunction.getConjunctionString.toString
		}
		else {
			"Clauses"
		}
		
	}
	
	def String getGetConjunctionString(EList<Conjunction> list) {
		if (list.size === 1) {
			return '''«list.get(0).text»'''
		}
		else if (list.size === 2) {
			return '''«list.get(0).text», «list.get(1).text»'''
		}
		else {
			return '''«list.get(0).text», «FOR i : 1..< list.size-1»«list.get(i).text», «ENDFOR»«list.get(list.size-1).text»'''
		}
		
	}
	
	def text(Clause ele) {
		"Clause"
	}
	
	def text(ModalitySentence ele) {
		if (ele.modelity !== null) {
			if (ele.negation) {
				if (ele.auxiliarVerb !== null) {
					"ModalitySentence: " + ele.modelity + " not " + ele.auxiliarVerb
				}
				else {
					"ModalitySentence: " + ele.modelity + " not"
				}
			}
			else {
				if (ele.auxiliarVerb !== null) {
					"ModalitySentence: " + ele.modelity + " " + ele.auxiliarVerb
				}
				else {
					"ModalitySentence: " + ele.modelity
				}
			}
		}
		else {
			if (ele.negation) {
				if (ele.auxiliarVerb !== null) {
					"ModalitySentence: not " + ele.auxiliarVerb
				}
				else {
					"ModalitySentence: not"
				}
			}
			else {
				if (ele.auxiliarVerb !== null) {
					"ModalitySentence: " + ele.auxiliarVerb
				}
				else {
					"ModalitySentence"
				}
			}
		}
	}
	
	def text(PredicateSentence ele) {
		if (ele.auxiliarVerb !== null) {
			"PredicateSentence: " + ele.auxiliarVerb
		}
		else {
			"PredicateSentence"
		}
	}
	
	def text(ExistenceSentence ele) {
		"ExistenceSentence"
	}
	
	def text(PropertySentence ele) {
		if (ele.modality !== null) {
			"PropertySentence: " + ele.modality
		}
		else if (ele.modality !== null && ele.negation) {
			"PropertySentence: " + ele.modality + " not"
		} 
		else if (ele.auxiliarVerb !== null) {
			"PropertySentence: " + ele.auxiliarVerb
		} 
		else if (ele.auxiliarVerb !== null && ele.negation) {
			"PropertySentence: " + ele.auxiliarVerb + " not"
		} 
		else {
			"PropertySentence"
		}
	}
	
	def text(ActorProperties ap) {
		"ActorProperties"
	}
	
	def text(ObjectProperty op) {
		"ObjectProperty"
	}
	
	def text(Property ele) {
		if (!ele.property.empty) {
			"Property: " + ele.property.join(" ")
		}
		else {
			"Property"
		} 
	}
	
	def text(Actors ele) {
		if (!ele.conjunction.empty) {
			"Actors: " + ele.conjunction.getConjunctionString // + ele.actors.map[a | return a.actor]
		}
		else {
			"Actors" //+ ele.actors.get(0).actor
		}
	}
	
	def text(Actor ele) {
		"Actor: " + ele.preNominative.getPreNominativeString +" "+ ele.actor
	}
	
	def String getPreNominativeString(PreNominative nominative) {
		if (nominative.article !== null) {
			nominative.article
		}
		else if (nominative.determiner !== null) {
			nominative.determiner
		}
		else {
			""
		}
	}
	
	def text(Predicate ele) {
		if (ele.predicates !== null) {
			"Predicate: " + ele.predicates.join(" ")
		}
		else {
			"Predicate"
		}
	}
	
	def text(PredicateObject ele) {
		"PredicateObject: " + ele.object.join(" ")
	}
	
	def text(PreNominative ele) {
		if (ele.article !== null) {
			"Article: " + ele.article
		}
		else if (ele.determiner !== null) {
			"Determiner: " + ele.determiner
		}
		else {
			"PreNominative"
		}
	}
	
	def text(Object ele) {
		"Object: " + ele.object.join(" ")
	}
	
	def text(Constraint ele) {
		"Constraint"
	}
	
	def text(Constraints ele) {
		"Constraints"
	}
	
	def text(ConstraintOrdinators ele) {
		if (ele.stuffing !== null && ele.adverbial !== null && ele.comperator !== null) {
			"ConstraintOrdinators: " + ele.stuffing + " " + ele.adverbial + " " + ele.comperator
		}
		else if (ele.stuffing !== null && ele.adverbial !== null) {
			"ConstraintOrdinators: " + ele.stuffing + " " + ele.adverbial
		}	
		else if (ele.stuffing !== null) {
			"ConstraintOrdinators: " + ele.stuffing
		}
		else if (ele.adverbial !== null && ele.comperator !== null) {
			"ConstraintOrdinators: " + ele.adverbial + " " + ele.comperator
		}
		else if (ele.adverbial !== null) {
			"ConstraintOrdinators: " + ele.adverbial
		}
		else if (ele.stuffing !== null && ele.comperator !== null) {
			"ConstraintOrdinators: " + ele.stuffing + " " + ele.comperator
		}
		else if (ele.comperator !== null) {
			"ConstraintOrdinators: " + ele.comperator
		}
		else {
			"ConstraintOrdinators"
		}
	}
	
	def text(SetConstraint ele) {
		"SetConstraint"
	}
	
	def text(TimeConstraint ele) {
		"TimeConstraint"
	}
	
	def text(ObjectConstraint ele) {
		"ObjectConstraint"
	}
	
	def text(UnitConstraints ele) {
		"UnitConstraint"
	}
	
	def text(IntervallConstraints ele) {
		"IntervallConstraints"
	}
	
	def text(SingleValueConstraints ele) {
		"SingleValueConstraints"
	}
	
	def text(ValueSet ele) {
		"ValueSet"
	}
	
	def text(ObjectSet ele) {
		"ObjectSet"
	}
	
	def text(IntValue ele) {
		if (ele.unit !== null) {
			"IntValue: " + ele.value + " " + ele.unit
		}
		else {
			"IntValue"
		}
	}
	
	def text(FloatValue ele) {
		if (ele.unit !== null) {
			"FloatValue: " + ele.value + " " + ele.unit
		}
		else {
			"FloatValue"
		}
	}
	
	def text(SentenceBegin ele) {
		"SentenceBegin"
	}
	
	def text(SentenceEnding ele) {
		"SentenceEnding"
	}
	
	def text(Relation ele) {
		if (ele.relDel !== null) {
			"Relation: " + ele.relDel
		}
		else {
			"Relation" 
		}
	}
	
	def text(RelObjects ele) {
		"RelObjects" //+ ele.actor.map[a | return a.actor]
	}
	
	def text(AuxNeg ele) {
		if (ele.auxiliarVerb !== null) {
			"AuxNeg: " + ele.auxiliarVerb
		}
		else if (ele.auxiliarVerb !== null && ele.negation !== null) {
			"AuxNeg: " + ele.auxiliarVerb + " " + ele.negation
		}
		else if (ele.auxiliarVerbNeg !== null) {
			"AuxNeg: " + ele.auxiliarVerbNeg
		}
		else {
			"AuxNeg"
		}
	}

}
