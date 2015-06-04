package middleware_mini;

import java.io.*;
import java.net.*;

/**
 *
 * @author Thilanka Bowala <Thilanka Bowala at GIGABYTE>
 */
public class ServerSkeleton extends Thread {

    static String methodName;
    static ServerSocket socketWithClient;
    static Socket connectToClient, connectToServer;
    static DataOutputStream outWithServer;
    static BufferedReader inWithClient, inWithServer;
    static String request, response;
    static int portNumber;

    static void getRequestFromClientStub() throws IOException {
        while (request == null) {
        }
        System.out.println("Server Skeleton : Got request from Client Stub :- " + request);
    }

    static void setPortNumber() {

        if (request.contains("bubbleSort")) {
            methodName = "bubbleSort";
        } else if (request.contains("insertionSort")) {
            methodName = "insertionSort";
        } else if (request.contains("selectionSort")) {
            methodName = "selectionSort";
        } else if (request.contains("quickSort")) {
            methodName = "quickSort";
        }
        portNumber = Integer.parseInt(BusinessLogicLayer.readXML(methodName));
        System.out.println("Server Skeleton : Set port number : " + portNumber);
    }

    static void sendRequestToServer() throws IOException {
        connectToServer = new Socket("localhost", portNumber);
        System.out.println("Server Skeleton : Connected to the server ");
        inWithServer = new BufferedReader(new InputStreamReader(connectToServer.getInputStream()));
        outWithServer = new DataOutputStream(connectToServer.getOutputStream());

        //message to Server :
        outWithServer.writeBytes(request + "\n");
        System.out.println("Server Skeleton : Sent request to the server :" + request);
    }

    static void getResponseFromServer() throws IOException {
        response = inWithServer.readLine();
        System.out.println("Server Skeleton : Got response from server :" + response);
    }

    static void sendResponceToClientStub() throws IOException {
        //message to ClientStub : 
        ClientStub.response = response;     //marshalling and send
    }
}
