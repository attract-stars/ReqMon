package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.analytics.repository;

import java.util.HashMap;

import org.eclipse.emf.common.util.URI;

public class ModelMappingRepository extends HashMap<URI, URI> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6767133583649765275L;

	private static ModelMappingRepository INSTANCE = null;

	private ModelMappingRepository() {

	}

	public static ModelMappingRepository getInstance() {
		if (ModelMappingRepository.INSTANCE == null) {
			ModelMappingRepository.INSTANCE = new ModelMappingRepository();
		}
		return ModelMappingRepository.INSTANCE;
	}

}
