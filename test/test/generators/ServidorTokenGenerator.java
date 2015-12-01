package test.generators;

import java.util.Random;

import net.java.quickcheck.Generator;
import util.Pair;

public class ServidorTokenGenerator implements Generator<Pair<String,Integer>> {

	/** El numero de letras del token. */
	private final int LENGHT_OF_TOKEN = 10;

	/** El numero de intentos del token. */
	private final int TRIES_PER_TOKEN = 10;

	/**
	 * @see net.java.quickcheck.Generator#next()
	 */
	@Override
	public Pair<String,Integer> next() {
		return getToken(LENGHT_OF_TOKEN);
	}

	private Pair<String, Integer> getToken(int chars) {
		String CharSet = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";
		String Token = "";
		for (int a = 1; a <= chars; a++) {
			Token += CharSet.charAt(new Random().nextInt(CharSet.length()));
		}
		return new Pair<String, Integer>(Token, TRIES_PER_TOKEN);
	}
}
