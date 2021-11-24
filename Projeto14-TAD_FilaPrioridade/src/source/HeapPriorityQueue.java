package source;

import java.util.Comparator;
import exceptions.EmptyPriorityQueueException;
import exceptions.InvalidKeyException;
import interfaces.*;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 20/10/2021
 *
 */
public class HeapPriorityQueue<K, V> implements PriorityQueue<K, V> {
	protected CompleteBinaryTree<Entry<K, V>> heap; // underlying heap
	protected Comparator<K> comp; // comparator for the keys

	// Classe interna para entradas do heap
	protected static class MyEntry<K, V> implements Entry<K, V> {
		protected K key;
		protected V value;
		
		public MyEntry(K k, V v) {
			key = k;
			value = v;
		}

		public K getKey() { 
			return key; 
		}
	
		public V getValue() { 
			return value; 
		}
	
		public String toString() { 
			return "(" + key + "," + value + ")"; 
		}
	}

	// Cria um heap vazio com comparador padrão
	public HeapPriorityQueue() {
		heap = new ArrayListCompleteBinaryTree<Entry<K, V>>(); // Usa o ArrayListCompleteBinaryTree
	
		comp = new DefaultComparator<K>(); // usa o comparador padrão
	}

	// Cria um heap vazio com um comparador informado
	public HeapPriorityQueue(Comparator<K> c) {
		heap = new ArrayListCompleteBinaryTree<Entry<K, V>>();
	
		comp = c;
	}

	// Define o comparador usado para comparar itens no heap
	// @throws IllegalStateException se a fila de prioridade não estiver vazia
	public void setComparator(Comparator<K> c) throws IllegalStateException {
		if (!isEmpty()) { // Isto somente é permitido se a fila de prioridade estiver vazia
			throw new IllegalStateException("Priority queue is not empty");
		}
		
		comp = c;
	}

	// Retorna o tamanho do heap
	public int size() { 
		return heap.size(); 
	}

	// Retorna se o heap está vazio
	public boolean isEmpty() { 
		return heap.size() == 0; 
	}

	// Retorna mas não remove uma entrada com a chave mínima
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if (isEmpty()) {
			throw new EmptyPriorityQueueException("Priority queue is empty");
		}
	
		return heap.root().element();
	}

	// Insere um par chave-valor e retorna uma entrada criada
	public Entry<K, V> insert(K k, V x) throws InvalidKeyException {
		checkKey(k); // pode lançar um InvalidKeyException
	
		Entry<K, V> entry = new MyEntry<K, V>(k, x);
	
		upHeap(heap.add(entry));
	
		return entry;
	}

	// Remove e retona uma entrada com a chave mínima
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if (isEmpty()) {
			throw new EmptyPriorityQueueException("Priority queue is empty");
		}
	
		Entry<K, V> min = heap.root().element();
	
		if (size() == 1) {
			heap.remove();
		}
		else {
			heap.replace(heap.root(), heap.remove());
		
			downHeap(heap.root());
		}
	
		return min;
	}

	// Determina se uma dada chave é inválida
	protected void checkKey(K key) throws InvalidKeyException {
		try {
			comp.compare(key, key);
		} 
		catch (Exception e) {
			throw new InvalidKeyException("Invalid key");
		}
	}

	// Realiza o up-heap bubbling
	protected void upHeap(Position<Entry<K, V>> v) {
		Position<Entry<K, V>> u;
	
		while (!heap.isRoot(v)) {
			u = heap.parent(v);
	
			if (comp.compare(u.element().getKey(), v.element().getKey()) <= 0) {
				break;	
			}
			
			swap(u, v);
		
			v = u;
		}
	}

	// Realiza o down-heap bubbling
	protected void downHeap(Position<Entry<K, V>> r) {
		while (heap.isInternal(r)) {
			Position<Entry<K, V>> s; // a posição do menor filho
		
			if (!heap.hasRight(r)) {
				s = heap.left(r);
			}
			else if (comp.compare(heap.left(r).element().getKey(), heap.right(r).element().getKey()) <= 0) {
				s = heap.left(r);
			}
			else {
				s = heap.right(r);
			}
		
			if (comp.compare(s.element().getKey(), r.element().getKey()) < 0) {
				swap(r, s);
				r = s;
			}
			else {
				break;
			}
		}
	}

	// Troca as entradas de duas posições
	protected void swap(Position<Entry<K, V>> x, Position<Entry<K, V>> y) {
		Entry<K, V> temp = x.element();
	
		heap.replace(x, y.element());
	
		heap.replace(y, temp);
	}

	// Visualização textual para propósitos de depuração
	public String toString() {
		return heap.toString();
	}
}
