package angel.angel;
import java.io.*;
import java.net.*;


public class Connection {
	PrintWriter out;
	BufferedReader in;

	public Connection(Socket socket,int port) throws UnknownHostException, IOException {
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public Connection(ServerSocket serverSocket) throws IOException {
		Socket clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);                   
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	public void close() throws IOException {
		out.close();
		in.close();
	}
	
	public void sendMessage(String message) {
		out.println(message);
	}
	
	public String receive() throws IOException {
		return in.readLine();
	}
	
	public void receive(String expectedResponse) throws IOException, ConnectionException {
		String response = in.readLine();
		if (!response.equals(response))
			throw new ConnectionException("Expected " + expectedResponse + " received " + response);
	}
	
	public void sendAndReceive(String message, String response) throws IOException, ConnectionException {
		sendMessage(message);
		receive(response);
	}
	
	public void receiveAndSend(String message, String response) throws IOException, ConnectionException {
		receive(message);
		sendMessage(response);
	}
}
