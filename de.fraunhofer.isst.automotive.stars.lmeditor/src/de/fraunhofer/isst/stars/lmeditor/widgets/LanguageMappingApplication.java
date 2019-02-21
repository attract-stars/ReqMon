package de.fraunhofer.isst.stars.lmeditor.widgets;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
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

	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Language Mapping Editor");
		FormLayout shellLayout = new FormLayout();
		shellLayout.marginWidth = 100;
		shellLayout.marginHeight = 50;
		shell.setLayout(shellLayout);
		shell.setBackground(display.getSystemColor(SWT.COLOR_BLUE));

		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.addListener(SWT.Resize, event -> updateMinSize(scrolledComposite));
		
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new GridLayout(4, false));
		composite.setBackground(display.getSystemColor(SWT.COLOR_DARK_GREEN));
		
		ArrayList<String> nameList = new ArrayList<String>();
		addNamesToList(nameList);
		for (int i = 0; i < nameList.size(); i++)
			createBoxItem(display, composite, nameList.get(i), i+1);
		
		scrolledComposite.setContent(composite);

		Composite buttonField = new Composite(shell, SWT.NONE);
		GridLayout buttonFieldLayout = new GridLayout();
		buttonFieldLayout.numColumns = 3;
		buttonField.setLayout(buttonFieldLayout);
		buttonField.setBackground(display.getSystemColor(SWT.COLOR_DARK_GREEN));
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

	private static void createBoxItem(Display display, Composite parent, String name, int num) {
		Label number = new Label(parent, SWT.BORDER);
		number.setText("" + num);
		number.setBackground(display.getSystemColor(SWT.COLOR_DARK_YELLOW));

		Label reqLabel = new Label(parent, SWT.BORDER | SWT.WRAP);
		if (name.equals("")) {
			reqLabel.setText("Requirement element name Description");
		}
		else {
			reqLabel.setText(name);
		}
		reqLabel.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		Label symbol = new Label(parent, SWT.BORDER);
		symbol.setText("<=>");
		symbol.setBackground(display.getSystemColor(SWT.COLOR_DARK_YELLOW));

		Text text = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.WRAP);
		text.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String writed = text.getText();
				if (writed.endsWith(".")) {
					System.out.println("new rule");
					parserInput(writed);
				}
			}
		});

		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
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

	private static void parserInput(String text) {
		XtextParser parser = new XtextParser();
		Reader reader = new StringReader(text);
		String element = "";
		EObject result;
		try {
			result = parser.parse(reader);
			element = result.toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Syntaxerror");
		}
		System.out.println("Parser result: " + element);
	}

}
