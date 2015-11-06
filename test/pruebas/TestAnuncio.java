package pruebas;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import contenido.Anuncio;
import contenido.Contenido;

public class TestAnuncio {
	
	Anuncio anuncio = new Anuncio();

	@Test
	public void obtenerTituloTest() {
		assertTrue(anuncio.obtenerTitulo().equals("PUBLICIDAD"));
	}
	
	@Test
	public void obtenerDuracionTest() {
		assertTrue(Integer.compare(anuncio.obtenerDuracion(),5)==0);
	}
	
	@Test
	public void obtenerListaReproduccionTest() {
		List<Contenido> listaReproduccionIdeal = new ArrayList();
		listaReproduccionIdeal.add(anuncio); 
		assertTrue(anuncio.obtenerListaReproduccion().equals(listaReproduccionIdeal));
	}
	
	@Test
	public void buscarTest() {
		List<Contenido> listaReproduccionIdeal = new ArrayList();
		listaReproduccionIdeal.add(anuncio);
		assertTrue(anuncio.buscar("PUBLICIDAD").equals(listaReproduccionIdeal));
		listaReproduccionIdeal = new ArrayList();
		assertTrue(anuncio.buscar("OTRA COSA").equals(listaReproduccionIdeal));
	}
}
