package java.awt.desktop;

import java.awt.Desktop;
import java.util.EventObject;

public class AppEvent extends EventObject {
	private static final long serialVersionUID = -5958503993556009432L;
	
	AppEvent() {
		super(Desktop.getDesktop());
	}
}
