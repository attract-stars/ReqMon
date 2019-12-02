package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

class CMakeListsTemplate {
	
	def CharSequence generateCMakeListForProject() '''
	# cmake file for building STARS Package
	
	# CMake compatibility issues: don't modify this, please!
	CMAKE_MINIMUM_REQUIRED( VERSION 2.8.4 FATAL_ERROR)
	SET(CMAKE_USE_RELATIVE_PATHS true)
	
	#####################################################################
	## Makro definitions for path handling
	#####################################################################
	macro(MACRO_GETENV_WIN_PATH var name)
	   set(${var} $ENV{${name}})
	   string( REGEX REPLACE "\\\\" "/" ${var} ${${var}} )
	endmacro(MACRO_GETENV_WIN_PATH var name)
	
	macro(MACRO_GET_WIN_PATH var)
	   string( REGEX REPLACE "/" "\\\\" var ${var} )
	endmacro(MACRO_GET_WIN_PATH var)
	
	macro(SEARCH_CONFIG_FILES result curdir)
	  #message(STATUS "${curdir}")
	  FILE(GLOB children ${curdir}/*)
	  SET(dirlist "")
	  FOREACH(child ${children})
		IF(IS_DIRECTORY ${child})
			IF(NOT ${child} STREQUAL "${CMAKE_BINARY_DIR}")
				SEARCH_SINGLE_FOLDER(${result} ${child})
			ENDIF()
		ELSE()
		ENDIF(IS_DIRECTORY ${child})
	  ENDFOREACH()
	endmacro()
	
	macro(SEARCH_SINGLE_FOLDER return_list curdir)
	    FILE(GLOB_RECURSE new_list ${curdir}/*.cmake)
	    SET(dir_list "")
		#message(STATUS ${new_list})
	    FOREACH(file_path ${new_list})
	        GET_FILENAME_COMPONENT(dir_path ${file_path} PATH)
	        #message(STATUS ${dir_path})
			set(dir_list ${dir_list} ${dir_path})
	    ENDFOREACH()
	    LIST(REMOVE_DUPLICATES dir_list)
	    LIST(APPEND ${return_list} ${dir_list})
	endmacro()
	
	#####################################################################
	## allow more human readable "if then else" constructs
	#####################################################################
	SET( CMAKE_ALLOW_LOOSE_LOOP_CONSTRUCTS TRUE )
	
	#####################################################################
	## cmake policies 
	## http://www.cmake.org/Wiki/CMake/Policies#Interaction_with_Previous_Compatibility_Mechanisms
	#####################################################################
	cmake_policy(SET CMP0002 NEW)
	cmake_policy(SET CMP0003 NEW)
	cmake_policy(SET CMP0011 NEW)
	
	#####################################################################
	## project declaration with version info
	#####################################################################
	PROJECT( STARS )
	
	# project version
	SET( ${PROJECT_NAME}_MAJOR_VERSION 0 )
	SET( ${PROJECT_NAME}_MINOR_VERSION 1 )
	SET( ${PROJECT_NAME}_PATCH_LEVEL 0 )
	
	#####################################################################
	## set windows x64/32 and linux specific path and options
	#####################################################################
	IF(WIN32)
		IF("$ENV{}" STREQUAL "")
			set(ENV{} "C:/STARS_LIBRARIES")
		ELSE()
			MACRO_GETENV_WIN_PATH(DIR "")
			set(ENV{} ${DIR})
		endif()	
		
	    if(EXISTS "$ENV{STARS_LIB_DIR}/install_paths.txt")
	        message(STATUS "reading installation path from $ENV{STARS_LIB_DIR}/install_paths.txt")
	        
	        include("$ENV{STARS_LIB_DIR}/install_paths.txt")
	  
	        set(ENV{ADTF_DIR}       "$ENV{KEFFS_LIB_DIR}/${_ADTF_INSTALL_DIR${_INSTALL_ARCH_POSTFIX}}")
			set(ENV{OSG_DIR}       "$ENV{KEFFS_LIB_DIR}/${_OSG_DIR}")
			set(ENV{QT_DIR}       "$ENV{KEFFS_LIB_DIR}/${_QT_DIR}")
			set(ENV{QTDIR}          "$ENV{KEFFS_LIB_DIR}/${_QT_INSTALL_DIR${_INSTALL_ARCH_POSTFIX}}")
			   message(STATUS "$ENV{OSG_DIR}")
			   message(STATUS "$ENV{QT_DIR}")
		endif()
			
	ELSE()  # for unix/max
			set(ADTF_DIR "/opt/adtf")
	ENDIF()
	
	set(ENV{PATH} "$ENV{QTDIR}/bin:$ENV{PATH}")
	set(ENV{LD_LIBRARY_PATH} "$ENV{QTDIR}/lib")
	find_package(Qt 4.7.1 EXACT QUIET COMPONENTS QtCore QtGui QtOpenGL HINT $ENV{QTDIR})
	
	
	#####################################################################
	## Say that we want to use ADTF
	#####################################################################
	find_package(ADTF REQUIRED)
	#SET(ADTF_BUILD_DISPLAY_TOOLBOX 1)
	find_package(ADTF_DISPLAY_TOOLBOX PATHS "${ADTF_DIR}/addons/adtf-display-toolbox" REQUIRED)
	if(ADTF_FOUND)
	    message(STATUS "-- Found ADTF")
		if((NOT CMAKE_INSTALL_PREFIX) OR (CMAKE_INSTALL_PREFIX STREQUAL "C:/Program Files/DADAS"))
			set(CMAKE_INSTALL_PREFIX "${ADTF_DIR}/addons/DADAS/" CACHE STRING "Choose the plb directory." FORCE)
			message(STATUS "Setting install prefix to ${CMAKE_INSTALL_PREFIX}.")
		endif()
	endif()  
	if(ADTF_DISPLAY_TOOLBOX_FOUND)
		message(STATUS "-- Found ADTF Display Toolbox")
		link_directories("${ADTF_DISPLAY_TOOLBOX_DIR}/lib")
	endif()
	
	find_package(ADTF_DEVICE_TOOLBOX PATHS "${ADTF_DIR}/addons/adtf-device-toolbox" REQUIRED)
	
	add_definitions(-DUSING_CMAKE)
	
	if(NOT WIN32)
		if(NOT USING_ICC)
			message(STATUS "hallooooooo ")
			SET(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++14 -Wno-unused -Wno-unknown-pragmas -Wno-reorder -Wno-sign-compare -Wno-write-strings -Wno-switch -Wno-uninitialized -Wno-unused-but-set-variable -fpermissive")
		else()
			SET(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -gcc -pragma-optimization-level=GCC -fvisibility=extern -ansi -std=c++0x -D_REENTRANT -Wall -Werror -Wcheck -Wformat -Wmissing-declarations -Wmissing-prototypes -Wpointer-arith -Wuninitialized -Wmain -Wcomment -Wpragma-once -Wshadow -fdata-sections -pthread -wd1418 -wd193 -wd869 -wd981 -wd444 -wd7 -wd10156 -we1572 -we1 -gcc-name=gcc-4.4 -gxx-name=g++-4.4  -Wno-unused -Wno-unknown-pragmas -Wno-reorder -Wno-sign-compare -Wno-write-strings -Wno-switch -Wno-uninitialized -Wno-unused-but-set-variable -fpermissive")
		endif()
	else() 
		#SET(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wd4018")
		add_definitions( "/wd4996 /wd4005 /wd4616 /wd4800 /w24706 /w24703 /w24702 /w24701 /we4700 /W3 /arch:SSE2 -D_CRT_SECURE_NO_WARNINGS -D_CRT_NONSTDC_NO_DEPRECATE")
	    
	    if(MSVC_VERSION GREATER 1599) #for VS 2010
	        add_definitions("/MP")
	    endif()
	    
	    if(MSVC_VERSION GREATER 1700) #vs2012 and upwards -> support toolsets
	        if(CMAKE_CL_64)
	            set(CMAKE_GENERATOR_TOOLSET "v100" CACHE STRING "Platform Toolset" FORCE) #force v100 (VS2010) toolset
	        else()
	            set(CMAKE_GENERATOR_TOOLSET "v90" CACHE STRING "Platform Toolset" FORCE) #force v90 (VS2008) toolset          
	        endif()
	    endif()
	    
	endif()
	
	#####################################################################
	## find components for display-toolbox and toolbox itself
	#####################################################################
	find_package(OpenSceneGraph 2.0.0 COMPONENTS osgViewer osgGA osgFX osgDB osgUtil osgText OpenThreads)
	if(OPENSCENEGRAPH_FOUND)
	    message(STATUS "-- Found OSG")
	endif()
	
	find_package(ADTF_DISPLAY_TOOLBOX HINTS "${ADTF_DIR}/addons/adtf-display-toolbox")
	if(ADTF_DISPLAY_TOOLBOX_FOUND)
	    message(STATUS "-- Found ADTF Display Toolbox")
	    SET(ADTF_DISPLAY_TOOLBOX_INCLUDE_DIR "${ADTF_DISPLAY_TOOLBOX}/include")
	endif()
		
		
	#####################################################################
	## Global build options
	#####################################################################
	
	option(BUILD_MONITOR 				"Build complete environment: Monitor" true)
	
	###### Perception ######
	if(BUILD_MONITOR)
		message(STATUS "Build complete environment: Monitor")
		add_subdirectory(abstract_function_filter)
		add_subdirectory(functional_correctness_oracle_filter)
		add_subdirectory(scene_abstraction_filter)
		add_subdirectory(types)
	endif(BUILD_MONITOR)
	'''
	
