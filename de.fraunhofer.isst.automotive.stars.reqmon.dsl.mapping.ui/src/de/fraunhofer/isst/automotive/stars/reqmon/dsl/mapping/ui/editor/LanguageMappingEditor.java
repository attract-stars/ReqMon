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
	
	private boolean dirty = false;
	private String editorName;
	private MappingPage mapping;

	@Override
	public void doSave(IProgressMonitor monitor) {
		mapping.save();
	}

	@Override
	public void doSaveAs() {
		mapping.saveAs();
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
	public boolean isDirty() {
		return dirty;
	}
	
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
		firePropertyChange(PROP_DIRTY);
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

	

}
