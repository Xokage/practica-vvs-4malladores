package contenido;

import java.util.List;

public interface Contenido {
	
	//Se piden tres implementaciones: una para canciones, otra para anuncios, y otra para emisoras
	//Los anuncios duran siempre 5 segundos
	//Los anuncions tienen siempre como título 'PUBLICIDAD'
	
	public String obtenerTitulo();
	public Integer obtenerDuracion();
	public List<Contenido> obtenerListaReproduccion();
	
	//Buscar realiza comparaciones usando el titulo
	public List<Contenido> buscar(String subcadena);
	
	//En canciones y anuncios agregar no tiene efecto
	public void agregar(Contenido contenido, Contenido predecesor);
	
	//En canciones y anuncios eliminar no tiene efecto
	public void eliminar(Contenido contenido);
	
	public String getTitulo();
}
