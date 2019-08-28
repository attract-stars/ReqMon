package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.FileExtensionProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.embedded.IEditedResourceProvider;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;

import com.google.inject.Inject;

/**
 * This class implements IEditedResourceProvider from xtext. The resource is created with a dummy-URI. 
 * Important to know: The implemented interface is restricted, so changes are possible!
 * 
 * @author sgraf
 *
 */
@SuppressWarnings("restriction")
public class CostumResourceProvider implements IEditedResourceProvider {

	private Resource resource;
	private int index;
	
	@Inject private IResourceSetProvider resourceSetProvider;
	@Inject private FileExtensionProvider ext;
	
	@Override
	public XtextResource createResource() {
		ResourceSet resourceSet  = resourceSetProvider.get(null);
		URI uri = URI.createURI("dummy:/inmemory_" + index + "." + ext.getPrimaryFileExtension());
		XtextResource xtextResource = (XtextResource) resourceSet.createResource(uri);
		resourceSet.getResources().add(xtextResource);
		this.resource = xtextResource;
		System.out.println("index: " + index);
		return xtextResource;
	}

	public Resource getResource() {
		return resource;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
}
