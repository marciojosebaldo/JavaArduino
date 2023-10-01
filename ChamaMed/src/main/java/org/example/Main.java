package org.example;

import com.fazecast.jSerialComm.SerialPort;
import java.util.Timer;

public class Main {
    public static void main(String[] args) {

        long timeStart = System.currentTimeMillis();

        var sp = SerialPort.getCommPort("/dev/ttyUSB0"); //Porta Linux
        sp.setComPortParameters(9600, Byte.SIZE, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING,0,0);

        var hasOpened = sp.openPort();
        if(!hasOpened) {
            throw new IllegalStateException("Erro ao conectar com a porta serial");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {sp.closePort(); }));

        var timer = new Timer();
        var timedSchedule = new TimerScheduleHandler(timeStart);

        sp.addDataListener(timedSchedule);

        System.out.println("Atencao: " + timedSchedule.getListeningEvents());
        timer.schedule(timedSchedule, 0 , 100);
    }
}