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
import kalangos.kpizza.dto.Item;
import kalangos.kpizza.dto.Pedido;
import kalangos.kpizza.dto.PedidoItem;

public class PedidoItemDAO extends DataAccessObject {

    public static PedidoItem findByPK(int clienteCodigo, int pedidoCodigo, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_CLIENTE       CD_CLIENTE      ");
        sbSql.append("     , A.CD_PEDIDO        CD_PEDIDO       ");
        sbSql.append("     , A.NR_SEQ_ITEM      NR_SEQ_ITEM     ");
        sbSql.append("     , A.CD_ITEM          CD_ITEM         ");
        sbSql.append("     , A.VL_VENDA         VL_VENDA        ");
        sbSql.append("     , A.VL_DESCONTO      VL_DESCONTO     ");
        sbSql.append("     , A.NR_QUANTIDADE    NR_QUANTIDADE   ");
        sbSql.append("     , A.NR_SEQ_ITEM_PAI  NR_SEQ_ITEM_PAI ");
        sbSql.append("  FROM ITEM_PEDIDO    A ");
        sbSql.append(" WHERE A.CD_CLIENTE = ? ");
        sbSql.append("   AND A.CD_PEDIDO  = ? ");

        PedidoItem pedidoItem = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, pedidoCodigo);
            setInt(ps, pos++, pedidoCodigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pedidoItem = fromResultSet(rs);
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("PedidoItemDAO.findByPK (" + sqle.getMessage() + ")");
        }
        return pedidoItem;
    }

    public static List<PedidoItem> findAll(int clienteCodigo, int pedidoCodigo, Connection conn)
            throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_CLIENTE       CD_CLIENTE      ");
        sbSql.append("     , A.CD_PEDIDO        CD_PEDIDO       ");
        sbSql.append("     , A.NR_SEQ_ITEM      NR_SEQ_ITEM     ");
        sbSql.append("     , A.CD_ITEM          CD_ITEM         ");
        sbSql.append("     , A.VL_VENDA         VL_VENDA        ");
        sbSql.append("     , A.VL_DESCONTO      VL_DESCONTO     ");
        sbSql.append("     , A.NR_QUANTIDADE    NR_QUANTIDADE   ");
        sbSql.append("     , A.NR_SEQ_ITEM_PAI  NR_SEQ_ITEM_PAI ");
        sbSql.append("  FROM ITEM_PEDIDO    A ");
        sbSql.append(" WHERE A.CD_CLIENTE = ? ");
        sbSql.append("   AND A.CD_PEDIDO  = ? ");
        sbSql.append(" ORDER BY A.NR_SEQ_ITEM ");
        List<PedidoItem> pedidoItemsList = new ArrayList<PedidoItem>(100);
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, clienteCodigo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PedidoItem pedidoItem = fromResultSet(rs);
                pedidoItemsList.add(pedidoItem);
            }
            close(rs);
        } catch (SQLException sqle) {
            throw new TransactionException("PedidoItemDAO.findAll (" + sqle.getMessage() + ")");
        }
        return pedidoItemsList;
    }

    public static void insert(PedidoItem pedidoItem, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("INSERT INTO ITEM_PEDIDO ");
        sbSql.append("     ( CD_CLIENTE      ");
        sbSql.append("     , CD_PEDIDO       ");
        sbSql.append("     , NR_SEQ_ITEM     ");
        sbSql.append("     , CD_ITEM         ");
        sbSql.append("     , VL_VENDA        ");
        sbSql.append("     , VL_DESCONTO     ");
        sbSql.append("     , NR_QUANTIDADE   ");
        sbSql.append("     , NR_SEQ_ITEM_PAI ");
        sbSql.append(") VALUES (?,?,?,?,?,?,?,?)");

        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, pedidoItem.getCliente().getCodigo());
            setInt(ps, pos++, pedidoItem.getPedido().getCodigo());
            setInt(ps, pos++, pedidoItem.getCodigo());
            setInt(ps, pos++, pedidoItem.getItem().getCodigo());
            setDouble(ps, pos++, pedidoItem.getValorVenda());
            setDouble(ps, pos++, pedidoItem.getValorDesconto());
            setInt(ps, pos++, pedidoItem.getQuantidade());
            setInt(ps, pos++, pedidoItem.getPedidoItemPai().getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("PedidoItemDAO.insert (" + sqle.getMessage() + ")");
        }
    }

    public static void update(PedidoItem pedidoItem, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("UPDATE ITEM_PEDIDO         ");
        sbSql.append("   SET CD_ITEM         = ? ");
        sbSql.append("     , VL_VENDA        = ? ");
        sbSql.append("     , VL_DESCONTO     = ? ");
        sbSql.append("     , NR_QUANTIDADE   = ? ");
        sbSql.append("     , NR_SEQ_ITEM_PAI = ? ");
        sbSql.append(" WHERE CD_CLIENTE    = ? ");
        sbSql.append("   AND CD_PEDIDO     = ? ");
        sbSql.append("   AND NR_SEQ_ITEM   = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, pedidoItem.getItem().getCodigo());
            setDouble(ps, pos++, pedidoItem.getValorVenda());
            setDouble(ps, pos++, pedidoItem.getValorDesconto());
            setInt(ps, pos++, pedidoItem.getQuantidade());
            setInt(ps, pos++, pedidoItem.getPedidoItemPai().getCodigo());
            setInt(ps, pos++, pedidoItem.getCliente().getCodigo());
            setInt(ps, pos++, pedidoItem.getPedido().getCodigo());
            setInt(ps, pos++, pedidoItem.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("PedidoItemDAO.update (" + sqle.getMessage() + ")");
        }
    }

    public static void delete(PedidoItem pedidoItem, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("DELETE FROM ITEM_PEDIDO  ");
        sbSql.append(" WHERE CD_CLIENTE    = ? ");
        sbSql.append("   AND CD_PEDIDO     = ? ");
        sbSql.append("   AND NR_SEQ_ITEM   = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, pedidoItem.getCliente().getCodigo());
            setInt(ps, pos++, pedidoItem.getPedido().getCodigo());
            setInt(ps, pos++, pedidoItem.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("PedidoItemDAO.delete (" + sqle.getMessage() + ")");
        }
    }

    public static int getNextCodigo(int clienteCodigo, int pedidoCodigo, Connection conn) throws TransactionException {

        try {
            StringBuffer sbSql = new StringBuffer();
            sbSql.append("SELECT MAX(CD_PEDIDO) AS CD_MAX ");
            sbSql.append("  FROM ITEM_PEDIDO ");
            sbSql.append(" WHERE CD_CLIENTE    = ? ");
            sbSql.append("   AND CD_PEDIDO     = ? ");

            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, clienteCodigo);
            setInt(ps, pos++, pedidoCodigo);
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

    private static PedidoItem fromResultSet(ResultSet rs) throws SQLException {

        PedidoItem pedidoItem = new PedidoItem();
        // CLIENTE
        Cliente cliente = new Cliente();
        cliente.setCodigo(getInt(rs, "CD_CLIENTE"));
        pedidoItem.setCliente(cliente);
        // PEDIDO
        Pedido pedido = new Pedido();
        pedido.setCodigo(getInt(rs, "CD_PEDIDO"));
        pedidoItem.setPedido(pedido);
        //
        pedidoItem.setCodigo(getInt(rs, "NR_SEQ_ITEM"));
        // ITEM
        Item item = new Item();
        item.setCodigo(getInt(rs, "CD_ITEM"));
        pedidoItem.setItem(item);
        //
        pedidoItem.setValorVenda(getDouble(rs, "VL_VENDA"));
        pedidoItem.setValorDesconto(getDouble(rs, "VL_DESCONTO"));
        pedidoItem.setQuantidade(getInt(rs, "NR_QUANTIDADE"));
        // ITEM PAI
        PedidoItem pedidoItemPai = new PedidoItem();
        pedidoItemPai.setCliente(cliente);
        pedidoItemPai.setPedido(pedido);
        pedidoItemPai.setCodigo(getInt(rs, "NR_SEQ_ITEM_PAI"));
        pedidoItem.setPedidoItemPai(pedidoItemPai);
        //
        return pedidoItem;
        
    }

}
