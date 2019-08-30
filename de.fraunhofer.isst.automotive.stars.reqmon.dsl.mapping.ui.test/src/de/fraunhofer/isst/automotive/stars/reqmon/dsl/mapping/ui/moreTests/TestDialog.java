package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.moreTests;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

public class TestDialog extends Dialog {
	
	private ResourceSet resourceSet;
	private ComposedAdapterFactory adapterFactory;

	public TestDialog(Shell parentShell) {
		super(parentShell);
		
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
	
		final Composite body = (Composite)super.createDialogArea(parent);
		//Label label = new Label(body, SWT.NONE);
		//label.setText("Tree");
		
		System.out.println("Create Tree");
		TreeViewer tViewer = new TreeViewer(new Tree(body, SWT.NONE));
	    tViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
	    tViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
	    tViewer.setInput(resourceSet);

	    return body;
	}

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public void setAdapterFactory(ComposedAdapterFactory adapterFactory) {
		this.adapterFactory = adapterFactory;
	}
	
	

}
