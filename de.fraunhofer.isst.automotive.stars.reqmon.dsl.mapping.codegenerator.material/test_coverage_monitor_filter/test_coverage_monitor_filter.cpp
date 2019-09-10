#include "stdafx.h"
#include "dtypes.h"
#include "dadas_monitoring_types.h"
#include "dadas_mediatypes.h"
#include "test_coverage_monitor_filter.h"
#include <fstream>
#include "serializationhelper.h"
#include "dadas_event_codes.h"
#include "boost/serialization/serialization.hpp"
#include "boost/serialization/vector.hpp"
#include "boost/serialization/list.hpp"
#include "boost/archive/xml_iarchive.hpp"
#include "boost/archive/xml_iarchive.hpp"
#include "boost/archive/text_iarchive.hpp"
#include "boost/archive/text_oarchive.hpp"
#include "boost/filesystem/path.hpp"
#include "boost/filesystem.hpp"

//#include <vld.h>

#define OID_DADAS_STATISTICS_SIGNAL "de.tuc.ifi.sse.ipsse.dadas.monitoring.statistics.signal"

tBool debugOpt = tFalse;


ADTF_FILTER_PLUGIN("DADAS Test Coverage Monitoring Filter",OID_DADAS_TEST_COVERAGE_MONITOR, cDadasTestCoverageMonitorFilter)

	cDadasTestCoverageMonitorFilter::cDadasTestCoverageMonitorFilter(const tChar* __info)  : cConditionTriggeredFilter(tTrue,tTrue,__info), ILoadRecordsInterface(),
	m_bTimeout(tFalse)
{
	kernelMutex.Create();

	SetPropertyBool(NSPROP_CONFIGINFO, tTrue); // default false
	SetPropertyBool(NSPROP_CONFIGINFO_ENABLECONFIGOUTPINS, tTrue); // default false


	SetPropertyBool(NSPROP_CONFIGINFO_ENABLEDYNAMICPROPS, tTrue);

	SetPropertyInt("Timeout", 0);
	SetPropertyStr("Timeout" NSSUBPROP_DESCRIPTION,
		"Demo timeout that will issue a warning when no trigger has occurred "
		"in the specified amount of time (microseconds). 0 disables the timeout.");
	SetPropertyInt("Timeout" NSSUBPROP_MINIMUM, 0);

	SetPropertyInt("State", 1);
	SetPropertyStr("State" NSSUBPROP_DISPLAYNAME, "Usage"); 
	SetPropertyStr("State" NSSUBPROP_DESCRIPTION,
		"The acitivity the monitor has to perform."
		"Simulation: Record the encountered situations."
		"Operation: Supervise the test coverage of the real situations.");
	SetPropertyBool("State" NSSUBPROP_REQUIRED, tTrue);
	SetPropertyStr("State" NSSUBPROP_VALUELIST, "1@Simulation|2@Operation"); 
	//SetPropertyStr("State" NSSUBPROP_GLOBALCONFIGPROPERTYNAME, "MonitoringState"); 



	SetPropertyStr("RecordFile", "$CFGDIR$/recording");
	SetPropertyStr("RecordFile" NSSUBPROP_DISPLAYNAME, "Situations Record File"); 
	SetPropertyBool("RecordFile" NSSUBPROP_FILENAME, tTrue); 
	SetPropertyStr("RecordFile" NSSUBPROP_DESCRIPTION,
		"Simulation: The file where the encountered situations are saved for the supervision at operation."
		"This property has no effect in operation usage.");
	SetPropertyStr("RecordFile" NSSUBPROP_FILENAME NSSUBSUBPROP_EXTENSIONFILTER, "Monitoring Records (*.msr)"); 

	SetPropertyBool("AddRecording", tTrue);
	SetPropertyStr("AddRecording" NSSUBPROP_DISPLAYNAME, "Automatically add recording"); 
	SetPropertyStr("AddRecording" NSSUBPROP_DESCRIPTION,
		"Autmatically add recording from the simulation to list of files with the situations data for usage at runtime.");


	SetPropertyStr("SituationData", "$CFGDIR$");
	SetPropertyStr("SituationData" NSSUBPROP_DISPLAYNAME, "Recorded Situation Files"); 
	SetPropertyStr("SituationData" NSSUBPROP_DESCRIPTION,
		"Operation: The Files from which the informationen about the encountered situations is read."
		"This property has no effect in simulation usage.");
	SetPropertyInt("SituationData" NSSUBPROP_MINIMUM, 0);
	//SetPropertyBool("SituationData" NSSUBPROP_FILENAME, tTrue); 
	SetPropertyBool("SituationData" NSSUBPROP_FILENAME_LIST, tTrue);
	SetPropertyStr("SituationData" NSSUBPROP_FILENAME_LIST NSSUBSUBPROP_FILENAME_LIST_SEPARATOR, ";"); 
	SetPropertyStr("SituationData"NSSUBPROP_FILENAME_LIST NSSUBSUBPROP_EXTENSIONFILTER, "Monitoring Records (*.msr)");

	SetPropertyInt("HistorySize", 100);
	SetPropertyStr("HistorySize" NSSUBPROP_DESCRIPTION,
		"The max. size of the history of situations monitored and recorded by the test coverage monitor"
		"0 disables the resize of the history.");
	SetPropertyInt("HistorySize" NSSUBPROP_MINIMUM, 0);


	SetPropertyBool("histogram",tFalse);
	SetPropertyStr("histogram" NSSUBPROP_DESCRIPTION,
	"Annotation of abstract situations for histogram creation.");
	SetPropertyBool("histogram" NSSUBPROP_REQUIRED,tTrue);
}


