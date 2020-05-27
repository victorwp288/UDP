package Client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientHandler {
    public static void main(String[] args) {


    Scanner scan = new Scanner(System.in);
    boolean running = true;
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            while (running){
                System.out.println("Skriv din besked");
                String msg = scan.nextLine();
                byte[] outArray = msg.getBytes();
                InetAddress address = InetAddress.getByName("localhost");
                DatagramPacket outPacket = new DatagramPacket(outArray, outArray.length
                        , address, 6780);
                datagramSocket.send(outPacket);


                byte[] inArr = new byte[1000];
                DatagramPacket inPackage = new DatagramPacket(inArr, inArr.length);

                datagramSocket.receive(inPackage);
                String inMsg = new String(inArr, 0, inPackage.getLength());
                System.out.println(inMsg);
                if (msg.equalsIgnoreCase("exit")){
                    running = false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }


    }
}
