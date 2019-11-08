package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions;

/**
 * This class saves all informations that are needed to create and handle an adtf pin in the filter templates.
 * 
 * @author sgraf
 *
 */
public class Pin {
	
	private String pinName;
	private boolean isInputPin;
	private boolean isOutputPin;
	private String mediaType;
	private String mediaSubType;
	private String pinObjectName;
	private String pinObjectType;
	
	private boolean isPointerTest;
	private boolean isInterface;
	private boolean isOwnQueue;
	private boolean isNearestOlderInQueue;
	private String mediaTypeCreationTemplate;
	private String createParameters;
	private String mediaTypeDescription;
	
	
	private boolean isTrigger;

	/**
	 * Creates a new Pin with the given parameters. Attention: some parameters can only set with setter methods.
	 * @param name the name of the pin.
	 * @param isInput true if the pin is an input pin otherwise false (then it is an output pin).
	 * @param mediaSubType the adtf MediaSubType. The default MediaType is "MEDIATYPE_DADAS".
	 * @param pinObjectName the name of the object which is received or transmitted over the pin.
	 * @param pinObjectType the type of the object which is received or transmitted over the pin.
	 * @param isTrigger true if it should be registered as a TriggerPin otherwise false.
	 */
	public Pin(String name, boolean isInput, String mediaSubType, String pinObjectName, String pinObjectType, boolean isTrigger) {
		this.pinName = name;
		this.isInputPin = isInput;
		this.isOutputPin = !isInput;
		this.mediaType = "MEDIATYPE_DADAS";
		this.mediaSubType = mediaSubType;
		this.pinObjectName = pinObjectName;
		this.pinObjectType = pinObjectType;
		this.isTrigger = isTrigger;
		
		this.isPointerTest = false;
		this.isInterface = false;
		this.isOwnQueue = false;
		this.isNearestOlderInQueue = false;
		this.createParameters = "";
	}
	
	/**
	 * Creates a new Pin with the given parameters. 
	 * The value of isTriggere is set to false. 
	 * Attention: some parameters can only set with setter methods.
	 * @param name the name of the pin.
	 * @param isInput true if the pin is an input pin otherwise false (then it is an output pin).
	 * @param mediaSubType the adtf MediaSubType. The default MediaType is "MEDIATYPE_DADAS".
	 * @param objectName the name of the object which is received or transmitted over the pin.
	 * @param pinObject the type of the object which is received or transmitted over the pin.
	 */
	public Pin(String name, boolean isInput, String mediaSubType, String objectName, String pinObject) {
		this(name, isInput, mediaSubType, objectName, pinObject, false);
	}
	
	/**
	 * Creates a new Pin with the given parameters. 
	 * The value of isTriggere is set to false.
	 * The value of objectName is equals to pinObject. 
	 * Attention: some parameters can only set with setter methods.
	 * @param name the name of the pin.
	 * @param isInput true if the pin is an input pin otherwise false (then it is an output pin).
	 * @param mediaSubType the adtf MediaSubType. The default MediaType is "MEDIATYPE_DADAS".
	 * @param pinObject the type of the object which is received or transmitted over the pin.
	 */
	public Pin(String name, boolean isInput, String mediaSubType, String pinObject) {
		this(name, isInput, mediaSubType, pinObject, pinObject, false);
	}
	
	public String getPinName() {
		return this.pinName;
	}
	
	public void setPinName(String pinName) {
		this.pinName = pinName;
	}

	public boolean isInputPin() {
		return this.isInputPin;
	}
	
	public void setInputPin(boolean isInputPin) {
		this.isInputPin = isInputPin;
	}

	public boolean isOutputPin() {
		return this.isOutputPin;
	}
	
	public void setOutputPin(boolean isOutputPin) {
		this.isOutputPin = isOutputPin;
	}

	public String getMediaType() {
		return this.mediaType;
	}
	
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaSubType() {
		return this.mediaSubType;
	}
	
	public void setMediaSubType(String mediaSubType) {
		this.mediaSubType = mediaSubType;
	}

	public String getPinObjectName() {
		return this.pinObjectName;
	}
	
	public void setPinObjectName(String objectName) {
		this.pinObjectName = objectName;
	}

	public String getPinObjectType() {
		return this.pinObjectType;
	}

	public void setPinObjectType(String pinObject) {
		this.pinObjectType = pinObject;
	}

	public boolean isTrigger() {
		return isTrigger;
	}

	public void setTrigger(boolean isTrigger) {
		this.isTrigger = isTrigger;
	}

	
	public boolean isPointerTest() {
		return isPointerTest;
	}

	public void setPointerTest(boolean isPointerTest) {
		this.isPointerTest = isPointerTest;
	}

	public boolean isInterface() {
		return isInterface;
	}

	public void setInterface(boolean isInterface) {
		this.isInterface = isInterface;
	}

	public String getMediaTypeCreationTemplate() {
		return mediaTypeCreationTemplate;
	}

	public void setMediaTypeCreationTemplate(String mediaTypeCreation) {
		this.mediaTypeCreationTemplate = mediaTypeCreation;
	}

	public String getCreateParameters() {
		return createParameters;
	}

	public void setCreateParameters(String createParameters) {
		this.createParameters = createParameters;
	}

	public String getMediaTypeDescription() {
		return mediaTypeDescription;
	}

	public void setMediaTypeDescription(String mediaTypeDescription) {
		this.mediaTypeDescription = mediaTypeDescription;
	}

	public boolean isOwnQueue() {
		return isOwnQueue;
	}

	public void setOwnQueue(boolean isOwnQueue) {
		this.isOwnQueue = isOwnQueue;
	}

	public boolean isNearestOlderInQueue() {
		return isNearestOlderInQueue;
	}

	public void setNearestOlderInQueue(boolean isNearestOlderInQueue) {
		this.isNearestOlderInQueue = isNearestOlderInQueue;
	}

	
	
	
	
}