	def CharSequence generateCMakeListForFilterFolder(FilterType type) '''
	SET(_FILTER_VERS_ 1.0)
	SET(_FILTER_NAME_ «type.getFilterName»)
	
	PROJECT(${_FILTER_NAME_})
	
	SET(_FILTER_HEADERS_
		"«type.filterName.toString.toLowerCase».h"
		"../types/stdafx.h"
		"../types/dtypes.h"
		"../types/system_types.h"
		"../types/requirement_types.h"
		)
	
	SET(_FILTER_SOURCES_
		"«type.filterName.toString.toLowerCase».cpp"
		)
	
	# Specify the filter module target
	adtf_add_filter(${_FILTER_NAME_} ${_FILTER_HEADERS_} ${_FILTER_SOURCES_})
	
	# Specify where it should be installed to
	adtf_install_plugin(${_FILTER_NAME_} bin)
	
	get_filename_component(PARENT_DIR ${CMAKE_CURRENT_SOURCE_DIR} PATH)
	FILE(RELATIVE_PATH SOLUTION_FOLDER_DIR ${CMAKE_SOURCE_DIR} ${PARENT_DIR})
	adtf_set_folder(${_FILTER_NAME_} ${SOLUTION_FOLDER_DIR})
	'''
	
	/**
	 * Returns the class name. 
	 */
	def private getFilterName(FilterType type) {
		switch(type) {
			case ABSTRACT_FUNCTION: '''Abstract_Function_Filter'''
			case FUNCTIONAL_CORRECTNESS_ORACLE: '''Functional_Correctness_Oracle_Filter'''
			case SCENE_ABSTRACTION: '''Scene_Abstraction_Filter'''
			case TEST_COVERAGE_MONITOR: '''Test_Coverage_Monitor_Filter'''
		}
	}
	
}