package kalangos.kpizza.dao;

import gminet.infra.dao.DataAccessObject;
import gminet.infra.db.TransactionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kalangos.kpizza.dto.Filial;
import kalangos.kpizza.dto.LojaFisica;
import kalangos.kpizza.dto.LojaVirtual;

public class FilialDAO extends DataAccessObject {

    public static Filial findByPK(int filialCodigo, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_FILIAL       CD_FILIAL      ");
        sbSql.append("     , A.TX_NOME_FILIAL  TX_FILIAL         ");
        sbSql.append("     , A.CD_LOJA_VIRTUAL CD_LOJA_VIRTUAL ");
        sbSql.append("     , A.CD_LOJA_FISICA  CD_LOJA_FISICA  ");
        sbSql.append("  FROM FILIAL        A ");
        sbSql.append(" WHERE A.CD_FILIAL = ? ");

        Filial filial = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, filialCodigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                filial = fromResultSet(rs);
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("FilialDAO.findByPK (" + sqle.getMessage() + ")");
        }
        return filial;
    }

    public static List<Filial> findAll(Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_FILIAL       CD_FILIAL      ");
        sbSql.append("     , A.TX_NOME_FILIAL  TX_FILIAL         ");
        sbSql.append("     , A.CD_LOJA_VIRTUAL CD_LOJA_VIRTUAL ");
        sbSql.append("     , A.CD_LOJA_FISICA  CD_LOJA_FISICA  ");
        sbSql.append("  FROM FILIAL    A ");
        sbSql.append(" ORDER BY A.TX_NOME_FILIAL ");
        List<Filial> filialsList = new ArrayList<Filial>(100);
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Filial filial = fromResultSet(rs);
                filialsList.add(filial);
            }
            close(rs);
        } catch (SQLException sqle) {
            throw new TransactionException("FilialDAO.findAll (" + sqle.getMessage() + ")");
        }
        return filialsList;
    }

    public static void insert(Filial filial, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("INSERT INTO FILIAL    ");
        sbSql.append("     ( CD_FILIAL      ");
        sbSql.append("     , TX_NOME_FILIAL ");
        sbSql.append("     , CD_LOJA_VIRTUAL ");
        sbSql.append("     , CD_LOJA_FISICA ");
        sbSql.append(") VALUES (?,?,?,?)");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, filial.getCodigo());
            setString(ps, pos++, filial.getNome());
            setInt(ps, pos++, filial.getLojaVirtual().getCodigo());
            setInt(ps, pos++, filial.getLojaFisica().getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("FilialDAO.insert (" + sqle.getMessage() + ")");
        }
    }

    public static void update(Filial filial, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("UPDATE FILIAL           ");
        sbSql.append("   SET TX_NOME_FILIAL  = ? ");
        sbSql.append("     , CD_LOJA_VIRTUAL = ? ");
        sbSql.append("     , CD_LOJA_FISICA  = ? ");
        sbSql.append(" WHERE CD_FILIAL    = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setString(ps, pos++, filial.getNome());
            setInt(ps, pos++, filial.getLojaVirtual().getCodigo());
            setInt(ps, pos++, filial.getLojaFisica().getCodigo());
            setInt(ps, pos++, filial.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("FilialDAO.update (" + sqle.getMessage() + ")");
        }
    }

    public static void delete(Filial filial, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("DELETE FROM FILIAL  ");
        sbSql.append(" WHERE CD_FILIAL = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, filial.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("FilialDAO.delete (" + sqle.getMessage() + ")");
        }
    }

    public static int getNextCodigo(Connection conn) throws TransactionException {

        try {
            StringBuffer sbSql = new StringBuffer();
            sbSql.append("SELECT MAX(CD_FILIAL) AS CD_MAX ");
            sbSql.append("  FROM FILIAL ");

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

    private static Filial fromResultSet(ResultSet rs) throws SQLException {

        // FILIAL
        Filial filial = new Filial();
        filial.setCodigo(getInt(rs, "CD_FILIAL"));
        filial.setNome(getString(rs, "TX_NOME"));
        // LOJA VIRTUAL
        LojaVirtual lojaVirtual = new LojaVirtual();
        lojaVirtual.setCodigo(getInt(rs, "CD_LOJA_VIRTUAL"));
        // LOJA FISICA
        LojaFisica lojaFisica = new LojaFisica();
        lojaFisica.setCodigo(getInt(rs, "CD_LOJA_FISICA"));
        filial.setLojaFisica(lojaFisica);
        //
        return filial;
    }

}
