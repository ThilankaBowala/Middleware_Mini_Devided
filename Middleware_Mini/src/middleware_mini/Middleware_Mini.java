package middleware_mini;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2012/cs/024, 2012/cs/067, 2012/cs/089, 2012/cs/140, 2012/cs/150
 */
public class Middleware_Mini {

    public static void main(String[] args) {
        try {
            
            ClientStub.getRequestsFromClient();
            ClientStub.sendRequestsToServerSkeleton();
            ServerSkeleton.getRequestFromClientStub();
            ServerSkeleton.setPortNumber();
            ServerSkeleton.sendRequestToServer();
            ServerSkeleton.getResponseFromServer();
            ServerSkeleton.sendResponceToClientStub();
            ClientStub.getResponseFromServerSkeleton();
            ClientStub.sendResponseToClient();
            
            System.out.println("\n -------------------------------------------------------------------------------\n");
            
            ClientStub.getRequestsFromClient();
            ClientStub.sendRequestsToServerSkeleton();
            ServerSkeleton.getRequestFromClientStub();
            ServerSkeleton.setPortNumber();
            ServerSkeleton.sendRequestToServer();
            ServerSkeleton.getResponseFromServer();
            ServerSkeleton.sendResponceToClientStub();
            ClientStub.getResponseFromServerSkeleton();
            ClientStub.sendResponseToClient();
            
            System.out.println("\n -------------------------------------------------------------------------------\n");
            
            ClientStub.getRequestsFromClient();
            ClientStub.sendRequestsToServerSkeleton();
            ServerSkeleton.getRequestFromClientStub();
            ServerSkeleton.setPortNumber();
            ServerSkeleton.sendRequestToServer();
            ServerSkeleton.getResponseFromServer();
            ServerSkeleton.sendResponceToClientStub();
            ClientStub.getResponseFromServerSkeleton();
            ClientStub.sendResponseToClient();
            
            System.out.println("\n -------------------------------------------------------------------------------\n");
            
            ClientStub.getRequestsFromClient();
            ClientStub.sendRequestsToServerSkeleton();
            ServerSkeleton.getRequestFromClientStub();
            ServerSkeleton.setPortNumber();
            ServerSkeleton.sendRequestToServer();
            ServerSkeleton.getResponseFromServer();
            ServerSkeleton.sendResponceToClientStub();
            ClientStub.getResponseFromServerSkeleton();
            ClientStub.sendResponseToClient();
            
        } catch (IOException ex) {
            Logger.getLogger(Middleware_Mini.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
