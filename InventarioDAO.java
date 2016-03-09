package kalangos.kpizza.dao;

import gminet.infra.dao.DataAccessObject;
import gminet.infra.db.TransactionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kalangos.kpizza.dto.Inventario;

public class InventarioDAO extends DataAccessObject {

    public static Inventario findByPK(int codigoItem, int lojaCodigo, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_ITEM_INVENTARIO  CD_ITEM_INVENTARIO  ");
        sbSql.append("     , A.CD_LOJA_FISICA     CD_LOJA_FISICA     ");
        sbSql.append("     , A.NR_QUANTIDADE    NR_QUANTIDADE    ");
        sbSql.append("  FROM INVENTARIO       A ");
        sbSql.append(" WHERE CD_ITEM_INVENTARIO = ? ");
        sbSql.append("   AND A.CD_LOJA_FISICA = ? ");

        Inventario inventario = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++,codigoItem);
            setInt(ps, pos++, lojaCodigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                inventario = fromResultSet(rs);
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("InventarioDAO.findByPK (" + sqle.getMessage() + ")");
        }
        return inventario;
    }

    public static List<Inventario> findAll(Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_ITEM_INVENTARIO  CD_ITEM_INVENTARIO  ");
        sbSql.append("     , A.CD_LOJA_FISICA     CD_LOJA_FISICA     ");
        sbSql.append("     , A.NR_QUANTIDADE    NR_QUANTIDADE    ");
        sbSql.append("  FROM INVENTARIO       A ");
        List<Inventario> inventariosList = new ArrayList<Inventario>(100);
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inventario inventario = fromResultSet(rs);
                inventariosList.add(inventario);
            }
            close(rs);
        } catch (SQLException sqle) {
            throw new TransactionException("InventarioDAO.findAll (" + sqle.getMessage() + ")");
        }
        return inventariosList;
    }

    public static void insert(Inventario inventario, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("INSERT INTO INVENTARIO ");
        sbSql.append("     ( CD_ITEM_INVENTARIO  ");
        sbSql.append("     , CD_LOJA_FISICA     ");
        sbSql.append("     , NR_QUANTIDADE    ");
        sbSql.append(") VALUES (?,?,?)");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, inventario.getItem().getCodigo());
            setInt(ps, pos++, inventario.getLoja().getCodigo());
            setInt(ps, pos++, inventario.getQuantidade());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("InventarioDAO.insert (" + sqle.getMessage() + ")");
        }
    }

    public static void update(Inventario inventario, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("UPDATE INVENTARIO         ");
        sbSql.append("   SET NR_QUANTIDADE   = ? ");
        sbSql.append(" WHERE CD_ITEM_INVENTARIO = ? ");
        sbSql.append("   AND CD_LOJA_FISICA    = ? ");

        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, inventario.getQuantidade());
            setInt(ps, pos++, inventario.getItem().getCodigo());
            setInt(ps, pos++, inventario.getLoja().getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("InventarioDAO.update (" + sqle.getMessage() + ")");
        }
    }

    public static void delete(Inventario inventario, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("DELETE FROM INVENTARIO  ");
        sbSql.append(" WHERE CD_ITEM_INVENTARIO = ? ");
        sbSql.append("   AND CD_LOJA_FISICA    = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, inventario.getItem().getCodigo());
            setInt(ps, pos++, inventario.getLoja().getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("InventarioDAO.delete (" + sqle.getMessage() + ")");
        }
    }

    private static Inventario fromResultSet(ResultSet rs) throws SQLException {

        // INVENTARIO
        Inventario inventario = new Inventario();
        inventario.setCodigo(getInt(rs, "CD_ITEM_INVENTARIO")); //duvida
        inventario.setCodigo(getInt(rs, "CD_LOJA_FISICA"));     //duvida usei como exemplo cliente acesso
        inventario.setQuantidade(getInt(rs, "NR_QUANTIDADE"));
        //
        return inventario;
    }

}
