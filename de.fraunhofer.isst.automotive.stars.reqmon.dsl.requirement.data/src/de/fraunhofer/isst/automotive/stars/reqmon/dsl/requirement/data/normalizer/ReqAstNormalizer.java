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
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.normalizer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.xtext.EcoreUtil2;

import de.fraunhofer.isst.stars.requirementDSL.Actor;
import de.fraunhofer.isst.stars.requirementDSL.ActorProperties;
import de.fraunhofer.isst.stars.requirementDSL.ActorProperty;
import de.fraunhofer.isst.stars.requirementDSL.ActorPropertyRelation;
import de.fraunhofer.isst.stars.requirementDSL.Actors;
import de.fraunhofer.isst.stars.requirementDSL.AuxNeg;
import de.fraunhofer.isst.stars.requirementDSL.Clause;
import de.fraunhofer.isst.stars.requirementDSL.Clauses;
import de.fraunhofer.isst.stars.requirementDSL.Conjunction;
import de.fraunhofer.isst.stars.requirementDSL.Existence;
import de.fraunhofer.isst.stars.requirementDSL.ModalitySentence;
import de.fraunhofer.isst.stars.requirementDSL.Object;
import de.fraunhofer.isst.stars.requirementDSL.PredicateSentence;
import de.fraunhofer.isst.stars.requirementDSL.PropertySentence;
import de.fraunhofer.isst.stars.requirementDSL.RelObjects;
import de.fraunhofer.isst.stars.requirementDSL.Relation;
import de.fraunhofer.isst.stars.requirementDSL.RelativeClause;
import de.fraunhofer.isst.stars.requirementDSL.RelativeSentence;
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLFactory;
import de.fraunhofer.isst.stars.requirementDSL.RequirementModel;
import de.fraunhofer.isst.stars.requirementDSL.SentenceBegin;
import de.fraunhofer.isst.stars.requirementDSL.SentenceEnding;

/**
 * THis class normalizes (flattens) the AST (model) of requirements for all
 * clauses to include only single actor,objects, properties, and constraints
 * 
 * @author mmauritz
 *
 */
// We dont use switch cause we want to do all sequentially in order to avoid complex backtracking etc. This might be time consuming but less fault prone.
public class ReqAstNormalizer {

	Logger logger = Logger.getLogger(ReqAstNormalizer.class);

	/**
	 * 
	 */
	public ReqAstNormalizer() {
	}

