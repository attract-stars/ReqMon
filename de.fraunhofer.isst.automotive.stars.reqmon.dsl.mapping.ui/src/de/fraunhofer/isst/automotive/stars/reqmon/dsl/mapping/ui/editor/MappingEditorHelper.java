package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor;

import com.google.inject.Injector;

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
		EmbeddedEditorFactory factory = dslInjector.getInstance(EmbeddedEditorFactory.class);
		
		editor = factory.newEditor((IEditedResourceProvider)provider).showErrorAndWarningAnnotations().withParent(parent);
		
		return editor;

	}
	
	
	

}
