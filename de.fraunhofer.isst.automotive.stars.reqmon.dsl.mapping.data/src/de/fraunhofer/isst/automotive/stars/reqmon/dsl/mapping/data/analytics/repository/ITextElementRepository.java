package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.analytics.repository;

import java.util.Map;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.SemanticTextElementMapping;

/**
 * Interface for the Repository Service to maintain the Mapping between syntactic elements from DSL to semantic basic elements used as replacement in mapping with system
 * @author mmauritz
 *
 */
//TODO NEED A CLAAS FOR THE ACTUAL MAPPING
public interface ITextElementRepository<S,T extends SemanticTextElementMapping<?, ?>> extends Map<S, T> {
		

}
