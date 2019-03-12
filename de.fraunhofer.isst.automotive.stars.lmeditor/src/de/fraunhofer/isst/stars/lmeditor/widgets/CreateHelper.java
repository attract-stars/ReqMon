package de.fraunhofer.isst.stars.lmeditor.widgets;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class CreateHelper {
	
	private XtextParser parser;
	private Display display;
	
	public CreateHelper(Display display) {
		this.parser = new XtextParser();
		this.display = display;
	}
	
	public void createBoxItem(Composite parent, String name, int num) {
		Group group = new Group(parent, SWT.NONE);
		GridLayout layout = new GridLayout(4, false);
	    layout.marginWidth = 10;
	    group.setLayout(layout);
	    
	    Label number = new Label(group, SWT.NONE);
		number.setText("" + num + ". ");
		number.setAlignment(SWT.CENTER);
		
		Label reqLabel = new Label(group, SWT.WRAP);
		
		if (name.equals("")) {
			reqLabel.setText("Requirement element name Description");
		}
		else {
			reqLabel.setText(name);
			
			if(name.contains("Object")) {
				group.setText("Object");
			}
			else if (name.contains("Relation")) {
				group.setText("Relation");
			}
			else if (name.contains("Function")) {
				group.setText("Function");
			}
		}

		Label symbol = new Label(group, SWT.NONE);
		symbol.setText("  <=>  ");
		symbol.setAlignment(SWT.CENTER);
		
		Text text = new Text(group, SWT.MULTI | SWT.BORDER | SWT.WRAP);
		
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
		
		char[] autoActivationCharacters = new char[] { '.', ' ' };
		KeyStroke keyStroke;
		try {
			keyStroke = KeyStroke.getInstance("Ctrl+Space");
			new ContentProposalAdapter(text, new TextContentAdapter(), 
					new SimpleContentProposalProvider(new String[] 
							{ "PorposalOne", "ProposalTwo", "ProposalThree"}),
					keyStroke, autoActivationCharacters);
		} catch (org.eclipse.jface.bindings.keys.ParseException e1) {
			e1.printStackTrace();
		}
		
		text.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String writed = text.getText();
				if (writed.endsWith(" ") || writed.endsWith(".")) {
					System.out.println("new rule");
					parser.parserInput(writed);
				}
			}
		});
		
		GridData gridData_1 = new GridData();
		gridData_1.horizontalAlignment = SWT.FILL;

		GridData gridData_2 = new GridData();
		gridData_2.horizontalAlignment = SWT.FILL;
		
		GridData gridData_3 = new GridData();
		gridData_3.horizontalAlignment = SWT.FILL;
		
		GridData gridData_4 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData_4.minimumHeight = 75;

		number.setLayoutData(gridData_1);
		reqLabel.setLayoutData(gridData_2);
		symbol.setLayoutData(gridData_3);
		text.setLayoutData(gridData_4);
		
	}
	
	public void createButtons(Composite comp) {
		Button saveButton = new Button(comp, SWT.PUSH);
		saveButton.setText("save");
		saveButton.setAlignment(SWT.CENTER);
		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Save called!");
			}
		});

		Button checkButton = new Button(comp, SWT.PUSH);
		checkButton.setText("check");
		checkButton.setAlignment(SWT.CENTER);
		checkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Check called!");
			}
		});
		
		Composite buttonGroup = new Composite(comp, SWT.BORDER);
		buttonGroup.setLayout(new GridLayout(2, false));

		Generator gen = new Generator();
		gen.generateSampleList();
		
		Button generateButton = new Button(buttonGroup, SWT.PUSH);
		generateButton.setText(gen.getNextGenerator());
		generateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				gen.activateGenerator(generateButton.getText());
			}
		});
		
		Button arrow = new Button(buttonGroup, SWT.ARROW | SWT.DOWN);
		arrow.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Arrow called!");
				generateButton.setText(gen.getNextGenerator());
			}
		});
	}
	
	public void createBoxItem_2(Composite parent, String name, int num) {
		Label number = new Label(parent, SWT.NONE);
		number.setText("" + num);

		Label reqLabel = new Label(parent, SWT.BORDER | SWT.WRAP);
		if (name.equals("")) {
			reqLabel.setText("Requirement element name Description");
		}
		else {
			reqLabel.setText(name);
		}

		Label symbol = new Label(parent, SWT.NONE);
		symbol.setText("<=>");
		// symbol.setImage(new Image(display, "C:/Users/Sarah Graf/Documents/Forschungsprojekt_STARS/
				//workspace_stars/de.fraunhofer.isst.automotive.stars.editor/icons/arrow.png"));
		Text text = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.WRAP);
		
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
		
		char[] autoActivationCharacters = new char[] { '.', ' ' };
		KeyStroke keyStroke;
		try {
			keyStroke = KeyStroke.getInstance("Ctrl+Space");
			new ContentProposalAdapter(text, new TextContentAdapter(), 
					new SimpleContentProposalProvider(new String[] 
							{ "PorposalOne", "ProposalTwo", "ProposalThree"}),
					keyStroke, autoActivationCharacters);
		} catch (org.eclipse.jface.bindings.keys.ParseException e1) {
			e1.printStackTrace();
		}
		
		text.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String writed = text.getText();
				if (writed.endsWith(" ") || writed.endsWith(".")) {
					System.out.println("new rule");
					//parserInput(writed, text);
				}
			}
		});
		
		// Color
		//number.setBackground(display.getSystemColor(SWT.COLOR_GRAY));
		//reqLabel.setBackground(display.getSystemColor(SWT.COLOR_GRAY));
		//symbol.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.minimumHeight = 75;

		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = SWT.FILL;

		text.setLayoutData(gridData);
		reqLabel.setLayoutData(gridData);
		number.setLayoutData(gridData2);

	}
	
}
