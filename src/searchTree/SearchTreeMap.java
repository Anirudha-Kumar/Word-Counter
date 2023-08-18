package searchTree;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class SearchTreeMap<K extends Comparable<K>, V>  {

	Tree<K,V> theTree = EmptyTree.getInstance();


	public V get(K k) {
		return theTree.search(k);
	}


	public void put(K k, V v) {
		theTree = theTree.insert(k, v);
	}


	public int size() {
		return theTree.size();
	}


	public void remove(K k) {
		theTree = theTree.delete(k);
	}

	public Set<K> keySet() {
		Set<K> result = new HashSet<K>();
		theTree.addKeysToCollection(result);
		return result;
	}

	public K getMin() {
		try {
			return theTree.min();
		} catch (TreeIsEmptyException e) {
			throw new NoSuchElementException();
		}
	}

	public K getMax() {
		try {
			return theTree.max();
		} catch (TreeIsEmptyException e) {
			throw new NoSuchElementException();
		}
	}

	public String toString() {
		return theTree.toString();
	}

	public List<K> keyList( ) {

		List <K> answer = new ArrayList <> (); 

		theTree.addKeysToCollection(answer);

		return answer; 
	}

	public SearchTreeMap<K, V> subMap(K fromKey, K toKey) {

		SearchTreeMap<K,V> newMap = new SearchTreeMap<>();
		newMap.theTree = this.theTree.subTree(fromKey, toKey);

		return newMap;
	}
}