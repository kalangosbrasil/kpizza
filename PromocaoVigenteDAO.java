package kalangos.kpizza.dao;

import gminet.infra.dao.DataAccessObject;
import gminet.infra.db.TransactionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kalangos.kpizza.dto.PromocaoVigente;
import kalangos.kpizza.dto.Item;

public class PromocaoVigenteDAO extends DataAccessObject {
	
	public static PromocaoVigente findByPK(int promocaoVigenteCodigo, int itemCodigo, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_PROMOCAO_VIGENTE              CD_PROMOCAO_VIGENTE            ");
        sbSql.append("     , A.CD_LOJA_VIRTUAL      CD_LOJA_VIRTUAL    ");
        sbSql.append("     , A.TX_NOME              TX_NOME            ");
        sbSql.append("     , A.TX_TAMANHO           TX_TAMANHO         ");
        sbSql.append("     , A.VL_VENDA_ITEM        VL_VENDA_ITEM      ");
        sbSql.append("     , A.CD_TIPO              CD_TIPO            ");
        sbSql.append("     , A.VL_CUSTO             VL_CUSTO           ");
        sbSql.append("     , A.NR_QUANTIDADE        NR_QUANTIDADE      ");
        sbSql.append("     , A.CD_ITEM_INVENTARIO   CD_ITEM_INVENTARIO ");
        sbSql.append("  FROM PROMOCAO_VIGENTE        A ");
        sbSql.append(" WHERE A.CD_PROMOCAO_VIGENTE = ? ");

        PromocaoVigente promocaoVigente = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, promocaoVigenteCodigo);
            setInt(ps, pos++, itemCodigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                promocaoVigente = fromResultSet(rs);
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("PromocaoVigenteDAO.findByPK (" + sqle.getMessage() + ")");
        }
        return promocaoVigente;
    }

    public static List<PromocaoVigente> findAll(Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_PROMOCAO_VIGENTE              CD_PROMOCAO_VIGENTE            ");
        sbSql.append("     , A.CD_LOJA_VIRTUAL      CD_LOJA_VIRTUAL    ");
        sbSql.append("     , A.TX_NOME              TX_NOME            ");
        sbSql.append("     , A.TX_TAMANHO           TX_TAMANHO         ");
        sbSql.append("     , A.VL_VENDA_PROMOCAO_VIGENTE        VL_VENDA_ITEM      ");
        sbSql.append("     , A.CD_TIPO              CD_TIPO            ");
        sbSql.append("     , A.VL_CUSTO             VL_CUSTO           ");
        sbSql.append("     , A.NR_QUANTIDADE        NR_QUANTIDADE      ");
        sbSql.append("     , A.CD_ITEM_INVENTARIO   CD_ITEM_INVENTARIO ");
        sbSql.append("  FROM PROMOCAO_VIGENTE    A ");
        sbSql.append(" ORDER BY A.TX_NOME ");
        List<PromocaoVigente> promocaoVigentesList = new ArrayList<PromocaoVigente>(100);
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PromocaoVigente promocaoVigente = fromResultSet(rs);
                promocaoVigentesList.add(promocaoVigente);
            }
            close(rs);
        } catch (SQLException sqle) {
            throw new TransactionException("PromocaoVigenteDAO.findAll (" + sqle.getMessage() + ")");
        }
        return promocaoVigentesList;
    }

    public static void insert(PromocaoVigente promocaoVigente, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("INSERT INTO PROMOCAO_VIGENTE            ");
        sbSql.append("     ( CD_PROMOCAO_VIGENTE              ");
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
            setInt(ps, pos++, promocaoVigente.getCodigo());
            setInt(ps, pos++, promocaoVigente.getLojaVirtual().getCodigo());
            setString(ps, pos++, promocaoVigente.getNome());
            setString(ps, pos++, promocaoVigente.getTamanho());
            setDouble(ps, pos++, promocaoVigente.getValorVenda());
            setInt(ps, pos++, promocaoVigente.getTipo().getCodigo());
            setDouble(ps, pos++, promocaoVigente.getValorCusto());
            setInt(ps, pos++, promocaoVigente.getQuantidade());
            setInt(ps, pos++, promocaoVigente.getItem().getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("PromocaoVigenteDAO.insert (" + sqle.getMessage() + ")");
        }
    }

    public static void update(PromocaoVigente promocaoVigente, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("UPDATE PROMOCAO_VIGENTE           ");
        sbSql.append("   SET TX_NOME           = ?    ");
        sbSql.append("     , TX_TAMANHO        = ?    ");
        sbSql.append("     , VL_VENDA_ITEM     = ?    ");
        sbSql.append("     , VL_CUSTO          = ?    ");
        sbSql.append("     , NR_QUANTIDADE     = ?    ");
        sbSql.append(" WHERE CD_PROMOCAO_VIGENTE    = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setString(ps, pos++, promocaoVigente.getNome());
            setString(ps, pos++, promocaoVigente.getTamanho());
            setDouble(ps, pos++, promocaoVigente.getValorVenda());
            setDouble(ps, pos++, promocaoVigente.getValorCusto());
            setInt(ps, pos++, promocaoVigente.getQuantidade());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("PromocaoVigenteDAO.update (" + sqle.getMessage() + ")");
        }
    }

    public static void delete(PromocaoVigente promocaoVigente, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("DELETE FROM PROMOCAO_VIGENTE  ");
        sbSql.append(" WHERE CD_PROMOCAO_VIGENTE = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, promocaoVigente.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("PromocaoVigenteDAO.delete (" + sqle.getMessage() + ")");
        }
    }

    public static int getNextCodigo(Connection conn) throws TransactionException {

        try {
            StringBuffer sbSql = new StringBuffer();
            sbSql.append("SELECT MAX(CD_PROMOCAO_VIGENTE) AS CD_MAX ");
            sbSql.append("  FROM PROMOCAO_VIGENTE ");

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

    private static PromocaoVigente fromResultSet(ResultSet rs) throws SQLException {

    	// PROMOCAO VIGENTE
    	PromocaoVigente promocaoVigente = new PromocaoVigente();
        promocaoVigente.setCodigo(getInt(rs,"CD_PROMOCAO"));
        promocaoVigente.setNome(getString(rs,"TX_NOME"));
        promocaoVigente.setTamanho(getString(rs,"TX_TAMANHO"));
        promocaoVigente.setValorVenda(getDouble(rs,"VL_VENDA_ITEM"));
        promocaoVigente.setValorCusto(getDouble(rs,"VL_CUSTO"));
        promocaoVigente.setQuantidade(getInt(rs,"NR_QUANTIDADE"));

    //    item.setSituacao(ItemSituacao.getById(getByte(rs, "ID_SITUACAO")));
        // ITEM
        Item item = new Item();
        item.setCodigo(getInt(rs, "CD_ITEM"));
        item.setItem(item);
        //
        
        //TIMESTAMP de GRAVAÇÃO
        //item.setDataCadastro(getDateFromTimestamp(rs, "TS_CADASTRO"));
        //
        return promocaoVigente;
    }


}
