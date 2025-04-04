package me.calaritooo.util;

public class MessageBus {
    private static MessageReceiver receiver;

    public static void set(MessageReceiver messageReceiver) {
        receiver = messageReceiver;
    }

    public static MessageReceiver get() {
        return receiver;
    }

    public static void send (String message) {
        if (receiver != null) {
            receiver.send(message);
        } else {
            System.err.println("[ERROR] MessageBus produced an error while outputting: " + message);
        }
    }
}
