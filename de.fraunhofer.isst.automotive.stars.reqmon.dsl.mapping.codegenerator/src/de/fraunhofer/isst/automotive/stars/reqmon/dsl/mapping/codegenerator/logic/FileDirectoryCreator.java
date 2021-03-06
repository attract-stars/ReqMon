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
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.logic;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.templates.FilterType;

/**
 * This class creates a new project in the runtime environment. In the project it creates folders for each editor-project.
 * In each project-folder it generates a folder for each filter type and a folder for the data types. 
 * The header and the c++ file of a generated filter is added to its corresponding filter folder.
 * @author sgraf
 *
 */
public class FileDirectoryCreator {
	private String rootPath;
	private String startFolder = "filter-gen";
	private String aff = "abstract_function_filter";
	private String fcof = "functional_correctness_oracle_filter";
	private String saf = "scene_abstraction_filter";
	//private String data = "types";
	private boolean isFirst;
	private IFolder projectFolder;
	private String projectName;
	
	/**
	 * The constructor generates a project with the given name (without the extension).
	 * @param projectName the name of the editor project
	 */
	public FileDirectoryCreator(String projectName) {
		if (projectName.contains(".")) {
			this.projectName = projectName.split(Pattern.quote("."))[0];
		}
		else {
			this.projectName = projectName;
		}
	}
	
	/**
	 * Creates folders for each filter type and the data types.
	 */
	public void createFileStructure() {
		isFirst = true;
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		rootPath = root.getLocation().toString() + "/" + startFolder + "/";
		IProject project = root.getProject(startFolder);
		
		try {
			if (!project.exists()) {
				project.create(null);
			}
			project.open(null);
			
			projectFolder = project.getFolder(projectName);
			if (!projectFolder.exists()) {
				projectFolder.create(false, true, null);
			}
			
			//IFolder dataFolder = projectFolder.getFolder(data);
			IFolder affFolder = projectFolder.getFolder(aff);
			IFolder fcofFolder = projectFolder.getFolder(fcof);
			IFolder safFolder = projectFolder.getFolder(saf);
			
			/*if (!dataFolder.exists()) {
				dataFolder.create(false, true, null);
			}*/
			if (!affFolder.exists()) {
				affFolder.create(false, true, null);
			}
			if (!fcofFolder.exists()) {
				fcofFolder.create(false, true, null);
			}
			if (!safFolder.exists()) {
				safFolder.create(false, true, null);
			}
			
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Writes the given filter content to the filter file of the given type.
	 * @param type type of the filter
	 * @param content the generated filter
	 */
	public void writeInFolder(FilterType type, String content) {
		String path = rootPath + projectName + "/";
		
		switch(type) {
		case ABSTRACT_FUNCTION: 
			if (isFirst) {
				path = path + aff + "/" + aff + ".h";
				isFirst = false;
			}
			else {
				path = path + aff + "/" + aff + ".cpp";
				isFirst = true;
			}
			break;
		case FUNCTIONAL_CORRECTNESS_ORACLE:
			if (isFirst) {
				path = path + fcof + "/" + fcof + ".h";
				isFirst = false;
			}
			else {
				path = path + fcof + "/" + fcof + ".cpp";
				isFirst = true;
			}
			break;
		case SCENE_ABSTRACTION:
			if (isFirst) {
				path = path + saf + "/" + saf + ".h";
				isFirst = false;
			}
			else {
				path = path + saf + "/" + saf + ".cpp";
				isFirst = true;
			}
			break;
		case TEST_COVERAGE_MONITOR:
			break;
		}
		
		write(content,new File(path));
	}
	
	/**
	 * Writes the given data type content into the file with the given name.
	 * @param name the name of the data type file
	 * @param content the generated data types
	 */
	public void writeInFolder(String name, String content) {
		String path = rootPath + projectName + "/" + aff + "/" + name + ".h";
		write(content,new File(path));
		path = rootPath + projectName + "/" + fcof + "/" + name + ".h";
		write(content,new File(path));
		path = rootPath + projectName + "/" + saf + "/" + name + ".h";
		write(content,new File(path));
	}
	
	/**
	 * Writes the given data type content into the file with the given name.
	 * @param name the name of the data type file
	 * @param type filter type
	 * @param content the generated data types
	 */
	public void writeCMakeListsInFolder(String name, FilterType type, String content) {
		String path = "";
		if (type != null) {
			path = rootPath + projectName + "/" + selectType(type) +  "/" + name + ".txt";
		}
		else {
			path = rootPath + projectName + "/" + name + ".txt";
		}
		write(content,new File(path));
	}
	
	private String selectType(FilterType type) {
		switch(type) {
		case ABSTRACT_FUNCTION:
			return aff;
		case FUNCTIONAL_CORRECTNESS_ORACLE:
			return fcof;
		case SCENE_ABSTRACTION:
			return saf;
		case TEST_COVERAGE_MONITOR:
			return "";
		default: return "";
		}
	}
	
	/**
	 * Writes the given text into the given file.
	 * @param text a text
	 * @param file a file
	 */
	private void write(String text, File file) {
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(text);
			writer.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		refresh();
	}
	
	/**
	 * Refresh of the view after the generation of the files. 
	 */
	private void refresh() {
		for(IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()){
		    try {
				project.refreshLocal(IResource.DEPTH_INFINITE, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

}
