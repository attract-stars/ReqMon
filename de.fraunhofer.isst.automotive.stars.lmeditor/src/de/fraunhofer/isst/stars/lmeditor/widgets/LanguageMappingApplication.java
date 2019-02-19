package de.fraunhofer.isst.stars.lmeditor.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.fraunhofer.isst.stars.requirementDSL.Model;
import de.fraunhofer.isst.stars.requirementDSL.Requirement;

import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;


public class LanguageMappingApplication {
	
	static int number = 30;
	
	
	public static void main(String[] args) {
		
		Display display = new Display();
	    Shell shell = new Shell(display);
	    shell.setText("Language Mapping Editor");
	    FormLayout shellLayout = new FormLayout();
	    shellLayout.marginWidth = 100;
	    shellLayout.marginHeight = 50;
	    shell.setLayout(shellLayout);
	    
	    ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
	    scrolledComposite.setExpandVertical(true);
	    scrolledComposite.setExpandHorizontal(true);
	    scrolledComposite.addListener(SWT.Resize, event -> updateMinSize(scrolledComposite));
	    Composite composite = new Composite(scrolledComposite, SWT.NONE);
	    composite.setLayout(new GridLayout(4, false));
	    for (int i = 0; i < 30; i++) createBoxItem(composite, i); 
	    scrolledComposite.setContent(composite);
	    
	    Composite buttonField = new Composite(shell, SWT.NONE);
		GridLayout buttonFieldLayout = new GridLayout();
		buttonFieldLayout.numColumns = 3;
		buttonField.setLayout(buttonFieldLayout);
		createButtons(buttonField);
		
		FormData formData = new FormData();
		formData.top = new FormAttachment(0,0);
		formData.bottom = new FormAttachment(100,0);
		formData.left = new FormAttachment(0,0);
		formData.right = new FormAttachment(100,0);
		scrolledComposite.setLayoutData(formData);
		
		FormData formDataButton = new FormData();
		formDataButton.top = new FormAttachment(scrolledComposite,5);
		formDataButton.right = new FormAttachment(100,0);
		buttonField.setLayoutData(formDataButton);
	    
	    shell.setSize(800, 600);
	    shell.open();
	    
	    while( !shell.isDisposed() ) {
	      if( !display.readAndDispatch() )
	        display.sleep();
	    }
	    display.dispose();
	  }

	  private static void updateMinSize( ScrolledComposite scrolledComposite ) {
	    Rectangle clientArea = scrolledComposite.getClientArea();
	    clientArea.width -= scrolledComposite.getVerticalBar().getSize().x;
	    Point minSize = scrolledComposite.getContent().computeSize( clientArea.width, SWT.DEFAULT );
	    scrolledComposite.setMinSize( minSize );
	  }

	  private static void createBoxItem(Composite parent, int num) {
			Label number = new Label(parent, SWT.BORDER);
			number.setText("" + num);
			
			Label reqLabel = new Label(parent, SWT.BORDER | SWT.WRAP);
			reqLabel.setText("Requirement element name Description");
			
			Label symbol = new Label(parent, SWT.BORDER);
			symbol.setText("<=>");
			
			Text text = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.WRAP);
			text.setText("\n\n");
			
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.FILL;
			gridData.grabExcessHorizontalSpace = true;
			gridData.verticalAlignment = SWT.FILL;
			gridData.grabExcessVerticalSpace = true;
			
			
			text.setLayoutData(gridData);
			reqLabel.setLayoutData(gridData);
			
	  }
	  
	  private static void createButtons(Composite comp) {
			Button saveButton = new Button(comp, SWT.PUSH);
			saveButton.setText("save");
			saveButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					System.out.println("Save called!");
				}
			});
			
			Button checkButton = new Button(comp, SWT.PUSH);
			checkButton.setText("check");
			checkButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					System.out.println("Check called!");
				}
			});
			
			Button generateButton = new Button(comp, SWT.PUSH);
			generateButton.setText("generate");
			generateButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					System.out.println("Generate called!");
				}
			});
		}
	  
	  private static void createRequirementModelElements() {
		  
	  }
	
}
