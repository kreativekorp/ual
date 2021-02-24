package java.awt.desktop;

public interface AppHiddenListener extends SystemEventListener {
	public void appHidden(AppHiddenEvent e);
	public void appUnhidden(AppHiddenEvent e);
}
