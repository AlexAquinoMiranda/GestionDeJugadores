package AccessData.Dao;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import AccessData.Dto.JugadorAnchoFijoDTO;
import AccessData.Persistencia.VolcadoBin;
import javafx.util.Pair;

/**
 * String a = "C:\\Users\\34605\\Desktop\\alee" + new
 * SimpleDateFormat("ddMMyy").format(new Date()) + ".dat o txt";
 * 
 * @author 34605
 *
 */
public class JugadorAnchoFijoDAO implements IJugadorDao<JugadorAnchoFijoDTO> {
	VolcadoBin faa = null;
//	public static int identificador = idAumento();
	List<JugadorAnchoFijoDTO> jugadores = new ArrayList<>();

	public JugadorAnchoFijoDAO() {
		List campos = new ArrayList();
		campos.add(new Pair("ID", 4));
		campos.add(new Pair("NOMBRE", 25));
		campos.add(new Pair("APELLIDO", 25));
		campos.add(new Pair("FECHA_NAC", 5));
		campos.add(new Pair("EQUIPO", 20));
		campos.add(new Pair("ACTIVO", 5));

		faa = new VolcadoBin(".\\resources\\accesoAleatorio.dat", campos);

	}

	@Override
	public void insertar(JugadorAnchoFijoDTO t) {
		Map reg = new HashMap();

		reg.put("ID", String.valueOf(t.getId()));
		reg.put("NOMBRE", t.getNombre());
		reg.put("APELLIDO", t.getApellido());
		reg.put("FECHA_NAC", String.valueOf(t.getFechaNacimiento()));
		reg.put("EQUIPO", t.getEquipo());
		if (t.isActivo() == true) {
			reg.put("ACTIVO", "true ");
		} else {
			reg.put("ACTIVO", "false");
		}

		faa.insertar(reg);
		reg.clear();
	}

