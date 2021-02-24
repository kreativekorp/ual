package com.kreative.ual.eawt;

import java.awt.desktop.AboutEvent;
import java.awt.desktop.AboutHandler;
import java.awt.desktop.AppReopenedEvent;
import java.awt.desktop.AppReopenedListener;
import java.awt.desktop.OpenFilesEvent;
import java.awt.desktop.OpenFilesHandler;
import java.awt.desktop.PreferencesEvent;
import java.awt.desktop.PreferencesHandler;
import java.awt.desktop.PrintFilesEvent;
import java.awt.desktop.PrintFilesHandler;
import java.awt.desktop.QuitEvent;
import java.awt.desktop.QuitHandler;
import java.awt.desktop.QuitResponse;
import java.awt.desktop.SystemEventListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.apple.eawt.Application;
import com.apple.eawt.ApplicationEvent;
import com.apple.eawt.ApplicationListener;

/**
 * OldApplicationAdapter translates between the Java 9+ Desktop API
 * and the original version of the Apple EAWT ApplicationListener API.
 * Only features common to both APIs are present here.
 * @author Rebecca Bettencourt, Kreative Software
 */
@SuppressWarnings("deprecation")
public final class OldApplicationAdapter {
	private static OldApplicationAdapter instance = null;
	
	public static OldApplicationAdapter getInstance() {
		if (instance == null) instance = new OldApplicationAdapter();
		return instance;
	}
	
	private final Application application;
	private AboutHandler aboutHandler;
	private OpenFilesHandler openFilesHandler;
	private PreferencesHandler preferencesHandler;
	private PrintFilesHandler printFilesHandler;
	private QuitHandler quitHandler;
	private final List<AppReopenedListener> reopenListeners;
	
	private OldApplicationAdapter() {
		application = Application.getApplication();
		aboutHandler = null;
		openFilesHandler = null;
		preferencesHandler = null;
		printFilesHandler = null;
		quitHandler = null;
		reopenListeners = new ArrayList<AppReopenedListener>();
		application.addApplicationListener(applicationListener);
	}
	
	public void addAppEventListener(final SystemEventListener sl) {
		if (sl instanceof AppReopenedListener) {
			reopenListeners.add((AppReopenedListener)sl);
		} else {
			throw new UnsupportedOperationException(
				"OldApplicationAdapter.addAppEventListener only supports AppReopenedListeners"
			);
		}
	}
	
	public void removeAppEventListener(final SystemEventListener sl) {
		reopenListeners.remove(sl);
	}
	
	public void setAboutHandler(final AboutHandler h) {
		application.setEnabledAboutMenu(h != null);
		aboutHandler = h;
	}
	
	public void setOpenFileHandler(final OpenFilesHandler h) {
		openFilesHandler = h;
	}
	
	public void setPreferencesHandler(final PreferencesHandler h) {
		application.setEnabledPreferencesMenu(h != null);
		preferencesHandler = h;
	}
	
	public void setPrintFileHandler(final PrintFilesHandler h) {
		printFilesHandler = h;
	}
	
	public void setQuitHandler(final QuitHandler h) {
		quitHandler = h;
	}
	
	private final ApplicationListener applicationListener = new ApplicationListener() {
		@Override
		public void handleAbout(ApplicationEvent e) {
			if (aboutHandler != null) {
				aboutHandler.handleAbout(new AboutEvent());
				e.setHandled(true);
			}
		}
		@Override
		public void handleOpenApplication(ApplicationEvent e) {
			// No equivalent java.awt.desktop event.
		}
		@Override
		public void handleOpenFile(ApplicationEvent e) {
			if (openFilesHandler != null) {
				List<File> files = Arrays.asList(new File(e.getFilename()));
				openFilesHandler.openFiles(new OpenFilesEvent(files, null));
				e.setHandled(true);
			}
		}
		@Override
		public void handlePreferences(ApplicationEvent e) {
			if (preferencesHandler != null) {
				preferencesHandler.handlePreferences(new PreferencesEvent());
				e.setHandled(true);
			}
		}
		@Override
		public void handlePrintFile(ApplicationEvent e) {
			if (printFilesHandler != null) {
				List<File> files = Arrays.asList(new File(e.getFilename()));
				printFilesHandler.printFiles(new PrintFilesEvent(files));
				e.setHandled(true);
			}
		}
		@Override
		public void handleQuit(ApplicationEvent e) {
			if (quitHandler != null) {
				quitHandler.handleQuitRequestWith(new QuitEvent(), new QuitResponse() {
					@Override
					public void performQuit() {
						System.exit(0);
					}
					@Override
					public void cancelQuit() {
						return;
					}
				});
				e.setHandled(false);
			}
		}
		@Override
		public void handleReOpenApplication(ApplicationEvent e) {
			for (AppReopenedListener l : reopenListeners) {
				l.appReopened(new AppReopenedEvent());
			}
		}
	};
}
