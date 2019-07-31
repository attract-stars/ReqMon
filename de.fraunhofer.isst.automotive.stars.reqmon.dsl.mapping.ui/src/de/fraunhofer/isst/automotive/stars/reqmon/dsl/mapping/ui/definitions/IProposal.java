package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions;

import org.eclipse.swt.widgets.Text;

/**
 * An implementation of this interface should provide proposals for the mapping language and can add a decoration 
 * to the mapping text field with additional informations.
 * 
 * @author sgraf
 *
 */
public interface IProposal {
	
	/**
	 * Provides proposals for the mapping language.
	 * @param text the mapping text field
	 */
	public void getProposal(Text text);
	
	/**
	 * Creates a decoration for the mapping text field which can provide additional informations when it is hovered.
	 * @param text the mapping text field
	 */
	public void createDeco(Text text);
	
	/**
	 * Returns the chars that activate the widget which contains the list of proposals.
	 * @return the chars that activate the proposals
	 */
	public char[] getAutoActivationCharacters();

}
