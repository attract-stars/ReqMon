#include $file_name1$.h
#include $file_name2$.h
#include $file_name3$.h

$type type_name = value$;

ADTF_FILTER_PLUGIN("$filter_name$", OID_DADAS_$oid_name$, $class_name$)

$class_name$::$class_name$(const tChar* __info) : $cFilter(_info)$
{
	$constructor_implementation$
}

$class_name$::~$class_name$()
{
	$destructor_implementation$
}

tResult $class_name$::Init(tInitStage eStage, __exception)
{
	$init_implementation$
}

tResult $class_name$::Start(__exception)
{
	RETURN_IF_FAILED(cFilter::Start(__exception_ptr));
	
	RETURN_NOERROR;
}

tResult $class_name$::Stop(__exception)
{
	return cFilter::Stop(__exception_ptr);
}

tResult $class_name$::Shutdown(tInitStage eStage, __exception)
{
	switch (eStage)
	{
	case StageFirst:
		{
			m_pCoderDesc = NULL;
			break;
		}
	default:
		{
			break;
		}
	}
	
	return cFilter::Shutdown(eStage, __exception_ptr);
}

$public_methods_implementation$

$protected_methods_implementation$

$private_methods_implementation$
