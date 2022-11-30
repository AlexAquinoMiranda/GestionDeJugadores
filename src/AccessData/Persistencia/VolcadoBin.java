package AccessData.Persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.*;
import javafx.util.*;

public class VolcadoBin {

	private File fichero = null;
	private List<Pair<String, Integer>> campos;
	private long longReg;
	private long numReg = 0;

	public static void main(String[] args) {
		List campos = new ArrayList();
		campos.add(new Pair("ID", 4));

		campos.add(new Pair("NOMBRE", 25));
		campos.add(new Pair("APELLIDO", 25));
		campos.add(new Pair("FECHA_NAC", 5));
		campos.add(new Pair("EQUIPO", 20));
		campos.add(new Pair("ACTIVO", 4));

		VolcadoBin faa = new VolcadoBin(".\\resources\\accesoAleatorio.dat", campos);
		Map reg = new HashMap();

		reg.put("DNI", "123456789");
		reg.put("ID", "9234");
		reg.put("NOMBRE", "AlecisAquinoqwertfdsaxcvx");
		reg.put("APELLIDO", "AQUINOMIRANDAqertyuiasdfg");
		reg.put("FECHA_NAC", "2.003");
		reg.put("EQUIPO", "madridwertyuiopqwerd");
		reg.put("ACTIVO", "true");

		faa.insertar(reg);
		System.out.println(reg);
		reg.clear();

//		

		System.out.println("realizado");
		try {
			System.out.println("id =  " + new String(faa.readCharsFromFile(0, 4)) + " \nnombre = "
					+ new String(faa.readCharsFromFile(4, 25)) + " \napellido = "
					+ new String(faa.readCharsFromFile(29, 25)) + "\n fecha= "
					+ new String(faa.readCharsFromFile(54, 5)) + "\n equipo =  "
					+ new String(faa.readCharsFromFile(59, 20)) + "\n activo = "
					+ new String(faa.readCharsFromFile(79, 4)));
			
			System.out.println(new String(faa.readCharsFromFile(83, 2)));
			// 0 a 4
			// - 4 a 25 -
			// de 29 a 25 -
			// de 54 a 5
//			de 59 a 20
//			/de 79 a 4

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	VolcadoBin(String name, List<Pair<String, Integer>> campos) {
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

	public void leer() {
		try {
			RandomAccessFile faa = new RandomAccessFile(fichero, "rws");
			faa.seek(0);
			faa.read(new byte[1]);

//			System.out.println());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private byte[] readCharsFromFile(int seek, int chars) throws IOException {
		String nom, apell, dni, id, fec, equi, activo;

		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		file.seek(seek);
		byte[] bytes = new byte[chars];

		file.read(bytes);
		file.close();
		return bytes;
	}

	public void insertar(Map<String, String> reg) {
		insertar(reg, this.numReg++);
	}

	public void insertar(Map<String, String> reg, long pos) {
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
