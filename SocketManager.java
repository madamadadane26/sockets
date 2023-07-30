import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SocketManager {

    public static void main(String[] args) {

        // get port for this server to listen on.
        System.out.print("Enter port for this server to listen on: ");
        int iThisServerPort = new Scanner(System.in).nextInt();
        SocketServer oServer = new SocketServer(iThisServerPort);

        Thread oServerThread = new Thread(oServer);
        oServerThread.start();

        // get details of server to connect to.
        System.out.println("Enter IP address of server to connect to: ");
        String sOtherServerIP = new Scanner(System.in).nextLine();
        System.out.println("Enter port of server to connect to: ");
        int iOtherServerPort = new Scanner(System.in).nextInt();

        while(true){

            System.out.print("Enter 3 numbers separated by commas to send to server: ");
            String sMessage = new Scanner(System.in).nextLine();
            // instantiate client and call it
            SocketClient oClient = new SocketClient();
            String sReply = oClient.connectForOneMessage(sOtherServerIP, iOtherServerPort, sMessage);
            System.out.println("[client] Remote server reply: " + sReply);


        }




    }
}
