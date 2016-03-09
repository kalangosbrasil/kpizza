package kalangos.kpizza.dao;

import gminet.infra.dao.DataAccessObject;
import gminet.infra.db.TransactionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kalangos.kpizza.dto.Item;
import kalangos.kpizza.dto.TipoItem;
import kalangos.kpizza.dto.LojaVirtual;
import kalangos.kpizza.dto.ItemInventario;

public class ItemDAO extends DataAccessObject {
	
	public static Item findByPK(int itemCodigo, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_ITEM              CD_ITEM            ");
        sbSql.append("     , A.CD_LOJA_VIRTUAL      CD_LOJA_VIRTUAL    ");
        sbSql.append("     , A.TX_NOME              TX_NOME            ");
        sbSql.append("     , A.TX_TAMANHO           TX_TAMANHO         ");
        sbSql.append("     , A.VL_VENDA_ITEM        VL_VENDA_ITEM      ");
        sbSql.append("     , A.CD_TIPO              CD_TIPO            ");
        sbSql.append("     , A.VL_CUSTO             VL_CUSTO           ");
        sbSql.append("     , A.NR_QUANTIDADE        NR_QUANTIDADE      ");
        sbSql.append("     , A.CD_ITEM_INVENTARIO   CD_ITEM_INVENTARIO ");
        sbSql.append("  FROM ITEM        A ");
        sbSql.append(" WHERE A.CD_ITEM = ? ");

        Item item = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, itemCodigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                item = fromResultSet(rs);
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ItemDAO.findByPK (" + sqle.getMessage() + ")");
        }
        return item;
    }

    public static List<Item> findAll(Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_ITEM              CD_ITEM            ");
        sbSql.append("     , A.CD_LOJA_VIRTUAL      CD_LOJA_VIRTUAL    ");
        sbSql.append("     , A.TX_NOME              TX_NOME            ");
        sbSql.append("     , A.TX_TAMANHO           TX_TAMANHO         ");
        sbSql.append("     , A.VL_VENDA_ITEM        VL_VENDA_ITEM      ");
        sbSql.append("     , A.CD_TIPO              CD_TIPO            ");
        sbSql.append("     , A.VL_CUSTO             VL_CUSTO           ");
        sbSql.append("     , A.NR_QUANTIDADE        NR_QUANTIDADE      ");
        sbSql.append("     , A.CD_ITEM_INVENTARIO   CD_ITEM_INVENTARIO ");
        sbSql.append("  FROM ITEM    A ");
        sbSql.append(" ORDER BY A.TX_NOME ");
        List<Item> itemsList = new ArrayList<Item>(100);
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Item item = fromResultSet(rs);
                itemsList.add(item);
            }
            close(rs);
        } catch (SQLException sqle) {
            throw new TransactionException("ItemDAO.findAll (" + sqle.getMessage() + ")");
        }
        return itemsList;
    }

    public static void insert(Item item, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("INSERT INTO ITEM            ");
        sbSql.append("     ( CD_ITEM              ");
        sbSql.append("     , CD_LOJA_VIRTUAL      ");
        sbSql.append("     , TX_NOME              ");
        sbSql.append("     , TX_TAMANHO           ");
        sbSql.append("     , VL_VENDA_ITEM        ");
        sbSql.append("     , CD_TIPO              ");
        sbSql.append("     , VL_CUSTO             ");
        sbSql.append("     , NR_QUANTIDADE        ");
        sbSql.append("     , CD_ITEM_INVENTARIO   ");
        sbSql.append(") VALUES (?,?,?,?,?,?,?,?,?)");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, item.getCodigo());
            setInt(ps, pos++, item.getLojaVirtual().getCodigo());
            setString(ps, pos++, item.getNome());
            setString(ps, pos++, item.getTamanho());
            setDouble(ps, pos++, item.getValorVenda());
            setInt(ps, pos++, item.getTipo().getCodigo());
            setDouble(ps, pos++, item.getValorCusto());
            setInt(ps, pos++, item.getQuantidade());
            setInt(ps, pos++, item.getItem().getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ItemDAO.insert (" + sqle.getMessage() + ")");
        }
    }

    public static void update(Item item, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("UPDATE ITEM           ");
        sbSql.append("   SET TX_NOME           = ?    ");
        sbSql.append("     , TX_TAMANHO        = ?    ");
        sbSql.append("     , VL_VENDA_ITEM     = ?    ");
        sbSql.append("     , VL_CUSTO          = ?    ");
        sbSql.append("     , NR_QUANTIDADE     = ?    ");
        sbSql.append(" WHERE CD_ITEM    = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setString(ps, pos++, item.getNome());
            setString(ps, pos++, item.getTamanho());
            setDouble(ps, pos++, item.getValorVenda());
            setDouble(ps, pos++, item.getValorCusto());
            setInt(ps, pos++, item.getQuantidade());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ItemDAO.update (" + sqle.getMessage() + ")");
        }
    }

    public static void delete(Item item, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("DELETE FROM ITEM  ");
        sbSql.append(" WHERE CD_ITEM = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, item.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("ItemDAO.delete (" + sqle.getMessage() + ")");
        }
    }

    public static int getNextCodigo(Connection conn) throws TransactionException {

        try {
            StringBuffer sbSql = new StringBuffer();
            sbSql.append("SELECT MAX(CD_ITEM) AS CD_MAX ");
            sbSql.append("  FROM ITEM ");

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

    private static Item fromResultSet(ResultSet rs) throws SQLException {

    	// ITEM
    	Item item = new Item();
        item.setCodigo(getInt(rs,"CD_ITEM"));
        item.setNome(getString(rs,"TX_NOME"));
        item.setTamanho(getString(rs,"TX_TAMANHO"));
        item.setValorVenda(getDouble(rs,"VL_VENDA_ITEM"));
        item.setValorCusto(getDouble(rs,"VL_CUSTO"));
        item.setQuantidade(getInt(rs,"NR_QUANTIDADE"));

    //    item.setSituacao(ItemSituacao.getById(getByte(rs, "ID_SITUACAO")));
        // LOJA VIRTUAL
        LojaVirtual lojaVirtual = new LojaVirtual();
        lojaVirtual.setCodigo(getInt(rs, "CD_LOJA_VIRTUAL"));
        item.setLojaVirtual(lojaVirtual);
        //
        // TIPO ITEM
        TipoItem tipoItem = new TipoItem();
        tipoItem.setCodigo(getInt(rs, "CD_TIPO"));
        item.setLojaVirtual(lojaVirtual);
        //
     // TIPO ITEM
        ItemInventario itemInventario = new ItemInventario();
        itemInventario.setCodigo(getInt(rs, "CD_ITEM_INVENTARIO"));
        item.setLojaVirtual(lojaVirtual);
        //
        
        //TIMESTAMP de GRAVAÇÃO
        //item.setDataCadastro(getDateFromTimestamp(rs, "TS_CADASTRO"));
        //
        return item;
    }


}
