package nmd.tagger.application.cli;

import nmd.tagger.application.ApplicationError;
import nmd.tagger.application.ErrorCode;
import nmd.tagger.application.Parameters;

public final class CommandLineParser {

    public static Parameters parse(String[] arguments) throws ApplicationError {

        if (arguments.length == 0) {
            throw new ApplicationError(ErrorCode.NO_ARGUMENTS);
        }

        if (arguments.length > 1) {
            throw new ApplicationError(ErrorCode.TOO_MANY_ARGUMENTS);
        }

        return new Parameters(arguments[0]);
    }

}
