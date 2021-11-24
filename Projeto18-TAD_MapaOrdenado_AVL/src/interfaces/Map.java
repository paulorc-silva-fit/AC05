package interfaces;

import exceptions.InvalidKeyException;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 17/11/2021
 *
 */
public interface Map<K,V> {
	// Retorna o número de itens do mapa
	public int size();

	// Retorna se o mapa está vazio
	public boolean isEmpty();

	// Coloca uma par chave-valor no mapa, trocando o anterior,
	// se houver algum e retorna o valor anterior
	public V put(K key, V value) throws InvalidKeyException;

	// Retorna o valor associado com uma chave
	public V get(K key) throws InvalidKeyException;

	// Remove o par chave-valor de uma dada chave
	public V remove(K key) throws InvalidKeyException;

	// Retorna um objeto iterable para todoas as chaves do mapa
	public Iterable<K> keySet();

	// Retorna um objeto iterable para todos os valores do mapa
	public Iterable<V> values();
}
