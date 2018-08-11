package nmd.tagger.application;

public class ApplicationError extends Exception {

    private final ErrorCode errorCode;

    public ApplicationError(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
