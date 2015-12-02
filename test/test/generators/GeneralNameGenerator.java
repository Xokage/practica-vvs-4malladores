package test.generators;

import net.java.quickcheck.Generator;
import net.java.quickcheck.generator.PrimitiveGenerators;

/**
 * Clase CancionNameGenerator.
 */
public class GeneralNameGenerator implements Generator<String> {
	
	/** El nombre que se genera. */
	private final Generator<String> nome = PrimitiveGenerators.strings();

	/**
	 * @see net.java.quickcheck.Generator#next()
	 */
	@Override
	public final String next() {
		return new String(nome.next());
	}
}
