/*******************************************************************************
 * Copyright (C) 2020 Fraunhofer ISST
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
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
	«helper.getComment»
	
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
	«helper.getComment»
	
	#ifndef __TYPES_HEADER_
	#define __TYPES_HEADER_
	
	#define MEDIATYPE_DADAS (MEDIA_TYPE_USER + «helper.getMediaTypeOffset»)
	«FOR type : helper.getMediaSubTypes»
	#define MEDIASUBTYPE_«type»
	«ENDFOR»
	
	#endif //__TYPES_HEADER_
	'''
	
	
	
	
}
