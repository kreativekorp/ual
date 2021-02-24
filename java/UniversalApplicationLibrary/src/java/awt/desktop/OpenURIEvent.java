package java.awt.desktop;

import java.net.URI;

public final class OpenURIEvent extends AppEvent {
	private static final long serialVersionUID = 221209100935933476L;
	
	final URI uri;
	
	public OpenURIEvent(final URI uri) {
		this.uri = uri;
	}
	
	public URI getURI() {
		return uri;
	}
}
