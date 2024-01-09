package tlaw;
import java.util.List;

public class Consumer implements Runnable {

    List<Integer> qList = null;

    public Consumer(List<Integer> qList) {
        this.qList = qList;

    }

    public void answerQuestion() throws InterruptedException {
        synchronized (qList) {

            while (qList.isEmpty()) {
                System.out.println("The question list is empty...");
                qList.wait();
            }
        }
        synchronized (qList) {
            Thread.sleep(1500);
            System.out.println("Answered Question: " + qList.remove(0));
    
            Thread.sleep(100);
            qList.notify();
        }

    }

    @Override
    public void run() {
        while(true) {
            try {
                answerQuestion();
            } catch (IllegalMonitorStateException e) {
                
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

}
