/**
 * 
 */
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter;

import java.util.StringJoiner;

import de.fraunhofer.isst.stars.requirementDSL.Actor;
import de.fraunhofer.isst.stars.requirementDSL.ActorProperties;
import de.fraunhofer.isst.stars.requirementDSL.ActorProperty;
import de.fraunhofer.isst.stars.requirementDSL.Actors;
import de.fraunhofer.isst.stars.requirementDSL.AuxNeg;
import de.fraunhofer.isst.stars.requirementDSL.Constraint;
import de.fraunhofer.isst.stars.requirementDSL.ConstraintOrdinators;
import de.fraunhofer.isst.stars.requirementDSL.Constraints;
import de.fraunhofer.isst.stars.requirementDSL.FloatValue;
import de.fraunhofer.isst.stars.requirementDSL.IntValue;
import de.fraunhofer.isst.stars.requirementDSL.IntervallConstraint;
import de.fraunhofer.isst.stars.requirementDSL.Object;
import de.fraunhofer.isst.stars.requirementDSL.ObjectConstraint;
import de.fraunhofer.isst.stars.requirementDSL.ObjectSet;
import de.fraunhofer.isst.stars.requirementDSL.Predicate;
import de.fraunhofer.isst.stars.requirementDSL.PredicateObject;
import de.fraunhofer.isst.stars.requirementDSL.Property;
import de.fraunhofer.isst.stars.requirementDSL.RelObjectProperty;
import de.fraunhofer.isst.stars.requirementDSL.RelObjects;
import de.fraunhofer.isst.stars.requirementDSL.Relation;
import de.fraunhofer.isst.stars.requirementDSL.SentenceBegin;
import de.fraunhofer.isst.stars.requirementDSL.SentenceEnding;
import de.fraunhofer.isst.stars.requirementDSL.SetConstraint;
import de.fraunhofer.isst.stars.requirementDSL.SingleValueConstraint;
import de.fraunhofer.isst.stars.requirementDSL.TimeConstraint;
import de.fraunhofer.isst.stars.requirementDSL.UnitConstraint;
import de.fraunhofer.isst.stars.requirementDSL.Value;
import de.fraunhofer.isst.stars.requirementDSL.ValueSet;
import de.fraunhofer.isst.stars.requirementDSL.util.RequirementDSLSwitch;

/**
 * @author mmauritz Process the text of the AST Elements.
 */
//TODO listing for e.g. actor and object have not and inserted! Change?
public class SemanticStringSwitch extends RequirementDSLSwitch<String> {

	@Override
	public String caseValue(Value object) {
		if (object == null)
			return "";
		if (object instanceof IntValue) {
			return caseIntValue((IntValue) object);
		}
		if (object instanceof FloatValue) {
			return caseFloatValue((FloatValue) object);
		}
		return "";// Should never happen
	}

	@Override
	public String caseIntValue(IntValue object) {
		StringJoiner objTxt = new StringJoiner(" ");
		objTxt.add(String.valueOf(object.getValue()));
		if (!object.getUnit().isEmpty()) {
			objTxt.add(object.getUnit());
		}
		if (!object.getObject().isEmpty()) {
			for (String str : object.getObject()) {
				objTxt.add(str);
			}
		}
		return objTxt.toString();
	}

	@Override
	public String caseFloatValue(FloatValue object) {
		StringJoiner objTxt = new StringJoiner(" ");
		objTxt.add(String.valueOf(object.getValue()));
		if (!object.getUnit().isEmpty()) {
			objTxt.add(object.getUnit());
		}
		if (!object.getObject().isEmpty()) {
			for (String str : object.getObject()) {
				objTxt.add(str);
			}
		}
		return objTxt.toString();
	}

	@Override
	public String caseSingleValueConstraint(SingleValueConstraint object) {
		return caseValue(object.getValue());
	}

	@Override
	public String caseIntervallConstraint(IntervallConstraint object) {
		StringBuilder objTxt = new StringBuilder();
		objTxt.append("[");
		objTxt.append(caseValue(object.getLower()));
		objTxt.append(",");
		objTxt.append(caseValue(object.getHigher()));
		objTxt.append("]");
		return objTxt.toString();
	}

