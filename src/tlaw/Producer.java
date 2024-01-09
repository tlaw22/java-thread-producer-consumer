package tlaw;
import java.util.List;

public class Producer implements Runnable {

    List<Integer> qList = null;
    final int LIMIT = 5;
    private int questionNo;

    public Producer(List<Integer> qList) {
        this.qList = qList;

    }

    public void readQuestions(int questionNo) throws InterruptedException {
        synchronized (qList) {

            while (qList.size() == LIMIT) {
                System.out.println("Questions have piled up...");
                qList.wait();
            }
        }
        synchronized (qList) {
            System.out.println("New Question: " + questionNo);
            qList.add(questionNo);
            Thread.sleep(100);
            qList.notify();
        }

    }

    @Override
    public void run() {
        while(true) {
            try {
                readQuestions(questionNo++);
            } catch (IllegalMonitorStateException e) {
                
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

}
