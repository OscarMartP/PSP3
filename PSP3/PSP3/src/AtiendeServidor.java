import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class AtiendeServidor extends Thread {

	Socket salida;
	
	public AtiendeServidor(Socket salida) {
		
		this.salida = salida;
	}
	
	@Override
	public void run()
	{
		try 
		{
			while(true) 
			{
				DataInputStream entrada = new DataInputStream(salida.getInputStream());
				System.out.println(entrada.readUTF());
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	 
}
