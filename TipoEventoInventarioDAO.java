package kalangos.kpizza.dao;

import gminet.infra.dao.DataAccessObject;
import gminet.infra.db.TransactionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kalangos.kpizza.dto.TipoEventoInventario;

public class TipoEventoInventarioDAO extends DataAccessObject{
	
	public static TipoEventoInventario findByPK(int tipo_evento_inventarioCodigo, Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("SELECT A.CD_TIPO      CD_TIPO  ");
sbSql.append("     , A.TX_NOME_TIPO      TX_NOME_TIPO     ");
sbSql.append("  FROM TIPO_EVENTO_INVENTARIO        A ");
sbSql.append(" WHERE A.CD_TIPO  = ? ");

TipoEventoInventario tipo_evento_inventario = null;
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	setInt(ps, 1, tipo_evento_inventarioCodigo);
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
		tipo_evento_inventario = fromResultSet(rs);
	}
	close(ps);
} catch (SQLException sqle) {
	throw new TransactionException("TipoEventoInventarioDAO.findByPK ("
			+ sqle.getMessage() + ")");
}
return tipo_evento_inventario;
}

public static List<TipoEventoInventario> findAll(Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("SELECT A.CD_TIPO      CD_TIPO  ");
sbSql.append("     , A.TX_NOME_TIPO      TX_NOME_TIPO     ");
sbSql.append("  FROM TIPO_EVENTO_INVENTARIO        A ");
sbSql.append(" ORDER BY A.TX_NOME_TIPO ");
List<TipoEventoInventario> tipo_evento_inventariosList = new ArrayList<TipoEventoInventario>(100);
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
		TipoEventoInventario tipo_evento_inventario = fromResultSet(rs);
		tipo_evento_inventariosList.add(tipo_evento_inventario);
	}
	close(rs);
} catch (SQLException sqle) {
	throw new TransactionException("TipoEventoInventarioDAO.findAll ("
			+ sqle.getMessage() + ")");
}
return tipo_evento_inventariosList;
}

public static void insert(TipoEventoInventario tipo_evento_inventario, Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("INSERT INTO TIPO_EVENTO_INVENTARIO    ");
sbSql.append("     ( CD_TIPO      ");
sbSql.append("     , TX_NOME_TIPO         ");
sbSql.append(") VALUES (?,?)");
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	int pos = 1;
	setInt(ps, pos++, tipo_evento_inventario.getCodigo());
	setString(ps, pos++, tipo_evento_inventario.getNome());
	ps.executeUpdate();
	close(ps);
} catch (SQLException sqle) {
	throw new TransactionException("TipoEventoInventarioDAO.insert ("
			+ sqle.getMessage() + ")");
}
}

public static void update(TipoEventoInventario tipo_evento_inventario, Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("UPDATE TIPO_EVENTO_INVENTARIO           ");
sbSql.append("   SET TX_NOME_TIPO       = ? ");
sbSql.append(" WHERE CD_TIPO    = ? ");
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	int pos = 1;
	setString(ps, pos++, tipo_evento_inventario.getNome());
	setInt(ps, pos++, tipo_evento_inventario.getCodigo());
	ps.executeUpdate();
	close(ps);
} catch (SQLException sqle) {
	throw new TransactionException("TipoEventoInventarioDAO.update ("
			+ sqle.getMessage() + ")");
}
}

public static void delete(TipoEventoInventario tipo_evento_inventario, Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("DELETE FROM TIPO_EVENTO_INVENTARIO  ");
sbSql.append(" WHERE CD_TIPO = ?");
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	setInt(ps, 1, tipo_evento_inventario.getCodigo());
	ps.executeUpdate();
	close(ps);
} catch (SQLException sqle) {
	throw new TransactionException("TipoEventoInventarioDAO.delete ("
			+ sqle.getMessage() + ")");
}
}

public static int getNextCodigo(Connection conn)
	throws TransactionException {

try {
	StringBuffer sbSql = new StringBuffer();
	sbSql.append("SELECT MAX(CD_TIPO) AS CD_MAX ");
	sbSql.append("  FROM TIPO_EVENTO_INVENTARIO        ");
	
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	ResultSet rs = ps.executeQuery();
	int max = 0;
	if (rs.next()) {
		max = rs.getInt("CD_MAX");
	}
	close(rs);
	return max + 1;
} catch (SQLException sqlEx) {
	throw new TransactionException(sqlEx);
}
}

private static TipoEventoInventario fromResultSet(ResultSet rs) throws SQLException {

// TIPO_EVENTO_INVENTARIO
TipoEventoInventario tipo_evento_inventario = new TipoEventoInventario();
tipo_evento_inventario.setCodigo(getInt(rs, "CD_TIPO"));
tipo_evento_inventario.setNome(getString(rs, "TX_NOME_TIPO"));

//
return tipo_evento_inventario;
}

}
