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
import kalangos.kpizza.dto.ClienteEndereco;

public class ClienteEnderecoDAO extends DataAccessObject {

    public static ClienteEndereco findByPK(int enderecoCodigo, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_CLIENTE     CD_CLIENTE    ");
        sbSql.append("     , A.CD_ENDERECO    CD_ENDERECO   ");
        sbSql.append("     , A.NR_CEP         NR_CEP        ");
        sbSql.append("     , A.TX_RUA         TX_RUA        ");
        sbSql.append("     , A.TX_BAIRRO      TX_BAIRRO     ");
        sbSql.append("     , A.TX_CIDADE      TX_CIDADE     ");
        sbSql.append("     , A.TX_NUMERO      TX_NUMERO     ");
        sbSql.append("     , A.TX_REFERENCIA  TX_REFERENCIA ");
        sbSql.append("     , A.TS_CADASTRO    TS_CADASTRO   ");
        sbSql.append("  FROM ENDERECO        A ");
        sbSql.append(" WHERE A.CD_ENDERECO = ? ");

        ClienteEndereco endereco = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, enderecoCodigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                endereco = fromResultSet(rs);
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteEnderecoDAO.findByPK (" + sqle.getMessage() + ")");
        }
        return endereco;
    }

    public static List<ClienteEndereco> findAll(Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_CLIENTE     CD_CLIENTE    ");
        sbSql.append("     , A.CD_ENDERECO    CD_ENDERECO   ");
        sbSql.append("     , A.NR_CEP         NR_CEP        ");
        sbSql.append("     , A.TX_RUA         TX_RUA        ");
        sbSql.append("     , A.TX_BAIRRO      TX_BAIRRO     ");
        sbSql.append("     , A.TX_CIDADE      TX_CIDADE     ");
        sbSql.append("     , A.TX_NUMERO      TX_NUMERO     ");
        sbSql.append("     , A.TX_REFERENCIA  TX_REFERENCIA ");
        sbSql.append("     , A.TS_CADASTRO    TS_CADASTRO   ");
        sbSql.append("  FROM ENDERECO    A ");
        sbSql.append(" ORDER BY A.TX_RUA ");
        List<ClienteEndereco> enderecosList = new ArrayList<ClienteEndereco>(100);
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClienteEndereco endereco = fromResultSet(rs);
                enderecosList.add(endereco);
            }
            close(rs);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteEnderecoDAO.findAll (" + sqle.getMessage() + ")");
        }
        return enderecosList;
    }

    public static void insert(ClienteEndereco endereco, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("INSERT INTO ENDERECO ");
        sbSql.append("     ( CD_CLIENTE    ");
        sbSql.append("     , CD_ENDERECO   ");
        sbSql.append("     , NR_CEP        ");
        sbSql.append("     , TX_RUA        ");
        sbSql.append("     , TX_NUMERO     ");
        sbSql.append("     , TX_BAIRRO     ");
        sbSql.append("     , TX_CIDADE     ");
        sbSql.append("     , TX_REFERENCIA ");
        sbSql.append("     , TS_CADASTRO   ");
        sbSql.append(") VALUES (?,?,?,?,?,?,?,?,?)");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, endereco.getCliente().getCodigo());
            setInt(ps, pos++, endereco.getCodigo());
            setInt(ps, pos++, endereco.getCep());
            setString(ps, pos++, endereco.getRua());
            setString(ps, pos++, endereco.getNumero());
            setString(ps, pos++, endereco.getBairro());
            setString(ps, pos++, endereco.getCidade());
            setString(ps, pos++, endereco.getReferencia());
            setTimestamp(ps, pos++, endereco.getDataCadastro());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteEnderecoDAO.insert (" + sqle.getMessage() + ")");
        }
    }

    public static void update(ClienteEndereco endereco, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("UPDATE ENDERECO          ");
        sbSql.append("   SET NR_CEP        = ? ");
        sbSql.append("     , TX_RUA        = ? ");
        sbSql.append("     , TX_NUMERO     = ? ");
        sbSql.append("     , TX_BAIRRO     = ? ");
        sbSql.append("     , TX_CIDADE     = ? ");
        sbSql.append("     , TX_REFERENCIA = ? ");
        sbSql.append(" WHERE CD_CLIENTE    = ? ");
        sbSql.append("   AND CD_ENDERECO   = ? ");

        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, endereco.getCep());
            setString(ps, pos++, endereco.getRua());
            setString(ps, pos++, endereco.getNumero());
            setString(ps, pos++, endereco.getBairro());
            setString(ps, pos++, endereco.getCidade());
            setString(ps, pos++, endereco.getReferencia());
            setInt(ps, pos++, endereco.getCliente().getCodigo());
            setInt(ps, pos++, endereco.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteEnderecoDAO.update (" + sqle.getMessage() + ")");
        }
    }

    public static void delete(ClienteEndereco endereco, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("DELETE FROM ENDERECO  ");
        sbSql.append(" WHERE CD_ENDERECO = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, endereco.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ClienteEnderecoDAO.delete (" + sqle.getMessage() + ")");
        }
    }

    public static int getNextCodigo(Cliente cliente, Connection conn) throws TransactionException {

        try {
            StringBuffer sbSql = new StringBuffer();
            sbSql.append("SELECT MAX(CD_ENDERECO) AS CD_MAX ");
            sbSql.append("  FROM ENDERECO ");
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

    private static ClienteEndereco fromResultSet(ResultSet rs) throws SQLException {

        // ENDERECO
        ClienteEndereco endereco = new ClienteEndereco();
        endereco.setCodigo(getInt(rs, "CD_CLIENTE"));
        endereco.setCodigo(getInt(rs, "CD_ENDERECO"));
        endereco.setCep(getInt(rs, "NR_CEP"));
        endereco.setRua(getString(rs, "TX_RUA"));
        endereco.setBairro(getString(rs, "TX_BAIRRO"));
        endereco.setCidade(getString(rs, "TX_CIDADE"));
        endereco.setNumero(getString(rs, "TX_NUMERO"));
        endereco.setReferencia(getString(rs, "TX_REFERENCIA"));
        endereco.setDataCadastro(getDateFromTimestamp(rs, "TS_CADASTRO"));
        //
        return endereco;
    }

}
