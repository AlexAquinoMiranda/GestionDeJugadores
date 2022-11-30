package AccessData.Dto;

public class Jugador {

	private String nombre;
	private String apellido;
	private int fechaNacimiento;
	private String equipo;
	private int id;
	private boolean activo;

	public Jugador(String nombre, String apellido, int fechaNacimiento, String equipo, int id, boolean activo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.equipo = equipo;
		this.id = id;
		this.activo = activo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(int fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
