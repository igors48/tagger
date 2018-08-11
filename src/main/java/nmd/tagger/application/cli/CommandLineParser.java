package nmd.tagger.application.cli;

import nmd.tagger.application.ApplicationError;
import nmd.tagger.application.ErrorCode;
import nmd.tagger.application.Parameters;

public final class CommandLineParser {

    public static Parameters parse(String[] arguments) throws ApplicationError {

        switch (arguments.length) {
            case 0:
                throw new ApplicationError(ErrorCode.NO_ARGUMENTS);
            case 1:
                return new Parameters(arguments[0], "");
            case 2:
                return new Parameters(arguments[0], arguments[1]);
            default:
                throw new ApplicationError(ErrorCode.TOO_MANY_ARGUMENTS);
        }

    }

}
