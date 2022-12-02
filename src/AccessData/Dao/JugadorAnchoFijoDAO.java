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
	List<JugadorAnchoFijoDTO> jugadores = new ArrayList<>();

//	public JugadorAnchoFijoDAO (){
//		
//	}
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
		reg.put("ACTIVO", String.valueOf(t.isActivo()));
		System.out.println(reg);
		faa.insertar(reg);
		reg.clear();

		mostrarDatos(0);

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
						faa.Modificar(79 + contador, String.valueOf(false));
						System.out.println("jugador " + t.getNombre() + " dado de baja.(Lógica)");

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
						faa.Modificar(79 + contador, String.valueOf(t.isActivo()));

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

	public List<JugadorAnchoFijoDTO> buscarPorNombre(String name) {

		int contador = 0;

		try {
			for (;;) {
//				String //names;
				String names = new String(faa.readCharsFromFile(contador + 4, 25));
//				names = new String(faa.readCharsFromFile(contador, 4));
//				faa.getNumReg() >= contador ||
				if (  names == null || names == "" || names.equals("   ") || names == "    ") {

					break;
				}

				else if (names != null) {

					if (name.charAt(0) == names.charAt(0) && name.charAt(1) == names.charAt(1)
							&& name.charAt(2) == names.charAt(2)) {
						mostrarDatos(contador);
					}
						if(contador < faa.getNumReg()) {
					contador += 84;
					continue;
						}else {
							break;
						}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.jugadores;

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
			boolean active = Boolean.parseBoolean(new String(faa.readCharsFromFile(i + 79, 5)));
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

		try {
			for (;;) {
				String data;

				data = new String(faa.readCharsFromFile(contador, 4));
				try {
					int valr = Integer.valueOf(data);
				} catch (NumberFormatException e) {
					break;
				}
				if (data == null || data == "" || data.equals("   ") || data == "    ") {

					break;
				} else if (data != null) {
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

	@Override
	public void cargarJugadores() {

	}

	@Override
	public void volcarTxt() {
		/**
		 * String a = "C:\\Users\\34605\\Desktop\\alee" + new
		 * SimpleDateFormat("ddMMyy").format(new Date()) + ".dat o txt";
		 */
		String ruta = ".\\resources\\jugadores_" + new SimpleDateFormat("ddMMyy").format(new Date()) + ".txt";

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

				buffer.write("creado");// escribo todos los datos de los jugadores

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

				buffer.write("creado");// escribo todos los datos de los jugadores

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

}
