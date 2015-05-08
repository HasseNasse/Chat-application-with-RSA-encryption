# Chat-application-with-RSA-encryption

The app can be tested by opening two instances of the program either by executing them through the IDE or downloading the .jar file from the ".jar files" folder in GitHub. In the IP section Use localhost as the IP for both the instances and some socket. Note: The personal sockets must be different for each instance of the app, and each receiver socket should be pointing to the other instance.

For instance:App1: Private socket: 8877, IP: localhost, Reciever Socket: 8878 App2: Private socket: 8878, IP: localhost, Reciever Socket: 8877

After this you press the listen button on each chat-app to start listening for messages. The first message that you send will actually not send by clicking on the send-button will send the public keys to each recipient. After this, you are free to start typing safely with RSA encryption of your messages.


# Demonstration of the application:
![alt tag](https://github.com/HasseNasse/Chat-application-with-RSA-encryption/blob/master/.jar%20files/demo.png)