	@Override
	public String caseConstraintOrdinators(ConstraintOrdinators object) {
		StringJoiner objTxt = new StringJoiner(" ");
		if (object.getStuffing() != null && !object.getStuffing().isEmpty()) {
			objTxt.add(object.getStuffing());
		}
		// adverbial is a must
		if (object.getAdverbial() != null && !object.getAdverbial().isEmpty()) {
			objTxt.add(object.getAdverbial());
		}
		if (object.getComperator() != null && !object.getComperator().isEmpty()) {
			objTxt.add(object.getComperator());
		}
		return objTxt.toString();
	}

	@Override
	public String caseUnitConstraint(UnitConstraint object) {
		if (object instanceof SingleValueConstraint) {
			return caseSingleValueConstraint((SingleValueConstraint) object);
		}
		if (object instanceof IntervallConstraint) {
			return caseIntervallConstraint((IntervallConstraint) object);

		}
		return super.caseUnitConstraint(object);// Should never happen
	}

	@Override
	public String casePredicateObject(PredicateObject object) {
		StringJoiner objTxt = new StringJoiner(" ");
		if (object != null && object.getRelativ() != null && !object.getRelativ().isEmpty()) {
			objTxt.add(object.getRelativ());
		}
		if (object != null && object.getObject() != null && !(object.getObject().isEmpty())) {
			for (String text : object.getObject()) {
				// the text object lists all words for the real meaning these word have to be
				// concatenated
				objTxt.add(text);
			}
		}
		return objTxt.toString();
	}

	@Override
	public String casePredicate(Predicate object) {
		StringJoiner objTxt = new StringJoiner(" ");
		if (object.getPredicates() != null && !(object.getPredicates().isEmpty())) {
			for (String text : object.getPredicates()) {
				// the text object lists all words for the real meaning these word have to be
				// concatenated
				objTxt.add(text);
			}
		}
		if (object.getObject() != null) {
			objTxt.add(casePredicateObject(object.getObject()));
		}
		return objTxt.toString();
	}

	@Override
	public String caseAuxNeg(AuxNeg object) {
		if (object == null)
			return "";
		StringJoiner objTxt = new StringJoiner(" ");

		if (object.getAuxiliarVerb() != null) {
			objTxt.add(object.getAuxiliarVerb());
			// negation is not considered
		} else {// getAuxiliarVerb ==null -> Infomration are in get AuxiliarVerbNeg
			// change negative to positive
			// 'doesn�t' | 'don�t' | 'isn�t' | 'aren�t'
			objTxt.add(eleminateAulixierNegation(object.getAuxiliarVerbNeg()));
		}
		return objTxt.toString();
	}

	private String eleminateAulixierNegation(String auxiliarVerbNeg) {
		switch (auxiliarVerbNeg) {
		// TODO WAS IST MIT ANDEREN ABOSTROPH!?
		case "doesn't":
		case "doesn´t":
		case "doesn`t":
			return "does";
		case "don't":
		case "don´t":
		case "don`t":
			return "do";
		case "isn't":
		case "isn´t":
		case "isn`t":
			return "is";
		case "aren't":
		case "aren´t":
		case "aren`t":
			return "are";
		default:
			return null;
		}
	}

	@Override
	public String caseSentenceEnding(SentenceEnding object) {
		StringJoiner objTxt = new StringJoiner(" ");
		// constraints
		if (object.getConstraints() != null) {
			objTxt.add(caseConstraints(object.getConstraints()));
		}
		// Relation
		if (object.getRela() != null) {
			objTxt.add(caseRelation(object.getRela()));
		}
		return objTxt.toString();
	}

	@Override
	public String caseConstraint(Constraint object) {
		if (object == null)
			return "";
		StringJoiner objTxt = new StringJoiner(" ");
		// Currently only consider
		if (object.getConstraint() != null) {
			if (object.getOrdinator() != null) {
				objTxt.add(caseConstraintOrdinators(object.getOrdinator()));
			}
			if (object.getConstraint() instanceof UnitConstraint) {
				objTxt.add(caseUnitConstraint((UnitConstraint) object.getConstraint()));
			}
			if (object.getConstraint() instanceof ObjectConstraint) {
				objTxt.add(caseObjectConstraint((ObjectConstraint) object.getConstraint()));
			}
			if (object.getConstraint() instanceof SetConstraint) {
				objTxt.add(caseSetConstraint((SetConstraint) object.getConstraint()));
			}
		}
		return objTxt.toString();
	}

