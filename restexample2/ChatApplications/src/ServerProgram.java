import java.io.*;
import java.net.*;
public class ServerProgram{
	public static void main(String[] args) {
		try {

			ServerSocket serversocket = new ServerSocket(3333);
			Socket socket = serversocket.accept();

			DataInputStream datainput = new DataInputStream(socket.getInputStream());
			DataOutputStream dataoutput = new DataOutputStream(socket.getOutputStream());

			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));

			String msgin = "", msgout = "";

			while (!msgin.equals("end")) {
				msgin = datainput.readUTF();
				System.out.println(msgin);
				msgout = bufferedreader.readLine();
				dataoutput.writeUTF(msgout);
				dataoutput.flush();

			}
			socket.close();
			serversocket.close();
		} catch (Exception e) {

		}
	}
}