package org.example;

import java.util.TimerTask;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class TimerScheduleHandler extends TimerTask implements SerialPortDataListener {

    private final long timeStart;
    public TimerScheduleHandler(long timeStart){
        this.timeStart = timeStart;
    }

    @Override
    public void run() {
        System.out.println("Tempo percorrido: " + (System.currentTimeMillis() - this.timeStart) + "milissegundos");
    }

    @Override
    public int getListeningEvents(){
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.getEventType() == SerialPort.LISTENING_EVENT_DATA_RECEIVED) {
            System.out.println("Arduino comunicativo. Chamada do leito");
        }
    }
}