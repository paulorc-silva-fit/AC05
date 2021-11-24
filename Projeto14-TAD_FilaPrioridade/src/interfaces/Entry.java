package interfaces;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 20/10/2021
 *
 */
public interface Entry<K, V> {
	// Retorna uma chave armazenada nesta entrada.
	public K getKey();

	// Retorna o valor armazenado nesta entrada.
	public V getValue();
}
