package Data;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
			emisor.flush();
		}catch(IOException e) {
			System.out.println("No se pudo enviar el archivo: "+e.getMessage());
		}
	}
	
	public void enviarUser(String username) {
		try {
			emisor= new ObjectOutputStream(socket.getOutputStream());
			emisor.writeObject(username);
			emisor.flush();
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
	
	public String recibirArchivo(String ubicacion) {
        String salida = "";
        try{
            
            receptor = new ObjectInputStream(socket.getInputStream());
            File file = (File)receptor.readObject();
            File newFile = new File(ubicacion);
			
			FileInputStream input = new FileInputStream(file);
			FileOutputStream output = new FileOutputStream(newFile);
			
			byte[] buffer = new byte[1024];
			int length;
			while((length = input.read(buffer))>0) {
				output.write(buffer,0,length);
			}
			input.close();
			output.close();
			salida = "Archivo "+file.getName()+" guardado con exito";
        }catch(IOException | ClassNotFoundException e){
            System.err.println("No se pudieron recibir los datos en cliente "+e.getMessage());
            salida = "Error al recuperar el archivo";
        }
        return salida;
	}
	
	public void enviarPeticion(String archivo) {
		try {
			DataOutputStream emi = new DataOutputStream(socket.getOutputStream());
			emi.writeUTF(archivo);
			emi.flush();
		}catch(IOException e) {
			System.out.println("Error al enviar peticion user: "+e.getMessage());
		}
	}

}
