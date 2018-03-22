package command;

import nmd.tagger.application.command.ScanForMp3Files;
import nmd.tagger.application.state.State;
import nmd.tagger.operations.DirectoryOperations;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ScanForMp3FilesTest {

    private static final String PATH = "path";

    @Test
    public void happyFlow() throws IOException {
        DirectoryOperations directoryOperations = Mockito.mock(DirectoryOperations.class);
        List<Path> list = createPathList();
        Mockito.when(directoryOperations.scan(PATH)).thenReturn(list);

        State state = Mockito.mock(State.class);

        ScanForMp3Files command = new ScanForMp3Files(PATH, directoryOperations, state);
        command.execute();

        Mockito.verify(state).scan();
        Mockito.verify(directoryOperations).scan(PATH);
        Mockito.verify(state).scanCompleted(list);
    }

    @Test
    public void whenError() throws IOException {
        DirectoryOperations directoryOperations = Mockito.mock(DirectoryOperations.class);
        List<Path> list = createPathList();
        final IOException exception = new IOException();
        Mockito.when(directoryOperations.scan(PATH)).thenThrow(exception);

        State state = Mockito.mock(State.class);

        ScanForMp3Files command = new ScanForMp3Files(PATH, directoryOperations, state);
        command.execute();

        Mockito.verify(state).scan();
        Mockito.verify(directoryOperations).scan(PATH);
        Mockito.verify(state).scanError(exception);
    }

    private List<Path> createPathList() {
        return Arrays.asList(new Path() {
            @Override
            public FileSystem getFileSystem() {
                return null;
            }

            @Override
            public boolean isAbsolute() {
                return false;
            }

            @Override
            public Path getRoot() {
                return null;
            }

            @Override
            public Path getFileName() {
                return null;
            }

            @Override
            public Path getParent() {
                return null;
            }

            @Override
            public int getNameCount() {
                return 0;
            }

            @Override
            public Path getName(int index) {
                return null;
            }

            @Override
            public Path subpath(int beginIndex, int endIndex) {
                return null;
            }

            @Override
            public boolean startsWith(Path other) {
                return false;
            }

            @Override
            public boolean startsWith(String other) {
                return false;
            }

            @Override
            public boolean endsWith(Path other) {
                return false;
            }

            @Override
            public boolean endsWith(String other) {
                return false;
            }

            @Override
            public Path normalize() {
                return null;
            }

            @Override
            public Path resolve(Path other) {
                return null;
            }

            @Override
            public Path resolve(String other) {
                return null;
            }

            @Override
            public Path resolveSibling(Path other) {
                return null;
            }

            @Override
            public Path resolveSibling(String other) {
                return null;
            }

            @Override
            public Path relativize(Path other) {
                return null;
            }

            @Override
            public URI toUri() {
                return null;
            }

            @Override
            public Path toAbsolutePath() {
                return null;
            }

            @Override
            public Path toRealPath(LinkOption... options) throws IOException {
                return null;
            }

            @Override
            public File toFile() {
                return null;
            }

            @Override
            public WatchKey register(WatchService watcher, WatchEvent.Kind<?>[] events, WatchEvent.Modifier... modifiers) throws IOException {
                return null;
            }

            @Override
            public WatchKey register(WatchService watcher, WatchEvent.Kind<?>... events) throws IOException {
                return null;
            }

            @Override
            public Iterator<Path> iterator() {
                return null;
            }

            @Override
            public int compareTo(Path other) {
                return 0;
            }
        });
    }

}
