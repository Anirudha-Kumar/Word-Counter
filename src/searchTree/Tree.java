package searchTree;

import java.util.Collection;

public interface Tree <K extends Comparable<K>,V >{

	V search(K key);

	NonEmptyTree<K,V> insert(K key, V value);

	Tree<K,V> delete(K key);

	K max() throws TreeIsEmptyException;

	K min() throws TreeIsEmptyException;

	int size();

	void addKeysToCollection(Collection<K> c);

	public Tree<K,V> subTree(K fromKey, K toKey);
}