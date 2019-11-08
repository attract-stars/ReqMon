#define OID_DADAS_ABSTRACT_FUNCTION "$oid_string$"


class cDadasAbstractFunctionFilter : public cConditionTriggeredFilter
{
	ADTF_DECLARE_FILTER_VERSION(OID_DADAS_ABSTRACT_FUNCTION, "DADAS Abstract Function Filter", OBJCAT_DataFilter, "Version", 0, 1, 0, "Abstract Function")
	
	private: // private members
		cInputPin m_oCategorizationInput;
		cInputPin m_oConcreteTargetsInput;
		
		cOutputPin m_oTargetsOutputOutput;
		
		
		
	public:
		cDadasAbstractFunctionFilter(const tChar* __info);
		virtual ~cDadasAbstractFunctionFilter();
		
	private: // private functions
		
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
		void LOG(cString mes);
};

