/**
 * 
 */
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.eclipse.emf.common.util.EList;

import com.google.common.collect.Lists;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement;
import de.fraunhofer.isst.stars.requirementDSL.Actor;
import de.fraunhofer.isst.stars.requirementDSL.Actors;
import de.fraunhofer.isst.stars.requirementDSL.AuxNeg;
import de.fraunhofer.isst.stars.requirementDSL.Clause;
import de.fraunhofer.isst.stars.requirementDSL.Clauses;
import de.fraunhofer.isst.stars.requirementDSL.ConditionalClause;
import de.fraunhofer.isst.stars.requirementDSL.Constraint;
import de.fraunhofer.isst.stars.requirementDSL.ConstraintOrdinators;
import de.fraunhofer.isst.stars.requirementDSL.Constraints;
import de.fraunhofer.isst.stars.requirementDSL.ExistencePreface;
import de.fraunhofer.isst.stars.requirementDSL.ExistenceSentence;
import de.fraunhofer.isst.stars.requirementDSL.FloatValue;
import de.fraunhofer.isst.stars.requirementDSL.IntValue;
import de.fraunhofer.isst.stars.requirementDSL.IntervallConstraints;
import de.fraunhofer.isst.stars.requirementDSL.MainClause;
import de.fraunhofer.isst.stars.requirementDSL.ModalitySentence;
import de.fraunhofer.isst.stars.requirementDSL.Model;
import de.fraunhofer.isst.stars.requirementDSL.Object;
import de.fraunhofer.isst.stars.requirementDSL.ObjectConstraint;
import de.fraunhofer.isst.stars.requirementDSL.ObjectSet;
import de.fraunhofer.isst.stars.requirementDSL.PreNominative;
import de.fraunhofer.isst.stars.requirementDSL.PredOrObject;
import de.fraunhofer.isst.stars.requirementDSL.Predicate;
import de.fraunhofer.isst.stars.requirementDSL.PredicateObject;
import de.fraunhofer.isst.stars.requirementDSL.PredicateSentence;
import de.fraunhofer.isst.stars.requirementDSL.Preds;
import de.fraunhofer.isst.stars.requirementDSL.Property;
import de.fraunhofer.isst.stars.requirementDSL.PropertySentence;
import de.fraunhofer.isst.stars.requirementDSL.RelObjects;
import de.fraunhofer.isst.stars.requirementDSL.Relation;
import de.fraunhofer.isst.stars.requirementDSL.Requirement;
import de.fraunhofer.isst.stars.requirementDSL.RequirementText;
import de.fraunhofer.isst.stars.requirementDSL.SentenceBegin;
import de.fraunhofer.isst.stars.requirementDSL.SentenceEnding;
import de.fraunhofer.isst.stars.requirementDSL.SetConstraint;
import de.fraunhofer.isst.stars.requirementDSL.SingleValueConstraints;
import de.fraunhofer.isst.stars.requirementDSL.TimeConstraint;
import de.fraunhofer.isst.stars.requirementDSL.UnitConstraints;
import de.fraunhofer.isst.stars.requirementDSL.Value;
import de.fraunhofer.isst.stars.requirementDSL.ValueSet;
import de.fraunhofer.isst.stars.requirementDSL.relativeClause;
import de.fraunhofer.isst.stars.requirementDSL.relativeSentence;
import de.fraunhofer.isst.stars.requirementDSL.util.RequirementDSLSwitch;

/**
 * @author mmauritz
 *
 */
public class SemanticTextElementSwitch extends RequirementDSLSwitch<SemanticTextElement> {
	
	//Lookup for existing elements
	private HashMap<String, SemanticTextElement> elementlookup;

	public SemanticTextElementSwitch() {
		this.elementlookup = new HashMap<String, SemanticTextElement>();
	}
	
	public List<SemanticTextElement> getSingleSemanticTextElements() {
		return Lists.newArrayList(elementlookup.values());
	}

	public Map<String, SemanticTextElement> getLookup(){
		return elementlookup;
	}
	
	@Override
	public SemanticTextElement caseActor(Actor object) {
		System.out.println("Analyzing: " + object.toString());
		String actor = object.getActor();
		if (actor != null && !actor.isEmpty()) {
			if (elementlookup.containsKey(actor)) {
				return elementlookup.get(actor);
			} else {
				SemanticTextElement texElement = new SemanticTextElement(actor, RequirementType.OBJECT);
				elementlookup.put(actor, texElement);
				return texElement;
			}
		}
		return super.caseActor(object);
	}
	
	@Override
	public SemanticTextElement caseActors(Actors object) {
		// TODO Auto-generated method stub
		return super.caseActors(object);
	}
	
	@Override
	public SemanticTextElement caseAuxNeg(AuxNeg object) {
		// TODO Auto-generated method stub
		return super.caseAuxNeg(object);
	}
	
