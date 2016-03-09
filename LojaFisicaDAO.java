package kalangos.kpizza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kalangos.kpizza.dto.LojaFisica;
import gminet.infra.dao.DataAccessObject;
import gminet.infra.db.TransactionException;

public class LojaFisicaDAO extends DataAccessObject {

    public static LojaFisica findByPK(int loja_fisicaCodigo, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_LOJA_FISICA      CD_LOJA_FISICA  ");
        sbSql.append("     , A.TX_LOJA_FISICA      TX_LOJA_FISICA     ");
        sbSql.append("  FROM LOJA_FISICA        A ");
        sbSql.append(" WHERE A.CD_LOJA_FISICA  = ? ");

        LojaFisica loja_fisica = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, loja_fisicaCodigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                loja_fisica = fromResultSet(rs);
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("LojaFisicaDAO.findByPK (" + sqle.getMessage() + ")");
        }
        return loja_fisica;
    }

    public static List<LojaFisica> findAll(Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_LOJA_FISICA      CD_LOJA_FISICA  ");
        sbSql.append("     , A.TX_LOJA_FISICA      TX_LOJA_FISICA     ");
        sbSql.append("  FROM LOJA_FISICA        A ");
        sbSql.append(" ORDER BY A.TX_LOJA_FISICA ");
        List<LojaFisica> loja_fisicasList = new ArrayList<LojaFisica>(100);
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LojaFisica loja_fisica = fromResultSet(rs);
                loja_fisicasList.add(loja_fisica);
            }
            close(rs);
        } catch (SQLException sqle) {
            throw new TransactionException("LojaFisicaDAO.findAll (" + sqle.getMessage() + ")");
        }
        return loja_fisicasList;
    }

    public static void insert(LojaFisica loja_fisica, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("INSERT INTO LOJA_FISICA    ");
        sbSql.append("     ( CD_LOJA_FISICA      ");
        sbSql.append("     , TX_LOJA_FISICA         ");
        sbSql.append(") VALUES (?,?)");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, loja_fisica.getCodigo());
            setString(ps, pos++, loja_fisica.getNome());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("LojaFisicaDAO.insert (" + sqle.getMessage() + ")");
        }
    }

    public static void update(LojaFisica loja_fisica, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("UPDATE LOJA_FISICA           ");
        sbSql.append("   SET TX_LOJA_FISICA       = ? ");
        sbSql.append(" WHERE CD_LOJA_FISICA    = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setString(ps, pos++, loja_fisica.getNome());
            setInt(ps, pos++, loja_fisica.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("LojaFisicaDAO.update (" + sqle.getMessage() + ")");
        }
    }

    public static void delete(LojaFisica loja_fisica, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("DELETE FROM LOJA_FISICA  ");
        sbSql.append(" WHERE CD_LOJA_FISICA = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, loja_fisica.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("LojaFisicaDAO.delete (" + sqle.getMessage() + ")");
        }
    }

    public static int getNextCodigo(Connection conn) throws TransactionException {

        try {
            StringBuffer sbSql = new StringBuffer();
            sbSql.append("SELECT MAX(CD_LOJA_FISICA) AS CD_MAX ");
            sbSql.append("  FROM LOJA_FISICA        ");

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

    private static LojaFisica fromResultSet(ResultSet rs) throws SQLException {

        // LOJA_FISICA
        LojaFisica loja_fisica = new LojaFisica();
        loja_fisica.setCodigo(getInt(rs, "CD_LOJA_FISICA"));
        loja_fisica.setNome(getString(rs, "TX_LOJA_FISICA"));

        //
        return loja_fisica;
    }

}
