import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int maxRetry = 3;
        boolean isConnected = false;
        Flier flier = null;

        while(maxRetry != 0){
            try{
                System.out.println("Enter the drone ip address");
                String ip = sc.nextLine();

                System.out.println("Enter the port number");
                String port = sc.nextLine();

                System.out.println("Enter the number of missions");
                String numberOfMission = sc.nextLine();
                flier = new Flier(ip, port, numberOfMission);

                System.out.println("Connecting to drone at " + ip + ":" + port);
                //-------- Put drone in command mode ---------
                isConnected = flier.executeCommand("command");
                System.out.println("Connected to drone");
                break;
            }catch (Exception ex){
                System.out.println("Not connected: " + ex.getMessage());
            }

            System.out.println("Number of Retry left: " + --maxRetry);
            System.out.println("Do you want to continue: Y/N");
            String response = sc.nextLine();
            if(response.equals("n") || response.equals("N") ){
                break;
            }
        }


        while(isConnected){
            System.out.println("Enter the message");
            String message = sc.nextLine();

            switch (message){
                case "command":
                    flier.executeCommand(message);
                    break;

                case "takeoff":
                    flier.executeCommand(message);
                    break;

                case "land":
                    flier.executeCommand(message);
                    break;

                case "up":
                    flier.executeCommand(message);
                    break;

                case "left":
                    flier.executeCommand(message);
                    break;

                case "right":
                    flier.executeCommand(message);
                    break;

                case "forward":
                    flier.executeCommand(message);
                    break;

                case "back":
                    flier.executeCommand(message);
                    break;

                case "cw":
                    flier.executeCommand(message);
                    break;

                case "cww":
                    flier.executeCommand(message);
                    break;

                case "flip":
                    flier.executeCommand(message);
                    break;

                case "go":
                    flier.executeCommand(message);
                    break;

                case "stop":
                    flier.executeCommand(message);
                    break;

                case "speed":
                    flier.executeCommand(message);
                    break;

                case "battery":
                    flier.executeCommand(message);
                    break;

                case "time":
                    flier.executeCommand(message);
                    break;

                default:
                    System.out.println("Unknown Message");

            }

            try{
                flier.executeCommand(message);
            }catch (Exception e){
                isConnected = false;
                System.out.println(e.getMessage());
            }
        }
    }
}
