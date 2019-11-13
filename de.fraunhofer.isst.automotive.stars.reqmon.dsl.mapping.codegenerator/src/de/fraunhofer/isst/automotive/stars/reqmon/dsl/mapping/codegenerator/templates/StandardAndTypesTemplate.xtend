package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.AbstractModelInformationHelper

/**
 * This class offers templates for the stdafx.h file and the dtypes.h file. 
 * @author sgraf
 */
class StandardAndTypesTemplate {
	
	AbstractModelInformationHelper helper
	
	new (AbstractModelInformationHelper helper) {
		this.helper = helper
	}
	
	def CharSequence generateStdTemplate() '''
	#ifndef __STD_INCLUDES_HEADER
	#define __STD_INCLUDES_HEADER
	
	#pragma warning (push)
	#pragma warning (disable:858)
	#pragma warning (disable:1125)
	#pragma warning (disable:2259)
	#include <adtf_platform_inc.h>
	#include <adtf_plugin_sdk.h>
	#pragma warning (pop)
	
	#include <additional/datexport/chunksource_intf.h>
	#include <additional/datexport/samplesink_intf.h>
	
	#include <iostream>
	
	using namespace adtf;
	using namespace std;
	
	#endif // __STD_INCLUDES_HEADER
	'''
	
	def CharSequence generateTypesTemplate() '''
	#ifndef __TYPES_HEADER_
	#define __TYPES_HEADER_
	
	#define MEDIATYPE_DADAS (MEDIA_TYPE_USER + «helper.getMediaTypeOffset»)
	«FOR type : helper.getMediaSubTypes»
	#define MEDIASUBTYPE_«type»
	«ENDFOR»
	
	#endif //__TYPES_HEADER_
	'''
	
	
	
}