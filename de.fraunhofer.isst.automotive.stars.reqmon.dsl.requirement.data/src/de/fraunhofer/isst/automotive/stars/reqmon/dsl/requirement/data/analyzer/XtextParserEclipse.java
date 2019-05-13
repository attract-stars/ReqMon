package de.fraunhofer.isst.stars.reqmon.lmeditor.widgets;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.parser.ParseException;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Inject;
import com.google.inject.Injector;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.ui.internal.RequirementActivator;

public class XtextParserEclipse {
    @Inject
    XtextResourceSet resourceSet;
    @Inject
    IResourceServiceProvider resourceSetProvider;

    public XtextParserEclipse() {
	setupParser();
    }

    private void setupParser() {
	Injector injector = RequirementActivator.getInstance()
			.getInjector(RequirementActivator.DE_FRAUNHOFER_ISST_STARS_REQUIREMENTDSL);
	injector.injectMembers(this);
    }

    public EObject parse(URI uri) throws ParseException {
	try {
	    Resource resource = resourceSet.getResource(uri, true);
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
}
