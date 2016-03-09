package kalangos.kpizza.dao;

import gminet.infra.dao.DataAccessObject;
import gminet.infra.db.TransactionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kalangos.kpizza.dto.ClienteAcesso;

public class ClienteAcessoDAO extends DataAccessObject {

    public static ClienteAcesso findByPK(int codigoCliente, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_CLIENTE  CD_CLIENTE  ");
        sbSql.append("     , A.CD_TIPO     CD_TIPO     ");
        sbSql.append("     , A.TX_SENHA    TX_SENHA    ");
        sbSql.append("  FROM ACESSO       A ");
        sbSql.append(" WHERE CD_CLIENTE = ? ");

        ClienteAcesso acesso = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, codigoCliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                acesso = fromResultSet(rs);
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteAcessoDAO.findByPK (" + sqle.getMessage() + ")");
        }
        return acesso;
    }

    public static List<ClienteAcesso> findAll(Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_CLIENTE  CD_CLIENTE  ");
        sbSql.append("     , A.CD_TIPO     CD_TIPO     ");
        sbSql.append("     , A.TX_SENHA    TX_SENHA    ");
        sbSql.append("  FROM ACESSO       A ");
        List<ClienteAcesso> acessosList = new ArrayList<ClienteAcesso>(100);
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClienteAcesso acesso = fromResultSet(rs);
                acessosList.add(acesso);
            }
            close(rs);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteAcessoDAO.findAll (" + sqle.getMessage() + ")");
        }
        return acessosList;
    }

    public static void insert(ClienteAcesso acesso, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("INSERT INTO ACESSO ");
        sbSql.append("     ( CD_CLIENTE  ");
        sbSql.append("     , CD_TIPO     ");
        sbSql.append("     , TX_SENHA    ");
        sbSql.append(") VALUES (?,?,?)");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, acesso.getCliente().getCodigo());
            setInt(ps, pos++, acesso.getCodigo());
            setString(ps, pos++, acesso.getSenha());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteAcessoDAO.insert (" + sqle.getMessage() + ")");
        }
    }

    public static void update(ClienteAcesso acesso, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("UPDATE ACESSO         ");
        sbSql.append("   SET TX_SENHA   = ? ");
        sbSql.append(" WHERE CD_CLIENTE = ? ");
        sbSql.append("   AND CD_TIPO    = ? ");

        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setString(ps, pos++, acesso.getSenha());
            setInt(ps, pos++, acesso.getCliente().getCodigo());
            setInt(ps, pos++, acesso.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteAcessoDAO.update (" + sqle.getMessage() + ")");
        }
    }

    public static void delete(ClienteAcesso acesso, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("DELETE FROM ACESSO  ");
        sbSql.append(" WHERE CD_CLIENTE = ? ");
        sbSql.append("   AND CD_TIPO    = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, acesso.getCliente().getCodigo());
            setInt(ps, pos++, acesso.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteAcessoDAO.delete (" + sqle.getMessage() + ")");
        }
    }

    private static ClienteAcesso fromResultSet(ResultSet rs) throws SQLException {

        // ACESSO
        ClienteAcesso acesso = new ClienteAcesso();
        acesso.setCodigo(getInt(rs, "CD_CLIENTE"));
        acesso.setCodigo(getInt(rs, "CD_TIPO"));
        acesso.setSenha(getString(rs, "TX_SENHA"));
        //
        return acesso;
    }

}
