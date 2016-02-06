package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import AVL.*;


public class AVLTreeTest {

//	@Test
//	public void testAdd() {
//		AVLTree<Integer> b = new AVLTree<Integer>();
//		
//		assertTrue(b.add(10));
//		assertFalse(b.add(10));
//		assertTrue(b.add(5));
//		assertTrue(b.add(15));
//		assertTrue(b.add(14));
//		assertTrue(b.add(13));
//		assertTrue(b.add(12));
//		
//		System.out.println("Acabo");
//	}
//
//	@Test
//	public void testRemove() {
//		AVLTree<Integer> b = new AVLTree<Integer>();
//		
//		assertTrue(b.add(1));
//		assertTrue(b.add(2));
//		assertTrue(b.add(3));
//		assertTrue(b.add(4));
//		assertTrue(b.add(5));
//		assertTrue(b.add(6));
//		assertTrue(b.add(10));
//		assertTrue(b.add(11));
//		assertTrue(b.add(8));
//		assertTrue(b.add(7));
//		
//		assertTrue(b.remove(1));
//		assertTrue(b.remove(3));
//		assertTrue(b.remove(4));
//		assertTrue(b.remove(7));
//		assertTrue(b.remove(11));
//		assertTrue(b.remove(10));
//
//		
//		System.out.println("Acabo");
//	}
	
	@Test
	public void testEsAscendente(){
		AVLTree<Integer> b = new AVLTree<Integer>();
		
		System.out.println("Ascendente:");
		assertEquals(b.add(40).getInfo().compareTo(40),0);
		assertEquals(b.add(10).getInfo().compareTo(40),0);
		
		System.out.println(b.postOrder());
		
		assertTrue(b.esAscendente(40, 10));
		System.out.println("Fin Ascendente");
		
	}
	
	@Test
	public void testEsDescendente(){
		AVLTree<Integer> b = new AVLTree<Integer>();
		
		System.out.println("Descendente:");
		assertEquals(b.add(40).getInfo().compareTo(40),0);
		assertEquals(b.add(10).getInfo().compareTo(40),0);
		
		System.out.println(b.postOrder());
		
		assertTrue(b.esAscendente(40, 10));
		System.out.println("Fin Descendente");
		
	}
	
	@Test
	public void testNumAristas(){
		AVLTree<Integer> b = new AVLTree<Integer>();
		
		System.out.println("NumAristas:");
		assertEquals(b.add(40).getInfo().compareTo(40),0);
		assertEquals(b.add(10).getInfo().compareTo(40),0);
		assertEquals(b.add(60).getInfo().compareTo(40),0);
		assertEquals(b.add(20).getInfo().compareTo(40),0);
		assertEquals(b.add(30).getInfo().compareTo(40),0);
		
		System.out.println(b.postOrder());
		
		assertEquals(2, b.numAristas(40, 10));
		assertEquals(1, b.numAristas(40, 20));
		System.out.println("Fin numAristas");
		
	}
	
	@Test
	public void testNumNodos(){
		AVLTree<Integer> b = new AVLTree<Integer>();
		
		System.out.println("NumNodos:");
		assertEquals(b.add(40).getInfo().compareTo(40),0);
		assertEquals(b.add(10).getInfo().compareTo(40),0);
		assertEquals(b.add(60).getInfo().compareTo(40),0);
		assertEquals(b.add(20).getInfo().compareTo(40),0);
		assertEquals(b.add(30).getInfo().compareTo(40),0);
		
		System.out.println(b.postOrder());
		
		assertEquals(3, b.numNodos(40, 10));
		assertEquals(2, b.numNodos(40, 20));
		System.out.println("Fin numNodos");
		
	}
	
	@Test
	public void testTamaño(){
		AVLTree<Integer> b = new AVLTree<Integer>();
		
		System.out.println("Tamaño:");
		assertEquals(b.add(40).getInfo().compareTo(40),0);
		assertEquals(b.add(10).getInfo().compareTo(40),0);
		assertEquals(b.add(60).getInfo().compareTo(40),0);
		assertEquals(b.add(20).getInfo().compareTo(40),0);
		assertEquals(b.add(30).getInfo().compareTo(40),0);
		
		System.out.println(b.postOrder());
		
		assertEquals(5, b.tamaño());
		System.out.println("Fin tamaño");
		
	}
	
