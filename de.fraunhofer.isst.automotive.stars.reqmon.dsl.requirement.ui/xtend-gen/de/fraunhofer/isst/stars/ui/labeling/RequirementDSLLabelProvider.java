/**
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars.ui.labeling;

import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;

/**
 * Provides labels for EObjects.
 * 
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#label-provider
 */
@SuppressWarnings("all")
public class RequirementDSLLabelProvider extends DefaultEObjectLabelProvider {
  @Inject
  public RequirementDSLLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
}