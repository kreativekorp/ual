package com.apple.eawt;

import java.util.EventObject;

@Deprecated
public final class ApplicationEvent extends EventObject {
	private static final long serialVersionUID = 1L;
	
	final String filename;
	boolean handled;
	
	@Deprecated
	ApplicationEvent(final Object src) {
		super(src);
		this.filename = null;
		this.handled = false;
	}
	
	@Deprecated
	ApplicationEvent(final Object src, final String filename) {
		super(src);
		this.filename = filename;
		this.handled = false;
	}
	
	@Deprecated
	public boolean isHandled() {
		return handled;
	}
	
	@Deprecated
	public void setHandled(final boolean handled) {
		this.handled = handled;
	}
	
	@Deprecated
	public String getFilename() {
		return filename;
	}
}
