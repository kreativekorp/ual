package java.awt.desktop;

import java.io.File;
import java.util.List;

public final class PrintFilesEvent extends FilesEvent {
	private static final long serialVersionUID = -5752560876153618618L;
	
	public PrintFilesEvent(final List<File> files) {
		super(files);
	}
}
