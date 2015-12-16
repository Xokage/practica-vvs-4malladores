package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


import org.junit.Test;
import org.mockito.Mockito;

import contenido.Contenido;
import static org.mockito.Mockito.*;
import contenido.*;


/**
 * Tests de la clase MockAnuncio.
 */
public class AnuncioMockTest {
	
	/** Creamos un mock de anuncio para probar. */
	Anuncio anuncioMock = mock(Anuncio.class);

	/**
	 * Obtener titulo test.
	 */
	@Test
	public void obtenerTituloConAnuncioMockTest() {
		when(anuncioMock.obtenerTitulo()).thenReturn("PUBLICIDAD");
		assertTrue(anuncioMock.obtenerTitulo().equals("PUBLICIDAD"));
	}
	
	/**
	 * Obtener duracion test.
	 */
	@Test
	public void obtenerDuracionConAnuncioMockTest() {
		when(anuncioMock.obtenerDuracion()).thenReturn(5);
		assertTrue(Integer.compare(anuncioMock.obtenerDuracion(),5)==0);
	}
	
	/**
	 * Obtener lista reproduccion test.
	 */
	@Test
	public void obtenerListaReproduccionConAnuncioMockTest() {
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(anuncioMock); 
		when(anuncioMock.obtenerListaReproduccion()).thenReturn(listaReproduccionIdeal);
		assertTrue(anuncioMock.obtenerListaReproduccion().equals(listaReproduccionIdeal));
	}
	
	/**
	 * Buscar test.
	 */
	@Test
	public void buscarConAnuncioMockTest() {
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(anuncioMock);
		when(anuncioMock.buscar("PUBLICIDAD")).thenReturn(listaReproduccionIdeal);
		assertTrue(anuncioMock.buscar("PUBLICIDAD").equals(listaReproduccionIdeal));
		listaReproduccionIdeal = new ArrayList<Contenido>();
		when(anuncioMock.buscar(anyString())).thenReturn(listaReproduccionIdeal);
		assertTrue(anuncioMock.buscar("OTRA COSA").equals(listaReproduccionIdeal));
	}
}
