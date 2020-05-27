package Server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class ServerHandler {
    public static void main(String[] args) {
        boolean running = true;
        try {
            DatagramSocket datagramSocket = new DatagramSocket(6780);
            byte[] recieveArr = new byte[1000];

            DatagramPacket recievePackage = new DatagramPacket(recieveArr, 0);
            byte[] recieveArray = new byte[1000];

            Scanner scan = new Scanner(System.in);
            while (running){
                System.out.println("venter på modtaget besked");
                DatagramPacket recievePacket = new DatagramPacket(recieveArray, recieveArray.length);
                datagramSocket.receive(recievePacket);
                System.out.println("Modtaget pakke");
                String inMsg = new String(recieveArray, 0, recievePacket.getLength());
                System.out.println(inMsg);


                System.out.println("Skriv din besked: ");
                String msg = scan.nextLine();
                byte[] outArr = msg.getBytes();
                DatagramPacket outPackage = new DatagramPacket(outArr, outArr.length, recievePacket.getAddress(), recievePacket.getPort());
                datagramSocket.send(outPackage);

                if (msg.equalsIgnoreCase("exit")){
                    running = false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}
