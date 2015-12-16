package test.junit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;


import org.junit.Test;
import org.mockito.Mockito;

import servidor.BackupServerException;
import servidor.Servidor;
import servidor.ServidorSimpleConRespaldoImp;
import servidor.ServidorSimpleImp;
import test.generators.ContenidoDuracionGenerator;
import test.generators.GeneralNameGenerator;
import test.generators.ServidorTokenGenerator;
import contenido.Cancion;
import contenido.Contenido;
import static org.mockito.Mockito.*;

public class ServidorSimpleConRespaldoImpMockTest {
	Contenido mockContenido = Mockito.mock(Contenido.class);
	
	
	/** Generador de nombres válidos. */
	private final GeneralNameGenerator gNameGen = new GeneralNameGenerator();

	/** Generador de duraciones válidas. */
	private final ContenidoDuracionGenerator cDuracionGen = 
			new ContenidoDuracionGenerator();

	/** Generador de tokens válidos. */
	private final ServidorTokenGenerator sTokenGen = 
			new ServidorTokenGenerator();

	
	/**
	 * Buscar test.
	 *
	 * @throws BackupServerException la excepción BackupServerException
	 */
	@Test
	public final void buscarTest() throws BackupServerException {
		String nombre = gNameGen.next();
		String passwd = gNameGen.next();
		String tokenValido = sTokenGen.next().getLeft();
		
		Servidor servidorRespaldo = new ServidorSimpleImp("ServidorSimple",
				null, passwd, tokenValido);
		
		ServidorSimpleConRespaldoImp servidorSimpleConRespaldoImp = new ServidorSimpleConRespaldoImp(
				nombre, null, passwd, tokenValido, servidorRespaldo);
		
		String titulo = gNameGen.next();
		String titulo1 = gNameGen.next();
		String titulo2 = gNameGen.next();
		
		Integer duracion = cDuracionGen.next();
		Integer duracion1 = cDuracionGen.next();
		Integer duracion2 = cDuracionGen.next();
		
		Contenido mockCancion = Mockito.mock(Contenido.class);
		when(mockCancion.obtenerTitulo()).thenReturn(titulo);
		when(mockCancion.obtenerDuracion()).thenReturn(duracion);
		
		Contenido mockCancion1 = Mockito.mock(Contenido.class);
		when(mockCancion1.obtenerTitulo()).thenReturn(titulo1);
		when(mockCancion1.obtenerDuracion()).thenReturn(duracion1);
		
		Contenido mockCancion2 = Mockito.mock(Contenido.class);
		when(mockCancion2.obtenerTitulo()).thenReturn(titulo2);
		when(mockCancion2.obtenerDuracion()).thenReturn(duracion2);
		
		
		servidorSimpleConRespaldoImp.agregar(mockCancion, passwd);
		servidorSimpleConRespaldoImp.agregar(mockCancion1, passwd);
		servidorSimpleConRespaldoImp.agregar(mockCancion2, passwd);
		
		List<Contenido> result = new ArrayList<>();
		result.add(mockCancion);
		assertEquals(servidorSimpleConRespaldoImp.buscar(titulo, tokenValido),result);
		
		result.clear();
		result.add(mockCancion1);
		assertEquals(servidorSimpleConRespaldoImp.buscar(titulo1, tokenValido), result);
		
		result.clear();
		result.add(mockCancion2);
		assertEquals(servidorSimpleConRespaldoImp.buscar(titulo2, tokenValido), result);
	}
	
	/**
	 * Buscar sin contenido test.
	 *
	 * @throws BackupServerException la excepción BackupServerException
	 */
	@Test
	public final void buscarWithoutContentTest() throws BackupServerException {
		String nombre = gNameGen.next();
		String passwd = gNameGen.next();
		String tokenValido = sTokenGen.next().getLeft();
		
		Servidor servidorRespaldo = new ServidorSimpleImp("ServidorSimple",
				null, passwd, tokenValido);
		
		String titulo = gNameGen.next();
		Integer duracion = cDuracionGen.next();
		
		Contenido mockCancion = Mockito.mock(Contenido.class);
		when(mockCancion.obtenerTitulo()).thenReturn(titulo);
		when(mockCancion.obtenerDuracion()).thenReturn(duracion);
		
		servidorRespaldo.agregar(mockCancion, passwd); //Añadimos un contenido al servidor de respaldo
		
		ServidorSimpleConRespaldoImp servidorSimpleConRespaldoImp = new ServidorSimpleConRespaldoImp(
				nombre, null, passwd, tokenValido, servidorRespaldo); //Creamos servidor simple con respaldo sin contenido
		
		List<Contenido> result1 = new ArrayList<>();
		result1.add(mockCancion);
		assertEquals(servidorSimpleConRespaldoImp.buscar(titulo, tokenValido), result1);
	}

}
