package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import otros.EDBinaryHeap;


public class EDBinaryHeapTest {

	@Test
	public void testAdd() {
		EDBinaryHeap<Integer> b = new EDBinaryHeap<Integer>(4);
		
		assertTrue(b.add(3));
		assertTrue(b.add(4));
		assertTrue(b.add(1));
		assertTrue(b.add(2));
		assertFalse(b.add(1));
		assertFalse(b.add(4));
	}
	
	@Test
	public void testAscendete() {
		EDBinaryHeap<Integer> b = new EDBinaryHeap<Integer>(4);
		
		assertTrue(b.add(3));
		assertTrue(b.add(4));
		assertTrue(b.add(1));
		assertTrue(b.add(2));
		System.out.println("Ascendete");
		System.out.println(b.toString());
		assertTrue(b.esAscendente(1, 2));
		assertTrue(b.esAscendente(1, 3));
	}
	
	@Test
	public void testDescendete() {
		EDBinaryHeap<Integer> b = new EDBinaryHeap<Integer>(4);
		
		assertTrue(b.add(3));
		assertTrue(b.add(4));
		assertTrue(b.add(1));
		assertTrue(b.add(2));
		System.out.println("Descendete");
		System.out.println(b.toString());
		assertTrue(b.esDescendente(2, 1));
		assertTrue(b.esDescendente(3, 1));
	}
	
	@Test
	public void testNumAristas() {
		EDBinaryHeap<Integer> b = new EDBinaryHeap<Integer>(4);
		
		assertTrue(b.add(3));
		assertTrue(b.add(4));
		assertTrue(b.add(1));
		assertTrue(b.add(2));
		System.out.println("NumAristas");
		System.out.println(b.toString());
		assertEquals(1,b.numAristas(1, 2));
		assertEquals(1,b.numAristas(1, 3));
		assertEquals(0,b.numAristas(1, 1));
		assertEquals(2,b.numAristas(1, 4));
	}
	
	@Test
	public void testNodoMaximo() {
		EDBinaryHeap<Integer> b = new EDBinaryHeap<Integer>(4);
		
		assertTrue(b.add(3));
		assertTrue(b.add(4));
		assertTrue(b.add(1));
		assertTrue(b.add(2));
		System.out.println("NodoMaximo");
		System.out.println(b.toString());
		assertTrue(b.nodoMaximo().equals(4));
	}
	
	@Test
	public void testIdenticoA() {
		EDBinaryHeap<Integer> b = new EDBinaryHeap<Integer>(4);
		
		assertTrue(b.add(3));
		assertTrue(b.add(4));
		assertTrue(b.add(1));
		assertTrue(b.add(2));
		System.out.println("Identico a");
		System.out.println(b.toString());
		Integer[] monticulo = new Integer[]{1 ,2 ,3, 4};
		assertTrue(b.identicoA(monticulo));
		monticulo = new Integer[]{1 ,2 ,3};
		assertFalse(b.identicoA(monticulo));
		monticulo = new Integer[]{1 ,2 ,3, 5};
		assertFalse(b.identicoA(monticulo));
	}
	
	@Test
	public void testMostrarPorAltura() {
		EDBinaryHeap<Integer> b = new EDBinaryHeap<Integer>(4);
		
		assertTrue(b.add(3));
		assertTrue(b.add(4));
		assertTrue(b.add(1));
		assertTrue(b.add(2));
		System.out.println("Mostrar por altura");
		System.out.println(b.toString());
		b.mostrarPorAltura(0);
		b.mostrarPorAltura(1);
		b.mostrarPorAltura(2);
	}
	
	@Test
	public void testNodoNivel() {
		EDBinaryHeap<Integer> b = new EDBinaryHeap<Integer>(5);
		
		assertTrue(b.add(3));
		assertTrue(b.add(4));
		assertTrue(b.add(1));
		assertTrue(b.add(2));
		System.out.println("nodoNivel");
		System.out.println(b.toString());
		assertEquals(b.nodoNivel(0),1);
		assertEquals(b.nodoNivel(1),2);
		assertEquals(b.nodoNivel(2),1);
	}
	
	@Test
	public void testNumHojas() {
		EDBinaryHeap<Integer> b = new EDBinaryHeap<Integer>(10);
		
		assertTrue(b.add(3));
		assertTrue(b.add(1));
		assertTrue(b.add(2));
		assertTrue(b.add(5));
		assertTrue(b.add(6));
		assertTrue(b.add(7));
		assertTrue(b.add(8));
		assertTrue(b.add(9));
		assertTrue(b.add(10));
		assertTrue(b.add(4));
		System.out.println("numHojas");
		System.out.println(b.toString());
		assertEquals(3, b.numHojas());
	}
	
