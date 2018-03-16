package nmd.tagger;

import nmd.tagger.application.State;
import nmd.tagger.application.Step;
import nmd.tagger.operations.Mp3OperationsFactory;
import nmd.tagger.operations.mp3agic.Mp3agicOperationsFactory;
import nmd.tagger.tracks.Track;

import java.io.IOException;
import java.nio.file.Path;
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
            state.setStep(Step.SCAN);
            final List<Path> files = Tools.scan(DIRECTORY);

            state.setStep(Step.READ);
            final List<Track> tracks = Tools.readTracks(files);

            state.setStep(Step.VERIFY);
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
                state.setStep(Step.PROCESS);
                final Set<String> artists = Tools.createArtistList(tracks);
                artists.forEach(System.out::println);
                //Tools.update(tracks, operationsFactory);
            }

            System.out.println(tracks);
            state.setStep(Step.END);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
