package nmd.tagger.operations.path;

import nmd.tagger.operations.DirectoryOperations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PathOperations implements DirectoryOperations {

    private static final String MP3_EXTENSION = ".mp3";
    private static final String JPG_EXTENSION = ".jpg";

    @Override
    public List<Path> scanForMp3(String path) throws IOException {
        return Files.walk(Paths.get(path))
                .filter(p -> p.toString().toLowerCase().endsWith(MP3_EXTENSION))
                .collect(Collectors.toList());
    }

    @Override
    public List<Path> scanForJpg(String path) throws IOException {
        return Files.walk(Paths.get(path))
                .filter(p -> p.toString().toLowerCase().endsWith(JPG_EXTENSION))
                .collect(Collectors.toList());
    }

}
