package angel.angel;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Client {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String clientResponse;
		String serverResponse;
		
		String hostname = "10.1.82.34";
		//int port = 60009;
		int port = 42000;
		
		try {
			Socket socket = new Socket(hostname, port);
			Connection con = new Connection(socket, port);
			
			con.receiveAndSend("BENVINGUT", "ACK");
			
			do {
				con.receiveAndSend("PREPARAT", "ACK");
				
				JSONObject json = readJSON(con.receive());
				System.out.println(json.get("pregunta"));
				for (Object resposta : (JSONArray)json.get("respostes"))
					System.out.println(resposta);
				con.sendMessage("ACK");
				
				clientResponse = sc.nextLine();
				con.sendAndReceive(clientResponse, "ACK");
				
				serverResponse = con.receive();
				con.sendMessage("ACK");
				
				if (serverResponse.equals(":pogchamp:"))
					System.out.println("respuesta correcta");
				else
					System.out.println("respuesta incorrecta");
				
				if (con.receive().equals("'TAS PICAO?"))
					System.out.println("volver a jugar? (si, no)");
				
				clientResponse = sc.nextLine();
				if (clientResponse.equals("si"))
					con.sendMessage("NIPAH");
				else
					con.sendMessage("PLEGA");
				
			} while (clientResponse.equals("si"));
			
			con.receive("ACK");
			System.out.println("Conexion de Cliente finalizada");
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	static JSONObject readJSON(String json) throws ParseException {
		JSONParser parser = new JSONParser();
		return (JSONObject) parser.parse(json);
	}
	
}
