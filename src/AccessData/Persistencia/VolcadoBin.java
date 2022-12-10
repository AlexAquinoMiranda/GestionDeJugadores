package AccessData.Persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.*;
import javafx.util.*;
/**
 * A
 * @author Alexis Aquino
 *
 */
public class VolcadoBin {

	private File fichero = null;
	private List<Pair<String, Integer>> campos;
	private long longReg;
	private long numReg = 0;

	public int longitudFichero() {

		return (int) longReg;
	}

	/**
	 * Constructor VolcadoBin
	 * @param name   name del archivo en donde almacenar.
	 * @param campos campos es la lista que contiene claves y valores de tipo pair
	 */
	public VolcadoBin(String name, List<Pair<String, Integer>> campos) {
		this.fichero = new File(name);
		this.campos = campos;
		longReg = 0;
		for (Pair<String, Integer> campo : campos) {
			this.longReg += campo.getValue();
		}
		if (this.fichero.exists()) {
			this.numReg = this.fichero.length() / this.longReg;
		}
	}

	public long getNumReg() {
		return numReg;
	}

	/**
	 * Modificar datos de un jugador
	 * @param seek seek es la posicion del puntero en el fichero
	 * @param data data es la longitud de bytes que va a modificar a partir de
	 *             puntero Ej: seek = 0 data = 5 { modifica dato en tipo string del
	 *             0 al 5 }
	 */
	public void Modificar(int seek, String data) {
		RandomAccessFile file;

		try {
			file = new RandomAccessFile(fichero, "rw");
			file.seek(seek);
			file.write(data.getBytes());
			file.close();
		} catch (IOException e) {
		}

	}

	/**
	 * leer datos del fichero desde una posicion hasta una longitud
	 * @param seek  seek es la posición del puntero
	 * @param chars chars es la longitud de dato que va a leer
	 * @return chars de longitud especificado
	 * @throws IOException
	 */
	public byte[] readCharsFromFile(int seek, int chars) throws IOException {

		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		file.seek(seek);
		byte[] bytes = new byte[chars];

		file.read(bytes);
		file.close();
		return bytes;
	}

	/**
	 * insertar datos en el fichero
	 * 
	 * @param reg reg es el mapa que contiene los registros de clave valor
	 */
	public void insertar(Map<String, String> reg) {
		insertar(reg, this.numReg++);
	}

	private void insertar(Map<String, String> reg, long pos) {
		try (RandomAccessFile faa = new RandomAccessFile(fichero, "rws")) {

			faa.seek(pos * this.longReg);
			for (Pair<String, Integer> campo : this.campos) {
				String nomCampo = campo.getKey();
				Integer longCampo = campo.getValue();

				String valorCampo = reg.get(nomCampo);

				if (valorCampo == null) {
					valorCampo = "";
				}
				String valorCampoForm = String.format("%1$-" + longCampo + "s", valorCampo);

				faa.write(valorCampoForm.getBytes("UTF-8"), 0, longCampo);
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

}
