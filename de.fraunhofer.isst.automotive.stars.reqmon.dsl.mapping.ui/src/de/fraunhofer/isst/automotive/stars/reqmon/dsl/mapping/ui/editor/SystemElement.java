package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor;

public class SystemElement {
	
	private String path;

	public String getPath() {
		if (path == null) {
			path = "";
		}
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}