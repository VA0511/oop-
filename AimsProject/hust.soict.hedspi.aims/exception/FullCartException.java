package hust.soict.hedspi.aims.exception;

public class DuplicatedMediaException extends Exception {

	public DuplicatedMediaException() {}

	public DuplicatedMediaException(String message) {
		super(message);
	}

	public DuplicatedMediaException(Throwable cause) {
		super(cause);
	}

	public DuplicatedMediaException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicatedMediaException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
