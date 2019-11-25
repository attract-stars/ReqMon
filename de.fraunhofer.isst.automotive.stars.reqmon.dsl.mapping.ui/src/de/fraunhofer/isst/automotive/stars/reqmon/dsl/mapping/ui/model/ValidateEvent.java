package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.model;

import java.util.EventObject;

/**
 * This class extends the EventObject for the Listener pattern.
 * @author sgraf
 *
 */
public class ValidateEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;
	private boolean isValid;

	public ValidateEvent(Object source, boolean isValid) {
		super(source);
		this.isValid = isValid;
	}
	
	public boolean isValid() {
		return isValid;
	}

}
