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
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp;

import java.util.Comparator;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType;

public class TestAppRequirementElement implements IRequirementElement {
	
	private String name;
	private RequirementType type;
	
	public TestAppRequirementElement() {}
	
	public TestAppRequirementElement(String name, RequirementType type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public String getElementName() {
		return this.name;
	}

	@Override
	public RequirementType getElementType() {
		return this.type;
	}

	@Override
	public void setElementName(String name) {
		this.name = name;
	}

	@Override
	public void setElementType(RequirementType type) {
		this.type = type;
	}
	
	
	@Override
	public Comparator<? super IRequirementElement> getElementTypeComparator() {
		
		return new Comparator<IRequirementElement>() {

			@Override
			public int compare(IRequirementElement o1, IRequirementElement o2) {
				if (o1.getElementType().equals(o2.getElementType())) {
					return 0;
				}
				else if (o1.getElementType().equals(RequirementType.OBJECT)) {
					return -1;
				}
				else if (o1.getElementType().equals(RequirementType.FUNCTION) && 
						o2.getElementType().equals(RequirementType.RELATION)) {
					return -1;
				}
				else {
					return 1;
				}
			}
		};
			
	}
	
	

}
