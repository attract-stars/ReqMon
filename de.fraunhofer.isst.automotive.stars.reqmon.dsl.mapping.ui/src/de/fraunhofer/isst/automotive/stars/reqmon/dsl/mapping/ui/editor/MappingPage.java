package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor;

import java.util.List;

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

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.GeneratorController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.ParserController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.ProposalController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.RequirementController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.SystemController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp.TestAppGenerator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp.TestAppMappingParser;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp.TestAppProposal;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp.TestAppRequirementImporter;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp.TestAppSystemImporter;

/**
 * This class creates the GUI of the language mapping editor. 
 * 
 * @author sgraf
 *
 */
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
	
	private boolean isApp;
	private TestAppSystemImporter syselem;
	private TestAppProposal proposal;
	private TestAppRequirementImporter reqImporter;
	private TestAppMappingParser mapParser;
	private TestAppGenerator generator;
	
	/**
	 * The Constructor creates controllers for all parts of the GUI that can be extended.
	 * @param composite the parent Composite
	 * @param display the parent Display
	 * @param isApp true, if it is called from the TestApp
	 */
	public MappingPage(Composite composite, Display display, Shell shell, boolean isApp) {
		this.composite = composite;
		this.display = display;
		this.shell = shell;
		this.isApp = isApp;
		
		if (!isApp) {
			this.reqCon = new RequirementController(this);
			this.sysCon = new SystemController();
			this.propCon = new ProposalController();
			this.parserCon = new ParserController(isApp);
			this.genCon = new GeneratorController();
		}
		/** create Elements for the TestApp */
		else {
			this.syselem = new TestAppSystemImporter();
			this.proposal = new TestAppProposal();
			this.reqImporter = new TestAppRequirementImporter(this);
			this.mapParser = new TestAppMappingParser(isApp);
			this.generator = new TestAppGenerator();
		}
	}
	
	/**
	 * Creates the GUI for the language mapping editor. The GUI consists of three parts: the top with the file buttons and the file path labels,
	 * the main part for the items that consists of a requirement element on the left side and a mapping field on the right side, 
	 * and the bottom with the save, check and generate buttons.
	 */
	public void createMappingPage() {
		/** set parameters for the parent and the main Composite */
		setColor(composite, SWT.COLOR_WHITE);
		Composite maincomp = new Composite(composite, SWT.NONE);
		maincomp.setLayout(new FillLayout());
		
		/** create the main part with a scrollable composite */
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
		scrolledComposite.setContent(compositeInside);
		
		/** create three example items */
		int elemSize = isApp ? reqImporter.getRequirements().size() : 0;
		for (int i = 0; i < elemSize; i++) {
			createBoxItem(compositeInside, i, i+1);
		}
		
		/** create the top */
		Composite top = new Composite(composite, SWT.NONE);
		createTop(top);
		
		/** create the buttons at the bottom */
		Composite buttonFieldOne = new Composite(composite, SWT.NONE);
		createSaveAndCheckButtons(buttonFieldOne);
		Composite buttonFieldTwo = new Composite(composite, SWT.NONE);
		createGenerateButtons(buttonFieldTwo);
		
		/** set the positions of the parts of the GUI */
		setPositons(maincomp, buttonFieldOne, buttonFieldTwo, top);
	}
	
	/**
	 * This method updates the size of the Composite after changing of its inside so that all elements inside of it are still visible. 
	 * @param comp  the Composite
	 */
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
	
	/**
	 * Creates the top elements of the GUI: A button for the requirement importer, a button for the system importer,
	 * a label for the path of the requirement file and a label for the path of the system file.
	 * @param top the Composite at the top of the GUI
	 */
	private void createTop(Composite top) {
		top.setLayout(new FillLayout());
		
		/** a Composite for the requirement button and requirement path and one for the system button and system path  */
		Composite reqComp = new Composite(top, SWT.NONE);
		Composite sysComp = new Composite(top, SWT.NONE);
		GridLayout compLayout = new GridLayout(2, false);
		reqComp.setLayout(compLayout);
		sysComp.setLayout(compLayout);
		
		/** create the path labels as non editable Text */
		Text reqPath = new Text(reqComp, SWT.BORDER);
		Text sysPath = new Text(sysComp, SWT.BORDER);
		reqPath.setText("path of the requirement file ...");
		reqPath.setEditable(false);
		sysPath.setText("path of the system file ...");
		sysPath.setEditable(false);
		
		/** create the buttons */
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
		        
		        /** show the files with the correct file extension */
		        String[] filterExt = isApp ? reqImporter.getFilterExt() : reqCon.getFilterExt();
		        fd.setFilterExtensions(filterExt);
		        
		        String selected = fd.open();
		        if (selected != null) {
		        	System.out.println(selected);
		        	/** save the selected file path in a RequirementImporter */
		        	if (isApp) {
		        		reqImporter.setPath(selected);
		        	}
		        	else {
		        		reqCon.setPath(selected);
		        	}
		        	/** show the selected file path*/
		        	reqPath.setText(selected);
		        	/** execute the Parser of the RequirementImporter*/
		        	if (isApp) {
		        		reqImporter.execute(null);
		        	}
		        	else {
		        		reqCon.execute(display);
		        	}
		        	
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
		        
		        /** show the files with the correct file extension */
		        String[] filterExt = isApp ? syselem.getFilterExt() : sysCon.getFilterExt();
		        fd.setFilterExtensions(filterExt);
		        
		        String selected = fd.open();
		        if (selected != null) {
		        	System.out.println(selected);
		        	/** save the selected file path in a SystemElement */
		        	if (isApp) {
		        		syselem.setPath(selected);
		        	}
		        	else {
		        		sysCon.setPath(selected);
		        	}
			        /** show the selected file path */
			        sysPath.setText(selected);
			        /** execute the Parser of the SystemElement */
			        if (isApp) {
		        		syselem.execute();
		        	}
		        	else {
		        		sysCon.executeSystem();
		        	}
		        }
			}
		});
		
		
		GridData gridData = new GridData(SWT.FILL,SWT.FILL, true, false);
		reqPath.setLayoutData(gridData);
		sysPath.setLayoutData(gridData);
		
	}
	
	/**
	 * Creates an item for the main part. The item consists of four parts: a number, a requirement element of the type 'object', 'relation' or 'function', 
	 * an arrow symbol and a text field for the mapping.
	 * @param parent the parent Composite in the scrollable Composite
	 * @param index the index of the requirement element list
	 * @param num the number of the item
	 */
	private void createBoxItem(Composite parent, int index, int num) {
		Composite child = new Composite(parent, SWT.NONE);
		child.setLayout(new FillLayout());
		
		/** create a Group for the item */
		Group group = new Group(child, SWT.NONE);
		GridLayout layout = new GridLayout(4, false);
	    layout.marginWidth = 5;
		layout.horizontalSpacing = 15;
	    group.setLayout(layout);
	    
	    GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		child.setLayoutData(gridData);
	    
		/** create the number label */
	    Label number = new Label(group, SWT.NONE);
		number.setText("  " + num + ". ");
		number.setAlignment(SWT.CENTER);
		
		/** create the requirement element label */
		String name = isApp ? reqImporter.getRequirements().get(index).getElementName() : reqCon.getRequirements().get(index).getElementName();
		String type = isApp? reqImporter.getRequirements().get(index).getElementType() : reqCon.getRequirements().get(index).getElementType();
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
		
		/** set the requirement element name */
		if (name == null) {
			System.out.println("No name!");
			reqLabel.setText("Requirement element name Description");
		}
		else if (name.equals("")) {
			reqLabel.setText("\nRequirement element name Description");
		}
		else {
			/** positioning of the element name in the label */
			if (len <= 100) {
				reqLabel.setText("\n\n"+name);
			}
			else if (len <= 140) {
				reqLabel.setText("\n"+name);
			}
			else {
				reqLabel.setText(name);
			}
			/** set the type */
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

		/** set the symbol */
		Label symbol = new Label(group, SWT.NONE);
		symbol.setText("  <=>  \t");
		symbol.setAlignment(SWT.CENTER);
		
		/** create the Text for the mapping, the info decoration and the proposal */
		Text text = new Text(group, SWT.MULTI | SWT.V_SCROLL | SWT.BORDER | SWT.WRAP);
		createDecoAndProposal(text);
		
		text.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String writed = text.getText();
				if (writed.endsWith(" ") || writed.endsWith(".")) {
					if (isApp) {
						mapParser.parserInput(writed);
					}
					else {
						parserCon.parserInput(writed);
					}
				}
			}
		});
		
		/** Set LayoutData for the GridLayout of the box item */
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
	
	/**
	 * Creates the save and the check buttons at the bottom of the GUI.
	 * @param comp the parent Composite
	 */
	private void createSaveAndCheckButtons(Composite comp) {
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
				if (!isApp) {
					genCon.checkExtensions();
					reqCon.checkExtensions();
					sysCon.checkExtensions();
					parserCon.checkExtensions();
					propCon.checkExtensions();
				}
				
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
		if (isApp) {
			genButton.setText(generator.getGenerateLabels().get(0));
		}
		else {
			genButton.setText(genCon.getGenerateLabels().get(0));
		}
		
		genButton.setAlignment(SWT.CENTER);
		genButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (isApp) {
					generator.executeSelectedGenerator();
				}
				else {
					genCon.executeSelectedGenerator();
				}
			}
		});
		
	    comboDropDown.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Selected: " + comboDropDown.getText());	
				String genLabel = isApp ? generator.getLabel(comboDropDown.getText()) : genCon.getLabel(comboDropDown.getText());
				genButton.setText(genLabel);
				box.layout(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
	    
	    List<String> genList = isApp ? generator.getGenerators() : genCon.getGenerators();
	    for (String name : genList) {
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
	
	
	/**
	 * Updates the element list of the main GUI part
	 */
	public void updateList() {
		System.out.println("In updateList, Requirements: " + (reqCon.getRequirements() != null));
		if (compositeInside == null) {
			return;
		}
		for (Control con : compositeInside.getChildren()) {
			con.dispose();
		}
		int elemSize = 0;
		if (isApp) {
			elemSize = reqImporter.getRequirements().size();
		}
		else if (reqCon.getRequirements() != null) {
			elemSize = reqCon.getRequirements().size();
		}
		for (int i = 0; i < elemSize; i++) {
			createBoxItem(compositeInside, i, i+1);
			compositeInside.pack();
			compositeInside.layout(true);
			updateMinSize(compositeInside.getParent());
		}
	}

	private void createDecoAndProposal(Text text) {
		if (isApp) {
			proposal.createDeco(text);
			proposal.getProposal(text);
		}
		else {
			propCon.createDeco(text);
			propCon.getProposal(text);
		}
	}

	
}
