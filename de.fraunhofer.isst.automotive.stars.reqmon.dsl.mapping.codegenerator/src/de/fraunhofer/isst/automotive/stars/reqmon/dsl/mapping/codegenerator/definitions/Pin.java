package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions;

public class Pin {
	
	private String pinName;
	private boolean isInputPin;
	private boolean isOutputPin;
	private String mediaType;
	private String mediaSubType;
	private String objectName;
	private String pinObject;

	public Pin(String name, boolean isInput, String mediaSubType, String objectName, String pinObject) {
		this.pinName = name;
		this.isInputPin = isInput;
		this.isOutputPin = !isInput;
		this.mediaType = "MEDIATYPE_DADAS";
		this.mediaSubType = mediaSubType;
		this.objectName = objectName;
		this.pinObject = pinObject;
	}
	
	public String getPinName() {
		return this.pinName;
	}

	public boolean isInputPin() {
		return this.isInputPin;
	}

	public boolean isOutputPin() {
		return this.isOutputPin;
	}

	public String getMediaType() {
		return this.mediaType;
	}

	public String getMediaSubType() {
		return this.mediaSubType;
	}

	public String getPinObjectName() {
		return this.objectName;
	}

	public String getPinObjectType() {
		return this.pinObject;
	}
	
}
