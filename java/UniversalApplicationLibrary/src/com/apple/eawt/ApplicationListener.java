package com.apple.eawt;

import java.util.EventListener;

@Deprecated
public interface ApplicationListener extends EventListener {
	@Deprecated public void handleAbout(ApplicationEvent e);
	@Deprecated public void handleOpenApplication(ApplicationEvent e);
	@Deprecated public void handleOpenFile(ApplicationEvent e);
	@Deprecated public void handlePreferences(ApplicationEvent e);
	@Deprecated public void handlePrintFile(ApplicationEvent e);
	@Deprecated public void handleQuit(ApplicationEvent e);
	@Deprecated public void handleReOpenApplication(ApplicationEvent e);
}
