package kalangos.kpizza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kalangos.kpizza.dto.TipoItem;
import gminet.infra.dao.DataAccessObject;
import gminet.infra.db.TransactionException;

public class TipoItemDAO extends DataAccessObject{
	
	public static TipoItem findByPK(int tipo_itemCodigo, Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("SELECT A.CD_TIPO      CD_TIPO  ");
sbSql.append("     , A.TX_NOME_TIPO      TX_NOME_TIPO     ");
sbSql.append("  FROM TIPO_ITEM        A ");
sbSql.append(" WHERE A.CD_TIPO  = ? ");

TipoItem tipo_item = null;
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	setInt(ps, 1, tipo_itemCodigo);
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
		tipo_item = fromResultSet(rs);
	}
	close(ps);
} catch (SQLException sqle) {
	throw new TransactionException("TipoItemDAO.findByPK ("
			+ sqle.getMessage() + ")");
}
return tipo_item;
}

public static List<TipoItem> findAll(Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("SELECT A.CD_TIPO        CD_TIPO      ");
sbSql.append("     , A.TX_NOME_TIPO   TX_NOME_TIPO ");
sbSql.append("  FROM TIPO_ITEM                  A  ");
sbSql.append(" ORDER BY A.TX_NOME_TIPO ");
List<TipoItem> tipo_itemsList = new ArrayList<TipoItem>(100);
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
		TipoItem tipo_item = fromResultSet(rs);
		tipo_itemsList.add(tipo_item);
	}
	close(rs);
} catch (SQLException sqle) {
	throw new TransactionException("TipoItemDAO.findAll ("
			+ sqle.getMessage() + ")");
}
return tipo_itemsList;
}

public static void insert(TipoItem tipo_item, Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("INSERT INTO TIPO_ITEM  ");
sbSql.append("     ( CD_TIPO         ");
sbSql.append("     , TX_NOME_TIPO    ");
sbSql.append(") VALUES (?,?)         ");
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	int pos = 1;
	setInt(ps, pos++, tipo_item.getCodigo());
	setString(ps, pos++, tipo_item.getNome());
	ps.executeUpdate();
	close(ps);
} catch (SQLException sqle) {
	throw new TransactionException("TipoItemDAO.insert ("
			+ sqle.getMessage() + ")");
}
}

public static void update(TipoItem tipo_item, Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("UPDATE TIPO_ITEM        ");
sbSql.append("   SET TX_NOME_TIPO = ? ");
sbSql.append(" WHERE CD_TIPO =      ? ");
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	int pos = 1;
	setString(ps, pos++, tipo_item.getNome());
	setInt(ps, pos++, tipo_item.getCodigo());
	ps.executeUpdate();
	close(ps);
} catch (SQLException sqle) {
	throw new TransactionException("TipoItemDAO.update ("
			+ sqle.getMessage() + ")");
}
}

public static void delete(TipoItem tipo_item, Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("DELETE FROM TIPO_ITEM  ");
sbSql.append(" WHERE CD_TIPO = ?");
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	setInt(ps, 1, tipo_item.getCodigo());
	ps.executeUpdate();
	close(ps);
} catch (SQLException sqle) {
	throw new TransactionException("TipoItemDAO.delete ("
			+ sqle.getMessage() + ")");
}
}

public static int getNextCodigo(Connection conn, int iRange, int eRange)
	throws TransactionException {

try {
	StringBuffer sbSql = new StringBuffer();
	sbSql.append("SELECT MAX(CD_TIPO) AS CD_MAX ");
	sbSql.append("  FROM TIPO_ITEM              ");
	sbSql.append(" WHERE CD_TIPO > ? ");
	sbSql.append("   AND CD_TIPO < ? ");

	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	setInt(ps, 1, iRange);
	setInt(ps, 2, eRange);
	ResultSet rs = ps.executeQuery();
	int max = 0;
	if (rs.next()) {
		max = rs.getInt("CD_MAX");
	}
	close(rs);
	return (max != 0 ? max : iRange) + 1;
} catch (SQLException sqlEx) {
	throw new TransactionException(sqlEx);
}
}

private static TipoItem fromResultSet(ResultSet rs) throws SQLException {

// TIPO_ITEM
TipoItem tipo_item = new TipoItem();
tipo_item.setCodigo(getInt(rs, "CD_TIPO"));
tipo_item.setNome(getString(rs, "TX_NOME_TIPO"));

//
return tipo_item;
}




	
}
