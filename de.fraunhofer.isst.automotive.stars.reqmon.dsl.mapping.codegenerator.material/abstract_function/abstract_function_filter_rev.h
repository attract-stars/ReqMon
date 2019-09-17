#define OID_DADAS_ABSTRACT_FUNCTION	"de.tuc.sse.ipsse.dadas.data.monitoring.abstraction.function.rev.offset"



class cDadasAbstractFunctionRevOffsetFilter : 	public cConditionTriggeredFilter
{
ADTF_DECLARE_FILTER_VERSION(OID_DADAS_ABSTRACT_FUNCTION, "DADAS Abstract Function Filter (Rev. Offset)", OBJCAT_DataFilter, "Version", 0, 2, 0, "DADAS Abstract Function Rev")


private: //private members

	cInputPin					m_oCategorisationInput;
	cInputPin					m_oConcreteTargetsInput;
	cOutputPin                  m_oOutput;

	cObjectPtr<IMediaTypeDescription>		m_pCategorisationCoderDesc;	
	cObjectPtr<IMediaTypeDescription>		m_pTargetLaneCoderDesc;	

	DADAS::tCategorisation categorisation;
	vector<DADAS::tAbstractTarget> inputTargets;
	vector<DADAS::tAbstractTarget> targets;
	
	DADAS::tAbstractTarget bufferLaneChangeTarget;
	//tUInt8 laneChangeMissCount;

	cKernelMutex kernelMutex;
	cKernelTimeout  m_oTimeout;
	tBool           m_bTimeout;

public:
	cDadasAbstractFunctionRevOffsetFilter(const tChar* __info);
	virtual ~cDadasAbstractFunctionRevOffsetFilter();

private:


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
	tResult ProcessSample(IMediaSample* pSample);
	tResult Evaluate(DADAS::tCategorisation &categorisation,vector<DADAS::tAbstractTarget> &targets);
	tResult SendBOOSTTargetlanes(vector<DADAS::tAbstractTarget> &targets);
	tResult CheckCategorisationCorrectness(DADAS::tCategorisation &categorisation);
	DADAS::tAbstractTarget findLaneChangeTarget(vector<DADAS::tAbstractTarget> &targets);
	void LOG(cString mes);
};


