package BisnessLogic;

import java.util.InputMismatchException;
import java.util.Scanner;
import AccessData.Dao.JugadorAnchoFijoDAO;
import AccessData.Dto.JugadorAnchoFijoDTO;

/**
 * 
 * @author Alexis Aquino
 *
 */
public class LogicaNegocio {
	Scanner sc = new Scanner(System.in);
	JugadorAnchoFijoDAO j = new JugadorAnchoFijoDAO();
	JugadorAnchoFijoDTO player = null;
	Comprobante comprobar = new Comprobante();

	/**
	 * menú principal de la aplicación para gestionar jugadores
	 */
	public void menuPrincipal() {

		do {
			System.out.print("\n\n\n\n\t Menú de acciones:\n" + "1. Alta jugador\n" + "2. Consulta ficha jugador \n"
					+ "3. Listado de jugadores\n" + "4. Dar baja lógica un jugador \n"
					+ "5. Generar volcado a extension txt\n" + "6. Cargar jugadores desde txt/csv\n"
					+ "7. Generar volcado a extensión dat\n" + "8. Salir \n\n "
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

	/**
	 * eventos es un método en donde se va a ejecutar una acción acorde a un valor
	 * ingresado.
	 * 
	 * @param value value es el valor de la accion a realizar
	 */
	void eventos(int value) {
		switch (value) {
		case 1:
			System.out.print("Dar de alta un nuevo Jugador \n");
			comprobar.CrearNuevoJugador();

			break;
		case 2:
			System.out.println("Buscar un jugador\n");
			comprobar.consultarFichaJugador();

			break;
		case 3:
			System.out.println("Lista de todos los jugadores.");
			comprobar.listaDeJugadores();

			break;
		case 4:
			System.out.print("Dar de baja lógica un Jugador");
			comprobar.darBajaLogica();

			break;
		case 5:
			System.out.println("exportar a fichero	.txt");
			comprobar.volcadoTxt();
			break;
		case 6:
			System.out.println("cargar desde txt");
			comprobar.cargarDatos();
			break;
		case 7:
			System.out.println("Exportar a fichero	 .dat");
			comprobar.volcadoDat();
			break;
		case 8:
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

}
