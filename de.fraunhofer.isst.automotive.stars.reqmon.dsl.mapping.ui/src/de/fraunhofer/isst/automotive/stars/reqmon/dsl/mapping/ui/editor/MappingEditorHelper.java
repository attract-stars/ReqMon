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

import com.google.inject.Injector;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.text.source.LineNumberRulerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditor;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorModelAccess;
import org.eclipse.xtext.ui.editor.embedded.IEditedResourceProvider;

/**
 * This class helps the GUI to create an embedded xtext editor. 
 * Important to know: Some methods used from xtext are restricted, so changes are possible!
 * 
 * @author sgraf
 *
 */
@SuppressWarnings("restriction")
public class MappingEditorHelper {
	
	private EmbeddedEditor editor;
	private CostumResourceProvider provider;
	private int index = 0;
	
	
	/**
	 * Creates an embedded xtext editor and activates it. Importent to know: Some methods used from xtext are restricted, so changes are possible!
	 * @param parent the parent Composite
	 * @param dslInjector the mapping language
	 * @return an EmbeddedEditorModelAccess to obtain model access (for serialization and deserialization of the editor content)
	 */
	public EmbeddedEditorModelAccess createEditor(Composite parent, Injector dslInjector) {
		
		if (dslInjector == null) {
			return null;
		}
		
		Composite top = new Composite(parent, SWT.NONE);
        top.setLayout(new GridLayout());
			
		provider = dslInjector.getInstance(CostumResourceProvider.class);
		provider.setIndex(index);
		index++;
		EmbeddedEditorFactory factory = dslInjector.getInstance(EmbeddedEditorFactory.class);
		
		editor = factory.newEditor((IEditedResourceProvider)provider).showErrorAndWarningAnnotations().withParent(parent);
		
		// Important method that activates the nice behavior of a xtext editor 
		EmbeddedEditorModelAccess modelAccess = editor.createPartialEditor();
		
		LineNumberRulerColumn lineNumberRulerColumn = new LineNumberRulerColumn();
		editor.getViewer().addVerticalRulerColumn(lineNumberRulerColumn);
		
		return modelAccess;

	}
	
	/**
	 * Creates an embedded xtext editor without activation of it.
	 * Importent to know: Some methods used from xtext are restricted, so changes are possible!
	 * @param parent the parent Composite
	 * @param dslInjector the mapping language
	 * @return the EmbeddedEditor
	 */
	public EmbeddedEditor createEditorWithoutActivation(Composite parent, Injector dslInjector) {
		
		if (dslInjector == null) {
			return null;
		}
		
		Composite top = new Composite(parent, SWT.NONE);
        top.setLayout(new GridLayout());
			
		provider = dslInjector.getInstance(CostumResourceProvider.class);
		provider.setIndex(index);
		index++;
		
		EmbeddedEditorFactory factory = dslInjector.getInstance(EmbeddedEditorFactory.class);
		
		editor = factory.newEditor((IEditedResourceProvider)provider).showErrorAndWarningAnnotations().withParent(parent);
		
		return editor;

	}

	public Resource getResource() {
		return provider.getResource();
	}
	

}
