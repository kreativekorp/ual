package com.apple.eawt;

import java.util.EventListener;

public interface FullScreenListener extends EventListener {
	public void windowEnteringFullScreen(AppEvent.FullScreenEvent e);
	public void windowEnteredFullScreen(AppEvent.FullScreenEvent e);
	public void windowExitingFullScreen(AppEvent.FullScreenEvent e);
	public void windowExitedFullScreen(AppEvent.FullScreenEvent e);
}
