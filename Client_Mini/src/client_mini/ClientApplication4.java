package client_mini;

import java.io.*;
import java.net.*;

/**
 *
 * @author Thilanka Bowala <Thilanka Bowala at GIGABYTE>
 */
public class ClientApplication4 {
    
    static int[] integersToSort;
    static Socket connectToServer;
    static DataOutputStream out;
    static BufferedReader in;
    static String msg, methodName, result;

    static void sendRequest() throws IOException {

        connectToServer = new Socket("localhost", 5555);
        System.out.println("ClientApplication 4 : Connected to Client Stub");
        out = new DataOutputStream(connectToServer.getOutputStream());
        in = new BufferedReader(new InputStreamReader(connectToServer.getInputStream()));

        msg = methodName;
        for (int n = 0; n < integersToSort.length; n++) {
            msg = msg + "," + Integer.toString(integersToSort[n]);
        }
        
        System.out.println("ClientApplication 4 : Sending message to Client Stub to pass :");
        out.writeBytes(msg + "\n");
        System.out.println("ClientApplication 4 : Message sent : " + msg);

    }

    static void getResponse() throws IOException {
        result = in.readLine();
        System.out.println("");
        System.out.println("ClientApplication 4 : Got the final sorted output using : " + methodName + " : " + result);
    }

    static void setData() {
        methodName = "quickSort";
        integersToSort = new int[]{4, 2, 65, 87, 2, 76};
    }

    
    public static void runClient() {

        setData();

        try {
            sendRequest();
            getResponse();
            connectToServer.close();
        } catch (Exception ex) {
            System.out.println(ex + " client 4");
        }

    }
}
