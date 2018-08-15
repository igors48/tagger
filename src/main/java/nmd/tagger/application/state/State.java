package nmd.tagger.application.state;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by igu on 16-Mar-18.
 */
public class State {

    private Step step;
    private int filesCount;
    private int count;
    private List<Path> files;

    public State() {
        step = Step.SCAN_MP3;
        filesCount = 0;
        count = 0;
    }

    public synchronized void incFilesCount() {
        this.filesCount++;
    }

    public synchronized void incCount() {
        this.count++;
    }

    public synchronized Step getStep() {
        return step;
    }

    public synchronized void setStep(Step step) {
        this.step = step;
    }

    public synchronized void scan() {
        step = Step.SCAN_MP3;
    }

    public synchronized void scanCompleted(List<Path> files) {
        step = Step.SCAN_MP3;
        this.files = files;
        filesCount = files.size();
    }

    public synchronized void read(int current) {
        step = Step.READ_MP3;
        count = current;
    }

    public synchronized int getFilesCount() {
        return filesCount;
    }

    public synchronized void verify(int current) {
        step = Step.VERIFY;
        count = current;
    }

    public synchronized int getCount() {
        return count;
    }

    public void scanError(IOException e) {
        step = Step.ERROR;
    }

    public List<Path> getFiles() {
        return files;
    }

}
