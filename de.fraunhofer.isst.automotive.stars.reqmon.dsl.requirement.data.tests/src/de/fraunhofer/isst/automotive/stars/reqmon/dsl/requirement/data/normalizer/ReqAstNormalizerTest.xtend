/** 
 */
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

/** 
 * @author mmauritz
 */
@ExtendWith(typeof(InjectionExtension))
@InjectWith(typeof(RequirementDSLInjectorProvider))
package class ReqAstNormalizerTest {

	@Inject package ParseHelper<RequirementModel> parseHelper
	@Inject package ValidationTestHelper validationHelper

	ReqAstNormalizer normalizer;

	EMFCompare comparator

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

	@Test def package void testActorNormalization() {
		val inputModel = parseHelper.parse(
			"
				Req 1: The system and the bus must not perform a lane change to any lane if a vehicle is on that lane and the vehicle is behind the ego vehicle and the vehicle's relative velocity is more than 5 m/s.\n
				"
		)
		validationHelper.assertNoErrors(inputModel);
		val normModel = normalizer.normalize(inputModel);

		val expectedModel = parseHelper.parse(
			"
			Req 1: The system must not perform a lane change to any lane and the bus must not perform a lane change to any lane if a vehicle is on that lane and the vehicle is behind the ego vehicle and the vehicle's relative velocity is more than 5 m/s.\n
			"
		)
		System.out.println(validationHelper === null ? "help null" : "help not null");
		System.out.println(expectedModel === null ? "model null" : "model not null");
		validationHelper.assertNoErrors(expectedModel);
//      assertThat(normModel).^as("Model Comparison").isEqualTo(expectedModel);
		// Compare the two models
		val scope = EMFCompare.createDefaultScope(normModel, expectedModel);
		val result = comparator.compare(scope);
		assertThat(result.differences.size()).^as("EMF Compare Result:").isLessThanOrEqualTo(4);

		val inputModel2 = parseHelper.parse(
			"
			Req 1: The system and the bus must not perform a lane change to any lane if a vehicle and a truck are on that lane and the vehicle is behind the ego vehicle and the vehicle's relative velocity is more than 5 m/s.\n
			"
		)
		validationHelper.assertNoErrors(inputModel2);
		val normModel2 = normalizer.normalize(inputModel2);

		val expectedModel2 = parseHelper.parse(
			"
			Req 1: The system must not perform a lane change to any lane and the bus must not perform a lane change to any lane if a vehicle is on that lane and the vehicle is behind the ego vehicle and the vehicle's relative velocity is more than 5 m/s and a truck is on that lane.\n
			"
		)
		validationHelper.assertNoErrors(expectedModel2);

		val scope2 = EMFCompare.createDefaultScope(normModel2, expectedModel2);
		val result2 = comparator.compare(scope2);
		assertThat(result2.differences.size()).^as("EMF Compare Result:").isLessThanOrEqualTo(4);

		val inputModel3 = parseHelper.parse(
			"
			Req 1: The system and the bus must not start if a person or a animal are in danger.\n
			"
		)
		validationHelper.assertNoErrors(inputModel3);
		val normModel3 = normalizer.normalize(inputModel3);

		val expectedModel3 = parseHelper.parse(
			"
			Req 1: The system must not start and the bus must not start if a person is in danger or a animal is in danger.\n
			"
		)
		validationHelper.assertNoErrors(expectedModel3);

		val scope3 = EMFCompare.createDefaultScope(normModel3, expectedModel3);
		val result3 = comparator.compare(scope3);
		println(result3.toString);
		assertThat(result3.differences.size()).^as("EMF Compare Result:").isLessThanOrEqualTo(4); // for changes are -> is
	}

	@Test def package void testActorPropertiesNormalization() {
		val inputModel = parseHelper.parse(
			"
				Req 1: The system's design and the bus's lights must not start if a person stand in front of them.\n
			"
		)
		validationHelper.assertNoErrors(inputModel);
		val normModel = normalizer.normalize(inputModel);

		val expectedModel = parseHelper.parse(
			"
				Req 1: The system's design must not start and the bus's lights must not start if a person stand in front of them.\n
			"
		)
		validationHelper.assertNoErrors(expectedModel);
//      assertThat(normModel).^as("Model Comparison").isEqualTo(expectedModel);
		// Compare the two models
		val scope = EMFCompare.createDefaultScope(normModel, expectedModel);
		val result = comparator.compare(scope);
		assertThat(result.differences.size()).^as("EMF Compare Result:").isLessThanOrEqualTo(4);

		val inputModel2 = parseHelper.parse(
			"
			Req 1: The system's design and the bus's lights must not start if a person's waist and the animal's life are in danger.\n
			"
		)
		validationHelper.assertNoErrors(inputModel2);
		val normModel2 = normalizer.normalize(inputModel2);

		val expectedModel2 = parseHelper.parse(
			"
			Req 1: The system's design must not start and the bus's lights must not start if a person's waist is in danger and the animal's life is in danger.\n
			"
		)
		validationHelper.assertNoErrors(expectedModel2);

		val scope2 = EMFCompare.createDefaultScope(normModel2, expectedModel2);
		val result2 = comparator.compare(scope2);
		assertThat(result2.differences.size()).^as("EMF Compare Result:").isLessThanOrEqualTo(4); // for changes are -> is
	}
/*TODO
	@Test def package void testRelObjectsNormalization() {

		// Relations
		val inputModel = parseHelper.parse(
			"
				Req 1: The vehicle's position in relation to the street and to the sky must not alter if the vehicle's velocity has not changed.\n
			"
		)
		validationHelper.assertNoErrors(inputModel);
		val normModel = normalizer.normalize(inputModel);

		val expectedModel = parseHelper.parse(
			"
				Req 1: The vehicle's position in relation to the street must not alter and the vehicle's position in relation to the sky must not alter if the vehicle's velocity has not changed.\n
			"
		)
		validationHelper.assertNoErrors(expectedModel);
		// assertThat(normModel).^as("Model Comparison").isEqualTo(expectedModel);
		// Compare the two models
		val scope = EMFCompare.createDefaultScope(normModel, expectedModel);
		val result = comparator.compare(scope);
		assertThat(result.differences.size()).^as("EMF Compare Result:").isLessThanOrEqualTo(4);

		// SentenceBegin
		val inputModel2 = parseHelper.parse(
			"
				Req 1:  In relation to the street and to the sky, the vehicle must not alter if the vehicle's velocity has not changed.\n
			"
		)
		validationHelper.assertNoErrors(inputModel2);
		val normModel2 = normalizer.normalize(inputModel2);

		val expectedModel2 = parseHelper.parse(
			"
				Req 1: In relation to the street, the vehicle must not alter and in relation to the sky, the vehicle must not alter if the vehicle's velocity has not changed.\n
			"
		)
		validationHelper.assertNoErrors(expectedModel2);

		val scope2 = EMFCompare.createDefaultScope(normModel2, expectedModel2);
		val result2 = comparator.compare(scope2);
		assertThat(result2.differences.size()).^as("EMF Compare Result:").isLessThanOrEqualTo(10); // for changes are -> is

		val inputModel3 = parseHelper.parse(
			"
				Req 1: The vehicle's position must not altered in relation to the street and to the sky if the vehicle's velocity has not changed.\n
			"
		)
		validationHelper.assertNoErrors(inputModel3);
		val normModel3 = normalizer.normalize(inputModel3);

		val expectedModel3 = parseHelper.parse(
			"
				Req 1: The vehicle's position must not altered in relation to the street and the vehicle's position must not altered in relation to the sky if the vehicle's velocity has not changed.\n
			"
		)
		validationHelper.assertNoErrors(expectedModel3);

		val scope3 = EMFCompare.createDefaultScope(normModel3, expectedModel3);
		val result3 = comparator.compare(scope3);
		assertThat(result3.differences.size()).^as("EMF Compare Result:").isLessThanOrEqualTo(4); // for changes are -> is
	}

	@Test def package void testSentenceBeginNormalization() {

		// SentenceBegin
		val inputModel2 = parseHelper.parse(
			"
				Req 1:  In relation to the street, the vehicle must not alter if the vehicle's velocity has not changed.\n
			"
		)
		validationHelper.assertNoErrors(inputModel2);
		val normModel2 = normalizer.normalize(inputModel2);

		val expectedModel2 = parseHelper.parse(
			"
				Req 1: The vehicle must not alter in relation to the street if the vehicle's velocity has not changed.\n
			"
		)
		validationHelper.assertNoErrors(expectedModel2);

		val scope2 = EMFCompare.createDefaultScope(normModel2, expectedModel2);
		val result2 = comparator.compare(scope2);
		assertThat(result2.differences.size()).^as("EMF Compare Result:").isLessThanOrEqualTo(4); // for changes are -> is
	}
	*  
	*/
}
