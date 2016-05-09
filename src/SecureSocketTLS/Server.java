package SecureSocketTLS;

/**
 *
 * @author nazrul
 */

import java.io.IOException;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;



public class Server {

    public static void main(String[] args) throws IOException { 
        System.out.println("Begin Assignment2 by Nazrul.");
        
        System.setProperty("javax.net.ssl.keyStore", "naz.store");
        System.setProperty("javax.net.ssl.keyStorePassword", "password");
        
        SSLServerSocket SS = (SSLServerSocket) ((SSLServerSocketFactory)SSLServerSocketFactory.getDefault()).createServerSocket(4433);
        System.out.println("Server ready for connection...");
        
        SS.setEnabledProtocols(new String[] {"TLSv1", "TLSv1.1", "TLSv1.2"});
        
        while (true) new ServerThread((SSLSocket) SS.accept()).start();
    }
}
