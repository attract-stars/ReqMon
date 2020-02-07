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

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
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

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.MappingPage;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType;


/**
 * This class extends the MappingPage. It provides similar methods as the MappingPage. 
 * The methods are modified so that they do not call a controller but call the TestApp classes directly
 * because there are no extensions to be managed.
 * 
 * @author sgraf
 *
 */
public class TestAppMappingPage extends MappingPage {
	
	private TestAppSystemImporter syselem;
	private TestAppProposal proposal;
	private TestAppRequirementImporter reqImporter;
	private TestAppMappingParser mapParser;
	private TestAppGenerator generator;

	public TestAppMappingPage(Composite composite, Display display, Shell shell) {
		super(composite, display, shell);
		
		this.syselem = new TestAppSystemImporter();
		this.proposal = new TestAppProposal();
		this.reqImporter = new TestAppRequirementImporter(this);
		this.mapParser = new TestAppMappingParser();
		this.generator = new TestAppGenerator();
	}
	
	@Override
	public void createMappingPage() {
		/** set parameters for the parent and the main Composite */
		setColor(getParentComposite(), SWT.COLOR_WHITE);
		getMaincomp().setLayout(new FillLayout());
		
		/** create the main part with a scrollable composite */
		getScrolledComposite().setExpandVertical(true);
		getScrolledComposite().setExpandHorizontal(true);
		getScrolledComposite().addListener(SWT.Resize, event -> updateMinSize(getScrolledComposite()));
		
		GridLayout insideLayout = new GridLayout(1, false);
		insideLayout.marginWidth = 10;
		insideLayout.marginHeight = 10;
		insideLayout.horizontalSpacing = 10;
		getCompositeInScroll().setLayout(insideLayout);
		setColor(getCompositeInScroll(), SWT.COLOR_WIDGET_DARK_SHADOW);
		getScrolledComposite().setContent(getCompositeInScroll());
		
		/** create three example items */
		int elemSize = reqImporter.getRequirements().size();
		for (int i = 0; i < elemSize; i++) {
			createBoxItem(getCompositeInScroll(), i, i+1);
		}
		
		/** create the top */
		Composite top = new Composite(getParentComposite(), SWT.NONE);
		createTop(top);
		
		/** create the buttons at the bottom */
		Composite buttonFieldOne = new Composite(getParentComposite(), SWT.NONE);
		createSaveAndCheckButtons(buttonFieldOne);
		Composite buttonFieldTwo = new Composite(getParentComposite(), SWT.NONE);
		createGenerateButtons(buttonFieldTwo);
		
		/** set the positions of the parts of the GUI */
		setPositons(getMaincomp(), buttonFieldOne, buttonFieldTwo, top);
	}
	
	@Override
	public void updateList() {
		if (getCompositeInScroll() == null) {
			return;
		}
		for (Control con : getCompositeInScroll().getChildren()) {
			con.dispose();
		}
		int elemSize = 0;
		
		elemSize = reqImporter.getRequirements().size();
		
		for (int i = 0; i < elemSize; i++) {
			createBoxItem(getCompositeInScroll(), i, i+1);
			getCompositeInScroll().pack();
			getCompositeInScroll().layout(true);
			updateMinSize(getCompositeInScroll().getParent());
		}
	}
	
	
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
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
		        fd.setText("Open");
		        fd.setFilterPath("C:/");
		        
		        /** show the files with the correct file extension */
		        String[] filterExt = reqImporter.getFilterExt();
		        fd.setFilterExtensions(filterExt);
		        
		        String selected = fd.open();
		        if (selected != null) {
		        	System.out.println(selected);
		        	/** save the selected file path in a RequirementImporter */
		        	reqImporter.setPath(selected);
		        	/** show the selected file path*/
		        	//reqPath.setText(selected);
		        	/** execute the Parser of the RequirementImporter*/
		        	reqImporter.execute(null, selected);
		        }
			}
		});
		
		
		sysButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("System called!");
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
		        fd.setText("Open");
		        fd.setFilterPath("C:/");
		        
		        /** show the files with the correct file extension */
		        String[] filterExt = syselem.getFilterExt();
		        fd.setFilterExtensions(filterExt);
		        
		        String selected = fd.open();
		        if (selected != null) {
		        	System.out.println(selected);
		        	/** save the selected file path in a SystemElement */
		        	syselem.setPath(selected);
			        /** show the selected file path */
			        sysPath.setText(selected);
			        /** execute the Parser of the SystemElement */
			        syselem.execute();
		        }
			}
		});
		
		
		GridData gridData = new GridData(SWT.FILL,SWT.FILL, true, false);
		reqPath.setLayoutData(gridData);
		sysPath.setLayoutData(gridData);
		
	}
	
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
		String name = reqImporter.getRequirements().get(index).getElementName();
		RequirementType type = reqImporter.getRequirements().get(index).getElementType();
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
		
		text.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String writed = text.getText();
				if (writed.endsWith(" ") || writed.endsWith(".")) {
					mapParser.parserInput(writed);
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
				FileDialog fd = new FileDialog(getShell(), SWT.SAVE);
		        fd.setText("Save");
		        fd.setFilterPath("C:/");
		        String[] filterExt = { "*.txt", "*.doc", ".rtf", "*.*" }; 
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
				System.out.println("Check called!"); 
			}
		});
	}
	
	
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
		List<String> genList = generator.getGenerators();
	    for (String name : genList) {
	      comboDropDown.add(" " + name);
	    }
	    comboDropDown.select(0);
		
		/** take the label of the first generator in the generator list for the generate button */
		genButton.setText(generator.getGenerateLabels().get(0));
		
		genButton.setAlignment(SWT.CENTER);
		
		/** execute the generator that is actual selected in the combo */
		genButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				generator.generate();
			}
		});
		
		/** update the selected generator in the combo and the name of the generate button */
	    comboDropDown.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Selected: " + comboDropDown.getText());	
				String genLabel = generator.getLabel(comboDropDown.getText());
				genButton.setText(genLabel);
				box.layout(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
	    
	}
	
	private void createDecoAndProposal(Text text) {
		proposal.createDeco(text);
		proposal.getProposal(text);
	}

}
