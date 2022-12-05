package AccessData.Dto;

public class JugadorAnchoFijoDTO {

//statura 
	public static int aid = 1000;
	private String nombre;
	private String apellido;
	private int fechaNacimiento;
	private String equipo;
	private int id;
	private boolean activo;

	public JugadorAnchoFijoDTO(Jugador jugador) {
		this.nombre = jugador.getNombre();
		this.apellido = jugador.getApellido();
		this.fechaNacimiento = jugador.getFechaNacimiento();
		this.equipo = jugador.getEquipo();
		this.id = jugador.getId();
		this.activo = jugador.isActivo();
	}

	public JugadorAnchoFijoDTO(String nombre, String apellido, int fechaNacimiento, String equipo, int id, boolean ac) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.equipo = equipo;
		this.id = id;
		this.activo = ac;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
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

	@Override
	public String toString() {
		return "{" + "nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento
				+ ", equipo=" + equipo + " , activo= " + activo + ", id=" + id + "} \n";
	}

}
