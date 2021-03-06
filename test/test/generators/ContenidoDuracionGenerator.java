package test.generators;

import net.java.quickcheck.Generator;
import net.java.quickcheck.generator.PrimitiveGenerators;

/**
 * Clase CancionDuracionGenerator.
 */
public class ContenidoDuracionGenerator implements Generator<Integer> {
	
	/** La duracion que se genera. Siempre positiva. */
	private final Generator<Integer> duracion = 
			PrimitiveGenerators.positiveIntegers();

	/**
	 * @see net.java.quickcheck.Generator#next()
	 */
	@Override
	public final Integer next() {
		return new Integer(duracion.next());
	}
}
