import java.net.InetAddress;

public class DroneNotConnectedException extends Exception {

    public DroneNotConnectedException(InetAddress ip, int port){
        super("There is connection issue with the drone at: " + ip.toString() + ":" + port );
    }
}
