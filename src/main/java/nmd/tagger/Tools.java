package nmd.tagger;

import nmd.tagger.operations.Mp3Operations;
import nmd.tagger.operations.Mp3OperationsFactory;

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

    public static void update(List<Track> tracks, Mp3OperationsFactory factory) {
        tracks.forEach(track -> updateTrack(track, factory));
    }

    public static void updateTrack(Track track, Mp3OperationsFactory factory) {

        try {
            final Path path = track.getPath();
            Mp3Operations operations = factory.create(path);

            final TrackInfo trackInfo = track.getTrackInfo();
            final String artist = trackInfo.getArtist();
            final String title = trackInfo.getTitle();

            operations.setArtistAndTitle(artist, title);

            operations.store();
            track.setProcessed(true);
        } catch (Exception e) {
            track.setProcessed(false);
        }
    }

    public static void validate(List<Track> tracks, Mp3OperationsFactory factory) {
        tracks.forEach(track -> validateTrack(track, factory));
    }

    public static void validateTrack(Track track, Mp3OperationsFactory factory) {
        final TrackInfo trackInfo = track.getTrackInfo();

        boolean artistDefined = isNotEmpty(trackInfo.getArtist());
        boolean titleDefined = isNotEmpty(trackInfo.getTitle());

        boolean readable = true;

        final Path path = track.getPath();

        try {
            Mp3Operations operations = factory.create(path);
            operations.hasId3v1Tag();
            operations.hasId3v2Tag();
        } catch (Exception e) {
            readable = false;
        }

        track.setArtistDefined(artistDefined);
        track.setTitleDefined(titleDefined);
        track.setReadable(readable);
    }

    private static boolean isNotEmpty(String artist) {
        return !(artist == null || artist.isEmpty());
    }

    public static List<Track> readTracks(List<Path> tracks) {
        return tracks.stream()
                .map(Tools::read)
                .collect(Collectors.toList());
    }

    public static TrackInfo parse(String fileName) {
        final String name = getNameWithoutExtension(fileName);

        String artist = "";
        String title = "";

        final int dividerIndex = name.indexOf(DIVIDER);

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
        final String fileName = path.getFileName().toString();
        final TrackInfo trackInfo = parse(fileName);

        return new Track(path, trackInfo);
    }

    private static String getNameWithoutExtension(String fileName) {
        final int index = fileName.toLowerCase().indexOf(MP3_EXTENSION);

        return index == -1 ? fileName : fileName.substring(0, index);
    }

    private static String restoreSpaces(String source) {
        return source.replace("-", " ");
    }

}
