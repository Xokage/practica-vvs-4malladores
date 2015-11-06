package servidor;

import java.util.List;

import contenido.Contenido;

public class ServidorSimpleConRespaldoImp extends ServidorSimpleImp {

	private Servidor servidorRespaldo;
	
	public ServidorSimpleConRespaldoImp(String nombre, List<Contenido> contenidoList,
			String tokenContenido, String tokenValido, Servidor servidorRespaldo) throws BackupServerException{
		super(nombre,contenidoList,tokenContenido,tokenValido);
		if (servidorRespaldo == null) throw new BackupServerException();
		this.servidorRespaldo = servidorRespaldo;
	}

	@Override
	public List<Contenido> buscar(String subcadena, String token) {
		List<Contenido> resultado = super.buscar(subcadena,token);
		if(resultado.isEmpty()){
			return servidorRespaldo.buscar(subcadena,token);
		}
		else return resultado;
			
	}

}
