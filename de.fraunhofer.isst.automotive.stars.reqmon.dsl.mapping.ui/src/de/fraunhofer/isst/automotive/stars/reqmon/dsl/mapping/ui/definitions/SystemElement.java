package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions;

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
	
	public String[] getFilterExt() {
		String[] filterExt = { "*.txt", "*.doc", ".rtf", "*.*" };
		return filterExt;
	}

	public void execute() {
		System.out.println("System executed!");
	}

}
