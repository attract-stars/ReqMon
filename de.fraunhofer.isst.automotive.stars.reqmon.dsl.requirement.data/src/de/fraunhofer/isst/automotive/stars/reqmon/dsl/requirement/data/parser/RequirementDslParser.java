package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.xtext.parser.ParseException;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Injector;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.analytics.IRequirementElementMapping;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.analytics.repository.RequirementElementMappingRepository;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementImporter;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.RequirementTextElementMapping;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.RequirementDslResourceContentAdappter;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticTextElementSwitch;
import de.fraunhofer.isst.stars.RequirementDSLStandaloneSetup;

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

	protected void setupXtextParser() {
		// TODO SHOULD THIS BE SET OUTSIDE WITH THE RIGHT INJECTOR IN ORDER TO WORK IN
		// ECLIPSE AND IN TESTS
		Injector injector = new RequirementDSLStandaloneSetup().createInjectorAndDoEMFRegistration();
//		Injector injector = RequirementActivator.getInstance()
//				.getInjector(RequirementActivator.DE_FRAUNHOFER_ISST_STARS_REQUIREMENTDSL);
		injector.injectMembers(this);
	}

	protected List<SemanticTextElement> analyze(EObject model) {
		System.out.println("Analyzing Requirement DSL");
		TreeIterator<EObject> modelIterator = model.eAllContents();

		// lets lookup if an analysis of the resource exists
		RequirementElementMappingRepository eleRepo = RequirementElementMappingRepository.getInstance();
		if (eleRepo.containsKey(model.eResource().getURI())) {
			// TODO CHECK FOR CHANGES!!! -> eContentAdpater
			System.out.println("Resource has been analyzed. Recovering previous results");
			IRequirementElementMapping<?, ? extends IRequirementElement> existingMapping = eleRepo
					.get(model.eResource().getURI());
			if (!existingMapping.hasDirtySource()) {
				@SuppressWarnings("unchecked")
				Collection<SemanticTextElement> values = (Collection<SemanticTextElement>) existingMapping.values();
				return Lists.newArrayList(Sets.newHashSet(values));
			} else {
				System.out.println("Resource has been modified. Mapping not update. Re-Analyzing...");
			}
			// return new ArrayList<SemanticTextElement>(values);
		}

		// STARTING THE ANALYSIS
		System.out.println("STARTING: Analysis of AST");
		RequirementTextElementMapping mapping = new RequirementTextElementMapping();

		SemanticTextElementSwitch visitor = new SemanticTextElementSwitch();
		while (modelIterator.hasNext()) {
			EObject obj = modelIterator.next();
//			System.out.println("Analyzing: " + obj.toString());
			SemanticTextElement element = visitor.doSwitch(obj);
			if (element != null) {
				mapping.put(obj, element);
			} else {
				// null object tell we not defined a semantic object for the GUI -> Skip it
//				System.out.println("Visitor provided null Element: "+obj.toString());
			}
		}
		// Saving Mapping from syntactic elements to semantic elements in Repository
		eleRepo.put(model.eResource().getURI(), mapping);
		// Add a Adapter to see if resource is changed to not load a dirty mapping
		// TODO FUNCKTIONIERT GAR NICHT!!!
		EContentAdapter changeModellAdapter = new RequirementDslResourceContentAdappter(mapping);
		changeModellAdapter.setTarget(model.eResource().getResourceSet());
		model.eResource().getResourceSet().eAdapters().add(changeModellAdapter);
		System.out.println(model.eResource().getResourceSet().eAdapters().size());
		model.eResource().eAdapters().add(changeModellAdapter);
		model.eAdapters().add(changeModellAdapter);
		// Return the SemanticElement from Lookup of the visitor Class
		return visitor.getSingleSemanticTextElements();
	}

	protected EObject parseDslRequirementFile(URI file) throws ParseException {
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