	@Test
	public void testNumHojas(){
		AVLTree<Integer> b = new AVLTree<Integer>();
		
		System.out.println("NumHojas:");
		assertEquals(b.add(40).getInfo().compareTo(40),0);
		assertEquals(b.add(10).getInfo().compareTo(40),0);
		assertEquals(b.add(60).getInfo().compareTo(40),0);
		assertEquals(b.add(20).getInfo().compareTo(40),0);
		assertEquals(b.add(30).getInfo().compareTo(40),0);
		
		System.out.println(b.postOrder());
		
		assertEquals(3, b.numHojas());
		System.out.println("Fin numHojas");
		
	}
	
	@Test
	public void testNodoMaximo(){
		AVLTree<Integer> b = new AVLTree<Integer>();
		
		System.out.println("NodoMaximo:");
		assertEquals(b.add(40).getInfo().compareTo(40),0);
		assertEquals(b.add(10).getInfo().compareTo(40),0);
		assertEquals(b.add(60).getInfo().compareTo(40),0);
		assertEquals(b.add(20).getInfo().compareTo(40),0);
		assertEquals(b.add(30).getInfo().compareTo(40),0);
		
		System.out.println(b.postOrder());
		
		assertTrue(b.nodoMaximo().equals(60));
		System.out.println("Fin nodoMaximo");
		
	}
	
	@Test
	public void testEstaLleno(){
		AVLTree<Integer> b = new AVLTree<Integer>();
		
		System.out.println("EstaLleno:");
		assertFalse(b.estaLleno());
		
		assertEquals(b.add(40).getInfo().compareTo(40),0);
		assertEquals(b.add(10).getInfo().compareTo(40),0);
		assertEquals(b.add(60).getInfo().compareTo(40),0);
		
		System.out.println(b.postOrder());
		
	    assertTrue(b.estaLleno());
		
		System.out.println("Fin estaLleno");
		
	}
	
	@Test
	public void testCamino(){
		AVLTree<Integer> b = new AVLTree<Integer>();
		
		System.out.println("Camino:");
		assertEquals(b.add(40).getInfo().compareTo(40),0);
		assertEquals(b.add(10).getInfo().compareTo(40),0);
		assertEquals(b.add(60).getInfo().compareTo(40),0);
		assertEquals(b.add(20).getInfo().compareTo(40),0);
		assertEquals(b.add(30).getInfo().compareTo(40),0);
		
		
		System.out.println(b.postOrder());
		
	    assertNotNull(b.camino(40, 10));
		
		System.out.println("Fin camino");
		
	}
	
	@Test
	public void testEsCompleto(){
		AVLTree<Integer> b = new AVLTree<Integer>();
		
		System.out.println("EsCompleto:");
		assertEquals(b.add(40).getInfo().compareTo(40),0);
		assertEquals(b.add(20).getInfo().compareTo(40),0);
		assertEquals(b.add(50).getInfo().compareTo(40),0);
		assertEquals(b.add(10).getInfo().compareTo(40),0);
		assertEquals(b.add(70).getInfo().compareTo(40),0);
		
		
		System.out.println(b.postOrder());
		
		assertFalse(b.esCompleto());
		
        b = new AVLTree<Integer>();
        
		assertEquals(b.add(40).getInfo().compareTo(40),0);
		assertEquals(b.add(10).getInfo().compareTo(40),0);
		assertEquals(b.add(60).getInfo().compareTo(40),0);
		assertEquals(b.add(20).getInfo().compareTo(40),0);
		assertEquals(b.add(30).getInfo().compareTo(40),0);

		System.out.println(b.postOrder());
		
		assertTrue(b.esCompleto());
		
		System.out.println("Fin esCompleto");
		
	}
	
