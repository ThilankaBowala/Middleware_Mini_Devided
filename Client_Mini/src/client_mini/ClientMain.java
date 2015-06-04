package client_mini;

/**
 *
 * @author 2012/cs/024, 2012/cs/067, 2012/cs/089, 2012/cs/140, 2012/cs/150
 */
public class ClientMain {
    
    public static void main(String args[]){
        System.out.println("-----------------------Client 1 started working!!!--------------------------------------\n");
        ClientApplication1.runClient();
        System.out.println("\n -------------------------Client 2 started working!!!-----------------------------\n");
        ClientApplication2.runClient();
        System.out.println("\n -------------------------Client 3 started working!!!-----------------------------\n");
        ClientApplication3.runClient();
        System.out.println("\n -------------------------Client 3 started working!!!-----------------------------\n");
        ClientApplication4.runClient();
        System.out.println();
    }
    
}
