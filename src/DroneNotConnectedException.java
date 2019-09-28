import java.net.InetAddress;

public class DroneNotConnectedException extends Exception {

    public DroneNotConnectedException(InetAddress ip, int port){
        super("There is an error connecting to drone at: " + ip.toString() + ":" + port );
    }
}
