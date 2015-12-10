package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import mocks.MockCancion;

import org.junit.Test;
import org.mockito.Mockito;

import contenido.Contenido;
import static org.mockito.Mockito.*;


/**
 * Tests de la clase MockCancion.
 */
public class CancionMockTest {
	
	/** Creamos un mock de cancion para probar. */
	MockCancion cancionMock = Mockito.mock(MockCancion.class);

	/**
	 * Obtener titulo test.
	 */
	@Test
	public void obtenerTituloConCancionMockTest() {
		when(cancionMock.obtenerTitulo()).thenReturn("Hello");
		assertTrue(cancionMock.obtenerTitulo().equals("Hello"));
	}
	
	/**
	 * Obtener duracion test.
	 */
	@Test
	public void obtenerDuracionConCancionMockTest() {
		when(cancionMock.obtenerDuracion()).thenReturn(197);
		assertTrue(Integer.compare(cancionMock.obtenerDuracion(),197)==0);
	}
	
	/**
	 * Obtener lista reproduccion test.
	 */
	@Test
	public void obtenerListaReproduccionConCancionMockTest() {
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(cancionMock); 
		when(cancionMock.obtenerListaReproduccion()).thenReturn(listaReproduccionIdeal);
		assertTrue(cancionMock.obtenerListaReproduccion().equals(listaReproduccionIdeal));
	}
	
	/**
	 * Buscar test.
	 */
	@Test
	public void buscarConCancionMockTest() {
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(cancionMock);
		when(cancionMock.buscar("Hello")).thenReturn(listaReproduccionIdeal);
		assertTrue(cancionMock.buscar("Hello").equals(listaReproduccionIdeal));
		listaReproduccionIdeal = new ArrayList<Contenido>();
		when(cancionMock.buscar(anyString())).thenReturn(listaReproduccionIdeal);
		assertTrue(cancionMock.buscar("OTRA COSA").equals(listaReproduccionIdeal));
	}
}
