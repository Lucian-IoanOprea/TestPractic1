package ro.pub.cs.systems.myapplication;


import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.util.Date;
import java.util.Random;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();
    String instruct;
    public ProcessingThread(Context context, String instr) {
        this.context = context;
        instruct = instr;
    }

    @Override
    public void run() {
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid());
        try {
            Thread.sleep(5000);
            sendMessage();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.actionTypes[0]);
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA,
                new Date(System.currentTimeMillis()) + " " + instruct);
        context.sendBroadcast(intent);
    }

    public void stopThread() {
        isRunning = false;
    }
}