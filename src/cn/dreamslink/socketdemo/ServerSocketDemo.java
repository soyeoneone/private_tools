package cn.dreamslink.socketdemo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;


public class ServerSocketDemo {
	public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(6120);
        Socket socket = server.accept();
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        while(true){
        	String str = bufferReader.readLine();
        	System.out.println("~~~~" + str);
        	pw.println("has receive^");
        	pw.flush();
        	if ("end".equals(str)){
        		break;
        	}
        	socket.close();
        }
	}
}
