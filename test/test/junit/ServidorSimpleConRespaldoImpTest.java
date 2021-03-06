package test.junit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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

/**
 * Tests para la clase Servidor Simple con Respaldo.
 */
public class ServidorSimpleConRespaldoImpTest {

	/** Generador de nombres válidos. */
	private final GeneralNameGenerator gNameGen = new GeneralNameGenerator();

	/** Generador de duraciones válidas. */
	private final ContenidoDuracionGenerator cDuracionGen = new ContenidoDuracionGenerator();

	/** Generador de tokens válidos. */
	private final ServidorTokenGenerator sTokenGen = new ServidorTokenGenerator();

	/**
	 * Buscar server backup inválido test.
	 *
	 * @throws BackupServerException
	 *             la excepción BackupServerException
	 */
	@Test(expected = BackupServerException.class)
	public final void buscarInvalidBackupServerTest()
			throws BackupServerException {
		String nombre = gNameGen.next();
		String passwd = gNameGen.next();
		String tokenValido = sTokenGen.next().getLeft();

		new ServidorSimpleConRespaldoImp(nombre, null, passwd, tokenValido,
				null);
	}

	/**
	 * Buscar test.
	 *
	 * @throws BackupServerException
	 *             la excepción BackupServerException
	 */
	@Test
	public final void buscarTest() throws BackupServerException {

		String nombre = gNameGen.next();
		String passwd = gNameGen.next();
		String tokenValido = sTokenGen.next().getLeft();
		Servidor servidorRespaldo = new ServidorSimpleImp("ServidorSimple",
				null, passwd, tokenValido);

		String titulo = gNameGen.next();
		String titulo1 = gNameGen.next();
		String titulo2 = gNameGen.next();
		
		//Get sure no name contains another so there're no errors on tests
		do {
			titulo = gNameGen.next();
			titulo1 = gNameGen.next();
			titulo2 = gNameGen.next();
		} while ((titulo.contains(titulo1)) || (titulo.contains(titulo2))
				|| (titulo1.contains(titulo)) || (titulo1.contains(titulo2))
				|| (titulo2.contains(titulo)) || (titulo2.contains(titulo1)));
		Integer duracion = cDuracionGen.next();
		Integer duracion1 = cDuracionGen.next();
		Integer duracion2 = cDuracionGen.next();

		Contenido cancion = new Cancion(titulo, duracion);
		Contenido cancion1 = new Cancion(titulo1, duracion1);
		Contenido cancion2 = new Cancion(titulo2, duracion2);

		servidorRespaldo.agregar(cancion, passwd);
		servidorRespaldo.agregar(cancion1, passwd);
		servidorRespaldo.agregar(cancion2, passwd);

		ServidorSimpleConRespaldoImp s1 = new ServidorSimpleConRespaldoImp(
				nombre, null, passwd, tokenValido, servidorRespaldo);

		List<Contenido> result = s1.buscar(titulo, tokenValido);

		assertEquals(result.get(0).obtenerTitulo(), titulo);

		result = s1.buscar(titulo1, tokenValido);

		assertEquals(result.get(0).obtenerTitulo(), titulo1);

		result = s1.buscar(titulo2, tokenValido);

		assertEquals(result.get(0).obtenerTitulo(), titulo2);
	}

	/**
	 * Buscar sin contenido test.
	 *
	 * @throws BackupServerException
	 *             la excepción BackupServerException
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
				nombre, null, passwd, tokenValido, servidorRespaldo);

		List<Contenido> result1 = new ArrayList<Contenido>();
		result1.add(mockCancion);
		assertEquals(servidorSimpleConRespaldoImp.buscar(titulo, tokenValido), result1);
	}
}
