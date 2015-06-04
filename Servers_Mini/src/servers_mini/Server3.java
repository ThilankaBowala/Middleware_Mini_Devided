package servers_mini;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Thilanka Bowala <Thilanka Bowala at GIGABYTE>
 */
public class Server3 extends Thread {

    static int[] results;
    static ServerSocket socketWithServerSkeleton;
    static Socket connectToServerSkeleton;
    static DataOutputStream outWithServerSkeleton;
    static BufferedReader inWithServerSkeleton;
    static String request, parameterString, msg;

    static void selectionSort(int[] toSort) {
        results = toSort;

        for (int i = 0; i < results.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < results.length; j++) {
                if (results[j] < results[index]) {
                    index = j;
                }
            }

            int smallerNumber = results[index];
            results[index] = results[i];
            results[i] = smallerNumber;
        }
    }

    static void getRequestFromServerSkeleton() throws IOException, InterruptedException {
        connectToServerSkeleton = socketWithServerSkeleton.accept();
        System.out.println("Server 3 : Connected to the server skeleton ");
        outWithServerSkeleton = new DataOutputStream(connectToServerSkeleton.getOutputStream());
        inWithServerSkeleton = new BufferedReader(new InputStreamReader(connectToServerSkeleton.getInputStream()));

        request = inWithServerSkeleton.readLine();
        System.out.println("Server 3 : Got the request from server skeleton :" + request);
    }

    static void sendResponceToServerSkeleton() throws IOException {
        msg = "";
        for (int n = 0; n < results.length; n++) {
            if (n == 0) {
                msg = msg + Integer.toString(results[n]);
            } else {
                msg = msg + "," + Integer.toString(results[n]);
            }

        }
        System.out.println("Server 3 : Message is ready to send to server skeleton :" + msg);
        //message to ServerSkeleton : 
        outWithServerSkeleton.writeBytes(msg + "\n");
    }

    @Override
    public void run() {

        try {
            getRequestFromServerSkeleton();

            parameterString = request;
            int startIndex = parameterString.indexOf(",");
            int endIndex = parameterString.length();
            parameterString = parameterString.substring(startIndex + 1, endIndex);
            System.out.println("Server 3 : Parameters are :" + parameterString);

            String[] items = parameterString.split(",");
            int[] parameter = new int[items.length];

            for (int i = 0; i < items.length; i++) {
                try {
                    parameter[i] = Integer.parseInt(items[i]);
                } catch (NumberFormatException nfe) {
                    System.out.println(nfe);
                }
            }
            System.out.println("Server 3 : Ready to sort an array with length:" + parameter.length);

            selectionSort(parameter);
            sendResponceToServerSkeleton();

        } catch (Exception ex) {
            System.out.println(ex + " server 3");
        }
    }
}