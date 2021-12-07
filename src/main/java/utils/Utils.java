package utils;

import java.util.Random;

public class Utils {

    public void waitByMilliSeconds(long milliSeconds){

        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitBySeconds(long seconds){

        waitByMilliSeconds(seconds*1000);
    }

    public int generateRandom(){

        int maximum=30;
        int randomCount = new Random().nextInt((maximum - 2 ) + 1) + 2;
        System.out.println("-------------------"+randomCount+"-------------------------------");
        return randomCount;
    }
}