	@Test
	public void testMostrarPorAltura(){
		AVLTree<Integer> b = new AVLTree<Integer>();
		
		System.out.println("MostrarPorAltura:");
		assertEquals(b.add(40).getInfo().compareTo(40),0);
		assertEquals(b.add(10).getInfo().compareTo(40),0);
		assertEquals(b.add(60).getInfo().compareTo(40),0);
		assertEquals(b.add(20).getInfo().compareTo(40),0);
		assertEquals(b.add(30).getInfo().compareTo(40),0);
		
		System.out.println(b.postOrder());
		
		b.mostrarPorAltura(0);
		b.mostrarPorAltura(1);
		b.mostrarPorAltura(2);
		System.out.println("Fin mostrarPorAltura");
		
	}
	
	@Test
	public void testNodoNivel(){
		AVLTree<Integer> b = new AVLTree<Integer>();
		
		System.out.println("NodoNivel:");
		assertEquals(b.add(40).getInfo().compareTo(40),0);
		assertEquals(b.add(10).getInfo().compareTo(40),0);
		assertEquals(b.add(60).getInfo().compareTo(40),0);
		assertEquals(b.add(20).getInfo().compareTo(40),0);
		assertEquals(b.add(30).getInfo().compareTo(40),0);
		
		System.out.println(b.postOrder());
		
		assertEquals(1, b.nodoNivel(0));
		assertEquals(2, b.nodoNivel(1));
		assertEquals(2, b.nodoNivel(2));
		System.out.println("Fin nodoNivel");
		
	}
	
	@Test
	public void testIdenticoA(){
		AVLTree<Integer> b = new AVLTree<Integer>();
		AVLTree<Integer> c = new AVLTree<Integer>();
		
		System.out.println("Identico A:");
		assertEquals(b.add(40).getInfo().compareTo(40),0);
		assertEquals(b.add(10).getInfo().compareTo(40),0);
		assertEquals(b.add(60).getInfo().compareTo(40),0);
		assertEquals(b.add(20).getInfo().compareTo(40),0);
		assertEquals(b.add(30).getInfo().compareTo(40),0);
		
		assertEquals(c.add(40).getInfo().compareTo(40),0);
		assertEquals(c.add(10).getInfo().compareTo(40),0);
		assertEquals(c.add(60).getInfo().compareTo(40),0);
		assertEquals(c.add(20).getInfo().compareTo(40),0);
		assertEquals(c.add(30).getInfo().compareTo(40),0);
		
		System.out.println(b.postOrder());
		System.out.println(c.postOrder());
		
		assertTrue(b.identicoA(c.getRaiz()));
		System.out.println("Fin nodoNivel");
		
	}
	
	@Test
	public void testAdd() {
		
		AVLTree<Integer> b = new AVLTree<Integer>();
		
		assertEquals(b.add(40).getInfo().compareTo(40),0);
		assertEquals(b.add(10).getInfo().compareTo(40),0);
		assertEquals(b.add(60).getInfo().compareTo(40),0);
		assertEquals(b.add(20).getInfo().compareTo(40),0);
		assertEquals(b.add(30).getInfo().compareTo(40),0);
		System.out.println(b.postOrder());
		assertEquals("10\t30\t20\t60\t40\t",b.postOrder());
		
		//Rotación Doble Izquierda 
		assertEquals(b.add(35).getInfo().compareTo(30),0);
		System.out.println(b.postOrder());
		assertEquals("10\t20\t35\t60\t40\t30\t",b.postOrder());
		
		//Rotación Simple Izquierda
		assertEquals(b.add(80).getInfo().compareTo(30),0); 
		assertEquals(b.add(90).getInfo().compareTo(30),0);
		System.out.println(b.postOrder());
		assertEquals("10\t20\t35\t60\t90\t80\t40\t30\t",b.postOrder());
		
		//Rotacion Doble Derecha
		assertEquals(b.add(50).getInfo().compareTo(30),0);
		System.out.println(b.postOrder());
		assertEquals("10\t20\t35\t50\t40\t90\t80\t60\t30\t",b.postOrder());

		//Insertar uno que ya existe
		assertEquals(b.add(50).getInfo().compareTo(30),0);
		System.out.println(b.postOrder());
		assertEquals("10\t20\t35\t50\t40\t90\t80\t60\t30\t",b.postOrder());
		
		//Borrar
		assertEquals(b.remove(90).getInfo().compareTo(30),0);
		System.out.println(b.postOrder());
		assertEquals("10\t20\t35\t50\t40\t80\t60\t30\t",b.postOrder());
		
		assertEquals(b.remove(35).getInfo().compareTo(30),0);
		System.out.println(b.postOrder());
		assertEquals("10\t20\t50\t40\t80\t60\t30\t",b.postOrder());

		assertEquals(b.remove(50).getInfo().compareTo(30),0);
		System.out.println(b.postOrder());
		assertEquals("10\t20\t40\t80\t60\t30\t",b.postOrder());

		assertEquals(b.remove(30).getInfo().compareTo(20),0);
		System.out.println(b.postOrder());
		assertEquals("10\t40\t80\t60\t20\t",b.postOrder());
	}
	
