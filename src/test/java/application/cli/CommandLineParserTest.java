package application.cli;

import nmd.tagger.application.ApplicationError;
import nmd.tagger.application.ErrorCode;
import nmd.tagger.application.cli.CommandLineParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CommandLineParserTest {

    @Test
    public void whenNoArgumentsThenErrorThrown() {
        try {
            CommandLineParser.parse(new String[]{});
            fail();
        } catch (ApplicationError applicationError) {
            assertEquals(ErrorCode.NO_ARGUMENTS, applicationError.getErrorCode());
        }
    }

    @Test
    public void whenMoreThanOneArgumentsThenErrorThrown() {
        try {
            CommandLineParser.parse(new String[]{"1", "2"});
            fail();
        } catch (ApplicationError applicationError) {
            assertEquals(ErrorCode.TOO_MANY_ARGUMENTS, applicationError.getErrorCode());
        }
    }

    @Test
    public void whenOneArgumentsThenItIsReturnedInParameters() throws ApplicationError {
        assertEquals("1", CommandLineParser.parse(new String[]{"1"}).getPath());
    }

}
