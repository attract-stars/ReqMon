#include "load_records_interface.h"


#define OID_DADAS_TEST_COVERAGE_MONITOR	"de.tuc.sse.ipsse.dadas.data.monitoring.test.coverage.monitor"


class cDadasTestCoverageMonitorFilter : public ILoadRecordsInterface, public cConditionTriggeredFilter
{
ADTF_DECLARE_FILTER_VERSION(OID_DADAS_TEST_COVERAGE_MONITOR, "DADAS Test Coverage Monitoring Filter", OBJCAT_DataFilter, "Version", 0, 1, 0, "Test Coverage Monitor")

public:

private: //private members

	cKernelMutex kernelMutex;

	adtf::cInputPin						m_oCategorisationInput;
	adtf::cInputPin						m_oTargetsInput;

	adtf::cOutputPin					m_oCoverageOutput;
	adtf::cOutputPin					m_oHistoryOutput;
	adtf::cOutputPin					m_oCombinedMonitoringOutput;
	adtf::cOutputPin					m_oMonitoringInternalOutput;
	adtf::cOutputPin					m_oMonitoringKnowledgeOutput;
	adtf::cOutputPin					m_oLoggingOutput;

	adtf::cOutputPin m_oHistogramNumberOutputPin; 


	cObjectPtr<IMediaTypeDescription> m_pCategorisationCoderDesc;	
	cObjectPtr<IMediaTypeDescription> m_pTargetsCoderDesc;

	cObjectPtr<IMediaTypeDescription> m_pOutputTestCoverageCoderDesc;
	cObjectPtr<IMediaTypeDescription> m_pOutputHistoryCoderDesc;
	cObjectPtr<IMediaTypeDescription> m_pOutputCombinedCoderDesc;
	cObjectPtr<IMediaTypeDescription> m_pOutputInternalCoderDesc;
	cObjectPtr<IMediaTypeDescription> m_pOutputKnowledgeCoderDesc;

	cKernelTimeout  m_oTimeout;
	tBool           m_bTimeout;

	//Front are the old ones -> new are at the back!
	list<DADAS::tMonitoringSituation> oHistory;
	//Set of tested and known situations for iteration
	std::vector<DADAS::tMonitoringSituation> oVerifiedSituations;
	std::vector<DADAS::tMonitoringSituation> situationHistogramCategorization;

	DADAS::eState	eState;

	tUInt16			iHistorySize;
	tBool			automaticAdd;
	cFilename		logFile;
	cFilename		recordFile;
	cString			situationDataStrings;
	cFile			m_oRecordFile;
	cFile			m_oLogFile;

public:
	cDadasTestCoverageMonitorFilter(const tChar* __info);
	virtual ~cDadasTestCoverageMonitorFilter();
	
private:

public: //ovverride ILoadRecordsInterface
	//For Testing
	tResult loadRecordedSituations(cString fileList);
	tResult testClear();
	tResult initializeProperties();
	tUInt Ref();
	tUInt Unref();
	tVoid Destroy();

public: // overwrites cFilter
	tResult Init(tInitStage eStage, __exception = NULL);
	tResult Start(__exception);
	tResult Stop(__exception);
	tResult Shutdown(tInitStage eStage, __exception);

	 //tResult Connect(IPin* pSource,
  //                      const tChar* strDestName,
  //                      __exception=NULL);


	tResult GetInterface(const tChar* riid, tVoid** ppvObject);

public: // overrides cFilter //implements IRunnable
	tResult Run(tInt nActivationCode,
		const tVoid* pvUserData,
		tInt szUserDataSize,
		ucom::IException** __exception_ptr = NULL);



protected:
	tResult OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception = NULL);
	
	tResult Record(DADAS::tMonitoringSituation &currentSituation );
	tResult Monitor(DADAS::tMonitoringSituation &currentSituation );
	DADAS::tMonitoringSituation generateSituation(DADAS::tCategorisation &categorisation, vector<DADAS::tAbstractTarget> &targets);
	tBool EvaluateTestCoverage(DADAS::tMonitoringSituation &monitoringSituation);
	tResult updateHistory(DADAS::tMonitoringSituation &monitoringSituation);
	tResult TransmitBOOSTCoverageResult(tBool  &monResult, DADAS::tMonitoringSituation &monitoringSituation);
	//tResult TransmitCoverageResult(tBool* monResult,DADAS::tMonitoringSituation* monitoringSituation);
	tResult TransmitBOOSTHistory(list<DADAS::tMonitoringSituation>* history);
	//tResult TransmitHistory(list<DADAS::tMonitoringSituation>* history);
	tResult TransmitBOOSTCombindedResult(tBool* monResult,list<DADAS::tMonitoringSituation>* history);
	tResult TransmitBOOSTInternalState();
	tResult TransmitBOOSTMonioringKnowledge();
	tResult TransmitSituationHistogramNumber(tUInt16 sitNum, DADAS::tMonitoringSituation &monitoringSituation);
	void ExistingRecordFile(cFilename &recordFile);
	void UpdateFileNumberTrailer(cFilename &file,tUInt8 number,tChar &delimiter);
	vector<cString> inline Tokenize(const cString &source, const char *delimiter = " ", bool keepEmpty = false);
	tResult SaveRecordFile();
	tResult logMissingSituation(tBool &tested,list<DADAS::tMonitoringSituation> &history);
	tResult updateRecordFile(cFilename &record);
	tUInt8 intLength(tUInt8 num);
	tResult countSituation(DADAS::tMonitoringSituation &monitoringSituation);
	tResult calculateHistogram( DADAS::tMonitoringSituation &monitoringSituation);

	tResult SendStatisticsEvent(tInt32 code);

	void LOG(cString mes);
};


