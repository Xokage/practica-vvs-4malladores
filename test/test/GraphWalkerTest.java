package test;



import static org.junit.Assert.assertEquals;

import org.graphwalker.core.condition.AlternativeCondition;
import org.graphwalker.core.condition.CombinedCondition;
import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.condition.ReachedVertex;
import org.graphwalker.core.condition.TimeDuration;
import org.graphwalker.core.generator.AStarPath;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.test.TestBuilder;
import org.junit.Test;

import servidor.Servidor;
import servidor.ServidorSimpleImp;
import contenido.Cancion;
import contenido.Contenido;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GraphWalkerTest extends ExecutionContext implements VVS{
	public final static Path MODEL_PATH = Paths.get("main/resources/testautomation/VVS.graphml");


	@Override
	public void Ready() {
		String nombre = "ServidorTest";
		String passwd = "1234567890";
		ServidorSimpleImp s = new ServidorSimpleImp(nombre,null, passwd,null);
		assertEquals(s.getContenidoList().isEmpty(),true);
		assertEquals(s.getNombre(),nombre);
		assertEquals(s.getTokenMaestro(),passwd);
		assertEquals(s.getTokensValidos().isEmpty(),true);
		
	}

	@Override
	public void Server_with_content() {
		String nombre = "ServidorTest";
		String passwd = "1234567890";
		String token = "imatoken";
		Servidor s = new ServidorSimpleImp(nombre,null, passwd,token);
		String titulo = "Baby";
		Contenido cancion = new Cancion(titulo, 60);
		s.agregar(cancion, passwd);
		List<Contenido> result = s.buscar(titulo, token);
		assertEquals(result.get(0),cancion);
		
	}

	@Override
	public void power_up_server() {
		String nombre = "ServidorTest";
		String passwd = "1234567890";
		ServidorSimpleImp s = new ServidorSimpleImp(nombre,null, passwd,null);
		assertEquals(s.getContenidoList().isEmpty(),true);
		assertEquals(s.getNombre(),nombre);
		assertEquals(s.getTokenMaestro(),passwd);
		assertEquals(s.getTokensValidos().isEmpty(),true);
		
	}

	@Override
	public void do_search() {
		String nombre = "ServidorTest";
		String passwd = "1234567890";
		String token = "imatoken";
		ServidorSimpleImp s = new ServidorSimpleImp(nombre,null, passwd,token);
		String titulo = "Baby";
		List<Contenido> result = s.buscar(titulo, token);//Buscamos no servidor sen contido.
		assertEquals(result.isEmpty(),true);
		Contenido cancion = new Cancion(titulo, 60);
		s.agregar(cancion, passwd);
		result = s.buscar(titulo, token);
		assertEquals(result.get(0),cancion);//Buscamos no servidor con contido.
		
	}

	@Override
	public void do_sign_out() {
		String nombre = "ServidorTest";
		String passwd = "1234567890";
		String token = "imatoken";
		ServidorSimpleImp s = new ServidorSimpleImp(nombre,null, passwd,token);
		String titulo = "Baby";
		Contenido cancion = new Cancion(titulo, 60);
		s.agregar(cancion, passwd);
		List<Contenido> result = s.buscar(titulo, token);
		assertEquals(result.get(0),cancion);
		s.alta();
		assertEquals(s.getTokensValidos().size(),2);
		s.baja(s.getTokensValidos().get(1).getLeft());
		assertEquals(s.getTokensValidos().size(),1);//Comprobamos para mais de un token.
		s.baja(s.getTokensValidos().get(0).getLeft());
		assertEquals(s.getTokensValidos().size(),0);//Comprobamos para un só token.
		
	}

	@Override
	public void Server_with_valid_token() {
		String nombre = "ServidorTest";
		String passwd = "1234567890";
		String token = "imatoken";
		ServidorSimpleImp s = new ServidorSimpleImp(nombre,null, passwd,token);
		assertEquals(s.getTokensValidos().get(0).getLeft(),token);
		assertEquals(s.getTokensValidos().size(),1);
		
	}

	@Override
	public void do_remove_content() {
		String nombre = "ServidorTest";
		String passwd = "1234567890";
		String token = "imatoken";
		ServidorSimpleImp s = new ServidorSimpleImp(nombre,null, passwd,token);
		String titulo = "Baby";
		Contenido cancion = new Cancion(titulo, 60);
		s.agregar(cancion, passwd);
		List<Contenido> result = s.buscar(titulo, token);
		assertEquals(result.get(0),cancion);
		String titulo1 = "Big";
		Contenido cancion1 =  new Cancion(titulo1,50);
		s.agregar(cancion1,passwd);
		List<Contenido> result1 = s.buscar(titulo1, token);
		assertEquals(result1.get(0),cancion1);
		s.eliminar(cancion1, passwd);
		assertEquals(s.getContenidoList().size(),1);//Comprobamos para cando existe máis de un contido no servidor.
		s.eliminar(cancion, passwd);
		assertEquals(s.getContenidoList().size(),0);//Comprobamos para cando existe só un contido no servidor.
		s.eliminar(cancion, passwd);
		assertEquals(s.getContenidoList().isEmpty(),true);//Comprobamos para cando non existe ningún contido.
		
	}

	@Override
	public void do_sign_up() {
		String nombre = "ServidorTest";;
		ServidorSimpleImp s = new ServidorSimpleImp(nombre);
		String token=s.alta();
		assertEquals(s.getTokensValidos().get(0).getLeft(),token);
		assertEquals(s.getTokensValidos().size(),1);//Comprobamos para un servidor sen contido.
		String titulo = "Baby";
		Contenido cancion = new Cancion(titulo, 60);
		String passwd="a";
		s.setTokenMaestro(passwd);
		s.agregar(cancion, passwd);
		List<Contenido> result = s.buscar(titulo, token);
		assertEquals(result.get(0),cancion);
		s.alta();
		assertEquals(s.getTokensValidos().size(),2);//Comprobamos para un servidor con contido.
		
	}

	@Override
	public void do_add_content() {
		String nombre = "ServidorTest";
		String passwd = "1234567890";
		String token = "imatoken";
		ServidorSimpleImp s = new ServidorSimpleImp(nombre,null, passwd,token);
		String titulo = "Baby";
		Contenido cancion = new Cancion(titulo, 60);
		s.agregar(cancion, passwd);
		List<Contenido> result = s.buscar(titulo, token);
		assertEquals(result.get(0),cancion);
		assertEquals(s.getContenidoList().size(),1);//Comprobamos a inserción cando non existe contido no servidor.
		String titulo1 = "Big";
		Contenido cancion1 =  new Cancion(titulo1,50);
		s.agregar(cancion1,passwd);
		List<Contenido> result1 = s.buscar(titulo1, token);
		assertEquals(result1.get(0),cancion1);
		assertEquals(s.getContenidoList().size(),2);//Comprobamos a inserción cando existe contido.
		
	}
	

	
	@Test
    public void runSmokeTest() {
        new TestBuilder()
            .addModel(MODEL_PATH,new AStarPath(new ReachedVertex("Server_with_content")))
            .execute();
    }
	
	@Test
    public void runFunctionalTest() {
		AlternativeCondition condition = new AlternativeCondition();
		condition.addStopCondition(new EdgeCoverage(100));
		condition.addStopCondition(new TimeDuration(15, TimeUnit.SECONDS));
		new TestBuilder()
            .addModel(MODEL_PATH,new RandomPath(condition))
            .execute();
    }
	
	@Test
    public void runStabilityTest() {
        new TestBuilder()
            .addModel(MODEL_PATH,new RandomPath(new TimeDuration(30, TimeUnit.SECONDS)))
            .execute();
    }

}