	/**
	 * Analyzes a given RequirementModel and resolves conjunction for actors,
	 * objects, predicates, etc. This makes the mapping of elements less complex.
	 * Note: Conjunctions of clauses are preserved.
	 * 
	 * @param the initial model for given requirements
	 * @return the resolved model
	 */
	public RequirementModel normalize(RequirementModel model) {
		// copy the model to change it without changing the initial one
		Copier copier = new Copier();
		EObject normalizedModel = null;
		String file = model.eResource().getURI().toFileString();
		Pattern pattern = Pattern.compile("(\\w*).reqDSL");
		String fileExt = model.eResource().getURI().fileExtension();
		Matcher matcher = pattern.matcher(file);
		if (matcher.find()) {
			String filename = matcher.group(1);
			if (filename == "") {
				filename = "normalizedModel";
			}
			filename = "." + filename + "_norm";
			file = file.replaceFirst("(\\w*).reqDSL", filename + "." + fileExt);
		} else {
			logger.error("Could not find the Requirement DSL Extension in resource file path.");
			return model;
		}
		Resource normalizedResource = model.eResource().getResourceSet().createResource(URI.createFileURI(file));
		// COPY
		normalizedModel = copier.copy(model);

//		Collection results = copier.copyAll(eObjects);
		copier.copyReferences();
		// add copied model to new resource
		EcoreUtil.resolveAll(normalizedModel);
		normalizedResource.getContents().add(normalizedModel);
		// Analyze
		if (normalizedModel == null || !(normalizedModel instanceof RequirementModel)) {
			logger.error("Model provided is not instance of RequirementModel!");
			return model;// return unmodified model
		}
		// normalized model instance of Requirement Model
		// iterate over the copied model
		// IT is possible that we have to consider the model for each type individually
		// in order to mitigate problems
		// we don't resolve in the
//		EcoreUtil.Collection<EObject> begins = EcoreUtil.getObjectsByType(normalizedModel.eContents(),
//				RequirementDSLPackage.eINSTANCE.getSentenceBegin());

		// Resolve SentenceBegin
		// Append Relation in SentenceBegin to the End of the Clause!
		List<SentenceBegin> begins = EcoreUtil2.getAllContentsOfType(normalizedModel, SentenceBegin.class);
		if (begins != null && !begins.isEmpty()) {
			resolveSentenceBegin(begins);
		}
		// normalizing
		resolveActorConjunction(normalizedModel);
		resolveresolveActorPropertyConjunction(normalizedModel);
		resolveRelObjectConjunction(normalizedModel);
		resolveActorRelObjectConjunction(normalizedModel);
		resolveRelativeClause(normalizedModel);
		resolveConstraints(normalizedModel);

//		// reset modeliterator
//		TreeIterator<EObject> modelIterator = EcoreUtil.getAllContents(normalizedModel, true);
////		normalizedModel.eAllContents();
//		while (modelIterator.hasNext()) {
//			EObject obj = modelIterator.next();
//			if (obj != null) {
//				if (obj instanceof Actors) {
//					logger.info("Resolving Actors: " + obj.toString());
////					modelIterator.prune();// TODO THIS DOES NOT CHANGE THE RELATIONS in ACTOR PROPERTIES BUT LEAD TO PROBLEMS!
//				} else if (obj instanceof ActorProperties) {
//					logger.info("Resolving Actor Properties: " + obj.toString());
//					resolveresolveActorPropertyConjunction((ActorProperties) obj, modelIterator);
////					modelIterator.prune();//TODO THIS DOES NOT CHANGE THE RELATIONS in ACTOR PROPERTIES BUT LEAD TO PROBLEMS!
//				} else if (obj instanceof RelativeClause) {
//					logger.info("Resolving RelativeClause: " + obj.toString());
//					resolveRelativeClause((RelativeClause) obj, modelIterator);
////					modelIterator.prune();
//				} else if (obj instanceof RelObjects) {
//					logger.info("Resolving Relation Objects: " + obj.toString());
//					resolveRelObjectConjunction((RelObjects) obj, modelIterator);
//					modelIterator.prune();// Prevents outofbounds error
//				} else if (obj instanceof Constraints) {
//					logger.info("Resolving Constraints: " + obj.toString());
//					resolveConstraints((Constraints) obj, modelIterator);
//					modelIterator.prune();
//				}
//			}
//		}
		logger.info("Finished normalizing model: " + model.toString());
		// now save the content.
		try {
			logger.debug("Saving normalized model in resource.");
			normalizedResource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			logger.error("Can't save resource: " + normalizedResource.toString());
			e.printStackTrace();
		}
		return (RequirementModel) normalizedModel;
	}

	/**
	 * @param begins
	 */
	private void resolveSentenceBegin(List<SentenceBegin> begins) {
		for (SentenceBegin obj : begins) {
			Relation rela = obj.getRela();
			EObject sentence = obj.eContainer();
			if (sentence instanceof ModalitySentence) {
				resolveSentenceBegin(obj, rela, (ModalitySentence) sentence);
			} else if (sentence instanceof PredicateSentence) {
				resolveSentenceBegin(obj, rela, (PredicateSentence) sentence);
			} else {
				logger.error("Parent sentence of SentenceBegin not supported!");
			}
		}
	}

