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
import kalangos.kpizza.dto.ClienteSituacao;
import kalangos.kpizza.dto.LojaVirtual;

public class ClienteDAO extends DataAccessObject {

    public static Cliente findByPK(int clienteCodigo, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_CLIENTE      CD_CLIENTE      ");
        sbSql.append("     , A.TX_NOME         TX_NOME         ");
        sbSql.append("     , A.NR_CPF          NR_CPF          ");
        sbSql.append("     , A.TX_EMAIL        TX_EMAIL        ");
        sbSql.append("     , A.TX_COMENTARIO   TX_COMENTARIO   ");
        sbSql.append("     , A.ID_SITUACAO     ID_SITUACAO     ");
        sbSql.append("     , A.CD_LOJA_VIRTUAL CD_LOJA_VIRTUAL ");
        sbSql.append("     , A.TS_CADASTRO     TS_CADASTRO     ");
        sbSql.append("  FROM CLIENTE        A ");
        sbSql.append(" WHERE A.CD_CLIENTE = ? ");

        Cliente cliente = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, clienteCodigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cliente = fromResultSet(rs);
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteDAO.findByPK (" + sqle.getMessage() + ")");
        }
        return cliente;
    }

    public static List<Cliente> findAll(Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_CLIENTE      CD_CLIENTE      ");
        sbSql.append("     , A.TX_NOME         TX_NOME         ");
        sbSql.append("     , A.NR_CPF          NR_CPF          ");
        sbSql.append("     , A.TX_EMAIL        TX_EMAIL        ");
        sbSql.append("     , A.TX_COMENTARIO   TX_COMENTARIO   ");
        sbSql.append("     , A.ID_SITUACAO     ID_SITUACAO     ");
        sbSql.append("     , A.CD_LOJA_VIRTUAL CD_LOJA_VIRTUAL ");
        sbSql.append("     , A.TS_CADASTRO     TS_CADASTRO     ");
        sbSql.append("  FROM CLIENTE    A ");
        sbSql.append(" ORDER BY A.TX_NOME ");
        List<Cliente> clientesList = new ArrayList<Cliente>(100);
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = fromResultSet(rs);
                clientesList.add(cliente);
            }
            close(rs);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteDAO.findAll (" + sqle.getMessage() + ")");
        }
        return clientesList;
    }

    public static void insert(Cliente cliente, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("INSERT INTO CLIENTE    ");
        sbSql.append("     ( CD_CLIENTE      ");
        sbSql.append("     , TX_NOME         ");
        sbSql.append("     , NR_CPF          ");
        sbSql.append("     , TX_EMAIL        ");
        sbSql.append("     , TX_COMENTARIO   ");
        sbSql.append("     , ID_SITUACAO     ");
        sbSql.append("     , CD_LOJA_VIRTUAL ");
        sbSql.append("     , TS_CADASTRO     ");
        sbSql.append(") VALUES (?,?,?,?,?,?,?,?)");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, cliente.getCodigo());
            setString(ps, pos++, cliente.getNome());
            setString(ps, pos++, cliente.getCpf());
            setString(ps, pos++, cliente.getEmail());
            setString(ps, pos++, cliente.getComentario());
            setInt(ps, pos++, cliente.getSituacao().getCodigo());
            setInt(ps, pos++, cliente.getLojaVirtual().getCodigo());
            setTimestamp(ps, pos++, cliente.getDataCadastro());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteDAO.insert (" + sqle.getMessage() + ")");
        }
    }

    public static void update(Cliente cliente, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("UPDATE CLIENTE           ");
        sbSql.append("   SET TX_NOME       = ? ");
        sbSql.append("     , NR_CPF        = ? ");
        sbSql.append("     , TX_EMAIL      = ? ");
        sbSql.append("     , TX_COMENTARIO = ? ");
        sbSql.append("     , ID_SITUACAO   = ? ");
        sbSql.append(" WHERE CD_CLIENTE    = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setString(ps, pos++, cliente.getNome());
            setString(ps, pos++, cliente.getCpf());
            setString(ps, pos++, cliente.getEmail());
            setString(ps, pos++, cliente.getComentario());
            setInt(ps, pos++, cliente.getSituacao().getCodigo());
            setInt(ps, pos++, cliente.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteDAO.update (" + sqle.getMessage() + ")");
        }
    }

    public static void delete(Cliente cliente, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("DELETE FROM CLIENTE  ");
        sbSql.append(" WHERE CD_CLIENTE = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, cliente.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteDAO.delete (" + sqle.getMessage() + ")");
        }
    }

    public static int getNextCodigo(Connection conn) throws TransactionException {

        try {
            StringBuffer sbSql = new StringBuffer();
            sbSql.append("SELECT MAX(CD_CLIENTE) AS CD_MAX ");
            sbSql.append("  FROM CLIENTE ");

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

    private static Cliente fromResultSet(ResultSet rs) throws SQLException {

        // CLIENTE
        Cliente cliente = new Cliente();
        cliente.setCodigo(getInt(rs, "CD_CLIENTE"));
        cliente.setNome(getString(rs, "TX_NOME"));
        cliente.setEmail(getString(rs, "TX_EMAIL"));
        cliente.setSituacao(ClienteSituacao.getById(getByte(rs, "ID_SITUACAO")));
        // LOJA VIRTUAL
        LojaVirtual lojaVirtual = new LojaVirtual();
        lojaVirtual.setCodigo(getInt(rs, "CD_LOJA_VIRTUAL"));
        cliente.setLojaVirtual(lojaVirtual);
        //
        cliente.setDataCadastro(getDateFromTimestamp(rs, "TS_CADASTRO"));
        //
        return cliente;
    }

}
