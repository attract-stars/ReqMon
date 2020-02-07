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

import java.util.ArrayList;
import java.util.List;

public class Generator {
	
	private int index;
	private List<String> generators;
	
	public Generator() {
		this.index = 0;
		this.generators = new ArrayList<String>();
	}

	public int getIndex() {
		return index;
	}
	
	public boolean hasNext() {
		return index < generators.size();
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<String> getGenerators() {
		return generators;
	}

	public void setGenerators(List<String> generators) {
		this.generators = generators;
	}
	
	public void addGenerator(String name) {
		generators.add(name);
	}
	
	public boolean deleteGenerator(String name) {
		if (generators.contains(name)) {
			generators.remove(name);
			return true;
		}
		return false;
	}
	
	public void generateSampleList() {
		generators.add("Generator 1");
		generators.add("Generator 2");
		generators.add("Generator 3");
	}
	
	public String getNextGenerator() {
		return generators.get(index++);
	}
	
	public void activateGenerator(String name) {
		System.out.println("Call the " + name);
	}

}
