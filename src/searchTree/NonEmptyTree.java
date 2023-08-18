package searchTree;

import java.util.Collection;


public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	private Tree<K,V> left, right;
	private K key;
	private V value; 


	/* 
	 * Constructor 
	 * @param - K, V, Tree <K, V>, Tree <K, V> 
	 * 
	 * this constructor creates a nonEmptyTree object 
	 */
	public NonEmptyTree(K key, V value, Tree<K,V> left, Tree<K,V> right) { 
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}



	/* 
	 * @param - K (key to look for) 
	 * @return - V (value for associated key) 
	 * 
	 * this method returns the value for for the associated key we are looking for
	 * if key does not exist then returns null 
	 */
	public V search(K key) {

		int result = key.compareTo(this.key); // compare value 

		if (result == 0) { // if current Tree's key is the same as the param key 

			return this.value; 
		} else if (result < 0) { // if the current Tree's key is the greater than param key 

			return this.left.search(key); 
		} else { // if the current Tree's key is the less than param key 

			return this.right.search(key); 
		}

	}


	/* 
	 * @param - K (key), V (value) 
	 * @return - a nonEmptyTree with a tree added 
	 * 
	 * this method inserts the specific tree (K key, V value) into the BST. 
	 * If the key (K) is already in the tree then the value (V) is updated 
	 */
	public NonEmptyTree<K, V> insert(K key, V value) {

		int result = key.compareTo(this.key); // compare value 

		if (result == 0) { // if key already exists 

			this.value = value; 
			return this; 
		} else if (result < 0) { // if key is smaller than current key 

			left = this.left.insert(key, value); 
		} else { // // if key is greater than current key

			right = this.right.insert(key, value); 
		}

		return this;

	}


	/* 
	 * @param - K (key to delete) 
	 * 
	 * @return - Tree <K, V> 
	 * 
	 * this method deletes the tree with the specified key 
	 */
	public Tree<K, V> delete(K key) { 

		int result = key.compareTo(this.key); // compare value 

		if (result < 0) { // if key is smaller than current key 

			left = left.delete(key); 
		} else if (result > 0) { // if key is greater than current key 

			right = right.delete(key); 
		} else { // if the curr key is the same as param key 

			try { // checks for left max and replaces with left max 
				K newLKey = left.max();
				V newLValue = search(newLKey);
				this.key = newLKey; 
				this.value = newLValue; 
				left = left.delete(newLKey); 
			} catch (TreeIsEmptyException e) {

				try { // checks for right min, if left max does not exist 
					K newRKey = right.min();
					V newRValue = search(newRKey);
					this.key = newRKey;
					this.value = newRValue;
					right = right.delete(newRKey); 
				} catch (TreeIsEmptyException f) { // if ever reaches catch block then no children 

					return EmptyTree.getInstance(); 
				}
			}

		}

		return this; 
	}

	/* 
	 * @return - K (max key) 
	 * 
	 * this method returns the max key 
	 */

	public K max() {

		try { // keeps going right, until exception is thrown 

			return right.max(); 
		} catch (TreeIsEmptyException e) { // as soon as it catches exceptions, the key is returned 

			return this.key; 
		}

	}

	/* 
	 * @return - K (min key) 
	 * 
	 * this method returns the min key 
	 */
	public K min() {

		try { // keeps going left, until exception is thrown 

			return left.min(); 
		} catch (TreeIsEmptyException e) { // as soon as it catches exceptions, the key is returned 

			return this.key; 
		}

	}

	/* 
	 * @return - size 
	 * 
	 * this method returns the size of the tree 
	 */
	public int size() {

		// keep adding one for every tree it visits, then goes left & right 
		return 1 + this.left.size() + this.right.size(); 
	}

	/* 
	 * @return - Collection c 
	 * 
	 * this method returns a collection of all the keys in the tree 
	 */
	public void addKeysToCollection(Collection<K> c) {

		// in order traversal 

		left.addKeysToCollection(c); 
		c.add(key); 
		right.addKeysToCollection(c); 
	}

	/* 
	 * @param - K (lower range), K (upper range) 
	 * @return - Tree <K, V> 
	 * 
	 * this method returns a subTree 
	 */
	public Tree<K,V> subTree(K fromKey, K toKey) {

		Tree <K, V> answer = EmptyTree.getInstance(); // initially an emptyTree

		int checkOne = fromKey.compareTo(this.key); // if (-) too small, (+) good  
		int checkTwo = toKey.compareTo(this.key);  // if (-) then good, (+) then too big  

		if (checkOne <= 0 && checkTwo >= 0) { // checks if in range 

			answer = new NonEmptyTree<>(key, value, left.subTree(fromKey, toKey), right.subTree(fromKey, toKey)); 
		} else if (checkOne > 0) { // less than from  key 

			return right.subTree(fromKey, toKey); 
		} else if (checkTwo < 0) { // greater than toKey 

			return left.subTree(fromKey, toKey); 
		}

		return answer; 
	}
}