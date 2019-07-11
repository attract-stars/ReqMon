package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.scoping;

import org.eclipse.emf.common.util.URI;

/**
 * This Singleton helps to provide the system model URI for the MappingImportURIGlobalScopeProvider.
 * @author sgraf
 *
 */
public class ScopingProviderHelper {
	
	private static final ScopingProviderHelper instance = new ScopingProviderHelper();
	private URI modelUri;
	
	private ScopingProviderHelper() {}

	
	
	public static ScopingProviderHelper getInstance() {
		return instance;
	}


	/*
	 * Sets the system model URI.
	 */
	public void setModelUri(URI modelUri) {
		this.modelUri = modelUri;
	}


	/**
	 * Returns the system model URI.
	 * @return the system model URI
	 */
	public URI getModelUri() {
		return modelUri;
	}
	
	

}
