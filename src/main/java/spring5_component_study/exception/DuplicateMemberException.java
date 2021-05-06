package spring5_component_study.exception;

@SuppressWarnings("serial")
public class DuplicateMemberException extends RuntimeException {
	
	public DuplicateMemberException(String message) {
		super(message);
	}
}
