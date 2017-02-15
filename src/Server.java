import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Server {
	int port;
	ServerSocket ss;
	ServerWindow sw;
	ArrayList<Socket> connections;
	ArrayList<User> users;
	
	Server(int port, ServerWindow sw) {
		this.port = port;
		this.sw = sw;
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			sw.exitServer(e.getMessage());
		}
	}
}
