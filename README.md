# Chat-application-with-RSA-encryption

Can be tested by opening two instances of the program either by executing them through the IDE or
downloading the .jar file from the ".jar files" folder. Use localhost as the IP and some socket.
Note: The personal sockets must be different for each instance of the app, and each reciever socket
should be pointing to the other instance. 

For instance:
App1: Private socket: 8877, IP: localhost, Reciever Socket: 8878
App2: Private socket: 8878, IP: localhost, Reciever Socket: 8877

After this you press the listen button on each chatapp to start listening for messages. 
The first message that you send will actually not send the message itself, rather it 
will send your RSA public key to the recipient.

After that you are free to use:)

![alt tag](https://github.com/HasseNasse/Chat-application-with-RSA-encryption/blob/master/.jar%20files/demo.png)
