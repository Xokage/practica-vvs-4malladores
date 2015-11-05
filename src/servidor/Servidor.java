package servidor;

import java.util.List;

import contenido.Contenido;



public interface Servidor {
	//El token caduca tras devolver 10 contenidos
	//Se piden dos implementaciones: en una de ellas cada servidor tiene un Servidor de respaldo a quien pregunta en caso de que una b�squeda en local le resulte vac�a
	public String obtenerNombre();
	public String alta();
	public void baja(String token);
	//Solo un token especial puede agregar contenidos
	public void agregar(Contenido contenido, String token);
	//Solo un token especial puede eliminar contenidos
	public void eliminar(Contenido contenido, String token);
	//Si se usa un token vac�o en las b�squedas, la lista de contenidos empieza con y tiene un anuncio cada tres contenidos
	public List<Contenido> buscar(String subcadena, String token);
}