cDadasTestCoverageMonitorFilter::~cDadasTestCoverageMonitorFilter()
{
	kernelMutex.Release();
}

/**
*   The Filter Init Function.
*    eInitStage ... StageFirst ... should be used for creating and registering Pins
*               ... StageNormal .. should be used for reading the properies and initalizing
*                                  everything before pin connections are made
*   see {@link IFilter#Init IFilter::Init}.
*
*/
tResult cDadasTestCoverageMonitorFilter::Init(tInitStage eStage, __exception)
{
	RETURN_IF_FAILED(cConditionTriggeredFilter::Init(eStage, __exception_ptr));

	if (eStage == StageFirst)
	{ 
		//Input für Categioriation
		cObjectPtr<IMediaType> pCategorisationType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_CATEGORISATION_BOOST);
		RETURN_IF_FAILED(m_oCategorisationInput.Create("Categorisation", pCategorisationType, this));
		RETURN_IF_FAILED(RegisterTriggerPin(&m_oCategorisationInput));
		RETURN_IF_FAILED(pCategorisationType->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_pCategorisationCoderDesc));

		//Input für Abstract Targets
		cObjectPtr<IMediaType> pAbstrTargetOutputType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_TARGET_ABSTRACT_BOOST);
		RETURN_IF_FAILED(m_oTargetsInput.Create("Abstract_Targets", pAbstrTargetOutputType, this));
		RETURN_IF_FAILED(RegisterTriggerPin(&m_oTargetsInput));
		RETURN_IF_FAILED(pAbstrTargetOutputType->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_pTargetsCoderDesc));


		///OUTPUTS

		//Output for the Test Coverage
		cObjectPtr<IMediaType> pTestCoverageType = new cMediaType(MEDIATYPE_DADAS,MEDIASUBTYPE_MONITORING_TEST_COVERAGE_BOOST);
		RETURN_IF_FAILED(m_oCoverageOutput.Create("TestCoverage", pTestCoverageType, this));
		RETURN_IF_FAILED(RegisterPin(&m_oCoverageOutput));
		RETURN_IF_FAILED(pTestCoverageType->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_pOutputTestCoverageCoderDesc));

		//Light Output without the situation
		cObjectPtr<IMediaType> pHistoryType = new cMediaType(MEDIATYPE_DADAS,MEDIASUBTYPE_SITUATION_HISTORY_BOOST);
		RETURN_IF_FAILED(m_oHistoryOutput.Create("Situation_History", pHistoryType, this));
		RETURN_IF_FAILED(RegisterPin(&m_oHistoryOutput));
		RETURN_IF_FAILED(pHistoryType->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_pOutputHistoryCoderDesc));

		//Complex Output with the Situation
		cObjectPtr<IMediaType> pCombinedOutputType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_MONITORING_COMPLEX_BOOST);
		RETURN_IF_FAILED(m_oCombinedMonitoringOutput.Create("Combined_Monitoring_Informations", pCombinedOutputType, this));
		RETURN_IF_FAILED(RegisterPin(&m_oCombinedMonitoringOutput));
		RETURN_IF_FAILED(pCombinedOutputType->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_pOutputCombinedCoderDesc));

		//Internal State
		cObjectPtr<IMediaType> pInternalOutputType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_MONITORING_INTERNAL_BOOST);
		RETURN_IF_FAILED(m_oMonitoringInternalOutput.Create("Monitoring_Internal", pInternalOutputType, this));
		RETURN_IF_FAILED(RegisterPin(&m_oMonitoringInternalOutput));
		RETURN_IF_FAILED(pInternalOutputType->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_pOutputInternalCoderDesc));

		//Knowldege OUtput
		cObjectPtr<IMediaType> pKnowledgeOutputType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_MONITORING_KNOWLEDGE_BOOST);
		RETURN_IF_FAILED(m_oMonitoringKnowledgeOutput.Create("Monitoring_Knowledge", pKnowledgeOutputType, this));
		RETURN_IF_FAILED(RegisterPin(&m_oMonitoringKnowledgeOutput));
		RETURN_IF_FAILED(pInternalOutputType->GetInterface(IID_ADTF_MEDIA_TYPE_DESCRIPTION_EXT, (tVoid**)&m_pOutputKnowledgeCoderDesc));

		//Logging Output
		cObjectPtr<IMediaType> pLoggingOutputType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_LOGGING_MONITORING_COMPLEX_BOOST);
		RETURN_IF_FAILED(m_oLoggingOutput.Create("Logging", pLoggingOutputType, this));
		RETURN_IF_FAILED(RegisterPin(&m_oLoggingOutput));

		//Histogram Output
		cObjectPtr<IMediaType> pHistogramOutputType = new cMediaType(MEDIATYPE_DADAS, MEDIASUBTYPE_SITUATION_HISTOGRAM_SITUATION_NUMBER_BOOST);
		RETURN_IF_FAILED(m_oHistogramNumberOutputPin.Create("Histogram", pHistogramOutputType, this));
		RETURN_IF_FAILED(RegisterPin(&m_oHistogramNumberOutputPin));
	}  
	else if (eStage == StageNormal)
	{
		//Nothing to do
	}
	else if (eStage == StageGraphReady)
	{
		// create a new timeout if required
		tTimeStamp nTimeout = GetPropertyInt("timeout");
		if (nTimeout < 0)
		{
			THROW_ERROR_DESC(ERR_INVALID_ARG, "The timeout value can not be negative");
		}
		else if (nTimeout != 0)
		{
			m_bTimeout = tTrue;
			RETURN_IF_FAILED(m_oTimeout.Create(this, nTimeout, OIGetInstanceName()));
		}

		//get state
		tUInt8 state = GetPropertyInt("State");
		eState = (DADAS::eState) state;

		//Recording File
		recordFile = GetPropertyStr("RecordFile");
		RETURN_IF_FAILED(updateRecordFile(recordFile));

		//History Size
		iHistorySize = GetPropertyInt("HistorySize");

		//automatic adding of Recording
		automaticAdd = GetPropertyBool("AddRecording");

		//Monitorind Data File List
		situationDataStrings = GetPropertyStr("SituationData");
		RETURN_IF_FAILED(loadRecordedSituations(situationDataStrings));

	}
	RETURN_NOERROR;
}