	@Override
	public String caseObjectConstraint(ObjectConstraint object) {
		if (object == null)
			return "";
		StringJoiner objTxt = new StringJoiner(" ");
		if (object.getObject() != null) {
			objTxt.add(caseObject(object.getObject()));
		}
		return objTxt.toString();
	}

	@Override
	public String caseTimeConstraint(TimeConstraint object) {
		if (object == null)
			return "";
		StringJoiner objTxt = new StringJoiner(" ");
		if (object.getOrdinator() != null) {
			objTxt.add(caseConstraintOrdinators(object.getOrdinator()));
		}
		objTxt.add(Integer.toString(object.getTime()));
		if (!object.getUnit().isEmpty()) {
			objTxt.add(object.getUnit());
		}
		objTxt.add(Integer.toString(object.getTime()));
		return objTxt.toString();
	}

	@Override
	public String caseSetConstraint(SetConstraint object) {
		if (object == null)
			return "";
		if (object.getSet() instanceof ValueSet) {
			return caseValueSet((ValueSet) object.getSet());
		}
		if (object.getSet() instanceof ObjectSet) {
			return caseObjectSet((ObjectSet) object.getSet());
		}
		return "";
	}

	@Override
	public String caseValueSet(ValueSet object) {
		if (object != null && object.getElements() != null && !object.getElements().isEmpty()) {
			StringJoiner objTxt = new StringJoiner(";", "[", "]");
			for (Value val : object.getElements()) {
				objTxt.add(caseValue(val));
			}
			return objTxt.toString();
		}
		return "";
	}

	@Override
	public String caseObjectSet(ObjectSet object) {
		if (object != null && object.getElements() != null && !object.getElements().isEmpty()) {
			StringJoiner objTxt = new StringJoiner(";", "[", "]");
			for (Object val : object.getElements()) {
				objTxt.add(caseObject(val));
			}
			return objTxt.toString();
		}
		return "";
	}

	@Override
	public String caseActor(Actor object) {
		if (object != null && !object.getActor().isEmpty()) {
			return object.getActor();
		}
		return "";
	}

	@Override
	public String caseActors(Actors object) {
		if (object != null && !object.getActors().isEmpty()) {
			StringJoiner actorsStr = new StringJoiner(" ");
			for (int i = 0; i < object.getActors().size(); i++) {
				actorsStr.add(caseActor(object.getActors().get(i)));
				if (object.getConjunction() != null && !object.getConjunction().isEmpty()
						&& i < object.getConjunction().size()) {
					actorsStr.add(object.getConjunction().get(i).getText());
				}
			}
			return actorsStr.toString();
		}
		return super.caseActors(object);
	}

	@Override
	public String caseRelation(Relation object) {
		if (object == null)
			return "";
		StringJoiner objTxt = new StringJoiner(" ");
		if (object.getRelDel() != null && !object.getRelDel().isEmpty()) {
			objTxt.add(object.getRelDel());
		}
		if (object.getRelElements() != null) {
//			objTxt.add("<" + caseRelObjects(object.getRelElements()) + ">");
			objTxt.add(caseRelObjects(object.getRelElements()));
		}
		return objTxt.toString();
	}

	@Override
	// TODO HERE WE CAN'T CONTAIN THE ORDER! OF OBJECT AND PROPERTY WITH RESPECT TO
	// CONJUNCTION!
	public String caseRelObjects(RelObjects object) {
		if (object == null)
			return "";
		// StringJoiner objTxt = new StringJoiner(";", "<", ">");
		StringJoiner relObjTxt = new StringJoiner(" ");
		// iterate over objects and add corresponding property
		// get the object for iteration over all objects
		if (object.getObject() != null && !object.getObject().isEmpty()) {
			StringJoiner objTxt = new StringJoiner(" ");
			// iterate overall objects - there should not be a property without object
			for (Object obj : object.getObject()) {
				// Object may consists of multiple string -> concatenate them
				if (obj.getRelativ() != null && !obj.getRelativ().isEmpty()) {
					objTxt.add(obj.getRelativ());
				}
				if (obj.getObject() != null && !obj.getObject().isEmpty()) {
					// Concatenate multi Word String
					for (String objStr : obj.getObject()) {
						objTxt.add(objStr);
					}

				}
			}
			relObjTxt.add(objTxt.toString());
		}
		if (object.getProperty() != null && !object.getProperty().isEmpty()) {
			StringJoiner propTxt = new StringJoiner(" ");
			for (RelObjectProperty prop : object.getProperty()) {
				propTxt.add(caseRelObjectProperty(prop));
			}
			relObjTxt.add(propTxt.toString());
		}
		return relObjTxt.toString();
	}

