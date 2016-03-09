package kalangos.kpizza.dao;

import gminet.infra.dao.DataAccessObject;
import gminet.infra.db.TransactionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kalangos.kpizza.dto.ItemInventario;
import kalangos.kpizza.dto.UnidadeMedida;

public class ItemInventarioDAO extends DataAccessObject {

    public static ItemInventario findByPK(int item_inventarioCodigo, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_ITEM_INVENTARIO       CD_ITEM_INVENTARIO      ");
        sbSql.append("     , A.TX_NOME  TX_ITEM_INVENTARIO         ");
        sbSql.append("     , A.CD_UNIDADE_MEDIDA CD_UNIDADE_MEDIDA ");
        sbSql.append("  FROM ITEM_INVENTARIO        A ");
        sbSql.append(" WHERE A.CD_ITEM_INVENTARIO = ? ");

        ItemInventario item_inventario = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, item_inventarioCodigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                item_inventario = fromResultSet(rs);
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ItemInventarioDAO.findByPK (" + sqle.getMessage() + ")");
        }
        return item_inventario;
    }

    public static List<ItemInventario> findAll(Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_ITEM_INVENTARIO       CD_ITEM_INVENTARIO      ");
        sbSql.append("     , A.TX_NOME  TX_ITEM_INVENTARIO         ");
        sbSql.append("     , A.CD_UNIDADE_MEDIDA CD_UNIDADE_MEDIDA ");
        sbSql.append("  FROM ITEM_INVENTARIO        A ");
        sbSql.append(" ORDER BY A.TX_NOME ");
        List<ItemInventario> item_inventariosList = new ArrayList<ItemInventario>(100);
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ItemInventario item_inventario = fromResultSet(rs);
                item_inventariosList.add(item_inventario);
            }
            close(rs);
        } catch (SQLException sqle) {
            throw new TransactionException("ItemInventarioDAO.findAll (" + sqle.getMessage() + ")");
        }
        return item_inventariosList;
    }

    public static void insert(ItemInventario item_inventario, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("INSERT INTO ITEM_INVENTARIO    ");
        sbSql.append("     ( CD_ITEM_INVENTARIO      ");
        sbSql.append("     , TX_NOME ");
        sbSql.append("     , CD_UNIDADE_MEDIDA ");
        sbSql.append(") VALUES (?,?,?)");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, item_inventario.getCodigo());
            setString(ps, pos++, item_inventario.getNome());
            setInt(ps, pos++, item_inventario.getUnidade().getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ItemInventarioDAO.insert (" + sqle.getMessage() + ")");
        }
    }

    public static void update(ItemInventario item_inventario, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("UPDATE ITEM_INVENTARIO           ");
        sbSql.append("   SET TX_NOME  = ? ");
        sbSql.append("     , CD_UNIDADE_MEDIDA = ? ");
        sbSql.append(" WHERE CD_ITEM_INVENTARIO    = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setString(ps, pos++, item_inventario.getNome());
            setInt(ps, pos++, item_inventario.getUnidade().getCodigo());
            setInt(ps, pos++, item_inventario.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ItemInventarioDAO.update (" + sqle.getMessage() + ")");
        }
    }

    public static void delete(ItemInventario item_inventario, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("DELETE FROM ITEM_INVENTARIO  ");
        sbSql.append(" WHERE CD_ITEM_INVENTARIO = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, item_inventario.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ItemInventarioDAO.delete (" + sqle.getMessage() + ")");
        }
    }

    public static int getNextCodigo(Connection conn) throws TransactionException {

        try {
            StringBuffer sbSql = new StringBuffer();
            sbSql.append("SELECT MAX(CD_ITEM_INVENTARIO) AS CD_MAX ");
            sbSql.append("  FROM ITEM_INVENTARIO ");

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

    private static ItemInventario fromResultSet(ResultSet rs) throws SQLException {

        // ITEM_INVENTARIO
        ItemInventario item_inventario = new ItemInventario();
        item_inventario.setCodigo(getInt(rs, "CD_ITEM_INVENTARIO"));
        item_inventario.setNome(getString(rs, "TX_NOME"));
        // LOJA VIRTUAL
        UnidadeMedida unidademedida = new UnidadeMedida();
        unidademedida.setCodigo(getInt(rs, "CD_UNIDADE_MEDIDA"));

        //
        return item_inventario;
    }

}
