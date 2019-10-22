#ifndef SCENE_ABSTRACTION_FILTER
#define SCENE_ABSTRACTION_FILTER

#define OID_DADAS_$oid_name$ "$oid_string$"
$more_defines$

$includes$

class cDadasSceneAbstractionFilter : public cFilter
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
		cDadasSceneAbstractionFilter(const tChar* __info);
		virtual ~cDadasSceneAbstractionFilter();
		
	private: // private functions
		tResult $function1$();
		tResult $function2$();
		
	public: // overwrites cFilter
		tResult Init(tInitStage eStage, __exception = NULL);
		tResult Start(__exception);
		tResult Stop(__exception);
		tResult Shutdown(tInitStage eStage, __exception);
		
		
	protected: 
		tResult Evaluate($parameter$);
		
		$type function1$();
		$type function2$();
		$type function3$();
		
		void LOG(cString mes);
}

#endif
