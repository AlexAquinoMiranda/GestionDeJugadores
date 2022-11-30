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
		reg.put("ID", "1");
		reg.put("NOMBRE", "AlecisAquino");
		reg.put("APELLIDO", "AQUINOMIRANDA");
		reg.put("FECHA_NAC", "2.003");
		reg.put("EQUIPO", "madrid");
		reg.put("ACTIVO", "true");
		
		faa.insertar(reg);
		System.out.println(reg);
		reg.clear();
		
//		reg.put("DNI", "987654321");
//		reg.put("NOMBRE", "Alexitosuino");
//
//		reg.put("APELLIDO", "AQUINOMIRANDAss");
//
//		faa.insertar(reg, 1);
//		
		
		System.out.println("realizado");
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
