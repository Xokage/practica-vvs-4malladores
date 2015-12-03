package util;

/**
 * The Class Pair.
 *
 * @param <K>
 *            the key type
 * @param <V>
 *            the value type
 */
public class Pair<K, V> {

	/** The element0. */
	private final K element0;

	/** The element1. */
	private final V element1;

	/**
	 * Creates the pair.
	 *
	 * @param <K>
	 *            the key type
	 * @param <V>
	 *            the value type
	 * @param element0
	 *            the element0
	 * @param element1
	 *            the element1
	 * @return the pair
	 */
	public static <K, V> Pair<K, V> createPair(final K element0,
			final V element1) {
		return new Pair<K, V>(element0, element1);
	}

	/**
	 * Instantiates a new pair.
	 *
	 * @param element0Received
	 *            the element0
	 * @param element1Received
	 *            the element1
	 */
	public Pair(final K element0Received, final V element1Received) {
		this.element0 = element0Received;
		this.element1 = element1Received;
	}

	/**
	 * Gets the left.
	 *
	 * @return the left
	 */
	public final K getLeft() {
		return element0;
	}

	/**
	 * Gets the right.
	 *
	 * @return the right
	 */
	public final V getRight() {
		return element1;
	}

}
