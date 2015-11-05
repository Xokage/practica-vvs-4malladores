package servidor;

import java.util.ArrayList;
import java.util.List;

import contenido.Contenido;

public class ServidorSimpleImp implements Servidor {

	private String nombre;
	private List<Contenido> contenidoList;
	private String tokenEspecial;
	
	public ServidorSimpleImp(String nombre, List<Contenido> contenidoList, String tokenEspecial) {
		this.nombre = nombre;
		this.contenidoList = contenidoList;
		this.tokenEspecial = tokenEspecial;
	}

	public ServidorSimpleImp(String nombre, String tokenEspecial) {
		this.nombre = nombre;
		this.contenidoList = new ArrayList<>();
		this.tokenEspecial = tokenEspecial;
	}

	@Override
	public String obtenerNombre() {
		return nombre;
	}

	@Override
	public String alta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String baja(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregar(Contenido contenido, String token) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(Contenido contenido, String token) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Contenido> buscar(String subcadena, String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
