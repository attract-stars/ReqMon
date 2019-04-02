package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.editor;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.logic.ExistingGeneratorsHandler;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.logic.GeneratorController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.logic.ReqDSLParser;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.models.RequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.models.SystemElement;

public class MappingPage {
	
	private Composite composite;
	private Display display;
	private RequirementElement reqElem;
	private SystemElement sysElem;
	private Shell shell;
	private Boolean isApp;
	private ReqDSLParser parser;
	private Composite compositeInside;
	
	
	public MappingPage(Composite composite, Display display, Shell shell, boolean isApp) {
		this.composite = composite;
		this.display = display;
		this.reqElem = new RequirementElement();
		reqElem.createSampleElements();
		this.sysElem = new SystemElement();
		this.shell = shell;
		this.isApp = isApp;
		this.parser = new ReqDSLParser(isApp);
	}
	
	public RequirementElement getReqElem() {
		return reqElem;
	}
	
	public void createMappingPage() {
		Composite maincomp = new Composite(composite, SWT.NONE);
		maincomp.setLayout(new FillLayout());
		Composite inner = new Composite(maincomp, SWT.NONE);
		createBorder(inner);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(inner, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.addListener(SWT.Resize, event -> updateMinSize(scrolledComposite));
		
		compositeInside = new Composite(scrolledComposite, SWT.BORDER);
		FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		fillLayout.marginWidth = 10;
		fillLayout.marginHeight = 10;
		fillLayout.spacing = 10;
		compositeInside.setLayout(fillLayout);
		
		for (int i = 0; i < reqElem.getElementSize(); i++) {
			createBoxItem(compositeInside, i, i+1);
		}
		
		scrolledComposite.setContent(compositeInside);
		
		Composite top = new Composite(composite, SWT.NONE);
		createTop(top);
		
		Composite buttonField = new Composite(composite, SWT.NONE);
		createButtons(buttonField);
		setPositons(maincomp, buttonField, top);
	}
	
	public void updateMinSize(Composite comp) {
		Rectangle clientArea = comp.getClientArea();
		
		if (comp instanceof ScrolledComposite) {
			clientArea.width -= comp.getVerticalBar().getSize().x;
			Point minSize = ((ScrolledComposite)comp).getContent().computeSize(clientArea.width, SWT.DEFAULT);
			((ScrolledComposite)comp).setMinSize(minSize);
		}
		else {
			Point minSize = comp.computeSize(clientArea.width, SWT.DEFAULT);
			comp.setSize(minSize);
		}
		
	}
	
	public void createTop(Composite top) {
		top.setLayout(new FillLayout());
		
		Composite reqComp = new Composite(top, SWT.NONE);
		Composite sysComp = new Composite(top, SWT.NONE);
		GridLayout compLayout = new GridLayout(2, false);
		reqComp.setLayout(compLayout);
		sysComp.setLayout(compLayout);
		
		Text reqPath = new Text(reqComp, SWT.BORDER);
		Text sysPath = new Text(sysComp, SWT.BORDER);
		reqPath.setText("path of the requirement file ...");
		reqPath.setEditable(false);
		sysPath.setText("path of the system file ...");
		sysPath.setEditable(false);
		
		Button reqButton = new Button(reqComp, SWT.PUSH);
		Button sysButton = new Button(sysComp, SWT.PUSH);
		reqButton.setText(" Requirement ");
		sysButton.setText(" System ");
		
		reqButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Requirements called!");
				//RequirementTable table = new RequirementTable(display);
				//table.createTable();
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
		        fd.setText("Open");
		        fd.setFilterPath("C:/");
		        String[] filterExt = { "*.txt", "*.doc", ".rtf", "*.*" };
		        fd.setFilterExtensions(filterExt);
		        String selected = fd.open();
		        if (selected != null) {
		        	System.out.println(selected);
		        	reqElem.setPath(selected);
		        	reqPath.setText(selected);
		        	reqElem.readFile();
		        	updateList();
		        }
			}
		});
		
		
		sysButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("System called!");
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
		        fd.setText("Open");
		        fd.setFilterPath("C:/");
		        String[] filterExt = { "*.txt", "*.doc", ".rtf", "*.*" };
		        fd.setFilterExtensions(filterExt);
		        String selected = fd.open();
		        if (selected != null) {
		        	System.out.println(selected);
			        sysElem.setPath(selected);
			        sysPath.setText(selected);
		        }
			}
		});
		
		
		GridData gridData = new GridData(SWT.FILL,SWT.FILL, true, false);
		reqPath.setLayoutData(gridData);
		sysPath.setLayoutData(gridData);
		
	}
	
	public void createBorder(Composite inner) {
		FillLayout innerlayout = new FillLayout();
		innerlayout.marginHeight = 1;
		innerlayout.marginWidth = 1;
		inner.setLayout(innerlayout);
		inner.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW));
	}
	
	public void createBoxItem(Composite parent, int index, int num) {
		//Composite box = new Composite(parent, SWT.BORDER);
		//FillLayout boxlayout = new FillLayout();
		//box.setLayout(boxlayout);
		Group group = new Group(parent, SWT.NONE);
		GridLayout layout = new GridLayout(4, false);
	    //boxlayout.marginWidth = 10;
	    //boxlayout.marginHeight = 5;
		layout.marginWidth = 5;
		layout.horizontalSpacing = 15;
	    group.setLayout(layout);
	    group.pack();
	    
	    Label number = new Label(group, SWT.NONE);
		number.setText("  " + num + ". ");
		number.setAlignment(SWT.CENTER);
		
		//ScrolledComposite scrollLabel = new ScrolledComposite(group, SWT.V_SCROLL | SWT.BORDER);
	    //scrollLabel.setExpandVertical(true);
		//scrollLabel.setExpandHorizontal(true);
		//scrollLabel.addListener(SWT.Resize, event -> updateMinSize(scrollLabel));
		
		Label reqLabel = new Label(group, SWT.WRAP);
		String name = reqElem.getElement(index);
		String type = reqElem.getType(index);
		if (name == null) {
			System.out.println("No name!");
			reqLabel.setText("Requirement element name Description");
		}
		else if (name.equals("")) {
			reqLabel.setText("Requirement element name Description");
		}
		else {
			reqLabel.setText(name);
			
			if(type.equals("object")) {
				group.setText("Object");
			}
			else if (type.equals("relation")) {
				group.setText("Relation");
			}
			else if (type.equals("function")) {
				group.setText("Function");
			}
		}

		Label symbol = new Label(group, SWT.NONE);
		symbol.setText("  <=>  \t");
		symbol.setAlignment(SWT.CENTER);
		
		//ScrolledComposite scrollText = new ScrolledComposite(group, SWT.V_SCROLL | SWT.BORDER);
		//scrollText.setExpandVertical(true);
		//scrollText.setExpandHorizontal(true);
		//scrollText.addListener(SWT.Resize, event -> updateMinSize(scrollText));
		
		Text text = new Text(group, SWT.MULTI | SWT.V_SCROLL | SWT.BORDER | SWT.WRAP);
		
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
					if (isApp) {
						parser.parserInput(writed);
					}
				}
			}
		});
		
		//scrollLabel.setContent(reqLabel);
		//scrollText.setContent(text);
		
		GridData gridData_1 = new GridData();
		gridData_1.horizontalAlignment = SWT.FILL;

		GridData gridData_2 = new GridData();
		gridData_2.horizontalAlignment = SWT.FILL;
		gridData_2.grabExcessHorizontalSpace = true;
		gridData_2.minimumWidth = 300;
		
		GridData gridData_3 = new GridData();
		gridData_3.horizontalAlignment = SWT.FILL;
		
		GridData gridData_4 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData_4.minimumHeight = 75;
		gridData_4.minimumWidth = 350;

		number.setLayoutData(gridData_1);
		reqLabel.setLayoutData(gridData_2);
		symbol.setLayoutData(gridData_3);
		text.setLayoutData(gridData_4);
	}
	
	public void createButtons(Composite comp) {
		GridLayout buttonFieldLayout = new GridLayout();
		buttonFieldLayout.numColumns = 4;
		comp.setLayout(buttonFieldLayout);
		
		Composite box = new Composite(comp, SWT.NONE);
		FillLayout boxlayout = new FillLayout();
		boxlayout.marginHeight = 5;
		boxlayout.marginWidth = 5;
		boxlayout.spacing = 15;
		box.setLayout(boxlayout);
		
		Button saveButton = new Button(box, SWT.PUSH);
		saveButton.setText("Save");
		saveButton.setAlignment(SWT.CENTER);
		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Save called!");
				FileDialog fd = new FileDialog(shell, SWT.SAVE);
		        fd.setText("Save");
		        fd.setFilterPath("C:/");
		        String[] filterExt = { "*.txt", "*.doc", ".rtf", "*.*" };
		        fd.setFilterExtensions(filterExt);
		        String selected = fd.open();
		        System.out.println(selected);
			}
		});
		
		ExistingGeneratorsHandler handler = new ExistingGeneratorsHandler();

		Button checkButton = new Button(box, SWT.PUSH);
		checkButton.setText("Check");
		checkButton.setAlignment(SWT.CENTER);
		checkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Check called!");
				handler.execute();
			}
		});
		
		GeneratorController gen = new GeneratorController();
		handler.generateGeneratorList(gen);
		if (gen.getGenerators().isEmpty()) {
			gen.generateSampleList();
		}
		
		Button genButton = new Button(box, SWT.PUSH);
		genButton.setText(gen.getGenerateLabels().get(0));
		genButton.setAlignment(SWT.CENTER);
		genButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handler.executeSelectedGenerator(gen.getActiveGenerator());
			}
		});

		
		
		Combo comboDropDown = new Combo(box, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
	    comboDropDown.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Selected: " + comboDropDown.getText());	
				genButton.setText(gen.getLabel(comboDropDown.getText()));
				box.layout(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    for (String name : gen.getGenerators()) {
	      comboDropDown.add(" " + name);
	    }
	    comboDropDown.select(0);
	}
	
	public void setPositons(Composite maincomp, Composite buttonField, Composite top) {
		FormData formData = new FormData();
		formData.top = new FormAttachment(0, 80);
		formData.bottom = new FormAttachment(100, -80);
		formData.left = new FormAttachment(0, 20);
		formData.right = new FormAttachment(100, -20);
		maincomp.setLayoutData(formData);
		
		FormData formDataButton = new FormData();
		formDataButton.top = new FormAttachment(maincomp, 5);
		formDataButton.right = new FormAttachment(100, -20);
		buttonField.setLayoutData(formDataButton);
		
		FormData formDataTop = new FormData();
		formDataTop.top = new FormAttachment(0, 20);
		formDataTop.bottom = new FormAttachment(maincomp, -10);
		formDataTop.left = new FormAttachment(0, 20);
		formDataTop.right = new FormAttachment(100, -20);
		top.setLayoutData(formDataTop);
	}
	
	public void setColor(Composite shell, Composite composite, Composite buttonField) {
		shell.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		composite.setBackground(display.getSystemColor(SWT.COLOR_GRAY));
		buttonField.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
	}
	
	private void updateList() {
		if (compositeInside == null) {
			return;
		}
		for (Control con : compositeInside.getChildren()) {
			con.dispose();
		}
		for (int i = 0; i < reqElem.getElementSize(); i++) {
			createBoxItem(compositeInside, i, i+1);
			compositeInside.pack();
			compositeInside.layout(true);
			updateMinSize(compositeInside.getParent());
		}
	}

	public void setListComposite(Composite composite2) {
		this.compositeInside = composite2;
		
	}

}
