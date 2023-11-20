package threading.part1;

import java.util.ArrayList;
import java.util.List;

public class ThreadingApp {
    private List<Integer> theList = new ArrayList<>();
    private final int count = 29;

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
            System.out.println(theList.toString());
        }
    }
}
