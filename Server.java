package testSocketAPI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	ServerSocket listener;
	//String line;
	BufferedReader reader;
	BufferedWriter writer;
	Socket socketOfServer;
	
	
	public Server() {
		super();
		try {
			this.listener = new ServerSocket(9999);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("Server is waiting to accept user...");
			this.socketOfServer = listener.accept();
			System.out.println("Accept a client!");
			this.reader = new BufferedReader(new InputStreamReader(this.socketOfServer.getInputStream()));
			this.writer = new BufferedWriter(new OutputStreamWriter(this.socketOfServer.getOutputStream()));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void run()
	{
		while(true)
		{
			try {
				this.reader = new BufferedReader(new InputStreamReader(this.socketOfServer.getInputStream()));
				this.writer = new BufferedWriter(new OutputStreamWriter(this.socketOfServer.getOutputStream()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				String line;
				line = reader.readLine();

				
				if(line.equals("q"))
				{
					writer.write(">>OK");
					writer.newLine();
					writer.flush();
					break;
				}
				else if(line.equals("Hello Server, I am Client"))
				{
					writer.write(">>Hello Client, I am Server");
					writer.newLine();
					writer.flush();
				}
				else
				{
					writer.write(">>" + line);
					writer.newLine();
					writer.flush();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line;
		Server server = new Server();
		server.start();
		
		//System.out.println("Server stopped");
		
	}

}