tResult cDadasTestCoverageMonitorFilter::loadRecordedSituations(cString fileList) {
	eState = (DADAS::eState) GetPropertyInt("State");//Fürs Testen nochmal holen
	if(eState==DADAS::eOperation) {
		LOG("File List: "+fileList);
		vector<cString> situationFiles = Tokenize(fileList,";");
		// IMPORT (READ) SITUATIONS
		//cFile file;
		for(auto imFile = situationFiles.begin(); imFile!=situationFiles.end();++imFile) {
			LOG("File: "+*imFile);
			cFilename file = *imFile;
			LOG("Initial Record File: "+file);
			if(!file.IsAbsolute()) { 
				ADTF_GET_CONFIG_FILENAME(file);
				LOG("Full Path with Config: "+file);
			}
			
			file.MakeNativeSlashes();
			//if(file.Open(*imFile,cFile::OM_Read)) {//Danach kann der STream nicht öffnen
			//place holder for situations from file
			vector<DADAS::tMonitoringSituation> _verSituations;
			// open the archive
			std::ifstream ifs(file,ios::in);
			if(ifs.is_open()) {
				boost::archive::xml_iarchive ia(ifs);
				// restore from the archive
				ia >> BOOST_SERIALIZATION_NVP(_verSituations);
				//find duplicate and add elements
				for(auto sit = _verSituations.begin();sit!=_verSituations.end();++sit) {
					if(find(oVerifiedSituations.begin(), oVerifiedSituations.end(), *sit)==oVerifiedSituations.end()) {
						//No duplicate add
						oVerifiedSituations.push_back(*sit);
					}
				}
				ifs.close();
			} else {
				LOG(cString::Format("ERROR: Datei nicht zu öffnen! -> " + file+" ("+*imFile+")"));
				//RETURN_ERROR(ERR_ACCESS_DENIED);
			}
		}
	}
	RETURN_NOERROR;
}

tResult cDadasTestCoverageMonitorFilter::Start(__exception)
{
	// start the timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}

	RETURN_IF_FAILED(cConditionTriggeredFilter::Start(__exception_ptr));

	if(eState==DADAS::eOperation) {
		TransmitBOOSTMonioringKnowledge();
		SendStatisticsEvent(EVENT_DADAS_STATISTICS_DATA_RECORDED_SITUATION);
	}

	RETURN_NOERROR;
}

