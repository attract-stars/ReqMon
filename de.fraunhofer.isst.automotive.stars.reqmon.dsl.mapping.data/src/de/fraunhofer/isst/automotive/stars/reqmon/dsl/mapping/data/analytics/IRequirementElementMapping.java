package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.analytics;

import java.util.Map;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;

public interface IRequirementElementMapping<T,S extends IRequirementElement> extends Map<T, S> {

	boolean hasDirtySource();

	void setDirtySource(boolean dirty);

}
