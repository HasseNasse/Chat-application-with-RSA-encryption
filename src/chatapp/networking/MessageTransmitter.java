package chatapp.networking;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HassanNazar
 */
public class MessageTransmitter extends Thread{
    String message, hostname;
    int port;
    
    public MessageTransmitter(String message, String hostname, int port){
        this.message = message;
        this.hostname = hostname;
        this.port = port;
    }
    
    public MessageTransmitter(){
        
    }
    
    @Override
    public void run(){
        try {
            Socket s = new Socket(hostname, port); //Connect to a serversocket.
            s.getOutputStream().write(message.getBytes());
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(MessageTransmitter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
