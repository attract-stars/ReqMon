package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.logic;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.swt.widgets.Text;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.definitions.ILanguageParser;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.definitions.Proposal;

public class ProposalController {
	
	private static final String IPROPOSAL_ID =
			"de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.proposal";

	private IExtensionRegistry registry;
	private IConfigurationElement[] configProp;
	private boolean isRegistry;
	private Proposal proposalExt;
	private Proposal proposal;
	private boolean isExtProp;
	
	public ProposalController() {
		registry = Platform.getExtensionRegistry();
		if (registry == null) {
			System.out.println("No registry!");
			isRegistry = false;
		}
		else {
			isRegistry = true;
		}
		if (isRegistry) {
			configProp = registry.getConfigurationElementsFor(IPROPOSAL_ID);
			
			if (configProp.length == 0) {
				isExtProp = false;
				System.out.println("No Proposal registered!");
			}
			else {
				isExtProp = true;
				createProposal();
				System.out.println("Proposal registered");
			}
		}
		else {
			isExtProp = false;
		}
		
		if (!isExtProp) {
			this.proposal = new Proposal();
		}
	}
	
	public void createDeco(Text text) {
		if (isExtProp) {
			createDecoExt(text);
		}
		else {
			proposal.createDeco(text);
		}
	}
	
	public void getProposal(Text text) {
		if (isExtProp) {
			getProposalExt(text);
		}
		else {
			proposal.getProposal(text);
		}
	}
	
	public void checkExtensions() {
		if (!isRegistry) {
			return;
		}
		try {
			for (IConfigurationElement e : configProp) {
				System.out.println("Evaluating proposal extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof ILanguageParser) {
					testPropExtension(o);
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
	
	private void createDecoExt(Text text) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				proposalExt.createDeco(text);
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in proposal client: can not create deco!");
			}
		};
		SafeRunner.run(runnable);
	}
	
	private void getProposalExt(Text text) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				proposalExt.getProposal(text);
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in proposal client: can not get proposal!");
			}
		};
		SafeRunner.run(runnable);
	}
	

	private void testPropExtension(Object o) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				System.out.println("Proposal exists.");
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in proposal client");
			}
		};
		SafeRunner.run(runnable);
		
	}
	
	private void createProposal() {
		if (!isRegistry) {
			return;
		}
		try {
			if (configProp.length == 0) {
				return;
			}
			final Object o = configProp[0].createExecutableExtension("class");
			if (o instanceof Proposal) {
				proposalExt = (Proposal) o;
			}
			
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
