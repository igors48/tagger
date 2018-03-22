package nmd.tagger.application.command;

import nmd.tagger.application.state.State;
import nmd.tagger.operations.DirectoryOperations;

public class CommandFactory {

    private final DirectoryOperations directoryOperations;
    private final State state;

    public CommandFactory(DirectoryOperations directoryOperations, State state) {
        this.directoryOperations = directoryOperations;
        this.state = state;
    }

    public ScanForMp3Files scanForMp3Files(String path) {
        return new ScanForMp3Files(path, directoryOperations, state);
    }

}
