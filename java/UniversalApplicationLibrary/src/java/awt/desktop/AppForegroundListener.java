package java.awt.desktop;

public interface AppForegroundListener extends SystemEventListener {
	public void appRaisedToForeground(AppForegroundEvent e);
	public void appMovedToBackground(AppForegroundEvent e);
}
