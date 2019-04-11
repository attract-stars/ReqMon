package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.logic.GeneratorController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.logic.ParserController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.logic.ProposalController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.logic.RequirementController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.logic.SystemController;


public class MappingPage {
	
	private Composite composite;
	private Display display;
	private Shell shell;
	private RequirementController reqCon;
	private SystemController sysCon;
	private ParserController parserCon;
	private ProposalController propCon;
	private Composite compositeInside;
	private GeneratorController genCon;
	
	
	public MappingPage(Composite composite, Display display, Shell shell, boolean isApp) {
		this.composite = composite;
		this.display = display;
		this.shell = shell;
		this.reqCon = new RequirementController();
		this.sysCon = new SystemController();
		this.propCon = new ProposalController();
		this.parserCon = new ParserController(isApp);
		this.genCon = new GeneratorController();
	}
	
	public RequirementController getReqCon() {
		return reqCon;
	}
	
	public void createMappingPage() {
		setColor(composite, SWT.COLOR_WHITE);
		Composite maincomp = new Composite(composite, SWT.NONE);
		maincomp.setLayout(new FillLayout());
		//Composite inner = new Composite(maincomp, SWT.NONE);
		//createBorder(inner);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(maincomp, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.addListener(SWT.Resize, event -> updateMinSize(scrolledComposite));
		
		compositeInside = new Composite(scrolledComposite, SWT.NONE);
		
		GridLayout insideLayout = new GridLayout(1, false);
		insideLayout.marginWidth = 10;
		insideLayout.marginHeight = 10;
		insideLayout.horizontalSpacing = 10;
		compositeInside.setLayout(insideLayout);
		setColor(compositeInside, SWT.COLOR_WIDGET_DARK_SHADOW);
		
		for (int i = 0; i < reqCon.getElementSize(); i++) {
			createBoxItem(compositeInside, i, i+1);
		}
		
		scrolledComposite.setContent(compositeInside);
		
		Composite top = new Composite(composite, SWT.NONE);
		createTop(top);
		
		Composite buttonFieldOne = new Composite(composite, SWT.NONE);
		createSaveAndCheckButtons(buttonFieldOne);
		Composite buttonFieldTwo = new Composite(composite, SWT.NONE);
		createGenerateButtons(buttonFieldTwo);
		setPositons(maincomp, buttonFieldOne, buttonFieldTwo, top);
	}
	
