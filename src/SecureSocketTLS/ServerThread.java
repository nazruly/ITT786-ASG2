package SecureSocketTLS;

/**
 *
 * @author nazrul
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;

public class ServerThread extends Thread {
    SSLSocket socket;

    ServerThread(SSLSocket socket) {
        this.socket = socket;
    }
    
    public void run() {
        try {
            PrintWriter printwriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String sessionuser = bufferedreader.readLine();
            System.out.println("User '"+ sessionuser + "' is now connected to the server.");
            while (true) {
                String message = bufferedreader.readLine();
                printwriter.println("Message received");
                System.out.println(sessionuser + ":" + message);
                if (message.equals("quit")) {
                    System.out.println("Session '" + sessionuser + "' closed.");
                    socket.close();
                    break;
                }
            
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
