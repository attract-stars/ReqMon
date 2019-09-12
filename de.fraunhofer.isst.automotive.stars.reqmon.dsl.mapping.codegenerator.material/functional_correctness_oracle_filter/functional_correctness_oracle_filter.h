#define OID_DADAS_ABSTRACTION_FUNC_ORACLE	"de.tuc.sse.ipsse.dadas.data.monitoring.abstraction.oracle"


class cDadasAbstractionFunctCorrectnessOracleFilter : 	public cConditionTriggeredFilter
{
ADTF_DECLARE_FILTER_VERSION(OID_DADAS_ABSTRACTION_FUNC_ORACLE, "DADAS Functional Correctness Orace Filter", OBJCAT_DataFilter, "Version", 0, 1, 0, "Functional Correctness Oracle")


private: //private members
	adtf::cInputPin					m_oCanInput;

	cInputPin						m_oCategorisationInput;
	adtf::cInputPin					m_oAbstractTargetsInput;
	adtf::cInputPin					m_oConcreteTargetsInput;

	adtf::cOutputPin                m_oBoolOutput;
	adtf::cOutputPin                m_oOutput;
	adtf::cOutputPin				m_oComplexOutput;
	adtf::cOutputPin				m_oLoggingOutput;

	cObjectPtr<IMediaTypeDescription> m_pCategorisationCoderDesc;	
	cObjectPtr<IMediaTypeDescription> m_pAbstractTargetsCoderDesc;
	cObjectPtr<IMediaTypeDescription> m_pConcreteTargetsCoderDesc;
	cObjectPtr<IMediaTypeDescription> m_pOutputBoolCoderDesc;
	cObjectPtr<IMediaTypeDescription> m_pOutputCoderDesc;
	cObjectPtr<IMediaTypeDescription> m_pOutputComplexCoderDesc;

	cKernelTimeout  m_oTimeout;
	tBool           m_bTimeout;

	cKernelMutex kernelMutex;
public:
	cDadasAbstractionFunctCorrectnessOracleFilter(const tChar* __info);
	virtual ~cDadasAbstractionFunctCorrectnessOracleFilter();

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
	tBool EvaluateTargets(vector<DADAS::tAbstractTarget> &abstrTargets, DADAS::tAbstractTarget&concrTarget);
	tBool EvaluateTargets(vector<DADAS::tAbstractTarget> &abstrTargets, vector<DADAS::tAbstractTarget> &concrTarget);
	tResult expandCategorisationHistory(tBool &result,vector<DADAS::tAbstractTarget> &abstrTargets,vector<DADAS::tAbstractTarget> &concrTargets,DADAS::tCategorisation &categorisation);
	tResult transmitBoolResult(tBool &result);
	tResult transmitEvaluationResult(tBool &result, vector<DADAS::tAbstractTarget> &abstrTargets, vector<DADAS::tAbstractTarget> &concrTargets,DADAS::tCategorisation &categorisation);
	tResult transmitSimpleResult(tBool &result, vector<DADAS::tAbstractTarget> &abstrTargets, vector<DADAS::tAbstractTarget> &concrTargets);
	tResult transmitComplexResult(tBool &result, vector<DADAS::tAbstractTarget> &abstrTargets, vector<DADAS::tAbstractTarget> &concrTargets,DADAS::tCategorisation &categorisation);
	tResult logFaultCompliance(tBool &result, vector<DADAS::tAbstractTarget> &abstrTargets, vector<DADAS::tAbstractTarget> &concrTargets, DADAS::tCategorisation &categorisation);
	tResult SendStatisticsEvent(tInt32 code);
	void LOG(cString mes);
};


