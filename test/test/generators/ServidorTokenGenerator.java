package test.generators;

import java.util.Random;

import net.java.quickcheck.Generator;
import util.Pair;

/**
 * 
 * @author Urist
 *
 *         Genera un token para las pruebas de servidores.
 */
public class ServidorTokenGenerator implements 
	Generator<Pair<String, Integer>> {

	/** El numero de letras del token. */
	private final int lengthOfToken = 10;

	/** El numero de intentos del token. */
	private final int triesPerToken = 10;

	/**
	 * @see net.java.quickcheck.Generator#next()
	 */
	@Override
	public final Pair<String, Integer> next() {
		return getToken(lengthOfToken);
	}

	/**
	 * Devuelve un token aleatorio.
	 * 
	 * @param chars
	 *            numero de caracteres del token.
	 * @return par (token, numero de intentos).
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
}
