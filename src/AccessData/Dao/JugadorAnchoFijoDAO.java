package AccessData.Dao;

import java.io.*;
import java.util.List;

import AccessData.Dto.JugadorAnchoFijoDTO;

/**
 * String a = "C:\\Users\\34605\\Desktop\\alee" + new
 * SimpleDateFormat("ddMMyy").format(new Date()) + ".dat o txt";
 * 
 * @author 34605
 *
 */
public class JugadorAnchoFijoDAO implements IJugadorDao<JugadorAnchoFijoDTO> {
	List<JugadorAnchoFijoDTO> jugadores;

	@Override
	public void insertar(JugadorAnchoFijoDTO t) {
		this.jugadores.add(t);

	}

	@Override
	public void eliminar(JugadorAnchoFijoDTO t) {
		for (JugadorAnchoFijoDTO a : this.jugadores) {
			if (a.getId() == t.getId()) {
				a.setActivo(false);
				System.out.println("baja logica");
			}
		}

	}

	@Override
	public void modificar(JugadorAnchoFijoDTO t) {
		for (JugadorAnchoFijoDTO a : this.jugadores) {
			if (a.getId() == t.getId()) {
				a.setActivo(t.isActivo());
				a.setApellido(t.getApellido());
				a.setEquipo(t.getEquipo());
				a.setFechaNacimiento(t.getFechaNacimiento());
				a.setNombre(t.getNombre());
				break;
			}
		}

	}

	@Override
	public JugadorAnchoFijoDTO buscarPorId(int id) {
		JugadorAnchoFijoDTO jugador = null;
		for (JugadorAnchoFijoDTO a : this.jugadores) {
			if (a.getId() == id) {
				jugador = a;
				System.out.println("se ha encontrado al jugador con id " + id + "\n + " + a.toString());
				break;
			}
		}
		return jugador;
	}

	@Override
	public List<JugadorAnchoFijoDTO> listaJugadores() {
		for(JugadorAnchoFijoDTO a : this.jugadores) {
			System.out.println(a.toString());
		}

		return this.jugadores;
	}

	@Override
	public void cargarJugadores() {
		

	}

	@Override
	public void volcarTxt() {
		File file = new File("");// me tiene que especificar la ruta
		FileWriter fw = null;
		BufferedWriter buffer = null;

		try {
			if (file.exists() && file.canWrite()) {
				fw = new FileWriter(file, true);
				buffer = new BufferedWriter(fw);
				for (JugadorAnchoFijoDTO a : jugadores) {

					buffer.write(a.toString());// escribo todos los datos de los jugadores
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
		

	}

}
