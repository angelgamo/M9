package angel.angel;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Server {
	static Random random = new Random();
	
	public static void main(String[] args) {
		String clientResponse;
		String serverResponse;
		
		int port = 60009;
		
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			Connection con = new Connection(serverSocket);
			
			con.sendAndReceive("BENVINGUT", "ACK");
			
			do {
				con.sendAndReceive("PREPARAT", "ACK");
				
				String[] pregunta = getPregunta();
				con.sendAndReceive(pregunta[0], "ACK");
				
				clientResponse = con.receive();
				con.sendMessage("ACK");
				
				if (clientResponse.equals(pregunta[1]))
					con.sendAndReceive(":pogchamp:", "ACK");
				else
					con.sendAndReceive(":peepoclown:", "ACK");
				
				con.sendMessage("'TAS PICAO?");
				
				clientResponse = con.receive();
			} while (clientResponse.equals("NIPAH"));
			
			con.sendMessage("ACK");
			System.out.println("Conexion de Servidor finalizada");
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	static String[] preguntas = new String[] {
			"{\"pregunta\":\"¿el tomate es una fruta, verdura u hortaliza?\", \"respostes\":[\"Fruta\", \"Hortaliza\", \"Verdura\"]}",
			"{\"pregunta\":\"¿Cuál es el único mamífero que puede volar?\", \"respostes\":[\"Murcielago\", \"Humanos\", \"Gatos\", \"Ornitorrinco\"]}",
			"{\"pregunta\":\"¿Qué país es el mayor exportador de café del mundo?\", \"respostes\":[\"Brasil\", \"Argentina\", \"Bolivia\"], \"correcta\":\"1\"}"
	};
	
	static String[] getPregunta() throws ParseException {
		String pregunta = preguntas[random.nextInt(preguntas.length)];
		JSONObject json = readJSON(pregunta);
		JSONArray array = (JSONArray) json.get("respostes");
		String correcta = array.get(0).toString();
		json.replace("respostes", shuffleJsonArray(array));
		return new String[] {json.toJSONString(), (array.indexOf(correcta)+1)+""};
	}
	
	static JSONObject readJSON(String json) throws ParseException {
		JSONParser parser = new JSONParser();
		return (JSONObject) parser.parse(json);
	}
	

	static JSONArray shuffleJsonArray (JSONArray array) {
			random.setSeed(System.currentTimeMillis());
	        for (int i = array.size() - 1; i >= 0; i--)
	        {
	          int j = random.nextInt(i + 1);
	          Object object = array.get(j);
	          array.set(j, array.get(i));
	          array.set(i, object);
	        }
	    return array;
	}
}
