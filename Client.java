package testSocketAPI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	final String serverHost = "localhost";
	BufferedReader reader;
	BufferedWriter writer;
	Socket socketOfClient;
	
	
	
	public Client() {
		super();
		try {
			this.socketOfClient = new Socket(serverHost, 9999);
			this.reader = new BufferedReader(new InputStreamReader(this.socketOfClient.getInputStream()));
			this.writer = new BufferedWriter(new OutputStreamWriter(this.socketOfClient.getOutputStream()));
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client client = new Client();
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Client: >>Hello Server, I am Client");
			client.writer.write("Hello Server, I am Client");
			client.writer.newLine();
			client.writer.flush();
			System.out.println("Server: "+ client.reader.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true)
		{
			String requestLine,responseLine;
			try {
				System.out.print("Client: ");
				requestLine = sc.nextLine();
				client.writer.write(requestLine);
				client.writer.newLine();
				client.writer.flush();
				responseLine = client.reader.readLine();
				System.out.println("Server: " +responseLine);
				if(responseLine.equals(">>OK"))
					break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		try {
			client.reader.close();
			client.writer.close();
			client.socketOfClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
