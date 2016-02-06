package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tablahash.HashTable;


public class HashTableTest {

	@Test
	public void testGetSize() {
		HashTable<Integer> b = new HashTable<Integer>(3);
		
		assertEquals(0, b.getNumElementos());
		assertTrue(b.add(3));
		assertEquals(1, b.getNumElementos());
	}

	@Test
	public void testAdd() {
		HashTable<Integer> b = new HashTable<Integer>(3);
		
		System.out.println("Añadir(3,6,2):");
		assertEquals(0, b.getNumElementos());
		assertTrue(b.add(3));
		assertEquals(1, b.getNumElementos());
		assertFalse(b.add(3));
		assertTrue(b.add(6));
		assertEquals(2, b.getNumElementos());
		assertTrue(b.add(2));
		assertEquals(3, b.getNumElementos());
		System.out.println(b.toString());
		
		b = new HashTable<Integer>(3);
		
		System.out.println("\nAñadir(8,10):");
		assertEquals(0, b.getNumElementos());
		assertTrue(b.add(8));
		assertEquals(1, b.getNumElementos());
		assertFalse(b.add(8));
		assertTrue(b.add(10));
		assertEquals(2, b.getNumElementos());
		System.out.println(b.toString());
	}

	@Test
	public void testFind() {
		HashTable<Integer> b = new HashTable<Integer>(3);
		
		System.out.println("\nBuscar:");
		assertEquals(0, b.getNumElementos());
		assertTrue(b.add(3));
		assertEquals(1, b.getNumElementos());
		assertTrue(b.find(3).equals(3));
		assertEquals(b.find(2), null);
		System.out.println(b.toString());
	}

	@Test
	public void testRemove() {
		HashTable<Integer> b = new HashTable<Integer>(3);
		
		System.out.println("\nBorrar(3,2):");
		assertEquals(0, b.getNumElementos());
		assertTrue(b.add(3));
		assertEquals(1, b.getNumElementos());
		assertTrue(b.add(2));
		assertEquals(2, b.getNumElementos());
		System.out.println(b.toString());
		assertTrue(b.remove(3));
		System.out.println(b.toString());
		assertTrue(b.remove(2));
		System.out.println(b.toString());
		
	}
	
	@Test
	public void testAddString() {
		HashTable<String> b = new HashTable<String>(3);
		
		System.out.println("\nAñadir(Hola, Melon, Pera, Manzana):");
		assertTrue(b.add("Hola"));
		assertEquals(1, b.getNumElementos());
		assertTrue(b.add("Melon"));
		assertEquals(2, b.getNumElementos());
		assertTrue(b.add("Pera"));
		assertEquals(3, b.getNumElementos());
		assertTrue(b.add("Manzana"));
		assertEquals(4, b.getNumElementos());
		System.out.println(b.toString());
	}
	
	@Test
	public void testRemoveString() {
		HashTable<String> b = new HashTable<String>(3);
		
		System.out.println("\nBorrar String:");
		assertTrue(b.add("Hola"));
		assertEquals(1, b.getNumElementos());
		assertTrue(b.add("Melon"));
		assertEquals(2, b.getNumElementos());
		assertTrue(b.add("Pera"));
		assertEquals(3, b.getNumElementos());
		assertTrue(b.add("Manzana"));
		assertEquals(4, b.getNumElementos());
		System.out.println(b.toString());
		assertTrue(b.remove("Hola"));
		assertEquals(3, b.getNumElementos());
		System.out.println(b.toString());
		assertFalse(b.add("Manzana"));
		assertEquals(3, b.getNumElementos());
		System.out.println(b.toString());
	}
	
	@Test
	public void testFindString() {
		HashTable<String> b = new HashTable<String>(3);
		
		System.out.println("\nBuscar String:");
		assertTrue(b.add("Hola"));
		assertEquals(1, b.getNumElementos());
		assertTrue(b.add("Melon"));
		assertEquals(2, b.getNumElementos());
		assertTrue(b.add("Pera"));
		assertEquals(3, b.getNumElementos());
		assertTrue(b.find("Hola").equals("Hola"));
		System.out.println(b.toString());
	}
	
	@Test
	public void testAdd2() {
		//Crea una tabla del tamaño 10 (numero no primo)
		HashTable<Integer> hashTable = new HashTable<Integer>(10);
		//Muestra la tabla. Debería estar vacia y ser de tamaño 11
		System.out.println(hashTable.toString());
		//Inserta elementos
		System.out.println("\nAñadir(8,10,..,88):");
		assertTrue(hashTable.add(8));
		assertTrue(hashTable.add(10));
		assertTrue(hashTable.add(66));
		assertTrue(hashTable.add(77));
		assertTrue(hashTable.add(7));
		assertTrue(hashTable.add(9));
		assertTrue(hashTable.add(88));
		System.out.println(hashTable.toString());
		//Borra un elemento
		assertTrue(hashTable.remove(8));
		System.out.println(hashTable.toString());
		assertTrue(hashTable.add(13));
		System.out.println(hashTable.toString());
		assertTrue(hashTable.add(19));
		System.out.println(hashTable.toString());
		//Inserta elemetos con colisiones
		assertFalse(hashTable.add(66));
		assertFalse(hashTable.add(88));
		//Borra un elemento que no existe
		assertFalse(hashTable.remove(2));
	}
	
	@Test
	public void testAdd3() {
		HashTable<Integer> hashTable = new HashTable<Integer>(5);
		System.out.println(hashTable.toString());
		assertEquals(5, hashTable.getB());
		assertEquals(0, hashTable.getNumElementos());
		assertTrue(hashTable.add(8));
		assertTrue(hashTable.add(10));
		assertTrue(hashTable.add(6));
		assertTrue(hashTable.add(7));
		assertTrue(hashTable.add(9));
		assertEquals(5, hashTable.getNumElementos());
		System.out.println(hashTable.toString());
		assertTrue(hashTable.remove(6));
		System.out.println(hashTable.toString());
		
	}

}
