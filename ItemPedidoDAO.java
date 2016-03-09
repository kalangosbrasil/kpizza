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
import kalangos.kpizza.dto.Filial;
import kalangos.kpizza.dto.Pedido;
import kalangos.kpizza.dto.PedidoCanal;
import kalangos.kpizza.dto.PedidoFormaPagamento;
import kalangos.kpizza.dto.PedidoSituacao;
import kalangos.kpizza.dto.Usuario;

public class ItemPedidoDAO extends DataAccessObject {

    public static Pedido findByPK(int clienteCodigo, int pedidoCodigo, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_CLIENTE     CD_CLIENTE    ");
        sbSql.append("     , A.CD_PEDIDO      CD_PEDIDO     ");
        sbSql.append("     , A.CD_USUARIO     CD_USUARIO    ");
        sbSql.append("     , A.CD_FORMA_PGT   CD_FORMA_PGT  ");
        sbSql.append("     , A.CD_SITUACAO    CD_SITUACAO   ");
        sbSql.append("     , A.DT_CADASTRO    DT_CADASTRO   ");
        sbSql.append("     , A.HR_CADASTRO    HR_CADASTRO   ");
        sbSql.append("     , A.VL_PAGAMENTO   VL_PAGAMENTO  ");
        sbSql.append("     , A.VL_TROCO       VL_TROCO      ");
        sbSql.append("     , A.VL_TOTAL       VL_TOTAL      ");
        sbSql.append("     , A.CD_CANAL       CD_CANAL      ");
        sbSql.append("     , A.CD_FILIAL      CD_FILIAL     ");
        sbSql.append("     , A.TX_OBSERVACAO  TX_OBSERVACAO ");
        sbSql.append("  FROM PEDIDO         A ");
        sbSql.append(" WHERE A.CD_CLIENTE = ? ");
        sbSql.append("   AND A.CD_PEDIDO  = ? ");

        Pedido pedido = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, pedidoCodigo);
            setInt(ps, pos++, pedidoCodigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pedido = fromResultSet(rs);
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("PedidoDAO.findByPK (" + sqle.getMessage() + ")");
        }
        return pedido;
    }

    public static List<Pedido> findAll(int clienteCodigo, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_CLIENTE     CD_CLIENTE    ");
        sbSql.append("     , A.CD_PEDIDO      CD_PEDIDO     ");
        sbSql.append("     , A.CD_USUARIO     CD_USUARIO    ");
        sbSql.append("     , A.CD_FORMA_PGT   CD_FORMA_PGT  ");
        sbSql.append("     , A.CD_SITUACAO    CD_SITUACAO   ");
        sbSql.append("     , A.TS_PEDIDO      TS_PEDIDO     ");
        sbSql.append("     , A.VL_PAGAMENTO   VL_PAGAMENTO  ");
        sbSql.append("     , A.VL_TROCO       VL_TROCO      ");
        sbSql.append("     , A.VL_TOTAL       VL_TOTAL      ");
        sbSql.append("     , A.CD_CANAL       CD_CANAL      ");
        sbSql.append("     , A.CD_FILIAL      CD_FILIAL     ");
        sbSql.append("     , A.TX_OBSERVACAO  TX_OBSERVACAO ");
        sbSql.append("  FROM PEDIDO         A ");
        sbSql.append(" WHERE A.CD_CLIENTE = ? ");
        sbSql.append(" ORDER BY A.CD_CLIENTE ");
        List<Pedido> pedidosList = new ArrayList<Pedido>(100);
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, clienteCodigo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pedido pedido = fromResultSet(rs);
                pedidosList.add(pedido);
            }
            close(rs);
        } catch (SQLException sqle) {
            throw new TransactionException("PedidoDAO.findAll (" + sqle.getMessage() + ")");
        }
        return pedidosList;
    }

    public static void insert(Pedido pedido, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("INSERT INTO PEDIDO   ");
        sbSql.append("     ( CD_CLIENTE    ");
        sbSql.append("     , CD_PEDIDO     ");
        sbSql.append("     , CD_USUARIO    ");
        sbSql.append("     , CD_FORMA_PGT  ");
        sbSql.append("     , TS_PEDIDO     ");
        sbSql.append("     , VL_PAGAMENTO  ");
        sbSql.append("     , VL_TROCO      ");
        sbSql.append("     , VL_TOTAL      ");
        sbSql.append("     , CD_CANAL      ");
        sbSql.append("     , CD_FILIAL     ");
        sbSql.append("     , TX_OBSERVACAO ");
        sbSql.append(") VALUES (?,?,?,?,?,?,?,?,?,?,?)");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, pedido.getCliente().getCodigo());
            setInt(ps, pos++, pedido.getCodigo());
            setInt(ps, pos++, pedido.getUsuario().getCodigo());
            setInt(ps, pos++, pedido.getFormaPagamento().getCodigo());
            setTimestamp(ps, pos++, pedido.getDataHoraPedido());
            setDouble(ps, pos++, pedido.getValorPagamento());
            setDouble(ps, pos++, pedido.getValorTroco());
            setDouble(ps, pos++, pedido.getValorTotal());
            setInt(ps, pos++, pedido.getCanal().getCodigo());
            setInt(ps, pos++, pedido.getFilial().getCodigo());
            setString(ps, pos++, pedido.getObservacao());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("PedidoDAO.insert (" + sqle.getMessage() + ")");
        }
    }

    public static void update(Pedido pedido, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("UPDATE PEDIDO            ");
        sbSql.append("   SET CD_USUARIO    = ? ");
        sbSql.append("     , CD_FORMA_PGT  = ? ");
        sbSql.append("     , TS_PEDIDO     = ? ");
        sbSql.append("     , VL_PAGAMENTO  = ? ");
        sbSql.append("     , VL_TROCO      = ? ");
        sbSql.append("     , VL_TOTAL      = ? ");
        sbSql.append("     , CD_CANAL      = ? ");
        sbSql.append("     , CD_FILIAL     = ? ");
        sbSql.append("     , TX_OBSERVACAO = ? ");
        sbSql.append(" WHERE CD_CLIENTE    = ? ");
        sbSql.append("   AND CD_PEDIDO     = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, pedido.getUsuario().getCodigo());
            setInt(ps, pos++, pedido.getFormaPagamento().getCodigo());
            setTimestamp(ps, pos++, pedido.getDataHoraPedido());
            setDouble(ps, pos++, pedido.getValorPagamento());
            setDouble(ps, pos++, pedido.getValorTroco());
            setDouble(ps, pos++, pedido.getValorTotal());
            setInt(ps, pos++, pedido.getCanal().getCodigo());
            setInt(ps, pos++, pedido.getFilial().getCodigo());
            setString(ps, pos++, pedido.getObservacao());
            setInt(ps, pos++, pedido.getCliente().getCodigo());
            setInt(ps, pos++, pedido.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("PedidoDAO.update (" + sqle.getMessage() + ")");
        }
    }

    public static void delete(Pedido pedido, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("DELETE FROM PEDIDO    ");
        sbSql.append(" WHERE CD_CLIENTE = ? ");
        sbSql.append("   AND CD_PEDIDO  = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, pedido.getCliente().getCodigo());
            setInt(ps, pos++, pedido.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("PedidoDAO.delete (" + sqle.getMessage() + ")");
        }
    }

    public static int getNextCodigo(int clienteCodigo, Connection conn) throws TransactionException {

        try {
            StringBuffer sbSql = new StringBuffer();
            sbSql.append("SELECT MAX(CD_PEDIDO) AS CD_MAX ");
            sbSql.append("  FROM PEDIDO ");
            sbSql.append(" WHERE CD_CLIENTE = ? ");

            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, clienteCodigo);
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

    private static Pedido fromResultSet(ResultSet rs) throws SQLException {

        // PEDIDO
        Pedido pedido = new Pedido();
        pedido.setCodigo(getInt(rs, "CD_PEDIDO"));
        // CLIENTE
        Cliente cliente = new Cliente();
        cliente.setCodigo(getInt(rs, "CD_CLIENTE"));
        pedido.setCliente(cliente);
        // USUARIO
        Usuario usuario = new Usuario();
        usuario.setCodigo(getInt(rs, "CD_USUARIO"));
        pedido.setUsuario(usuario);
        //
        pedido.setFormaPagamento(PedidoFormaPagamento.getById(getInt(rs, "CD_FORMA_PGT")));
        pedido.setSituacao(PedidoSituacao.getById(getInt(rs, "CD_SITUACAO")));
        pedido.setDataHoraPedido(getDateFromTimestamp(rs, "TS_PEDIDO"));
        pedido.setValorPagamento(getDouble(rs, "VL_PAGAMENTO"));
        pedido.setValorTroco(getDouble(rs, "VL_TROCO"));
        pedido.setValorTotal(getDouble(rs, "VL_TOTAL"));
        pedido.setCanal(PedidoCanal.getById(getInt(rs, "CD_CANAL")));
        // FILIAL
        Filial filial = new Filial();
        filial.setCodigo(getInt(rs, "CD_FILIAL"));
        pedido.setFilial(filial);
        //
        pedido.setObservacao(getString(rs, "TX_OBSERVACAO"));
        //
        return pedido;
    }

}
