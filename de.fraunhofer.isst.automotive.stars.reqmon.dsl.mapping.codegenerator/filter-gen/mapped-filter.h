#ifndef �filename�
#define �filename�

#define OID_DADAS_�oidName� "�oidString�"
�moreDefines�

�includes�

class �className� �filtertype.inheritances�
{
	ADTF_DECLARE_FILTER_VERSION(OID_DADAS_�oidName�, "�filterName�", OBJCAT_DataFilter, "�versionTerm�", �version�, "�oidDesignation�")
	
	private: // private members
		�inputPins�
		
		�outputPins�
		
		�objectPtrs�
		
		�morePrivateMembers�
		
	public:
		�className�(const tChar* __info);
		virtual ~�className�();
		
	private: // private functions
		�privateFunctions�
		
	public: // overwrites cFilter
		tResult Init(tInitStage eStage, __exception = NULL);
		tResult Start(__exception);
		tResult Stop(__exception);
		tResult Shutdown(tInitStage eStage, __exception);
		
	�filtertype.publicFunctions�
		
	protected: 
		�filtertype.protectedFunctions�
}

#endif
