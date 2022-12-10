package Interface;

import AccessData.Dao.JugadorAnchoFijoDAO;

public class Pruebas {

	public Pruebas() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JugadorAnchoFijoDAO	jugadorDao = new JugadorAnchoFijoDAO();
		
		System.out.println(jugadorDao.idAumento());
	}

}
