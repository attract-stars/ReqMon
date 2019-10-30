package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.logic.FilterGenerator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.AbstractModelInformationHelper;

public class FilterTemplateTest {
	
	@Test
	public void generateFilterTest() {
		FilterGenerator gen = new FilterGenerator();
		
		AbstractModelInformationHelper infoHelper = new ModelInformationTestHelper();
		MappingTestModel model = new MappingTestModel();
		
		infoHelper.setModel((IMappingModel)model);
		gen.generate(infoHelper);
		
		try {
			assertTrue("The files differ!", FileUtils.contentEquals(
					new File("filter/aff_expected.h"),
					new File("filter-gen/abstract_function_filter.h")
				));	
			assertTrue("The files differ!", FileUtils.contentEquals(
					new File("filter/aff_expected.cpp"),
					new File("filter-gen/abstract_function_filter.cpp")
				));
			assertTrue("The files differ!", FileUtils.contentEquals(
					new File("filter/fcof_expected.h"),
					new File("filter-gen/functional_correctness_oracle_filter.h")
				));
			assertTrue("The files differ!", FileUtils.contentEquals(
					new File("filter/fcof_expected.cpp"),
					new File("filter-gen/functional_correctness_oracle_filter.cpp")
				));
			assertTrue("The files differ!", FileUtils.contentEquals(
					new File("filter/saf_expected.h"),
					new File("filter-gen/scene_abstraction_filter.h")
				));
			assertTrue("The files differ!", FileUtils.contentEquals(
					new File("filter/saf_expected.cpp"),
					new File("filter-gen/scene_abstraction_filter.cpp")
				));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
