package kalangos.kpizza.dao;

import gminet.infra.dao.DataAccessObject;
import gminet.infra.db.TransactionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kalangos.kpizza.dto.LojaVirtual;

public class LojaVirtualDAO extends DataAccessObject{
	
	public static LojaVirtual findByPK(int loja_virtualCodigo, Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("SELECT A.CD_LOJA_VIRTUAL      CD_LOJA_VIRTUAL  ");
sbSql.append("     , A.TX_NOME      TX_NOME     ");
sbSql.append("  FROM LOJA_VIRTUAL        A ");
sbSql.append(" WHERE A.CD_LOJA_VIRTUAL  = ? ");

LojaVirtual loja_virtual = null;
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	setInt(ps, 1, loja_virtualCodigo);
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
		loja_virtual = fromResultSet(rs);
	}
	close(ps);
} catch (SQLException sqle) {
	throw new TransactionException("LojaVirtualDAO.findByPK ("
			+ sqle.getMessage() + ")");
}
return loja_virtual;
}

public static List<LojaVirtual> findAll(Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("SELECT A.CD_LOJA_VIRTUAL      CD_LOJA_VIRTUAL  ");
sbSql.append("     , A.TX_NOME      TX_NOME     ");
sbSql.append("  FROM LOJA_VIRTUAL        A ");
sbSql.append(" ORDER BY A.TX_NOME ");
List<LojaVirtual> loja_virtualsList = new ArrayList<LojaVirtual>(100);
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
		LojaVirtual loja_virtual = fromResultSet(rs);
		loja_virtualsList.add(loja_virtual);
	}
	close(rs);
} catch (SQLException sqle) {
	throw new TransactionException("LojaVirtualDAO.findAll ("
			+ sqle.getMessage() + ")");
}
return loja_virtualsList;
}

public static void insert(LojaVirtual loja_virtual, Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("INSERT INTO LOJA_VIRTUAL    ");
sbSql.append("     ( CD_LOJA_VIRTUAL      ");
sbSql.append("     , TX_NOME         ");
sbSql.append(") VALUES (?,?)");
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	int pos = 1;
	setInt(ps, pos++, loja_virtual.getCodigo());
	setString(ps, pos++, loja_virtual.getNome());
	ps.executeUpdate();
	close(ps);
} catch (SQLException sqle) {
	throw new TransactionException("LojaVirtualDAO.insert ("
			+ sqle.getMessage() + ")");
}
}

public static void update(LojaVirtual loja_virtual, Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("UPDATE LOJA_VIRTUAL           ");
sbSql.append("   SET TX_NOME       = ? ");
sbSql.append(" WHERE CD_LOJA_VIRTUAL    = ? ");
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	int pos = 1;
	setString(ps, pos++, loja_virtual.getNome());
	setInt(ps, pos++, loja_virtual.getCodigo());
	ps.executeUpdate();
	close(ps);
} catch (SQLException sqle) {
	throw new TransactionException("LojaVirtualDAO.update ("
			+ sqle.getMessage() + ")");
}
}

public static void delete(LojaVirtual loja_virtual, Connection conn)
	throws TransactionException {

StringBuffer sbSql = new StringBuffer();
sbSql.append("DELETE FROM LOJA_VIRTUAL  ");
sbSql.append(" WHERE CD_LOJA_VIRTUAL = ?");
try {
	PreparedStatement ps = conn.prepareStatement(sbSql.toString());
	setInt(ps, 1, loja_virtual.getCodigo());
	ps.executeUpdate();
	close(ps);
} catch (SQLException sqle) {
	throw new TransactionException("LojaVirtualDAO.delete ("
			+ sqle.getMessage() + ")");
}
}

public static int getNextCodigo(Connection conn)
	throws TransactionException {

try {
	StringBuffer sbSql = new StringBuffer();
	sbSql.append("SELECT MAX(CD_LOJA_VIRTUAL) AS CD_MAX ");
	sbSql.append("  FROM LOJA_VIRTUAL        ");
	

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

private static LojaVirtual fromResultSet(ResultSet rs) throws SQLException {

// LOJA_VIRTUAL
LojaVirtual loja_virtual = new LojaVirtual();
loja_virtual.setCodigo(getInt(rs, "CD_LOJA_VIRTUAL"));
loja_virtual.setNome(getString(rs, "TX_NOME"));

//
return loja_virtual;
}




}
