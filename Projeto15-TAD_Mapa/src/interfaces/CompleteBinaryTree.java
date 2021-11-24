package interfaces;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 27/10/2021
 *
 */
public interface CompleteBinaryTree<E> extends BinaryTree<E> {
	// Adiciona um elemento à árvore após o último nodo. Retorna a nova posição criada.
	public Position<E> add(E elem);

	// Remove e retorna o elemento armazenado no último nodo da árvoere.
	public E remove();
}
