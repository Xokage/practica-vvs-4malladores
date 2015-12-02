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
	private String tokenMaestro;

	/** Lista de tokens validos para buscar. */
	private List<Pair<String, Integer>> tokensValidos;

	/** Número de intentos por token. */
	private final int triesPerToken = 10;

	/** Número de intentos por token. */
	private final int anuncioTrasXContenidos = 3;

	/**
	 * Genera un nuevo token aleatoriamente.
	 *
	 * @param chars
	 *            numero de letras.
	 * @return nuevo token aleatorio.
	 */
	private Pair<String, Integer> getToken(final int chars) {
		String charSet = "abcdefghijkmnopqrstuvwxyz"
				+ "ABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";
		String token = "";
		for (int a = 1; a <= chars; a++) {
			token += charSet.charAt(new Random().nextInt(charSet.length()));
		}
		return new Pair<String, Integer>(token, triesPerToken);
	}

	/**
	 * Instancia un nuevo servidor simple.
	 *
	 * @param nombreRecibido
	 *            el nombre del servidor.
	 * @param contenidoListRecibido
	 *            la lista con el contenido del servidor.
	 * @param tokenContenido
	 *            el token para modificar contenido.
	 * @param tokenValido
	 *            token para realizar búsquedas.
	 */
	public ServidorSimpleImp(final String nombreRecibido,
			final List<Contenido> contenidoListRecibido,
			final String tokenContenido, final String tokenValido) {
		this.nombre = nombreRecibido;
		if (contenidoListRecibido == null) {
			contenidoList = new ArrayList<>();
		} else {
			contenidoList = contenidoListRecibido;
		}
		this.tokenMaestro = tokenContenido;
		this.tokensValidos = new ArrayList<>();
		this.tokensValidos.add(new Pair<String, Integer>(tokenValido,
				triesPerToken));
	}

	/**
	 * Instancia un nuevo servidor simple sin contenido.
	 *
	 * @param nombreRecibido
	 *            el nombre del servidor.
	 * @param tokenContenidoRecibido
	 *            el token para modificar contenido.
	 * @param tokenValido
	 *            token para realizar búsquedas.
	 */
	public ServidorSimpleImp(final String nombreRecibido,
			final String tokenContenidoRecibido, final String tokenValido) {
		this.nombre = nombreRecibido;
		this.contenidoList = new ArrayList<>();

		this.tokenMaestro = tokenContenidoRecibido;
		this.tokensValidos = new ArrayList<>();
		this.tokensValidos.add(new Pair<String, Integer>(tokenValido,
				triesPerToken));
	}

	/**
	 * Instancia un nuevo servidor simple sin contenido ni tokens definidos.
	 *
	 * @param nombreRecibido
	 *            el nombre del servidor.
	 */
	public ServidorSimpleImp(final String nombreRecibido) {
		this.nombre = nombreRecibido;
		this.contenidoList = new ArrayList<>();

		this.tokensValidos = new ArrayList<>();
	}

	/**
	 * @see servidor.Servidor#obtenerNombre()
	 */
	@Override
	public final String obtenerNombre() {
		return nombre;
	}

	/**
	 * @see servidor.Servidor#alta()
	 */
	@Override
	public final String alta() {
		Pair<String, Integer> par = null;
		boolean existe = true;
		while (existe) {

			existe = false;
			par = getToken(triesPerToken);

			for (Pair<String, Integer> parLista : tokensValidos) {
				if (parLista.getLeft().toString().equals(par.getLeft())) {
					existe = true;
				}
			}
		}

		tokensValidos.add(par);
		return par.getLeft().toString();

	}

	/**
	 * @see servidor.Servidor#baja(java.lang.String)
	 */
	@Override
	public final void baja(final String token) {

		for (Pair<String, Integer> par : this.tokensValidos) {
			if (par.getLeft().equals(token)) {

				this.tokensValidos.remove(par);
			}
		}
	}

	/**
	 * @see servidor.Servidor#agregar(contenido.Contenido, java.lang.String)
	 */
	@Override
	public final void agregar(final Contenido contenido, final String token) {

		if (this.tokenMaestro.equals(token)) {
			this.contenidoList.add(contenido);
		}
	}

	/**
	 * @see servidor.Servidor#eliminar(contenido.Contenido, java.lang.String)
	 */
	@Override
	public final void eliminar(final Contenido contenido, final String token) {
		if (this.contenidoList.contains(contenido)

		&& this.tokenMaestro.equals(token)) {
			this.contenidoList.remove(contenido);
		}
	}

	/**
	 * @see servidor.Servidor#buscar(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Contenido> buscar(final String subcadena,
			final String token) {
		List<Contenido> resultado = new ArrayList<>();
		Pair<String, Integer> user = null;

		for (Pair<String, Integer> tmpUser : this.tokensValidos) {
			if (tmpUser.getLeft().equals(token)) {
				user = tmpUser;
			}
		}
		if (user != null) {
			this.tokensValidos.remove(user);
		}
		// Quitamos o usuario da lista de tokens validos
		// mentres esta pedindo contido.
		int contidosVisualizados = 0;
		for (Contenido elemento : this.contenidoList) {
			if (elemento.obtenerTitulo().contains(subcadena)) {

				if (user == null) {
					if (contidosVisualizados == 0) {
						resultado.add(new Anuncio()); // Comeza cun anuncio
					}
					contidosVisualizados++;
					resultado.add(elemento); // Añadimos elemento que contén a
												// subcadena
					if (contidosVisualizados % anuncioTrasXContenidos == 0) {
						resultado.add(new Anuncio()); // Un anuncio cada 3
					} // contidos
				} else if (user.getRight() <= 0) {
					user = null;
				} else {
					resultado.add(elemento); // Añadimos elemento
					user = new Pair<String, Integer>(user.getLeft(),
							user.getRight() - 1); // Usuario gasta un intento
				}
			}
		}

		if (user != null) {
			if (user.getRight() > 0) { // Token non é vacío, por tanto mantense.

				tokensValidos.add(user);
			}
		}
		return resultado;
	}
}
