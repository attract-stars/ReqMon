package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.analytics.IRequirementElementMapping;

public class RequirementDslResourceContentAdappter extends EContentAdapter {

	IRequirementElementMapping<?, ?> semanticMapping;

	public RequirementDslResourceContentAdappter(IRequirementElementMapping<?, ?> mapping) {
		this.semanticMapping = mapping;
	}

	@Override
	public void notifyChanged(Notification notification) {
		System.out.println("Mapping has been created for resource but ressource has just been changed!");
		super.notifyChanged(notification);
		semanticMapping.setDirtySource(true);
	}
}
