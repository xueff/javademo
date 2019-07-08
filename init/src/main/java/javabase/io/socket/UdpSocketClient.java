package javabase.io.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpSocketClient {
    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");

        byte[] sendData = "Hello".getBytes();
        DatagramPacket packet = new DatagramPacket(sendData,sendData.length,8080);
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(packet);
    }
}
