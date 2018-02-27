package nmd.tagger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Tools {

    private static final String DIVIDER = "--";
    private static final int DIVIDER_LENGTH = "--".length();
    private static final String MP3_EXTENSION = ".mp3";

    public static TrackInfo parse(String name) {
        String artist = "";
        String title = "";

        int dividerIndex = name.indexOf(DIVIDER);

        if (dividerIndex != -1) {
            artist = restoreSpaces(name.substring(0, dividerIndex));
            title = restoreSpaces(name.substring(dividerIndex + DIVIDER_LENGTH, name.length()));
        }

        return new TrackInfo(artist, title);
    }

    private static String restoreSpaces(String source) {
        return source.replace("-", " ");
    }

    public static List<Path> scan(String directory) throws IOException {
        return Files.walk(Paths.get(directory))
                .filter(p -> p.toString().toLowerCase().endsWith(MP3_EXTENSION))
                .collect(Collectors.toList());
    }

    public static Track read(Path path, TrackFileActions trackFileActions) {
        return null;
    }

}
