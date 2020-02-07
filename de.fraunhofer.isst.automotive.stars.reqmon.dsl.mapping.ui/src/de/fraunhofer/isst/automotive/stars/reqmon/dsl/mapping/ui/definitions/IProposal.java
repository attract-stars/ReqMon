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
