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
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.swt.widgets.Text;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IProposal;

/**
 * This class manages the Proposal extensions for the mapping text field. 
 * The Proposal is selected in dependency of the mapping language.  
 * 
 * @author sgraf
 *
 */
public class ProposalController {
	
	private static final String IPROPOSAL_ID =
			"de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.proposal";

	private IExtensionRegistry registry;
	private IConfigurationElement[] configProp;
	private boolean isRegistry;
	private IProposal proposal;
	private boolean isProp;
	
	/**
	 * This constructor checks if a registry exists and if Proposals are registered.
	 */
	public ProposalController() {
		registry = Platform.getExtensionRegistry();
		isProp = false;
		
		if (registry == null) {
			System.out.println("No registry!");
			isRegistry = false;
		}
		else {
			isRegistry = true;
			configProp = registry.getConfigurationElementsFor(IPROPOSAL_ID);
			
			if (configProp.length == 0) {
				System.out.println("No Proposal registered!");
			}
		}	
	}
	
	/**
	 * Selects a Proposal for the given language from the registered Proposals.
	 * If no suitable Proposal exists no Proposal will be selected.
	 * @param language the mapping language
	 */
	public void selectProposal(String language) {
		if (!isRegistry) {
			return;
		}
		try {
			if (configProp.length == 0) {
				return;
			}
			for (IConfigurationElement e : configProp) {
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IProposal && e.getAttribute("lang").equals(language)) {
					proposal = (IProposal) o;
					isProp = true;
					break;
				}
			}
			
		} catch (CoreException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Checks if the registered Proposals are executable.
	 */
	public void checkExtensions() {
		if (!isRegistry) {
			return;
		}
		try {
			for (IConfigurationElement e : configProp) {
				System.out.println("\nEvaluating proposal extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IProposal) {
					testPropExtension(o);
				}
			}
		} catch (CoreException ex) {
			ex.printStackTrace();
		}
	}
	
	 
	/**
	  * Creates the decoration for the mapping text field if a suitable Proposal exists. 
	  * The decoration can provide additional informations.
	  * @param text the mapping text field
	  */
	public void createDeco(Text text) {
		if (!isProp) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				proposal.createDeco(text);
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in proposal client:");
				e.printStackTrace();
			}
		};
		SafeRunner.run(runnable);
	}
	
	 /**
	  * Creates proposals for the mapping text field if a suitable Proposal exists. 
	  * @param text the mapping text field
	  */
	public void getProposal(Text text) {
		if (!isProp) {
			return;
		}
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				proposal.getProposal(text);
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in proposal client:");
				e.printStackTrace();
			}
		};
		SafeRunner.run(runnable);
	}

	/**
	 * Checks if the Proposal is executable.
	 * @param o an object of the type IProposal
	 */
	private void testPropExtension(Object o) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void run() throws Exception {
				System.out.println("Proposal exists.");
			}
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in proposal client:");
				e.printStackTrace();
			}
		};
		SafeRunner.run(runnable);
		
	}
	
	

}
