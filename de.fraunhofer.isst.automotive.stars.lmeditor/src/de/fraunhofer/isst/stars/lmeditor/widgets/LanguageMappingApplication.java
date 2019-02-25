package de.fraunhofer.isst.stars.lmeditor.widgets;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xtext.parser.ParseException;

import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public class LanguageMappingApplication {
	
	static Display display;
	static XtextParser parser;

	public static void main(String[] args) {

		display = new Display();
		parser = new XtextParser();
		
		Shell shell = new Shell(display);
		shell.setText("Language Mapping Editor");
		FormLayout shellLayout = new FormLayout();
		shellLayout.marginWidth = 100;
		shellLayout.marginHeight = 50;
		shell.setLayout(shellLayout);

		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.addListener(SWT.Resize, event -> updateMinSize(scrolledComposite));
		
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new GridLayout(4, false));
		
		ArrayList<String> nameList = new ArrayList<String>();
		addNamesToList(nameList);
		for (int i = 0; i < nameList.size(); i++)
			createBoxItem(composite, nameList.get(i), i+1);
		
		scrolledComposite.setContent(composite);
		
		Button reqButton = new Button(shell, SWT.PUSH);
		reqButton.setText("requirements");
		reqButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Requirements called!");
				//RequirementTable table = new RequirementTable(display);
				//table.createTable();
			}
		});
		
		Button systemButton = new Button(shell, SWT.PUSH);
		systemButton.setText("system");
		systemButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("System called!");
			}
		});

		Composite buttonField = new Composite(shell, SWT.NONE);
		GridLayout buttonFieldLayout = new GridLayout();
		buttonFieldLayout.numColumns = 3;
		buttonField.setLayout(buttonFieldLayout);
		createButtons(buttonField);

		FormData formData = new FormData();
		formData.top = new FormAttachment(0, 0);
		formData.bottom = new FormAttachment(100, 0);
		formData.left = new FormAttachment(0, 0);
		formData.right = new FormAttachment(100, 0);
		scrolledComposite.setLayoutData(formData);
		
		FormData formDataButton = new FormData();
		formDataButton.top = new FormAttachment(scrolledComposite, 5);
		formDataButton.right = new FormAttachment(100, 0);
		buttonField.setLayoutData(formDataButton);
		
		FormData formDataReqButton = new FormData();
		formDataReqButton.bottom = new FormAttachment(scrolledComposite, -10);
		formDataReqButton.left = new FormAttachment(0, 0);
		reqButton.setLayoutData(formDataReqButton);
		
		FormData formDataSysButton = new FormData();
		formDataSysButton.bottom = new FormAttachment(scrolledComposite, -10);
		formDataSysButton.right = new FormAttachment(100, 0);
		systemButton.setLayoutData(formDataSysButton);

		
		// Color
		shell.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		composite.setBackground(display.getSystemColor(SWT.COLOR_GRAY));
		buttonField.setBackground(display.getSystemColor(SWT.COLOR_BLACK));

		shell.setSize(800, 600);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	private static void addNamesToList(ArrayList<String> nameList) {
		// TODO getModelelemts
		// add Modelelemts to list
		for (int i = 0; i < 10; i++) {
			nameList.add("Object ...");
			nameList.add("Relation ...");
			nameList.add("Function ...");
		}
	}

	private static void updateMinSize(ScrolledComposite scrolledComposite) {
		Rectangle clientArea = scrolledComposite.getClientArea();
		clientArea.width -= scrolledComposite.getVerticalBar().getSize().x;
		Point minSize = scrolledComposite.getContent().computeSize(clientArea.width, SWT.DEFAULT);
		scrolledComposite.setMinSize(minSize);
	}

	private static void createBoxItem(Composite parent, String name, int num) {
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
					parserInput(writed, text);
				}
			}
		});
		
		// Color
		number.setBackground(display.getSystemColor(SWT.COLOR_GRAY));
		//reqLabel.setBackground(display.getSystemColor(SWT.COLOR_GRAY));
		symbol.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.minimumHeight = 75;

		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = SWT.FILL;

		text.setLayoutData(gridData);
		reqLabel.setLayoutData(gridData);
		number.setLayoutData(gridData2);

	}

	private static void createButtons(Composite comp) {
		Button saveButton = new Button(comp, SWT.PUSH);
		saveButton.setText("save");
		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Save called!");
			}
		});

		Button checkButton = new Button(comp, SWT.PUSH);
		checkButton.setText("check");
		checkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Check called!");
			}
		});

		Button generateButton = new Button(comp, SWT.PUSH);
		generateButton.setText("generate");
		generateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Generate called!");
			}
		});
	}

	private static void parserInput(String text, Text comp) {
		Reader reader = new StringReader(text);
		String element = "";
		EObject result;
		try {
			result = parser.parse(reader);
			element = result.toString();
			System.out.println("Parser result: " + element);
			comp.setBackground(display.getSystemColor(SWT.COLOR_GREEN));
		} catch (ParseException e) {
			System.out.println("Syntaxerror");
			comp.setBackground(display.getSystemColor(SWT.COLOR_RED));
		} catch (Exception e) {
			System.out.println("Parser Exception");
		}
	}

}