tResult cDadasTestCoverageMonitorFilter::updateRecordFile(cFilename &record) {
	
	//Recording File
	if(eState==DADAS::eSimulation && record.IsEmpty()) {
		//Für Simulation muss ein File da sein
		RETURN_ERROR(ERR_EMPTY);
	}

	//for linux make paths absolute if their are not
	LOG("Initial Record File: "+record);
	if(!record.IsAbsolute()) { 
				ADTF_GET_CONFIG_FILENAME(record);
				LOG("Full Path with Config: "+record);
	}

	record.MakeNativeSlashes();
	LOG("Starting Record File: "+record);

	//check for Existence and alter file
	ExistingRecordFile(record);

	//Create File
	boost::filesystem::path dir(record.GetPath().GetPtr());
	if(!boost::filesystem::exists(dir)&& !boost::filesystem::create_directory(dir)) {
		RETURN_AND_LOG_ERROR(ERR_PATH_NOT_FOUND);
	}
	cFile recordCFile;
	RETURN_IF_FAILED(recordCFile.Open(record, cFile::OM_Write));
	recordCFile.Close();//FOr ofstream to open the file later

	//Update Property
	RETURN_IF_FAILED(SetPropertyStr("RecordFile",recordFile));

	RETURN_NOERROR;
}

tResult cDadasTestCoverageMonitorFilter::Stop(__exception)
{
	// cancel the timeout, we expect no more samples
	if (m_bTimeout)
	{
		m_oTimeout.Cancel();
	}

	////Save Record file in simulation
	//cFilename file = GetPropertyStr("RecordFile");
	//SaveRecordFile(&file);

	if(m_oRecordFile.IsValid()) {
		m_oRecordFile.Close();
	}

	if(automaticAdd) {
		cString situationDataStrings = GetPropertyStr("SituationData");
		cFilename recordingFile = GetPropertyStr("RecordFile");
		situationDataStrings.Append(";"+recordingFile);
		SetPropertyStr("SituationData",situationDataStrings);
	}

	return cConditionTriggeredFilter::Stop(__exception_ptr);
}

tResult cDadasTestCoverageMonitorFilter::Shutdown(tInitStage eStage, __exception)
{
	if (eStage == StageFirst)
	{ 

	}
	if(StageNormal ==eStage) {
		//Do nothing
	}
	if (StageGraphReady == eStage)
	{
		m_oTimeout.Release();
	}

	return cConditionTriggeredFilter::Shutdown(eStage, __exception_ptr);
}


//Only triggers on the both targetpoints but not on the categorisation -> the catergorisation is got from the additional queue
tResult cDadasTestCoverageMonitorFilter::OnTrigger(adtf::IPin* pSource, adtf::IMediaSample* pSample, __exception) {
	// reset our timeout
	if (m_bTimeout)
	{
		m_oTimeout.Start();
	}

	tTimeStamp nTriggerTime = pSample->GetTime();
	// you can now get the relevant samples from the queues using the

	//Get Categorisation Sample
	cObjectPtr<IMediaSample> pCategorisationSample;
	ISampleQueue* pCategorisationQueue =  GetQueue(&m_oCategorisationInput);
	tSize size = pCategorisationQueue->Size();
	if(pCategorisationQueue) {
		pCategorisationQueue->Get(&pCategorisationSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest); //Thinking that the categorisation is send first before the targets
	}
	RETURN_IF_POINTER_NULL(pCategorisationSample);

	//Get Abstract Targets Sample
	cObjectPtr<IMediaSample> pAbstrTargetsSample;
	ISampleQueue* pAbstrTargetsQueue = GetQueue(&m_oTargetsInput);
	if(pAbstrTargetsQueue) {
		pAbstrTargetsQueue->Get(&pAbstrTargetsSample,
			nTriggerTime,
			1000000,
			adtf::ISampleQueue::SQG_GetNearest); 

	}
	RETURN_IF_POINTER_NULL(pAbstrTargetsSample);

	kernelMutex.Enter();

	//Get Categorisation
	DADAS::tCategorisation pCategorisationData;
	//Lock Sample
	RETURN_IF_FAILED(DADAS::HELPER::DeserializeFromSample(pCategorisationSample,pCategorisationData));

	//vector<char>* cCatbuffer= 0;
	////Lock Sample
	//pCategorisationSample->Lock((const void**)&cCatbuffer);//PROBLEM BUFFER BEKOMMT KEINE DATEN

	//RETURN_IF_POINTER_NULL(cCatbuffer);

	//RETURN_IF_FAILED(DADAS::HELPER::Deserialize(*cCatbuffer,pCategorisationData));
	////RETURN_IF_FAILED(DADAS::HELPER::Deserialize(cCatbuffer,&pCategorisationData));

	//GEt Abstract Targets
	vector<DADAS::tAbstractTarget> pAbstrTargetsData;

	RETURN_IF_FAILED(DADAS::HELPER::DeserializeFromSample(pAbstrTargetsSample,pAbstrTargetsData));

	//vector<char>* cAbtrTarbuffer= 0;
	////Lock Sample
	//pAbstrTargetsSample->Lock((const void**)&cAbtrTarbuffer);//PROBLEM BUFFER BEKOMMT KEINE DATEN

	//RETURN_IF_POINTER_NULL(cAbtrTarbuffer);

	//RETURN_IF_FAILED(DADAS::HELPER::Deserialize(*cAbtrTarbuffer,pAbstrTargetsData));

	//Generate Situation
	DADAS::tMonitoringSituation currentSituation = generateSituation(pCategorisationData,pAbstrTargetsData);

	countSituation(currentSituation);

	if(eState==DADAS::eSimulation) {
		//Simulation -> Record
		RETURN_IF_FAILED(Record(currentSituation));
	}
	if(eState==DADAS::eOperation) {
		//Operation -> Supervise
		RETURN_IF_FAILED(Monitor(currentSituation));
	}

	tBool activeHistogram =  GetPropertyBool("histogram",tFalse);
	if(activeHistogram) {
		RETURN_IF_FAILED(calculateHistogram(currentSituation));
	}
	kernelMutex.Leave();

	//Unlock Samples
	//pCategorisationSample->Unlock(cCatbuffer);
	//pAbstrTargetsSample->Unlock(cAbtrTarbuffer);

	RETURN_NOERROR;
}

