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
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Text;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IProposal;

/**
 * This class implements the IProposal interface and provides a decoration with an info text for the mapping text field 
 * and some proposals.
 * 
 * @author sgraf
 *
 */
public class TestAppProposal implements IProposal {
	
	@Override
	public void createDeco(Text text) {
		ControlDecoration deco = new ControlDecoration(text, SWT.TOP | SWT.LEFT);
		Image image = FieldDecorationRegistry.getDefault()
						.getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage();
		deco.setDescriptionText("Use CTRL + SPACE to see possible values");
		deco.setImage(image);
		deco.setShowOnlyOnFocus(false);
		
		text.addModifyListener(e -> {
			Text source = (Text) e.getSource();
			if (!source.getText().isEmpty()) {
				deco.hide();
			}
			else {
				deco.show();
			}
		});
	}
	
	@Override
	public void getProposal(Text text) {
		char[] autoActivationCharacters = new char[] { '.', ' ' };
		KeyStroke keyStroke;
		try {
			keyStroke = KeyStroke.getInstance("Ctrl+Space");
			new ContentProposalAdapter(text, new TextContentAdapter(), 
					new SimpleContentProposalProvider(new String[] 
							{ "PorposalOne", "ProposalTwo", "ProposalThree"}),
					keyStroke, autoActivationCharacters);
		} catch (org.eclipse.jface.bindings.keys.ParseException e1) {
			System.out.println("Key Binding Parse Exception!");
		}
	}

	@Override
	public char[] getAutoActivationCharacters() {
		return new char[] { '.', ' ' };
	}

}
