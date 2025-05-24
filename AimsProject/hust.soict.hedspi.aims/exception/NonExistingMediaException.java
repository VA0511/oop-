package hust.soict.hedspi.aims.exception;

public class NonExistingMediaException extends Exception {

	public NonExistingMediaException() {}

	public NonExistingMediaException(String message) {
		super(message);
	}

	public NonExistingMediaException(Throwable cause) {
		super(cause);
	}

	public NonExistingMediaException(String message, Throwable cause) {
		super(message, cause);
	}

	public NonExistingMediaException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