tResult cDadasTestCoverageMonitorFilter::Record(DADAS::tMonitoringSituation &monitoringSituation) 
{
	//Put situation into vector for verified situations
	//PREVENT DUPLICATES!
	if(find(oVerifiedSituations.begin(), oVerifiedSituations.end(), monitoringSituation)==oVerifiedSituations.end()) { 
		//situation not found add
		oVerifiedSituations.push_back(monitoringSituation);
		SaveRecordFile();//Save verifiedSituations to recordFile;
		SendStatisticsEvent(EVENT_DADAS_STATISTICS_DATA_RECORDED_SITUATION);
		TransmitBOOSTMonioringKnowledge();
	}

	tBool coverage = tTrue;
	RETURN_IF_FAILED(TransmitBOOSTCoverageResult(coverage,monitoringSituation));

	RETURN_IF_FAILED(updateHistory(monitoringSituation));

	RETURN_IF_FAILED(TransmitBOOSTHistory(&oHistory));

	RETURN_IF_FAILED(TransmitBOOSTCombindedResult(&coverage, &oHistory));

	RETURN_NOERROR;
}

tResult cDadasTestCoverageMonitorFilter::Monitor(DADAS::tMonitoringSituation  &monitoringSituation)
{
	tBool tCovered =EvaluateTestCoverage(monitoringSituation);

	RETURN_IF_FAILED(TransmitBOOSTCoverageResult(tCovered, monitoringSituation));

	RETURN_IF_FAILED(updateHistory(monitoringSituation));

	SendStatisticsEvent(EVENT_DADAS_STATISTICS_DATA_RECORDED_SITUATION);

	if(!tCovered) {
		RETURN_IF_FAILED(logMissingSituation(tCovered,oHistory));
		SendStatisticsEvent(EVENT_DADAS_STATISTICS_DATA_UNTESTED_SITUATION);
	} else {
		SendStatisticsEvent(EVENT_DADAS_STATISTICS_DATA_TESTED_SITUATION);
	}

	RETURN_IF_FAILED(TransmitBOOSTHistory(&oHistory));

	RETURN_IF_FAILED(TransmitBOOSTCombindedResult(&tCovered, &oHistory));


	RETURN_NOERROR;
}


tResult cDadasTestCoverageMonitorFilter::calculateHistogram(DADAS::tMonitoringSituation &monitoringSituation) 
{
	std::vector<DADAS::tMonitoringSituation>::iterator it = std::find(situationHistogramCategorization.begin(), situationHistogramCategorization.end(), monitoringSituation);
	tUInt16 catNumber =0;
	if(it != situationHistogramCategorization.end()) {
		//situation categorized
		catNumber = std::distance(situationHistogramCategorization.begin(),it)+1;//Verschiebung um 1
	} else {
		//situation NOT categorized / Not found
		catNumber = situationHistogramCategorization.size()+1;//Verschiebung um 1
		situationHistogramCategorization.push_back(monitoringSituation);
	}
	if(catNumber<=0) {
		RETURN_ERROR(ERR_INVALID_ARG);
	}
	RETURN_IF_FAILED(TransmitSituationHistogramNumber(catNumber,monitoringSituation));
	RETURN_NOERROR;
}

tResult cDadasTestCoverageMonitorFilter::countSituation(DADAS::tMonitoringSituation &monitoringSituation) {
	if(find(oHistory.begin(), oHistory.end(), monitoringSituation)==oHistory.end()) { //TODO Evt. hier die Situation hahsen und alle Hashs speichern.
		//not find -> unique
		SendStatisticsEvent(EVENT_DADAS_STATISTICS_DATA_UNIQUE_SITUATION);
	} else {
		SendStatisticsEvent(EVENT_DADAS_STATISTICS_DATA_OVERALL_SITUATION);
	}
	RETURN_NOERROR;
}

DADAS::tMonitoringSituation cDadasTestCoverageMonitorFilter::generateSituation(DADAS::tCategorisation &categorisation, vector<DADAS::tAbstractTarget> &targets)
{
	return DADAS::tMonitoringSituation(categorisation, targets);
}

