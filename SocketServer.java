import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer implements Runnable{
    private int thisServerPort;
    int sum = 0;

    public SocketServer(int iPort){
        thisServerPort = iPort;
    }

    public void run(){

        try(ServerSocket oServerSocket = new ServerSocket (thisServerPort)){

            System.out.println("Server is listening on port" + thisServerPort);

            while(true){

                int sum = 0;

                Socket oSocket = oServerSocket.accept();
                System.out.println("[server]: new client connected" + oSocket.getRemoteSocketAddress());

                InputStream input = oSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                OutputStream output = oSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                // get one time message from client
                String sReceivedMessage = reader.readLine();

                String[] items = sReceivedMessage.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

                String [] tokens = sReceivedMessage.split("[, ]");

                System.out.println("[server]: Server received message: " + sReceivedMessage.split(","));
                // send message back to calling client.
                writer.println("Server received message: " + sReceivedMessage.split(","));

                int[] ints = new int[items.length];


                for (int i = 0; i < items.length; i++) {
                    try {
                        ints[i] = Integer.parseInt(items[i]);
                    } catch (NumberFormatException nfe) {
                    };
                }




                int num = sReceivedMessage.length();
                for (int i=0; i<=num; i++){
                    sum += i;

                }

                System.out.println("Sum is: " + sum);
                writer.flush();



            }
        }

        catch(IOException ex){

            System.out.println("[server]: Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}
