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
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
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
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.analytics.repository.ModelMappingRepository;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.analytics.repository.RequirementElementMappingRepository;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementImporter;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.RequirementTextElementMapping;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.RequirementDslResourceContentAdappter;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter.SemanticTextElementSwitch;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.normalizer.ReqAstNormalizer;
import de.fraunhofer.isst.stars.RequirementDSLStandaloneSetup;
import de.fraunhofer.isst.stars.requirementDSL.Clauses;
import de.fraunhofer.isst.stars.requirementDSL.Conjunction;
import de.fraunhofer.isst.stars.requirementDSL.RelativeClause;
import de.fraunhofer.isst.stars.requirementDSL.RequirementModel;

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

	Logger logger = Logger.getLogger(ReqAstNormalizer.class);

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
		logger.info("Analyzing Requirement DSL");

		// lets lookup if an analysis of the resource exists
		RequirementElementMappingRepository eleRepo = RequirementElementMappingRepository.getInstance();
		ModelMappingRepository modelRepo = ModelMappingRepository.getInstance();
		if (eleRepo.containsKey(model.eResource().getURI())) {
			// TODO CHECK FOR CHANGES!!! -> eContentAdpater
			logger.info("Resource has been analyzed. Recovering previous results");
			IRequirementElementMapping<?, ? extends IRequirementElement> existingMapping = eleRepo
					.get(model.eResource().getURI());
			if (!existingMapping.hasDirtySource()) {
				@SuppressWarnings("unchecked")
				Collection<SemanticTextElement> values = (Collection<SemanticTextElement>) existingMapping.values();
				return Lists.newArrayList(Sets.newHashSet(values));
			} else {
				logger.info("Resource has been modified. Mapping not update. Re-Analyzing...");
			}
			// return new ArrayList<SemanticTextElement>(values);
		}
		// Setting Priority of original 'AND' and 'OR'
		if (model != null) {
			prioritizeConjunctions(model);// does not change the structure
		}
		// NORMALIZE Requirements
		logger.debug("Starting: Normalization of AST");
		RequirementModel normalizedModel = null;
		if (model instanceof RequirementModel) {
			ReqAstNormalizer normalizer = new ReqAstNormalizer();
			normalizedModel = normalizer.normalize((RequirementModel) model);// chaning model!
		}

		// STARTING THE ANALYSIS
		logger.debug("STARTING: Analysis of AST");
		RequirementTextElementMapping mapping = new RequirementTextElementMapping();

		SemanticTextElementSwitch visitor = new SemanticTextElementSwitch();
		TreeIterator<EObject> modelIterator = null;
		if (normalizedModel == null) {
			modelIterator = model.eAllContents();
		} else {
			modelIterator = normalizedModel.eAllContents();
		}
		if (modelIterator != null) {
			while (modelIterator.hasNext()) {
				EObject obj = modelIterator.next();
				// System.out.println("Analyzing: " + obj.toString());
				SemanticTextElement element = visitor.doSwitch(obj);
				if (element != null) {
					mapping.put(obj, element);
				} else {
					// null object tell we not defined a semantic object for the GUI -> Skip it
					// System.out.println("Visitor provided null Element: "+obj.toString());
				}
			}
		}
		// Saving Mapping from syntactic elements to semantic elements in Repository
		eleRepo.put(model.eResource().getURI(), mapping);
		// Save normalized model in the model mapping
		if (normalizedModel != null) {
			modelRepo.put(model.eResource().getURI(), normalizedModel.eResource().getURI());
		}
		// Add a Adapter to see if resource is changed to not load a dirty mapping
		// TODO FUNCKTIONIERT GAR NICHT!!!
		EContentAdapter changeModellAdapter = new RequirementDslResourceContentAdappter(mapping);
		changeModellAdapter.setTarget(model.eResource().getResourceSet());// THis has to be the original model!
		model.eResource().getResourceSet().eAdapters().add(changeModellAdapter);
		logger.debug(model.eResource().getResourceSet().eAdapters().size());
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
			logger.error(e.getLocalizedMessage());
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

	private void prioritizeConjunctions(EObject model) {
		TreeIterator<EObject> modelIterator = model.eAllContents();
		while (modelIterator.hasNext()) {
			EObject obj = modelIterator.next();
			if (obj != null && obj instanceof Clauses) {
				for (Conjunction conj : ((Clauses) obj).getConjunction()) {
					if (conj.getPriority() == 0) {
						conj.setPriority(100);// normal priority is 100
					}
				}
			} else if (obj != null && obj instanceof RelativeClause) {
				for (Conjunction conj : ((RelativeClause) obj).getConjunction()) {
					if (conj.getPriority() == 0) {
						conj.setPriority(100);// normal priority is 100
					}
				}
			}
		}
	}

}
