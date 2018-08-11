package application.cli;

import nmd.tagger.application.ApplicationError;
import nmd.tagger.application.Parameters;
import nmd.tagger.application.cli.CommandLineParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class CommandLineParserTest {

    private final String[] arguments;
    private final Parameters parameters;

    public CommandLineParserTest(String[] arguments, Parameters parameters) {
        this.arguments = arguments;
        this.parameters = parameters;
    }

    @Parameterized.Parameters(name = "{index}: parse({0}) => {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new String[]{"a", "b"}, new Parameters("a", "b")},
                {new String[]{"a"}, new Parameters("a", "")}
        });
    }

    @Test
    public void test() throws ApplicationError {
        assertEquals(this.parameters, CommandLineParser.parse(this.arguments));
    }

}
