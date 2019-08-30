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
