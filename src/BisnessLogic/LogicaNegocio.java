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
					+ "4. Dar baja jugador \n" + "5. Generar volcado a extension txt\n"
					+ "6. Cargar jugadores desde txt/csv\n" + "7. Generar volcado a extensión dat\n"
					+ "8. Modify \n9. Salir \n\n " + "Ingresa valor de acción a realizar: ");

			int value = 0;
			try {
				value = sc.nextInt();
			} catch (InputMismatchException a) {

			}

			eventos(value);
		} while (true);
	}

	public void add() {

		System.out.println("\tCrear nuevo jugador. \n\n " + "Obs: longitud de carácteres:\n"
				+ "\n nombre: 25. \n apellido: 25. \n fechaNacimiento: 4. \n equipo: 20. \n activo: 5.\n id: 3\n"
				+ "Datos a ingresar:  nombre, apellido, fechaNacimiento, equipo, activo , id");
		// + " \n Ejemplo: Alex;Aquino;2003;Madrid;true;001 \n\n \tIntoduce datos=");
		System.out.println("\n nombre= ");
		String dato = sc.next();

		System.out.println("\napellido= ");
		String apellido = sc.next();
		System.out.println("\nfechaNacimiento = ");
		int naci = sc.nextInt();
		System.out.println("\n equipo = ");
		String team = sc.next();
		System.out.println("\n activo (true/false)= ");
		boolean v = sc.nextBoolean();
		System.out.println("\n id = ");
		int id = sc.nextInt();

//		String value[] = { "Alex", "Aquino", "2003", "Madrid", "true", "1", "", "" };
//		 value = dato.split(";");
//		int nacimiento = (int) Integer.valueOf(value[2]);
//		int aid = (int) Integer.valueOf(value[5]);
//		boolean estado = Boolean.parseBoolean(value[4]);
//		player = new JugadorAnchoFijoDTO(value[0], value[1], nacimiento, value[3], aid, estado);
		player = new JugadorAnchoFijoDTO(dato, apellido, naci, team, id, v);
		System.out.println("desde metodo add");
		System.out.println(player.toString());
		j.insertar(player);
	}

	public void buscarJugador() {
		System.out.println(" 1. Busqueda por id\n" + " 2. Busqueda por nombre\n 3. Volver al menú principal.\n" + "Elige una opción a realizar = ");
		int value = 0;
		do  {
		try {
			value = sc.nextInt();
		} catch (InputMismatchException a) {

		}
		if (value == 1) {
			System.out.println("introduce el id = ");
			int id = sc.nextInt();
			System.out.println("buscando id " + id + "..");
			j.buscarPorId(id);
		} else if (value == 2) {
			System.out.println("introduce nombre de jugador = ");
			String nombre = sc.next();
			System.out.println("buscando al jugador " + nombre + "...");
			j.buscarPorNombre(nombre);
			
			break;
		}else if(value ==3){
			break;
		}
		}	while(value ==3);
	}

	void eventos(int value) {
		switch (value) {
		case 1:
			System.out.print("alta");
			add();
			break;
		case 2:
			buscarJugador();
			break;
		case 3:
			j.listaJugadores();
			
			System.out.println("\n\t Filtrar jugador: ");// listo
			buscarJugador();
			break;
			
//			System.out.println("listado de jugadores \n" + "	3.1 busqueda por id\n");
			
		case 4:
			// bajaJugador();
			baja();
			System.out.print("baja JUgador");
			break;
		case 5:
			System.out.println("a txt");
			j.volcarTxt();
			break;
		case 6:

			System.out.println("cargar de txt csv");
			break;
		case 7:
			System.out.println("a dat");
			j.volcarDat();
			break;
		case 8:
			System.out.println("modify\n");
			modify();
			break;
		case 9: 
			System.out.println("Saliendo del programa...");
			try {
				Thread.sleep(2000);
				System.out.println("fin programa");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.exit(0);
			}
			System.exit(0);
			
		default:
			System.out.println("valor introducido incorrecto");
			break;
		}
	}
public void baja() {
	JugadorAnchoFijoDTO jugador = null;
	System.out.println("\tIntroduce el id del jugador =");
	int id = sc.nextInt();
	jugador = j.buscarPorId(id);
	j.eliminar(jugador);
}
	public void modify() {
		
		JugadorAnchoFijoDTO jNew = null;

		System.out.println("\tIntroduce el id del jugador =");
		int id = sc.nextInt();

		jNew = j.buscarPorId(id);

		System.out.println("\n nombre= ");
		String name = sc.next();
		jNew.setNombre(name);
		System.out.println("\napellido= ");
		String apellido = sc.next();
		jNew.setApellido(apellido);
		System.out.println("\nfechaNacimiento = ");
		int naci = sc.nextInt();
		jNew.setFechaNacimiento(naci);
		System.out.println("\n equipo = ");
		String team = sc.next();
		jNew.setEquipo(team);
		System.out.println("\n activo (true/false)= ");
		boolean v = sc.nextBoolean();
		jNew.setActivo(v);

		j.modificar(jNew);
	}
	
	
	public void altaBajaJugador() {
		
	}
	public void nuevoJugador() {
		
	}

	public void modificarJugador() {
		
	}
	

}
