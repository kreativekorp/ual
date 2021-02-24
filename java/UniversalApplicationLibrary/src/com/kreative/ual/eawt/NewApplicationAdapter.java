package com.kreative.ual.eawt;

import java.awt.desktop.AboutEvent;
import java.awt.desktop.AppForegroundEvent;
import java.awt.desktop.AppHiddenEvent;
import java.awt.desktop.AppReopenedEvent;
import java.awt.desktop.OpenFilesEvent;
import java.awt.desktop.OpenURIEvent;
import java.awt.desktop.PreferencesEvent;
import java.awt.desktop.PrintFilesEvent;
import java.awt.desktop.QuitEvent;
import java.awt.desktop.ScreenSleepEvent;
import java.awt.desktop.SystemEventListener;
import java.awt.desktop.SystemSleepEvent;
import java.awt.desktop.UserSessionEvent;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JMenuBar;
import com.apple.eawt.AppEvent;
import com.apple.eawt.AppEventListener;
import com.apple.eawt.Application;

/**
 * NewApplicationAdapter translates between the Java 9+ Desktop API
 * and the final version of the Apple EAWT Application API.
 * Only methods common to both APIs are present here.
 * @author Rebecca Bettencourt, Kreative Software
 */
public final class NewApplicationAdapter {
	private static NewApplicationAdapter instance = null;
	
	public static NewApplicationAdapter getInstance() {
		if (instance == null) instance = new NewApplicationAdapter();
		return instance;
	}
	
	private final Application application;
	private final Map<SystemEventListener,List<AppEventListener>> listeners;
	
	private NewApplicationAdapter() {
		application = Application.getApplication();
		listeners = new IdentityHashMap<SystemEventListener,List<AppEventListener>>();
	}
	
	public void addAppEventListener(final SystemEventListener sl) {
		List<AppEventListener> all = listeners.get(sl);
		if (all == null) listeners.put(sl, all = new ArrayList<AppEventListener>());
		
		if (sl instanceof java.awt.desktop.AppForegroundListener) {
			AppEventListener al = new com.apple.eawt.AppForegroundListener() {
				@Override
				public void appMovedToBackground(final AppEvent.AppForegroundEvent e) {
					AppForegroundEvent e2 = new AppForegroundEvent();
					((java.awt.desktop.AppForegroundListener)sl).appMovedToBackground(e2);
				}
				@Override
				public void appRaisedToForeground(final AppEvent.AppForegroundEvent e) {
					AppForegroundEvent e2 = new AppForegroundEvent();
					((java.awt.desktop.AppForegroundListener)sl).appRaisedToForeground(e2);
				}
			};
			application.addAppEventListener(al);
			all.add(al);
		}
		
		if (sl instanceof java.awt.desktop.AppHiddenListener) {
			AppEventListener al = new com.apple.eawt.AppHiddenListener() {
				@Override
				public void appHidden(AppEvent.AppHiddenEvent e) {
					AppHiddenEvent e2 = new AppHiddenEvent();
					((java.awt.desktop.AppHiddenListener)sl).appHidden(e2);
				}
				@Override
				public void appUnhidden(final AppEvent.AppHiddenEvent e) {
					AppHiddenEvent e2 = new AppHiddenEvent();
					((java.awt.desktop.AppHiddenListener)sl).appUnhidden(e2);
				}
			};
			application.addAppEventListener(al);
			all.add(al);
		}
		
		if (sl instanceof java.awt.desktop.AppReopenedListener) {
			AppEventListener al = new com.apple.eawt.AppReOpenedListener() {
				@Override
				public void appReOpened(final AppEvent.AppReOpenedEvent e) {
					AppReopenedEvent e2 = new AppReopenedEvent();
					((java.awt.desktop.AppReopenedListener)sl).appReopened(e2);
				}
			};
			application.addAppEventListener(al);
			all.add(al);
		}
		
		if (sl instanceof java.awt.desktop.ScreenSleepListener) {
			AppEventListener al = new com.apple.eawt.ScreenSleepListener() {
				@Override
				public void screenAboutToSleep(final AppEvent.ScreenSleepEvent e) {
					ScreenSleepEvent e2 = new ScreenSleepEvent();
					((java.awt.desktop.ScreenSleepListener)sl).screenAboutToSleep(e2);
				}
				@Override
				public void screenAwoke(final AppEvent.ScreenSleepEvent e) {
					ScreenSleepEvent e2 = new ScreenSleepEvent();
					((java.awt.desktop.ScreenSleepListener)sl).screenAwoke(e2);
				}
			};
			application.addAppEventListener(al);
			all.add(al);
		}
		
		if (sl instanceof java.awt.desktop.SystemSleepListener) {
			AppEventListener al = new com.apple.eawt.SystemSleepListener() {
				@Override
				public void systemAboutToSleep(final AppEvent.SystemSleepEvent e) {
					SystemSleepEvent e2 = new SystemSleepEvent();
					((java.awt.desktop.SystemSleepListener)sl).systemAboutToSleep(e2);
				}
				@Override
				public void systemAwoke(final AppEvent.SystemSleepEvent e) {
					SystemSleepEvent e2 = new SystemSleepEvent();
					((java.awt.desktop.SystemSleepListener)sl).systemAwoke(e2);
				}
			};
			application.addAppEventListener(al);
			all.add(al);
		}
		
		if (sl instanceof java.awt.desktop.UserSessionListener) {
			AppEventListener al = new com.apple.eawt.UserSessionListener() {
				@Override
				public void userSessionActivated(final AppEvent.UserSessionEvent e) {
					UserSessionEvent.Reason r = UserSessionEvent.Reason.UNSPECIFIED;
					UserSessionEvent e2 = new UserSessionEvent(r);
					((java.awt.desktop.UserSessionListener)sl).userSessionActivated(e2);
				}
				@Override
				public void userSessionDeactivated(final AppEvent.UserSessionEvent e) {
					UserSessionEvent.Reason r = UserSessionEvent.Reason.UNSPECIFIED;
					UserSessionEvent e2 = new UserSessionEvent(r);
					((java.awt.desktop.UserSessionListener)sl).userSessionDeactivated(e2);
				}
			};
			application.addAppEventListener(al);
			all.add(al);
		}
	}
	