	/**
	 * @param obj
	 * @param rela
	 * @param sentence
	 */
	private void resolveSentenceBegin(EObject obj, Relation rela, PredicateSentence sentence) {
		SentenceEnding ending;
		ending = sentence.getEnding();
		if (ending == null) {
			ending = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
			sentence.setEnding(ending);
		}
		if (ending.getRela() == null) {
			ending.setRela(rela);
			rela.getRelDel().toLowerCase();
			sentence.setBegin(null);// delete Sentence Begin from Tree
			// Capitalize first actor!
			if (sentence.getActors() != null && sentence.getActors().getActors() != null
					&& !sentence.getActors().getActors().isEmpty()) {
				Actor act = sentence.getActors().getActors().get(0);
				act.setActor(act.getActor().substring(0, 1).toUpperCase() + act.getActor().substring(1));
			}

		} else {
			logger.warn("Can't append relation of SentenceBegin to SentenEnding: " + obj.toString());
		}
	}

	/**
	 * @param obj
	 * @param rela
	 * @param sentence
	 */
	private void resolveSentenceBegin(EObject obj, Relation rela, ModalitySentence sentence) {
		SentenceEnding ending;
		ending = sentence.getEnding();
		if (ending == null) {
			ending = RequirementDSLFactory.eINSTANCE.createSentenceEnding();
			sentence.setEnding(ending);
		}
		if (ending.getRela() == null) {
			ending.setRela(rela);
			rela.getRelDel().toLowerCase();
			sentence.setBegin(null);// delete Sentence Begin from Tree
			// Capitalize first actor!
			if (sentence.getActors() != null && sentence.getActors().getActors() != null
					&& !sentence.getActors().getActors().isEmpty()) {
				Actor act = sentence.getActors().getActors().get(0);
				act.setActor(act.getActor().substring(0, 1).toUpperCase() + act.getActor().substring(1));
			}
		} else {
			logger.warn("Can't append relation of SentenceBegin to SentenEnding: " + obj.toString());
		}
	}

	/**
	 * Resolves conjunction of actors in order for all actors have their own clause
	 * 
	 * @param modelIterator
	 * 
	 * @param the           initial model for given requirements
	 * @return the resolved model
	 */
	protected void resolveActorConjunction(EObject inputObj) {
		List<Actors> actorsList = EcoreUtil2.getAllContentsOfType(inputObj, Actors.class);
		for (Actors actors : actorsList) {
			if (actors.getActors().size() > 1) {
				logger.info("Normalizing Actors " + actors.toString());
				Copier copier = new Copier();
				// We need the top clause to duplicate it
				EObject sentence = actors.eContainer();// Sentence to Copy
				// Container can be ExistenceSentence, ModalitySentence,PredicateSentence ->
				// These have to be duplicated for each actor
				EObject clauses = (sentence.eContainer());// clauses to add copy sentenced
				Actor firstActor = null;
				for (int i = 0; i < actors.getActors().size(); i++) {// start with secon
					// forward modelIterator
					if (i == 0) {
						firstActor = actors.getActors().get(0);
					} else {
						Actor curActor = actors.getActors().get(i);
						// clone the sentence
						EObject copiedSentence = copier.copy(sentence);
						Actors copyActors = getActorsFromSentence(copiedSentence);
						if (copyActors != null && copyActors instanceof Actors) {
							copyActors.getActors().clear();
							copyActors.getActors().add(curActor);
							copyActors.getConjunction().clear();
							if (isPluralActor(curActor)) {
								adaptPluralVerb((Clause) copiedSentence);
							}
							if (clauses != null && clauses instanceof Clauses) {
								((Clauses) clauses).getClauses().add((Clause) copiedSentence);
								((Clauses) clauses).getConjunction().add(actors.getConjunction().get(i - 1));// should
																												// always

							} else {
								logger.error("Clause has not the anticipated type 'Clause'" + clauses.toString());
							}
						} else {
							logger.error("Copied Actors has not anticipated type 'Actors'" + copyActors.toString());
						}

					}
				}
				// Remove the old actors
				actors.getActors().clear();
				actors.getActors().add(firstActor);
				actors.getConjunction().clear();
				// adapt verbs (singular - plural)
				if (isPluralActor(firstActor)) {
					adaptPluralVerb((Clause) sentence);
				}
				copier.copyReferences();
			}
		}
	}

