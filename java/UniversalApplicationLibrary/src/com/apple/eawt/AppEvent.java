package com.apple.eawt;

import java.awt.Window;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class AppEvent extends EventObject {
	private static final long serialVersionUID = 1L;
	
	AppEvent() {
		super(Application.getApplication());
	}
	
	public static final class AboutEvent extends AppEvent {
		private static final long serialVersionUID = 1L;
		AboutEvent() {}
	}
	
	public static final class AppForegroundEvent extends AppEvent {
		private static final long serialVersionUID = 1L;
		AppForegroundEvent() {}
	}
	
	public static final class AppHiddenEvent extends AppEvent {
		private static final long serialVersionUID = 1L;
		AppHiddenEvent() {}
	}
	
	public static final class AppReOpenedEvent extends AppEvent {
		private static final long serialVersionUID = 1L;
		AppReOpenedEvent() {}
	}
	
	public static class FilesEvent extends AppEvent {
		private static final long serialVersionUID = 1L;
		final List<File> files;
		FilesEvent(final List<File> files) {
			this.files = files;
		}
		public List<File> getFiles() {
			if (files == null) return null;
			return new ArrayList<File>(files);
		}
	}
	
	public static final class FullScreenEvent extends AppEvent {
		private static final long serialVersionUID = 1L;
		final Window window;
		FullScreenEvent(final Window window) {
			this.window = window;
		}
		public Window getWindow() {
			return window;
		}
	}
	
	public static final class OpenFilesEvent extends FilesEvent {
		private static final long serialVersionUID = 1L;
		final String searchTerm;
		OpenFilesEvent(final List<File> files, final String searchTerm) {
			super(files);
			this.searchTerm = (searchTerm != null) ? searchTerm : "";
		}
		public String getSearchTerm() {
			return searchTerm;
		}
	}
	
	public static final class OpenURIEvent extends AppEvent {
		private static final long serialVersionUID = 1L;
		final URI uri;
		OpenURIEvent(final URI uri) {
			this.uri = uri;
		}
		public URI getURI() {
			return uri;
		}
	}
	
	public static final class PreferencesEvent extends AppEvent {
		private static final long serialVersionUID = 1L;
		PreferencesEvent() {}
	}
	
	public static final class PrintFilesEvent extends FilesEvent {
		private static final long serialVersionUID = 1L;
		PrintFilesEvent(final List<File> files) {
			super(files);
		}
	}
	
	public static final class QuitEvent extends AppEvent {
		private static final long serialVersionUID = 1L;
		QuitEvent() {}
	}
	
	public static final class ScreenSleepEvent extends AppEvent {
		private static final long serialVersionUID = 1L;
		ScreenSleepEvent() {}
	}
	
	public static final class SystemSleepEvent extends AppEvent {
		private static final long serialVersionUID = 1L;
		SystemSleepEvent() {}
	}
	
	public static final class UserSessionEvent extends AppEvent {
		private static final long serialVersionUID = 1L;
		UserSessionEvent() {}
	}
}
