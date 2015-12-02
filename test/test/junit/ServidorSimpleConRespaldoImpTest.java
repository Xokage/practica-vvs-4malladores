package test.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import servidor.BackupServerException;
import servidor.Servidor;
import servidor.ServidorSimpleConRespaldoImp;
import servidor.ServidorSimpleImp;
import test.generators.ContenidoDuracionGenerator;
import test.generators.GeneralNameGenerator;
import test.generators.ServidorTokenGenerator;
import contenido.Cancion;
import contenido.Contenido;



/**
 * Tests para la clase Servidor Simple con Respaldo.
 */
public class ServidorSimpleConRespaldoImpTest {


	/** Generador de nombres válidos. */
	GeneralNameGenerator gNameGen = new GeneralNameGenerator();

	/** Generador de duraciones válidas. */
	ContenidoDuracionGenerator cDuracionGen = new ContenidoDuracionGenerator();

	/** Generador de tokens válidos. */
	ServidorTokenGenerator sTokenGen = new ServidorTokenGenerator();


	/**
	 * Buscar server backup inválido test.
	 *
	 * @throws BackupServerException la excepción BackupServerException
	 */
	@Test (expected = BackupServerException.class)
	public void buscarInvalidBackupServerTest() throws BackupServerException {
		String nombre = gNameGen.next();
		String passwd = gNameGen.next();
		String tokenValido = sTokenGen.next().getLeft();
		
		new ServidorSimpleConRespaldoImp(nombre, null,
				passwd, tokenValido, null);
	}
	
	/**
	 * Buscar test.
	 *
	 * @throws BackupServerException la excepción BackupServerException
	 */
	@Test
	public void buscarTest() throws BackupServerException{
		String nombre = gNameGen.next();
		String passwd = gNameGen.next();
		String tokenValido = sTokenGen.next().getLeft();
		
		Servidor servidor_respaldo = new ServidorSimpleImp("ServidorSimple",null,passwd,tokenValido);
		ServidorSimpleConRespaldoImp s1 = new ServidorSimpleConRespaldoImp(nombre, null,
				passwd, tokenValido, servidor_respaldo);
		
		String titulo = gNameGen.next();
		String titulo1 = gNameGen.next();
		String titulo2 = gNameGen.next();
		
		Integer duracion = cDuracionGen.next();
		Integer duracion1 = cDuracionGen.next();
		Integer duracion2 = cDuracionGen.next();
		
		Contenido cancion = new Cancion(titulo, duracion);
		Contenido cancion1 = new Cancion(titulo1, duracion1);
		Contenido cancion2 = new Cancion(titulo2, duracion2);
		
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
	
	/**
	 * Buscar sin contenido test.
	 *
	 * @throws BackupServerException la excepción BackupServerException
	 */
	@Test
	public void buscarWithoutContentTest() throws BackupServerException{
		String nombre = gNameGen.next();
		String passwd = gNameGen.next();
		String tokenValido = sTokenGen.next().getLeft();
		
		Servidor servidor_respaldo = new ServidorSimpleImp("ServidorSimple",null,passwd,tokenValido);
		ServidorSimpleConRespaldoImp s1 = new ServidorSimpleConRespaldoImp(nombre, null,
				passwd, tokenValido, servidor_respaldo);
	
		List<Contenido> result = s1.buscar(gNameGen.next(), tokenValido);
		List<Contenido> result1 = new ArrayList<Contenido>();
		assertEquals(result,result1);
	}
}
