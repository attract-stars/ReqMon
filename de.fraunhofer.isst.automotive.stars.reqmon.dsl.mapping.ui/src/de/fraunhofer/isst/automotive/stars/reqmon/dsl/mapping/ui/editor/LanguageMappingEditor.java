package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

/**
 * This class extends the EditorPart. It creates the MappingPage for the language mapping editor.
 * @author sgraf
 */
public class LanguageMappingEditor extends EditorPart {
	
	private String editorName;
	private MappingPage mapping;

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
	}

	/**
	 * Sets the site, the input and sets the name of the file as part name. 
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
		editorName = input.getName();
		System.out.println(editorName);
		setPartName(editorName);
	}

	
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	/**
	 * Creates a MappingPage.
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		Display display = parent.getDisplay();
		Shell shell = parent.getShell();
		FormLayout layout = new FormLayout();
		composite.setLayout(layout);
		
		mapping = new MappingPage(composite, display, shell, this);
		mapping.createMappingPage();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

}
