package servers_mini;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2012/cs/024, 2012/cs/067, 2012/cs/089, 2012/cs/140, 2012/cs/150
 */
public class Servers_Mini {

    public static void main(String[] args) {
        try {
            Server1.socketWithServerSkeleton = new ServerSocket(60001);
            Server2.socketWithServerSkeleton = new ServerSocket(60002);
            Server3.socketWithServerSkeleton = new ServerSocket(60003);
            Server4.socketWithServerSkeleton = new ServerSocket(60004);

            Server1 s1 = new Server1();
            Server2 s2 = new Server2();
            Server3 s3 = new Server3();
            Server4 s4 = new Server4();

            s1.setPriority(1);
            s2.setPriority(1);
            s2.setPriority(1);
            s2.setPriority(1);

            s1.start();
            s2.start();
            s3.start();
            s4.start();
        } catch (IOException ex) {
            Logger.getLogger(Servers_Mini.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
