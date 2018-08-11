package nmd.tagger.application;

import nmd.tagger.operations.Mp3Operations;
import nmd.tagger.operations.Mp3OperationsFactory;
import nmd.tagger.tracks.Track;
import nmd.tagger.tracks.TrackInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Tools {

    private static final String DIVIDER = "--";
    private static final int DIVIDER_LENGTH = DIVIDER.length();
    private static final String MP3_EXTENSION = ".mp3";

    static Set<String> createArtistList(List<Track> tracks) {
        return tracks.stream()
                .map(track -> track.getTrackInfo().getArtist())
                .collect(Collectors.toCollection(TreeSet::new));
    }

    static void update(List<Track> tracks, Mp3OperationsFactory factory) {
        tracks.forEach(track -> updateTrack(track, factory));
    }

    private static void updateTrack(Track track, Mp3OperationsFactory factory) {

        try {
            final Path path = track.getPath();
            Mp3Operations operations = factory.create(path);

            final TrackInfo trackInfo = track.getTrackInfo();
            final String artist = trackInfo.getArtist();
            final String title = trackInfo.getTitle();

            updateArtistAndTitle(artist, title, operations);

            final String tempFileName = track.getPath().getParent().toString() + "\\temp.mp3";

            operations.replaceFrontCover();

            operations.store(tempFileName);

            File dest = path.toFile();
            File source = new File(tempFileName);

            dest.delete();
            source.renameTo(dest);

            track.setProcessed(true);
        } catch (Exception e) {
            track.setProcessed(false);
        }
    }

    private static void updateArtistAndTitle(String artist, String title, Mp3Operations operations) {

        if (!operations.hasId3v1Tag()) {
            operations.createId3v1Tag();
        }

        if (!operations.hasId3v2Tag()) {
            operations.createId3v2Tag();
        }

        operations.setArtistToId3v1Tag(artist);
        operations.setArtistToId3v2Tag(artist);
        operations.setAlbumArtistToId3v2Tag(artist);

        operations.setTitleToId3v1Tag(title);
        operations.setTitleToId3v2Tag(title);

        operations.setAlbumToId3v1Tag(artist + " tracks");
        operations.setAlbumToId3v2Tag(artist + " tracks");

        operations.setTrackToId3v1Tag(null);
        operations.setTrackToId3v2Tag(null);
    }

    static void validateTrack(Track track, Mp3OperationsFactory factory) {
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

    static List<Track> readTracks(List<Path> tracks) {
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

    static List<Path> scan(String directory) throws IOException {
        return Files.walk(Paths.get(directory))
                .filter(p -> p.toString().toLowerCase().endsWith(MP3_EXTENSION))
                .collect(Collectors.toList());
    }

    static Track read(Path path) {
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
