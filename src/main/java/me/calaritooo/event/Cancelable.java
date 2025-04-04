package me.calaritooo.event;

public interface Cancelable {
    boolean isCanceled();
    void setCancelled(boolean canceled);
}
