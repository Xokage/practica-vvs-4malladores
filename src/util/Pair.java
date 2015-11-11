package util;

/**
 * The Class Pair.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class Pair<K, V> {

	/** The element0. */
	private final K element0;
	
	/** The element1. */
	private final V element1;

	/**
	 * Creates the pair.
	 *
	 * @param <K> the key type
	 * @param <V> the value type
	 * @param element0 the element0
	 * @param element1 the element1
	 * @return the pair
	 */
	public static <K, V> Pair<K, V> createPair(K element0, V element1) {
		return new Pair<K, V>(element0, element1);
	}

	/**
	 * Instantiates a new pair.
	 *
	 * @param element0 the element0
	 * @param element1 the element1
	 */
	public Pair(K element0, V element1) {
		this.element0 = element0;
		this.element1 = element1;
	}

	/**
	 * Gets the left.
	 *
	 * @return the left
	 */
	public K getLeft() {
		return element0;
	}

	/**
	 * Gets the right.
	 *
	 * @return the right
	 */
	public V getRight() {
		return element1;
	}

}
