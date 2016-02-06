package test;
import static org.junit.Assert.*;

import org.junit.Test;

import BS.BSTree;


public class BSTreeTest {

	@Test
	public void testAdd() {
		BSTree<Integer> b = new BSTree<Integer>();
		
		//A�adimos nodos al arbol
		assertTrue(b.add(10));
		assertTrue(b.add(5));
		assertTrue(b.add(3));
		assertFalse(b.add(3)); //Ya insertado
		assertTrue(b.add(7));
		assertTrue(b.add(15));
		assertTrue(b.add(12));
		assertTrue(b.add(20));
		assertTrue(b.add(21));
		assertTrue(b.add(1));
		assertTrue(b.add(11));
	}
	
	@Test
	public void testInOrden(){
		BSTree<Integer> b = new BSTree<Integer>();
		
		//A�adimos nodos al arbol
		assertTrue(b.add(10));
		assertTrue(b.add(5));
		assertTrue(b.add(3));
		assertTrue(b.add(7));
		assertTrue(b.add(15));
		assertTrue(b.add(12));
		assertTrue(b.add(20));
		assertTrue(b.add(21));
		assertTrue(b.add(1));
		assertTrue(b.add(11));
		
		//Comprobamos el inOrden
		assertTrue(b.inOrden().equals("1\t3\t5\t7\t10\t11\t12\t15\t20\t21\t"));
	}

	@Test
	public void testPosOrden(){
		BSTree<Integer> b = new BSTree<Integer>();
		
		//A�adimos nodos al arbol
		assertTrue(b.add(10));
		assertTrue(b.add(5));
		assertTrue(b.add(3));
		assertTrue(b.add(7));
		assertTrue(b.add(15));
		assertTrue(b.add(12));
		assertTrue(b.add(20));
		assertTrue(b.add(21));
		assertTrue(b.add(1));
		assertTrue(b.add(11));
		
		//Comprobamos el postOrden
		assertTrue(b.postOrden().equals("1\t3\t7\t5\t11\t12\t21\t20\t15\t10\t"));
	}
	
	@Test
	public void testPreOrden(){
		BSTree<Integer> b = new BSTree<Integer>();
		
		//A�adimos nodos al arbol
		assertTrue(b.add(10));
		assertTrue(b.add(5));
		assertTrue(b.add(3));
		assertTrue(b.add(7));
		assertTrue(b.add(15));
		assertTrue(b.add(12));
		assertTrue(b.add(20));
		assertTrue(b.add(21));
		assertTrue(b.add(1));
		assertTrue(b.add(11));
		
		//Comprobamos el preOrden
		assertTrue(b.preOrden().equals("10\t5\t3\t1\t7\t15\t12\t11\t20\t21\t"));
	}
	
	@Test
	public void testRemove(){
		BSTree<Integer> b = new BSTree<Integer>();
		
		//A�adimos nodos al arbol
		assertTrue(b.add(10));
		assertTrue(b.add(5));
		assertTrue(b.add(3));
		assertTrue(b.add(7));
		
		//Borramos
		assertFalse(b.remove(1)); //No existe
		assertTrue(b.remove(5));
		assertTrue(b.add(5));     //Tratamos de a�adir uno ya borrado
		assertFalse(b.add(3));
		assertFalse(b.add(7));
		assertTrue(b.remove(10));
		assertFalse(b.add(3));
		assertFalse(b.add(7));
		assertTrue(b.add(10));
		assertFalse(b.add(5));
	}
	
	@Test
	public void testUnit3(){
		BSTree<Integer> b = new BSTree<Integer>();
		String res;
				
		assertTrue(b.add(10));
		assertTrue(b.add(5));
		assertTrue(b.add(3));
		assertTrue(b.add(7));
		assertTrue(b.add(15));
		assertTrue(b.add(12));
		assertTrue(b.add(20));
		assertTrue(b.add(21));
		assertTrue(b.add(1));
		assertTrue(b.add(11));
		assertFalse(b.add(11)); //Ya insertado

		assertEquals(b.find(3).getInfo().compareTo(3),0);
		assertEquals(b.find(33),null); //No existe el nodo

		res=b.inOrden();
		System.out.println(res);
		assertEquals("1\t3\t5\t7\t10\t11\t12\t15\t20\t21\t",res);
			
		res=b.postOrden();
		System.out.println(res);
		assertEquals("1\t3\t7\t5\t11\t12\t21\t20\t15\t10\t",res);
				
		res=b.preOrden();
		System.out.println(res);
		assertEquals("10\t5\t3\t1\t7\t15\t12\t11\t20\t21\t",res);

	}
}
