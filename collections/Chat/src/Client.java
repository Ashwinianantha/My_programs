import java.io	.*;
import java.net.*;

public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 1201);
			DataInputStream datainput = new DataInputStream(socket.getInputStream());
			DataOutputStream dataoutput = new DataOutputStream(socket.getOutputStream());

			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));

			String msgin = "", msgout = "";
			while(!msgin.equals("end")) {
				msgout = bufferedreader.readLine();
				dataoutput.writeUTF(msgout);
				msgin = datainput.readUTF();
				System.out.println(msgin);
				}
			
			}catch(Exception e) {
		}
		
	}   
		
}