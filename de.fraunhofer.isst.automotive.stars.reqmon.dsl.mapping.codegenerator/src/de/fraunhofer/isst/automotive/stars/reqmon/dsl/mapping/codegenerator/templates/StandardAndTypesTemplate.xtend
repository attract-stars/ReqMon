package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

class StandardAndTypesTemplate {
	
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
	
	#define MEDIATYPE_PATH_PLANING (MEDIA_TYPE_USER + 0x5555)
	#define MEDIASUBTYPE_SCENE 0x0010
	#define MEDIASUBTYPE_SCENE_PREDICTED 0x0012
	#define MEDIASUBTYPE_TARGETPOINT      0x0030
	#define MEDIASUBTYPE_TARGETPOINT_MESSAGE 0x0031
	#define MEDIASUBTYPE_TARGETPOINT_MESSAGE_PREDICTED 0x0032
	
	#include <driving/types/dataTypes.h>
	
	#endif //__TYPES_HEADER_
	'''
	
}