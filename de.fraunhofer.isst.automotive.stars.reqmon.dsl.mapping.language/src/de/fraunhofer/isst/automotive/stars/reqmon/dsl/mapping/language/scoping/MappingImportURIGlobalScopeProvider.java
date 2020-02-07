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
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.scoping;


import java.util.LinkedHashSet;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.scoping.impl.ImportUriGlobalScopeProvider;

/**
 * This class extends the ImportUriGlobalScopeProvider, so that a system model URI can be added to the LinkedHashSet of imported URIs at runtime.
 * 
 * @author sgraf
 *
 */
public class MappingImportURIGlobalScopeProvider extends ImportUriGlobalScopeProvider {

	public MappingImportURIGlobalScopeProvider() {
		super();
	}
	
	@Override
	protected LinkedHashSet<URI> getImportedUris(Resource resource) {
		URI modelUri = ScopingProviderHelper.getInstance().getModelUri();
		if (modelUri == null) {
			/*System.out.println("No modelUri! Set default URI!");
			String defUri = "platform:/plugin/de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.systemimporter/models/Systemdefinition.sysdef";
			modelUri = URI.createURI(defUri);*/
			return null;
		}
		
		LinkedHashSet<URI> importedURIs = super.getImportedUris(resource);
		if (modelUri != null) {
			importedURIs.add(modelUri);
		}
		return importedURIs;
	}
	
	/**
	 * Sets the system model URI.
	 * @param modelUri the system model URI
	 */
	public void setModelUri(URI modelUri) {
		ScopingProviderHelper.getInstance().setModelUri(modelUri);
	}
	
	
	
}
