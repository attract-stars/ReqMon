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
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.xtext.EcoreUtil2;

import de.fraunhofer.isst.stars.requirementDSL.Actor;
import de.fraunhofer.isst.stars.requirementDSL.ActorProperties;
import de.fraunhofer.isst.stars.requirementDSL.Actors;
import de.fraunhofer.isst.stars.requirementDSL.AuxNeg;
import de.fraunhofer.isst.stars.requirementDSL.Clause;
import de.fraunhofer.isst.stars.requirementDSL.Clauses;
import de.fraunhofer.isst.stars.requirementDSL.Conjunction;
import de.fraunhofer.isst.stars.requirementDSL.Constraints;
import de.fraunhofer.isst.stars.requirementDSL.Existence;
import de.fraunhofer.isst.stars.requirementDSL.ModalitySentence;
import de.fraunhofer.isst.stars.requirementDSL.Object;
import de.fraunhofer.isst.stars.requirementDSL.ObjectProperty;
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

		// reset modeliterator
		TreeIterator<EObject> modelIterator = normalizedModel.eAllContents();
		while (modelIterator.hasNext()) {
			EObject obj = modelIterator.next();
			if (obj != null) {
				if (obj instanceof Actors) {
					logger.info("Resolving Actors: " + obj.toString());
					resolveActorConjunction((Actors) obj);
					modelIterator.prune();
				} else if (obj instanceof ActorProperties) {
					logger.info("Resolving Actor Properties: " + obj.toString());
					resolveresolveActorPropertyConjunction((ActorProperties) obj);
					modelIterator.prune();
				} else if (obj instanceof RelObjects) {
					logger.info("Resolving Relation Objects: " + obj.toString());
					resolveRelObjectConjunction((RelObjects) obj);
					modelIterator.prune();
				} else if (obj instanceof Constraints) {
					logger.info("Resolving Constraints: " + obj.toString());
					resolveConstraints((Constraints) obj);
					modelIterator.prune();
				}
			}
		}
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
	 * @param the initial model for given requirements
	 * @return the resolved model
	 */
	protected void resolveActorConjunction(Actors actors) {
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
			RelativeSentence relSent = relClause.getSentence();
			String auxiliar = relSent.getAuxiliar();
			if (auxiliar != null) {
				relSent.setAuxiliar(transposePluralVerb(auxiliar));
			}
			String auxNeg = relSent.getAuxNeg();
			if (auxNeg != null) {
				relSent.setAuxNeg(transposePluralVergNegation(auxNeg));
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
	 * @param the initial model for given requirements
	 * @return the resolved model
	 */
	protected void resolveresolveActorPropertyConjunction(ActorProperties properties) {
		if (properties.getProperty().size() > 1) {
			logger.info("Normalizing Actor Properties " + properties.toString());
			Copier copier = new Copier();
			// We need the top clause to duplicate it
			EObject sentence = properties.eContainer();// Sentence to Copy
			// Container can be ExistenceSentence, ModalitySentence,PredicateSentence ->
			// These have to be duplicated for each actor
			EObject clauses = (sentence.eContainer());// clauses to add copy sentenced
			ObjectProperty firstProperty = null;
			for (int i = 0; i < properties.getProperty().size(); i++) {
				if (i == 0) {
					firstProperty = properties.getProperty().get(0);
				} else {
					ObjectProperty prop = properties.getProperty().get(i);
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

	private boolean isPluralProperty(ObjectProperty firstProperty) {
		EList<String> propertyWords = firstProperty.getProperty().getProperty();
		if (propertyWords.isEmpty() || propertyWords.get(propertyWords.size() - 1).endsWith("s")) {
			return true;
		}
		// TODO USE dictonary for plural nominatives
		return false;
	}

	/**
	 * Resolves conjunction of objects in order for all actors have their own clause
	 * 
	 * @param the initial model for given requirements
	 * @return the resolved model
	 */
	protected void resolveRelObjectConjunction(RelObjects relObj) {
		if (relObj.getObject().size() > 1 || relObj.getProperty().size() > 1) {
			logger.info("Normalizing Relative Objects and Object Properties " + relObj.toString());
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
					Object obj = relObj.getObject().get(i);
					// clone the sentence
					EObject copiedSentence = copier.copy(sentence);
					RelObjects copiedRelObjects = getCopiedRelObjects(relObj, copiedSentence);
					if (copiedRelObjects != null) {
						copiedRelObjects.getObject().clear();
						copiedRelObjects.getObject().add(obj);
						copiedRelObjects.getProperty().clear();
						copiedRelObjects.getRelConj().clear();
						lowerCaseRelation(copiedRelObjects);
						if (clauses != null && clauses instanceof Clauses) {
							((Clauses) clauses).getClauses().add((Clause) copiedSentence);
							Conjunction clauseConj = RequirementDSLFactory.eINSTANCE.createConjunction();
							clauseConj.setText(relObj.getRelConj().get(i - 1).getText());
							clauseConj.setPriority(1);// low priority
							((Clauses) clauses).getConjunction().add(clauseConj);
						} else {
							logger.error("Clause has not the anticipated type 'Clause'" + clauses.toString());
						}
					}
				}
			}
			// Evaluate Object Properties
			for (int i = 0; i < relObj.getProperty().size(); i++) {
				if (i == 0 && first == null) {
					first = relObj.getProperty().get(0);// only set if there are only objectproperties
				} else {
					ObjectProperty prop = relObj.getProperty().get(i);
					// clone the sentence
					EObject copiedSentence = copier.copy(sentence);
					RelObjects copiedRelObjects = getCopiedRelObjects(relObj, copiedSentence);
					if (copiedRelObjects != null) {
						copiedRelObjects.getProperty().clear();
						copiedRelObjects.getProperty().add(prop);
						copiedRelObjects.getRelConj().clear();
						lowerCaseRelation(copiedRelObjects);
						if (clauses != null && clauses instanceof Clauses) {
							((Clauses) clauses).getClauses().add((Clause) copiedSentence);
							Conjunction clauseConj = RequirementDSLFactory.eINSTANCE.createConjunction();
							clauseConj.setText(relObj.getRelConj().get(i - 1).getText());
							clauseConj.setPriority(1);// low priority
							((Clauses) clauses).getConjunction().add(clauseConj);
						} else {
							logger.error("Clause has not the anticipated type 'Clause'" + clauses.toString());
						}
					}
				}
			}
			// rework first element from which was copied
			if (first instanceof Object) {
				// Remove the old actors
				relObj.getObject().clear();
				relObj.getObject().add((Object) first);
				relObj.getRelConj().clear();
				// Dont lower case relation as it can be in the first sentence beginning
			} else {// instanceof ObjectProperty
				// Remove the old actors
				relObj.getProperty().clear();
				relObj.getProperty().add((ObjectProperty) first);
				relObj.getRelConj().clear();
				// Dont lower case relation as it can be in the first sentence beginning
			}
			copier.copyReferences();
		}

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
	private RelObjects getCopiedRelObjects(RelObjects obj, EObject sentence) {
		// Backtrack where we are here
		if (obj.eContainer().eContainer() instanceof PropertySentence) {
			return ((PropertySentence) sentence).getRela().getRelElements();
			// we can now go in into relation directly
		} else if (obj.eContainer().eContainer() instanceof SentenceBegin) {
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
		if (obj.eContainer().eContainer() instanceof PropertySentence) {
			return obj.eContainer().eContainer(); // Relation for actors properties
		} else {
			return obj.eContainer().eContainer().eContainer();// via SentenceBegin/ SentenceEnding
		}
	}

	/**
	 * Resolves conjunction of constraints in order for all constraints have their
	 * own clause
	 * 
	 * @param the initial model for given requirements
	 * @return the resolved model
	 */
	protected void resolveConstraints(Constraints obj) {
		// TODO we currently omit this but include it if we introtuce conjunction of
		// constraints
	}

	public RequirementModel reorderSenteceBegin(RequirementModel model) {

		return model;

	}

}
