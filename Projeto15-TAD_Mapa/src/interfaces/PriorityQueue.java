package interfaces;

import exceptions.EmptyPriorityQueueException;
import exceptions.InvalidKeyException;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 27/10/2021
 *
 */
public interface PriorityQueue<K, V> {
	// Retorna a quantidade de itens na fila de prioridade
	public int size( );

	// Retorna se a fila de prioridade está vazia
	public boolean isEmpty( );

	// Retorna mas não remove uma entrada com chave mínima
	public Entry<K,V> min() throws EmptyPriorityQueueException;

	// Insere um par chave-valor e retorna a entrada criada
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException;

	// Remove e retorna uma entrada com chave mínima
	public Entry<K,V> removeMin( ) throws EmptyPriorityQueueException;
}