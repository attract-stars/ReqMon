/**
 * 
 */
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

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
import de.fraunhofer.isst.stars.requirementDSL.Existence;
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
import de.fraunhofer.isst.stars.requirementDSL.RelativeClause;
import de.fraunhofer.isst.stars.requirementDSL.RelativeSentence;
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
import de.fraunhofer.isst.stars.requirementDSL.util.RequirementDSLSwitch;

/**
 * @author mmauritz
 *
 */
//TODO HIER KANN MAN DIE TEXT ZUSAMMENBAU DURCH WEITERE SWITCH KLASSE ERSETZEN!
public class SemanticTextElementSwitch extends RequirementDSLSwitch<SemanticTextElement> {

	//Lookup for existing elements
	private HashMap<String, SemanticTextElement> elementLookup;
	private SemanticStringSwitch semanticStringSwitch;

	public SemanticTextElementSwitch() {
		this.elementLookup = new HashMap<String, SemanticTextElement>();
		this.semanticStringSwitch = new SemanticStringSwitch();
	}

	public List<SemanticTextElement> getSingleSemanticTextElements() {
		return Lists.newArrayList(elementLookup.values());
	}

	public Map<String, SemanticTextElement> getLookup(){
		return elementLookup;
	}

	@Override
	public SemanticTextElement caseActor(Actor object) {
		System.out.println("Analyzing: " + object.toString());
		String actor = object.getActor();
		if (actor != null && !actor.isEmpty()) {
			if (elementLookup.containsKey(actor)) {
				return elementLookup.get(actor);
			} else {
				SemanticTextElement texElement = new SemanticTextElement(actor, RequirementType.OBJECT);
				elementLookup.put(actor, texElement);
				return texElement;
			}
		}
		return super.caseActor(object);
	}

	@Override
	public SemanticTextElement caseActors(Actors object) {
		//Not intersting
		return super.caseActors(object);
	}

	@Override
	public SemanticTextElement caseAuxNeg(AuxNeg object) {
		//Not intersting
		return super.caseAuxNeg(object);
	}

	@Override
	public SemanticTextElement caseClause(Clause object) {
		//Not intersting
		return super.caseClause(object);
	}

	@Override
	public SemanticTextElement caseClauses(Clauses object) {
		//Not intersting
		return super.caseClauses(object);
	}

	@Override
	public SemanticTextElement caseConditionalClause(ConditionalClause object) {
		//Not intersting
		return super.caseConditionalClause(object);
	}


	@Override
	public SemanticTextElement caseConstraint(Constraint object) {
		System.out.println("Analyzing: " + object.toString());
		//We only consider Unit Contstraint here
		if (object.getConstraint()!=null && object.getConstraint() instanceof UnitConstraints) {
			StringJoiner objTxt = new StringJoiner(" ");
			//Backtracking in order to get the OBJECT!
			if(object.eContainer()!=null && object.eContainer() instanceof RelativeSentence) {
				objTxt.add(backtrackRelativeActors((RelativeSentence) object.eContainer()));
			}
			if(object.eContainer()!=null && object.eContainer() instanceof  SentenceEnding) {
				objTxt.add(getObjectFromSentence( (SentenceEnding) object.eContainer()));
			}
			objTxt.add(semanticStringSwitch.caseConstraintOrdinators(object.getOrdinator()));
			//Added ordinators here - goon with numbered value
			UnitConstraints constraint = (UnitConstraints) object.getConstraint();
			if(constraint instanceof UnitConstraints) {
				objTxt.add(semanticStringSwitch.caseUnitConstraints(constraint));
			}	
			if (elementLookup.containsKey(objTxt.toString())) {
				return elementLookup.get(objTxt.toString());
			} else {
				SemanticTextElement texElement = new SemanticTextElement(objTxt.toString(),
						RequirementType.RELATION);
				elementLookup.put(objTxt.toString(), texElement);
				return texElement;
			}
		}
		return super.caseConstraint(object);
	}

	private String backtrackRelativeActors(RelativeSentence object) {
		StringJoiner objTxt = new StringJoiner(" ");
		if(object!=null && object instanceof  RelativeSentence) {
			if(object.eContainer()!=null && object.eContainer() instanceof  RelativeClause) {
				if(object.eContainer().eContainer()!=null && object.eContainer() instanceof  Existence) {
					Existence existenceSentence= (Existence) object.eContainer();
					if(existenceSentence.getActors()!=null)
						objTxt.add(getActorsText(existenceSentence.getActors()));
				}
			}
		}
		return objTxt.toString();
	}

	private String getObjectFromSentence(SentenceEnding object) {

		StringJoiner objTxt = new StringJoiner(" ");
		SentenceEnding ending = (SentenceEnding) object.eContainer();
		if(ending!=null && ending.eContainer()!=null) {
			if ( ending.eContainer() instanceof ModalitySentence) {
				ModalitySentence sentence = (ModalitySentence) object.eContainer();
				objTxt.add(getActorsText(sentence.getActors()));
			}
			if ( ending.eContainer() instanceof PredicateSentence) {
				PredicateSentence sentence = (PredicateSentence) object.eContainer();
				objTxt.add(getActorsText(sentence.getActors()));;
			}
			if ( ending.eContainer() instanceof PropertySentence) {
				PropertySentence sentence = (PropertySentence) object.eContainer();
				objTxt.add(getActorsText(sentence.getActors()));
			}
		}
		return "";
	}

