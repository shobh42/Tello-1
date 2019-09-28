import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the drone ip address");
        String ip = sc.nextLine();

        System.out.println("Enter the port number");
        String port = sc.nextLine();

        System.out.println("Enter the number of missions");
        String numberOfMission = sc.nextLine();

        Flier flier = new Flier(ip, port, numberOfMission);

        //-------- Put drone in command mode ---------

        System.out.println("Connecting to drone at " + ip + ":" + port);







    }
}