	private boolean isPluralActor(Actor firstActor) {
		if (firstActor.getActor().endsWith("s")) {
			return true;
		}
		// TODO USE dictonary for plural nominatives
		return false;
	}

	private Actors getActorsFromSentence(EObject copiedSentence) {
		if (copiedSentence instanceof ModalitySentence) {
			return ((ModalitySentence) copiedSentence).getActors();
		}
		if (copiedSentence instanceof PredicateSentence) {
			return ((PredicateSentence) copiedSentence).getActors();
		}
		if (copiedSentence instanceof Existence) {
			return ((Existence) copiedSentence).getActors();
		}
		logger.error("Copied Sentence Object has wrong type: " + copiedSentence.toString());
		return null;
	}

	private void adaptPluralVerb(Clause sentence) {
		if (sentence instanceof ModalitySentence) {
			String auxiliarVerb = ((ModalitySentence) sentence).getAuxiliarVerb();
			if (auxiliarVerb != null) {
				((ModalitySentence) sentence).setAuxiliarVerb(transposePluralVerb(auxiliarVerb));
			}
		}
		if (sentence instanceof PredicateSentence) {
			String auxiliarVerb = ((PredicateSentence) sentence).getAuxiliarVerb();
			if (auxiliarVerb != null) {
				((PredicateSentence) sentence).setAuxiliarVerb(transposePluralVerb(auxiliarVerb));
			}
			transposePluralAuxNeg(((PredicateSentence) sentence).getAuxNeg());
		}
		if (sentence instanceof Existence) {
			RelativeClause relClause = ((Existence) sentence).getRelativeClause();
			for (RelativeSentence relSent : relClause.getSentences()) {
				String auxiliar = relSent.getAuxiliar();
				if (auxiliar != null) {
					relSent.setAuxiliar(transposePluralVerb(auxiliar));
				}
				String auxNeg = relSent.getAuxNeg();
				if (auxNeg != null) {
					relSent.setAuxNeg(transposePluralVergNegation(auxNeg));
				}
			}
			// Further COnditional Clauses of RelativeClause have not be considered because
			// they have their own actors
		}
	}

	private void transposePluralAuxNeg(AuxNeg auxNeg) {
		if (auxNeg != null) {
			auxNeg.setAuxiliarVerb(transposePluralVerb(auxNeg.getAuxiliarVerb()));
			auxNeg.setAuxiliarVerbNeg(transposePluralVergNegation(auxNeg.getAuxiliarVerbNeg()));
		}
	}

	private String transposePluralVerb(String verb) {
		if (verb == null) {
			return null;
		}
		switch (verb) {
		case "are":
			return "is";
		case "have":
			return "has";
		default:
			return verb;
		}
	}

	private String transposePluralVergNegation(String verb) {
		if (verb == null) {
			return null;
		}
		switch (verb) {
		case "don't":
			return "doesn't";
		case "haven't":
			return "hasn't";
		case "aren't":
			return "isn't";
		case "don´t":
			return "doesn´t";
		case "haven´t":
			return "hasn´t";
		case "aren´t":
			return "isn´t";
		case "don`t":
			return "doesn`t";
		case "haven`t":
			return "hasn`t";
		case "aren`t":
			return "isn`t";
		default:
			return verb;
		}
	}