	@Override
	public void eliminar(JugadorAnchoFijoDTO t) {
		int contador = 0;
		int valor;
		try {
			for (;;) {
				String data = new String(faa.readCharsFromFile(contador, 4));
				if (data != null) {
					valor = Integer.valueOf(data);
					if (valor == t.getId()) {

						if (t.isActivo() == true) {
							faa.Modificar(79 + contador, "false");
							System.out.println("jugador " + t.getNombre() + " dado de baja.(Lógica)");

							break;
						} else if (t.isActivo() == false) {
							faa.Modificar(79 + contador, "true ");
							System.out.println("jugador " + t.getNombre() + " dado de Alta.(Lógica)");
							break;
						}
					} else {
						contador += 84;
						continue;
					}
				}
			}

		} catch (NumberFormatException | IOException e1) {
			System.out.println("ingresa datos correctos");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void modificar(JugadorAnchoFijoDTO t) {
		int contador = 0;
		int valor;
		try {
			for (;;) {
				String data = new String(faa.readCharsFromFile(contador, 4));
				if (data != null) {
					valor = Integer.valueOf(data);
					if (valor == t.getId()) {

						faa.Modificar(0 + contador, String.valueOf(t.getId()));
						faa.Modificar(4 + contador, t.getNombre());
						faa.Modificar(29 + contador, t.getApellido());
						faa.Modificar(54 + contador, String.valueOf(t.getFechaNacimiento()));
						faa.Modificar(59 + contador, t.getEquipo());
						if (t.isActivo() == false) {
							faa.Modificar(79 + contador, "true ");
						} else {
							faa.Modificar(79 + contador, String.valueOf(t.isActivo()));
						}

						break;
					} else {
						contador += 84;
						continue;
					}
				}
			}

		} catch (NumberFormatException | IOException e1) {
			System.out.println("ingresa datos correctos");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void buscarPorNombre(String name) {
		int contador = 0;
		int a = (int) faa.getNumReg() * 84;
		System.out.println(a);

		try {
			for (;;) {
				// busco el id
				String names = new String(faa.readCharsFromFile(contador + 4, 25));
				// si es nulo o esta vacio me salgo
				if (names == null || names == "" || names.equals("   ") || names == "    ") {

					break;
				}
				// si es distinto que null
				else if (names != null) {
					// compruebo si los tres primeros caracteres son iguals y muestro sus datos
					if (name.charAt(0) == names.charAt(0) && name.charAt(1) == names.charAt(1)
							&& name.charAt(2) == names.charAt(2)) {
						mostrarDatos(contador);
					}
					// salgo del bucle hasta que supere la longitud del fichero
					if (contador < a) {
						contador += 84;
						continue;
					} else {
						break;
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error al buscar por nombre de jugador");
		}

	}

	@Override
	public JugadorAnchoFijoDTO buscarPorId(int id) {
		int valor = 0;
		JugadorAnchoFijoDTO j = null;
		int contador = 0;

		try {
			for (;;) {
				String data = new String(faa.readCharsFromFile(contador, 4));
				if (data != null) {
					valor = Integer.valueOf(data);
					if (valor == id) {
						mostrarDatos(contador);
						j = mostrar(contador);
						break;
					} else {
						contador += 84;
						continue;
					}
				}
			}

		} catch (NumberFormatException | IOException e1) {
			System.out.println("ingresa datos correctos");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return j;
	}

	private JugadorAnchoFijoDTO mostrar(int i) {
		JugadorAnchoFijoDTO j = null;
		try {
//		
			int id = (int) Integer.valueOf(new String(faa.readCharsFromFile(i + 0, 4)));
			String name = new String(faa.readCharsFromFile(i + 4, 25));
			String apellido = new String(faa.readCharsFromFile(i + 29, 25));
			int fecha = (int) Integer.valueOf(new String(faa.readCharsFromFile(i + 54, 4)));
			String equipo = new String(faa.readCharsFromFile(i + 59, 20));
			String condicion = new String(faa.readCharsFromFile(i + 79, 5));
			boolean active = false;
			if (condicion.equals("true ")) {
				active = true;
			} else {
				active = false;
			}

			j = new JugadorAnchoFijoDTO(name, apellido, fecha, equipo, id, active);

		} catch (IOException e) {
		}
		return j;
	}

	private void mostrarDatos(int i) {
		try {
			System.out.println("---------------------\n\nid =  " + new String(faa.readCharsFromFile(i + 0, 4))
					+ " \nnombre = " + new String(faa.readCharsFromFile(i + 4, 25)) + " \napellido = "
					+ new String(faa.readCharsFromFile(i + 29, 25)) + "\nfecha= "
					+ new String(faa.readCharsFromFile(i + 54, 5)) + "\nequipo =  "
					+ new String(faa.readCharsFromFile(i + 59, 20)) + "\nactivo = "
					+ new String(faa.readCharsFromFile(i + 79, 5)) + " \n\n---------------------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<JugadorAnchoFijoDTO> listaJugadores() {
		int contador = 0;
		int a = (int) faa.getNumReg() * 84;
		try {
			for (;;) {
				String data;

				data = new String(faa.readCharsFromFile(contador, 4));
				try {
					int valr = Integer.valueOf(data);
					System.out.println("ID DEL JUGADOR ES= " + valr);
				} catch (NumberFormatException e) {
					break;
				}
				if (data == null || data == "" || data.equals("   ") || data == "    ") {

					break;
				} else if (data != null && contador < a) {
					mostrarDatos(contador);
					contador += 84;
					continue;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.jugadores;
	}

	/**
	 * tengo que borrar lo anterior!!!!!!!!
	 */
	@Override
	public void cargarJugadores() {
//		rita hardcoreada
		String ruta = ".\\resources\\jugadoresTxT_101222.txt";
		File fichero = null;
		FileReader fr = null;
		BufferedReader buffer = null;

		fichero = new File(ruta);
		if (fichero.exists() && fichero.canRead()) {
			try {
				fr = new FileReader(fichero);
				buffer = new BufferedReader(fr);
				String dato = "";
				while ((dato = buffer.readLine()) != null) {

//					dato.replace("} ", "").replace("nombre=", " ").replace(" apellido=", " ").replace(" apellido=", "")
//							.replace(" equipo=", "").replace(" fechaNacimiento=", " ").replace(" id=", "")
//							.replace(" activo=", " ");

					String values[] = dato.replace("} ", "").replace("nombre=", "").replace(" apellido=", "")
							.replace(" equipo=", "").replace(" fechaNacimiento=", "").replace(" id=", "")
							.replace(" activo=", "").replace("{", "").split(",");

					int nac = Integer.parseInt(values[2]);
					int id = Integer.valueOf(values[5]);
					boolean con = Boolean.valueOf(values[4]);
					insertar(new JugadorAnchoFijoDTO(values[0], values[1], nac, values[3], JugadorAnchoFijoDTO.aid++,
							con));
					System.out.println("Insertando...");
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void volcarTxt() {
		/**
		 * String a = "C:\\Users\\34605\\Desktop\\alee" + new
		 * SimpleDateFormat("ddMMyy").format(new Date()) + ".dat o txt";
		 */
		String ruta = ".\\resources\\jugadoresTxT_" + new SimpleDateFormat("ddMMyy").format(new Date()) + ".txt";
		int i = 0;
		System.out.println(ruta);
		File file = new File(ruta);// me tiene que especificar la ruta
		try {
			file.createNewFile();
			System.out.println("fichero creado");
		} catch (IOException e1) {
			System.err.println("No se ha podido crear el fichero.");
		}
		FileWriter fw = null;
		BufferedWriter buffer = null;

		try {
			if (file.exists() && file.canWrite()) {
				fw = new FileWriter(file, true);
				buffer = new BufferedWriter(fw);
				while (i < faa.getNumReg() * 84) {
					;
					buffer.write(mostrar(i).toString());
					i += 84;
					// escribo todos los datos de los jugadores
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero");
		} catch (IOException e) {
			System.err.println("Error al guardar en el fichero");
		} finally {
			try {
				if (buffer != null) {
					buffer.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.getCause();
			}
		}
	}

	@Override
	public void volcarDat() {
		String ruta = ".\\resources\\jugadoresRAF_" + new SimpleDateFormat("ddMMyy").format(new Date()) + ".dat";

		System.out.println(ruta);
		File file = new File(ruta);// me tiene que especificar la ruta
		try {
			file.createNewFile();
			System.out.println("fichero creado");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		FileWriter fw = null;
		BufferedWriter buffer = null;

		try {
			if (file.exists() && file.canWrite()) {
				fw = new FileWriter(file, true);
				buffer = new BufferedWriter(fw);
				int i = 0;
				while (i < faa.getNumReg() * 84) {
					;
					buffer.write(mostrar(i).toString());
					i += 84;

				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero");
		} catch (IOException e) {
			System.err.println("Error al guardar en el fichero");
		} finally {
			try {
				if (buffer != null) {
					buffer.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.getCause();
			}
		}
	}

	public int idAumento() {
		int a = (int) faa.getNumReg() * 84;
		int i = 0;
		int contador = 1000;
		int id = 0;

		while (i < faa.getNumReg() * 84) {
			String dato;
			try {
				dato = new String(faa.readCharsFromFile(i + 0, 4));
				
				if (dato != null) {
					System.out.println(dato);
					id = Integer.parseInt(dato);

					if (id >=contador) {
						contador = id + 1;
					}

				}
				i += 84;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

//		try {
//			for (;;) {
//				int id = (int) Integer.valueOf(new String(faa.readCharsFromFile(i + 0, 4)));
//				System.out.println(id );
//				
//				if (id > contador) {
//					contador = id + 1;
//				}
//				if (i < a) {
//					i += 84;
//					continue;
//				} else {
//					break;
//				}
//			}
//		} catch (NumberFormatException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return contador;
	}
}
