package com.apple.eawt;

public interface UserSessionListener extends AppEventListener {
	public void userSessionDeactivated(AppEvent.UserSessionEvent e);
	public void userSessionActivated(AppEvent.UserSessionEvent e);
}
