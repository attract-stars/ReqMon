package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.definitions;

import org.eclipse.swt.widgets.Text;

public interface IProposal {
	
	public void getProposal(Text text);
	
	public void createDeco(Text text);
	
	public char[] getAutoActivationCharacters();

}
