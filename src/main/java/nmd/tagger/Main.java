package nmd.tagger;

import nmd.tagger.application.State;
import nmd.tagger.application.Step;
import nmd.tagger.operations.Mp3OperationsFactory;
import nmd.tagger.operations.mp3agic.Mp3agicOperationsFactory;
import nmd.tagger.tracks.Track;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by igu on 16-Mar-18.
 */
public class Main implements Runnable {

    private static final String DIRECTORY = "D:\\";

    private final State state;

    public Main(State state) {
        this.state = state;
    }

    @Override
    public void run() {

        try {
            state.scan();
            final List<Path> files = Tools.scan(DIRECTORY);

            state.scanCompleted(files.size());

            final List<Track> tracks = new ArrayList<>();

            int count = 0;

            for (Path file : files) {
                state.read(count++);

                final Track track = Tools.read(file);

                tracks.add(track);
            }

            final Mp3OperationsFactory operationsFactory = new Mp3agicOperationsFactory();

            count = 0;

            for (Track track : tracks) {
                state.verify(count++);

                Tools.validateTrack(track, operationsFactory);
            }

            boolean allFilesAreValid = true;

            for (Track track : tracks) {

                if (!track.isValid()) {
                    System.out.println(String.format("file %s is not valid", track.getPath().getFileName().toString()));
                    allFilesAreValid = false;
                }
            }

            if (allFilesAreValid) {
                state.setStep(Step.PROCESS);
                final Set<String> artists = Tools.createArtistList(tracks);
                artists.forEach(System.out::println);
                //Tools.update(tracks, operationsFactory);
            }

            System.out.println(tracks);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            state.setStep(Step.END);
        }
    }

}
