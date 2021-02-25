
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class AppCliente {
	
	static Scanner sc = new Scanner(System.in);
	

	  public static void main(String[] args) throws IOException {
	    
		  try (Socket socket = new Socket("localhost", 6000)) {

				DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

				System.out.println("¿Cual es tu usuario?");
				String usuario = sc.nextLine();
				salida.writeUTF(usuario);

				AtiendeServidor threadServer = new AtiendeServidor(socket);
				threadServer.start();

				String mensaje = sc.nextLine();

				while (!mensaje.equals("*")) {
					salida.writeUTF(mensaje);
					mensaje = sc.nextLine();
				}

				salida.writeUTF(mensaje);
				System.out.println("Te has desconectado");
			}
		}
	}