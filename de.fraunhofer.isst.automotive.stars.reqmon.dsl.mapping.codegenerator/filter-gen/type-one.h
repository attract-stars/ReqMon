#ifndef $name$
#define $name$

#define OID_DADAS_$oid_name$ "$oid_string$"
$more_defines$

$includes$

class $class_name$ : public cConditionTriggeredFilter
{
	ADTF_DECLARE_FILTER_VERSION(OID_DADAS_$oid_name$, "$filter_name$", OBJCAT_DataFilter, "$Version$", $0, 1, 0$, "$oid_designation$")
	
	private: // private members
		$cInputPin m_oInput1$;
		$cInputPin m_oInput2$;
		
		$cOutputPin m_oOutput1$;
		$cOutputPin m_oOutput2$;
		
		$cObjectPtr<IMediaTypeDescription> m_pCoderDesc1$;
		$cObjectPtr<IMediaTypeDescription> m_pCoderDesc2$;
		
		$cMember member1$;
		$cMember member2$;
		
	public:
		$class_name$(const tChar* __info);
		virtual ~$class_name$();
		
	private: // private functions
		tResult $function1$();
		tResult $function2$();
		
	public: // overwrites cFilter
		tResult Init(tInitStage eStage, __exception = NULL);
		tResult Start(__exception);
		tResult Stop(__exception);
		tResult Shutdown(tInitStage eStage, __exception);
		
	public: // overrides cFilter //implements IRunnable
		tResult Run(tInt nActivationCode,
			const tVoid* pvUserData,
			tInt szUserDataSize,
			ucom::IException** __exception_ptr = NULL);
		
	protected: 
		tResult OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception = NULL);
		tResult Evaluate($parameter$);
		
		$type function1$();
		$type function2$();
		$type function3$();
		
		void LOG(cString mes);
}

#endif
