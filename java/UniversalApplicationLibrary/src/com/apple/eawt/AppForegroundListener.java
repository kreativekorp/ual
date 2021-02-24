package com.apple.eawt;

public interface AppForegroundListener extends AppEventListener {
	public void appRaisedToForeground(AppEvent.AppForegroundEvent e);
	public void appMovedToBackground(AppEvent.AppForegroundEvent e);
}
