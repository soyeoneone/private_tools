package cn.dreamslink.socketdemo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientSocketDemo {
    public static void main(String[] args) throws UnknownHostException, IOException {
		Socket server = new Socket(InetAddress.getLocalHost(), 6120);
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintWriter pw = new PrintWriter(server.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
        String str = reader.readLine();
        pw.println("---" + str);
        pw.flush();
        if("end".equals(str)) {
        	break;
        }
        System.out.println("==" + bufferReader.readLine());
        }
        server.close();
	}
}
