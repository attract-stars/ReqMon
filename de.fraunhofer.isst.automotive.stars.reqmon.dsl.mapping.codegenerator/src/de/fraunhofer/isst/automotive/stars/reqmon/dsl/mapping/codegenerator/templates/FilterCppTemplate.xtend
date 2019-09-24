package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates

class FilterCppTemplate {
	
	def CharSequence generateTemplate() '''
	«includes»
	
	«datatypeSettings»
	
	ADTF_FILTER_PLUGIN("«filterName»", OID_DADAS_«oidName», «className»)
	
	«className»::«className»(const tChar* __info) : «memberValueSetting»
	{
		«constructor»
	}
	
	«className»::~«className»()
	{
		«destructor»
	}
	
	tResult «className»::Init(tInitStage eStage, __exception)
	{
		«init»
	}
	
	tResult «className»::Start(__exception)
	{
		«start»
	}
	
	tResult «className»::Stop(__exception)
	{
		«stop»
	}
	
	tResult «className»::Shutdown(tInitStage eStage, __exception)
	{
		«shutdown»
	}
	
	«publicMethods»
	
	«protectedMethods»
	
	«privateMethods»
	'''
	
	def getIncludes() '''
	#include $file_name1$.h
	#include $file_name2$.h
	#include $file_name3$.h
	'''
	
	def getDatatypeSettings() '''$type type_name = value$;'''
	
	def getFilterName() '''$filter_name$'''
	
	def getOidName() '''$oid_name$'''
	
	def getClassName() '''$class_name$'''
	
	def getMemberValueSetting() '''$cFilter(_info)$'''
	
	def getConstructor() '''$constructor_implementation$'''
	
	def getDestructor() '''$destructor_implementation$'''
	
	def getInit() '''$init_implementation$'''
	
	def getStart() '''$start_implementation$'''
	
	def getStop() '''$stop_implementation$'''
	
	def getShutdown() '''$shutdown_implementation$'''
	
	def getPublicMethods() '''$public_methods_implementation$'''
	
	def getProtectedMethods() '''$protected_methods_implementation$'''
	
	def getPrivateMethods() '''$private_methods_implementation$'''
	
}