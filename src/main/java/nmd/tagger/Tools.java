package nmd.tagger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Tools {

    private static final String DIVIDER = "--";
    private static final int DIVIDER_LENGTH = DIVIDER.length();
    private static final String MP3_EXTENSION = ".mp3";

    public static void validateTrack(Track track) {

    }

    public static List<Track> readTracks(List<Path> tracks) {
        return tracks.stream()
                .map(Tools::read)
                .collect(Collectors.toList());
    }

    public static TrackInfo parse(String fileName) {
        String name = getNameWithoutExtension(fileName);

        String artist = "";
        String title = "";

        int dividerIndex = name.indexOf(DIVIDER);

        if (dividerIndex != -1) {
            artist = restoreSpaces(name.substring(0, dividerIndex));
            title = restoreSpaces(name.substring(dividerIndex + DIVIDER_LENGTH, name.length()));
        }

        return new TrackInfo(artist, title);
    }

    public static List<Path> scan(String directory) throws IOException {
        return Files.walk(Paths.get(directory))
                .filter(p -> p.toString().toLowerCase().endsWith(MP3_EXTENSION))
                .collect(Collectors.toList());
    }

    public static Track read(Path path) {
        String fileName = path.getFileName().toString();
        TrackInfo trackInfo = parse(fileName);

        return new Track(path, trackInfo);
    }

    private static String getNameWithoutExtension(String fileName) {
        int index = fileName.toLowerCase().indexOf(MP3_EXTENSION);

        return index == -1 ? fileName : fileName.substring(0, index);
    }

    private static String restoreSpaces(String source) {
        return source.replace("-", " ");
    }

}
