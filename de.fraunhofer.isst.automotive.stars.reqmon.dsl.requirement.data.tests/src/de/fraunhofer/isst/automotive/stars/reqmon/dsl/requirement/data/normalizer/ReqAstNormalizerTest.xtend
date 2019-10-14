/** 
 */
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.normalizer

import org.eclipse.xtext.testing.InjectWith
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.junit.jupiter.api.^extension.ExtendWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import de.fraunhofer.isst.stars.tests.RequirementDSLInjectorProvider
import com.google.inject.Inject
import org.eclipse.xtext.testing.util.ParseHelper
import de.fraunhofer.isst.stars.requirementDSL.RequirementModel
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import com.google.inject.Injector
import de.fraunhofer.isst.stars.RequirementDSLStandaloneSetup
import static org.assertj.core.api.Assertions.assertThat
import org.eclipse.emf.compare.match.eobject.IEObjectMatcher
import org.eclipse.emf.compare.match.DefaultMatchEngine
import org.eclipse.emf.compare.utils.UseIdentifiers
import org.eclipse.emf.compare.match.IComparisonFactory
import org.eclipse.emf.compare.match.DefaultComparisonFactory
import org.eclipse.emf.compare.match.DefaultEqualityHelperFactory
import org.eclipse.emf.compare.match.IMatchEngine
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl
import org.eclipse.emf.compare.EMFCompare
import org.eclipse.emf.compare.diff.DefaultDiffEngine
import org.eclipse.emf.compare.diff.DiffBuilder
import org.eclipse.emf.compare.diff.FeatureFilter
import org.eclipse.emf.compare.Match
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.EStructuralFeature

/** 
 * @author mmauritz
 */
@ExtendWith(InjectionExtension)
@InjectWith(RequirementDSLInjectorProvider)
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
	comparator = EMFCompare.builder().setMatchEngineFactoryRegistry(matchEngineRegistry).setDiffEngine(diffEngine).build();
	}

	/** 
	 * @throws java.lang.Exception
	 */
	@AfterEach def package void tearDown() throws Exception {
	}

	@Test def package void testNormalization() {
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
		validationHelper.assertNoErrors(expectedModel);
//      assertThat(normModel).^as("Model Comparison").isEqualTo(expectedModel);
		// Compare the two models
		val scope = EMFCompare.createDefaultScope(normModel, expectedModel);
		val result = comparator.compare(scope);
		assertThat(result.differences.size()).^as("EMF Compare Result:").isEqualTo(0);

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
		assertThat(result2.differences.size()).^as("EMF Compare Result:").isEqualTo(0);

		val inputModel3 = parseHelper.parse(
			"
				Req 1: The system and the bus must not perform a lane change to any lane if a vehicle and a truck are on that lane and the vehicle is behind the ego vehicle and the vehicle's relative velocity is more than 5 m/s.\n
				"
		)
		validationHelper.assertNoErrors(inputModel3);
		val normModel3 = normalizer.normalize(inputModel3);

		val expectedModel3 = parseHelper.parse(
			"
				Req 1: The system must not perform a lane change to any lane and the bus must not perform a lane change to any lane if a vehicle is on that lane and a truck is on that lane and the vehicle is behind the ego vehicle and the vehicle's relative velocity is more than 5 m/s.\n
				"
		)
		validationHelper.assertNoErrors(expectedModel3);

		val scope3 = EMFCompare.createDefaultScope(normModel3, expectedModel3);
		val result3 = comparator.compare(scope3);
		println(result3.toString);
		assertThat(result3.differences.size()).^as("EMF Compare Result:").isEqualTo(0);
	}
}
