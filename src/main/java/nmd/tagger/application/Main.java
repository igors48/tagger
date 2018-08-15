package nmd.tagger.application;

import nmd.tagger.application.command.CommandFactory;
import nmd.tagger.application.state.State;
import nmd.tagger.application.state.Step;
import nmd.tagger.tracks.Track;
import nmd.tagger.tracks.TrackInfo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static nmd.tagger.application.Tools.parse;

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

    private List<Path> scanForMp3(String path) {
        try {
            return Files.walk(Paths.get(path))
                    .filter(p -> {
                        final boolean acceptable = p.toString().toLowerCase().endsWith(".mp3");

                        if (acceptable) {
                            this.state.incFilesCount();
                        }

                        return acceptable;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Track read(Path path) {
        final String fileName = path.getFileName().toString();
        final TrackInfo trackInfo = parse(fileName);
        this.state.incCount();
        // add 'not accessible' for error reporting
        return new Track(path, trackInfo);
    }

    @Override
    public void run() {
        try {
            final String musicPath = parameters.getMusicPath();
            final CompletableFuture<List<Path>> mp3Paths = CompletableFuture.supplyAsync(() -> scanForMp3(musicPath));
            final List<Path> paths = mp3Paths.get();

            state.setStep(Step.READ_MP3);

            final CompletableFuture[] tracks = new CompletableFuture[paths.size()];
            int index = 0;

            for (final Path path : paths) {
                final CompletableFuture<Track> track = CompletableFuture.supplyAsync(() -> read(path));
                tracks[index++] = track;
            }

            CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(tracks);
            combinedFuture.get();

            state.setStep(Step.END);
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
