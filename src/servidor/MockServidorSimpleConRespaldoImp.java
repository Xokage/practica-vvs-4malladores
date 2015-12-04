package servidor;

import java.util.ArrayList;
import java.util.List;

import contenido.Contenido;

public class MockServidorSimpleConRespaldoImp extends MockServidorSimpleImp{
	
	/**
	 * Instancia un nuevo servidor simple con respaldo.
	 *
	 */
	
	


	/**
	 * @see servidor.ServidorSimpleImp#buscar(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public List<Contenido> buscar(final String subcadena,
			final String token) {
		List<Contenido> resultado = new ArrayList<>();
		return resultado;
	}

}
