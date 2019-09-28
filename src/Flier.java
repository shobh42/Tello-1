import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Flier {

    private DatagramSocket udpClient;
    private InetAddress droneAddress;
    private int dronePort;
    private int numberOfMission;

    public Flier(String ip, String port, String mission) throws SocketException, UnknownHostException {
        udpClient = new DatagramSocket();
        droneAddress = InetAddress.getByName(ip);
        dronePort = Integer.parseInt(port);
        numberOfMission = Integer.parseInt(mission);
        setupUDPClient();
    }

    private void setupUDPClient() throws SocketException {
        udpClient.setSoTimeout(1000);
    }

    public boolean executeCommande(String message) throws IOException {
        String request = message;
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
            return false;

        return true;
    }
}
