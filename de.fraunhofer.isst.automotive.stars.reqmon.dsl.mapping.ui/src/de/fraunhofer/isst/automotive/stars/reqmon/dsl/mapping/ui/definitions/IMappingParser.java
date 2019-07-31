package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions;

import org.eclipse.emf.common.util.URI;

import com.google.inject.Injector;

/**
 * An implementation of this interface should provide a dsl injector written with xtext for the mapping parsing.
 * 
 * @author sgraf
 *
 */
public interface IMappingParser {
	
	/**
	 * Returns the xtext mapping language injector. The system model URI is needed to complete the mapping language creation.
	 * @param systemModelUri the system model URI
	 * @return the xtext mapping language injector
	 */
	public Injector getDslInjector(URI systemModelUri);

}
