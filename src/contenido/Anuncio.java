package contenido;

import java.util.ArrayList;
import java.util.List;

public class Anuncio implements Contenido {
	private String titulo;
	private int duracion;
	private List<Contenido> listaReproduccion;
	
	public Anuncio(){
		titulo = "PUBLICIDAD";
		duracion = 5;
		listaReproduccion = new ArrayList<Contenido>();
		listaReproduccion.add(this);
	}
	
	@Override
	public String obtenerTitulo() {
		return titulo;
	}

	@Override
	public Integer obtenerDuracion() {
		return duracion;
	}

	@Override
	public List<Contenido> obtenerListaReproduccion() {
		return listaReproduccion;
	}

	@Override
	public List<Contenido> buscar(String subcadena) {
		if (titulo.equalsIgnoreCase(subcadena))
			return listaReproduccion;
		else return new ArrayList<Contenido>();
	}

	@Override
	public void agregar(Contenido contenido, Contenido predecesor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Contenido contenido) {
		// TODO Auto-generated method stub
		
	}
}
