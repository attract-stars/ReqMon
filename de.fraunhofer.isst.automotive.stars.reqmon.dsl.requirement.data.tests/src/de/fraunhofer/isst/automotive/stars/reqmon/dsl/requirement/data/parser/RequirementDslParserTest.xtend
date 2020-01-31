/** 
 */
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.parser

import com.google.inject.Inject
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.analytics.repository.RequirementElementMappingRepository
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.RequirementController
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement
import de.fraunhofer.isst.stars.requirementDSL.RequirementModel
import de.fraunhofer.isst.stars.tests.RequirementDSLInjectorProvider
import java.io.File
import java.io.FileOutputStream
import java.nio.channels.FileChannel
import java.nio.channels.FileLock
import java.util.ArrayList
import java.util.List
import java.util.stream.Stream
import org.apache.commons.io.FileUtils
import org.assertj.core.api.SoftAssertions
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.resource.FileExtensionProvider
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.eclipse.xtext.util.StringInputStream
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.^extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.MockitoAnnotations

import static org.mockito.Mockito.*
import java.io.RandomAccessFile
import java.util.concurrent.TimeUnit

/** 
 * @author mmauritz
 */
@ExtendWith(typeof(InjectionExtension))
@InjectWith(typeof(RequirementDSLInjectorProvider))
package class RequirementDslParserTest {

	static val testfolder = "./testing";

	static val req1 = '''Req 1: The system must not perform a lane change to any lane if a vehicle is on that lane and the vehicle is behind the ego vehicle and the vehicle's relative velocity is more than 5 m/s.\n'''
	static val req2 = '''Req 4: The system must not perform a lane change to any lane if a vehicle is on the ego lane and the vehicle is behind the ego vehicle  and the vehicle's relative velocity is larger than 10 m/s.\n'''
	static val req3 = '''Req 8: The system must not perform a lane change to any lane if the lane is not existing.\n'''
	static val req4 = '''Req 13: The system must not perform a lane change to any lane if the lane's curvature is higher than 25 rad/m.\n'''
	static val req5 = '''Req 15: The system must not perform a lane change to any lane if domain is not handled.\n'''
	static val req6 = '''Req Req17a: The system must not perform a lane change to any lane if the ego-vehicle's lateral-offset in relation to the ego-lane's center is more than 0.4 m.\n'''

	@Inject ParseHelper<RequirementModel> parser;
	@Inject extension ValidationTestHelper validator;

	@Inject
	ResourceSet rs

	@Inject
	extension FileExtensionProvider fep

	RequirementDslParser reqAnalyzer;
	
	 @Captor ArgumentCaptor<List<? extends IRequirementElement>> reqCaptor;

	@BeforeAll
	static def package void setUpBeforeAll() throws Exception {
		FileUtils.forceMkdir(new File(testfolder));
	}

	@AfterAll
	static def package void tearDownAfterAll() throws Exception {
		//WAIT For all files to get lock
		//TimeUnit.MILLISECONDS.sleep(100);
		val testFolderFile = new File(testfolder)
		try {
			for(File file : testFolderFile.listFiles) {
				val raFile = new RandomAccessFile(file.absolutePath,"rw");
				raFile.channel.lock().release();//Release any lock
			}
			//After all files are unlocked from test -> delete them
			FileUtils.cleanDirectory(testFolderFile)
			FileUtils.deleteDirectory(testFolderFile);
		} catch (Exception exception) {
			System.err.println("Unable to Delete Test Files: "+exception.toString());
		}

	}

	/** 
	 * @throws java.lang.Exception
	 */
	@BeforeEach def package void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		reqAnalyzer = new RequirementDslParser();
	}

	/** 
	 * @throws java.lang.Exception
	 */
	@AfterEach def package void tearDown() throws Exception {
		RequirementElementMappingRepository.getInstance().clear();
	}

	/**
	 * Build the test cases of input string and corresponding set of semantic text elements
	 */
	def static Stream<Arguments> createTestCases() {
		// REQ 1:
		// Req 1: The system must not perform a lane change to any lane if a vehicle is on that lane and the vehicle is behind the ego vehicle and the vehicle's relative velocity is more than 5 m/s.
		var req1Ele = new ArrayList<SemanticTextElement>();
		req1Ele.add(new SemanticTextElement("system", RequirementType.OBJECT));
		req1Ele.add(new SemanticTextElement("<system> perform lane change to lane", RequirementType.RELATION));
		req1Ele.add(new SemanticTextElement("lane", RequirementType.OBJECT));
		req1Ele.add(new SemanticTextElement("vehicle", RequirementType.OBJECT));
		req1Ele.add(new SemanticTextElement("<vehicle> is on lane", RequirementType.RELATION));
		req1Ele.add(new SemanticTextElement("<vehicle> is behind ego vehicle", RequirementType.RELATION));
		req1Ele.add(new SemanticTextElement("ego vehicle", RequirementType.OBJECT));
		req1Ele.add(new SemanticTextElement("vehicle's relative velocity", RequirementType.FUNCTION));
		req1Ele.add(
			new SemanticTextElement("<vehicle's relative velocity> is more than 5 m/s", RequirementType.RELATION));
		// REQ 2:
		// Req 4: The system must not perform a lane change to any lane if a vehicle is on the ego lane and the vehicle is behind the ego vehicle  and the vehicle's relative velocity is larger than 10 m/s.\n
		var req2Ele = new ArrayList<SemanticTextElement>();
		req2Ele.add(new SemanticTextElement("system", RequirementType.OBJECT));
		req2Ele.add(new SemanticTextElement("<system> perform lane change to lane", RequirementType.RELATION));
		req2Ele.add(new SemanticTextElement("lane", RequirementType.OBJECT));
		req2Ele.add(new SemanticTextElement("vehicle", RequirementType.OBJECT));
		req2Ele.add(new SemanticTextElement("ego vehicle", RequirementType.OBJECT));
		req2Ele.add(new SemanticTextElement("<vehicle> is on ego lane", RequirementType.RELATION));
		req2Ele.add(new SemanticTextElement("ego lane", RequirementType.OBJECT));
		req2Ele.add(new SemanticTextElement("<vehicle> is behind ego vehicle", RequirementType.RELATION));
		req2Ele.add(new SemanticTextElement("vehicle's relative velocity", RequirementType.FUNCTION));
		req2Ele.add(
			new SemanticTextElement("<vehicle's relative velocity> is larger than 10 m/s", RequirementType.RELATION));
		// REQ 3:
		// Req 8: The system must not perform a lane change to any lane if the lane is not existing.\n
		var req3Ele = new ArrayList<SemanticTextElement>();
		req3Ele.add(new SemanticTextElement("system", RequirementType.OBJECT));
		req3Ele.add(new SemanticTextElement("<system> perform lane change to lane", RequirementType.RELATION));
		req3Ele.add(new SemanticTextElement("lane", RequirementType.OBJECT));
		req3Ele.add(new SemanticTextElement("<lane> is existing", RequirementType.RELATION));
		// REQ 4:
		// Req 13: The system must not perform a lane change to any lane if the lane's curvature is higher than 25 rad/m.\n
		var req4Ele = new ArrayList<SemanticTextElement>();
		req4Ele.add(new SemanticTextElement("system", RequirementType.OBJECT));
		req4Ele.add(new SemanticTextElement("<system> perform lane change to lane", RequirementType.RELATION));
		req4Ele.add(new SemanticTextElement("lane", RequirementType.OBJECT))
		req4Ele.add(new SemanticTextElement("lane's curvature", RequirementType.FUNCTION))
		req4Ele.add(new SemanticTextElement("<lane's curvature> is higher than 25 rad/m", RequirementType.RELATION))
		// REQ 5:
		// Req 15: The system must not perform a lane change to any lane if domain is not handled.\n
		var req5Ele = new ArrayList<SemanticTextElement>();
		req5Ele.add(new SemanticTextElement("system", RequirementType.OBJECT));
		req5Ele.add(new SemanticTextElement("<system> perform lane change to lane", RequirementType.RELATION));
		req5Ele.add(new SemanticTextElement("lane", RequirementType.OBJECT));
		req5Ele.add(new SemanticTextElement("domain", RequirementType.OBJECT));
		req5Ele.add(new SemanticTextElement("<domain> is handled", RequirementType.RELATION));
		// REQ 6:
		// Req Req17a: The system must not perform a lane change to any lane if the ego-vehicle's lateral-offset in relation to the ego-lane's center is more than 0.4 m.\n
		var req6Ele = new ArrayList<SemanticTextElement>();
		req6Ele.add(new SemanticTextElement("system", RequirementType.OBJECT));
		req6Ele.add(new SemanticTextElement("<system> perform lane change to lane", RequirementType.RELATION));
		req6Ele.add(new SemanticTextElement("lane", RequirementType.OBJECT));
		req6Ele.add(new SemanticTextElement("ego-vehicle", RequirementType.OBJECT));
		req6Ele.add(
			new SemanticTextElement(
				"<ego-vehicle's lateral-offset in relation to ego-lane's center> is more than 0.4 m",
				RequirementType.RELATION));
		req6Ele.add(
			new SemanticTextElement("ego-vehicle's lateral-offset in relation to ego-lane's center",
				RequirementType.FUNCTION));
		req6Ele.add(new SemanticTextElement("ego-lane's center", RequirementType.FUNCTION));
		req6Ele.add(new SemanticTextElement("ego-lane", RequirementType.OBJECT));
		return Stream.of(Arguments.of(1, req1, req1Ele), Arguments.of(2, req2, req2Ele), Arguments.of(3, req3, req3Ele),
			Arguments.of(4, req4, req4Ele), Arguments.of(5, req5, req5Ele), Arguments.of(6, req6, req6Ele));
	}

	/** 
	 * Test method for{@link RequirementDslParser#analyze(org.eclipse.emf.ecore.EObject)}.
	 */
	@ParameterizedTest
	@MethodSource("createTestCases")
	def package void testAnalyze(int id, String req, List<SemanticTextElement> result) {
		val softly = new SoftAssertions();
		val model = parser.parse(req);
		// Check for Errors!
		model.assertNoErrors("Requirements should be correctly parsed for this test!!!")
		val actual = reqAnalyzer.analyze(model);
		softly.assertThat(actual.size()).^as("Checking size of result (analysis rerun):").isEqualTo(result.size());
		softly.assertThat(actual).^as("Test %s for correct semantic analysis (analysis rerun)", id).
			containsOnlyElementsOf(result);
		// Here it should take the prvious result 
		val rerun = reqAnalyzer.analyze(model);
		softly.assertThat(rerun.size()).^as("Checking size of result (analysis rerun):").isEqualTo(result.size());
		softly.assertThat(rerun).^as("Test %s for correct semantic analysis (analysis rerun)", id).
			containsOnlyElementsOf(result);
		softly.assertAll();
		RequirementElementMappingRepository.getInstance().clear();
	}

	/** 
	 * Test method for{@link RequirementDslParser#parseDslRequirementFile(org.eclipse.emf.common.util.URI)}.
	 */
	@ParameterizedTest
	@MethodSource("createTestCases")
	def package void testParseDslRequirementFile(int id, String req, List<SemanticTextElement> result) {
		val softly = new SoftAssertions();
		val uri = URI.createURI(testfolder + "/" + String.format("test%s", id) + "." + primaryFileExtension);
		val resource = rs.createResource(uri);
		resource.load(new StringInputStream(req), null);
		resource.save(new FileOutputStream(uri.toFileString), null);
		val model = reqAnalyzer.parseDslRequirementFile(resource.URI);
		// Check for Errors!
		model.assertNoErrors("Requirements should be correctly parsed for this test!!!")
		val actual = reqAnalyzer.analyze(model);
		softly.assertThat(actual.size()).^as("Checking size of result (analysis rerun):").isEqualTo(result.size());
		softly.assertThat(actual).^as("Test %s for correct semantic analysis (analysis rerun)", id).
			containsOnlyElementsOf(result);
		// Here it should take the prvious result 
		val rerun = reqAnalyzer.analyze(model);
		softly.assertThat(rerun.size()).^as("Checking size of result (analysis rerun):").isEqualTo(result.size());
		softly.assertThat(rerun).^as("Test %s for correct semantic analysis (analysis rerun)", id).
			containsOnlyElementsOf(result);
		softly.assertAll();
		RequirementElementMappingRepository.getInstance().clear();
		resource.unload();
		resource.delete(null);
	}

	/** 
	 * Test method for{@link RequirementDslParser#execute(de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementController, java.lang.String)}.
	 */
	@ParameterizedTest
	@MethodSource("createTestCases")
	def package void testExecute(int id, String req, List<SemanticTextElement> result) {
		val softly = new SoftAssertions();
		val uri = URI.createURI(testfolder + "/" + String.format("test%s", id) + "." + primaryFileExtension);
		val resource = rs.createResource(uri);
		resource.load(new StringInputStream(req), null);
		resource.save(new FileOutputStream(uri.toFileString), null);
		val reqConMock = mock(typeof(RequirementController));
//		doNothing().when(mappingPageMock).updateList();
//		val rc = new RequirementController(getDisplay());
		reqAnalyzer.execute(reqConMock, uri.toFileString);
		verify(reqConMock,times(1)).updateList(reqCaptor.capture())
		softly.assertThat(reqCaptor.value.size()).^as("Checking size of result (analysis rerun):").isEqualTo(
			result.size());
		softly.assertThat(reqCaptor.value).^as("Test %s for correct semantic analysis (analysis rerun)", id).containsOnlyElementsOf(result);
		// Here it should take the prvious result 
//		val rc2 = new RequirementController(getDisplay());
		reqAnalyzer.execute(reqConMock, uri.toFileString);
		verify(reqConMock,times(2)).updateList(reqCaptor.capture());
		softly.assertThat(reqCaptor.getValue().size()).^as("Checking size of result (analysis rerun):").isEqualTo(
			result.size());
		softly.assertThat(reqCaptor.value).^as("Test %s for correct semantic analysis (analysis rerun)", id).
			containsOnlyElementsOf(result);
		softly.assertAll();
		RequirementElementMappingRepository.getInstance().clear();
		resource.unload();
		resource.delete(null);
	}

}
