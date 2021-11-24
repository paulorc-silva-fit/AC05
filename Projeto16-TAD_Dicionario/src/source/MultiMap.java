package source;

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
public interface MultiMap<K, V> {
	// Retorna a quantidade de entradas no dicionário
	public int size(); 
	
	// Retorna se o dicionário está vazio
	public boolean isEmpty();
	
	// Retorna uma entrada contendo uma dada chave ou null se não existe a entrada
	public Map.Entry<K,V> get(K k) throws IllegalArgumentException; 
	
	// Retorna um iterador contendo todas as entradas contendo uma certa chave ou um iterador
	// vazio se a entrada não existir.	
	public Iterable<Map.Entry<K,V>> getAll(K k) throws IllegalArgumentException; 

	// Insere um item em um dicionário. Retorna uma entrada recentemente criada
	public Map.Entry<K,V> put(K k, V v) throws IllegalArgumentException; 

	// Remove e retorna uma dada entrada do dicionário.
	public Map.Entry<K,V> remove(Map.Entry<K,V> e) throws IllegalArgumentException; 
	
	// Retorna um iterador contendo todas as entradas do dicionário.
	public Iterable<Map.Entry<K,V>> entrySet();
}