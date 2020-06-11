package Data;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JLabel;

public class Cliente {
	private Socket socket;
	private ObjectOutputStream emisor;
	private ObjectInputStream receptor;
	
	public Cliente(String ip, int puerto, JLabel l) {
		// TODO Auto-generated constructor stub
		try {
			socket = new Socket(ip, puerto);
		}catch(IOException e) {
			//System.out.println("No se levantó el cliente: "+e.getMessage());
			l.setText("Puerto Incorrecto");
		}
	}
	
	public void enviar(File file) {
		try {
			emisor= new ObjectOutputStream(socket.getOutputStream());
			emisor.writeObject(file);
		}catch(IOException e) {
			System.out.println("No se pudo enviar el archivo: "+e.getMessage());
		}
	}
	
	public void enviarUser(String username) {
		try {
			emisor= new ObjectOutputStream(socket.getOutputStream());
			emisor.writeObject(username);
		}catch(IOException e) {
			System.out.println("Error al enviar el user: "+e.getMessage());
		}
	}
	
	public File[] recibir() {
		File[] files = null;
        
        try{
            
            receptor = new ObjectInputStream(socket.getInputStream());
            files = (File[])receptor.readObject();
            
        }catch(IOException | ClassNotFoundException e){
            System.err.println("No se pudieron recibir los datos en cliente "+e.getMessage());
        }
        return files;
	}

}
