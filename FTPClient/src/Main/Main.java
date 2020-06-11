package Main;

import java.io.File;

import Data.Cliente;
import Data.DBUsuario;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Cliente c = new Cliente("localhost", 8080);
		//c.enviar(new File("C:\\Users\\Randall\\Desktop\\xd.txt"));
		//c.enviarUser("saibor");
		//System.out.println(c.recibir()[0].getName());
		GUI g = new GUI();
		g.init();
	}

}
