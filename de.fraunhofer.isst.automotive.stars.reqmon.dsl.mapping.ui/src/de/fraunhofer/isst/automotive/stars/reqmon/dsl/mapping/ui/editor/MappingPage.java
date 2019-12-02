package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.source.LineNumberRulerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditor;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorModelAccess;

import com.google.inject.Injector;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.ValidateListener;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.GeneratorController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.MappingParserController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.ProposalController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.RequirementController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.SerializationController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.SystemController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.model.SaveModel;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.model.ValidateEvent;

/**
 * This class creates the GUI of the language mapping editor. 
 * It uses the embedded xtext editor, that is restricted. 
 * 
 * @author sgraf
 *
 */
@SuppressWarnings("restriction")
public class MappingPage implements Observer, ValidateListener {
	
	// SWT components
	private Display display;
	private Shell shell;
	private Composite parentComposite;
	private Composite compositeInScroll;
	private Composite maincomp;
	private ScrolledComposite scrolledComposite;
	private Text sysPath;
	
	
	// Controllers
	private RequirementController reqCon;
	private SystemController sysCon;
	private MappingParserController parserCon;
	private ProposalController propCon;
	private GeneratorController genCon;
	
	/**
	 * Helps to create the embedded xtext editor.
	 */
	private MappingEditorHelper editorHelper;
	
	private List<Resource> resourceList;
		
	/**
	 * Injector for the mapping parser.
	 */
	private Injector dslInjector;
	/**
	 * If it is true, then requirement elements exists for displaying
	 */
	private boolean isReqElems;
	/** 
	 * If it is true, then the requirements will be updated with the saved requirements.
	 */ 
	private boolean isModelLoading;
	/**
	 * The system model path. The model is needed to complete the DSL of the mapping parser.
	 */
	private String systemPath;
	
	// for serialization
		private String editorName;
		private SaveModel savedModel;
		private List<IRequirementElement> savedReqList;
		private String savedSystemPath;
		private String savedReqPath;
		private List<String> savedEditorContent;
		private List<EmbeddedEditorModelAccess> modelAccessList;
		// for access of the save and dirty method and part name 
		private LanguageMappingEditor langMapEditor;
	
	
	
	/**
	 * The Constructor creates controllers for all parts of the GUI that can be extended.
	 * @param parentComposite the parent Composite
	 * @param display the parent Display
	 * @param editorName the name of the mapping language editor file
	 */
	public MappingPage(Composite parentComposite, Display display, Shell shell, LanguageMappingEditor langMapEditor) { 
		this.parentComposite = parentComposite;
		this.display = display;
		this.shell = shell;
		this.langMapEditor = langMapEditor;
		this.editorName = langMapEditor.getPartName(); 
		
		setup();
	}
	
	/**
	 * The Constructor creates controllers for all parts of the GUI that can be extended.
	 * @param parentComposite the parent Composite
	 * @param display the parent Display
	 */
	public MappingPage(Composite parentComposite, Display display, Shell shell) {
		this.parentComposite = parentComposite;
		this.display = display;
		this.shell = shell;
		
		setup();
	}
	
