package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.test;

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
public class TestProposal implements IProposal {

	public TestProposal() {
	}
	
	@Override
	public void getProposal(Text text) {
		char[] autoActivationCharacters = new char[] { '+' };
		KeyStroke keyStroke;
		try {
			keyStroke = KeyStroke.getInstance("Ctrl+Space");
			new ContentProposalAdapter(text, new TextContentAdapter(), 
					new SimpleContentProposalProvider(new String[] 
							{ "<", ">", "="}),
					keyStroke, autoActivationCharacters);
		} catch (org.eclipse.jface.bindings.keys.ParseException e1) {
			System.out.println("Key Binding Parse Exception!");
		}
	}
	
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
	public char[] getAutoActivationCharacters() {
		return new char[] { ' ' };
	}
	
	

}
