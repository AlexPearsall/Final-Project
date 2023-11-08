package threading.part1;

import java.util.ArrayList;
import java.util.List;

public class ThreadingApp implements Runnable {
    private List<Integer> theList = new ArrayList<>();

    public ThreadingApp() throws InterruptedException {
        int count = 10000;

        Thread thread = new Thread();
        thread.start();
        System.out.println(thread.isAlive());
        run();
        addElements(count);
        thread.join();
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadingApp threadingApp = new ThreadingApp();
    }

    public void addElements(int count) {

        for (int i = 0; i < count; i++) {
            int num = (int) Math.round((Math.random() * 10000));
            theList.add(num);
            System.out.println(theList.toString());
        }
    }

    @Override
    public void run() {
        System.out.println("Thread is running");
    }
}
