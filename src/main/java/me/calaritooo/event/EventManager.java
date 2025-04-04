package me.calaritooo.event;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private static final List<Listener> listeners = new ArrayList<>();

    public static void registerListener(Listener listener) {
        listeners.add(listener);
    }
    public static void onEvent(Object event) {
        for (Listener listener : listeners) {
            listener.handle(event);
        }
    }
}