	/**
	 * Configures all relevant dependencies and load the saved model or create a new one for saving. 
	 */
	protected void setup() {
		// create the important GUI components 
		this.maincomp = new Composite(parentComposite, SWT.NONE);
		this.scrolledComposite = new ScrolledComposite(maincomp, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		this.compositeInScroll = new Composite(scrolledComposite, SWT.NONE);
		
		// create the controllers 
		this.reqCon = new RequirementController(display);
		this.sysCon = new SystemController();
		this.propCon = new ProposalController();
		this.parserCon = new MappingParserController();
		this.genCon = new GeneratorController();
		
		// add this to observable controllers
		observe(parserCon);
		observe(reqCon);
		// use the listener pattern
		sysCon.addValidateListener(this);
		
		// set the value for isReqElements 
		this.isReqElems = false;
		
		// create the helper for the embedded xtext editor
		this.editorHelper = new MappingEditorHelper();
		
		// create resource list and model for the generators
		this.resourceList = new ArrayList<Resource>();
		
		// set the editor name for serialization and deserialization 
		SerializationController.getInstance().setFilename(editorName);
				
		// load the SaveModel 
		this.savedModel = SerializationController.getInstance().load();
				
		// create a new SaveModel if the model is null 
		if (this.savedModel == null) {
			System.out.println("Save new model of the file " + editorName);
			this.savedModel = new SaveModel();
		}
		// otherwise load the saved data
		else {
			setupSavedData();
		}
	
		// select the appropriate mapping parser 
		String lang = "map";  // TODO: get the appropriate language
		//System.out.println("Select the mapping parser for lang = " + lang);
		parserCon.selectMappingParser(lang);
	}
	
	/**
	 * Creates the GUI for the language mapping editor. The GUI consists of three parts: the top with the file buttons and the file path labels,
	 * the main part for the items that consists of a requirement element on the left side and a mapping field on the right side, 
	 * and the bottom with the save, check and generate buttons.
	 */
	public void createMappingPage() {
		// set parameters for the parent and the main Composite 
		setColor(parentComposite, SWT.COLOR_WHITE);
		maincomp.setLayout(new FillLayout());
		
		// create the main part with a scrollable composite 
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
		
		// create three example items 
		/*int elemSize = 0;
		for (int i = 0; i < elemSize; i++) {
			createBoxItem(compositeInside, i, i+1);
		}*/
		
		// create the top 
		Composite top = new Composite(parentComposite, SWT.NONE);
		createTop(top);
		
		// create the buttons at the bottom 
		Composite buttonFieldOne = new Composite(parentComposite, SWT.NONE);
		createSaveAndCheckButtons(buttonFieldOne);
		Composite buttonFieldTwo = new Composite(parentComposite, SWT.NONE);
		createGenerateButtons(buttonFieldTwo);
		
		// set the positions of the parts of the GUI 
		setPositons(maincomp, buttonFieldOne, buttonFieldTwo, top);
	}
	
	
	/**
	 * Updates the requirement list of the main GUI part.
	 */
	public void updateList() {
		
		if (compositeInScroll == null) {
			return;
		}
		
		// delete old elements 
		for (Control con : compositeInScroll.getChildren()) {
			con.dispose();
		}
		
		int elemSize = 0;
		List<? extends IRequirementElement> reqList = null;
		
		// take the requirement elements which are new selected by the user
		if (reqCon.getRequirements() != null) {
			reqList = reqCon.getRequirements();
			elemSize = reqList.size();
			
			// add requirements to the SaveModel for serialization 
			savedModel.setReqList(reqCon.getRequirements());
		}
		/* take the saved requirement elements, while the mapping editor is loading
		 * or take the saved elements if no new ones have been selected by the user 
		 * and the mapping editor is already loaded */
		else if (isModelLoading && savedReqList != null || isReqElems && reqCon.getRequirements() == null) {
			reqList = savedReqList;
			elemSize = savedReqList.size();
		}
		else {
			System.out.println("No requirements!");
			return;
		}
		
		this.modelAccessList = new ArrayList<EmbeddedEditorModelAccess>();
		
		// sort reqlist: objects, functions, relations
		if (reqList.isEmpty()) {
			// the model is ready loaded
			isModelLoading = false;
			return;
		}
		reqList.sort(reqList.get(0).getElementTypeComparator());
		
		for (int i = 0; i < elemSize; i++) {
			EmbeddedEditorModelAccess modelAccess = null;
			
			/* create the box item with the saved requirement element and the saved editor content,
			 * if the mapping editor is loading, 
			 * saved requirements and saved editor contents exist 
			 * and both lists have the same length */
			if (isModelLoading && savedEditorContent != null && savedReqList != null &&
					savedReqList.size() == savedEditorContent.size()) {
				modelAccess = createBoxItem(reqList.get(i), i+1, savedEditorContent.get(i));
			}
			/* create the box item with the saved requirement element but without editor content,
			 * if the mapping editor is loading and saved requirements exist */
			else if (isModelLoading && savedEditorContent == null && !savedReqList.isEmpty()) {
				modelAccess = createBoxItem(reqList.get(i), i+1, null);
			}
			/* create the box item with the new or saved requirement element without editor content,
			 * if the mapping editor is already loaded 
			 * or no saved requirements and / or no saved editor contents exist
			 * or both list do not have the same length */
			else {
				modelAccess = createBoxItem(reqList.get(i), i+1, null);
				// changes for saving
				langMapEditor.setDirty(true);
			}
			
			// add the model access of the new created embedded editor to a new model access list
			modelAccessList.add(modelAccess);
			
			compositeInScroll.pack();
			compositeInScroll.layout(true);
			updateMinSize(compositeInScroll.getParent());
		}
		
		// now the requirement elements should be visible in the mapping editor
		isReqElems = true;
		// the model is ready loaded
		isModelLoading = false;
	}
	
	/**
	 * Creates the injector for the mapping language, if the system model is valid. 
	 * The requirement list will be updated if requirements exist. 
	 * The system model path will be shown if the system model is valid. 
	 * @param isValidSystemModel the injector is created if this value is true, otherwise not
	 */
	public void createDslInjectorAndUpdateList() {
		// look first if a new system model file path is given
		if (systemPath != null) {
			parserCon.createDslInjector(URI.createFileURI(systemPath), display);
		}
		// otherwise take the saved path, if it exist
		else if (savedSystemPath != null) {
			parserCon.createDslInjector(URI.createFileURI(savedSystemPath), display);
			// set modelLoading to false after injector creation if no requirements are saved
			if (savedReqList == null || savedReqList.isEmpty()) {
				isModelLoading = false;
			}
		}
	}
	
	
	public void setDslInjector(Injector injector) {
		this.dslInjector = injector;
	}
	
	public boolean isReqElements() {
		return this.isReqElems;
	}
	
	public Display getDisplay() {
		return display;
	}
	
	/**
	 * Saves all contents of the model: the file paths (requirement and system model) 
	 * and the mapping object list (requirement elements and content of the embedded editors).
	 * Sets the dirty value to false.
	 */
	public void save() {
		// add all contents of the embedded editor model access list to the SaveModel for serialization
		savedEditorContent = new ArrayList<String>();
		//System.out.println("Model access list: " + modelAccessList.size());
		
		if (modelAccessList != null) {
			for (EmbeddedEditorModelAccess modelAccess : modelAccessList) {
				if (modelAccess != null) {
					//System.out.println("Save with content");
					String content = modelAccess.getEditablePart();
					//System.out.println("Mapping content: " + content);
					savedEditorContent.add(content);
				}
				// if there is no content save only the requirement elements and the paths
				else {
					//System.out.println("Save without content");
					savedEditorContent.add(null);
				}
			}
			savedModel.setEditorContentList(savedEditorContent);
		}
		
		// print warning if no requirements are selected
		if (!this.isReqElems) {
			MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES | SWT.NO | SWT.CANCEL);
	        
	        messageBox.setText("Warning");
	        messageBox.setMessage("There are no requirements selected! Save anyway?");
	        int buttonID = messageBox.open();
	        switch(buttonID) {
	          case SWT.YES:
	        	// start the serialization
	  			SerializationController.getInstance().save(savedModel);
	  			langMapEditor.setDirty(false);
	          case SWT.NO:
	            break;
	          case SWT.CANCEL:
	        }
	        System.out.println(buttonID);
		}
		else {
			// start the serialization
			SerializationController.getInstance().save(savedModel);
			langMapEditor.setDirty(false);
		}
		
	}
	
