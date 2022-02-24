package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	T modelFromResultSet(ResultSet resultSet) throws SQLException;

	int countRow();

	T create(T input);

	T read(int id);

	List<T> readAll();

	boolean delete(int id);
}
