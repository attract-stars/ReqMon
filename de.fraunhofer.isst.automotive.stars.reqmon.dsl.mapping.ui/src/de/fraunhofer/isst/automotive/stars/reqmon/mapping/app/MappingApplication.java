package de.fraunhofer.isst.automotive.stars.reqmon.mapping.app;

import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.fraunhofer.isst.automotive.stars.reqmon.mapping.editors.CreateHelper;
import de.fraunhofer.isst.automotive.stars.reqmon.mapping.models.RequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.mapping.models.SystemElement;

public class MappingApplication {
	
	private static Display display;
	private static CreateHelper helper; 

	public static void main(String[] args) {

		display = new Display();
		RequirementElement reqElem = new RequirementElement();
		SystemElement sysElem = new SystemElement();
		
		Shell shell = new Shell(display);
		shell.setText("Language Mapping Editor");
		FormLayout shellLayout = new FormLayout();
		shell.setLayout(shellLayout);
		
		helper = new CreateHelper(display, shell, true, reqElem, sysElem);
		
		Composite maincomp = new Composite(shell, SWT.NONE);
		maincomp.setLayout(new FillLayout());
		Composite inner = new Composite(maincomp, SWT.NONE);
		helper.createBorder(inner);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(inner, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.addListener(SWT.Resize, event -> helper.updateMinSize(scrolledComposite));
		
		Composite composite = new Composite(scrolledComposite, SWT.BORDER);
		FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		fillLayout.marginWidth = 10;
		fillLayout.marginHeight = 10;
		fillLayout.spacing = 10;
		composite.setLayout(fillLayout);
		helper.setListComposite(composite);
		
		reqElem.createSampleElements();
		for (int i = 0; i < reqElem.getElementSize(); i++) {
			helper.createBoxItem(composite, i, i+1);
		}
		
		scrolledComposite.setContent(composite);
		
		Composite top = new Composite(shell, SWT.NONE);
		helper.createTop(top);
		
		Composite buttonField = new Composite(shell, SWT.NONE);
		helper.createButtons(buttonField);
		helper.setPositons(maincomp, buttonField, top);

		shell.setSize(1000, 600);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