	private String getActorsText( Actors actors) {
		StringJoiner objTxt = new StringJoiner(" ");
		StringJoiner actorsStr= new StringJoiner(";","<",">");
		for (Actor act : actors.getActors()) {
			actorsStr.add(act.getActor());
		}
		objTxt.add(actorsStr.toString());
		return objTxt.toString();
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
			if (elementLookup.containsKey(objTxt.toString())) {
				return elementLookup.get(objTxt.toString());
			} else {
				SemanticTextElement texElement = new SemanticTextElement(objTxt.toString(),
						RequirementType.OBJECT);
				elementLookup.put(objTxt.toString(), texElement);
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
		System.out.println("Analyzing: " + object.toString());
		//GET ACTOR for text
		StringJoiner objTxt = new StringJoiner(" ");
		objTxt.add(getPredicateActors(object));
		objTxt.add(getPredicateModalityAuxiliarWords(object));
		objTxt.add(semanticStringSwitch.casePredicate(object));
		if (elementLookup.containsKey(objTxt.toString())) {
			return elementLookup.get(objTxt.toString());
		} else {
			SemanticTextElement texElement = new SemanticTextElement(objTxt.toString(),
					RequirementType.RELATION);
			elementLookup.put(objTxt.toString(), texElement);
			return texElement;
		}
	}

	private String getPredicateModalityAuxiliarWords(Predicate object) {
		StringJoiner objTxt = new StringJoiner(" ");
		if(object!=null) {
			if(object.eContainer()!=null) {
				EObject container = object.eContainer();
				if(container instanceof RelativeSentence) {
					if(((RelativeSentence) container).getModelity()!=null) {
						objTxt.add(((RelativeSentence) container).getModelity().toString());
					}
					if(((RelativeSentence) container).getAuxiliar()!=null) {
						objTxt.add(((RelativeSentence) container).getAuxiliar().toString());
					}
					return objTxt.toString();
				}
				if(container instanceof ModalitySentence) {
					if(((ModalitySentence) container).getModelity()!=null) {
						objTxt.add(((ModalitySentence) container).getModelity().toString());
					}
					if(((ModalitySentence) container).getAuxiliarVerb()!=null) {
						objTxt.add(((ModalitySentence) container).getAuxiliarVerb());
					}
					return objTxt.toString();
				}
				if(container instanceof PredOrObject) {
					if(container.eContainer()!=null && container.eContainer()instanceof PropertySentence) {
						PropertySentence sentence =(PropertySentence) container.eContainer();
						if(sentence.getAuxNeg()==null) {
							if(sentence.getModality()!=null) {
								objTxt.add(sentence.getModality().toString());
							}
							if(sentence.getAuxiliarVerb()!=null) {
								objTxt.add(sentence.getAuxiliarVerb());
							}
						} else {
							AuxNeg aux = sentence.getAuxNeg();
							if(aux.getAuxiliarVerb()!=null) {
								objTxt.add(aux.getAuxiliarVerb());
							} else {
								//change negative to positive
								//'doesn´t' | 'don´t' | 'isn´t' | 'aren´t'
								objTxt.add(eleminateAulixierNegation(aux.getAuxiliarVerbNeg()));
							}
						}
						return objTxt.toString();
					}
				}
			}
		}
		return objTxt.toString();
	}

	private String eleminateAulixierNegation(String auxiliarVerbNeg) {
		switch(auxiliarVerbNeg) {
		case "doesn´t": 
			return "does";
		case "don´t":
			return "do";
		case "isn´t":
			return "is";
		case "aren´t":
			return "are";
		default:
			return null;
		}
	}

	private String getPredicateActors(Predicate object) {
		StringJoiner objTxt = new StringJoiner(" ");
		if(object!=null) {
			if(object.eContainer()!=null) {
				EObject container = object.eContainer();
				if(container instanceof RelativeSentence) {
					objTxt.add(backtrackRelativeActors((RelativeSentence) container));
				}
				if(container instanceof ModalitySentence) {
					objTxt.add(getActorsText(((ModalitySentence) container).getActors()));
				}
				if(container instanceof PredOrObject) {
					if(container.eContainer()!=null && container.eContainer()instanceof PropertySentence) {
						PropertySentence sentence =(PropertySentence) container.eContainer();
						objTxt.add(getActorsText(sentence.getActors()));
					}
				}
			}
		}
		return objTxt.toString();
	}

	@Override
	public SemanticTextElement casePredicateObject(PredicateObject object) {
		System.out.println("Analyzing: " + object.toString());
		if (!(object.getObject().isEmpty())) {
			String objects = semanticStringSwitch.casePredicateObject(object);
			if (elementLookup.containsKey(objects.toString())) {
				return elementLookup.get(objects.toString());
			} else {
				SemanticTextElement texElement = new SemanticTextElement(objects.toString(),
						RequirementType.OBJECT);
				elementLookup.put(objects.toString(), texElement);
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
			if (elementLookup.containsKey(text)) {
				return elementLookup.get(text);
			} else {
				SemanticTextElement texElement = new SemanticTextElement(text,
						RequirementType.FUNCTION);
				elementLookup.put(text, texElement);
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

	//TODO DAS IST DOCH EINE SPEZIFISCHERE AUSSAGE FÜR EIN OBJEKT bzw. Eigenschaft
	@Override
	public SemanticTextElement caseRelation(Relation object) {
		// TODO Auto-generated method stub
		return super.caseRelation(object);
	}

	@Override
	public SemanticTextElement caseRelativeClause(RelativeClause object) {
		// TODO Auto-generated method stub
		return super.caseRelativeClause(object);
	}

	@Override
	public SemanticTextElement caseRelativeSentence(RelativeSentence object) {
		// TODO Auto-generated method stub
		return super.caseRelativeSentence(object);
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
