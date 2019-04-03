package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.app;

import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.editor.MappingPage;

public class MappingApplication {
	
	private static Display display;
	private static MappingPage helper; 

	public static void main(String[] args) {

		display = new Display();
		
		Shell shell = new Shell(display);
		shell.setText("Language Mapping Editor");
		FormLayout shellLayout = new FormLayout();
		shell.setLayout(shellLayout);
		
		helper = new MappingPage(shell, display, shell, true);
		
		Composite maincomp = new Composite(shell, SWT.NONE);
		maincomp.setLayout(new FillLayout());
		Composite inner = new Composite(maincomp, SWT.NONE);
		helper.createBorder(inner);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(inner, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.addListener(SWT.Resize, event -> helper.updateMinSize(scrolledComposite));
		
		Composite composite = new Composite(scrolledComposite, SWT.BORDER);
		/*FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		fillLayout.marginWidth = 10;
		fillLayout.marginHeight = 10;
		fillLayout.spacing = 10;
		composite.setLayout(fillLayout);*/
		
		GridLayout insideLayout = new GridLayout(1, false);
		insideLayout.marginWidth = 10;
		insideLayout.marginHeight = 10;
		insideLayout.horizontalSpacing = 10;
		composite.setLayout(insideLayout);
		
		helper.setListComposite(composite);
		
		for (int i = 0; i < helper.getReqElem().getElementSize(); i++) {
			helper.createBoxItem(composite, i, i+1);
		}
		
		scrolledComposite.setContent(composite);
		
		Composite top = new Composite(shell, SWT.NONE);
		helper.createTop(top);
		
		Composite buttonFieldOne = new Composite(shell, SWT.NONE);
		helper.createSaveAndCheckButtons(buttonFieldOne);
		Composite buttonFieldTwo = new Composite(shell, SWT.NONE);
		helper.createGenerateButtons(buttonFieldTwo);
		//helper.createButtons(buttonField);
		helper.setPositons(maincomp, buttonFieldOne, buttonFieldTwo, top);

		shell.setSize(1000, 600);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