	/**
	 * Save the model as the selected filename. 
	 */
	public void saveAs() {
		FileDialog fd = new FileDialog(shell, SWT.SAVE);
        fd.setText("Save");
        fd.setFilterPath("C:/");
        String[] filterExt = { "*.txt", "*.dslm", ".ser", "*.*" }; // only .ser is used in the background
        fd.setFilterExtensions(filterExt);
        String selected = fd.open();
        System.out.println(selected);
        if (selected != null) {
        	SerializationController.getInstance().setFilename(selected);
        	save(); // TODO: How shall "save as" work?
        }
        
        
        // change name of the mapping editor
       /* String[] nameParts = selected.split(Pattern.quote("."));
		String name = "";
        if (nameParts.length != 0) {
			name = nameParts[0] + ".dslm";
		}
        langMapEditor.setMappingEditorName(name);*/
        
        
	}
	
	
	
	protected Composite getParentComposite() {
		return parentComposite;
	}

	protected Shell getShell() {
		return shell;
	}

	protected Composite getCompositeInScroll() {
		return compositeInScroll;
	}
	
	protected Composite getMaincomp() {
		return maincomp;
	}

	protected ScrolledComposite getScrolledComposite() {
		return scrolledComposite;
	}
	
	
	
	/**
	 * This method updates the size of the Composite after changing of its inside so that all elements inside of it are still visible. 
	 * @param comp  the Composite
	 */
	protected void updateMinSize(Composite comp) {
		Rectangle clientArea = comp.getClientArea();
		
		if (comp == null || ((ScrolledComposite)comp).getContent() == null) {
			return;
		}
		
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
		
		// a Composite for the requirement button and requirement path and one for the system button and system path  
		Composite reqComp = new Composite(top, SWT.NONE);
		Composite sysComp = new Composite(top, SWT.NONE);
		GridLayout compLayout = new GridLayout(2, false);
		reqComp.setLayout(compLayout);
		sysComp.setLayout(compLayout);
		
		// create the path labels as non editable Text 
		Text reqPath = new Text(reqComp, SWT.BORDER);
		sysPath = new Text(sysComp, SWT.BORDER);
		reqPath.setEditable(false);
		sysPath.setEditable(false);
		
		// load the saved paths if they are not empty otherwise set default text
		if (savedReqPath != null && !savedReqPath.equals("")) {
			reqPath.setText(savedReqPath);
		}
		else {
			reqPath.setText("path of the requirement file ...");
		}
		if (savedSystemPath != null && !savedSystemPath.equals("")) {
			sysPath.setText(savedSystemPath);
		}
		else {
			sysPath.setText("path of the system file ...");
		}
		
		// create the buttons 
		Button reqButton = new Button(reqComp, SWT.PUSH);
		Button sysButton = new Button(sysComp, SWT.PUSH);
		reqButton.setText(" Requirement ");
		sysButton.setText(" System ");
		
		// listen to the requirement button selection
		reqButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Requirements called!");
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
		        fd.setText("Open");
		        
		     // remember the path (after serialization and new start of the application)
		        // if the path for the requirement file is not given, look after the path of the system model file
		        String filterpath = "C:/";
		        if (savedModel.getReqPath() != null && !savedModel.getReqPath().equals("")) {
		        	filterpath = savedModel.getReqPath();
		        }
		        else if (savedModel.getSysPath() != null && !savedModel.getSysPath().equals("")) {
		        	filterpath = savedModel.getSysPath();
		        }
		        if (!filterpath.equals("C:/")) {
		        	String[] pathSegments = filterpath.split(Pattern.quote("\\"));
		        	filterpath = "";
		        	for(int i = 0; i < pathSegments.length - 1; i++) {
			        	filterpath += pathSegments[i] + "\\";
			        }
			        System.out.println("Filterpath: " + filterpath);
		        }
		        fd.setFilterPath(filterpath);
		        
		        // TODO: ? show the files with the correct file extension ?
		        String[] filterExt = {"*.*"};
		        fd.setFilterExtensions(filterExt);
		        
		        String selected = fd.open();
		        if (selected != null) {
		        	System.out.println(selected);
		        	// show the selected file path
		        	reqPath.setText(selected);
		        	// execute the RequirementImporter 
		        	reqCon.execute(display, selected);
		        	// add the selected path to the SaveModel for the serialization 
		        	savedModel.setReqPath(selected);
		        	langMapEditor.setDirty(true);
		        }
			}
		});
		
		// listen to the system button selection
		sysButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("System called!");
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
		        fd.setText("Open");
		        
		        // remember the path (after serialization and new start of the application)
		        // if the path for the system model file is not given, look after the path of the requirement file
		        String filterpath = "C:/";
		        if (savedModel.getSysPath() != null && !savedModel.getSysPath().equals("")) {
		        	filterpath = savedModel.getSysPath();
		        }
		        else if (savedModel.getReqPath() != null && !savedModel.getReqPath().equals("")) {
		        	filterpath = savedModel.getReqPath();
		        }
		        if (!filterpath.equals("C:/")) {
		        	String[] pathSegments = filterpath.split(Pattern.quote("\\"));
		        	filterpath = "";
		        	for(int i = 0; i < pathSegments.length - 1; i++) {
			        	filterpath += pathSegments[i] + "\\";
			        }
			        System.out.println("Filterpath: " + filterpath);
		        }
		        fd.setFilterPath(filterpath);
		        
		        // TODO: ? show the files with the correct file extension ? 
		        String[] filterExt = {"*.*"};
		        fd.setFilterExtensions(filterExt);
		        
		        systemPath = fd.open();
		        if (systemPath != null) {
		        	System.out.println(systemPath);
			        /* validate the selected file.
			         * If the validation is correct 
			         * the injector for the mapping parser is created,
			         * the selected file path is shown
					 * and the item list is updated */
			        sysCon.checkFile(systemPath, display);
		        }
			}
		});
		
		GridData gridData = new GridData(SWT.FILL,SWT.FILL, true, false);
		reqPath.setLayoutData(gridData);
		sysPath.setLayoutData(gridData);
		
	}
	
	/**
	 * Creates an item for the main part. The item consists of four parts: a number, a requirement element 
	 * of the type 'object', 'relation' or 'function', 
	 * an arrow symbol and a text field for the mapping.
	 * @param reqElem the requirement Element of type IRequirementElement
	 * @param num the number of the item
	 * @param savedContent the saved embedded editor content
	 * @return the EmebeddedEditorModelAccess for serialization and deserialization of the embedded editor content
	 */
	private EmbeddedEditorModelAccess createBoxItem(IRequirementElement reqElem, int num, String savedContent) {
		Composite child = new Composite(compositeInScroll, SWT.NONE);
		child.setLayout(new FillLayout());
		
		// create a Group for the item 
		Group group = new Group(child, SWT.NONE);
		GridLayout layout = new GridLayout(4, false);
		layout.marginWidth = 5;
		layout.horizontalSpacing = 15;
	    group.setLayout(layout);
	    
	    GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		child.setLayoutData(gridData);
	    
		// create the number label 
	    Label number = new Label(group, SWT.NONE);
		number.setText("  " + num + ". ");
		number.setAlignment(SWT.CENTER);
		
		// create the requirement element label 
		String name = "";
		RequirementType type = null;
		
		name = reqElem.getElementName();
		type = reqElem.getElementType();
		 
		// get the length of the element name for the name position calculation
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
		
		// set the requirement element name 
		if (name == null) {
			System.out.println("No name!");
			reqLabel.setText("Requirement element name Description");
		}
		else if (name.equals("")) {
			reqLabel.setText("\nRequirement element name Description");
		}
		else {
			// positioning of the element name in the label 
			if (len <= 100) {
				reqLabel.setText("\n\n"+name);
			}
			else if (len <= 140) {
				reqLabel.setText("\n"+name);
			}
			else {
				reqLabel.setText(name);
			}
			// set the type 
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
		
		// set the symbol 
		Label symbol = new Label(group, SWT.NONE);
		symbol.setText("  <=>  \t");
		symbol.setAlignment(SWT.CENTER);
		
		
		// create an embedded xtextEditor for the mapping 
		Composite embeddedEditorComposite = new Composite(group, SWT.BORDER);
		embeddedEditorComposite.setLayout(new GridLayout());
		GridData gridDataText = new GridData(SWT.FILL, SWT.FILL, true, true);
		embeddedEditorComposite.setLayoutData(gridDataText);
		
		EmbeddedEditor embed = editorHelper.createEditorWithoutActivation(embeddedEditorComposite, dslInjector);
		EmbeddedEditorModelAccess modelAccess= null;
		
		if (embed != null) {
			// Important method that activates the nice behavior of a xtext editor 
			modelAccess = embed.createPartialEditor();
			
			LineNumberRulerColumn lineNumberRulerColumn = new LineNumberRulerColumn();
			embed.getViewer().addVerticalRulerColumn(lineNumberRulerColumn);
			
			resourceList.add(editorHelper.getResource());
			savedModel.setMappingResourceList(resourceList);

			// Listen to text changes to set the dirty status
			embed.getViewer().addTextListener(new ITextListener() {
							
				@Override
				public void textChanged(TextEvent event) {
					if (isModelLoading) {
						// set the text selection to 0 after the saved text is inserted
						embed.getViewer().setSelectedRange(0, 0);
						return;
					}
					String text = embed.getDocument().get();
					if (savedEditorContent == null && !text.equals("")) {
						langMapEditor.setDirty(true);
					}
					else if (savedEditorContent != null && !savedEditorContent.get(num-1).equals(text)) {
						langMapEditor.setDirty(true);
					}
					
				}
			});
				
			// if there exists a saved embedded editor content write it back to the embedded editor 
			if (savedContent != null) {
				modelAccess.updateModel(savedContent);
			}

		}
		
		
		// set LayoutData for the GridLayout of the box item 
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
		embeddedEditorComposite.setLayoutData(gridData_4);
		
		return modelAccess;
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
		
		// create the save button 
		Button saveButton = new Button(box, SWT.PUSH);
		saveButton.setText("  Save  ");
		saveButton.setAlignment(SWT.CENTER);
		
		/* save all data (requirement path and system model path, requirement list and embedded editor content list) 
		 * when the save button is selected */
		saveButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Save called!");
				save();
				//saveAs();
			}
		});
		

		// create the check button 
		Button checkButton = new Button(box, SWT.PUSH);
		checkButton.setText("  Check   ");
		checkButton.setAlignment(SWT.CENTER);
		checkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Check called!"); // TODO
				// only for test  
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
		
		// create the generator combo and the generator button  
		Combo comboDropDown = new Combo(box, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
		Button genButton = new Button(box, SWT.PUSH);
		
		// take the labels of the generators in the generator list for the combo items and
		// take the label for the generate button 
		List<String> genList = genCon.getGenerators();
	    for (String name : genList) {
	      comboDropDown.add(" " + name);
	    }
	    
	    if (isModelLoading && savedModel.getGenLabel() != null) {
	    	comboDropDown.select(savedModel.getGeneratorIndex());
		    genCon.setIndex(savedModel.getGeneratorIndex());
	    	genButton.setText(savedModel.getGenLabel());
	    }
	    else {
	    	comboDropDown.select(0);
	    	savedModel.setGeneratorIndex(0);
	    	
			String firstGenLabel = genCon.getGenerateLabels().get(0);
		    genButton.setText(firstGenLabel);
			savedModel.setGenLabel(firstGenLabel);
	    }
	    
		genButton.setAlignment(SWT.CENTER);
		
		// execute the generator that is actual selected in the combo 
		genButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// add all available informations to the mapping model
				if (sysCon.getSysModel() != null) {
					savedModel.setSystemModel(sysCon.getSysModel());
				}
				if (reqCon.getRequirements() != null) {
					savedModel.setRequirementList(reqCon.getRequirements());
				}
				else if (savedReqList != null) {
					savedModel.setRequirementList(savedReqList);
				}
				
				// execute the selected generator
				genCon.executeSelectedGenerator(savedModel, editorName);
			}
		});
		
		// update the selected generator in the combo and the name of the generate button during loading
		if (isModelLoading) {
			genButton.setText(savedModel.getGenLabel());
			comboDropDown.select(savedModel.getGeneratorIndex());
		}
		
		// update the selected generator in the combo and the name of the generate button 
	    comboDropDown.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Selected: " + comboDropDown.getText().substring(1));	
				String genLabel = genCon.getLabel(comboDropDown.getText().substring(1));
				genButton.setText(genLabel);
				savedModel.setGenLabel(genLabel);
				savedModel.setGeneratorIndex(genCon.getIndex());
				langMapEditor.setDirty(true);
				box.layout(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
	    
	}
	
	/**
	 * Loads all data of the saved model, 
	 * creates the injector for the mapping parser based on the saved system model path,
	 * updates the item list
	 */
	private void setupSavedData() {
		this.savedReqPath = this.savedModel.getReqPath();
		this.savedSystemPath = this.savedModel.getSysPath();
		this.savedReqList = this.savedModel.getReqList();
		this.savedEditorContent = this.savedModel.getEditorContentList();
		this.savedEditorContent = this.savedModel.getEditorContentList();
		this.isModelLoading = true;
		
		if (savedReqList == null || savedReqList.isEmpty()) {
			isReqElems = false;
		}
		else {
			isReqElems = true;
		}
		
		if (savedSystemPath != null && !savedSystemPath.equals("")) {
			sysCon.checkFile(savedSystemPath, display);
		}
		else {
			updateList();
		}
	}
	
	public void observe(Observable obs) {
		obs.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof MappingParserController) {
			setDslInjector(parserCon.getDslInjector());
			if (dslInjector == null) {
				return;
			}
			//System.out.println("DslInjector: " + dslInjector.getClass().getName());
			if (isReqElems) {
				updateList();
			}
		}
		else if (o instanceof RequirementController) {
			updateList();
		}
	}
	
	@Override
	public void advertisment(ValidateEvent e) {
		if (e.isValid()) {
			if (!isModelLoading) {
				/// show the selected file path 
		        sysPath.setText(systemPath);
		        // add the selected path to the SaveModel for the serialization 
	        	savedModel.setSysPath(systemPath);
	        	langMapEditor.setDirty(true);
			}
			createDslInjectorAndUpdateList();
		}
	}
	

	
}
