package Business;

import java.io.File;
import java.util.ArrayList;

import Data.DBUsuario;

public class lnUsuarios {
	
	private DBUsuario db;
	
	public lnUsuarios() {
		db = new DBUsuario();
	}
	
	public String getDatos(String user, String pass) {
		return db.getDatos(user, pass);
	}

	public Object[][] getRowsCliente(File[] data){
		File[] datos = data;
		Object[][] rows = new Object[datos.length][1];
		
		int i = 0;
		
		for (File f : datos) {
			
			rows[i][0] = f.getName();
			
			i++;
		}
		
		return rows;
	}
}
