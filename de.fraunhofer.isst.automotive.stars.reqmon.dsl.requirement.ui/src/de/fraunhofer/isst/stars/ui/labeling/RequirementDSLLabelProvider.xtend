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
import de.fraunhofer.isst.stars.requirementDSL.Modifier
import de.fraunhofer.isst.stars.requirementDSL.Modality
import de.fraunhofer.isst.stars.requirementDSL.Property
import de.fraunhofer.isst.stars.requirementDSL.RelObjects
import de.fraunhofer.isst.stars.requirementDSL.AuxNeg
import de.fraunhofer.isst.stars.requirementDSL.Relation
import de.fraunhofer.isst.stars.requirementDSL.RelationConstraint

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
		// the modifier is never null and as default "globally"
		if (ele.modifier !== null && !ele.modifier.equals(Modifier.GLOBALLY)) {  
			"MainClause: " + ele.modifier.toString
		}
		else {
			"MainClause"
		}
	}
	
	def text(Clauses ele) {
		if (!ele.conjunction.empty) {
			"Clauses: " + ele.conjunction
		}
		else {
			"Clauses"
		}
		
	}
	
	def text(Clause ele) {
		"Clause"
	}
	
	def text(ModalitySentence ele) {
		if (ele.modelity !== null) {
			if (ele.negation) {
				if (ele.auxiliarVerb !== null) {
					"ModalitySentence: " + ele.modelity.getName.toLowerCase + " not " + ele.auxiliarVerb
				}
				else {
					"ModalitySentence: " + ele.modelity.getName.toLowerCase + " not"
				}
			}
			else {
				if (ele.auxiliarVerb !== null) {
					"ModalitySentence: " + ele.modelity.getName.toLowerCase + " " + ele.auxiliarVerb
				}
				else {
					"ModalitySentence: " + ele.modelity.getName.toLowerCase
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
		if (!ele.auxiliarVerb.empty) {
			"PredicateSentence: " + String.join(" ",ele.auxiliarVerb)
		}
		else {
			"PredicateSentence"
		}
	}
	
	def text(ExistenceSentence ele) {
		"ExistenceSentence"
	}
	
	def text(PropertySentence ele) {
		// the modifier is never null and as default "shall"
		if (ele.modality !== null && !ele.modality.equals(Modality.SHALL)) {
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
			"Actors: " + ele.conjunction // + ele.actors.map[a | return a.actor]
		}
		else {
			"Actors" //+ ele.actors.get(0).actor
		}
	}
	
	def text(Actor ele) {
		"Actor: " + ele.preNominative +" "+ ele.actor
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
		else if (ele.article !== null) {
			"Determiner: " + ele.article
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
	
	def text(RelationConstraint ele) {
		"RelationConstraint"
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
