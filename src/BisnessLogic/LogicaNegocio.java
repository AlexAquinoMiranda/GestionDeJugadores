package BisnessLogic;

import java.util.InputMismatchException;
import java.util.Scanner;
import AccessData.Dao.JugadorAnchoFijoDAO;
import AccessData.Dto.JugadorAnchoFijoDTO;

public class LogicaNegocio {
	Scanner sc = new Scanner(System.in);

	JugadorAnchoFijoDAO j = new JugadorAnchoFijoDAO();
	JugadorAnchoFijoDTO player = null;

	public void menuPrincipal() {

		do {
			System.out.print("\n\n\n\n\t Menú de acciones:\n" + "1. Alta jugador\n" + "2. Consulta ficha jugador \n"
			// + " 2.1 busqueda por id\n"
			// + " 2.2 busqueda por nombre\n"
					+ "3. Listado de jugadores\n"
					// + " 3.1 busqueda por id\n"
					+ "4. Dar baja lógica un jugador \n" + "5. Generar volcado a extension txt\n"
					+ "6. Cargar jugadores desde txt/csv\n" + "7. Generar volcado a extensión dat\n" + "8. Modify \n"
					+ "9. Dar de alta lógica un jugador\n" + "10. Salir \n\n "
					+ "Ingresa valor de acción a realizar: ");

			int value = 0;
			try {
				value = sc.nextInt();
			} catch (InputMismatchException a) {
				System.out.println("Ingresa un valor que sea válida.");

			}

			eventos(value);
		} while (true);
	}

	private JugadorAnchoFijoDTO AddDatosJugador() throws InputMismatchException {
		System.out.println("\n Nombre= ");
		String dato = sc.next();
		System.out.println("\n Apellido= ");
		String apellido = sc.next();
		System.out.println("\n FechaNacimiento = ");
		int naci = sc.nextInt();
		System.out.println("\n Equipo = ");
		String team = sc.next();
		System.out.println("\n Activo (true/false)= ");
		boolean v = sc.nextBoolean();

		player = new JugadorAnchoFijoDTO(dato, apellido, naci, team, JugadorAnchoFijoDTO.aid++, v);
		System.out.println("desde metodo addDatosJugador");
//		System.out.println(player.toString());

		return player;
//		j.insertar(player);
	}

	public void buscarJugador() {
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
						if (id > 0001) {
							System.out.println("tiene que ser de longitud 4");
						}

						System.out.println("Buscando id " + id + "..");
						j.buscarPorId(id);
					} catch (InputMismatchException e) {

					}
				} else if (value == 2) {
					System.out.println("Introduce nombre de jugador = ");
					String nombre = sc.next();
					System.out.println("Buscando al jugador " + nombre + "...");
					j.buscarPorNombre(nombre);

					break;
				} else if (value == 3) {
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Valor introducido incorrecto.");
			}

		} while (value == 3);
	}

	void eventos(int value) {
		switch (value) {
		case 1:
			System.out.print("Dar de alta un nuevo Jugador \n");
			nuevoJugador();
			break;
		case 2:
			System.out.println("Buscar un jugador\n");
			buscarJugador();
			break;
		case 3:
			System.out.println("Lista de todos los jugadores.");
			j.listaJugadores();
			System.out.println("\n\t Filtrar jugador: ");// listo
			buscarJugador();
			break;
		case 4:
			System.out.print("Dar de baja lógica un Jugador");
			altaBajaJugador();
			break;
		case 5:
			System.out.println("exportar a fichero	.txt");
			j.volcarTxt();
			break;
		case 6:
			System.out.println("cargar desde txt");
			j.cargarJugadores();
			break;
		case 7:
			System.out.println("Exportar a fichero	 .dat");
			j.volcarDat();
			break;
		case 8:
			System.out.println("Modificar un Jugador\n");
			modificarJugador();

			break;
		case 9:
			System.out.println("Dar de alta lógica un jugador");

			altaBajaJugador();
			break;

		case 10:
			System.out.println("Saliendo del programa...");
			try {
				Thread.sleep(2000);
				System.out.println("Fin programa.");
			} catch (InterruptedException e) {
				System.exit(0);
			}
			System.exit(0);

		default:
			System.out.println("Valor introducido incorrecto");
			break;
		}
	}

	public void altaBajaJugador() {
		JugadorAnchoFijoDTO jugador = null;
		System.out.println("\tIntroduce el id del jugador =");
		try {
			int id = sc.nextInt();
			jugador = j.buscarPorId(id);
			System.out.println("el jugador a modificar estado esta = " + jugador.isActivo());
			j.eliminar(jugador);
		} catch (InputMismatchException e) {
			System.err.println("Ingresa valores correcto");
		}

	}

	public void nuevoJugador() {
		System.out.println("\tCrear nuevo jugador. \n\n " + "Obs: longitud de carácteres:\n"
				+ "\n nombre: 25. \n apellido: 25. \n fechaNacimiento: 4. \n equipo: 20. \n activo: 5.\n id: 3\n"
				+ "Datos a ingresar:  nombre, apellido, fechaNacimiento, equipo, activo , id");

		try {

			j.insertar(AddDatosJugador());
		} catch (InputMismatchException e) {
			System.err.println("Has  ingresado datos incorrectos.");

		}

	}

	public void modificarJugador() {
		JugadorAnchoFijoDTO jugadorAModificar = null;
		JugadorAnchoFijoDTO jugadorAux = null;

		System.out.println("\t Introduce el id del jugador =");
		int id = sc.nextInt();

		jugadorAux = j.buscarPorId(id);

		jugadorAModificar = AddDatosJugador();
		JugadorAnchoFijoDTO.aid--;// se mantiene el valor
		jugadorAModificar.setId(jugadorAux.getId());

		j.modificar(jugadorAModificar);

	}

}
