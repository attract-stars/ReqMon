/**
 * 
 */
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.normalizer;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import de.fraunhofer.isst.stars.requirementDSL.Actor;
import de.fraunhofer.isst.stars.requirementDSL.Actors;
import de.fraunhofer.isst.stars.requirementDSL.AuxNeg;
import de.fraunhofer.isst.stars.requirementDSL.Clause;
import de.fraunhofer.isst.stars.requirementDSL.Clauses;
import de.fraunhofer.isst.stars.requirementDSL.Existence;
import de.fraunhofer.isst.stars.requirementDSL.ModalitySentence;
import de.fraunhofer.isst.stars.requirementDSL.PredicateSentence;
import de.fraunhofer.isst.stars.requirementDSL.RelativeClause;
import de.fraunhofer.isst.stars.requirementDSL.RelativeSentence;
import de.fraunhofer.isst.stars.requirementDSL.RequirementModel;

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
		// TODO think about creating an invisible Ressource
		Copier copier = new Copier();
		EObject normalizedModel = copier.copy(model);
//		Collection results = copier.copyAll(eObjects);
		copier.copyReferences();

		if (normalizedModel == null && !(normalizedModel instanceof RequirementModel)) {
			logger.error("Model provided is not instance of RequirementModel!");
			return model;// return unmodified model
		}
		// normalized model instance of Requirement Model
		// iterate over the copied model
		TreeIterator<EObject> modelIterator = normalizedModel.eAllContents();
		while (modelIterator.hasNext()) {
			EObject obj = modelIterator.next();
			if (obj != null && obj instanceof Actors) {
				logger.info("Resolving Actors: " + obj.toString());
				resolveActorConjunction((Actors) obj);
				modelIterator.prune();
			}
		}
		logger.info("Finished normalizing model: " + model.toString());
		return (RequirementModel) normalizedModel;

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
						if (isSignularActor(curActor)) {
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
			if (isSignularActor(firstActor)) {
				adaptPluralVerb((Clause) sentence);
			}
			copier.copyReferences();
		}
	}

	private boolean isSignularActor(Actor firstActor) {
		if (firstActor.getActor().endsWith("s")) {
			return false;
		}
		// TODO USE dictonary for plural nominatives
		return true;
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
	protected RequirementModel resolvePropertyConjunction(RequirementModel model) {
		return model;
	}

	/**
	 * Resolves conjunction of objects in order for all actors have their own clause
	 * 
	 * @param the initial model for given requirements
	 * @return the resolved model
	 */
	protected RequirementModel resolveObjectConjunction(RequirementModel model) {
		return model;
	}

	/**
	 * Resolves conjunction of constraints in order for all constraints have their
	 * own clause
	 * 
	 * @param the initial model for given requirements
	 * @return the resolved model
	 */
	protected RequirementModel resolveConstraints(RequirementModel model) {
		return model;
	}

}
