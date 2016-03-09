package kalangos.kpizza.dao;

import gminet.infra.dao.DataAccessObject;
import gminet.infra.db.TransactionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kalangos.kpizza.dto.Cliente;
import kalangos.kpizza.dto.ClienteTelefone;

public class ClienteTelefoneDAO extends DataAccessObject {

    public static ClienteTelefone findByPK(int telefoneCodigo, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_CLIENTE     CD_CLIENTE   ");
        sbSql.append("     , A.CD_TELEFONE    CD_TELEFONE  ");
        sbSql.append("     , A.NR_DDD         NR_DDD       ");
        sbSql.append("     , A.NR_TELEFONE    NR_TELEFONE  ");
        sbSql.append("     , A.NR_RAMAL       NR_RAMAL     ");
        sbSql.append("     , A.TS_CADASTRO    TS_CADASTRO  ");
        sbSql.append("  FROM TELEFONE        A ");
        sbSql.append(" WHERE A.CD_TELEFONE = ? ");

        ClienteTelefone telefone = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, telefoneCodigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                telefone = fromResultSet(rs);
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteTelefoneDAO.findByPK (" + sqle.getMessage() + ")");
        }
        return telefone;
    }

    public static List<ClienteTelefone> findAll(Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_CLIENTE     CD_CLIENTE   ");
        sbSql.append("     , A.CD_TELEFONE    CD_TELEFONE  ");
        sbSql.append("     , A.NR_DDD         NR_DDD       ");
        sbSql.append("     , A.NR_TELEFONE    NR_TELEFONE  ");
        sbSql.append("     , A.NR_RAMAL       NR_RAMAL     ");
        sbSql.append("     , A.TS_CADASTRO    TS_CADASTRO  ");
        sbSql.append("  FROM TELEFONE    A ");
        sbSql.append(" ORDER BY A.NR_TELEFONE ");
        List<ClienteTelefone> telefonesList = new ArrayList<ClienteTelefone>(100);
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClienteTelefone telefone = fromResultSet(rs);
                telefonesList.add(telefone);
            }
            close(rs);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteTelefoneDAO.findAll (" + sqle.getMessage() + ")");
        }
        return telefonesList;
    }

    public static void insert(ClienteTelefone telefone, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("INSERT INTO TELEFONE ");
        sbSql.append("     ( CD_CLIENTE    ");
        sbSql.append("     , CD_TELEFONE   ");
        sbSql.append("     , NR_DDD        ");
        sbSql.append("     , NR_TELEFONE   ");
        sbSql.append("     , NR_RAMAL      ");
        sbSql.append("     , TS_CADASTRO   ");
        sbSql.append(") VALUES (?,?,?,?,?,?)");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, telefone.getCliente().getCodigo());
            setInt(ps, pos++, telefone.getCodigo());
            setInt(ps, pos++, telefone.getDdd());
            setInt(ps, pos++, telefone.getTelefone());
            setInt(ps, pos++, telefone.getRamal());
            setTimestamp(ps, pos++, telefone.getDataCadastro());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteTelefoneDAO.insert (" + sqle.getMessage() + ")");
        }
    }

    public static void update(ClienteTelefone telefone, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("UPDATE TELEFONE          ");
        sbSql.append("   SET NR_DDD        = ? ");
        sbSql.append("     , NR_TELEFONE   = ? ");
        sbSql.append("     , TX_NUMERO     = ? ");
        sbSql.append("     , NR_RAMAL      = ? ");
        sbSql.append(" WHERE CD_CLIENTE    = ? ");
        sbSql.append("   AND CD_TELEFONE   = ? ");

        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, telefone.getDdd());
            setInt(ps, pos++, telefone.getTelefone());
            setInt(ps, pos++, telefone.getRamal());
            setInt(ps, pos++, telefone.getCliente().getCodigo());
            setInt(ps, pos++, telefone.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteTelefoneDAO.update (" + sqle.getMessage() + ")");
        }
    }

    public static void delete(ClienteTelefone telefone, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("DELETE FROM TELEFONE  ");
        sbSql.append(" WHERE CD_CLIENTE  = ?");
        sbSql.append("   AND CD_TELEFONE = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, telefone.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteTelefoneDAO.delete (" + sqle.getMessage() + ")");
        }
    }

    public static int getNextCodigo(Cliente cliente, Connection conn) throws TransactionException {

        try {
            StringBuffer sbSql = new StringBuffer();
            sbSql.append("SELECT MAX(CD_TELEFONE) AS CD_MAX ");
            sbSql.append("  FROM TELEFONE ");
            sbSql.append(" WHERE CD_CLIENTE = ?");

            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, cliente.getCodigo());
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

    private static ClienteTelefone fromResultSet(ResultSet rs) throws SQLException {

        // TELEFONE
        ClienteTelefone telefone = new ClienteTelefone();
        telefone.setCodigo(getInt(rs, "CD_CLIENTE"));
        telefone.setCodigo(getInt(rs, "CD_TELEFONE"));
        telefone.setDdd(getInt(rs, "NR_DDD"));
        telefone.setTelefone(getInt(rs, "NR_TELEFONE"));
        telefone.setRamal(getInt(rs, "NR_RAMAL"));
        telefone.setDataCadastro(getDateFromTimestamp(rs, "TS_CADASTRO"));
        //
        return telefone;
    }

}
