/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import chatapp.gui.mainChat;
/**
 *
 * @author HassanNazar
 */
public class MessageListener extends Thread
{
    //RSA variables
    boolean keyRecieved = false;
    BigInteger publicKey;
    BigInteger modulus;
    
    //Other variables
    ServerSocket server;
    int Recport = 8877;
    String hostname;
    int Tarport;
    WriteableGUI gui;
    public MessageListener(WriteableGUI gui, int Recport, String hostname, int Tarport)
    {
        this.gui = gui;
        this.Recport = Recport;
        this.hostname = hostname;
        this.Tarport = Tarport;
        
        try {
            server = new ServerSocket(Recport);
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public MessageListener()
    {
        try {
            server = new ServerSocket(Recport);
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        Socket clientSocket; //Connection to a port
        try {
            while ((clientSocket = server.accept()) != null) { //Keeps accepting connection to the server
                InputStream is = clientSocket.getInputStream();
                if (keyRecieved == true) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line = br.readLine();
                    BigInteger encryptedMessage = new BigInteger(line);
                    BigInteger decryption = mainChat.rsa.decrypt(encryptedMessage);
                    String decryptedMsg = new String(decryption.toByteArray());
                    if (line != null) {
                        gui.write("Encrypted message recieved: '" + encryptedMessage + "'.. Decrypting");
                        gui.write("Him -->" + decryptedMsg);
                    }
                } else {
                    // Get the data!!!
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line = br.readLine();

                    //Tear apart the public key and modulus
                    String[] parts = line.split("-");
                    this.publicKey = new BigInteger(parts[0].toString());
                    this.modulus = new BigInteger(parts[1].toString());
                    
                    mainChat.rsa.SetHisPK(this.publicKey, this.modulus);
                    
                    gui.write("Recipients Public Key: " + this.publicKey.toString());
                    gui.write("Recipients Modulus: " + this.modulus.toString());
                    keyRecieved = true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
