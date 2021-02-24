package java.awt.desktop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilesEvent extends AppEvent {
	private static final long serialVersionUID = 5271763715462312871L;
	
	final List<File> files;
	
	FilesEvent(final List<File> files) {
		this.files = files;
	}
	
	public List<File> getFiles() {
		if (files == null) return null;
		return new ArrayList<File>(files);
	}
}
