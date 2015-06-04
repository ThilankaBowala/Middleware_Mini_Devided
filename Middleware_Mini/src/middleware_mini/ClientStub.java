package middleware_mini;

import java.io.*;
import java.net.*;

/**
 *
 * @author Thilanka Bowala <Thilanka Bowala at GIGABYTE>
 */
public class ClientStub {

    static ServerSocket socketWithClient;
    static Socket connectToClient, connectToServer;
    static DataOutputStream outWithClient;
    static BufferedReader inWithClient, inWithServer;
    static String request, response;

    static void getRequestsFromClient() throws IOException {

        socketWithClient = new ServerSocket(5555);
        connectToClient = socketWithClient.accept();
        System.out.println("Client Stub : Connected to Client");
        outWithClient = new DataOutputStream(connectToClient.getOutputStream());
        inWithClient = new BufferedReader(new InputStreamReader(connectToClient.getInputStream()));

        request = inWithClient.readLine();
        System.out.println("Client Stub : Got request from Client :- " + request);
    }

    static void sendRequestsToServerSkeleton() throws IOException {

        ServerSkeleton.request = request;
        System.out.println("Client Stub : Send request to server skeleton");
    }

    static void getResponseFromServerSkeleton() throws IOException {
        while (response == null) {
        }
        System.out.println("Client Stub : Got response from server :- " + response);
    }

    static void sendResponseToClient() throws IOException {
        outWithClient.writeBytes(response + "\n");
        ClientStub.socketWithClient.close();
    }
}
