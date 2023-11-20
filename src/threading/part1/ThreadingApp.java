package threading.part1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ThreadingApp implements Runnable {
    private List<Integer> theList = new LinkedList<>();
    private final int count = 1000;

    public ThreadingApp() throws InterruptedException {
        Thread thread = new Thread(this);
        thread.start();
        run();
        thread.join();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadingApp threadingApp = new ThreadingApp();
        //System.out.println(threadingApp.theList.size());
    }

    public void addElements(int count) {

        for (int i = 0; i < count; i++) {
            theList.add(i);
            System.out.println(theList.toString());
        }
    }

    @Override
    public void run() {
        addElements(count);
    }
}
