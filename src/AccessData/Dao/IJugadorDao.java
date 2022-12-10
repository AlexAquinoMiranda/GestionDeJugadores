package AccessData.Dao;

import java.util.List;
/**
 * 
 * @author Alexis Aquino
 *
 * @param <T> objeto a realizar acciones
 */
public interface IJugadorDao<T> {
	/**
	 * Insertar un nuevo jugador al fichero de longitud fija.
	 * 
	 * @param t t es objeto del que vamos a guardar los datos
	 */
	void insertar(T t);

	/**
	 * eliminar un jugador del fichero
	 * 
	 * @param t t es el objeto en donde vamos a comprar el id con los que tenemos
	 *          almacenado en el ficheros
	 */
	void eliminar(T t);

	/**
	 * modificar datos de un objeto
	 * 
	 * @param t t es el objeto que contiene los nuevos datos del jugador, { solo
	 *          cambia los demas datos, mantiene el mismo id
	 */
	void modificar(T t);

	/**
	 * Buscar un jugador por su id en el fichero
	 * 
	 * @param id id es el identificador del jugador que es de longitud 4
	 * @return Objeto que contiene el id especificado con sus datos almacenados en
	 *         el fichero.
	 */
	T buscarPorId(int id);

	List<T> listaJugadores();

	/**
	 * Cargar jugadores es un metodo que tiene la ruta de un fichero por defecto en
	 * donde tiene almacenados 10 jugadores y los agrega al fichero principal.
	 */
	void cargarJugadores();

	/**
	 * volcar a txt es un metodo que crea un nuevo fichero con nombre JugadoresTXT +
	 * ddMMYY (la fecha actual que se llama al metodo) con todos los datos de los
	 * jugadores
	 */
	void volcarTxt();

	/**
	 * volcar a dat es un metodo que crea un nuevo fichero con nombre JugadoresRAF+
	 * ddMMYY (la fecha actual que se llama al metodo) con todos los datos de los
	 * jugadores
	 */
	void volcarDat();

}
