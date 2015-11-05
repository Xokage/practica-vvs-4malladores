package contenido;

import java.util.ArrayList;
import java.util.List;

public class Emisora implements Contenido {
	
	private String titulo;
	private Integer duracion;
	private List<Contenido> listaReproduccion;
	
	public Emisora(String titulo, Integer duracion) {
		this.titulo = titulo;
		this.duracion = duracion;
		this.listaReproduccion = new ArrayList<Contenido>();
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
		
		List<Contenido> lista = new ArrayList<Contenido>();
		
		for (Contenido c : listaReproduccion) {
			
			if (c.getTitulo().contains(subcadena)){
				
				lista.add(c);
			}
				
		}
		
		return lista;
	}

	@Override
	public void agregar(Contenido contenido, Contenido predecesor) {
		
		Integer indice = listaReproduccion.indexOf(predecesor);
		listaReproduccion.add(indice + 1, contenido);
		
	}

	@Override
	public void eliminar(Contenido contenido) {
		
		listaReproduccion.remove(contenido);
		
	}
	
	@Override
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


}