tResult cDadasTestCoverageMonitorFilter::logMissingSituation(tBool &tested, list<DADAS::tMonitoringSituation> &history) {
	cObjectPtr<IMediaSample> pMediaSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));

	DADAS::tMonitoring mon(tested,history);

	RETURN_IF_FAILED(DADAS::HELPER::SerializeToSample(pMediaSample,mon));

	RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));

	//vector<char>* buffer = new vector<char>();

	//DADAS::tMonitoring mon(tested,history);

	//RETURN_IF_FAILED(DADAS::HELPER::Serialize(*buffer,mon));

	//pMediaSample->Update(_clock->GetStreamTime(), buffer, sizeof(*buffer), 0);

	RETURN_IF_FAILED(m_oLoggingOutput.Transmit(pMediaSample));

	RETURN_NOERROR;
}

tBool cDadasTestCoverageMonitorFilter::EvaluateTestCoverage(DADAS::tMonitoringSituation &monitoringSituation)
{
	//TODO EVENTUELL BENÖTIGEN WIR HIER EINE EFFIZIENTERE METHODE -> Wenn die Daten extrem ansteigen
	if(find(oVerifiedSituations.begin(), oVerifiedSituations.end(),monitoringSituation)!=oVerifiedSituations.end()) {
		return tTrue;
	} else {
		return tFalse;
	}
}

tResult cDadasTestCoverageMonitorFilter::updateHistory(DADAS::tMonitoringSituation &monitoringSituation)
{
	oHistory.push_back(monitoringSituation);
	while(oHistory.size()>iHistorySize) {
		oHistory.pop_front();
	}
	RETURN_NOERROR;
}

tResult cDadasTestCoverageMonitorFilter::TransmitBOOSTCoverageResult(tBool &monResult, DADAS::tMonitoringSituation &monitoringSituation) {
	cObjectPtr<IMediaSample> pMediaSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));

	DADAS::tTestCoverage testCoverage(monResult, monitoringSituation);

	RETURN_IF_FAILED(DADAS::HELPER::SerializeToSample(pMediaSample,testCoverage));

	RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));

	//vector<char>* buffer = new vector<char>();

	//DADAS::tTestCoverage testCoverage(monResult, monitoringSituation);

	//RETURN_IF_FAILED(DADAS::HELPER::Serialize(*buffer,testCoverage));

	//pMediaSample->Update(_clock->GetStreamTime(),  buffer, sizeof(*buffer), 0);

	RETURN_IF_FAILED(m_oCoverageOutput.Transmit(pMediaSample));

	RETURN_NOERROR;
}

tResult cDadasTestCoverageMonitorFilter::TransmitBOOSTHistory(list<DADAS::tMonitoringSituation>* history)
{
	cObjectPtr<IMediaSample> pMediaSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));

	DADAS::tSituations situations(*history);

	RETURN_IF_FAILED(DADAS::HELPER::SerializeToSample(pMediaSample,situations));

	RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));

	//vector<char>* buffer =  new vector<char>();

	//DADAS::tSituations situations(*history);
	//RETURN_IF_FAILED(DADAS::HELPER::Serialize(*buffer,situations));

	//pMediaSample->Update(_clock->GetStreamTime(), buffer, sizeof(*buffer), 0);

	RETURN_IF_FAILED(m_oHistoryOutput.Transmit(pMediaSample));

	RETURN_NOERROR;
}

tResult cDadasTestCoverageMonitorFilter::TransmitBOOSTCombindedResult(tBool* monResult,list<DADAS::tMonitoringSituation>* history) {
	cObjectPtr<IMediaSample> pMediaSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));
	
	DADAS::tMonitoring mon(*monResult,*history);

	RETURN_IF_FAILED(DADAS::HELPER::SerializeToSample(pMediaSample,mon));

	RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));

	//vector<char>* buffer = new vector<char>();

	//DADAS::tMonitoring mon(*monResult,*history);

	//RETURN_IF_FAILED(DADAS::HELPER::Serialize(*buffer,mon));

	//pMediaSample->Update(_clock->GetStreamTime(), buffer, sizeof(*buffer), 0);

	RETURN_IF_FAILED(m_oCombinedMonitoringOutput.Transmit(pMediaSample));

	RETURN_NOERROR;
}

//TODO WHEN TO SEND!?
tResult cDadasTestCoverageMonitorFilter::TransmitBOOSTInternalState() {
	cObjectPtr<IMediaSample> pMediaSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));

	DADAS::tMonitoringState state(eState,recordFile,logFile,situationDataStrings,(tUInt8) oHistory.size() );

	RETURN_IF_FAILED(DADAS::HELPER::SerializeToSample(pMediaSample,state));

	RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));

	//vector<char>* buffer = new vector<char>();

	//RETURN_IF_FAILED(DADAS::HELPER::Serialize(*buffer,state));

	//pMediaSample->Update(_clock->GetStreamTime(), buffer, sizeof(*buffer), 0);

	RETURN_IF_FAILED(m_oMonitoringInternalOutput.Transmit(pMediaSample));

	RETURN_NOERROR;
}

