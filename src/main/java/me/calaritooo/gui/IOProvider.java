package me.calaritooo.gui;

public class IOProvider {
    private static SimulatorIO io;

    public static void set(SimulatorIO simulatorIO) {
        io = simulatorIO;
    }

    public static SimulatorIO get() {
        return io;
    }

    public static void send (String message) {
        if (io != null) {
            io.send(message);
        } else {
            System.out.println("[ERROR] SimulatorIO not registered]");
        }
    }
}
