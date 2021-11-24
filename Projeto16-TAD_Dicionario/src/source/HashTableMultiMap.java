package source;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 10/11/2021
 *
 */
public class HashTableMultiMap<K, V> implements MultiMap<K, V> {
	// Mapa de chaves para listas de entradas
	Map<K, LinkedList<Map.Entry<K, V>>> m;
	
	// Tamanho do dicionaio
	int nSize;

	// Método construtor
	public HashTableMultiMap() {
		m = new HashMap<K, LinkedList<Map.Entry<K, V>>>(); // mapa default
		nSize = 0;
	}
	
	// Retorna a quantidade de entradas no dicionário
	public int size() { 
		return nSize; 
	}
	
	// Retorna se o dicionário está vazio
	public boolean isEmpty() { 
		return nSize == 0; 
	}

	// Insere um item em um dicionário. Retorna uma entrada recentemente criada
	public Map.Entry<K, V> put(K key, V value) throws IllegalArgumentException {
		LinkedList<Map.Entry<K, V>> ll;
		
		if (key == null) {
			throw new IllegalArgumentException();
		}
		
		if ((ll = m.get(key)) == null) {
			ll = new LinkedList<Map.Entry<K, V>>();
			m.put(key, ll);
		}
		
		Map.Entry<K, V> e = new AbstractMap.SimpleEntry<K, V>(key, value);
		ll.add(e);
		nSize++;
		return e;
	}
		
	// Retorna uma entrada contendo uma dada chave ou null se não existe a entrada
	public Map.Entry<K, V> get(K key) throws IllegalArgumentException {
		LinkedList<Map.Entry<K, V>> ll;
		
		if (key == null) {
			throw new IllegalArgumentException();
		}
		
		if ((ll = m.get(key)) == null) {
			return null;
		}
		
		return ll.peekFirst();
	}

	// Retorna um iterador contendo todas as entradas contendo uma certa chave ou um iterador
	// vazio se a entrada não existir.
	public Iterable<Map.Entry<K, V>> getAll(K key) throws IllegalArgumentException {
		LinkedList<Map.Entry<K, V>> ll;
		if (key == null) {
			throw new IllegalArgumentException();
		}
		
		if ((ll = m.get(key)) == null) {
			return null;
		}
		
		return ll;
	}
	
	// Remove e retorna uma dada entrada do dicionário.
	public Map.Entry<K, V> remove(Map.Entry<K, V> e) throws IllegalArgumentException {
		LinkedList<Map.Entry<K, V>> ll;
		
		if (e == null) {
			throw new IllegalArgumentException();
		}
		
		K key = e.getKey();
		ll = m.get(key);
		
		if (ll == null) {
			throw new IllegalArgumentException();
		}
		
		if (ll.remove(e)) {
			nSize--;
			if (ll.isEmpty()) {
				m.remove(key);
			}
			
			return e;
		} 
		else {
			throw new IllegalArgumentException();
		}
	}

	// Retorna um iterador contendo todas as entradas do dicionário.
	public Iterable<Map.Entry<K, V>> entrySet() {
		LinkedList<Map.Entry<K, V>> ll = new LinkedList<Map.Entry<K, V>>();

		for (LinkedList<Map.Entry<K, V>> sub : m.values()) {
			ll.addAll(sub);
		}
		
		return ll;
	}
}
