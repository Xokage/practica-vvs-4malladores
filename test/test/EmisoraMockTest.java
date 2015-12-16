package test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import contenido.Emisora;

import org.junit.Test;
import org.mockito.Mockito;

import contenido.Anuncio;
import contenido.Cancion;
import contenido.Contenido;
import contenido.Emisora;
import static org.mockito.Mockito.*;


/**
 * Tests de la clase Emisora.
 */
public class EmisoraMockTest {
	
	/** El mock de emisora. */
	Emisora emisoraMock = mock(Emisora.class);


	/**
	 * Obtener titulo test.
	 */
	@Test
	public void obtenerTituloConEmisoraMockTest() {
		when(emisoraMock.obtenerTitulo()).thenReturn("40 principales");
		assertTrue(emisoraMock.obtenerTitulo().equals("40 principales"));
	}
	
	/**
	 * Obtener duracion test.
	 */
	@Test
	public void obtenerDuracionConEmisoraMockTest() {
		when(emisoraMock.obtenerDuracion()).thenReturn(3);
		assertTrue(Integer.compare(emisoraMock.obtenerDuracion(),3)==0);
	}
	
	/**
	 * Obtener lista reproduccion test.
	 */
	@Test
	public void obtenerListaReproduccionConEmisoraMockTest() {
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		Cancion cancionMock = Mockito.mock(Cancion.class);
		listaReproduccionIdeal.add(cancionMock);
		when(emisoraMock.obtenerListaReproduccion()).thenReturn(listaReproduccionIdeal);
		assertTrue(emisoraMock.obtenerListaReproduccion().equals(listaReproduccionIdeal));
	}
	
	/**
	 * Buscar test.
	 */
	@Test
	public void buscarConEmisoraMockTest() {
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		Cancion cancionMock = Mockito.mock(Cancion.class);
		listaReproduccionIdeal.add(cancionMock);
		when(emisoraMock.buscar("Hello")).thenReturn(listaReproduccionIdeal);
		assertTrue(emisoraMock.buscar("Hello").equals(listaReproduccionIdeal));
		listaReproduccionIdeal = new ArrayList<Contenido>();
		assertTrue(emisoraMock.buscar("otra emisora").equals(listaReproduccionIdeal));
	}
	
	/**
	 * Agregar test.
	 */
	@Test
	public void agregarConEmisoraMockTest() {
		Cancion cancionMock = Mockito.mock(Cancion.class);
		Anuncio anuncioMock = Mockito.mock(Anuncio.class);
		emisoraMock.agregar(cancionMock, null);
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(cancionMock);
		when(emisoraMock.obtenerListaReproduccion()).thenReturn(listaReproduccionIdeal);
		assertTrue(emisoraMock.obtenerListaReproduccion().equals(listaReproduccionIdeal));
		emisoraMock.agregar(anuncioMock,null);
		listaReproduccionIdeal.add(0,anuncioMock);
		assertTrue(emisoraMock.obtenerListaReproduccion().equals(listaReproduccionIdeal));
	}
	
	/**
	 * Eliminar test.
	 */
	@Test
	public void eliminarConEmisoraMockTest() {
		Cancion cancionMock = Mockito.mock(Cancion.class);
		emisoraMock.agregar(cancionMock, null);
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(cancionMock);
	    when((emisoraMock).obtenerListaReproduccion()).thenReturn(listaReproduccionIdeal);
		assertTrue(emisoraMock.obtenerListaReproduccion().equals(listaReproduccionIdeal));
		emisoraMock.eliminar(cancionMock);
		listaReproduccionIdeal = new ArrayList<Contenido>();
	    when((emisoraMock).obtenerListaReproduccion()).thenReturn(listaReproduccionIdeal);
		assertTrue(emisoraMock.obtenerListaReproduccion().equals(listaReproduccionIdeal));
	}
}
