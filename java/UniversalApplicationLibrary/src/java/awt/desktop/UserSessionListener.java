package java.awt.desktop;

public interface UserSessionListener extends SystemEventListener {
	public void userSessionDeactivated(UserSessionEvent e);
	public void userSessionActivated(UserSessionEvent e);
}