	@Override
	public String caseRelObjectProperty(RelObjectProperty object) {
		if (object == null)
			return "";
		StringJoiner propText = new StringJoiner(" ");
		// Check for property and make sure not to be out of bounds
		if (object.getObject() != null) {
			propText.add(caseObject(object.getObject()) + "\'s");
		}
		if (object.getProperty() != null) {
			propText.add(caseProperty(object.getProperty()));
		}
		return propText.toString();
	}

	@Override
	public String caseActorProperty(ActorProperty object) {
		if (object == null)
			return "";
		StringJoiner propText = new StringJoiner(" ");
		// Check for property and make sure not to be out of bounds
		if (object.getObject() != null) {
			propText.add(caseObject(object.getObject()) + "\'s");
		}
		if (object.getProperty() != null) {
			propText.add(caseProperty(object.getProperty()));
		}
		if (object.getRela() != null) {
			propText.add(caseRelation(object.getRela()));
		}
		return propText.toString();
	}

	@Override
	public String caseObject(Object object) {
		if (object == null)
			return "";
		StringJoiner objTxt = new StringJoiner(" ");
		if (object.getRelativ() != null && !object.getRelativ().isEmpty()) {
			objTxt.add(object.getRelativ());
		}
		if (object.getObject() != null && !object.getObject().isEmpty()) {
			for (String objStr : object.getObject()) {
				objTxt.add(objStr);
			}
		}
		return objTxt.toString();
	}

	@Override
	public String caseProperty(Property object) {
		if (object == null)
			return "";
		StringJoiner objTxt = new StringJoiner(" ");
		if (object.getRelativ() != null && !object.getRelativ().isEmpty()) {
			objTxt.add(object.getRelativ());
		}
		if (object.getProperty() != null && !object.getProperty().isEmpty()) {
			for (String word : object.getProperty()) {
				objTxt.add(word);
			}
		}
		return objTxt.toString();
	}

	@Override
	public String caseConstraints(Constraints object) {
		if (object == null)
			return "";
		StringJoiner txt = new StringJoiner(" ");
		// Constraint
		if (object.getConstraint() != null && !object.getConstraint().isEmpty()) {
			for (Constraint cont : object.getConstraint()) {
				txt.add(caseConstraint(cont));
			}
		}
		// Time Constraint
		if (object.getTimeConstraint() != null && !object.getTimeConstraint().isEmpty()) {
			for (TimeConstraint time : object.getTimeConstraint()) {
				txt.add(caseTimeConstraint(time));
			}
		}
		// Conjunction of Constraints
		if (object.getConjunction() != null) {
			if (object.getConstraints() == null) {
				// ERROR -> should both be set if one is set
				// TODO LOG ERROR
			}
			txt.add(object.getConjunction().getText());
			txt.add(caseConstraints(object.getConstraints()));
		}
		return txt.toString();
	}

	@Override
	public String caseSentenceBegin(SentenceBegin object) {
		if (object.getRela() != null) {
			return caseRelation(object.getRela());
		}
		return "";
	}

	@Override
	public String caseActorProperties(ActorProperties object) {
		if (object == null)
			return "";
		StringJoiner propText = new StringJoiner(" ");
		// Check for property and make sure not to be out of bounds
		if (object.getProperty() != null && !object.getProperty().isEmpty()) {
			for (int i = 0; i < object.getProperty().size(); i++) {
				propText.add(caseActorProperty(object.getProperty().get(i)));
				// TODO TAKE OUT IF only SINGLE ELEMENTS ARE
				if (object.getConjunction() != null && !object.getConjunction().isEmpty()
						&& i < object.getConjunction().size()) {
					propText.add(object.getConjunction().get(i).getText());
				}
			}
		}
		return propText.toString();
	}
}
