package servers_mini;

import java.io.*;
import java.net.*;

/**
 *
 * @author 2012/cs/024, 2012/cs/067, 2012/cs/089, 2012/cs/140, 2012/cs/150
 */
public class Server1 extends Thread {

    static int[] results;
    static ServerSocket socketWithServerSkeleton;
    static Socket connectToServerSkeleton;
    static DataOutputStream outWithServerSkeleton;
    static BufferedReader inWithServerSkeleton;
    static String request, parameterString, msg;

    static void bubbleSort(int[] toSort) {
        results = toSort;
        int length = results.length;
        int swap;

        for (int c = 0; c < length; c++) {
            for (int d = 0; d < length - c - 1; d++) {
                if (results[d] > results[d + 1]) {
                    swap = results[d];
                    results[d] = results[d + 1];
                    results[d + 1] = swap;
                }
                
            }
        }
        System.out.println("Server 1 : Sorting finished!!! ");
    }

    static void getRequestFromServerSkeleton() throws IOException, InterruptedException {
        connectToServerSkeleton = socketWithServerSkeleton.accept();
        System.out.println("Server 1 : Connected to the server skeleton ");
        outWithServerSkeleton = new DataOutputStream(connectToServerSkeleton.getOutputStream());
        inWithServerSkeleton = new BufferedReader(new InputStreamReader(connectToServerSkeleton.getInputStream()));

        request = inWithServerSkeleton.readLine();
        System.out.println("Server 1 : Got the request from server skeleton :" + request);
    }

    static void sendResponceToServerSkeleton() throws IOException {
        msg = "";
        for (int n = 0; n < results.length; n++) {
            if(n==0){
                msg = msg + Integer.toString(results[n]);
            }else{
                msg = msg + "," + Integer.toString(results[n]);
            }
            
        }
        System.out.println("Server 1 : Message is ready to send to server skeleton :" + msg);
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
            parameterString = parameterString.substring(startIndex+1, endIndex); 
            System.out.println("Server 1 : Parameters are :" + parameterString);

            String[] items = parameterString.split(",");
            int[] parameter = new int[items.length];

            for (int i = 0; i < items.length; i++) {
                try {
                    parameter[i] = Integer.parseInt(items[i]);
                } catch (NumberFormatException nfe) {
                    System.out.println(nfe);
                }
            }
            System.out.println("Server 1 : Ready to sort an array with length:" + parameter.length);

            bubbleSort(parameter);
            sendResponceToServerSkeleton();

        } catch (Exception ex) {
            System.out.println(ex + " server 1");
        }
    }
}