tResult cDadasTestCoverageMonitorFilter::TransmitBOOSTMonioringKnowledge() {
	cObjectPtr<IMediaSample> pMediaSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));

	DADAS::tSituations state(oVerifiedSituations);

	RETURN_IF_FAILED(DADAS::HELPER::SerializeToSample(pMediaSample,state));

	RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));

	//vector<char>* buffer = new vector<char>();

	//DADAS::tSituations state(oVerifiedSituations);

	//RETURN_IF_FAILED(DADAS::HELPER::Serialize(*buffer,state));

	//pMediaSample->Update(_clock->GetStreamTime(), buffer, sizeof(*buffer), 0);

	RETURN_IF_FAILED(m_oMonitoringKnowledgeOutput.Transmit(pMediaSample));

	//delete state;
	//delete buffer;

	RETURN_NOERROR;
}

tResult cDadasTestCoverageMonitorFilter::TransmitSituationHistogramNumber(tUInt16 sitNum,DADAS::tMonitoringSituation &monitoringSituation)
{
	cObjectPtr<IMediaSample> pMediaSample;
	RETURN_IF_FAILED(AllocMediaSample((tVoid**)&pMediaSample));

	DADAS::tHistogramLog histogram(sitNum,monitoringSituation);

	RETURN_IF_FAILED(DADAS::HELPER::SerializeToSample(pMediaSample,histogram));

	RETURN_IF_FAILED(pMediaSample->SetTime(_clock->GetStreamTime()));

	RETURN_IF_FAILED(m_oHistogramNumberOutputPin.Transmit(pMediaSample));

	RETURN_NOERROR;
}

tResult cDadasTestCoverageMonitorFilter::SaveRecordFile() {
	//ONLY in simulation
	if(eState == DADAS::eSimulation) {
		// make an archive
		LOG("Record File: "+recordFile);
		//Ensure the path to the File is present
		boost::filesystem::path dir(recordFile.GetPath().GetPtr());
		if(!boost::filesystem::exists(dir)&& !boost::filesystem::create_directory(dir)) {
			RETURN_AND_LOG_ERROR(ERR_PATH_NOT_FOUND);
		}
		LOG("Resolved Record File: "+recordFile);
		ofstream ofs(recordFile.GetPtr(),ofstream::trunc);
		if(ofs.is_open()) {
			boost::archive::xml_oarchive oa(ofs);
			oa << BOOST_SERIALIZATION_NVP(oVerifiedSituations);
			ofs.flush();
			//Automatic add of recording reference
			/*if(automaticAdd) {
			cString situationDataStrings = GetPropertyStr("SituationData");
			cFilename recordingFile = GetPropertyStr("RecordFile");
			situationDataStrings.Append(";"+recordingFile);
			SetPropertyStr("SituationData",situationDataStrings);
			}*/
			ofs.close();
		} else {
			LOG("Unable to write Record File: "+recordFile);
			RETURN_ERROR(ERR_ACCESS_DENIED);
		}
	}

	RETURN_NOERROR;
}

vector<cString> inline cDadasTestCoverageMonitorFilter::Tokenize(const cString &source, const char *delimiter, bool keepEmpty)
{
	vector<cString> results;

	size_t prev = 0;
	size_t next = 0;

	while ((next = source.Find(delimiter, prev)) != std::string::npos)
	{
		if (keepEmpty || (next - prev != 0))
		{
			results.push_back(source.Mid(prev, next - prev));
		}
		prev = next + 1;
	}

	if (prev < source.GetLength())
	{
		results.push_back(source.Mid(prev));
	}

	return results;
}

void cDadasTestCoverageMonitorFilter::ExistingRecordFile(cFilename &recordFile) {
	//Existiert die Datei nehm neue mit nachführende Endung

	cFile testFile;
	tUInt8 counter = 0;
	tChar delimiter = ('_');
	while(testFile.Open(recordFile, cFile::OM_Read)) {// PROBLEM: DER TEST STARTET SO FRÜH, dass das Record File blockiert ist und hier niemals aktualisiert wird!
		testFile.Close();
		UpdateFileNumberTrailer(recordFile, counter, delimiter );
		counter++;
	}


	if(recordFile.GetExtension().IsEmpty())
	{
		// we add a default extension, makes automatic exporting easy
		recordFile.SetExtension("msr");
	}
	LOG("Recording File:" +recordFile);
}

