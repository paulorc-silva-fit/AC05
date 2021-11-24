package source;

import java.util.ArrayList;
import java.util.Iterator;

import exceptions.BoundaryViolationException;
import exceptions.EmptyTreeException;
import exceptions.InvalidPositionException;
import interfaces.CompleteBinaryTree;
import interfaces.Position;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 27/10/2021
 *
 */
public class ArrayListCompleteBinaryTree<E> implements CompleteBinaryTree<E> {
	protected ArrayList<BTPos<E>> T; // Lista indexada dos posições da árvore

	// Classe aninhada para um indice para um nodo de árvore binária

	// completa baseado em lista.

	protected static class BTPos<E> implements Position<E> {
		E element; // elemento armazenado nesta posição
		int index; // índice desta posição na lista de array
	
		public BTPos(E elt, int i) {
			element = elt;
			index = i;
		}
	
		public E element() { 
			return element; 
		}
	
		public int index() { 
			return index; 
		}
	
		public E setElement(E elt) {
			E temp = element;
			element = elt;
		
			return temp;
		}
	
		public String toString() { 
			return ("[" + element + "," + index + "]"); 
		}

	}

	public ArrayListCompleteBinaryTree() {
		T = new ArrayList<BTPos<E>>();
	
		T.add(0, null); // a localização em rank 0 é considerada vazia
	}

	// Retorna o número de nodos da árvore binária
	public int size() { 
		return T.size() - 1; 
	}

	// Retorna se a árvore está vazia
	public boolean isEmpty() { 
		return (size() == 0);
	}

	// Retorna se v é um nodo interno	
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		return hasLeft(v); // Se v tiver um filho esquerdo, ele será interno
	}

	// Retorna se v é um nodo externo
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		return !isInternal(v);
	}

	// Determina se uma dada posição é um nodo válido
	protected BTPos<E> checkPosition(Position<E> v) throws InvalidPositionException {
		if (v == null || !(v instanceof BTPos)) {
			throw new InvalidPositionException("Position is invalid");
		}
		
		return (BTPos<E>) v;
	}

	// Retorna se v é raiz
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		BTPos<E> vv = checkPosition(v);
	
		return vv.index() == 1;
	}

	// Retorna se v tem um filho esquerdo
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTPos<E> vv = checkPosition(v);
	
		return 2 * vv.index() <= size();
	}

	// Retorna se v tem um filha direito
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTPos<E> vv = checkPosition(v);
	
		return 2 * vv.index() + 1 <= size();
	}

	// Returns the root of the tree.
	public Position<E> root() throws EmptyTreeException {
		if (isEmpty()) {
			throw new EmptyTreeException("Tree is empty");
		}
	
		return T.get(1);
	}

	// Retorna o filho esquerdo de v
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		if (!hasLeft(v)) {
			throw new BoundaryViolationException("No left child");
		}
	
		BTPos<E> vv = checkPosition(v);
	
		return T.get(2 * vv.index());
	}

	// Retorna o filho direito de v
	public Position<E> right(Position<E> v) throws InvalidPositionException {
		if (!hasRight(v)) {
			throw new BoundaryViolationException("No right child");
		}
	
		BTPos<E> vv = checkPosition(v);
	
		return T.get(2 * vv.index() + 1);
	}

	// Retorna o pai de v
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		if (isRoot(v)) {
			throw new BoundaryViolationException("No parent");
		}
	
		BTPos<E> vv = checkPosition(v);
	
		return T.get(vv.index() / 2);
	}

	// Retorna a coleção iterável de filhos de v
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		NodePositionList<Position<E>> children = new NodePositionList<Position<E>>();
	
		if (hasLeft(v)) {
			children.addLast(left(v));
		}
	
		if (hasRight(v)) {
			children.addLast(right(v));
		}
	
		return children;
	}

	// Retorna a coleção iterável de todos os nodos da árvore
	public Iterable<Position<E>> positions() {
		ArrayList<Position<E>> P = new ArrayList<Position<E>>();
		Iterator<BTPos<E>> iter = T.iterator();
	
		iter.next(); // Pula a primeira posição
	
		while (iter.hasNext()) {
			P.add(iter.next());
		}
	
		return P;
	}

	// Troca o elemento de v
	public E replace(Position<E> v, E o) throws InvalidPositionException {
		BTPos<E> vv = checkPosition(v);
	
		return vv.setElement(o);
	}

	// Adiciona um elemento exatamente após o último nodo
	public Position<E> add(E e) {
		int i = size() + 1;
		BTPos<E> p = new BTPos<E>(e, i);
	
		T.add(i, p);
	
		return p;
	}

	// Remove e retorna o elemento no do último nodo
	public E remove() throws EmptyTreeException {
		if (isEmpty()) {
			throw new EmptyTreeException("Tree is empty");
		}
	
		return T.remove(size()).element();
	}

	// Métodos adicionais
	// Retorna o irmão de v
	public Position<E> sibling(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		try {
			Position<E> p = parent(v);
			Position<E> lc = left(p);
		
			if (v == lc) {
				return right(p);
			}
			else {
				return lc;
			}
		} 
		catch (BoundaryViolationException e) { 
			throw new BoundaryViolationException("Node has no sibling"); 
		}
	}

	// Troca os elementos de dois nodos
	public void swapElements(Position<E> v, Position<E> w) throws InvalidPositionException {
		BTPos<E> vv = checkPosition(v);
		BTPos<E> ww = checkPosition(w);	
		E temp = vv.element();

		vv.setElement(ww.element());
		ww.setElement(temp);
	}

	// Retorna um iterator dos elementos armazenados em todos os nodos da árvore
	public Iterator<E> iterator() {
		ArrayList<E> list = new ArrayList<E>();
		Iterator<BTPos<E>> iter = T.iterator();
		iter.next(); // skip the first element
	
		while (iter.hasNext()) {
			list.add(iter.next().element());
		}
	
		return list.iterator();
	}

	// Retorna uma string representando esta árvore binária completa
	public String toString() { 
		return T.toString(); 
	}
}
