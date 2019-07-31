package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.test;

import java.util.ArrayList;
import java.util.List;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementImporter;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType;

public class TestOtherReqImporter implements IRequirementImporter {

	@Override
	public void execute(IRequirementController rc, String path) {
		List<IRequirementElement> list = new ArrayList<IRequirementElement>();
		IRequirementElement elem = new TestRequirementElement();
		elem.setElementName("name");
		elem.setElementType(RequirementType.OBJECT);
		list.add(elem);
		System.out.println("Other requirement importer");
		rc.updateList(list);
	}

}
