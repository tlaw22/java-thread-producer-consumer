package tlaw;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        List<Integer> qList = new ArrayList<Integer>();
        
        Thread t1 = new Thread(new Producer(qList));
        Thread t2 = new Thread(new Consumer(qList));
        t1.start();
        t2.start();
    }
}
