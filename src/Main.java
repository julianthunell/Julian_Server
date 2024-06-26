import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Main {
    //A nivå
    public static void main(String[] args)
            throws IOException {

        // Default port number we are going to use
        int portnumber = 1234;
        if (args.length >= 1) {
            portnumber = Integer.parseInt(args[0]);
        }

        //create a MulticastSocket
        MulticastSocket serverMulticastCocket = new MulticastSocket(1234);

        //Determine the IP adress of host
        InetAddress group = InetAddress.getByName("225.4.5.6");

        serverMulticastCocket.joinGroup(group);
        System.out.println("joinGroup method is called...");

        boolean infinite = true;
        //Continually receives data and prints them
        while (infinite){
            byte buf[] = new byte[1024];
            DatagramPacket data = new DatagramPacket(buf, buf.length);
            serverMulticastCocket.receive(data);
            String msg = new String(data.getData()).trim();

            System.out.println(readMsg(msg));
        }
        serverMulticastCocket.close();

    }
    public static String readMsg(String msg){

        if (msg.indexOf("*") > 0){
            int sum1 = Integer.valueOf(msg.substring(0,msg.indexOf("*")));
            int sum2 = Integer.valueOf(msg.substring(msg.indexOf("*")+1,msg.length()));

            return sum1 +" * "+ sum2+" = " + sum1*sum2;
        }
        if (msg.indexOf("/") > 0){
            int sum1 = Integer.valueOf(msg.substring(0,msg.indexOf("/")));
            int sum2 = Integer.valueOf(msg.substring(msg.indexOf("/")+1,msg.length()));

            return sum1 +" / "+ sum2+" = " + sum1/sum2;
        }
        if (msg.indexOf("+") > 0){
            int sum1 = Integer.valueOf(msg.substring(0,msg.indexOf("+")));
            int sum2 = Integer.valueOf(msg.substring(msg.indexOf("+")+1,msg.length()));

            return sum1 +" + "+ sum2+" = " + (sum1+sum2);
        }

        if (msg.indexOf("-") > 0){
            int sum1 = Integer.valueOf(msg.substring(0,msg.indexOf("-")));
            int sum2 = Integer.valueOf(msg.substring(msg.indexOf("-")+1,msg.length()));

            return sum1 +" - "+ sum2+" = " + (sum1-sum2);
        }
        return "0";
    }
}