	@Override
	public SemanticTextElement caseClause(Clause object) {
		// TODO Auto-generated method stub
		return super.caseClause(object);
	}
	
	@Override
	public SemanticTextElement caseClauses(Clauses object) {
		// TODO Auto-generated method stub
		return super.caseClauses(object);
	}
	
	@Override
	public SemanticTextElement caseConditionalClause(ConditionalClause object) {
		// TODO Auto-generated method stub
		return super.caseConditionalClause(object);
	}
	

	@Override
	public SemanticTextElement caseConstraint(Constraint object) {
		// TODO Auto-generated method stub
		return super.caseConstraint(object);
	}
	
	@Override
	public SemanticTextElement caseConstraintOrdinators(ConstraintOrdinators object) {
		// TODO Auto-generated method stub
		return super.caseConstraintOrdinators(object);
	}
	
	@Override
	public SemanticTextElement caseConstraints(Constraints object) {
		// TODO Auto-generated method stub
		return super.caseConstraints(object);
	}
	
	@Override
	public SemanticTextElement caseExistencePreface(ExistencePreface object) {
		// TODO Auto-generated method stub
		return super.caseExistencePreface(object);
	}
	
	@Override
	public SemanticTextElement caseExistenceSentence(ExistenceSentence object) {
		// TODO Auto-generated method stub
		return super.caseExistenceSentence(object);
	}
	
	@Override
	public SemanticTextElement caseFloatValue(FloatValue object) {
		// TODO Auto-generated method stub
		return super.caseFloatValue(object);
	}
	
	@Override
	public SemanticTextElement caseIntervallConstraints(IntervallConstraints object) {
		// TODO Auto-generated method stub
		return super.caseIntervallConstraints(object);
	}
	
	
	@Override
	public SemanticTextElement caseIntValue(IntValue object) {
		// TODO Auto-generated method stub
		return super.caseIntValue(object);
	}
	
	@Override
	public SemanticTextElement caseMainClause(MainClause object) {
		// TODO Auto-generated method stub
		return super.caseMainClause(object);
	}
	
	@Override
	public SemanticTextElement caseModalitySentence(ModalitySentence object) {
		// TODO Auto-generated method stub
		return super.caseModalitySentence(object);
	}
	
	@Override
	public SemanticTextElement caseModel(Model object) {
		// TODO Auto-generated method stub
		return super.caseModel(object);
	}
	
	@Override
	public SemanticTextElement caseObject(Object object) {
		System.out.println("Analyzing: " + object.toString());
		if (!(object.getObject().isEmpty())) {
			StringJoiner objTxt = new StringJoiner(" ");
			for (String text : object.getObject()) {
				// the text object lists all words for the real meaning these word have to be
				// concatenated
				objTxt.add(text);
			}
			if (elementlookup.containsKey(objTxt.toString())) {
				return elementlookup.get(objTxt.toString());
			} else {
				SemanticTextElement texElement = new SemanticTextElement(objTxt.toString(),
						RequirementType.OBJECT);
				elementlookup.put(objTxt.toString(), texElement);
				return texElement;
			}
		}
		return super.caseObject(object);
	}
	
	@Override
	public SemanticTextElement caseObjectConstraint(ObjectConstraint object) {
		// TODO Auto-generated method stub
		return super.caseObjectConstraint(object);
	}
	
	@Override
	public SemanticTextElement caseObjectSet(ObjectSet object) {
		// TODO Auto-generated method stub
		return super.caseObjectSet(object);
	}
	
	@Override
	public SemanticTextElement casePredicate(Predicate object) {
		// TODO Auto-generated method stub
		return super.casePredicate(object);
	}
	
	@Override
	public SemanticTextElement casePredicateObject(PredicateObject object) {
		System.out.println("Analyzing: " + object.toString());
		if (!(object.getObject().isEmpty())) {
			StringJoiner objTxt = new StringJoiner(" ");
			for (String text : object.getObject()) {
				// the text object lists all words for the real meaning these word have to be
				// concatenated
				objTxt.add(text);
			}
			if (elementlookup.containsKey(objTxt.toString())) {
				return elementlookup.get(objTxt.toString());
			} else {
				SemanticTextElement texElement = new SemanticTextElement(objTxt.toString(),
						RequirementType.OBJECT);
				elementlookup.put(objTxt.toString(), texElement);
				return texElement;
			}
		}
		return super.casePredicateObject(object);
	}
	
	@Override
	public SemanticTextElement casePredicateSentence(PredicateSentence object) {
		// TODO Auto-generated method stub
		return super.casePredicateSentence(object);
	}
	
	@Override
	public SemanticTextElement casePredOrObject(PredOrObject object) {
		// TODO Auto-generated method stub
		return super.casePredOrObject(object);
	}
	
