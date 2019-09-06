#define OID_DADAS_ABSTRACTION_SCENE	"de.tuc.sse.ipsse.dadas.data.monitoring.abstraction.scene"


class cDadasAbstractionSceneFilter : 	public cFilter
{
	ADTF_DECLARE_FILTER_VERSION(OID_DADAS_ABSTRACTION_SCENE, "DADAS Scene Abstraction Filter", OBJCAT_DataFilter, "Version", 0, 1, 0, "Scene Abstraction")

private: //private members
	struct t2dVector {
		tFloat64 x;
		tFloat64 y;

		t2dVector() {

		};

		t2dVector(tFloat64 xx, tFloat64 yy) {
			x=xx;
			y=yy;
		}

		~t2dVector() {
		}

		tFloat64 length() {
			return sqrt(pow(x,2)+pow(y,2));
		}

		 t2dVector operator-(t2dVector &vec)	{
			 return t2dVector(x- vec.x,y-vec.y);
		}

		t2dVector operator+(t2dVector &vec)	{
			 return t2dVector(x+ vec.x,y+vec.y);
		};
	};


	cInputPin					m_oInput;
	cOutputPin                  m_oOutput;

	cObjectPtr<IMediaTypeDescription> m_pCoderDesc;

	//DADAS::tCategorisation* categorisation;

public:
	cDadasAbstractionSceneFilter(const tChar* __info);
	virtual ~cDadasAbstractionSceneFilter();

private:


public: // overwrites cFilter
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
	tResult ProcessSample(IMediaSample* pSample);
	DADAS::tCategorisation Categorize(tScene* scene);
	tResult SendBOOSTCategories(DADAS::tCategorisation* categorisation);
	void LOG(cString mes);
};