	/**
	 * Resolves conjunction of actor properties in order for all actor properties
	 * have their own clause
	 * 
	 * @param modelIterator
	 * 
	 * @param the           initial model for given requirements
	 * @return the resolved model
	 */
	protected void resolveresolveActorPropertyConjunction(EObject inputObj) {
		List<ActorProperties> actorPropList = EcoreUtil2.getAllContentsOfType(inputObj, ActorProperties.class);
		for (ActorProperties properties : actorPropList) {
			if (properties.getProperty().size() > 1) {
				logger.info("Normalizing Actor Properties " + properties.toString());
				Copier copier = new Copier();
				// We need the top clause to duplicate it
				EObject sentence = properties.eContainer();// Sentence to Copy
				// Container can be ExistenceSentence, ModalitySentence,PredicateSentence ->
				// These have to be duplicated for each actor
				EObject clauses = (sentence.eContainer());// clauses to add copy sentenced
				ActorProperty firstProperty = null;
				for (int i = 0; i < properties.getProperty().size(); i++) {
					if (i == 0) {
						firstProperty = properties.getProperty().get(0);
					} else {
						ActorProperty prop = properties.getProperty().get(i);
						// clone the sentence
						PropertySentence copiedSentence = (PropertySentence) copier.copy(sentence);

						ActorProperties copyProperties = copiedSentence.getProperties();
						if (copyProperties != null && copyProperties instanceof ActorProperties) {
							copyProperties.getProperty().clear();
							copyProperties.getProperty().add(prop);
							copyProperties.getConjunction().clear();
							if (isPluralProperty(prop)) {
								adaptPluralVerb(copiedSentence);
							}
							if (clauses != null && clauses instanceof Clauses) {
								((Clauses) clauses).getClauses().add(copiedSentence);
								((Clauses) clauses).getConjunction().add(properties.getConjunction().get(i - 1));// should
																													// always
							} else {
								logger.error("Clause has not the anticipated type 'Clause'" + clauses.toString());
							}
						} else {
							logger.error("Copied Ibhect has not anticipated type 'Actor Properties'"
									+ copyProperties.toString());
						}

					}
				}
				// Remove the old actors
				properties.getProperty().clear();
				properties.getProperty().add(firstProperty);
				properties.getConjunction().clear();
				// adapt verbs (singular - plural)
				// Assuming Property always follow by singular
				if (isPluralProperty(firstProperty)) {
					adaptPluralVerb((Clause) sentence);
				}
				copier.copyReferences();
			}
		}
	}

	private boolean isPluralProperty(ActorProperty firstProperty) {
		EList<String> propertyWords = firstProperty.getProperty().getProperty();
		if (propertyWords.isEmpty() || propertyWords.get(propertyWords.size() - 1).endsWith("s")) {
			return true;
		}
		// TODO USE dictonary for plural nominatives
		return false;
	}

