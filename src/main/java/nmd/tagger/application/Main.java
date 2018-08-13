package nmd.tagger.application;

import nmd.tagger.application.command.CommandFactory;
import nmd.tagger.application.state.State;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Created by igu on 16-Mar-18.
 */
public class Main implements Runnable {

    private final Parameters parameters;
    private final CommandFactory commandFactory;
    private final State state;

    public Main(Parameters parameters, CommandFactory commandFactory, State state) {
        this.parameters = parameters;
        this.commandFactory = commandFactory;
        this.state = state;
    }

    public static List<Path> scanForMp3(String path) {
        try {
            return Files.walk(Paths.get(path))
                    .filter(p -> p.toString().toLowerCase().endsWith(".mp3"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        final String musicPath = parameters.getMusicPath();
        final CompletableFuture<List<Path>> mp3Files = CompletableFuture.supplyAsync(() -> scanForMp3(musicPath));
        try {
            final List<Path> files = mp3Files.get();
            System.out.println(files);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        try {
//            ScanForMp3Files scanForMp3Files = commandFactory.scanForMp3Files(parameters.getMusicPath());
//            scanForMp3Files.execute();
//
//            if (state.getStep().equals(Step.ERROR)) {
//                return;
//            }
//
//            final List<Track> tracks = new ArrayList<>();
//
//            int count = 0;
//
//            for (Path file : state.getFiles()) {
//                state.read(count++);
//
//                final Track track = Tools.read(file);
//
//                tracks.add(track);
//            }
//
//            final Mp3OperationsFactory operationsFactory = new Mp3agicOperationsFactory();
//
//            count = 0;
//
//            for (Track track : tracks) {
//                state.verify(count++);
//
//                Tools.validateTrack(track, operationsFactory);
//            }
//
//            boolean allFilesAreValid = true;
//
//            for (Track track : tracks) {
//
//                if (!track.isValid()) {
//                    System.out.println(String.format("file %s is not valid", track.getPath().getFileName().toString()));
//                    allFilesAreValid = false;
//                }
//            }
//
//            if (allFilesAreValid) {
//                state.setStep(Step.PROCESS);
//                final Set<String> artists = Tools.createArtistList(tracks);
//                artists.forEach(System.out::println);
//                Tools.update(tracks, operationsFactory);
//            }
//
//            System.out.println(tracks);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            state.setStep(Step.END);
//        }
    }

}
