package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.app;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class TestApp {
	
	public static void main(final String[] args) {
	    final Display display = new Display();
	    final Shell shell = new Shell(display);
	    shell.setLayout(new FillLayout());

	    final Composite composite = new Composite(shell, SWT.VERTICAL);
	    composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).equalWidth(true).spacing(0, 0).create());

	    final List<Composite> children = new ArrayList<Composite>();
	    for (int i = 0; i < 10; i++) {
	        final Composite c = new Composite(composite, SWT.BORDER);
	        c.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	        c.setLayout(new GridLayout());

	        final Label label = new Label(c, SWT.WRAP);
	        label.setAlignment(SWT.CENTER);
	        label.setText(i < 5 ? "small " + i : "a very big text for the row that is named " + i);
	        label.setToolTipText(label.getText());
	        label.setLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).grab(true, true).create());

	        children.add(c);
	    }

	    composite.addControlListener(new ControlAdapter() {
	        @Override
	        public void controlResized(final ControlEvent e) {
	            int maxHeight = 0;
	            for (final Composite c : children) {
	                if (c.getClientArea().height > maxHeight) {
	                    maxHeight = c.getClientArea().height;
	                }
	            }
	            for (final Composite c : children) {
	                ((GridData) c.getLayoutData()).heightHint = maxHeight;
	            }
	        }
	    });

	    shell.setSize(100, 600);
	    shell.open();

	    while (!shell.isDisposed()) {
	        if (!display.readAndDispatch()) {
	            display.sleep();
	        }
	    }
	}

}
