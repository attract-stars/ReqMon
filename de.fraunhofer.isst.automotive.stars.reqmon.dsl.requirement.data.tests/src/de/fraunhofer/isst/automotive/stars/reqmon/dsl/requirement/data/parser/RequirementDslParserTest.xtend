/** 
 */
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.parser

import static org.junit.jupiter.api.Assertions.fail
import static org.assertj.core.api.Assertions.*
import de.fraunhofer.isst.stars.tests.RequirementDSLInjectorProvider
import org.eclipse.xtext.testing.InjectWith
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.^extension.ExtendWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.eclipse.xtext.testing.util.ParseHelper
import com.google.inject.Inject
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.resource.FileExtensionProvider
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.util.StringInputStream
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import de.fraunhofer.isst.stars.ui.RequirementDSLExecutableExtensionFactory

/** 
 * @author mmauritz
 */
@ExtendWith(typeof(InjectionExtension))
@InjectWith(typeof(RequirementDSLInjectorProvider))
package class RequirementDslParserTest {
	
	static val req1 = '''Test'''
	
	 
	@Inject ParseHelper<RequirementDSLPackage> parser;
	@Inject extension ValidationTestHelper;
	
	@Inject
	ResourceSet rs
	
	@Inject
	extension FileExtensionProvider fep
	
	
			
	/** 
	 * @throws java.lang.Exception
	 */
	@BeforeEach def package void setUp() throws Exception {
		val parser = new RequirementDslParser();
	}

	/** 
	 * @throws java.lang.Exception
	 */
	@AfterEach def package void tearDown() throws Exception {
	}


	/** 
	 * Test method for{@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.parser.RequirementDslParser#analyze(org.eclipse.emf.ecore.EObject)}.
	 */
	@ParameterizedTest
	@ValueSource
	def package void testAnalyze(String test) {
		val model = parser.parse(req1);
		assertThat(true);
		fail("Not yet implemented") // TODO
	}

	/** 
	 * Test method for{@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.parser.RequirementDslParser#parseDslRequirementFile(org.eclipse.emf.common.util.URI)}.
	 */
	@Test def package void testParseDslRequirementFile() {
		//TODO NEED FILE
		val resource = rs.createResource(URI.createURI("test." + primaryFileExtension))
		resource.load(new StringInputStream('''Bla Blub!'''), null)//TODO WRITE TEXT
		//TEST FOR MODEL ELEMENT as ROOT
		fail("Not yet implemented") // TODO
	}

	/** 
	 * Test method for{@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.parser.RequirementDslParser#execute(de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementController, java.lang.String)}.
	 */
	@Test def package void testExecute() {
		//TODO NEED FILE
		//TEST CHeck for ALL FILES
		fail("Not yet implemented") // TODO
	}
}
