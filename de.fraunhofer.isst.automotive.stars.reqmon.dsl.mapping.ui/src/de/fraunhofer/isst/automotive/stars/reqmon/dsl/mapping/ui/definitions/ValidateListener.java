package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions;

import java.util.EventListener;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.model.ValidateEvent;

/**
 * This interface extends the EventListener for the listener pattern.
 * @author sgraf
 *
 */
public interface ValidateListener extends EventListener {
	
	/**
	 * This method is called if a ValidateEvent occurs.
	 * @param e a ValidateEvent
	 */
	void advertisment(ValidateEvent e);

}
