package Data;

import java.awt.Label;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JLabel;

import Domain.Mensaje;
import Domain.Usuario;

public class Server {
	private ServerSocket server;
	private ArrayList<String> clientes;
	
	public Server(int puerto, JLabel l) {
		clientes = new ArrayList<String>();
		aceptarConexiones(puerto, l);
	}
	
	public void aceptarConexiones(int puerto, JLabel l) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					server = new ServerSocket(puerto);
					while(true) {
						l.setText("Esperando Clientes...");
						Socket nuevo = server.accept();
						String aux = recibirUser(nuevo);
						l.setText("Usuario: "+aux+" ,conectado");
						l.revalidate();
						l.repaint();
						enviar(aux, nuevo);
						recibir(nuevo, aux);
						clientes.add(aux);
					}
				} catch (IOException e) {
					System.out.println("No se levanto el server: "+e.getMessage());
				}
			}
		}).start();
	}
	
	public void enviar(String username, Socket socket) {
		File file = new File("C:\\Users\\Randall\\Desktop\\FTP\\"+username);
		if(!file.exists()) {
			file.mkdirs();
			System.out.println("Directorio creado");
		}
		
		try{
            
            Socket enviar = socket;
            ObjectOutputStream flujoSalida = new ObjectOutputStream(enviar.getOutputStream());
            File filelist[] = file.listFiles();
            flujoSalida.writeObject(filelist);
            
        }catch(IOException e){
            System.err.println("El servidor no pudo reenviar "+e);
        }
	}
	
	public void recibir(Socket cliente, String user) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while(true) {
						Socket recibir = cliente;
						ObjectInputStream flujoEntrada = new ObjectInputStream(recibir.getInputStream());
						Mensaje mensaje = (Mensaje) flujoEntrada.readObject();
						if(mensaje.getTipo().equals("archivo")) {
							String nombre = mensaje.getArchivo().getName();
							File newFile = new File("C:\\Users\\Randall\\Desktop\\FTP\\"+user+"\\"+nombre);
							
							FileInputStream input = new FileInputStream(mensaje.getArchivo());
							FileOutputStream output = new FileOutputStream(newFile);
							
							byte[] buffer = new byte[1024];
							int length;
							while((length = input.read(buffer))>0) {
								output.write(buffer,0,length);
							}
							input.close();
							output.close();
							System.out.println("Archivo recibido correctamente");
							enviar(user, cliente);
						}else {
							File pedido = new File("C:\\Users\\Randall\\Desktop\\FTP\\"+user+"\\"+mensaje.getMensaje());
							ObjectOutputStream flujoSalida = new ObjectOutputStream(recibir.getOutputStream());
							flujoSalida.writeObject(pedido);
						}
						
						
						
					}
					
					
				}catch(IOException  | ClassNotFoundException e) {
					System.out.println("El servidor no pudo recibir peticion de archivo: "+e.getMessage());
				}
				
			}
		}).start();
	}
	
	public String recibirUser(Socket cliente) {
		String salida = "";
		try {
			Socket recibir = cliente;
			ObjectInputStream flujoEntrada = new ObjectInputStream(recibir.getInputStream());
			salida = (String) flujoEntrada.readObject();
		}catch(IOException | ClassNotFoundException e) {
			System.out.println("Error recibiendo usuario: "+e.getMessage());
		}
		return salida;
	}

	public ArrayList<String> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<String> clientes) {
		this.clientes = clientes;
	}
	
	

}
