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
		
		gen.setup(files);
		gen.generate(infoHelper);
		
		try {
			assertTrue("The files differ!", FileUtils.contentEquals(
					new File("filter/aff_expected.h"),
					new File("filter-gen/aff.h")
				));	
			assertTrue("The files differ!", FileUtils.contentEquals(
					new File("filter/aff_expected.cpp"),
					new File("filter-gen/aff.cpp")
				));
			assertTrue("The files differ!", FileUtils.contentEquals(
					new File("filter/fcof_expected.h"),
					new File("filter-gen/fcof.h")
				));
			assertTrue("The files differ!", FileUtils.contentEquals(
					new File("filter/fcof_expected.cpp"),
					new File("filter-gen/fcof.cpp")
				));
			assertTrue("The files differ!", FileUtils.contentEquals(
					new File("filter/saf_expected.h"),
					new File("filter-gen/saf.h")
				));
			assertTrue("The files differ!", FileUtils.contentEquals(
					new File("filter/saf_expected.cpp"),
					new File("filter-gen/saf.cpp")
				));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		files = new ArrayList<String>();
		files.add("aff.h");
		files.add("aff.cpp");
		files.add("fcof.h");
		files.add("fcof.cpp");
		files.add("saf_two_inputs.h");
		files.add("saf_two_inputs.cpp");
		infoHelper = new ModelInformationTestHelper(model, 2);
		gen.setup(files);
		gen.generate(infoHelper);
		
		try {
			assertTrue("The files differ!", FileUtils.contentEquals(
					new File("filter/saf_two_inputs_expected.h"),
					new File("filter-gen/saf_two_inputs.h")
				));
			assertTrue("The files differ!", FileUtils.contentEquals(
					new File("filter/saf_two_inputs_expected.cpp"),
					new File("filter-gen/saf_two_inputs.cpp")
				));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
