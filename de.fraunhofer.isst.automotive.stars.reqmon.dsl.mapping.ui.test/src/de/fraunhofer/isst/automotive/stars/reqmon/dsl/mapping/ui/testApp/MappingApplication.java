package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MappingApplication {
	
	private static Display display;
	private static TestAppMappingPage helper; 

	public static void main(String[] args) {

		display = new Display();
		
		Shell shell = new Shell(display);
		shell.setText("Language Mapping Editor");
		FormLayout shellLayout = new FormLayout();
		shell.setLayout(shellLayout);
		
		helper = new TestAppMappingPage(shell, display, shell);
		helper.createMappingPage();

		shell.setSize(1000, 600);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}