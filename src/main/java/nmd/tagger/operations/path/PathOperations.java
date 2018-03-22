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

    @Override
    public List<Path> scan(String path) throws IOException {
        return Files.walk(Paths.get(path))
                .filter(p -> p.toString().toLowerCase().endsWith(MP3_EXTENSION))
                .collect(Collectors.toList());

    }

}
