
package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import source.SortedListPriorityQueue;
import exceptions.EmptyPriorityQueueException;
import interfaces.Entry;

class SortedListPriorityQueueTest {
	@Test
	void test() {
		SortedListPriorityQueue<Integer, String> P = new SortedListPriorityQueue<Integer, String>();
		Entry<Integer, String> saida;

		saida = P.insert(5, "A");
		assertEquals("(5,A)", saida.toString());
		assertEquals("[(5,A)]", P.toString());

		saida = P.insert(9, "C");
		assertEquals("(9,C)", saida.toString());
		assertEquals("[(5,A), (9,C)]", P.toString());

		saida = P.insert(3, "B");
		assertEquals("(3,B)", saida.toString());
		assertEquals("[(3,B), (5,A), (9,C)]", P.toString());

		saida = P.insert(7, "D");
		assertEquals("(7,D)", saida.toString());
		assertEquals("[(3,B), (5,A), (7,D), (9,C)]", P.toString());

		saida = P.min();
		assertEquals("(3,B)", saida.toString());
		assertEquals("[(3,B), (5,A), (7,D), (9,C)]", P.toString());

		saida = P.removeMin();
		assertEquals("(3,B)", saida.toString());
		assertEquals("[(5,A), (7,D), (9,C)]", P.toString());

		assertEquals(3, P.size());

		saida = P.removeMin();
		assertEquals("(5,A)", saida.toString());
		assertEquals("[(7,D), (9,C)]", P.toString());

		saida = P.removeMin();
		assertEquals("(7,D)", saida.toString());
		assertEquals("[(9,C)]", P.toString());

		saida = P.removeMin();
		assertEquals("(9,C)", saida.toString());
		assertEquals("[]", P.toString());

		assertThrows(EmptyPriorityQueueException.class, () -> { P.removeMin(); });	
	}
	
	@Test
	void TesteOrdenaFilaPrioridade() {
		SortedListPriorityQueue<Integer, Integer> P = new SortedListPriorityQueue<Integer, Integer>();
		
		P.insert(9, 1);
		P.insert(1, 2);
		P.insert(3, 3);
		P.insert(6, 4);
		P.insert(2, 5);
		P.insert(7, 6);
		P.insert(8, 7);
		
		System.out.println(P.toString());
	} 
}
