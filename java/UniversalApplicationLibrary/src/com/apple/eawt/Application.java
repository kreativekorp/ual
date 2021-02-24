package com.apple.eawt;

import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Window;
import javax.swing.JMenuBar;

public final class Application {
	private static PopupMenu dockMenu = null;
	private static Image dockIconImage = null;
	
	@Deprecated private static boolean aboutPresent = true;
	@Deprecated private static boolean aboutEnabled = true;
	@Deprecated private static boolean preferencesPresent = false;
	@Deprecated private static boolean preferencesEnabled = false;
	
	public static Application getApplication() {
		throw new UnsupportedOperationException("com.apple.eawt.Application is a stub");
	}
	
	@Deprecated
	public static Point getMouseLocationOnScreen() {
		return MouseInfo.getPointerInfo().getLocation();
	}
	
	@Deprecated
	public Application() {
		throw new UnsupportedOperationException("com.apple.eawt.Application is a stub");
	}
	
	public void addAppEventListener(final AppEventListener l) {}
	public void disableSuddenTermination() {}
	public void enableSuddenTermination() {}
	public Image getDockIconImage() { return dockIconImage; }
	public PopupMenu getDockMenu() { return dockMenu; }
	public void openHelpViewer() {}
	public void removeAppEventListener(final AppEventListener l) {}
	public void requestForeground(final boolean allWindows) {}
	public void requestToggleFullScreen(final Window window) {}
	public void requestUserAttention(final boolean critical) {}
	public void setAboutHandler(final AboutHandler h) {}
	public void setDefaultMenuBar(final JMenuBar mb) {}
	public void setDockIconBadge(final String badge) {}
	public void setDockIconImage(final Image image) { dockIconImage = image; }
	public void setDockMenu(final PopupMenu menu) { dockMenu = menu; }
	public void setOpenFileHandler(final OpenFilesHandler h) {}
	public void setOpenURIHandler(final OpenURIHandler h) {}
	public void setPreferencesHandler(final PreferencesHandler h) {}
	public void setPrintFileHandler(final PrintFilesHandler h) {}
	public void setQuitHandler(final QuitHandler h) {}
	public void setQuitStrategy(final QuitStrategy s) {}
	
	@Deprecated public void addAboutMenuItem() { aboutPresent = true; }
	@Deprecated public void addApplicationListener(final ApplicationListener l) {}
	@Deprecated public void addPreferencesMenuItem() { preferencesPresent = true; }
	@Deprecated public boolean getEnabledAboutMenu() { return aboutEnabled; }
	@Deprecated public boolean getEnabledPreferencesMenu() { return preferencesEnabled; }
	@Deprecated public boolean isAboutMenuItemPresent() { return aboutPresent; }
	@Deprecated public boolean isPreferencesMenuItemPresent() { return preferencesPresent; }
	@Deprecated public void removeAboutMenuItem() { aboutPresent = false; }
	@Deprecated public void removeApplicationListener(final ApplicationListener l) {}
	@Deprecated public void removePreferencesMenuItem() { preferencesPresent = false; }
	
	@Deprecated
	public void setEnabledAboutMenu(final boolean enable) {
		aboutPresent = true;
		aboutEnabled = enable;
	}
	
	@Deprecated
	public void setEnabledPreferencesMenu(final boolean enable) {
		preferencesPresent = true;
		preferencesEnabled = enable;
	}
}
