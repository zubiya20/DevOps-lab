import java.io.*;
import java.net.*;
class UDPServer
{
 public static void main(String args[]) throws Exception
 {
DatagramSocket serverSocket = new DatagramSocket(9876);
byte[] receiveData = new byte[128];
byte[] sendData = new byte[128];
String rev;
while(true)
{

DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
serverSocket.receive(receivePacket);
String sentence = new String(receivePacket.getData());
InetAddress IPAddress = receivePacket.getAddress();
int port = receivePacket.getPort();
String sentenceTrim = sentence.trim();
System.out.println("RECEIVED: " + sentenceTrim);
for ( int i = sentenceTrim.length() - 1; i >= 0; i-- ){
rev = rev + sentenceTrim.charAt(i);
}
if (sentenceTrim.equals(rev)){
sentence = (sentenceTrim +" is a palindrome" + "\n");
sendData = sentence.getBytes();
DatagramPacket sendPacket =
new DatagramPacket(sendData, sendData.length, IPAddress, port);
serverSocket.send(sendPacket);
}
else{
sentence = (sentenceTrim +" is not a palindrome" + "\n");
sendData = sentence.getBytes();
DatagramPacket sendPacket =
new DatagramPacket(sendData, sendData.length, IPAddress, port);
serverSocket.send(sendPacket);
}
}
}
}
