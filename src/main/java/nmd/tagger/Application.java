package nmd.tagger;

import nmd.tagger.operations.Mp3OperationsFactory;
import nmd.tagger.operations.mp3agic.Mp3agicOperationsFactory;
import nmd.tagger.tracks.Track;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public class Application {

    private static final String DIRECTORY = "D:\\";

    public static void main(String[] args) throws IOException {
        final List<Path> files = Tools.scan(DIRECTORY);
        final List<Track> tracks = Tools.readTracks(files);

        final Mp3OperationsFactory operationsFactory = new Mp3agicOperationsFactory();
        Tools.validate(tracks, operationsFactory);

        boolean allFilesAreValid = true;

        for (Track track : tracks) {

            if (!track.isValid()) {
                System.out.println(String.format("file %s is not valid", track.getPath().getFileName().toString()));
                allFilesAreValid = false;
            }
        }

        if (allFilesAreValid) {
            final Set<String> artists = Tools.createArtistList(tracks);
            artists.forEach(System.out::println);
            Tools.update(tracks, operationsFactory);
        }

        System.out.println(tracks);
    }

}
