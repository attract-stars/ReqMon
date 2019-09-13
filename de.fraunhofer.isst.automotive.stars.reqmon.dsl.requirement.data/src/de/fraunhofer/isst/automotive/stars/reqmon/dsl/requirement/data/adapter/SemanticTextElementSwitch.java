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
import de.fraunhofer.isst.stars.requirementDSL.Constraints;
import de.fraunhofer.isst.stars.requirementDSL.Existence;
import de.fraunhofer.isst.stars.requirementDSL.ModalitySentence;
import de.fraunhofer.isst.stars.requirementDSL.Object;
import de.fraunhofer.isst.stars.requirementDSL.PredicateSentence;
import de.fraunhofer.isst.stars.requirementDSL.Property;
import de.fraunhofer.isst.stars.requirementDSL.PropertySentence;
import de.fraunhofer.isst.stars.requirementDSL.RelObjects;
import de.fraunhofer.isst.stars.requirementDSL.RelativeClause;
import de.fraunhofer.isst.stars.requirementDSL.RelativeSentence;
import de.fraunhofer.isst.stars.requirementDSL.util.RequirementDSLSwitch;

/**
 * @author mmauritz This class defibes the entry points for the analysis of the
 *         AST. Here the analysis of relevant AST elements is started. The
 *         instantiation of the text for all necessary Element is processed in
 *         the class {@link SemanticStringSwitch}
 */
public class SemanticTextElementSwitch extends RequirementDSLSwitch<SemanticTextElement> {

	// Lookup for existing elements
	private HashMap<String, SemanticTextElement> elementLookup;
	private SemanticStringSwitch semanticStringSwitch;

	public SemanticTextElementSwitch() {
		this.elementLookup = new HashMap<String, SemanticTextElement>();
		this.semanticStringSwitch = new SemanticStringSwitch();
	}

	public List<SemanticTextElement> getSingleSemanticTextElements() {
		return Lists.newArrayList(elementLookup.values());
	}

	public Map<String, SemanticTextElement> getLookup() {
		return elementLookup;
	}

