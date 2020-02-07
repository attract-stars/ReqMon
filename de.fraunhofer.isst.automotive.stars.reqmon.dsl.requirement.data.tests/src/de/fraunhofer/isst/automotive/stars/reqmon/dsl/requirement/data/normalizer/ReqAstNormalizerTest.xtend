/*******************************************************************************
 * Copyright (C) 2020 Fraunhofer ISST
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.normalizer

import com.google.inject.Inject
import de.fraunhofer.isst.stars.RequirementDSLStandaloneSetup
import de.fraunhofer.isst.stars.requirementDSL.RequirementModel
import de.fraunhofer.isst.stars.tests.RequirementDSLInjectorProvider
import org.eclipse.emf.compare.EMFCompare
import org.eclipse.emf.compare.Match
import org.eclipse.emf.compare.diff.DefaultDiffEngine
import org.eclipse.emf.compare.diff.DiffBuilder
import org.eclipse.emf.compare.diff.FeatureFilter
import org.eclipse.emf.compare.match.DefaultComparisonFactory
import org.eclipse.emf.compare.match.DefaultEqualityHelperFactory
import org.eclipse.emf.compare.match.DefaultMatchEngine
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl
import org.eclipse.emf.compare.utils.UseIdentifiers
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.^extension.ExtendWith

import static org.assertj.core.api.Assertions.assertThat
import org.apache.log4j.Logger
import org.eclipse.xtext.serializer.impl.Serializer
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.api.BeforeAll
import org.apache.log4j.SimpleLayout
import org.apache.log4j.ConsoleAppender
import org.junit.BeforeClass

/** 
 * @author mmauritz
 */
