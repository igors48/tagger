import nmd.tagger.NameParser;
import nmd.tagger.TrackInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class NameParserTest {

    private String name;
    private String artist;
    private String title;

    public NameParserTest(String name, String artist, String title) {
        this.name = name;
        this.artist = artist;
        this.title = title;
    }

    @Parameterized.Parameters(name = "{index}: parse({0}) => {1}, {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"a-b--c-d", "a b", "c d"},
                {"c-d", "", ""},
                {"-c-d", "", ""},
                {"--c-d", "", "c d"},
                {"a-b--", "a b", ""}
        });
    }

    @Test
    public void test() {
        assertEquals(NameParser.parse(name), new TrackInfo(artist, title));
    }

}
