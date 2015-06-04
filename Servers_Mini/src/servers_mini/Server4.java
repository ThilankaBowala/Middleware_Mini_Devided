package servers_mini;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Thilanka Bowala <Thilanka Bowala at GIGABYTE>
 */
public class Server4 extends Thread {

    static int[] results;
    static ServerSocket socketWithServerSkeleton;
    static Socket connectToServerSkeleton;
    static DataOutputStream outWithServerSkeleton;
    static BufferedReader inWithServerSkeleton;
    static String request, parameterString, msg;

    public static void quickSort(int[] toSort) {
        results = toSort;
        int left = 0;
        int right = results.length - 1;
        sort(left, right);
    }

    private static void sort(int left, int right) {
        if (left >= right) {
            return;
        }
        double pivot = results[right];
        int partition = partition(left, right, pivot);
        sort(0, partition - 1);
        sort(partition + 1, right);
    }

    private static int partition(int left, int right, double pivot) {
        int leftCursor = left - 1;
        int rightCursor = right;
        while (leftCursor < rightCursor) {
            while (results[++leftCursor] < pivot);
            while (rightCursor > 0 && results[--rightCursor] > pivot);
            if (leftCursor >= rightCursor) {
                break;
            } else {
                swap(leftCursor, rightCursor);
            }
        }
        swap(leftCursor, right);
        return leftCursor;
    }

    private static void swap(int left, int right) {
        int temp = results[left];
        results[left] = results[right];
        results[right] = temp;
    }

    static void getRequestFromServerSkeleton() throws IOException, InterruptedException {
        connectToServerSkeleton = socketWithServerSkeleton.accept();
        System.out.println("Server 4 : Connected to the server skeleton ");
        outWithServerSkeleton = new DataOutputStream(connectToServerSkeleton.getOutputStream());
        inWithServerSkeleton = new BufferedReader(new InputStreamReader(connectToServerSkeleton.getInputStream()));

        request = inWithServerSkeleton.readLine();
        System.out.println("Server 4 : Got the request from server skeleton :" + request);
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
        System.out.println("Server 4 : Message is ready to send to server skeleton :" + msg);
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
            System.out.println("Server 4 : Parameters are :" + parameterString);

            String[] items = parameterString.split(",");
            int[] parameter = new int[items.length];

            for (int i = 0; i < items.length; i++) {
                try {
                    parameter[i] = Integer.parseInt(items[i]);
                } catch (NumberFormatException nfe) {
                    System.out.println(nfe);
                }
            }
            System.out.println("Server 4 : Ready to sort an array with length:" + parameter.length);

            quickSort(parameter);
            sendResponceToServerSkeleton();

        } catch (Exception ex) {
            System.out.println(ex + " server 4");
        }
    }
}
