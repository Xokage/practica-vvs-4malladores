package servidor;

import java.util.ArrayList;
import java.util.List;

import util.Pair;
import contenido.Contenido;

public class ServidorSimpleConRespaldoImp extends ServidorSimpleImp {

	private ServidorRespaldoImp servidorRespaldo;
	
	public ServidorSimpleConRespaldoImp(String nombre, List<Contenido> contenidoList,
			String tokenContenido, String tokenValido, ServidorRespaldoImp servidorRespaldo) throws BackupServerException{
		super(nombre,contenidoList,tokenContenido,tokenValido);
		if (servidorRespaldo == null) throw new BackupServerException();
		this.servidorRespaldo = servidorRespaldo;
	}

	public ServidorSimpleConRespaldoImp(String nombre, String tokenContenido,
			String tokenValido, ServidorRespaldoImp servidorRespaldo) throws BackupServerException{
		super(nombre,tokenContenido,tokenValido);
		if (servidorRespaldo == null) throw new BackupServerException();
		this.servidorRespaldo = servidorRespaldo;
	}

	public ServidorSimpleConRespaldoImp(String nombre, ServidorRespaldoImp servidorRespaldo) throws BackupServerException {
		super(nombre);
		if (servidorRespaldo == null) throw new BackupServerException();
		this.servidorRespaldo = servidorRespaldo;
	}
	
	@Override
	public List<Contenido> buscar(String subcadena, String token) {
		
		if(super.buscar(subcadena,token).isEmpty())
			return servidorRespaldo.buscar(subcadena,token);
		else return super.buscar(subcadena, token);
			
	}

}
