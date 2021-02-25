import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ComunHilos {
	
	ArrayList<String> todosMensajes = new ArrayList<>();
	ArrayList<Socket> todosSockets = new ArrayList<>();
	
	
	public void aniadir(String mensaje) throws IOException 
	{
		todosMensajes.add(mensaje);
	
	
	for (Socket sc : todosSockets) {
		
		DataOutputStream salida = new DataOutputStream(sc.getOutputStream());
		salida.writeUTF(mensaje);
	}
	
	}
	public void mostrarHistorial() throws IOException {
		for (Socket sc : todosSockets) {
			for (String msg : todosMensajes) {
				DataOutputStream salida = new DataOutputStream(sc.getOutputStream());
				salida.writeUTF(msg);
			}
		}
	}
	
	public synchronized void aniadirCliente(Socket client) throws IOException 
	{
		 DataOutputStream salida = new DataOutputStream(client.getOutputStream());
		 
		 while(todosSockets.size() == 4) {
			 try {
				 salida.writeUTF("El servidor esta lleno , espere");
				 wait();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	todosSockets.add(client);
	salida.writeUTF("Bienvenido al chat TERRA");
	notifyAll();
	
	}
	
	public synchronized void eliminarClient(Socket client) 
	{
		
		todosSockets.remove(client);
		notifyAll();
		
	}
	

	
}
