import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServidor {

	public static void main(String[] args) throws IOException {
	    
		try(ServerSocket server = new ServerSocket(6000))
		{
			ComunHilos comunHilos = new ComunHilos();
			
			System.out.println("Servidor en marcha");
			
			while(true) {
				
				Socket cliente = server.accept();
				System.out.println("*************************");
				System.out.println("CLIENTE CONECTADO");
				System.out.println("*************************");
				
				AtiendeCliente clientThread = new AtiendeCliente(cliente , comunHilos);
				clientThread.start();
				
				
			}
		}
	  }
	}
