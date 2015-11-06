package pruebas;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import servidor.BackupServerException;
import servidor.Servidor;
import servidor.ServidorSimpleConRespaldoImp;
import servidor.ServidorSimpleImp;
import contenido.Cancion;
import contenido.Contenido;



public class ServidorSimpleConRespaldoImpTest {

	@Test (expected = BackupServerException.class)
	public void buscarInvalidBackupServerTest() throws BackupServerException {
		String nombre = "Servidor1";
		String passwd = "1234567890";
		String tokenValido = "valido";
		new ServidorSimpleConRespaldoImp(nombre, null,
				passwd, tokenValido, null);
	}
	
	@Test
	public void buscarTest() throws BackupServerException{
		String nombre = "Servidor1";
		String passwd = "1234567890";
		String tokenValido = "valido";
		Servidor servidor_respaldo = new ServidorSimpleImp("ServidorSimple",null,passwd,tokenValido);
		ServidorSimpleConRespaldoImp s1 = new ServidorSimpleConRespaldoImp(nombre, null,
				passwd, tokenValido, servidor_respaldo);
		
		String titulo = "Someone like you";
		String titulo1 = "Paradise city";
		String titulo2 = "Tsunami";
		
		Contenido cancion = new Cancion(titulo, 60);
		Contenido cancion1 = new Cancion(titulo1, 60);
		Contenido cancion2 = new Cancion(titulo2, 60);
		
		servidor_respaldo.agregar(cancion, passwd);
		servidor_respaldo.agregar(cancion1, passwd);
		servidor_respaldo.agregar(cancion2, passwd);
		servidor_respaldo.agregar(cancion2, passwd);
		
		List<Contenido> result = s1.buscar(titulo, tokenValido);
		assertEquals(result.get(0).obtenerTitulo(),titulo);
		result = s1.buscar(titulo1, tokenValido);
		assertEquals(result.get(0).obtenerTitulo(),titulo1);
		result = s1.buscar(titulo2, tokenValido);
		assertEquals(result.get(0).obtenerTitulo(),titulo2);
		assertEquals(result.get(1).obtenerTitulo(),titulo2);
	}
	
	@Test
	public void buscarWithoutContentTest() throws BackupServerException{
		String nombre = "Servidor1";
		String passwd = "1234567890";
		String tokenValido = "valido";
		Servidor servidor_respaldo = new ServidorSimpleImp("ServidorSimple",null,passwd,tokenValido);
		ServidorSimpleConRespaldoImp s1 = new ServidorSimpleConRespaldoImp(nombre, null,
				passwd, tokenValido, servidor_respaldo);
	
		List<Contenido> result = s1.buscar("Hey!", tokenValido);
		List<Contenido> result1 = new ArrayList<Contenido>();
		assertEquals(result,result1);
	}
}
