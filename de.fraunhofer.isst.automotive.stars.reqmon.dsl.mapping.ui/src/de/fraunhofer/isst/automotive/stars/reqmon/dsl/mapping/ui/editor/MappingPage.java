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
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.MappingParserController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.ProposalController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.RequirementController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.SystemController;

/**
 * This class creates the GUI of the language mapping editor. 
 * 
 * @author sgraf
 *
 */
public class MappingPage {
	
	private Display display;
	private Shell shell;
	private Composite parentComposite;
	private Composite compositeInScroll;
	private Composite maincomp;
	private ScrolledComposite scrolledComposite;
	private RequirementController reqCon;
	private SystemController sysCon;
	private MappingParserController parserCon;
	private ProposalController propCon;
	private GeneratorController genCon;
	
	
	/**
	 * The Constructor creates controllers for all parts of the GUI that can be extended.
	 * @param parentComposite the parent Composite
	 * @param display the parent Display
	 */
	public MappingPage(Composite parentComposite, Display display, Shell shell) {
		this.parentComposite = parentComposite;
		this.display = display;
		this.shell = shell;
		
		this.maincomp = new Composite(parentComposite, SWT.NONE);
		this.scrolledComposite = new ScrolledComposite(maincomp, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		this.compositeInScroll = new Composite(scrolledComposite, SWT.NONE);
		
		this.reqCon = new RequirementController(this);
		this.sysCon = new SystemController();
		this.propCon = new ProposalController();
		this.parserCon = new MappingParserController();
		this.genCon = new GeneratorController();
	}
	
	/**
	 * Creates the GUI for the language mapping editor. The GUI consists of three parts: the top with the file buttons and the file path labels,
	 * the main part for the items that consists of a requirement element on the left side and a mapping field on the right side, 
	 * and the bottom with the save, check and generate buttons.
	 */
	public void createMappingPage() {
		/** set parameters for the parent and the main Composite */
		setColor(parentComposite, SWT.COLOR_WHITE);
		maincomp.setLayout(new FillLayout());
		
		/** create the main part with a scrollable composite */
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.addListener(SWT.Resize, event -> updateMinSize(scrolledComposite));
		
		GridLayout insideLayout = new GridLayout(1, false);
		insideLayout.marginWidth = 10;
		insideLayout.marginHeight = 10;
		insideLayout.horizontalSpacing = 10;
		compositeInScroll.setLayout(insideLayout);
		setColor(compositeInScroll, SWT.COLOR_WIDGET_DARK_SHADOW);
		scrolledComposite.setContent(compositeInScroll);
		
		/** create three example items */
		/*int elemSize = 0;
		for (int i = 0; i < elemSize; i++) {
			createBoxItem(compositeInside, i, i+1);
		}*/
		
		/** create the top */
		Composite top = new Composite(parentComposite, SWT.NONE);
		createTop(top);
		
		/** create the buttons at the bottom */
		Composite buttonFieldOne = new Composite(parentComposite, SWT.NONE);
		createSaveAndCheckButtons(buttonFieldOne);
		Composite buttonFieldTwo = new Composite(parentComposite, SWT.NONE);
		createGenerateButtons(buttonFieldTwo);
		
		/** set the positions of the parts of the GUI */
		setPositons(maincomp, buttonFieldOne, buttonFieldTwo, top);
	}
	
	
	/**
	 * Updates the requirement list of the main GUI part.
	 */
	public void updateList() {
		if (compositeInScroll == null) {
			return;
		}
		for (Control con : compositeInScroll.getChildren()) {
			con.dispose();
		}
		int elemSize = 0;
		
		if (reqCon.getRequirements() != null) {
			elemSize = reqCon.getRequirements().size();
		}
		for (int i = 0; i < elemSize; i++) {
			createBoxItem(compositeInScroll, i, i+1);
			compositeInScroll.pack();
			compositeInScroll.layout(true);
			updateMinSize(compositeInScroll.getParent());
		}
	}
	
	public Composite getParentComposite() {
		return parentComposite;
	}

	public Display getDisplay() {
		return display;
	}

	public Shell getShell() {
		return shell;
	}

	public Composite getCompositeInScroll() {
		return compositeInScroll;
	}
	
	public Composite getMaincomp() {
		return maincomp;
	}

	public ScrolledComposite getScrolledComposite() {
		return scrolledComposite;
	}
	
	
	/**
	 * This method updates the size of the Composite after changing of its inside so that all elements inside of it are still visible. 
	 * @param comp  the Composite
	 */
	protected void updateMinSize(Composite comp) {
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
	 * Sets the relative positions of the GUI elements for the FormLayout of the main Composite.
	 * 
	 * @param maincomp the main Composite
	 * @param buttonFieldOne the bottom Composite on the left (save and check buttons)
	 * @param buttonFieldTwo the bottom Composite on the right (generator combo and generate button)
	 * @param top the top Composite (requirement and system importers)
	 */
	protected void setPositons(Composite maincomp, Composite buttonFieldOne, Composite buttonFieldTwo, Composite top) {
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
	
	/**
	 * Sets the given SystemColor of the Display as Integer to the given Composite.
	 * 
	 * @param comp the Composite
	 * @param color the Color
	 */
	protected void setColor(Composite comp, int color) {
		comp.setBackground(display.getSystemColor(color));
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
		        String[] filterExt = {"*.*"};
		        fd.setFilterExtensions(filterExt);
		        
		        String selected = fd.open();
		        if (selected != null) {
		        	System.out.println(selected);
		        	/** show the selected file path*/
		        	reqPath.setText(selected);
		        	/** execute the RequirementImporter*/
		        	reqCon.execute(display, selected);
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
		        String[] filterExt = {"*.*"};
		        fd.setFilterExtensions(filterExt);
		        
		        String selected = fd.open();
		        if (selected != null) {
		        	System.out.println(selected);
		        	/** show the selected file path */
			        sysPath.setText(selected);
			        /** execute the SystemImporter */
			        sysCon.execute(selected);
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
		String name = reqCon.getRequirements().get(index).getElementName();
		RequirementType type = reqCon.getRequirements().get(index).getElementType();
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
			if(type.equals(RequirementType.OBJECT)) {
				group.setText("Object");
			}
			else if (type.equals(RequirementType.RELATION)) {
				group.setText("Relation");
			}
			else if (type.equals(RequirementType.FUNCTION)) {
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
		
		/** create the appropriate mapping parser */
		parserCon.selectMappingParser("test"); // TODO: get the appropriate language
		
		text.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String writed = text.getText();
				if (writed.endsWith(" ") || writed.endsWith(".")) {
					parserCon.parserInput(writed);
				}
			}
		});
		
		/** set LayoutData for the GridLayout of the box item */
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
		
		/** create the save button */
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
		        String[] filterExt = { "*.txt", "*.doc", ".rtf", "*.*" }; // TODO 
		        fd.setFilterExtensions(filterExt);
		        String selected = fd.open();
		        System.out.println(selected);
			}
		});
		

		/** create the check button */
		Button checkButton = new Button(box, SWT.PUSH);
		checkButton.setText("  Check   ");
		checkButton.setAlignment(SWT.CENTER);
		checkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Check called!"); // TODO
				/** only for test  */
				genCon.checkExtensions();
				reqCon.checkExtensions();
				sysCon.checkExtensions();
				parserCon.checkExtensions();
				propCon.checkExtensions();
			}
		});
	}
	
	/**
	 * Creates a combo box for the selecting of the wished generator and the generate button.
	 * 
	 * @param comp the parent Composite
	 */
	private void createGenerateButtons(Composite comp) {
		GridLayout buttonFieldLayout = new GridLayout();
		buttonFieldLayout.numColumns = 1;
		comp.setLayout(buttonFieldLayout);
		
		Composite box = new Composite(comp, SWT.NONE);
		FillLayout boxlayout = new FillLayout();
		boxlayout.marginHeight = 5;
		boxlayout.marginWidth = 5;
		boxlayout.spacing = 15;
		box.setLayout(boxlayout);
		
		/** create the generator combo and the generator button  */
		Combo comboDropDown = new Combo(box, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
		Button genButton = new Button(box, SWT.PUSH);
		
		/** take the labels of the generators in the generator list for the combo items */
		List<String> genList = genCon.getGenerators();
	    for (String name : genList) {
	      comboDropDown.add(" " + name);
	    }
	    comboDropDown.select(0);
		
		/** take the label of the first generator in the generator list for the generate button */
		genButton.setText(genCon.getGenerateLabels().get(0));
		genButton.setAlignment(SWT.CENTER);
		
		/** execute the generator that is actual selected in the combo */
		genButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				genCon.executeSelectedGenerator();
			}
		});
		
		/** update the selected generator in the combo and the name of the generate button */
	    comboDropDown.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Selected: " + comboDropDown.getText());	
				String genLabel = genCon.getLabel(comboDropDown.getText());
				genButton.setText(genLabel);
				box.layout(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
	    
	}
	
	

	/**
	 * Creates the decoration of the mapping text field and the proposals for the input. 
	 * 
	 * @param text the mapping text field
	 */
	private void createDecoAndProposal(Text text) {
		propCon.selectProposal("test"); // TODO: get the appropriate language
		propCon.createDeco(text);
		propCon.getProposal(text);
	}

	
}
