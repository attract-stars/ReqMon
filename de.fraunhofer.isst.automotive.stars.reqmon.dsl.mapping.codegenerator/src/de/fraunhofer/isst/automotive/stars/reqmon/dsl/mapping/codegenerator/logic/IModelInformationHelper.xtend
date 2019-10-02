package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.logic

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel
import java.util.List

/**
 * The implementation of this interface should help to extract the informations of the IMappingModel.
 */
interface IModelInformationHelper {
	
	def void setModel(IMappingModel model)
	
	def int getSourceCount()
	
	def List<String> getClasses()
	
	def List<String> getSignals()
	
}