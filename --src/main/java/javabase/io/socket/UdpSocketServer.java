package javabase.io.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpSocketServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(8080);

        byte[] receiveData = new byte[1024];
        DatagramPacket packet = new DatagramPacket(receiveData,receiveData.length);

        datagramSocket.receive(packet);
        System.out.print(new String(receiveData,0,packet.getLength()));
    }
}