	@Test
	public void testCamino() {
		EDBinaryHeap<Integer> b = new EDBinaryHeap<Integer>(10);
		
		assertTrue(b.add(3));
		assertTrue(b.add(1));
		assertTrue(b.add(2));
		assertTrue(b.add(5));
		assertTrue(b.add(6));
		assertTrue(b.add(7));
		assertTrue(b.add(8));
		assertTrue(b.add(9));
		assertTrue(b.add(10));
		assertTrue(b.add(4));
		System.out.println("Camino:");
		System.out.println(b.toString());
		assertNotNull(b.camino(1, 6));
	}
	
	@Test
	public void testEsCompleto() {
		EDBinaryHeap<Integer> b = new EDBinaryHeap<Integer>(5);
		
		assertTrue(b.add(3));
		assertTrue(b.add(4));
		assertTrue(b.add(1));
		assertTrue(b.add(2));
		System.out.println("esCompleto");
		System.out.println(b.toString());
		assertFalse(b.esCompleto());
		assertTrue(b.add(5));
		assertTrue(b.esCompleto());
		
	}
	
	@Test
	public void testTodo() {
		EDBinaryHeap<Integer> m= new EDBinaryHeap<Integer>(15);
		//Comprobar si el montículo está vacio
		assertTrue(m.isEmpty());
		//Intenta borrar en un montículo vacio
		assertNull(m.poll());
		assertFalse(m.remove(2));
		
		//Añadir elementos al montículo
		assertTrue(m.add(13));
		assertTrue(m.add(21));
		assertTrue(m.add(16));
		assertTrue(m.add(24));
		assertTrue(m.add(31));
		assertTrue(m.add(25));
		assertTrue(m.add(50));
		assertTrue(m.add(65));
		//Elemento que ya existe
		assertFalse(m.add(65));
		
		//Inserta un valor que es menor que el padre. Intercambia
		assertTrue(m.add(18));
		assertEquals("13\t18\t16\t21\t31\t25\t50\t65\t24\t",m.toString());
		System.out.println(m.toString());
		
		//Borra un elemento que no existe
		assertFalse(m.remove(400));
		//Borra la raiz del arbol
		assertEquals(m.poll().compareTo(13),0);
		assertEquals("16\t18\t24\t21\t31\t25\t50\t65\t",m.toString());
		System.out.println(m.toString());
		
		//Borra una hoja
		assertTrue(m.remove(65));
		assertEquals("16\t18\t24\t21\t31\t25\t50\t",m.toString());
		System.out.println(m.toString());
		
		//Borra un nodo intermedio
		assertTrue(m.remove(24));
		assertEquals("16\t18\t25\t21\t31\t50\t",m.toString());
		System.out.println(m.toString());
		
		assertTrue(m.add(100));
		assertTrue(m.add(101));
		assertTrue(m.add(102));
		assertTrue(m.add(103));
		assertTrue(m.add(104));
		assertTrue(m.add(105));
		assertTrue(m.add(106));
		assertTrue(m.add(107));		
		assertTrue(m.add(108));
		//Monticulo lleno
		assertFalse(m.add(200));
	}

	@Test
	public void testPoll() {
		EDBinaryHeap<Integer> b = new EDBinaryHeap<Integer>(4);
		
		assertTrue(b.add(4));
		assertTrue(b.add(3));
		assertTrue(b.add(2));
		assertTrue(b.add(1));
		
		assertEquals(b.poll().intValue(), 1);
		assertEquals(b.poll().intValue(), 2);
		assertEquals(b.poll().intValue(), 3);
		assertEquals(b.poll().intValue(), 4);
	}

	@Test
	public void testRemove() {
		EDBinaryHeap<Integer> b = new EDBinaryHeap<Integer>(4);
		
		assertTrue(b.add(4));
		assertTrue(b.add(3));
		assertTrue(b.add(2));
		assertTrue(b.add(1));
		
		assertTrue(b.remove(3));
		assertFalse(b.remove(3));
		assertTrue(b.remove(1));
		assertTrue(b.remove(4));
		assertTrue(b.remove(2));
	}

	@Test
	public void testIsEmpty() {
		EDBinaryHeap<Integer> b = new EDBinaryHeap<Integer>(4);
		
		assertTrue(b.isEmpty());
		assertTrue(b.add(4));
		assertFalse(b.isEmpty());
	}

	@Test
	public void testClear() {
		EDBinaryHeap<Integer> b = new EDBinaryHeap<Integer>(4);
		
		assertTrue(b.isEmpty());
		assertTrue(b.add(4));
		assertFalse(b.isEmpty());
		b.clear();
		assertTrue(b.isEmpty());
	}

}
