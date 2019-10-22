package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.logic

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel
import java.util.ArrayList
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.FilterType
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.IModelInformationHelper

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
	
	override setFilterType(FilterType filtertype) {
		//throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getIncludes() {
		//throw new UnsupportedOperationException("TODO: auto-generated method stub")
		return new ArrayList
	}
	
	override isDebugOpt() {
		//throw new UnsupportedOperationException("TODO: auto-generated method stub")
		return false
	}
	
	override getPins() {
		//throw new UnsupportedOperationException("TODO: auto-generated method stub")
		return new ArrayList
	}
	
	override getInputPins() {
		//throw new UnsupportedOperationException("TODO: auto-generated method stub")
		return new ArrayList
	}
	
	override getOutputPins() {
		//throw new UnsupportedOperationException("TODO: auto-generated method stub")
		return new ArrayList
	}
	
	override getAttributes(String objectName) {
		//throw new UnsupportedOperationException("TODO: auto-generated method stub")
		return new ArrayList
	}
	
	override getSystemAttribut(String string) {
		//throw new UnsupportedOperationException("TODO: auto-generated method stub")
		return ""
	}
	
	override getCorrespondingAttribute(String monAttr) {
		//throw new UnsupportedOperationException("TODO: auto-generated method stub")
		return ""
	}
	
	override getSignalBoundarys(String attr) {
		//throw new UnsupportedOperationException("TODO: auto-generated method stub")
		return new ArrayList
	}
	
	
}