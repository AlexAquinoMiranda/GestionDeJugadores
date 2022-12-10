package BisnessLogic;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import AccessData.Dao.JugadorAnchoFijoDAO;
import AccessData.Dto.JugadorAnchoFijoDTO;

public class Comprobante {

	JugadorAnchoFijoDAO jugadorDao = new JugadorAnchoFijoDAO();
	JugadorAnchoFijoDTO jugadorDto = null;
	Scanner sc = new Scanner(System.in);
//	int identificador = jugadorDao.idAumento();

	public void CrearNuevoJugador() {
		JugadorAnchoFijoDTO jugadorNuevo = null;
		System.out.println("\n \n\tCrear nuevo jugador. \n\n " + "Obs: longitud de carácteres:\n"
				+ "\n nombre: 25. \n apellido: 25. \n fechaNacimiento: 4. \n equipo: 20. \n activo: 5.\n id: 3\n"
				+ "Datos a ingresar:  nombre, apellido, fechaNacimiento, equipo, activo , id");

		try {
			jugadorNuevo = AddDatosJugador();
			if (jugadorNuevo.getNombre().length() <= 25 && jugadorNuevo.getApellido().length() <= 25
					&& jugadorNuevo.getEquipo().length() <= 20 && jugadorNuevo.getFechaNacimiento() > 999
					&& jugadorNuevo.getFechaNacimiento() <= 9999) {
				jugadorDao.insertar(jugadorNuevo);
			} else {
				System.err.println("Datos introducidos superan el limite de logitud. Los datos serán truncados.");
				jugadorDao.insertar(jugadorNuevo);
			}
		} catch (InputMismatchException e) {
			System.err.println("Has  ingresado datos incorrectos.");

		}

	}

	/**
	 * este metodo tiene que poder modifiicar a un jugador cuando se buxca su nombre
	 */
	public void consultarFichaJugador() {
		JugadorAnchoFijoDTO jugadorAModificar = null;
		JugadorAnchoFijoDTO jugadorAux = null;
		System.out.println(" 1. Busqueda por id\n" + " 2. Busqueda por nombre\n" + " 3. Volver al menú principal.\n"
				+ "Elige una opción a realizar = ");

		int value = 0;
		do {
			try {
				value = sc.nextInt();
			} catch (InputMismatchException a) {
				System.err.println("Valor introducido incorrecto. valores admitidos {1-3}");
			}
			try {
				if (value == 1) {
					System.out.println("Introduce el id = ");
					try {
						int id = sc.nextInt();
						if (id > 999 && id <= 9999) {

							System.out.println("Buscando el id " + id + "...");

							jugadorAux=	jugadorDao.buscarPorId(id);
						
							
						} else {
							System.err.println("El id debe de ser de longitud 4");
						}
						
						System.out.println(" \nModificar al jugador (s/n): ");
						String condicion= 	sc.next();
						 if(condicion.equals("s") || condicion.equals("S")) {
							jugadorAModificar = AddDatosJugador();
							
							jugadorAModificar.setId(jugadorAux.getId());

							jugadorDao.modificar(jugadorAModificar);
						
						}

					} catch (InputMismatchException e) {

					}
				} else if (value == 2) {
					System.out.println("Introduce nombre de jugador = ");
					String nombre = sc.next();
					System.out.println("Buscando al jugador con nombre " + nombre + "...");
					// me muestra los datos de los jugadores
					jugadorDao.buscarPorNombre(nombre);

					break;
				} else if (value == 3) {
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Valor introducido incorrecto.");
			}

		} while (value == 3);

	}

	public void listaDeJugadores() {
		// tengo que eliminar la lista(es un void)
		jugadorDao.listaJugadores();
		System.out.println("\n\t Filtrar jugador: ");
		consultarFichaJugador();

	}

	public void darBajaLogica() {
		JugadorAnchoFijoDTO jugador = null;
		System.out.println("\tIntroduce el id del jugador =");
		try {
			int id = sc.nextInt();

			if (id > 999 && id <= 9999) {

				System.out.println("Buscando el id " + id + "...");

				jugador = jugadorDao.buscarPorId(id);
			} else {
				System.err.println("El id debe de ser de longitud 4");
			}

			// System.out.println("el jugador a modificar estado esta = " +
			// jugador.isActivo());
			if (jugador != null) {
				if (jugador.isActivo()) {
					jugadorDao.eliminar(jugador);
				} else {
					System.err.println("El jugador ya está dado de baja.");
				}
			}

		} catch (InputMismatchException e) {
			System.err.println("Ingresa valores correcto");
		}

	}

	public void volcadoTxt() {
		System.out.println("\n Volcando lista de jugadores a fichero txt...");
		jugadorDao.volcarTxt();

	}

	public void cargarDatos() {
		System.out.println("vas a perder todos los datos, estas seguro? (s/n)");
		jugadorDao.cargarJugadores();

	}

	public void volcadoDat() {
		System.out.println("\n Volcando lista de jugadores a fichero  dat...");
		jugadorDao.volcarDat();

	}

	public void modificarJugador() {
		JugadorAnchoFijoDTO jugadorAModificar = null;
		JugadorAnchoFijoDTO jugadorAux = null;

		System.out.println("\n\t Introduce el id del jugador =");
		int id = sc.nextInt();

		jugadorAux = jugadorDao.buscarPorId(id);

		jugadorAModificar = AddDatosJugador();
//		JugadorAnchoFijoDTO.aid--;// se mantiene el valor
		jugadorAModificar.setId(jugadorAux.getId());

		jugadorDao.modificar(jugadorAModificar);

	}

	public void darAltaLogica() {

	}

	private JugadorAnchoFijoDTO AddDatosJugador() throws InputMismatchException {
		System.out.println("\n Nombre= ");
		String dato = sc.next();
		System.out.println("\n Apellido= ");
		String apellido = sc.next();
		System.out.println("\n Año de nacimiento = ");
		int naci = sc.nextInt();
		System.out.println("\n Equipo = ");
		String team = sc.next();
		System.out.println("\n Activo (true/false)= ");
		String v = sc.next();
		boolean activo = false;
		if (v.equals("true")) {
			activo = true;
		} else if (v.equals("false")) {
			activo = false;
		}
//		JugadorAnchoFijoDTO.aid++/
		jugadorDto = new JugadorAnchoFijoDTO(dato, apellido, naci, team, jugadorDao.idAumento(), activo);

		return jugadorDto;

	}

}
