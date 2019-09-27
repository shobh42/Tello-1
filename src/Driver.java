import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws Exception {
        String ip;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the drone ip address");
        ip = sc.nextLine();
        InetAddress droneIPAddress = InetAddress.getByName(ip);

        System.out.println("Enter the port number");
        String port = sc.nextLine();

        System.out.println("Enter the number of missions");
        String numberOfMission = sc.nextLine();

        InetAddress droneAddress = InetAddress.getByName(ip);
        int dronePort = Integer.parseInt(port);
        DatagramSocket udpClient =  new DatagramSocket();
        udpClient.setSoTimeout(1000);

        //-------- Put drone in command mode ---------

        System.out.println("Put drone in command mode...");

        String request = "command";
        byte[] bytesToSent;
        byte[] bytesReceived;
        DatagramPacket datagramPacket;

        String reply = null;
        int maxRetries = 3;
        while (maxRetries > 0) {

            bytesToSent = request.getBytes(StandardCharsets.UTF_8);
            datagramPacket = new DatagramPacket(bytesToSent, bytesToSent.length, droneAddress, dronePort);
            udpClient.send(datagramPacket);
            System.out.println("Sent " + request + " bytes to " + droneAddress.toString() + ":" + dronePort);

            bytesReceived = new byte[64];
            datagramPacket = new DatagramPacket(bytesReceived, 64);
            try {
                udpClient.receive(datagramPacket);
            }
            catch (SocketTimeoutException ex) {
                datagramPacket = null;
            }
            if (datagramPacket != null) {
                System.out.println(String.format("Received %d bytes", datagramPacket.getLength()));
                reply = new String(bytesReceived, 0, datagramPacket.getLength(), StandardCharsets.UTF_8);
                System.out.println("Receive " + reply);
                if (reply.equals("ok")) {
                    break;
                }
            }
            System.out.println("Remaining retries: " + maxRetries);
            maxRetries--;
        }

        if (reply == null || !reply.equals("ok"))
            return;



    }
}
