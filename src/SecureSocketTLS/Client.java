package SecureSocketTLS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//import java.net.Socket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author nazrul
 */



public class Client {
    
    public static void main(String[] args) throws IOException { 
        
        System.setProperty("javax.net.ssl.trustStore", "naz.store");
        
        SSLSocket socket = (SSLSocket) ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket("localhost",4433);
        
        BufferedReader sbufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter printwriter = new PrintWriter(socket.getOutputStream(),true);
        BufferedReader ibufferedreader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter username:");
        printwriter.println(ibufferedreader.readLine());
        
        
        String message = null;
        
        while (true) {
            System.out.print("Please enter message to sent to the server:");
            message = ibufferedreader.readLine();
            printwriter.println(message);
            System.out.println("Server: " + sbufferedreader.readLine());
            if (message.equals("quit")) {
                socket.close();
                break;
            }
        }
       
    }
    
    
}
