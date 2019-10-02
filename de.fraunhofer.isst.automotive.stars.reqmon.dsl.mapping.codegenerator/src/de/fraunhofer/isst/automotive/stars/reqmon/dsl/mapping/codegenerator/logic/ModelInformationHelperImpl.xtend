package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.logic

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel
import java.util.ArrayList

class ModelInformationHelperImpl implements IModelInformationHelper {
	
	IMappingModel model
	
	
	override getSourceCount() {
		return 0
	}
	
	override getClasses() {
		return new ArrayList
	}
	
	override getSignals() {
		return new ArrayList
	}
	
	override setModel(IMappingModel model) {
		this.model = model
	}
	
}