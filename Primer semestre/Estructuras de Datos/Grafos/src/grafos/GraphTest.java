package grafos;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

	
public class GraphTest {
			
	@Test
	public void testAddNode() {
		Graph<Integer> graph = new Graph<Integer>(5);
		
		assertEquals(0, graph.addNode(5));
		assertEquals(1, graph.getSize());
		assertEquals(-1, graph.addNode(5));
		assertEquals(1, graph.getSize());
	}

	@Test
	public void testGetNode() {
		Graph<Integer> graph = new Graph<Integer>(5);
		
		assertEquals(-1, graph.getNode(5));
		assertEquals(0, graph.addNode(5));
		assertEquals(0, graph.getNode(5));
	}

	@Test
	public void testAddEdge() {
		Graph<Integer> graph = new Graph<Integer>(5);

		assertEquals(0, graph.addNode(5));
		assertEquals(0, graph.addNode(4));
		assertEquals(0, graph.addEdges(5, 4, 1));
	}

	@Test
	public void testDijkstra() {
		Graph<Integer> graph = new Graph<Integer>(5);

		//Añadimos los nodos
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		
		//Añadimos las conexiones
		graph.addEdges(1, 2, 1);
		graph.addEdges(1, 5, 10);
		graph.addEdges(1, 4, 3);
		graph.addEdges(2, 3, 5);
		graph.addEdges(2, 2, 4);
		graph.addEdges(3, 5, 1);
		graph.addEdges(4, 3, 2);

		//Nodo 1, fuente
		Assert.assertArrayEquals(new double[]{0.0,1.0,5.0,3.0,6.0}, graph.dijkstra(1), 0);
		//Nodo 2
		Assert.assertArrayEquals(new double[]{Double.POSITIVE_INFINITY,0.0,5.0,Double.POSITIVE_INFINITY,6.0}, graph.dijkstra(2), 0);
		//Nodo 3
		Assert.assertArrayEquals(new double[]{Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0.0,Double.POSITIVE_INFINITY,1.0}, graph.dijkstra(3), 0);
		//Nodo 4
		Assert.assertArrayEquals(new double[]{Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,2.0,0.0,3.0}, graph.dijkstra(4), 0);
		//Nodo 5, sumidero
		Assert.assertArrayEquals(new double[]{Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0.0}, graph.dijkstra(5), 0);
		//Nodo 6, aislado
		graph.addNode(6);
		Assert.assertArrayEquals(null, graph.dijkstra(6), 0);
		//Añadimos arista y volvemos a calcular dijkstra
		graph.addEdges(5, 2, 1);
		//Nodo 1
		Assert.assertArrayEquals(new double[]{0.0,1.0,5.0,3.0,6.0}, graph.dijkstra(1), 0);
		//Nodo 2
		Assert.assertArrayEquals(new double[]{Double.POSITIVE_INFINITY,0.0,5.0,Double.POSITIVE_INFINITY,6.0}, graph.dijkstra(2), 0);
		//Nodo 3
		Assert.assertArrayEquals(new double[]{Double.POSITIVE_INFINITY,2.0,0.0,Double.POSITIVE_INFINITY,1.0}, graph.dijkstra(3), 0);
		//Nodo 4
		Assert.assertArrayEquals(new double[]{Double.POSITIVE_INFINITY,4.0,2.0,0.0,3.0}, graph.dijkstra(4), 0);
		//Nodo 5
		Assert.assertArrayEquals(new double[]{Double.POSITIVE_INFINITY,1.0,6.0,Double.POSITIVE_INFINITY,0.0}, graph.dijkstra(5), 0);
		
	}
	
	@Test
	public void testExitsNode() {
		Graph<Integer> graph = new Graph<Integer>(5);
		
		assertFalse(graph.existNode(5));
		assertEquals(0, graph.addNode(5));
		assertTrue(graph.existNode(5));
	}

	@Test
	public void testExitsEdge() {
		Graph<Integer> graph = new Graph<Integer>(5);
		
		assertFalse(graph.existEdge(5,3));
		assertEquals(0, graph.addNode(5));
		assertEquals(0, graph.addNode(3));
		assertEquals(0, graph.addEdges(5, 3, 10));
		assertTrue(graph.existEdge(5,3));
	}
	