	protected void resolveActorRelObjectConjunction(EObject inputObj) {
		List<ActorPropertyRelation> actRelations = EcoreUtil2.getAllContentsOfType(inputObj,
				ActorPropertyRelation.class);
		for (ActorPropertyRelation actRel : actRelations) {
			RelObjects relObj = actRel.getRelElements();
//		if (relObj.getObject().size() > 1 || relObj.getProperty().size() > 1) {
			if (relObj != null && relObj.getObject().size() > 1) {
				logger.info("Normalizing Relative Objects and Object Properties " + relObj.toString());
//			(object+=Object|property+=RelObjectProperty) (relConj+=RelConjunction (object+=Object | property+=RelObjectProperty))*
				Copier copier = new Copier();
				// We need the top clause to duplicate it
				EObject sentence = getSentence(relObj);// Sentence to Copy
				// These have to be duplicated for each actor
				EObject clauses = (sentence.eContainer());// clauses to add copy sentenced
				EObject first = null;
				// Evaluate Objects
				for (int i = 0; i < relObj.getObject().size(); i++) {
					if (i == 0) {
						first = relObj.getObject().get(0);
					} else {
						EObject obj = relObj.getObject().get(i);
						// clone the sentence
						EObject copiedSentence = copier.copy(sentence);
						RelObjects copiedRelObjects = getCopiedActorRelObjects(relObj, copiedSentence);
						if (copiedRelObjects != null) {
							copiedRelObjects.getObject().clear();
							copiedRelObjects.getObject().add(obj);
							copiedRelObjects.getRelConj().clear();
							lowerCaseRelation(copiedRelObjects);
							if (clauses != null && clauses instanceof Clauses) {
								((Clauses) clauses).getClauses().add((Clause) copiedSentence);
								Conjunction clauseConj = RequirementDSLFactory.eINSTANCE.createConjunction();
								clauseConj.setText(normalizeRelConjunction(relObj.getRelConj().get(i - 1).getText()));
								clauseConj.setPriority(1);// low priority
								((Clauses) clauses).getConjunction().add(clauseConj);
							} else {
								logger.error("Clause has not the anticipated type 'Clause'" + clauses.toString());
							}
						}
					}
				}
//			//removed because Object Properties and Objects are handled identical
//			// Evaluate Object Properties
//			for (int i = 0; i < relObj.getProperty().size(); i++) {
//				if (i == 0 && first == null) {
//					first = relObj.getProperty().get(0);// only set if there are only objectproperties
//				} else {
//					RelObjectProperty prop = relObj.getProperty().get(i);
//					// clone the sentence
//					EObject copiedSentence = copier.copy(sentence);
//					RelObjects copiedRelObjects = getCopiedRelObjects(relObj, copiedSentence);
//					if (copiedRelObjects != null) {
//						copiedRelObjects.getProperty().clear();
//						copiedRelObjects.getProperty().add(prop);
//						copiedRelObjects.getRelConj().clear();
//						lowerCaseRelation(copiedRelObjects);
//						if (clauses != null && clauses instanceof Clauses) {
//							((Clauses) clauses).getClauses().add((Clause) copiedSentence);
//							Conjunction clauseConj = RequirementDSLFactory.eINSTANCE.createConjunction();
//							clauseConj.setText(relObj.getRelConj().get(i - 1).getText());
//							clauseConj.setPriority(1);// low priority
//							((Clauses) clauses).getConjunction().add(clauseConj);
//						} else {
//							logger.error("Clause has not the anticipated type 'Clause'" + clauses.toString());
//						}
//					}
//				}
//			}
				// rework first element from which was copied
				if (first instanceof Object) {
					// Remove the old actors
					relObj.getObject().clear();
					relObj.getObject().add(first);
					relObj.getRelConj().clear();
					// Dont lower case relation as it can be in the first sentence beginning
				}
//			//removed because Object Properties and Objects are handled identical
//			else {// instanceof ObjectProperty
//				// Remove the old actors
//				relObj.getProperty().clear();
//				relObj.getProperty().add((RelObjectProperty) first);
//				relObj.getRelConj().clear();
//				// Dont lower case relation as it can be in the first sentence beginning
//			}
				copier.copyReferences();
			}
		}
	}

	private RelObjects getCopiedActorRelObjects(RelObjects obj, EObject sentence) {
		if (obj.eContainer().eContainer() instanceof ActorProperty) {
			ActorProperty origAProp = (ActorProperty) obj.eContainer().eContainer();
			if (origAProp.eContainer().eContainer() instanceof PropertySentence) {
				EList<ActorProperty> copiedProperies = ((PropertySentence) sentence).getProperties().getProperty();
				for (ActorProperty actorProperty : copiedProperies) {
					if (actorProperty.getObject().getObject().equals(origAProp.getObject().getObject()) && actorProperty
							.getProperty().getProperty().equals(origAProp.getProperty().getProperty())) {
						return actorProperty.getRela().getRelElements();
					}
				}
				logger.error("Matchin RelObject to " + obj.toString() + " has not been found in PropertySentence: "
						+ sentence.toString());
				return null;
			} else {
				logger.error("2 Containers above ActorProperty" + origAProp.toString()
						+ " should be PropertySentence but is not: " + origAProp.eContainer().eContainer().toString());
				return null;
			}
		} else {
			logger.error("Container of Object " + obj.toString() + " is not supported: " + obj.eContainer().toString());
			return null;
		}
	}

