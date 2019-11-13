package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.logic.FilterGenerator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.AbstractModelInformationHelper;

public class FilterTemplateTest {
	
	@Test
	public void generateFilterTest() {
		FilterGenerator gen = new FilterGenerator();
		IMappingModel model = new MappingTestModel();
		AbstractModelInformationHelper infoHelper = new ModelInformationTestHelper(model, 1);
		
		ArrayList<String> files = new ArrayList<String>();
		files.add("aff.h");
		files.add("aff.cpp");
		files.add("fcof.h");
		files.add("fcof.cpp");
		files.add("saf.h");
		files.add("saf.cpp");
		//files.add("test_coverage_monitor_filter.h");
		//files.add("test_coverage_monitor_filter.cpp");
		
		files.add("data_types.h");
		
		gen.setup(files);
		gen.generate(infoHelper);
		
		checkEquality("aff", true);
		checkEquality("fcof", true);
		checkEquality("saf", true);
		checkEquality("data_types", false);
		
		files = new ArrayList<String>();
		files.add("aff.h");
		files.add("aff.cpp");
		files.add("fcof.h");
		files.add("fcof.cpp");
		files.add("saf_two_inputs.h");
		files.add("saf_two_inputs.cpp");
		files.add("data_types.h");
		infoHelper = new ModelInformationTestHelper(model, 2);
		gen.setup(files);
		gen.generate(infoHelper);
		
		checkEquality("saf_two_inputs", true);
		
	}
	
	
	private void checkEquality(String filename, boolean isCpp) {
		try {
			assertTrue("The files differ!", FileUtils.contentEquals(
					new File("filter/" + filename + "_expected.h"),
					new File("filter-gen/" + filename + ".h")
				));
			if (isCpp) {
				assertTrue("The files differ!", FileUtils.contentEquals(
						new File("filter/" + filename + "_expected.cpp"),
						new File("filter-gen/" + filename + ".cpp")
					));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
