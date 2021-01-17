package exception;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import ensa.pay.exception.ExceptionCode;
import ensa.pay.exception.MicroServiceException;

public class MicroServiceMatcher extends TypeSafeMatcher<MicroServiceException> {

	private final ExceptionCode exeExceptionCode;
	private ExceptionCode foundExceptionCode;

	private MicroServiceMatcher(ExceptionCode exceptionCode) {
		this.exeExceptionCode = exceptionCode;
	}

	public static MicroServiceMatcher hasType(ExceptionCode item) {
		return new MicroServiceMatcher(item);
	}

	@Override
	protected boolean matchesSafely(final MicroServiceException exception) {
		foundExceptionCode = exception.getExceptionCode();
		return exeExceptionCode.equals(exception.getExceptionCode());
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("Expected type is ").appendValue(exeExceptionCode.name()).appendText(" but ")
				.appendValue(foundExceptionCode).appendText(" was found");
	}
}
