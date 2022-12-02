package AccessData.Dao;

import java.util.List;

public interface IDaoJugador<T> {

	/**
	 * alta lógica atrbt esatdo = true or false
	 * 
	 * @param t
	 */
	void altaJugador(T t);

	/**
	 * crear nuevo jugador
	 * 
	 * @param t
	 */
	void nuevoJugador(T t);

	/**
	 * baja logica
	 * 
	 * @param t
	 */
	void eliminarJugador(T t);

	/**
	 * modifiicar datos
	 * 
	 * @param t
	 */
	void modificarJugador(T t);

	/**
	 * busar desde un id
	 * 
	 * @param id
	 * @return
	 */
	T buscarPorId(int id);

	/**
	 * buscar nombre
	 * 
	 * @param name
	 * @return
	 */
	T buscarPorNombre(String name);

	/**
	 * listar jugadores disponibles
	 * 
	 * @return
	 */
	List<T> listaJugadores();

	/**
	 * cargar jgadores desde txt o csv
	 */
	void cargarJugadores();

	/**
	 * volcar a txt (luego lo puedo cargar de nuevo
	 */
	void volcarTxt();

	/**
	 * volcar a dat (luego lo puedo cargar de nuevo
	 */
	void volcarDat();

}
