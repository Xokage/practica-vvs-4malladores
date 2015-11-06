package servidor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.Pair;
import contenido.Anuncio;
import contenido.Contenido;

public class ServidorSimpleImp implements Servidor {

	private String nombre;
	private List<Contenido> contenidoList;
	private String tokenContenido;
	private List<Pair<String, Integer>> tokenValido;
	
	private Pair getToken(int chars) {
	    String CharSet = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";
	    String Token = "";
	    for (int a = 1; a <= chars; a++) {
	        Token += CharSet.charAt(new Random().nextInt(CharSet.length()));
	    }
	    return new Pair(Token,10);
	}

	public ServidorSimpleImp(String nombre, List<Contenido> contenidoList,
			String tokenContenido, String tokenValido) {
		this.nombre = nombre;
		this.contenidoList = contenidoList;
		this.tokenContenido = tokenContenido;
		this.tokenValido = new ArrayList<>();
		this.tokenValido.add(new Pair<String, Integer>(tokenValido, 10));
	}

	public ServidorSimpleImp(String nombre, String tokenContenido,
			String tokenValido) {
		this.nombre = nombre;
		this.contenidoList = new ArrayList<>();
		this.tokenContenido = tokenContenido;
		this.tokenValido = new ArrayList<>();
		this.tokenValido.add(new Pair<String, Integer>(tokenValido, 10));
	}

	public ServidorSimpleImp(String nombre) {
		this.nombre = nombre;
		this.contenidoList = new ArrayList<>();
		this.tokenValido = new ArrayList<>();
	}

	@Override
	public String obtenerNombre() {
		return nombre;
	}

	@Override
	public String alta() {
		Pair par = null;
		boolean existe = true;
		while (existe){
			par = getToken(10);
			for (Pair parLista : tokenValido) {
                if (parLista.getLeft().toString().equals(par.getLeft())) existe=true;
            }
		}
		tokenValido.add(par);
		return par.getLeft().toString();
		
	}

	@Override
	public void baja(String token) {
		for (Pair<String, Integer> par : this.tokenValido) {
			if (par.getLeft().equals(token))
				this.tokenValido.remove(par);
		}
	}

	@Override
	public void agregar(Contenido contenido, String token) {
		if (!this.contenidoList.contains(contenido)
				&& this.tokenContenido.equals(token))
			this.contenidoList.add(contenido);
	}

	@Override
	public void eliminar(Contenido contenido, String token) {
		if (this.contenidoList.contains(contenido)
				&& this.tokenContenido.equals(token))
			this.contenidoList.remove(contenido);

	}

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

				if (user.getRight() <= 0) { // usuario queda sen intentos,
											// comezan os anuncios
					if (contidosVisualizados == 0)
						resultado.add(new Anuncio()); // Comeza cun anuncio
					contidosVisualizados++;
					resultado.add(elemento); // Añadimos elemento que contén a
												// subcadena
					if (contidosVisualizados % 3 == 0)
						resultado.add(new Anuncio()); // Un anuncio cada 3
														// contidos
				} else {
					resultado.add(elemento); // Añadimos elemento que contén a
												// subcadena
					user = new Pair<String, Integer>(user.getLeft(),
							user.getRight() - 1);// Usuario gasta un intento
				}
			}
		}

		if (user.getRight() > 0) // Token non é vacío, por tanto mantense.
			tokenValido.add(user);

		return resultado;
	}
}
