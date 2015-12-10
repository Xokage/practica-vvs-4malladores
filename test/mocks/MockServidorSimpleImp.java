package mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import servidor.Servidor;
import util.Pair;
import contenido.Anuncio;
import contenido.Contenido;

public class MockServidorSimpleImp implements Servidor {
	
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
		return new Pair<String, Integer>("", triesPerToken);
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
	public final String alta() {
		
		return "";

	}

	/**
	 * @see servidor.Servidor#baja(java.lang.String)
	 */
	@Override
	public final void baja(final String token) {

	}

	/**
	 * @see servidor.Servidor#agregar(contenido.Contenido, java.lang.String)
	 */
	@Override
	public final void agregar(final Contenido contenido, final String token) {

	}

	/**
	 * @see servidor.Servidor#eliminar(contenido.Contenido, java.lang.String)
	 */
	@Override
	public final void eliminar(final Contenido contenido, final String token) {
		
	}

	/**
	 * @see servidor.Servidor#buscar(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Contenido> buscar(final String subcadena,
			final String token) {
		List<Contenido> resultado = new ArrayList<>();
		return resultado;
	}
	
}
