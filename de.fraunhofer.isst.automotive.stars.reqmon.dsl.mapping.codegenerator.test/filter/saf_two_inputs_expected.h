#define OID_DADAS_SCENE_ABSTRACTION "de.fraunhofer.isst.automotive.stars.reqmon.dsl.data.monitoring.scene.abstraction"


class cDadasSceneAbstractionFilter : public cConditionTriggeredFilter
{
	ADTF_DECLARE_FILTER_VERSION(OID_DADAS_SCENE_ABSTRACTION, "DADAS Scene Abstraction Filter", OBJCAT_DataFilter, "Version", 0, 1, 0, "Scene Abstraction")
	
	private: // private members
		cInputPin m_oSceneInput;
		cInputPin m_oTimeInput;
		
		cOutputPin m_oCategorizationOutput;
		
		
		cKernelMutex kernelMutex;
		cKernelTimeout m_oTimeout;
		tBool m_bTimeout;
		
	public:
		cDadasSceneAbstractionFilter(const tChar* __info);
		virtual ~cDadasSceneAbstractionFilter();
		
	private: // private functions
		
	public: // overwrites cConditionTriggeredFilter
		tResult Init(tInitStage eStage, __exception = NULL);
		tResult Start(__exception);
		tResult Stop(__exception);
		tResult Shutdown(tInitStage eStage, __exception);
		
	public: 
		tResult OnPinEvent(IPin* pSource,
			tInt nEventCode,
			tInt nParam1,
			tInt nParam2,
			IMediaSample* pMediaSample);
		
	protected: 
		tResult OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception = NULL);
		tCategorization* Evaluate(IMediaSample* pSceneSample, IMediaSample* pTimeSample);
		tResult TransmitEvaluationResult(tCategorization* evaluationResult);
		void LOG(cString mes);
};