	private String addTextMarkings(String string) {
		return "<" + string + ">";
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
	public SemanticTextElement caseModalitySentence(ModalitySentence object) {
		if (object == null) {
			return super.caseModalitySentence(object);
		}
		System.out.println("Analyzing: " + object.toString() + " for predicates and constraints");
		StringJoiner objTxt = new StringJoiner(" ");
		// Actors
		objTxt.add(addTextMarkings(semanticStringSwitch.caseActors(object.getActors())));
		// auxiliar words
		if (object.getModelity() != null) {
			objTxt.add(object.getModelity().toString());
		}
		if (object.getAuxiliarVerb() != null) {
			objTxt.add(object.getAuxiliarVerb());
		}
		// predicate
		if (object.getPredicate() != null) {
			objTxt.add(semanticStringSwitch.casePredicate(object.getPredicate()));
		}
		// constraints
		if (object.getEnding() != null) {
			objTxt.add(semanticStringSwitch.caseSentenceEnding(object.getEnding()));
		}
		// Save information
		if (elementLookup.containsKey(objTxt.toString())) {
			return elementLookup.get(objTxt.toString());
		} else {
			SemanticTextElement texElement = new SemanticTextElement(objTxt.toString(), RequirementType.RELATION);
			elementLookup.put(objTxt.toString(), texElement);
			return texElement;
		}
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
				SemanticTextElement texElement = new SemanticTextElement(objTxt.toString(), RequirementType.OBJECT);
				elementLookup.put(objTxt.toString(), texElement);
				return texElement;
			}
		}
		return super.caseObject(object);
	}

	@Override
	public SemanticTextElement casePredicateSentence(PredicateSentence object) {
		if (object == null) {
			return super.casePredicateSentence(object);
		}
		System.out.println("Analyzing: " + object.toString() + " for predicates and constraints");
		StringJoiner objTxt = new StringJoiner(" ");
		if (object.getBegin() != null) {
			// TODO HAS THIS PREDEFINITION TO BE SORTED DIFFERENTLY IN THE ORDER!"?
			objTxt.add(semanticStringSwitch.caseSentenceBegin(object.getBegin()));
		}
		if (object.getActors() != null) {
			objTxt.add(addTextMarkings(semanticStringSwitch.caseActors(object.getActors())));
		}
		if (object.getAuxNeg() != null) {
			objTxt.add(semanticStringSwitch.caseAuxNeg(object.getAuxNeg()));
		}
		if (object.getAuxiliarVerb() != null) {
			objTxt.add(object.getAuxiliarVerb());
		}
		if (object.getPreds() != null) {
			objTxt.add(semanticStringSwitch.casePreds(object.getPreds()));
		}
		if (object.getEnding() != null) {
			objTxt.add(semanticStringSwitch.caseSentenceEnding(object.getEnding()));
		}
		// Save information
		if (elementLookup.containsKey(objTxt.toString())) {
			return elementLookup.get(objTxt.toString());
		} else {
			SemanticTextElement texElement = new SemanticTextElement(objTxt.toString(), RequirementType.RELATION);
			elementLookup.put(objTxt.toString(), texElement);
			return texElement;
		}
	}

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
			// adding additional text for better Understand -> The preceeding object
			String prefix = "";
			if (object.eContainer() instanceof PropertySentence) {
				PropertySentence sentence = (PropertySentence) object.eContainer();
				EList<Actor> actors = sentence.getActors().getActors();
				Actor lastActor = actors.get(actors.size() - 1);
				prefix = lastActor.getActor() + "`s";// TODO HIER WIRD NUR DER LETZTE ACTOR ADDRESSIERT
			}
			if (object.eContainer() instanceof RelObjects) {
				RelObjects relObjects = (RelObjects) object.eContainer();
				EList<Object> objects = relObjects.getObject();
				Object lastObject = objects.get(objects.size() - 1);// TODO HIER WIRD NUR DAS LETZTE OBJECT ADDRESSIERT
				StringJoiner obj2Text = new StringJoiner(" ");
				for (String str : lastObject.getObject()) {
					// the text object lists all words for the real meaning these word have to be
					// concatenated
					obj2Text.add(str);
				}
				prefix = obj2Text.toString();
			}
			String text = !prefix.isEmpty() ? prefix + " " + objTxt.toString() : objTxt.toString();
			if (elementLookup.containsKey(text)) {
				return elementLookup.get(text);
			} else {
				SemanticTextElement texElement = new SemanticTextElement(text, RequirementType.FUNCTION);
				elementLookup.put(text, texElement);
				return texElement;
			}
		}
		return super.caseProperty(object);
	}

	@Override
	public SemanticTextElement casePropertySentence(PropertySentence object) {
		if (object == null) {
			return super.casePropertySentence(object);
		}
		System.out.println("Analyzing: " + object.toString() + " for predicates and constraints");
		StringJoiner objTxt = new StringJoiner(" ");
		if (object.getActors() != null && object.getProperty() != null) {
			// for "<"... ">" encapsulation of actors properties
			StringJoiner actorPropTxt = new StringJoiner(" ", "<", ">");// DOUBLE "<" ">" due to actors
			actorPropTxt.add(semanticStringSwitch.caseActors(object.getActors()));
			actorPropTxt.add(semanticStringSwitch.caseProperty(object.getProperty()));
			actorPropTxt.add(semanticStringSwitch.caseRelation(object.getRela()));
			objTxt.add(actorPropTxt.toString());
		}
		// first version of property senctence
		if (object.getAuxiliarVerb() != null) {
			objTxt.add(object.getAuxiliarVerb());
		}
		// second version of property sentence
		if (object.getAuxNeg() != null) {
			objTxt.add(semanticStringSwitch.caseAuxNeg(object.getAuxNeg()));
		}
		if (object.getPredObj() != null) {
			objTxt.add(semanticStringSwitch.casePredOrObject(object.getPredObj()));
		}
		if (object.getConstraints() != null) {
			objTxt.add(semanticStringSwitch.caseConstraints(object.getConstraints()));
		}
		if (object.getEnding() != null) {
			objTxt.add(semanticStringSwitch.caseSentenceEnding(object.getEnding()));
		}
		// Save
		if (elementLookup.containsKey(objTxt.toString())) {
			return elementLookup.get(objTxt.toString());
		} else {
			SemanticTextElement texElement = new SemanticTextElement(objTxt.toString(), RequirementType.RELATION);
			elementLookup.put(objTxt.toString(), texElement);
			return texElement;
		}
	}

	@Override
	public SemanticTextElement caseRelativeSentence(RelativeSentence object) {
		if (object == null) {
			return super.caseRelativeSentence(object);
		}
		System.out.println("Analyzing: " + object.toString() + " for predicates and constraints");
		StringJoiner objTxt = new StringJoiner(" ");
		if (object.getPronoun() != null) {
			// Replace RelativePronoun with real subject/object/actor
			objTxt.add(addTextMarkings(retriveSubject(object)));
		}
		// modality has not to be considered
		if (object.getAuxiliar() != null) {
			objTxt.add(object.getAuxiliar());
		}
		if (object.getPredicate() != null) {
			objTxt.add(semanticStringSwitch.casePredicate(object.getPredicate()));
		}
		if (object.getConstraints() != null && !object.getConstraints().isEmpty()) {
			for (Constraints con : object.getConstraints()) {
				String constrainTxt = semanticStringSwitch.caseConstraints(con);
				// Avoid adding whitespaces for time constraints which are not considered
				if (!constrainTxt.isEmpty()) {
					objTxt.add(constrainTxt);
				}
			}
		}
		// Clauses are analyzed by the specific cases in this class
		// Save
		if (elementLookup.containsKey(objTxt.toString())) {
			return elementLookup.get(objTxt.toString());
		} else {
			SemanticTextElement texElement = new SemanticTextElement(objTxt.toString(), RequirementType.RELATION);
			elementLookup.put(objTxt.toString(), texElement);
			return texElement;
		}
	}

	private String retriveSubject(RelativeSentence object) {
		EObject container = object.eContainer();
		if (!(container instanceof RelativeClause)) {
			return "";
		}
		container = container.eContainer();
		if (container instanceof Existence) {// TODO Existence instead of ExistenceSentence working here?
			return semanticStringSwitch.caseActors(((Existence) container).getActors());
		} else {
			return "";
		}
	}

	// HERE YOU HAVE TO ADD RELEVANT MODELL ELEMENTS FOR VISITING THEM

}
