package java.awt.desktop;

public interface ScreenSleepListener extends SystemEventListener {
	public void screenAboutToSleep(ScreenSleepEvent e);
	public void screenAwoke(ScreenSleepEvent e);
}
