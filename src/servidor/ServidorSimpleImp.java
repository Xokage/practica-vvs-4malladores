package servidor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.Pair;
import contenido.Anuncio;
import contenido.Contenido;

/**
 * Clase ServidorSimpleImp.
 */
public class ServidorSimpleImp implements Servidor {

	/** Nombre del servidor. */
	private String nombre;
	
	/** Lista de contenido del servidor. */
	private List<Contenido> contenidoList;
	
	/** Token para modificar el contenido. */
	private String tokenContenido;
	
	/** Lista de tokens validos para buscar. */
	private List<Pair<String, Integer>> tokenValido;

	/** Número de intentos por token. */
	private final int TRIES_PER_TOKEN = 10;
	
	
	/**
	 * Genera un nuevo token aleatoriamente.
	 *
	 * @param chars numero de letras.
	 * @return nuevo token aleatorio.
	 */
	private Pair<String, Integer> getToken(int chars) {
	    String CharSet = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";
	    String Token = "";
	    for (int a = 1; a <= chars; a++) {
	        Token += CharSet.charAt(new Random().nextInt(CharSet.length()));
	    }
	    return new Pair<String, Integer>(Token,TRIES_PER_TOKEN);
	}
	
	/**
	 * Instancia un nuevo servidor simple.
	 *
	 * @param nombre el nombre del servidor.
	 * @param contenidoList la lista con el contenido del servidor.
	 * @param tokenContenido el token para modificar contenido.
	 * @param tokenValido token para realizar búsquedas.
	 */
	public ServidorSimpleImp(String nombre, List<Contenido> contenidoList,
			String tokenContenido, String tokenValido) {
		this.nombre = nombre;
		this.contenidoList = contenidoList == null ? new ArrayList<>()
				: contenidoList;
		this.tokenContenido = tokenContenido;
		this.tokenValido = new ArrayList<>();
		this.tokenValido.add(new Pair<String, Integer>(tokenValido, TRIES_PER_TOKEN));
	}
	
	/**
	 * Instancia un nuevo servidor simple sin contenido.
	 *
	 * @param nombre el nombre del servidor.
	 * @param tokenContenido el token para modificar contenido.
	 * @param tokenValido token para realizar búsquedas.
	 */
	public ServidorSimpleImp(String nombre, String tokenContenido,
			String tokenValido) {
		this.nombre = nombre;
		this.contenidoList = new ArrayList<>();
		this.tokenContenido = tokenContenido;
		this.tokenValido = new ArrayList<>();
		this.tokenValido.add(new Pair<String, Integer>(tokenValido, TRIES_PER_TOKEN));
	}

	/**
	 * Instancia un nuevo servidor simple sin contenido ni tokens definidos.
	 *
	 * @param nombre el nombre del servidor.
	 */
	public ServidorSimpleImp(String nombre) {
		this.nombre = nombre;
		this.contenidoList = new ArrayList<>();
		this.tokenValido = new ArrayList<>();
	}

	/**
	 * @see servidor.Servidor#obtenerNombre()
	 */
	@Override
	public String obtenerNombre() {
		return nombre;
	}

	/**
	 * @see servidor.Servidor#alta()
	 */
	@Override
	public String alta() {
		Pair<String, Integer> par = null;
		boolean existe = true;
		while (existe){
			par = getToken(TRIES_PER_TOKEN);
			for (Pair<?, ?> parLista : tokenValido) {
                if (parLista.getLeft().toString().equals(par.getLeft())) existe=true;
            }
		}
		tokenValido.add(par);
		return par.getLeft().toString();
		
	}

	/**
	 * @see servidor.Servidor#baja(java.lang.String)
	 */
	@Override
	public void baja(String token) {
		for (Pair<String, Integer> par : this.tokenValido) {
			if (par.getLeft().equals(token))
				this.tokenValido.remove(par);
		}
	}

	/**
	 * @see servidor.Servidor#agregar(contenido.Contenido, java.lang.String)
	 */
	@Override
	public void agregar(Contenido contenido, String token) {
		if (this.tokenContenido.equals(token))
			this.contenidoList.add(contenido);
	}

	/**
	 * @see servidor.Servidor#eliminar(contenido.Contenido, java.lang.String)
	 */
	@Override
	public void eliminar(Contenido contenido, String token) {
		if (this.contenidoList.contains(contenido)
				&& this.tokenContenido.equals(token))
			this.contenidoList.remove(contenido);

	}

	/**
	 * @see servidor.Servidor#buscar(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Contenido> buscar(String subcadena, String token) {
		List<Contenido> resultado = new ArrayList<>();
		Pair<String, Integer> user = null;

		for (Pair<String, Integer> tmpUser : this.tokenValido) {
			if (tmpUser.getLeft().equals(token))
				user = tmpUser;
		}
		if (user != null)
			this.tokenValido.remove(user);
		// Quitamos o usuario da lista de tokens validos
		// mentres estÃ¡ pedindo contido.
		int contidosVisualizados = 0;
		for (Contenido elemento : this.contenidoList) {
			if (elemento.obtenerTitulo().contains(subcadena)) {

				if (user == null) {
					if (contidosVisualizados == 0)
						resultado.add(new Anuncio()); // Comeza cun anuncio
					contidosVisualizados++;
					resultado.add(elemento); // Añadimos elemento que contén a
												// subcadena
					if (contidosVisualizados % 3 == 0)
						resultado.add(new Anuncio()); // Un anuncio cada 3
														// contidos
				} else if (user.getRight() <= 0)
					user = null;
				else {
					resultado.add(elemento); // Añadimos elemento
					user = new Pair<String, Integer>(user.getLeft(),
							user.getRight() - 1);// Usuario gasta un intento
				}
			}
		}

		if (user != null)
			if (user.getRight() > 0) // Token non é vacío, por tanto mantense.
			tokenValido.add(user);

		return resultado;
	}
}