void cDadasTestCoverageMonitorFilter::UpdateFileNumberTrailer(cFilename &file,tUInt8 number,tChar &delimiter) {
	cString fileExt ="msr";
	cString fileName;
	if(!file.GetExtension().IsEmpty()) {
		fileExt = file.GetExtension();
	}
	fileName =file.GetNameWithoutExtension();
	LOG("FileName vor Löschung: " + fileName);
	tUInt8 cif = intLength(number);
	if(number!=0 && number%10==0) {
		--cif;
	}
	if(fileName.GetAt(fileName.GetLength()-(cif+1))==delimiter) {
		//We have a trailer
		fileName = fileName.Left(fileName.GetLength()-(cif+1));
	}
	LOG("FileName nach Löschung: " + fileName);
	fileName=cString::Format(fileName+"%c"+"%u",delimiter,number);
	//LOG("überarbeiteter FileName (Zwischenschritt): " + fileName);
	LOG(cString::Format("Akt. Counter: %u" ,number));
	//fileName = cString::Format(fileName,number);
	LOG("überarbeiteter FileName: " + fileName);
	cFilename strTargetFileName =file.GetPath();
	LOG("Pfad: " + strTargetFileName);
	strTargetFileName.AppendPath(fileName);

	if(!fileExt.IsEmpty()) {
		strTargetFileName.SetExtension(fileExt);
	}

	strTargetFileName.MakeNativeSlashes();
	LOG("Neuer Pfad zur Datei: " + strTargetFileName);

	file=strTargetFileName;
}

/**
This Kernelthread continously checks for timeout and writes Warning!
*/
tResult cDadasTestCoverageMonitorFilter::Run(tInt nActivationCode,
	const tVoid* pvUserData,
	tInt szUserDataSize,
	ucom::IException** __exception_ptr)
{
	RETURN_IF_FAILED(cConditionTriggeredFilter::Run(nActivationCode, pvUserData, szUserDataSize, __exception_ptr));

	if (adtf::cKernelTimeout::RUN_TIMEOUT == nActivationCode)
	{
		//Clear Queues
		ISampleQueue* catQueue = GetQueue(&m_oCategorisationInput);
		catQueue->Clear();
		ISampleQueue* targetsQueue = GetQueue(&m_oTargetsInput);
		targetsQueue->Clear();
		LOG_WARNING("Timeout");
		// restart our timeout
		m_oTimeout.Start();

	}
	RETURN_NOERROR;
}

tUInt8 cDadasTestCoverageMonitorFilter::intLength(tUInt8 num)
{
	tUInt8 l = 0;
	while(num>0)
	{
		num = num/10;
		l++;
	}
	return l;
}

void cDadasTestCoverageMonitorFilter::LOG(cString mes) {
	if(debugOpt) {
		LOG_INFO(mes);
		//OutputDebugStringWrapper(mes+"\n");
	}
}
 
tResult cDadasTestCoverageMonitorFilter::SendStatisticsEvent(tInt32 code) 
{
	 IFilter* pFilter = (IFilter *) this;
	 cEventInfo* statEvent =0; //TODO: MEMORY LEAK
	if(code==EVENT_DADAS_STATISTICS_DATA_RECORDED_SITUATION && eState==DADAS::eOperation) {
		tUInt16 count =oVerifiedSituations.size();
		statEvent = &cEventInfo(pFilter, IEvent::CLASS_CUSTOM,code,0,0,&count,sizeof(count));
	} else {
		statEvent = &cEventInfo(pFilter, IEvent::CLASS_CUSTOM,code,0,0,0,0);
	}
    _kernel->SignalSend(statEvent);
	RETURN_NOERROR;
}

//IOBJECT
tResult cDadasTestCoverageMonitorFilter::GetInterface(const tChar* riid, tVoid** ppvObject)
{
	if (ppvObject == NULL)
	{
		RETURN_ERROR(ERR_POINTER);
	}

	if (idmatch(riid, IID_DADAS_INTERFACE_LOAD_RECORDS))//IID_DADAS_INTERFACE_LOAD_RECORDS "iid.de.tuc.sse.ipsse.dadas.interface.records.loading"
	{
		*ppvObject =  (ILoadRecordsInterface*) (this);
		Ref();
	} else {
		RETURN_IF_FAILED(cConditionTriggeredFilter::GetInterface(riid,ppvObject));
	}
	
		RETURN_NOERROR;
}

tResult cDadasTestCoverageMonitorFilter::testClear() {
	oHistory.clear();
	oVerifiedSituations.clear();
	logFile ="";
	recordFile ="";
	situationDataStrings ="";

	RETURN_NOERROR;
}

tResult cDadasTestCoverageMonitorFilter::initializeProperties() {
	testClear();
	eState = (DADAS::eState) GetPropertyInt("State");
	logFile = GetPropertyStr("LogFile");
	recordFile =GetPropertyStr("RecordFile");
	updateRecordFile(recordFile);
	situationDataStrings = GetPropertyStr("SituationData");
	LOG("Situation Property :"+situationDataStrings);
	loadRecordedSituations(situationDataStrings);

	RETURN_NOERROR;
}

tUInt cDadasTestCoverageMonitorFilter::Ref() {
	return cConditionTriggeredFilter::Ref();
}
tUInt cDadasTestCoverageMonitorFilter::Unref() {
	return cConditionTriggeredFilter::Unref();
}
tVoid cDadasTestCoverageMonitorFilter::Destroy()
{
	return cConditionTriggeredFilter::Destroy();
}

