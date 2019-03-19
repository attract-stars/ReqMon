package de.fraunhofer.isst.stars.reqmon.lmeditor.widgets;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.parser.ParseException;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Inject;

public class XtextParserEclipse {
    @Inject
    private XtextResourceSet resourceSet;

    public XtextParserEclipse() {
	setupParser();
    }

    private void setupParser() {

    }

    public EObject parse(URI uri) throws ParseException {
	try {
	    Resource resource = resourceSet.getResource(uri, true);
	    if (!resource.isLoaded()) {
		resource.load(null);
	    }
	    return resource.getContents().get(0);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
//	    Resource resource = resourceSet.createResource(uri);
	    return null;
	}
    }
}