	boolean debug=false;
	boolean print=false;
	@Test
	public void test() {
		AVLTree <Integer> t = new AVLTree<Integer>();
			
		if (debug) System.out.println("ADD(200)");
		assertTrue(t.addB(200));
		if (print) System.out.println(t);
		assertEquals("200:FB=0\t",t.postOrderFB());
		if (debug) System.out.println("ADD(100)");
		assertTrue(t.addB(100));
		if (print) System.out.println(t);
		assertEquals("100:FB=0\t200:FB=-1\t",t.postOrderFB());
		if (debug) System.out.println("ADD(50)");
		assertTrue(t.addB(50));
		if (print) System.out.println(t);
		assertEquals("50:FB=0\t200:FB=0\t100:FB=0\t",t.postOrderFB());
		if (debug) System.out.println("ADD(30)");
		assertTrue(t.addB(30));
		if (print) System.out.println(t);
		assertEquals("30:FB=0\t50:FB=-1\t200:FB=0\t100:FB=-1\t",t.postOrderFB());
		if (debug) System.out.println("ADD(70)");
		assertTrue(t.addB(70));
		if (print) System.out.println(t);
		assertEquals("30:FB=0\t70:FB=0\t50:FB=0\t200:FB=0\t100:FB=-1\t",t.postOrderFB());
		if (debug) System.out.println("ADD(60)");
		assertTrue(t.addB(60));
		if (print) System.out.println(t);
		assertEquals("30:FB=0\t60:FB=0\t50:FB=0\t200:FB=0\t100:FB=1\t70:FB=0\t",t.postOrderFB());
		if (debug) System.out.println("ADD(65)");
		assertTrue(t.addB(65));
		if (print) System.out.println(t);
		assertEquals("30:FB=0\t65:FB=0\t60:FB=1\t50:FB=1\t200:FB=0\t100:FB=1\t70:FB=-1\t",t.postOrderFB());
		if (debug) System.out.println("ADD(67)");
		assertTrue(t.addB(67));
		if (print) System.out.println(t);
		assertEquals("30:FB=0\t60:FB=0\t67:FB=0\t65:FB=0\t50:FB=1\t200:FB=0\t100:FB=1\t70:FB=-1\t",t.postOrderFB());
		if (debug) System.out.println("ADD(55)");
		assertTrue(t.addB(55));
		if (print) System.out.println(t);
		assertEquals("30:FB=0\t55:FB=0\t50:FB=0\t67:FB=0\t65:FB=1\t60:FB=0\t200:FB=0\t100:FB=1\t70:FB=-1\t",t.postOrderFB());
		if (debug) System.out.println("ADD(68)");
		assertTrue(t.addB(68));
		if (print) System.out.println(t);
		assertEquals("30:FB=0\t55:FB=0\t50:FB=0\t65:FB=0\t68:FB=0\t67:FB=0\t60:FB=0\t200:FB=0\t100:FB=1\t70:FB=-1\t",t.postOrderFB());
		if (debug) System.out.println("ADD(64)");
		assertTrue(t.addB(64));
		if (print) System.out.println(t);
		assertEquals("30:FB=0\t55:FB=0\t50:FB=0\t64:FB=0\t65:FB=-1\t60:FB=0\t68:FB=0\t200:FB=0\t100:FB=1\t70:FB=1\t67:FB=0\t",t.postOrderFB());
		if (debug) System.out.println("ADD(20)");
		assertTrue(t.addB(20));
		if (print) System.out.println(t);
		assertEquals("20:FB=0\t30:FB=-1\t55:FB=0\t50:FB=-1\t64:FB=0\t65:FB=-1\t60:FB=-1\t68:FB=0\t200:FB=0\t100:FB=1\t70:FB=1\t67:FB=-1\t",t.postOrderFB());
	}
	
