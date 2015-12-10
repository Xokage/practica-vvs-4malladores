package test.junit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import mocks.MockServidorSimpleConRespaldoImp;
import mocks.MockServidorSimpleImp;

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
	
	/** Creamos un mock de servidorSimpleConRespaldoImp para probar. */
	MockServidorSimpleConRespaldoImp servidorSimpleConRespaldoImpMock = Mockito.mock(MockServidorSimpleConRespaldoImp.class);
	
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
		
		
		String titulo = gNameGen.next();
		String titulo1 = gNameGen.next();
		String titulo2 = gNameGen.next();
		
		Integer duracion = cDuracionGen.next();
		Integer duracion1 = cDuracionGen.next();
		Integer duracion2 = cDuracionGen.next();
		
		Contenido cancion = new Cancion(titulo, duracion);
		Contenido cancion1 = new Cancion(titulo1, duracion1);
		Contenido cancion2 = new Cancion(titulo2, duracion2);
		
		servidorSimpleConRespaldoImpMock.agregar(cancion, passwd);
		servidorSimpleConRespaldoImpMock.agregar(cancion1, passwd);
		servidorSimpleConRespaldoImpMock.agregar(cancion2, passwd);
		
		List<Contenido> result = new ArrayList<>();
		result.add(cancion);
		
		when(servidorSimpleConRespaldoImpMock.buscar(titulo, tokenValido)).thenReturn(result);

		assertEquals(servidorSimpleConRespaldoImpMock.buscar(titulo, tokenValido),result);
		
		result.clear();
		result.add(cancion1);
		when(servidorSimpleConRespaldoImpMock.buscar(titulo1, tokenValido)).thenReturn(result);
		assertEquals(servidorSimpleConRespaldoImpMock.buscar(titulo1, tokenValido), result);
		
		result.clear();
		result.add(cancion2);
		when(servidorSimpleConRespaldoImpMock.buscar(titulo2, tokenValido)).thenReturn(result);
		
		assertEquals(servidorSimpleConRespaldoImpMock.buscar(titulo2, tokenValido), result);
	}
	
	/**
	 * Buscar sin contenido test.
	 *
	 * @throws BackupServerException la excepción BackupServerException
	 */
	@Test
	public final void buscarWithoutContentTest() throws BackupServerException {
		String tokenValido = sTokenGen.next().getLeft();
		
		
		List<Contenido> result = new ArrayList<>();
		when (servidorSimpleConRespaldoImpMock.buscar(gNameGen.next(), tokenValido)).thenReturn(result);
		List<Contenido> result1 = new ArrayList<Contenido>();
		assertEquals(servidorSimpleConRespaldoImpMock.buscar(gNameGen.next(), tokenValido), result1);
	}

}
