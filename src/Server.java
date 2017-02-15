import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Server implements Runnable {
	int port;
	ServerSocket ss;
	ServerWindow sw;
	PluginLUA plua;
	ArrayList<Socket> connections;
	ArrayList<User> users;
	
	Server(int port, ServerWindow sw) {
		this.port = port;
		this.sw = sw;
	}

	@Override
	public void run() {
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			sw.exitServer(e.getMessage());
		}
		sw.addLogToConsoleBox("LUA eklenti sistemi açýlýyor...", 1);
		plua = new PluginLUA(this);
		
	}
}
