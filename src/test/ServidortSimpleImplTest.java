package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import contenido.Anuncio;
import contenido.Cancion;
import contenido.Contenido;
import servidor.Servidor;
import servidor.ServidorSimpleImp;

public class ServidortSimpleImplTest {

	@Test
	public void obtenerNombreTest() {
		String nombre = "ServidorTest";
		Servidor s = new ServidorSimpleImp(nombre);
		assertEquals(s.obtenerNombre(), nombre);
	}

	
	@Test
	public void agregarTest() {
		String nombre = "ServidorTest";
		String passwd = "1234567890";
		String token = "imatoken";
		Servidor s = new ServidorSimpleImp(nombre,passwd,token);
		String titulo = "Baby";
		Contenido cancion = new Cancion(titulo, 60);
		s.agregar(cancion, token);
		List<Contenido> result = s.buscar(titulo, token);
		assertEquals(result.get(0),cancion);
	}

	
	@Test
	public void eliminarTest() {
		String nombre = "ServidorTest";
		String passwd = "1234567890";
		String token = "imatoken";
		Servidor s = new ServidorSimpleImp(nombre,passwd,token);
		String titulo = "Baby";
		Contenido cancion = new Cancion(titulo, 60);
		s.agregar(cancion, token);
		List<Contenido> resultAntes = s.buscar(titulo, token);
		s.eliminar(cancion, passwd);
		List<Contenido> resultDespois = s.buscar(titulo, token);
		assertEquals(resultAntes.get(0),cancion);
		assertEquals(resultDespois.get(0),null);
	}

	@Test
	public void buscarTokenInvalidoTest() {
		String nombre = "ServidorTest";
		String passwd = "1234567890";
		String token = "asdf";
		String tokenInvalido = "invalido";
		Servidor s = new ServidorSimpleImp(nombre,passwd,token);
		String titulo = "Baby";
		Contenido cancion = new Cancion(titulo, 60);
		s.agregar(cancion, token);
		s.agregar(cancion, token);
		s.agregar(cancion, token);
		s.agregar(cancion, token);
		List<Contenido> result = s.buscar(titulo, tokenInvalido);
		int i = 0;
		assertEquals(result.get(i++).obtenerTitulo(),"PUBLICIDAD");
		assertEquals(result.get(i++).obtenerTitulo(),titulo);
		assertEquals(result.get(i++).obtenerTitulo(),titulo);
		assertEquals(result.get(i++).obtenerTitulo(),titulo);
		assertEquals(result.get(i++).obtenerTitulo(),"PUBLICIDAD");
	}
	
	@Test
	public void buscarTokenValidoTest() {
		String nombre = "ServidorTest";
		String passwd = "1234567890";
		String token = "asdf";
		String tokenValido = "valido";
		Servidor s = new ServidorSimpleImp(nombre,passwd,token);
		String titulo = "Baby";
		Contenido cancion = new Cancion(titulo, 60);
		s.agregar(cancion, token);
		s.agregar(cancion, token);
		s.agregar(cancion, token);
		s.agregar(cancion, token);
		List<Contenido> result = s.buscar(titulo, tokenValido);
		int i = 0;
		assertEquals(result.get(i++).obtenerTitulo(),titulo);
		assertEquals(result.get(i++).obtenerTitulo(),titulo);
		assertEquals(result.get(i++).obtenerTitulo(),titulo);
	}

	@Test
	public void buscarTokenValidoMasDeDiezTest() {
		String nombre = "ServidorTest";
		String passwd = "1234567890";
		String token = "asdf";
		String tokenValido = "valido";
		Servidor s = new ServidorSimpleImp(nombre,passwd,token);
		String titulo = "Baby";
		Contenido cancion = new Cancion(titulo, 60);
		for(int j = 0; j <=15; j++)
			s.agregar(cancion, token);
		List<Contenido> result = s.buscar(titulo, tokenValido);
		int i;
		for(i=0;i<=10;i++)
			assertEquals(result.get(i).obtenerTitulo(),titulo);
		
		assertEquals(result.get(i++).obtenerTitulo(),"PUBLICIDAD");
		assertEquals(result.get(i++).obtenerTitulo(),titulo);
		assertEquals(result.get(i++).obtenerTitulo(),titulo);
		assertEquals(result.get(i++).obtenerTitulo(),titulo);
		assertEquals(result.get(i++).obtenerTitulo(),"PUBLICIDAD");
	}

}
