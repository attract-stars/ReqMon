#define OID_DADAS_FUNCTIONAL_CORRECTNESS_ORACLE "de.fraunhofer.isst.automotive.stars.reqmon.dsl.data.monitoring.functional.correctness.oracle"


class cDadasFunctionalCorrectnessOracleFilter : public cConditionTriggeredFilter
{
	ADTF_DECLARE_FILTER_VERSION(OID_DADAS_FUNCTIONAL_CORRECTNESS_ORACLE, "DADAS Functional Correctness Oracle Filter", OBJCAT_DataFilter, "Version", 0, 1, 0, "Functional Correctness Oracle")
	
	private: // private members
		cInputPin m_oCanInput;
		cInputPin m_oCategorizationInput;
		cInputPin m_oAbstractTargetsInput;
		cInputPin m_oConcreteTargetsInput;
		
		
		
		cKernelMutex kernelMutex;
		cKernelTimeout m_oTimeout;
		tBool m_bTimeout;
		
	public:
		cDadasFunctionalCorrectnessOracleFilter(const tChar* __info);
		virtual ~cDadasFunctionalCorrectnessOracleFilter();
		
	private: // private functions
		
	public: // overwrites cConditionTriggeredFilter
		tResult Init(tInitStage eStage, __exception = NULL);
		tResult Start(__exception);
		tResult Stop(__exception);
		tResult Shutdown(tInitStage eStage, __exception);
		
	public: // overrides cConditionTriggeredFilter //implements IRunnable
		tResult Run(tInt nActivationCode,
			const tVoid* pvUserData,
			tInt szUserDataSize,
			ucom::IException** __exception_ptr = NULL);
		
	protected: 
		tResult OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception = NULL);
		tBool Evaluate(IMediaSample* pCanSample, IMediaSample* pCategorizationSample);
		tResult TransmitEvaluationResult(tBool* evaluationResult);
		void LOG(cString mes);
};

