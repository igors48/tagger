package nmd.tagger.operations.mp3agic;

import com.mpatric.mp3agic.Mp3File;
import nmd.tagger.operations.Mp3Operations;
import nmd.tagger.operations.Mp3OperationsFactory;
import nmd.tagger.operations.Mp3OperationsFactoryException;

import java.nio.file.Path;

/**
 * Created by igu on 28-Feb-18.
 */
public class Mp3agicOperationsFactory implements Mp3OperationsFactory {

    @Override
    public Mp3Operations create(Path path) throws Mp3OperationsFactoryException {
        try {
            Mp3File mp3File = new Mp3File(path);

            return new Mp3agicOperations(mp3File);
        } catch (Exception e) {
            throw new Mp3OperationsFactoryException(e);
        }
    }

}