	/**
	 * Resolves conjunction of objects in order for all actors have their own clause
	 * 
	 * @param modelIterator
	 * 
	 * @param the           initial model for given requirements
	 * @return the resolved model
	 */
	protected void resolveRelObjectConjunction(EObject inputObj) {
		List<Relation> relations = EcoreUtil2.getAllContentsOfType(inputObj, Relation.class);
		for (Relation rel : relations) {
			RelObjects relObj = rel.getRelElements();
//		if (relObj.getObject().size() > 1 || relObj.getProperty().size() > 1) {
			if (relObj != null && relObj.getObject().size() > 1) {
				logger.info("Normalizing Relative Objects and Object Properties " + relObj.toString());
//			(object+=Object|property+=RelObjectProperty) (relConj+=RelConjunction (object+=Object | property+=RelObjectProperty))*
				Copier copier = new Copier();
				// We need the top clause to duplicate it
				EObject sentence = getSentence(relObj);// Sentence to Copy
				// These have to be duplicated for each actor
				EObject clauses = (sentence.eContainer());// clauses to add copy sentenced
				EObject first = null;
				// Evaluate Objects
				for (int i = 0; i < relObj.getObject().size(); i++) {
					if (i == 0) {
						first = relObj.getObject().get(0);
					} else {
						EObject obj = relObj.getObject().get(i);
						// clone the sentence
						EObject copiedSentence = copier.copy(sentence);
						RelObjects copiedRelObjects = getCopiedRelationObjects(relObj, copiedSentence);
						if (copiedRelObjects != null) {
							copiedRelObjects.getObject().clear();
							copiedRelObjects.getObject().add(obj);
							copiedRelObjects.getRelConj().clear();
							lowerCaseRelation(copiedRelObjects);
							if (clauses != null && clauses instanceof Clauses) {
								((Clauses) clauses).getClauses().add((Clause) copiedSentence);
								Conjunction clauseConj = RequirementDSLFactory.eINSTANCE.createConjunction();
								clauseConj.setText(normalizeRelConjunction(relObj.getRelConj().get(i - 1).getText()));
								clauseConj.setPriority(1);// low priority
								((Clauses) clauses).getConjunction().add(clauseConj);
							} else {
								logger.error("Clause has not the anticipated type 'Clause'" + clauses.toString());
							}
						}
					}
				}
//			//removed because Object Properties and Objects are handled identical
//			// Evaluate Object Properties
//			for (int i = 0; i < relObj.getProperty().size(); i++) {
//				if (i == 0 && first == null) {
//					first = relObj.getProperty().get(0);// only set if there are only objectproperties
//				} else {
//					RelObjectProperty prop = relObj.getProperty().get(i);
//					// clone the sentence
//					EObject copiedSentence = copier.copy(sentence);
//					RelObjects copiedRelObjects = getCopiedRelObjects(relObj, copiedSentence);
//					if (copiedRelObjects != null) {
//						copiedRelObjects.getProperty().clear();
//						copiedRelObjects.getProperty().add(prop);
//						copiedRelObjects.getRelConj().clear();
//						lowerCaseRelation(copiedRelObjects);
//						if (clauses != null && clauses instanceof Clauses) {
//							((Clauses) clauses).getClauses().add((Clause) copiedSentence);
//							Conjunction clauseConj = RequirementDSLFactory.eINSTANCE.createConjunction();
//							clauseConj.setText(relObj.getRelConj().get(i - 1).getText());
//							clauseConj.setPriority(1);// low priority
//							((Clauses) clauses).getConjunction().add(clauseConj);
//						} else {
//							logger.error("Clause has not the anticipated type 'Clause'" + clauses.toString());
//						}
//					}
//				}
//			}
				// rework first element from which was copied
				if (first instanceof Object) {
					// Remove the old actors
					relObj.getObject().clear();
					relObj.getObject().add(first);
					relObj.getRelConj().clear();
					// Dont lower case relation as it can be in the first sentence beginning
				}
//			//removed because Object Properties and Objects are handled identical
//			else {// instanceof ObjectProperty
//				// Remove the old actors
//				relObj.getProperty().clear();
//				relObj.getProperty().add((RelObjectProperty) first);
//				relObj.getRelConj().clear();
//				// Dont lower case relation as it can be in the first sentence beginning
//			}
				copier.copyReferences();
			}
		}
	}

