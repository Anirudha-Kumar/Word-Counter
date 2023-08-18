package searchTree;

import java.util.Collection;

public class EmptyTree<K extends Comparable<K>,V> implements Tree<K,V> {

	private static EmptyTree SINGLETON = new EmptyTree();

	public static  <K extends Comparable<K>, V> EmptyTree<K,V> getInstance() {
		return SINGLETON;
	}

	private EmptyTree() {
		// Nothing to do
	}

	/* 
	 * @param - K (key to search for) 
	 * @return - V (value of the key) 
	 * 
	 * this method returns null because an Empty List can never contain a value 
	 */
	public V search(K key) {

		return null; 
	}


	/* 
	 * @param - K (key to be inserted), V (value associated with the key) 
	 * @return - a nonEmptyTree 
	 * a nonEmptyTree is created that points to two emptyTrees 
	 */
	public NonEmptyTree<K, V> insert(K key, V value) {

		return new NonEmptyTree<> (key, value, this, this); 
	}


	/* 
	 * @param - Key (key to delete) 
	 * @return - any type of tree 
	 * 
	 * return a emptyTree because an emptyTree has nothing to delete 
	 */
	public Tree<K, V> delete(K key) {

		return this; 
	}

	/* 
	 * @return - K (key) 
	 * throws a TreeIsEmptyException because an emptyTree does not have a max Key  
	 */
	public K max() throws TreeIsEmptyException {

		throw new TreeIsEmptyException(); 
	}

	/* 
	 * @return - K (key) 
	 * throws a TreeIsEmptyException because an emptyTree does not have a min Key  
	 */
	public K min() throws TreeIsEmptyException {

		throw new TreeIsEmptyException(); 
	}

	/* 
	 * @return - int 
	 * 
	 * returns 0 because an emptyTree always has size 0  
	 */
	public int size() {

		return 0; 
	}

	/* 
	 * @param - c (collection) 
	 * 
	 * this method does nothing, as there no keys to add 
	 * c is left empty 
	 */
	public void addKeysToCollection(Collection<K> c) {

		return; 
	}

	/* 
	 * @param - K (lowerRange), K (upperRange) 
	 * @return - Tree <K, V> 
	 * 
	 * this method returns an emptyTree as the subTree of an emptyTree is an emptyTree 
	 */
	public Tree<K,V> subTree(K fromKey, K toKey) {

		return this; 
	}
}