	@Override
	public SemanticTextElement casePreds(Preds object) {
		// TODO Auto-generated method stub
		return super.casePreds(object);
	}
	
	@Override
	public SemanticTextElement casePreNominative(PreNominative object) {
		// TODO Auto-generated method stub
		return super.casePreNominative(object);
	}
	
	//TODO WIR BRAUCHEN EIN BESSERES OBJECT IN DER SPRACHE FÜR OBJECT + PROPERTY denn eig ist das eine Einheit
	@Override
	public SemanticTextElement caseProperty(Property object) {
		System.out.println("Analyzing: " + object.toString());
		if (!(object.getProperty().isEmpty())) {
			StringJoiner objTxt = new StringJoiner(" ");
			for (String text : object.getProperty()) {
				// the text object lists all words for the real meaning these word have to be
				// concatenated
				objTxt.add(text);
			}
			//adding additional text for better Understand -> The preceeding object
			String prefix ="";
			if(object.eContainer() instanceof  PropertySentence) {
				PropertySentence sentence = (PropertySentence) object.eContainer();
				EList<Actor> actors = sentence.getActors().getActors();
				Actor lastActor = actors.get(actors.size()-1);
				prefix = lastActor.getActor()+"`s";
			}
			if(object.eContainer() instanceof  RelObjects) {
				RelObjects relObjects = (RelObjects) object.eContainer();
				EList<Object> objects = relObjects.getObject();
				Object lastObject = objects.get(objects.size()-1);
				StringJoiner obj2Text = new StringJoiner(" ");
				for (String str : lastObject.getObject()) {
					// the text object lists all words for the real meaning these word have to be
					// concatenated
					obj2Text.add(str);
				}
				prefix= obj2Text.toString();
			}
			String text=!prefix.isEmpty()?prefix+" "+objTxt.toString() : objTxt.toString();
			//TODO
			if (elementlookup.containsKey(text)) {
				return elementlookup.get(text);
			} else {
				SemanticTextElement texElement = new SemanticTextElement(text,
						RequirementType.FUNCTION);
				elementlookup.put(text, texElement);
				return texElement;
			}
		}
		return super.caseProperty(object);
	}
	
	@Override
	public SemanticTextElement casePropertySentence(PropertySentence object) {
		// TODO Auto-generated method stub
		return super.casePropertySentence(object);
	}
	
	@Override
	public SemanticTextElement caseRelation(Relation object) {
		// TODO Auto-generated method stub
		return super.caseRelation(object);
	}
	
	@Override
	public SemanticTextElement caserelativeClause(relativeClause object) {
		// TODO Auto-generated method stub
		return super.caserelativeClause(object);
	}
	
	@Override
	public SemanticTextElement caserelativeSentence(relativeSentence object) {
		// TODO Auto-generated method stub
		return super.caserelativeSentence(object);
	}
	
	@Override
	public SemanticTextElement caseRelObjects(RelObjects object) {
		// TODO Auto-generated method stub
		return super.caseRelObjects(object);
	}
	
	@Override
	public SemanticTextElement caseRequirement(Requirement object) {
		// TODO Auto-generated method stub
		return super.caseRequirement(object);
	}
	
	@Override
	public SemanticTextElement caseRequirementText(RequirementText object) {
		// TODO Auto-generated method stub
		return super.caseRequirementText(object);
	}
	
	@Override
	public SemanticTextElement caseSentenceBegin(SentenceBegin object) {
		// TODO Auto-generated method stub
		return super.caseSentenceBegin(object);
	}
	
	@Override
	public SemanticTextElement caseSentenceEnding(SentenceEnding object) {
		// TODO Auto-generated method stub
		return super.caseSentenceEnding(object);
	}
	
	@Override
	public SemanticTextElement caseSetConstraint(SetConstraint object) {
		// TODO Auto-generated method stub
		return super.caseSetConstraint(object);
	}
	
	@Override
	public SemanticTextElement caseSingleValueConstraints(SingleValueConstraints object) {
		// TODO Auto-generated method stub
		return super.caseSingleValueConstraints(object);
	}
	
	@Override
	public SemanticTextElement caseTimeConstraint(TimeConstraint object) {
		// TODO Auto-generated method stub
		return super.caseTimeConstraint(object);
	}
	
	@Override
	public SemanticTextElement caseUnitConstraints(UnitConstraints object) {
		// TODO Auto-generated method stub
		return super.caseUnitConstraints(object);
	}
	
	@Override
	public SemanticTextElement caseValue(Value object) {
		// TODO Auto-generated method stub
		return super.caseValue(object);
	}
	
	@Override
	public SemanticTextElement caseValueSet(ValueSet object) {
		// TODO Auto-generated method stub
		return super.caseValueSet(object);
	}


	//HERE YOU HAVE TO ADD RELEVANT MODELL ELEMENTS FOR VISITING THEM

}
