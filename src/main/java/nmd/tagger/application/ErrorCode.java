package nmd.tagger.application;

public enum ErrorCode {

    NO_ARGUMENTS("No arguments"),
    TOO_MANY_ARGUMENTS("Too many arguments");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
