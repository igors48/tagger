package nmd.tagger.operations;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface DirectoryOperations {

    List<Path> scan(String path) throws IOException;

}
