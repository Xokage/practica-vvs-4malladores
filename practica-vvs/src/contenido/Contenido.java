package contenido;

import java.util.List;

public interface Contenido {
	public String obtenerTitulo();
	public Integer obtenerDuracion();
	public List<Contenido> obtenerListaReproduccion();
	public List<Contenido> buscar(String subcadena);
	public void agregar(Contenido contenido, Contenido predecesor);
	public void eliminar(Contenido contenido);
}
