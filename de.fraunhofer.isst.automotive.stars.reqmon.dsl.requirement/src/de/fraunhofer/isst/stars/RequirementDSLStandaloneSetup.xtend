/*
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class RequirementDSLStandaloneSetup extends RequirementDSLStandaloneSetupGenerated {

	def static void doSetup() {
		new RequirementDSLStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}
