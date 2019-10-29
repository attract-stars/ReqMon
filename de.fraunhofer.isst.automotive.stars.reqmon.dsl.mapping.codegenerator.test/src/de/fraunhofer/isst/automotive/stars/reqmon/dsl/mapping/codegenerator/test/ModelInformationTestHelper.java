package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.test;

import java.util.ArrayList;
import java.util.List;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.IModelInformationHelper;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.IPin;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.FilterType;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel;

public class ModelInformationTestHelper implements IModelInformationHelper {
	
	private MappingTestModel model;
	private FilterType filtertype;
	private List<IPin> inputs;
	private List<IPin> outputs;
	private List<IPin> pins;
	
	public ModelInformationTestHelper() {
		setupPins();
	}
	
	private void setupPins() {
		IPin input = new PinImpl("input", true, "MEDIATYPE_DADAS_SCENE", "scene", "tScene");
		IPin output = new PinImpl("output", false, "MEDIATYPE_DADAS_CATEGORISATION", "categorisation", "tCategorisation");
		
		inputs = new ArrayList<IPin>();
		outputs = new ArrayList<IPin>();
		pins = new ArrayList<IPin>();
		
		inputs.add(input);
		outputs.add(output);
		pins.add(input);
		pins.add(output);
	}

	@Override
	public void setModel(IMappingModel model) {
		if (model instanceof MappingTestModel) {
			this.model = (MappingTestModel) model;
		}
	}

	@Override
	public int getSourceCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getSignals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFilterType(FilterType filtertype) {
		this.filtertype = filtertype;
	}

	@Override
	public List<String> getIncludes() {
		if (filtertype.equals(FilterType.SCENE_ABSTRACTION)) {
			return getSAFIncludes();
		}
		return null;
	}
	
	private List<String> getSAFIncludes() {
		List<String> list = new ArrayList<String>();
		list.add(getSAFFilterFileName());
		list.add("system-types");
		list.add("mediatypes");
		return list;
	}
	
	private String getSAFFilterFileName() {
		return "scene_abstraction_filter";
	}

	@Override
	public boolean isDebugOpt() {
		return false;
	}

	@Override
	public List<IPin> getPins() {
		if (filtertype.equals(FilterType.SCENE_ABSTRACTION)) {
			return getSAFPins();
		}
		return null;
	}
	
	private List<IPin> getSAFPins() {
		return this.pins;
	}

	
	@Override
	public List<IPin> getInputPins() {
		return this.inputs;
	}
	
	
	public class PinImpl implements IPin {
		
		private String pinName;
		private boolean isInputPin;
		private boolean isOutputPin;
		private String mediaType;
		private String mediaSubType;
		private String objectName;
		private String pinObject;

		public PinImpl(String name, boolean isInput, String mediaSubType, String objectName, String pinObject) {
			this.pinName = name;
			this.isInputPin = isInput;
			this.isOutputPin = !isInput;
			this.mediaType = "MEDIATYPE_DADAS";
			this.mediaSubType = mediaSubType;
			this.objectName = objectName;
			this.pinObject = pinObject;
		}
		
		@Override
		public String getPinName() {
			return this.pinName;
		}

		@Override
		public boolean isInputPin() {
			return this.isInputPin;
		}

		@Override
		public boolean isOutputPin() {
			return this.isOutputPin;
		}

		@Override
		public String getMediaType() {
			return this.mediaType;
		}

		@Override
		public String getMediaSubType() {
			return this.mediaSubType;
		}

		@Override
		public String getPinObjectName() {
			return this.objectName;
		}

		@Override
		public String getPinObjectType() {
			return this.pinObject;
		}

		
	}


	@Override
	public List<IPin> getOutputPins() {
		return this.outputs;
	}

	@Override
	public List<String> getAttributes(String objectName) {
		return model.getAllAtributesOf(objectName);
	}

	@Override
	public String getSystemAttribut(String name) {
		return model.getSystemAttribute(name);
	}

	@Override
	public String getCorrespondingAttribute(String monAttr) {
		return model.getCorrespondingAttribute(monAttr);
	}

	@Override
	public List<String> getSignalBoundarys(String attr) {
		return model.isSignal(attr);
	}

	@Override
	public List<String> getReqObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getReqAttribute(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getInheritance(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<String>> getReqEnums() {
		// TODO Auto-generated method stub
		return null;
	}



	

}
