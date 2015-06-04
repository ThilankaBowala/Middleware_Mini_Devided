package client_mini;

import java.io.*;
import java.net.*;

/**
 *
 * @author 2012/cs/024, 2012/cs/067, 2012/cs/089, 2012/cs/140, 2012/cs/150
 */
public class ClientApplication1 {

    static int[] integersToSort;
    static Socket connectToServer;
    static DataOutputStream out;
    static BufferedReader in;
    static String msg, methodName, result;

    static void sendRequest() throws IOException {

        connectToServer = new Socket("localhost", 5555);
        System.out.println("ClientApplication 1 : Connected to Client Stub");
        out = new DataOutputStream(connectToServer.getOutputStream());
        in = new BufferedReader(new InputStreamReader(connectToServer.getInputStream()));

        msg = methodName;
        for (int n = 0; n < integersToSort.length; n++) {
            msg = msg + "," + Integer.toString(integersToSort[n]);
        }
        
        System.out.println("ClientApplication 1 : Sending message to Client Stub to pass :");
        out.writeBytes(msg + "\n");
        System.out.println("ClientApplication 1 : Message sent : " + msg);

    }

    static void getResponse() throws IOException {
        result = in.readLine();
        System.out.println("");
        System.out.println("ClientApplication 1 : Got the final sorted output using : " + methodName + " : " + result);
    }

    static void setData() {
        methodName = "bubbleSort";
        integersToSort = new int[]{4, 2, 65, 87, 2, 76};
    }

    
    public static void runClient() {

        setData();

        try {
            sendRequest();
            getResponse();
            connectToServer.close();
        } catch (Exception ex) {
            System.out.println(ex + " client 1");
        }

    }
}