//TODO THE ORDER IS DIFFERENT AFTER NORMALIZATION!
@ExtendWith(typeof(InjectionExtension))
@InjectWith(typeof(RequirementDSLInjectorProvider))
package class ReqAstNormalizerTest {

	@Inject
	static Logger logger = Logger.getLogger(typeof(ReqAstNormalizerTest));

	@Inject package ParseHelper<RequirementModel> parseHelper
	@Inject package ValidationTestHelper validationHelper
	@Inject package Serializer serializer;

	ReqAstNormalizer normalizer;

	EMFCompare comparator

	@BeforeClass
	def package void configureLogger() {
		val layout = new SimpleLayout();
		val consoleAppender = new ConsoleAppender(layout, ConsoleAppender.SYSTEM_ERR);
		logger.addAppender(consoleAppender);
	}

	/** 
	 * @throws java.lang.Exception
	 */
	@BeforeEach def package void setUp() throws Exception {
		val injector = new RequirementDSLStandaloneSetup().createInjectorAndDoEMFRegistration();
		normalizer = injector.getInstance(typeof(ReqAstNormalizer));
//		injector.injectMembers(normalizer);
		// Configure EMF Compare
		val matcher = DefaultMatchEngine.createDefaultEObjectMatcher(UseIdentifiers.NEVER);
		val comparisonFactory = new DefaultComparisonFactory(new DefaultEqualityHelperFactory());
		val matchEngineFactory = new MatchEngineFactoryImpl(matcher, comparisonFactory);
		matchEngineFactory.setRanking(20);
		val matchEngineRegistry = new MatchEngineFactoryRegistryImpl();
		matchEngineRegistry.add(matchEngineFactory);
		val diffProcessor = new DiffBuilder();
		val diffEngine = new DefaultDiffEngine(diffProcessor) {
			override FeatureFilter createFeatureFilter() {
				return new FeatureFilter() {
					override boolean isIgnoredReference(Match match, EReference reference) {
						return reference == EcorePackage.Literals.ENAMED_ELEMENT__NAME ||
							super.isIgnoredReference(match, reference);
					}

					override boolean checkForOrderingChanges(EStructuralFeature feature) {
						return false;
					}
				};
			}
		};
		comparator = EMFCompare.builder().setMatchEngineFactoryRegistry(matchEngineRegistry).setDiffEngine(diffEngine).
			build();
	}

	/** 
	 * @throws java.lang.Exception
	 */
	@AfterEach def package void tearDown() throws Exception {
	}

	//TODO SOME OF THE ERROR BOUNDS ARE WAY TOO HIGH
	def static Stream<Arguments> createTestData() {
		// arguments For tests are input model and expected model and error bound
		// ACtor Normalization
		val data1 = Arguments.of("
				Req 1: The system and the bus must not perform a lane change to any lane if a vehicle is on that lane and the vehicle is behind the ego vehicle and the vehicle's relative velocity is more than 5 m/s.\n
				", "
			Req 1: The system must not perform a lane change to any lane and the bus must not perform a lane change to any lane if a vehicle is on that lane and the vehicle is behind the ego vehicle and the vehicle's relative velocity is more than 5 m/s.\n
			", 4);
		val data2 = Arguments.of("
			Req 1: The system and the bus must not perform a lane change to any lane if a vehicle and a truck are on that lane and the vehicle is behind the ego vehicle and the vehicle's relative velocity is more than 5 m/s.\n
			", "
			Req 1: The system must not perform a lane change to any lane and the bus must not perform a lane change to any lane if a vehicle is on that lane and the vehicle is behind the ego vehicle and the vehicle's relative velocity is more than 5 m/s and a truck is on that lane.\n
			", 4);
		val data3 = Arguments.of("
			Req 1: The system and the bus must not start if a person or a animal are in danger.\n
			", "
			Req 1: The system must not start and the bus must not start if a person is in danger or a animal is in danger.\n
			", 4);
		// Actor Properties Normalization
		val data4 = Arguments.of("
				Req 1: The system's design and the bus's lights must not start if a person stand in front of them.\n
			", "
				Req 1: The system's design must not start and the bus's lights must not start if a person stand in front of them.\n
			", 4);
		val data5 = Arguments.of("
			Req 1: The system's design and the bus's lights must not start if a person's waist and the animal's life are in danger.\n
			", "
			Req 1: The system's design must not start and the bus's lights must not start if a person's waist is in danger and the animal's life is in danger.\n
			", 4);
		// RElObjectNormalization
		// Relations
		val data6 = Arguments.of("
				Req 1: The vehicle's position in relation to the street and to the sky must not alter if the vehicle's velocity has not changed.\n
			", "
				Req 1: The vehicle's position in relation to the street must not alter and the vehicle's position in relation to the sky must not alter if the vehicle's velocity has not changed.\n
			", 4);
		// SentenceBegin
		// SentenceBeginNormalization
		val data7 = Arguments.of("
				Req 1:  In relation to the street, the vehicle must not alter if the vehicle's velocity has not changed.\n
			", "
				Req 1: The vehicle must not alter in relation to the street if the vehicle's velocity has not changed.\n
			", 4);
		val data8 = Arguments.of("
				Req 1: In relation to the street and to the sky, the vehicle must not alter if the vehicle's velocity has not changed.\n
			", "
				Req 1: The vehicle's position must not alter in relation to the street and the vehicle's position must not alter in relation to the sky if the vehicle's velocity has not changed.\n
			", 31);
		val data9 = Arguments.of("
				Req 1: The vehicle's position must not alter in relation to the street and to the sky if the vehicle's velocity has not changed.\n
			", "
				Req 1: The vehicle's position must not alter in relation to the street and the vehicle's position must not alter in relation to the sky if the vehicle's velocity has not changed.\n
			", 4);
		val data10 = Arguments.of("
				Req 1: The vehicle's position in relation to the street and to the sky and the bus' size in relation to the length and to the height must not alter if the vehicle's velocity has not changed.\n
			", "
				Req 1: The vehicle's position must not alter in relation to the street and the vehicle's position must not alter in relation to the sky and the bus' size must not alter in relation to the length and the bus' size must not alter in relation to the height if the vehicle's velocity has not changed.\n
			", 34);	
			val data11 = Arguments.of("
				Req 1: The vehicle's position and the bus' size must not alter in relation to the street and to the sky if the vehicle's velocity has not changed.\n
			", "
				Req 1: The vehicle's position must not alter in relation to the street and the vehicle's position must not alter in relation to the sky and the bus' size must not alter in relation to the length and the bus' size must not alter in relation to the height if the vehicle's velocity has not changed.\n
			", 31);	
			val data12 = Arguments.of("
				Req 1: The vehicle's position in relation to the street and to the sky and the bus' size in relation to the length and to the height must not alter in relation to the world and to the hell if the vehicle's velocity has not changed.\n
			", "
				Req 1: The vehicle's position must not alter in relation to the street and the vehicle's position must not alter in relation to the sky and the bus' size must not alter in relation to the length and the bus' size must not alter in relation to the height if the vehicle's velocity has not changed.\n
			",96);
		
			//TODO CONSTRAINTS ARE MISSING -> double object and double time and
			//TODO RELATIVE CLAUSE IS MSSING
		return Stream.of(data1, data2, data3, data4, data5, data6, data7, data8, data9,data10,data11,data12);
	}

	@ParameterizedTest
	@MethodSource("createTestData")
	def package void testNormalization(String input, String expectation, int possibleError) {
		logger.info("%%%% Starting New Test %%%")
		val inputModel = parseHelper.parse(input)
		logger.info("Input: " + serializer.serialize(inputModel))
		validationHelper.assertNoErrors(inputModel);
		val normModel = normalizer.normalize(inputModel);
		logger.info("Normalized: " + serializer.serialize(normModel))
		val expectedModel = parseHelper.parse(expectation);
		logger.info("Expected: " + serializer.serialize(expectedModel))
		logger.debug(validationHelper === null ? "help null" : "help not null");
		logger.debug(expectedModel === null ? "model null" : "model not null");
		logger.info("Difference: " + difference(serializer.serialize(normModel),serializer.serialize(expectedModel)))
		validationHelper.assertNoErrors(expectedModel);
//      assertThat(normModel).^as("Model Comparison").isEqualTo(expectedModel);
		// Compare the two models
		val scope = EMFCompare.createDefaultScope(normModel, expectedModel);
		val result = comparator.compare(scope);
		assertThat(result.differences.size()).^as("EMF Compare Result:").isLessThanOrEqualTo(possibleError);
		logger.info("Test End!\n\n")
	}

	def static int indexOfDifference(String str1, String str2) {
		if (str1 === str2) {
			return -1;
		}
		if (str1 === null || str2 === null) {
			return 0;
		}
		for (var i = 0; i < str1.length() && i < str2.length(); i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				if (i < str2.length() || i < str1.length()) {
					return i;
				}
			}
		}
		return -1;
	}

	// copy
	def static String difference(String str1, String str2) {
		if (str1 === null) {
			return str2;
		}
		if(str2 === null)
		{
			return str1;
		}
		val at = indexOfDifference(str1, str2);
		if (at === -1) {
			return "";
		}
		return str2.substring(at);
	}

}
