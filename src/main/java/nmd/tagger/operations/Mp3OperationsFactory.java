package nmd.tagger.operations;

import java.nio.file.Path;

/**
 * Created by igu on 28-Feb-18.
 */
public interface Mp3OperationsFactory {

    Mp3Operations create(Path path) throws Mp3OperationsFactoryException;

}
