package java.awt.desktop;

import java.io.File;
import java.util.List;

public final class OpenFilesEvent extends FilesEvent {
	private static final long serialVersionUID = -3982871005867718956L;
	
	final String searchTerm;
	
	public OpenFilesEvent(final List<File> files, final String searchTerm) {
		super(files);
		this.searchTerm = (searchTerm != null) ? searchTerm : "";
	}
	
	public String getSearchTerm() {
		return searchTerm;
	}
}
