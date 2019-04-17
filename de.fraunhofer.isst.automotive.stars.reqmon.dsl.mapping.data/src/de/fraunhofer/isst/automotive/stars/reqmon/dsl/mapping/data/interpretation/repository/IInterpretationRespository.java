package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.interpretation.repository;

import java.util.Map;

/**
 * This interface for the repository to maintain the Interpretation of DSL entities and system signals/Data objects
 * @author mmauritz
 *
 * @param <T>
 */
public interface IInterpretationRespository<T> extends Map<String, T>{

}
