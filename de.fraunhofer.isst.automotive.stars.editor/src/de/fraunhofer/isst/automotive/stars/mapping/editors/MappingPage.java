package de.fraunhofer.isst.automotive.stars.mapping.editors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class MappingPage {
	
	private Composite composite;
	//private Display display;
	private CreateHelper helper; 
	private RequirementElement reqElem;
	private SystemElement sysElem;
	private Shell shell;
	
	public MappingPage(Composite composite, Display display, Shell shell) {
		this.composite = composite;
		//this.display = display;
		this.reqElem = new RequirementElement();
		this.sysElem = new SystemElement();
		this.helper = new CreateHelper(display, shell, false, reqElem, sysElem);
		this.shell = shell;
	}
	
	public void createMappingPage() {
		Composite maincomp = new Composite(composite, SWT.NONE);
		maincomp.setLayout(new FillLayout());
		Composite inner = new Composite(maincomp, SWT.NONE);
		helper.createBorder(inner);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(inner, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.addListener(SWT.Resize, event -> helper.updateMinSize(scrolledComposite));
		
		Composite compositeInside = new Composite(scrolledComposite, SWT.BORDER);
		FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		fillLayout.marginWidth = 10;
		fillLayout.marginHeight = 10;
		fillLayout.spacing = 10;
		compositeInside.setLayout(fillLayout);
		helper.setListComposite(composite);
		
		reqElem.createSampleElements();
		for (int i = 0; i < reqElem.getElementSize(); i++) {
			helper.createBoxItem(compositeInside, i, i+1);
		}
		
		scrolledComposite.setContent(compositeInside);
		
		Composite top = new Composite(composite, SWT.NONE);
		helper.createTop(top);
		
		Composite buttonField = new Composite(composite, SWT.NONE);
		helper.createButtons(buttonField);
		helper.setPositons(maincomp, buttonField, top);
	}
	
}
