package kalangos.kpizza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kalangos.kpizza.dto.UnidadeMedida;
import gminet.infra.dao.DataAccessObject;
import gminet.infra.db.TransactionException;

public class UnidadeMedidaDAO extends DataAccessObject {
	
	public static UnidadeMedida findByPK(int unidade_medidaCodigo, Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("SELECT A.CD_UNIDADE_MEDIDA      CD_UNIDADE_MEDIDA  ");
sbSql.append("     , A.TX_UNIDADE_MEDIDA      TX_UNIDADE_MEDIDA     ");
sbSql.append("  FROM UNIDADE_MEDIDA        A ");
sbSql.append(" WHERE A.CD_UNIDADE_MEDIDA  = ? ");

UnidadeMedida unidade_medida = null;
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	setInt(ps, 1, unidade_medidaCodigo);
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
		unidade_medida = fromResultSet(rs);
	}
	close(ps);
} catch (SQLException sqle) {
	throw new TransactionException("UnidadeMedidaDAO.findByPK ("
			+ sqle.getMessage() + ")");
}
return unidade_medida;
}

public static List<UnidadeMedida> findAll(Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("SELECT A.CD_UNIDADE_MEDIDA      CD_UNIDADE_MEDIDA  ");
sbSql.append("     , A.TX_UNIDADE_MEDIDA      TX_UNIDADE_MEDIDA     ");
sbSql.append("  FROM UNIDADE_MEDIDA        A ");
sbSql.append(" ORDER BY A.TX_UNIDADE_MEDIDA ");
List<UnidadeMedida> unidade_medidasList = new ArrayList<UnidadeMedida>(100);
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
		UnidadeMedida unidade_medida = fromResultSet(rs);
		unidade_medidasList.add(unidade_medida);
	}
	close(rs);
} catch (SQLException sqle) {
	throw new TransactionException("UnidadeMedidaDAO.findAll ("
			+ sqle.getMessage() + ")");
}
return unidade_medidasList;
}

public static void insert(UnidadeMedida unidade_medida, Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("INSERT INTO UNIDADE_MEDIDA    ");
sbSql.append("     ( CD_UNIDADE_MEDIDA      ");
sbSql.append("     , TX_UNIDADE_MEDIDA         ");
sbSql.append(") VALUES (?,?)");
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	int pos = 1;
	setInt(ps, pos++, unidade_medida.getCodigo());
	setString(ps, pos++, unidade_medida.getNome());
	ps.executeUpdate();
	close(ps);
} catch (SQLException sqle) {
	throw new TransactionException("UnidadeMedidaDAO.insert ("
			+ sqle.getMessage() + ")");
}
}

public static void update(UnidadeMedida unidade_medida, Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("UPDATE UNIDADE_MEDIDA           ");
sbSql.append("   SET TX_UNIDADE_MEDIDA       = ? ");
sbSql.append(" WHERE CD_UNIDADE_MEDIDA    = ? ");
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	int pos = 1;
	setString(ps, pos++, unidade_medida.getNome());
	setInt(ps, pos++, unidade_medida.getCodigo());
	ps.executeUpdate();
	close(ps);
} catch (SQLException sqle) {
	throw new TransactionException("UnidadeMedidaDAO.update ("
			+ sqle.getMessage() + ")");
}
}

public static void delete(UnidadeMedida unidade_medida, Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("DELETE FROM UNIDADE_MEDIDA  ");
sbSql.append(" WHERE CD_UNIDADE_MEDIDA = ?");
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	setInt(ps, 1, unidade_medida.getCodigo());
	ps.executeUpdate();
	close(ps);
} catch (SQLException sqle) {
	throw new TransactionException("UnidadeMedidaDAO.delete ("
			+ sqle.getMessage() + ")");
}
}

public static int getNextCodigo(Connection conn)
	throws TransactionException {

try {
	StringBuffer sbSql = new StringBuffer();
	sbSql.append("SELECT MAX(CD_UNIDADE_MEDIDA) AS CD_MAX ");
	sbSql.append("  FROM UNIDADE_MEDIDA        ");
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

private static UnidadeMedida fromResultSet(ResultSet rs) throws SQLException {

// UNIDADE_MEDIDA
UnidadeMedida unidade_medida = new UnidadeMedida();
unidade_medida.setCodigo(getInt(rs, "CD_UNIDADE_MEDIDA"));
unidade_medida.setNome(getString(rs, "TX_UNIDADE_MEDIDA"));

//
return unidade_medida;
}


}