	private void updateMinSize(Composite comp) {
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
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
		        fd.setText("Open");
		        fd.setFilterPath("C:/");
		        String[] filterExt = reqCon.getFilterExt();
		        fd.setFilterExtensions(filterExt);
		        String selected = fd.open();
		        if (selected != null) {
		        	System.out.println(selected);
		        	reqCon.setPath(selected);
		        	reqPath.setText(selected);
		        	reqCon.executeRequirement();
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
		        String[] filterExt = sysCon.getFilterExt();
		        fd.setFilterExtensions(filterExt);
		        String selected = fd.open();
		        if (selected != null) {
		        	System.out.println(selected);
			        sysCon.setPath(selected);
			        sysPath.setText(selected);
			        sysCon.executeSystem();
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
		//inner.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW));
	}
	
	public void createBoxItem(Composite parent, int index, int num) {
		Composite child = new Composite(parent, SWT.NONE);
		child.setLayout(new FillLayout());
		
		Group group = new Group(child, SWT.NONE);
		GridLayout layout = new GridLayout(4, false);
	    layout.marginWidth = 5;
		layout.horizontalSpacing = 15;
	    group.setLayout(layout);
	    
	    GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		child.setLayoutData(gridData);
	    
	    Label number = new Label(group, SWT.NONE);
		number.setText("  " + num + ". ");
		number.setAlignment(SWT.CENTER);
		
		String name = reqCon.getElement(index);
		String type = reqCon.getType(index);
		int len = 0;
		if (name != null) {
			len = name.length();
			//System.out.println(num + " len: " + len);
		}
		
		Composite reqLabelcomp = new Composite(group, SWT.BORDER);
		FillLayout reqCompLayout = new FillLayout();
		reqCompLayout.marginWidth = 1;
		reqLabelcomp.setLayout(reqCompLayout);
		
		Label reqLabel = new Label(reqLabelcomp, SWT.WRAP | SWT.CENTER);
		
		if (name == null) {
			System.out.println("No name!");
			reqLabel.setText("Requirement element name Description");
		}
		else if (name.equals("")) {
			reqLabel.setText("\nRequirement element name Description");
		}
		else {
			if (len <= 100) {
				reqLabel.setText("\n\n"+name);
			}
			else if (len <= 140) {
				reqLabel.setText("\n"+name);
			}
			else {
				reqLabel.setText(name);
			}
			
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
		
		
		Text text = new Text(group, SWT.MULTI | SWT.V_SCROLL | SWT.BORDER | SWT.WRAP);
		createDecoAndProposal(text);
		
		text.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String writed = text.getText();
				if (writed.endsWith(" ") || writed.endsWith(".")) {
					parserCon.parserInput(writed);
				}
			}
		});
		
		
		GridData gridData_1 = new GridData();
		gridData_1.horizontalAlignment = SWT.FILL;

		GridData gridData_2 = new GridData();
		gridData_2.minimumHeight = 75;
		gridData_2.minimumWidth = 200;
		gridData_2.heightHint = 75;
		gridData_2.widthHint = 250;
		
		GridData gridData_3 = new GridData();
		gridData_3.horizontalAlignment = SWT.FILL;
		
		GridData gridData_4 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData_4.minimumHeight = 75;
		gridData_4.minimumWidth = 350;
		gridData_4.heightHint = 75;
		gridData_4.widthHint = 400;

		number.setLayoutData(gridData_1);
		reqLabelcomp.setLayoutData(gridData_2);
		symbol.setLayoutData(gridData_3);
		text.setLayoutData(gridData_4);
		
	}
	
	
	public void createSaveAndCheckButtons(Composite comp) {
		GridLayout buttonFieldLayout = new GridLayout();
		buttonFieldLayout.numColumns = 1;
		comp.setLayout(buttonFieldLayout);
		
		Composite box = new Composite(comp, SWT.NONE);
		FillLayout boxlayout = new FillLayout();
		boxlayout.marginHeight = 5;
		boxlayout.marginWidth = 5;
		boxlayout.spacing = 15;
		box.setLayout(boxlayout);
		
		Button saveButton = new Button(box, SWT.PUSH);
		saveButton.setText("  Save  ");
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
		

		Button checkButton = new Button(box, SWT.PUSH);
		checkButton.setText("  Check   ");
		checkButton.setAlignment(SWT.CENTER);
		checkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Check called!");
				genCon.checkExtensions();
				reqCon.checkExtensions();
				sysCon.checkExtensions();
				parserCon.checkExtensions();
				propCon.checkExtensions();
			}
		});
	}
	
	public void createGenerateButtons(Composite comp) {
		GridLayout buttonFieldLayout = new GridLayout();
		buttonFieldLayout.numColumns = 1;
		comp.setLayout(buttonFieldLayout);
		
		Composite box = new Composite(comp, SWT.NONE);
		FillLayout boxlayout = new FillLayout();
		boxlayout.marginHeight = 5;
		boxlayout.marginWidth = 5;
		boxlayout.spacing = 15;
		box.setLayout(boxlayout);
		
		Combo comboDropDown = new Combo(box, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
		
		Button genButton = new Button(box, SWT.PUSH);
		genButton.setText(genCon.getGenerateLabels().get(0));
		genButton.setAlignment(SWT.CENTER);
		genButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				genCon.executeSelectedGenerator();
			}
		});
		
	    comboDropDown.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Selected: " + comboDropDown.getText());	
				genButton.setText(genCon.getLabel(comboDropDown.getText()));
				box.layout(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
	    
	    for (String name : genCon.getGenerators()) {
	      comboDropDown.add(" " + name);
	    }
	    comboDropDown.select(0);
	}
	
	
	public void setPositons(Composite maincomp, Composite buttonFieldOne, Composite buttonFieldTwo, Composite top) {
		FormData formData = new FormData();
		formData.top = new FormAttachment(0, 80);
		formData.bottom = new FormAttachment(100, -80);
		formData.left = new FormAttachment(0, 20);
		formData.right = new FormAttachment(100, -20);
		maincomp.setLayoutData(formData);
		
		FormData formDataButtonOne = new FormData();
		formDataButtonOne.top = new FormAttachment(maincomp, 5);
		formDataButtonOne.left = new FormAttachment(0, 20);
		buttonFieldOne.setLayoutData(formDataButtonOne);
		
		FormData formDataButtonTwo = new FormData();
		formDataButtonTwo.top = new FormAttachment(maincomp, 5);
		formDataButtonTwo.right = new FormAttachment(100, -20);
		buttonFieldTwo.setLayoutData(formDataButtonTwo);
		
		FormData formDataTop = new FormData();
		formDataTop.top = new FormAttachment(0, 20);
		formDataTop.bottom = new FormAttachment(maincomp, -10);
		formDataTop.left = new FormAttachment(0, 20);
		formDataTop.right = new FormAttachment(100, -20);
		top.setLayoutData(formDataTop);
	}
	
	public void setColor(Composite comp, int color) {
		comp.setBackground(display.getSystemColor(color));
	}
	
	
	private void updateList() {
		if (compositeInside == null) {
			return;
		}
		for (Control con : compositeInside.getChildren()) {
			con.dispose();
		}
		for (int i = 0; i < reqCon.getElementSize(); i++) {
			createBoxItem(compositeInside, i, i+1);
			compositeInside.pack();
			compositeInside.layout(true);
			updateMinSize(compositeInside.getParent());
		}
	}

	private void createDecoAndProposal(Text text) {
		propCon.createDeco(text);
		propCon.getProposal(text);
	}

}