	@Test
	public void testRemoveEdge() {
		Graph<Integer> graph = new Graph<Integer>(5);
		
		assertFalse(graph.existEdge(5,3));
		assertEquals(0, graph.addNode(5));
		assertEquals(0, graph.addNode(3));
		assertEquals(0, graph.addEdges(5, 3, 10));
		assertTrue(graph.existEdge(5,3));
		assertEquals(0, graph.removeEdge(5, 3));
		assertFalse(graph.existEdge(5,3));
	}
	
	@Test
	public void testRemoveNode() {
		Graph<Integer> graph = new Graph<Integer>(5);
		
		assertFalse(graph.existNode(5));
		assertEquals(0, graph.addNode(5));
		assertTrue(graph.existNode(5));
		assertEquals(0, graph.removeNode(5));
		assertFalse(graph.existNode(5));
	}
	
	@Test
	public void testGetEdge() {
		Graph<Integer> graph = new Graph<Integer>(5);
		
		assertFalse(graph.existEdge(5,3));
		assertEquals(0, graph.addNode(5));
		assertEquals(0, graph.addNode(3));
		assertEquals(0, graph.addEdges(5, 3, 10));
		assertEquals(10.0, graph.getEdge(5, 3), 0);
	}
	
	@Test
	public void testFloyd(){
		Graph<Integer> graph = new Graph<Integer>(6);
		double[][] result = new double[][]{{0.0, 3.0, 4.0, 12.0, 7.0, 10.0},
				                           {Double.POSITIVE_INFINITY, 0.0, Double.POSITIVE_INFINITY, 10.0, 5.0, 8.0},
				                           {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0, 8.0, 3.0,6.0},
				                           {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0 ,Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
				                           {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY , 5.0, 0.0, 3.0},
			                           	{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 2.0, Double.POSITIVE_INFINITY, 0.0}}; 
		
		//Añadimos los nodos
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addNode(6);
		
		//Añadimos las conexiones
		graph.addEdges(1, 2, 3);
		graph.addEdges(1, 3, 4);
		graph.addEdges(1, 5, 8);
		graph.addEdges(2, 5, 5);
		graph.addEdges(3, 5, 3);
		graph.addEdges(5, 4, 7);
		graph.addEdges(5, 6, 3);
		graph.addEdges(6, 4, 2);
		
		Assert.assertArrayEquals(result, graph.floyd());

	}
	
	@Test
	public void testDFS(){
		Graph<Integer> graph = new Graph<Integer>(6);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream current = System.out;
		System.setOut(new PrintStream(outContent));
		
		//Añadimos los nodos
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		
		//Añadimos las conexiones
		graph.addEdges(1, 2, 1);
		graph.addEdges(1, 4, 5);
		graph.addEdges(1, 5, 10);
		graph.addEdges(2, 2, 4);
		graph.addEdges(2, 3, 5);
		graph.addEdges(3, 5, 1);
		graph.addEdges(4, 3, 2);
		
		assertEquals(0,graph.deepFirstSearch(1));
		System.out.println();
		assertEquals(-1,graph.deepFirstSearch(2));
		System.out.println();
		assertEquals(-1,graph.deepFirstSearch(3));
		System.out.println();
		assertEquals(-1,graph.deepFirstSearch(4));
		System.out.println();
		assertEquals(-1,graph.deepFirstSearch(5));
		
		//Añadimos una arista
		graph.addEdges(2, 5, 1);
		
		Assert.assertEquals("", outContent.toString());
		outContent.reset();
		System.out.println();
		assertEquals(0,graph.deepFirstSearch(1));
		System.out.println();
		assertEquals(-1,graph.deepFirstSearch(2));
		System.out.println();
		assertEquals(-1,graph.deepFirstSearch(3));
		System.out.println();
		assertEquals(-1,graph.deepFirstSearch(4));
		System.out.println();
		assertEquals(-1,graph.deepFirstSearch(5));
		

	}
}
