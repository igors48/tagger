import nmd.tagger.Tools;
import nmd.tagger.tracks.TrackInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class FileNameParseTest {

    private String name;
    private String artist;
    private String title;

    public FileNameParseTest(String name, String artist, String title) {
        this.name = name;
        this.artist = artist;
        this.title = title;
    }

    @Parameterized.Parameters(name = "{index}: parse({0}) => {1}, {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"a-b--c-d.mp3", "a b", "c d"},
                {"a-B--c-D.mP3", "a B", "c D"},
                {"c-d.mp3", "", ""},
                {"-c-d.mp3", "", ""},
                {"--c-d.mp3", "", "c d"},
                {"a-b--.mp3", "a b", ""}
        });
    }

    @Test
    public void test() {
        assertEquals(Tools.parse(name), new TrackInfo(artist, title));
    }

}
