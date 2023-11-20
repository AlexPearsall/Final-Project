package threading.part2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ThreadingApp {
    private List<Integer> theList = new LinkedList<>();
    private final int count = 10_000;

    public ThreadingApp() throws InterruptedException {
        Thread thread = new Thread(() -> addElements(count));
        thread.start();
        addElements(count);
        thread.join();
        System.out.println(theList.size());
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadingApp threadingApp = new ThreadingApp();
    }

    public void addElements(int count) {
        //System.out.println("Working on thread");

        for (int i = 0; i < count; i++) {
            theList.add(i);
        }
    }
}
