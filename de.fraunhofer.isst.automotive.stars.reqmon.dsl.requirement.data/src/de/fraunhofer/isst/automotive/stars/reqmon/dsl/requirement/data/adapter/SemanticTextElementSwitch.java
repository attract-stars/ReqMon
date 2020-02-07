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
/**
 * 
 */
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Lists;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement;
import de.fraunhofer.isst.stars.requirementDSL.Actor;
import de.fraunhofer.isst.stars.requirementDSL.ActorProperty;
import de.fraunhofer.isst.stars.requirementDSL.Existence;
import de.fraunhofer.isst.stars.requirementDSL.ModalitySentence;
import de.fraunhofer.isst.stars.requirementDSL.Object;
import de.fraunhofer.isst.stars.requirementDSL.PredicateSentence;
import de.fraunhofer.isst.stars.requirementDSL.PropertySentence;
import de.fraunhofer.isst.stars.requirementDSL.RelObjectProperty;
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
		// Modality Verbs have not to be considered
//		if (object.getModelity() != null) {
//			objTxt.add(object.getModelity().toString());
//		}
		// auxiliar words
		if (object.getAuxiliarVerb() != null) {
			objTxt.add(object.getAuxiliarVerb());
		}
		// predicate
		if (object.getPredicate() != null) {
			objTxt.add(semanticStringSwitch.casePredicate(object.getPredicate()));
		}
		// constraints
		if (object.getBegin() != null) {
			objTxt.add(semanticStringSwitch.caseSentenceBegin(object.getBegin()));
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
		if (object.getActors() != null) {
			objTxt.add(addTextMarkings(semanticStringSwitch.caseActors(object.getActors())));
		}
		if (object.getAuxNeg() != null) {
			objTxt.add(semanticStringSwitch.caseAuxNeg(object.getAuxNeg()));
		}
		if (object.getAuxiliarVerb() != null) {
			objTxt.add(object.getAuxiliarVerb());
		}
		if (object.getPredicate() != null) {
			objTxt.add(semanticStringSwitch.casePredicate(object.getPredicate()));
		}
		// add the SentenceBeginning at the end -
		// TODO look for necessary adjustment -> Case
		if (object.getBegin() != null) {
			objTxt.add(semanticStringSwitch.caseSentenceBegin(object.getBegin()));
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
	public SemanticTextElement caseActorProperty(ActorProperty object) {
		System.out.println("Analyzing: " + object.toString());
		StringJoiner actorPropTxt = new StringJoiner(" ");
		if (object.getObject() != null) {
			actorPropTxt.add(semanticStringSwitch.caseObject(object.getObject()) + "\'s");
		}
		if (object.getProperty() != null) {
			actorPropTxt.add(semanticStringSwitch.caseProperty(object.getProperty()));
		}
		if (object.getRela() != null) {
			actorPropTxt.add(semanticStringSwitch.caseActorPropertyRelation(object.getRela()));
		}
		if (elementLookup.containsKey(actorPropTxt.toString())) {
			return elementLookup.get(actorPropTxt.toString());
		} else {
			SemanticTextElement texElement = new SemanticTextElement(actorPropTxt.toString(), RequirementType.FUNCTION);
			elementLookup.put(actorPropTxt.toString(), texElement);
			return texElement;
		}
	}

	// TODO PROBLEM IF PROPERTY IS IN RELATION -> RELATION NOT CONSIDERED
	@Override
	public SemanticTextElement caseRelObjectProperty(RelObjectProperty object) {
		System.out.println("Analyzing: " + object.toString());
		StringJoiner objPropTxt = new StringJoiner(" ");
		if (object.getObject() != null) {
			objPropTxt.add(semanticStringSwitch.caseObject(object.getObject()) + "\'s");
		}
		if (object.getProperty() != null) {
			objPropTxt.add(semanticStringSwitch.caseProperty(object.getProperty()));
		}
		if (elementLookup.containsKey(objPropTxt.toString())) {
			return elementLookup.get(objPropTxt.toString());
		} else {
			SemanticTextElement texElement = new SemanticTextElement(objPropTxt.toString(), RequirementType.FUNCTION);
			elementLookup.put(objPropTxt.toString(), texElement);
			return texElement;
		}
	}

	@Override
	public SemanticTextElement casePropertySentence(PropertySentence object) {
		if (object == null) {
			return super.casePropertySentence(object);
		}
		System.out.println("Analyzing: " + object.toString() + " for predicates and constraints");
		StringJoiner objTxt = new StringJoiner(" ");
		// TODO go for OBJECTS and PROPERTIES
		if (object.getProperties() != null) {
			// for "<"... ">" encapsulation of actor's properties
			StringJoiner actorPropTxt = new StringJoiner(" ", "<", ">");// DOUBLE "<" ">" due to actors
			actorPropTxt.add(semanticStringSwitch.caseActorProperties(object.getProperties()));
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
		if (object.getPredicate() != null) {
			objTxt.add(semanticStringSwitch.casePredicate(object.getPredicate()));
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
		if (object.getConstraints() != null) {
			// Avoid adding whitespaces for time constraints which are not considered
			objTxt.add(semanticStringSwitch.caseConstraints(object.getConstraints()));
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
		if (container instanceof Existence) {
			return semanticStringSwitch.caseActors(((Existence) container).getActors());
		} else {
			return "";
		}
	}

	// HERE YOU HAVE TO ADD RELEVANT MODELL ELEMENTS FOR VISITING THEM

}
