package de.fraunhofer.isst.automotive.stars.editor.editors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;


public class MappingPage {
	
	private Composite composite;
	//private Display display;
	private CreateHelper helper; 
	private RequirementElement reqElem;
	
	public MappingPage(Composite composite, Display display) {
		this.composite = composite;
		//this.display = display;
		this.helper = new CreateHelper(display);
		this.reqElem = new RequirementElement();
	}
	
	public void createMappingPage() {
		ScrolledComposite scrolledComposite = new ScrolledComposite(composite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.addListener(SWT.Resize, event -> updateMinSize(scrolledComposite));
		
		Composite compositeInside = new Composite(scrolledComposite, SWT.BORDER);
		FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		fillLayout.marginWidth = 10;
		fillLayout.marginHeight = 10;
		compositeInside.setLayout(fillLayout);
		
		reqElem.createSampleElements();
		for (int i = 0; i < reqElem.getElementSize(); i++) {
			helper.createBoxItem(compositeInside, reqElem.getElement(i), i+1);
		}
		
		scrolledComposite.setContent(compositeInside);
		
		Button reqButton = new Button(composite, SWT.PUSH);
		reqButton.setText("requirements");
		reqButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Requirements called!");
				//RequirementTable table = new RequirementTable(display);
				//table.createTable();
			}
		});
		
		Button systemButton = new Button(composite, SWT.PUSH);
		systemButton.setText("system");
		systemButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("System called!");
			}
		});

		Composite buttonField = new Composite(composite, SWT.NONE);
		GridLayout buttonFieldLayout = new GridLayout();
		buttonFieldLayout.numColumns = 3;
		buttonField.setLayout(buttonFieldLayout);
		helper.createButtons(buttonField);

		FormData formData = new FormData();
		formData.top = new FormAttachment(0, 50);
		formData.bottom = new FormAttachment(100, -80);
		formData.left = new FormAttachment(0, 20);
		formData.right = new FormAttachment(100, -20);
		scrolledComposite.setLayoutData(formData);
		
		FormData formDataButton = new FormData();
		formDataButton.top = new FormAttachment(scrolledComposite, 5);
		formDataButton.right = new FormAttachment(100, -20);
		buttonField.setLayoutData(formDataButton);
		
		FormData formDataReqButton = new FormData();
		formDataReqButton.bottom = new FormAttachment(scrolledComposite, -10);
		formDataReqButton.left = new FormAttachment(0, 20);
		reqButton.setLayoutData(formDataReqButton);
		
		FormData formDataSysButton = new FormData();
		formDataSysButton.bottom = new FormAttachment(scrolledComposite, -10);
		formDataSysButton.right = new FormAttachment(100, -20);
		systemButton.setLayoutData(formDataSysButton);
	}
	
	private void updateMinSize(ScrolledComposite scrolledComposite) {
		Rectangle clientArea = scrolledComposite.getClientArea();
		clientArea.width -= scrolledComposite.getVerticalBar().getSize().x;
		Point minSize = scrolledComposite.getContent().computeSize(clientArea.width, SWT.DEFAULT);
		scrolledComposite.setMinSize(minSize);
	}
	

}
