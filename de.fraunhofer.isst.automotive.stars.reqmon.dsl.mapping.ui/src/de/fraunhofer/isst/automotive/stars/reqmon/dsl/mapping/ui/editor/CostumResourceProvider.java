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
		return xtextResource;
	}

	public Resource getResource() {
		return resource;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
}
