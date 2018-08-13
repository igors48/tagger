package nmd.tagger.application.command;

import nmd.tagger.application.state.State;
import nmd.tagger.operations.DirectoryOperations;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ScanForJpgFiles implements Command {

    private final String path;
    private final DirectoryOperations operations;
    private final State state;

    public ScanForJpgFiles(String path, DirectoryOperations operations, State state) {
        this.path = path;
        this.operations = operations;
        this.state = state;
    }

    @Override
    public void execute() {
        try {
            state.scan();
            final List<Path> files = operations.scanForJpg(path);
            state.scanCompleted(files);
        } catch (IOException e) {
            state.scanError(e);
        }
    }

}
