package servidor;

import java.util.ArrayList;
import java.util.List;

import util.Pair;
import contenido.Contenido;

public class ServidorSimpleConRespaldoImp extends ServidorSimpleImp {

	private ServidorRespaldoImp servidorRespaldo;
	
	public ServidorSimpleConRespaldoImp(String nombre, List<Contenido> contenidoList,
			String tokenContenido, String tokenValido, ServidorRespaldoImp servidorRespaldo) {
		super(nombre,contenidoList,tokenContenido,tokenValido);
		this.servidorRespaldo = servidorRespaldo;
	}

	public ServidorSimpleConRespaldoImp(String nombre, String tokenContenido,
			String tokenValido) {
		super(nombre,tokenContenido,tokenValido);
	}

	public ServidorSimpleConRespaldoImp(String nombre) {
		super(nombre);
	}
	
	@Override
	public List<Contenido> buscar(String subcadena, String token) {
		
		if(super.buscar(subcadena,token).isEmpty())
			return servidorRespaldo.buscar(subcadena,token);
		else return super.buscar(subcadena, token);
			
	}

}