	private String normalizeRelConjunction(String text) {
		if (text.contains("and")) {
			return "and";
		}
		if (text.contains("or")) {
			return "or";
		}
		// ERROR -should not happen
		return "ERROR";
	}

	private void lowerCaseRelation(RelObjects obj) {
		if (obj != null) {
			EObject relation = obj.eContainer();
			if (relation != null && relation instanceof Relation) {
				((Relation) relation).setRelDel(((Relation) relation).getRelDel().toLowerCase());
			}
		}
	}

	/**
	 * Finds the correct RelObjects in the copied Sentece based on the Backtracking
	 * for the original RelObjects
	 * 
	 * @param obj      the original RelObjects
	 * @param sentence the copied Sentence
	 */
	private RelObjects getCopiedRelationObjects(RelObjects obj, EObject sentence) {
		// Backtrack where we are here
		if (obj.eContainer().eContainer() instanceof SentenceBegin) {
			// get RelObjects via SentenceBegin
			if (sentence instanceof PredicateSentence) {
				return ((PredicateSentence) sentence).getBegin().getRela().getRelElements();
			} else if (sentence instanceof ModalitySentence) {
				return ((ModalitySentence) sentence).getBegin().getRela().getRelElements();
			} else {
				logger.error(
						"Type of Sentence" + sentence.toString() + "is not supported for route via 'SentenceBegin' ");
				return null;
			}
		}
		if (obj.eContainer().eContainer() instanceof SentenceEnding) {
			// get RelObjects via SentenceEnding
			if (sentence instanceof PredicateSentence) {
				return ((PredicateSentence) sentence).getEnding().getRela().getRelElements();
			} else if (sentence instanceof ModalitySentence) {
				return ((ModalitySentence) sentence).getEnding().getRela().getRelElements();
			} else if (sentence instanceof PropertySentence) {
				return ((PropertySentence) sentence).getEnding().getRela().getRelElements();
			} else {
				logger.error(
						"Type of Sentence" + sentence.toString() + "is not supported for route via 'SentenceEnding' ");
				return null;
			}
		} else {
			logger.error("Container of Object " + obj.toString() + " is not supported: " + obj.eContainer().toString());
			return null;
		}
	}

	private EObject getSentence(RelObjects obj) {
		if (obj.eContainer().eContainer().eContainer().eContainer() instanceof PropertySentence) {
			return obj.eContainer().eContainer().eContainer().eContainer(); // Relation via actors properties
		} else {
			return obj.eContainer().eContainer().eContainer();// via SentenceBegin/ SentenceEnding
		}
	}

	protected void resolveRelativeClause(EObject inputObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Resolves conjunction of constraints in order for all constraints have their
	 * own clause
	 * 
	 * @param inputObj
	 */
	protected void resolveConstraints(EObject inputObj) {
		// TODO we currently omit this but include it if we introtuce conjunction of
		// constraints
	}

	public RequirementModel reorderSenteceBegin(RequirementModel model) {

		return model;

	}

}
