package servidor;

import java.util.ArrayList;
import java.util.List;

import util.Pair;
import contenido.Contenido;



public class ServidorRespaldoImp  {

	private String nombre;
	private List<Contenido> contenidoList;
	private String tokenContenido;
	private List<Pair<String, Integer>> tokenValido;
	
	
	public ServidorRespaldoImp(String nombre, List<Contenido> contenidoList,
			String tokenContenido, String tokenValido) {
		this.nombre = nombre;
		this.contenidoList = contenidoList;
		this.tokenContenido = tokenContenido;
		this.tokenValido = new ArrayList<>();
		this.tokenValido.add(new Pair<String, Integer>(tokenValido, 10));
	}

	public ServidorRespaldoImp(String nombre, String tokenContenido,
			String tokenValido) {
		this.nombre = nombre;
		this.contenidoList = new ArrayList<>();
		this.tokenContenido = tokenContenido;
		this.tokenValido = new ArrayList<>();
		this.tokenValido.add(new Pair<String, Integer>(tokenValido, 10));
	}

	public ServidorRespaldoImp(String nombre) {
		this.nombre = nombre;
		this.contenidoList = new ArrayList<>();
		this.tokenValido = new ArrayList<>();
	}
	
	
	public void baja(String token) {
		for (Pair<String, Integer> par : this.tokenValido) {
			if (par.getLeft().equals(token))
				this.tokenValido.remove(par);
		}
	}
	
	
	public List<Contenido> buscar(String subcadena, String token) {
		List<Contenido> resultado = new ArrayList<>();
		Pair<String, Integer> user = null;
		
		for(Pair<String, Integer> tmpUser : this.tokenValido){
			if(tmpUser.getLeft().equals(token))
				user = tmpUser;
		}
		if (user!=null)	this.tokenValido.remove(user); 
												//Quitamos o usuario da lista de tokens validos
												//mentres está pedindo contido.
		int contadorDeAnuncios = 3;
		for(Contenido elemento : this.contenidoList){
			if(user.getRight()<=0){
				baja(user.getLeft()); //Se o token non 
			 						  //ten mais usos eliminamolo.
				user = null;
			}
			
			if(user == null){		//Se non hai token valido
				if(contadorDeAnuncios>=3){
					contadorDeAnuncios = 0;
					//TODO meter un anuncio.
				} else contadorDeAnuncios++;
			}
			
			if(elemento.obtenerTitulo().contains(subcadena)){
				resultado.add(elemento);
				user = new Pair<String, Integer>(user.getLeft(),user.getRight() - 1);
			}
		}
		
		return resultado;
	}
	
}
