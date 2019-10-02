#define OID_DADAS_FUNCTIONAL_CORRECTNESS_ORACLE "$oid_string$"


class cDadasFunctionalCorrectnessOracleFilter : public cConditionTriggeredFilter
{
	ADTF_DECLARE_FILTER_VERSION(OID_DADAS_FUNCTIONAL_CORRECTNESS_ORACLE, "DADAS Functional Correctness Oracle Filter", OBJCAT_DataFilter, "Version", 0, 1, 0, "Functional Correctness Oracle")
	
	private: // private members
		
		
		
		
	public:
		cDadasFunctionalCorrectnessOracleFilter(const tChar* __info);
		virtual ~cDadasFunctionalCorrectnessOracleFilter();
		
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
		tResult Evaluate($parameter$);
		void LOG(cString mes);
};

