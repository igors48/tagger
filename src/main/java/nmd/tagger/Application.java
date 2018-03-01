package nmd.tagger;

import nmd.tagger.operations.Mp3OperationsFactory;
import nmd.tagger.operations.mp3agic.Mp3agicOperationsFactory;
import nmd.tagger.tracks.Track;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by igu on 01-Mar-18.
 */
public class Application {

    private static final String DIRECTORY = "D:\\T\\";

    public static void main(String[] args) throws IOException {
        List<Path> files = Tools.scan(DIRECTORY);
        List<Track> tracks = Tools.readTracks(files);

        Mp3OperationsFactory operationsFactory = new Mp3agicOperationsFactory();
        Tools.validate(tracks, operationsFactory);

        boolean allFilesAreValid = true;

        for (Track track : tracks) {

            if (!track.isValid()) {
                System.out.println(String.format("file %s is not valid", track.getPath().getFileName().toString()));
                allFilesAreValid = false;
            }
        }

        if (allFilesAreValid) {
            Tools.update(tracks, operationsFactory);
        }

        System.out.println(tracks);
    }

}
