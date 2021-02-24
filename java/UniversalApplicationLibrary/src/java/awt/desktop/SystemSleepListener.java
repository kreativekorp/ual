package java.awt.desktop;

public interface SystemSleepListener extends SystemEventListener {
	public void systemAboutToSleep(SystemSleepEvent e);
	public void systemAwoke(SystemSleepEvent e);
}
