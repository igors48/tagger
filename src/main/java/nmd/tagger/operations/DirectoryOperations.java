package nmd.tagger.operations;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface DirectoryOperations {

    List<Path> scanForMp3(String path) throws IOException;

    List<Path> scanForJpg(String path) throws IOException;

}