	@Test
	public void test2() {
		AVLTree <Integer> t = new AVLTree<Integer>();
		
		if (debug) System.out.println("ADD(200)");
		assertTrue(t.addB(200));
		if (print) System.out.println(t);
		assertEquals("200:FB=0\t",t.postOrderFB());
		if (debug) System.out.println("ADD(100)");
		assertTrue(t.addB(100));
		if (print) System.out.println(t);
		assertEquals("100:FB=0\t200:FB=-1\t",t.postOrderFB());
		if (debug) System.out.println("ADD(50)");
		assertTrue(t.addB(50));
		if (print) System.out.println(t);
		assertEquals("50:FB=0\t200:FB=0\t100:FB=0\t",t.postOrderFB());
		if (debug) System.out.println("ADD(30)");
		assertTrue(t.addB(30));
		if (print) System.out.println(t);
		assertEquals("30:FB=0\t50:FB=-1\t200:FB=0\t100:FB=-1\t",t.postOrderFB());
		if (debug) System.out.println("ADD(70)");
		assertTrue(t.addB(70));
		if (print) System.out.println(t);
		assertEquals("30:FB=0\t70:FB=0\t50:FB=0\t200:FB=0\t100:FB=-1\t",t.postOrderFB());
		if (debug) System.out.println("ADD(60)");
		assertTrue(t.addB(60));
		if (print) System.out.println(t);
		assertEquals("30:FB=0\t60:FB=0\t50:FB=0\t200:FB=0\t100:FB=1\t70:FB=0\t",t.postOrderFB());
		if (debug) System.out.println("ADD(65)");
		assertTrue(t.addB(65));
		if (print) System.out.println(t);
		assertEquals("30:FB=0\t65:FB=0\t60:FB=1\t50:FB=1\t200:FB=0\t100:FB=1\t70:FB=-1\t",t.postOrderFB());
		if (debug) System.out.println("ADD(67)");
		assertTrue(t.addB(67));
		if (print) System.out.println(t);
		assertEquals("30:FB=0\t60:FB=0\t67:FB=0\t65:FB=0\t50:FB=1\t200:FB=0\t100:FB=1\t70:FB=-1\t",t.postOrderFB());
		if (debug) System.out.println("ADD(55)");
		assertTrue(t.addB(55));
		if (print) System.out.println(t);
		assertEquals("30:FB=0\t55:FB=0\t50:FB=0\t67:FB=0\t65:FB=1\t60:FB=0\t200:FB=0\t100:FB=1\t70:FB=-1\t",t.postOrderFB());
		if (debug) System.out.println("ADD(68)");
		assertTrue(t.addB(68));
		if (print) System.out.println(t);
		assertEquals("30:FB=0\t55:FB=0\t50:FB=0\t65:FB=0\t68:FB=0\t67:FB=0\t60:FB=0\t200:FB=0\t100:FB=1\t70:FB=-1\t",t.postOrderFB());
		if (debug) System.out.println("ADD(64)");
		assertTrue(t.addB(64));
		if (print) System.out.println(t);
		assertEquals("30:FB=0\t55:FB=0\t50:FB=0\t64:FB=0\t65:FB=-1\t60:FB=0\t68:FB=0\t200:FB=0\t100:FB=1\t70:FB=1\t67:FB=0\t",t.postOrderFB());
		if (debug) System.out.println("ADD(20)");
		assertTrue(t.addB(20));
		if (print) System.out.println(t);
		assertEquals("20:FB=0\t30:FB=-1\t55:FB=0\t50:FB=-1\t64:FB=0\t65:FB=-1\t60:FB=-1\t68:FB=0\t200:FB=0\t100:FB=1\t70:FB=1\t67:FB=-1\t",t.postOrderFB());
		if (debug) System.out.println("REMOVE(68)");
		assertTrue(t.removeB(68));
		if (print) System.out.println(t);
		assertEquals("20:FB=0\t30:FB=-1\t55:FB=0\t50:FB=-1\t64:FB=0\t65:FB=-1\t70:FB=0\t200:FB=0\t100:FB=0\t67:FB=0\t60:FB=0\t",t.postOrderFB());
		if (debug) System.out.println("REMOVE(60)");
		assertTrue(t.removeB(60));
		if (print) System.out.println(t);
		assertEquals("20:FB=0\t50:FB=0\t30:FB=0\t64:FB=0\t65:FB=-1\t70:FB=0\t200:FB=0\t100:FB=0\t67:FB=0\t55:FB=1\t",t.postOrderFB());
		if (debug) System.out.println("REMOVE(50)");
		assertTrue(t.removeB(50));
		if (print) System.out.println(t);
		assertEquals("20:FB=0\t30:FB=-1\t64:FB=0\t65:FB=-1\t70:FB=0\t200:FB=0\t100:FB=0\t67:FB=0\t55:FB=1\t",t.postOrderFB());
		if (debug) System.out.println("REMOVE(30)");
		assertTrue(t.removeB(30));
		if (print) System.out.println(t);
		assertEquals("20:FB=0\t64:FB=0\t55:FB=0\t67:FB=0\t200:FB=0\t100:FB=1\t70:FB=1\t65:FB=1\t",t.postOrderFB());
		if (debug) System.out.println("REMOVE(65)");
		assertTrue(t.removeB(65));
		if (print) System.out.println(t);
		assertEquals("20:FB=0\t55:FB=-1\t67:FB=0\t200:FB=0\t100:FB=1\t70:FB=1\t64:FB=1\t",t.postOrderFB());
		if (debug) System.out.println("REMOVE(55)");
		assertTrue(t.removeB(55));
		if (print) System.out.println(t);
		assertEquals("20:FB=0\t67:FB=0\t64:FB=0\t200:FB=0\t100:FB=1\t70:FB=0\t",t.postOrderFB());
		if (debug) System.out.println("ADD(69)");
		assertTrue(t.addB(69));
		if (print) System.out.println(t);
		assertEquals("20:FB=0\t69:FB=0\t67:FB=1\t64:FB=1\t200:FB=0\t100:FB=1\t70:FB=-1\t",t.postOrderFB());
		if (debug) System.out.println("ADD(10)");
		assertTrue(t.addB(10));
		if (print) System.out.println(t);
		assertEquals("10:FB=0\t20:FB=-1\t69:FB=0\t67:FB=1\t64:FB=0\t200:FB=0\t100:FB=1\t70:FB=-1\t",t.postOrderFB());
		if (debug) System.out.println("REMOVE(100)");
		assertTrue(t.removeB(100));
		if (print) System.out.println(t);
		assertEquals("10:FB=0\t64:FB=0\t20:FB=0\t69:FB=0\t200:FB=0\t70:FB=0\t67:FB=0\t",t.postOrderFB());
		if (debug) System.out.println("REMOVE(67)");
		assertTrue(t.removeB(67));
		if (print) System.out.println(t);
		assertEquals("10:FB=0\t20:FB=-1\t69:FB=0\t200:FB=0\t70:FB=0\t64:FB=0\t",t.postOrderFB());
		if (debug) System.out.println("REMOVE(64)");
		assertTrue(t.removeB(64));
		if (print) System.out.println(t);
		assertEquals("10:FB=0\t69:FB=0\t200:FB=0\t70:FB=0\t20:FB=1\t",t.postOrderFB());
		if (debug) System.out.println("REMOVE(200)");
		assertTrue(t.removeB(200));
		if (print) System.out.println(t);
		assertEquals("10:FB=0\t69:FB=0\t70:FB=-1\t20:FB=1\t",t.postOrderFB());
		if (debug) System.out.println("REMOVE(20)");
		assertTrue(t.removeB(20));
		if (print) System.out.println(t);
		assertEquals("10:FB=0\t70:FB=0\t69:FB=0\t",t.postOrderFB());
		if (debug) System.out.println("ADD(35)");
		assertTrue(t.addB(35));
		if (print) System.out.println(t);
		assertEquals("35:FB=0	10:FB=1	70:FB=0	69:FB=-1	",t.postOrderFB());
		if (debug) System.out.println("ADD(5)");
		assertTrue(t.addB(5));
		if (print) System.out.println(t);
		assertEquals("5:FB=0	35:FB=0	10:FB=0	70:FB=0	69:FB=-1	",t.postOrderFB());
		if (debug) System.out.println("ADD(85)");
		assertTrue(t.addB(85));
		if (print) System.out.println(t);
		assertEquals("5:FB=0	35:FB=0	10:FB=0	85:FB=0	70:FB=1	69:FB=0	",t.postOrderFB());
		if (debug) System.out.println("ADD(25)");
		assertTrue(t.addB(25));
		if (print) System.out.println(t);
		assertEquals("5:FB=0	25:FB=0	35:FB=-1	10:FB=1	85:FB=0	70:FB=1	69:FB=-1	",t.postOrderFB());
		if (debug) System.out.println("REMOVE(70)");
		assertTrue(t.removeB(70));
		if (print) System.out.println(t);
		assertEquals("5:FB=0	25:FB=0	10:FB=0	85:FB=0	69:FB=1	35:FB=0	",t.postOrderFB());
		if (debug) System.out.println("ADD(30)");
		assertTrue(t.addB(30));
		if (print) System.out.println(t);
		assertEquals("5:FB=0	30:FB=0	25:FB=1	10:FB=1	85:FB=0	69:FB=1	35:FB=-1	",t.postOrderFB());
		if (debug) System.out.println("ADD(1)");
		assertTrue(t.addB(1));
		if (print) System.out.println(t);
		assertEquals("1:FB=0	5:FB=-1	30:FB=0	25:FB=1	10:FB=0	85:FB=0	69:FB=1	35:FB=-1	",t.postOrderFB());
		if (debug) System.out.println("REMOVE(85)");
		assertTrue(t.removeB(85));
		if (print) System.out.println(t);
		assertEquals("1:FB=0	10:FB=0	5:FB=0	30:FB=0	69:FB=0	35:FB=0	25:FB=0	",t.postOrderFB());
		if (debug) System.out.println("REMOVE(25)");
		assertTrue(t.removeB(25));
		if (print) System.out.println(t);
		assertEquals("1:FB=0	5:FB=-1	30:FB=0	69:FB=0	35:FB=0	10:FB=0	",t.postOrderFB());
		if (debug) System.out.println("REMOVE(10)");
		assertTrue(t.removeB(10));
		if (print) System.out.println(t);
		assertEquals("1:FB=0	30:FB=0	69:FB=0	35:FB=0	5:FB=1	",t.postOrderFB());
		if (debug) System.out.println("REMOVE(5)");
		assertTrue(t.removeB(5));
		if (print) System.out.println(t);
		assertEquals("1:FB=0	69:FB=0	35:FB=1	30:FB=1	",t.postOrderFB());
		if (debug) System.out.println("REMOVE(30)");
		assertTrue(t.removeB(30));
		if (print) System.out.println(t);
		assertEquals("1:FB=0	69:FB=0	35:FB=0	",t.postOrderFB());
		if (debug) System.out.println("REMOVE(35)");
		assertTrue(t.removeB(35));
		if (print) System.out.println(t);
		assertEquals("69:FB=0	1:FB=1	",t.postOrderFB());
		if (debug) System.out.println("REMOVE(1)");
		assertTrue(t.removeB(1));
		assertFalse(t.removeB(1));
		if (print) System.out.println(t);
		assertEquals("69:FB=0\t",t.postOrderFB());
		if (debug) System.out.println("REMOVE(69)");
		assertTrue(t.removeB(69));
		if (print) System.out.println(t);
		assertEquals("",t.postOrderFB());
	}

}
