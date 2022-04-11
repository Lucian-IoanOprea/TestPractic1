package ro.pub.cs.systems.myapplication;

public interface Constants {
    final public static String CLICKS = "clicks";
    final public static String INSTRUCTIONS = "instructions";
    final public static String[] actionTypes = {
            "ro.pub.cs.systems.eim.colocviu1.instr"
    };

    final public static int SERVICE_STOPPED = 0;
    final public static int SERVICE_STARTED = 1;

    final public static String PROCESSING_THREAD_TAG = "[Processing Thread]";

    final public static String BROADCAST_RECEIVER_EXTRA = "message";
    final public static String BROADCAST_RECEIVER_TAG = "[Message]";
}
