package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.parser.ParseException;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Injector;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.analytics.repository.RequirementElementMappingRepository;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementImporter;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.RequirementTextElementMapping;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.ui.internal.RequirementActivator;
import de.fraunhofer.isst.stars.requirementDSL.Actor;
import de.fraunhofer.isst.stars.requirementDSL.ConditionalClause;
import de.fraunhofer.isst.stars.requirementDSL.Object;
import de.fraunhofer.isst.stars.requirementDSL.Requirement;
import de.fraunhofer.isst.stars.requirementDSL.RequirementText;

/**
 * @author mmauritz
 *
 */
//TODO THIS CLASS NEEDS AN LOOKUP FOR EXISTING ELEMENTS
public class RequirementDslParser implements IRequirementImporter {

	@Inject
	XtextResourceSet resourceSet;
	@Inject
	IResourceServiceProvider resourceSetProvider;

	public RequirementDslParser() {
		setupXtextParser();
	}

	private void setupXtextParser() {
		Injector injector = RequirementActivator.getInstance()
				.getInjector(RequirementActivator.DE_FRAUNHOFER_ISST_STARS_REQUIREMENTDSL);
		injector.injectMembers(this);
	}

	private List<SemanticTextElement> analyze(EObject model) {
		System.out.println("Analyzing Requirement DSL");
		TreeIterator<EObject> modelIterator = model.eAllContents();

		// lets lookup if an analysis of the resource exists
		RequirementElementMappingRepository eleRepo = RequirementElementMappingRepository.getInstance();
		if (eleRepo.containsKey(model.eResource().getURI())) {// TODO PROBLEM HIER WIRD AUF OBJECT ID VERGLICHEN NICHT
																// NACH Inhalt
			System.out.println("Resource has been analyzed. Recovering previous results");
			Collection<SemanticTextElement> values = (Collection<SemanticTextElement>) eleRepo
					.get(model.eResource().getURI()).values();
			return Lists.newArrayList(Sets.newHashSet(values));
			// return new ArrayList<SemanticTextElement>(values);
		}

		// STARTING THE ANALYSIS
		System.out.println("STARTING: Analysis of AST");
		List<SemanticTextElement> semElements = new ArrayList<SemanticTextElement>();// output for the Mapping View
		RequirementTextElementMapping mapping = new RequirementTextElementMapping();// THe mapping from syn to sem save
																					// in the repository
		HashMap<String, SemanticTextElement> lookup = new HashMap<String, SemanticTextElement>();// local lookup to find
																									// existing sem
																									// elements

		// TODO We might have to implement a Specific Adapter for the Work on the
		// Leaves-> Leaves seems the only think interesting for consolidating the Data
		// -> No also prior objects like constraints are interesting
		// Model objects
		// Whats with relations that are defined over a particular set of other objects
		// / types /etc.
		while (modelIterator.hasNext()) {
			EObject obj = modelIterator.next();
			// TODO MACHT HIER EINE ANALYZER FACTORY SINN DIE F�R JEDES ELEMENT EINE EIGENEN
			// ANALYSE CLASSE AUFRUFT? -> Erweiterbarkeit
			if (obj instanceof Requirement) {
				System.out.println("Analyzing: " + obj.toString());
			}
			if (obj instanceof Object) {
				System.out.println("Analyzing: " + obj.toString());
				if (!((Object) obj).getObject().isEmpty()) {
					StringJoiner objTxt = new StringJoiner(" ");
					for (String text : ((Object) obj).getObject()) {
						// the text object lists all words for the real meaning these word have to be
						// concatenated
						objTxt.add(text);
					}
					if (lookup.containsKey(objTxt.toString())) {
						mapping.put(obj, lookup.get(objTxt.toString()));
					} else {
						SemanticTextElement texElement = new SemanticTextElement(objTxt.toString(),
								RequirementType.OBJECT);
						lookup.put(objTxt.toString(), texElement);
						mapping.put(obj, texElement);// TODO PROBLEM STRINGS SIND KEINE EOBJECT WERDEN ABER SO VERWALTET
						semElements.add(texElement);
					}
					// System.out.println(obj.toString());
				}
			}
			if (obj instanceof Actor) {
				System.out.println("Analyzing: " + obj.toString());
				String actor = ((Actor) obj).getActor();
				if (actor != null && !actor.isEmpty()) {
					if (lookup.containsKey(actor)) {
						mapping.put(obj, lookup.get(actor));
					} else {
						SemanticTextElement texElement = new SemanticTextElement(actor, RequirementType.OBJECT);
						lookup.put(actor, texElement);
						mapping.put(obj, texElement);
						semElements.add(texElement);
					}
				}
			}
		}
		//TODO INSERT INSTANCEOF FOR ALLE OTHER PARTS

		// Saving Mapping from syntactic elements to semantic elements in Repository
		eleRepo.put(model.eResource().getURI(), mapping);
		return semElements;
	}

	protected void analyzeRequirements(org.eclipse.emf.common.util.EList<Requirement> requirements) {
		for (Requirement req : requirements) {
			analyzeRequirementText(req.getText());
		}
	}

	/**
	 * @param text
	 */
	protected void analyzeRequirementText(RequirementText text) {
		analzyeCondClauses(text.getCondClauses());
	}

	/**
	 * @param condClauses
	 */
	protected void analzyeCondClauses(EList<ConditionalClause> condClauses) {
		for (ConditionalClause clause : condClauses) {
			analyzeConditionalClause(clause);
		}

	}

	/**
	 * @param clause
	 */
	private void analyzeConditionalClause(ConditionalClause clause) {
		// TODO Auto-generated method stub

	}

	private EObject parseDslRequirementFile(URI file) throws ParseException {
		try {
			Resource resource = resourceSet.getResource(file, true);
			if (!resource.isLoaded()) {
				resource.load(null);
			}
			return resource.getContents().get(0);
		} catch (Exception e) {
			// TODO Better Exception Handling
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

	@Override
	public void execute(IRequirementController rc, String path) {
		// TODO CATCH OR FORWARD PARSEEXCEPTION
		EObject modelObject = parseDslRequirementFile(URI.createFileURI(path));
		if (modelObject == null) {
			// if nothing in the file or error give empty list back
			rc.updateList(new ArrayList<IRequirementElement>());
		}
		// TODO INTERFERENCE!?
		List<? extends IRequirementElement> testElements = analyze(modelObject);
		if (testElements == null) {
			// if nothing in the file or error give empty list back
			rc.updateList(new ArrayList<IRequirementElement>());
		}
		rc.updateList(testElements);
	}
}
