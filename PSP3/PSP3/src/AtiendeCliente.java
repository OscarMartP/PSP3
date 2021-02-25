import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class AtiendeCliente extends Thread {
	
	String cliente; 
	Socket conexion;
	ComunHilos comunHilos;
	
	
	
	
	public AtiendeCliente(Socket conexion, ComunHilos comunHilos) 
	{
		this.cliente = "";
		this.conexion = conexion;
		this.comunHilos = comunHilos;
	}



	@Override
	public void run(){
		
	try
	{
		
		DataInputStream entrada = new DataInputStream(conexion.getInputStream());
		
		comunHilos.aniadirCliente(conexion);
		
		comunHilos.mostrarHistorial();
		
		cliente = entrada.readUTF();
		
		String mensaje = cliente + " dice: " + entrada.readUTF();
		
		while (!mensaje.equals(cliente + " dice: " + "*")) 
		{
			System.out.println(mensaje);
			comunHilos.aniadir(mensaje);
			mensaje = cliente + " dice: " + entrada.readUTF();
		}
		
		comunHilos.eliminarClient(conexion);
		
		System.out.println(cliente + " desconectado ");

		
	} catch (IOException e) {
		e.printStackTrace();
		
	}
	}
}