package BisnessLogic;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LogicaNegocio {
	public void menuPrincipal() {
		Scanner sc = new Scanner(System.in);
		System.err.print("\t Menú de acciones:\n" + "1. Alta jugador\n" + "2. Consulta ficha jugador \n"
		// + " 2.1 busqueda por id\n"
		// + " 2.2 busqueda por nombre\n"
				+ "3. Listado de jugadores\n"
				// + " 3.1 busqueda por id\n"
				+ "4. Dar baja jugador \n" + "5. Generar volcado a extension txt\n"
				+ "6. Cargar jugadores desde txt/csv\n" + "7. Generar volcado a extensión dat\n" + "8. Salir \n\n "
				+ "Ingresa valor de acción a realizar: ");
		do {
			int value = 0;
			try {
				value = sc.nextInt();
			} catch (InputMismatchException a) {

			}

			eventos(value);
		} while (true);
	}


	void eventos(int value) {
		switch (value) {
		case 1:
			System.out.print("alta");
			// altaJugador(a);//add
			break;
		case 2:
			System.out.print("ficha ");
			System.out.print("	1. busqueda por id\n" + "	2. busqueda por nombre\n");
			break;
		case 3:
			// listadoJugadores();//listo
			System.out.println("listado de jugadores \n" + "	3.1 busqueda por id\n");
			break;
		case 4:
			// bajaJugador();
			System.out.print("baja JUgador");
			break;
		case 5:
			System.out.println("a txt");
			break;
		case 6:

			System.out.println("cargar de txt csv");
			break;
		case 7:
			System.out.println("a dat");
			break;
		case 8:
			System.out.println("exit");
		default:
			System.out.println("valor introducido incorrecto");
			break;
		}
	}

}
