package Domain;

import java.io.File;
import java.io.Serializable;

public class Mensaje implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipo;
	private File archivo;
	private String mensaje;
	
	public Mensaje(String tipo, File archivo, String mensaje) {
		super();
		this.tipo = tipo;
		this.archivo = archivo;
		this.mensaje = mensaje;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
}
