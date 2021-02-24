package java.awt.desktop;

public final class UserSessionEvent extends AppEvent {
	private static final long serialVersionUID = 6747138462796569055L;
	
	public static enum Reason {
		UNSPECIFIED, CONSOLE, REMOTE, LOCK
	}
	
	private final Reason reason;
	
	public UserSessionEvent(Reason reason) {
		this.reason = reason;
	}
	
	public Reason getReason() {
		return reason;
	}
}