	public void removeAppEventListener(final SystemEventListener sl) {
		List<AppEventListener> all = listeners.remove(sl);
		if (all != null) for (AppEventListener al : all) application.removeAppEventListener(al);
	}
	
	public void disableSuddenTermination() {
		application.disableSuddenTermination();
	}
	
	public void enableSuddenTermination() {
		application.enableSuddenTermination();
	}
	
	public void openHelpViewer() {
		application.openHelpViewer();
	}
	
	public void requestForeground(final boolean allWindows) {
		application.requestForeground(allWindows);
	}
	
	public void setAboutHandler(final java.awt.desktop.AboutHandler h) {
		application.setAboutHandler(new com.apple.eawt.AboutHandler() {
			@Override
			public void handleAbout(final AppEvent.AboutEvent e) {
				h.handleAbout(new AboutEvent());
			}
		});
	}
	
	public void setDefaultMenuBar(final JMenuBar menuBar) {
		application.setDefaultMenuBar(menuBar);
	}
	
	public void setOpenFileHandler(final java.awt.desktop.OpenFilesHandler h) {
		application.setOpenFileHandler(new com.apple.eawt.OpenFilesHandler() {
			@Override
			public void openFiles(final AppEvent.OpenFilesEvent e) {
				h.openFiles(new OpenFilesEvent(e.getFiles(), e.getSearchTerm()));
			}
		});
	}
	
	public void setOpenURIHandler(final java.awt.desktop.OpenURIHandler h) {
		application.setOpenURIHandler(new com.apple.eawt.OpenURIHandler() {
			public void openURI(final AppEvent.OpenURIEvent e) {
				h.openURI(new OpenURIEvent(e.getURI()));
			}
		});
	}
	
	public void setPreferencesHandler(final java.awt.desktop.PreferencesHandler h) {
		application.setPreferencesHandler(new com.apple.eawt.PreferencesHandler() {
			@Override
			public void handlePreferences(final AppEvent.PreferencesEvent e) {
				h.handlePreferences(new PreferencesEvent());
			}
		});
	}
	
	public void setPrintFileHandler(final java.awt.desktop.PrintFilesHandler h) {
		application.setPrintFileHandler(new com.apple.eawt.PrintFilesHandler() {
			@Override
			public void printFiles(final AppEvent.PrintFilesEvent e) {
				h.printFiles(new PrintFilesEvent(e.getFiles()));
			}
		});
	}
	
	public void setQuitHandler(final java.awt.desktop.QuitHandler h) {
		application.setQuitHandler(new com.apple.eawt.QuitHandler() {
			@Override
			public void handleQuitRequestWith(
				final AppEvent.QuitEvent e,
				final com.apple.eawt.QuitResponse r
			) {
				h.handleQuitRequestWith(
					new QuitEvent(),
					new java.awt.desktop.QuitResponse() {
						@Override
						public void performQuit() {
							r.performQuit();
						}
						@Override
						public void cancelQuit() {
							r.cancelQuit();
						}
					}
				);
			}
		});
	}
	
	public void setQuitStrategy(final java.awt.desktop.QuitStrategy s) {
		switch (s) {
			case CLOSE_ALL_WINDOWS:
				application.setQuitStrategy(com.apple.eawt.QuitStrategy.CLOSE_ALL_WINDOWS);
				break;
			case NORMAL_EXIT:
				application.setQuitStrategy(com.apple.eawt.QuitStrategy.SYSTEM_EXIT_0);
				break;
		}
	}
}
