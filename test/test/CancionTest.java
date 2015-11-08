package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import contenido.Cancion;
import contenido.Contenido;

public class CancionTest {
	
	Cancion cancion = new Cancion("Mi gran noche",3);

	@Test
	public void obtenerTituloTest() {
		assertTrue(cancion.obtenerTitulo().equals("Mi gran noche"));
	}
	
	@Test
	public void obtenerDuracionTest() {
		assertTrue(Integer.compare(cancion.obtenerDuracion(),3)==0);
	}
	
	@Test
	public void obtenerListaReproduccionTest() {
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(cancion);
		assertTrue(cancion.obtenerListaReproduccion().equals(listaReproduccionIdeal));
	}
	
	@Test
	public void buscarTest() {
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(cancion);
		assertTrue(cancion.buscar("Mi gran noche").equals(listaReproduccionIdeal));
		listaReproduccionIdeal = new ArrayList<Contenido>();
		assertTrue(cancion.buscar("OTRA COSA").equals(listaReproduccionIdeal));
	}

}
