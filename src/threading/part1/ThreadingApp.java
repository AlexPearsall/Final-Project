package threading.part1;

import java.util.List;

public class ThreadingApp {
    private List<Integer> theList;

    public ThreadingApp() {
        Thread thread = new Thread();
        thread.start();
        addElements();

    }
    public static void main(String[] args) {

        ThreadingApp threadingApp = new ThreadingApp();



    }

    public void addElements() {

    }

}
