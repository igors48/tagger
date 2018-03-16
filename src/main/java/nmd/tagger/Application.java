package nmd.tagger;

import nmd.tagger.application.State;
import nmd.tagger.application.Step;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, InterruptedException {
        final State state = new State();

        final Main main = new Main(state);
        final Console console = new Console(state);

        Thread consoleThread = new Thread(console);
        consoleThread.start();

        Thread mainThread = new Thread(main);
        mainThread.start();

        while (!state.getStep().equals(Step.END)) {
            Thread.sleep(500);
        }

    }

}
