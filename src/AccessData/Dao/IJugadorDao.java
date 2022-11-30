package AccessData.Dao;

import java.util.List;

public interface IJugadorDao<T> {
	void insertar(T t);

	void eliminar(T t);

	void modificar(T t);

	T buscarPorId(int id);

	List<T> listaJugadores();

	void cargarJugadores();

	void volcarTxt();

	void volcarDat();

}
