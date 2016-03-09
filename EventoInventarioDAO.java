package kalangos.kpizza.dao;

import gminet.infra.dao.DataAccessObject;
import gminet.infra.db.TransactionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import kalangos.kpizza.dto.EventoInventario;
import kalangos.kpizza.dto.Inventario;
import kalangos.kpizza.dto.ItemInventario;
import kalangos.kpizza.dto.LojaFisica;
import kalangos.kpizza.dto.TipoEventoInventario;

public class EventoInventarioDAO extends DataAccessObject {

    public static EventoInventario findByPK(int itemCodigo, int lojaCodigo, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_ITEM_INVENTARIO     CD_ITEM_INVENTARIO    ");
        sbSql.append("     , A.CD_LOJA_FISICA      CD_LOJA_FISICA     ");
        sbSql.append("     , A.NR_SEQ_EVENTO     NR_SEQ_EVENTO    ");
        sbSql.append("     , A.NR_QUANTIDADE   NR_QUANTIDADE  ");
        sbSql.append("     , A.VL_VALOR    VL_VALOR   ");
        sbSql.append("     , A.DT_EVENTO    DT_EVENTO   ");
        sbSql.append("     , A.CD_TIPO    CD_TIPO   ");
        sbSql.append("  FROM EVENTO_INVNETARIO         A ");
        sbSql.append(" WHERE A.CD_ITEM_INVENTARIO = ? ");
        sbSql.append("   AND A.CD_LOJA_FISICA  = ? ");

        EventoInventario evento = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, itemCodigo);
            setInt(ps, pos++, lojaCodigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                evento = fromResultSet(rs);
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("EventoInventarioDAO.findByPK (" + sqle.getMessage() + ")");
        }
        return evento;
    }

    public static List<EventoInventario> findAll(int lojaCodigo, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_ITEM_INVENTARIO     CD_ITEM_INVENTARIO    ");
        sbSql.append("     , A.CD_LOJA_FISICA      CD_LOJA_FISICA     ");
        sbSql.append("     , A.NR_SEQ_EVENTO     NR_SEQ_EVENTO    ");
        sbSql.append("     , A.NR_QUANTIDADE   NR_QUANTIDADE  ");
        sbSql.append("     , A.VL_VALOR    VL_VALOR   ");
        sbSql.append("     , A.DT_EVENTO      DT_EVENTO     ");
        sbSql.append("     , A.CD_TIPO   CD_TIPO  ");
        sbSql.append("  FROM EVENTO_INVENTARIO         A ");
        sbSql.append(" WHERE A.CD_LOJA_FISICA = ? ");
        sbSql.append(" ORDER BY A.CD_LOJA_FISICA ");
        List<EventoInventario> eventosList = new ArrayList<EventoInventario>(100);
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, lojaCodigo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EventoInventario evento = fromResultSet(rs);
                eventosList.add(evento);
            }
            close(rs);
        } catch (SQLException sqle) {
            throw new TransactionException("EventoInventarioDAO.findAll (" + sqle.getMessage() + ")");
        }
        return eventosList;
    }

    public static void insert(EventoInventario evento, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("INSERT INTO EVENTO_INVENTARIO   ");
        sbSql.append("     ( CD_ITEM_INVENTARIO    ");
        sbSql.append("     , CD_LOJA_FISICA     ");
        sbSql.append("     , NR_SEQ_EVENTO    ");
        sbSql.append("     , NR_QUANTIDADE  ");
        sbSql.append("     , VL_VALOR     ");
        sbSql.append("     , DT_EVENTO     ");
        sbSql.append("     , CD_TIPO  ");
        sbSql.append(") VALUES (?,?,?,?,?,?,?)");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, evento.getInventario().getItem().getCodigo());
            setInt(ps, pos++, evento.getInventario().getLoja().getCodigo());
            setInt(ps, pos++, evento.getCodigo());
            setInt(ps, pos++, evento.getQuantidade());
            setDouble(ps, pos++, evento.getValor());
            setDate(ps, pos++, evento.getDataEvento());
            setInt(ps, pos++, evento.getTipo().getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("EventoInventarioDAO.insert (" + sqle.getMessage() + ")");
        }
    }

    public static void update(EventoInventario evento, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("UPDATE EVENTO_INVENTARIO            ");
        sbSql.append("   SET NR_QUANTIDADE  = ? ");
        sbSql.append("     , VL_VALOR   = ?  ");
        sbSql.append("     , DT_EVENTO     = ? ");
        sbSql.append("     , CD_TIPO  = ? ");
        sbSql.append(" WHERE CD_ITEM_INVENTARIO    = ? ");
        sbSql.append("   AND CD_LOJA_FISICA     = ? ");
        sbSql.append("   AND NR_SEQ_EVENTO      = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, evento.getQuantidade());
            setDouble(ps, pos++, evento.getValor());
            setDate(ps, pos++, evento.getDataEvento());
            setInt(ps, pos++, evento.getTipo().getCodigo());
            setInt(ps, pos++, evento.getInventario().getItem().getCodigo());
            setInt(ps, pos++, evento.getInventario().getLoja().getCodigo());
            setInt(ps, pos++, evento.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("EventoInventarioDAO.update (" + sqle.getMessage() + ")");
        }
    }

    public static void delete(EventoInventario evento, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("DELETE FROM EVENTO_INVENTARIO    ");
        sbSql.append(" WHERE CD_ITEM_INVENTARIO = ? ");
        sbSql.append("   AND CD_LOJA_FISICA  = ? ");
        sbSql.append("   AND NR_SEQ_EVENTO      = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, evento.getInventario().getItem().getCodigo());
            setInt(ps, pos++, evento.getInventario().getLoja().getCodigo());
            setInt(ps, pos++, evento.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("EventoInventarioDAO.delete (" + sqle.getMessage() + ")");
        }
    }

    public static int getNextCodigo(int itemCodigo, int lojaCodigo, Connection conn) throws TransactionException {

        try {
            StringBuffer sbSql = new StringBuffer();
            sbSql.append("SELECT MAX(NR_SEQ_EVENTO) AS CD_MAX ");
            sbSql.append("  FROM EVENTO_INVENTARIO ");
            sbSql.append(" WHERE CD_ITEM_INVENTARIO = ? ");
            sbSql.append("   AND CD_LOJA_FISICA  = ? ");

            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, itemCodigo);
            setInt(ps, pos++, lojaCodigo);
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

    private static EventoInventario fromResultSet(ResultSet rs) throws SQLException {

        // EVENTO_INVENTARIO
    	   EventoInventario evento = new EventoInventario();
        // ITEM
        ItemInventario item = new ItemInventario();
        item.setCodigo(getInt(rs, "CD_ITEM_INVENTARIO"));
        
     // LOJA
        LojaFisica loja = new LojaFisica();
        loja.setCodigo(getInt(rs, "CD_LOJA_FISICA"));
        
        //INVENTARIO
        Inventario inventario = new Inventario();
        inventario.setItem(item);
        inventario.setLoja(loja);
        
        evento.setInventario(inventario);
        
     
        evento.setCodigo(getInt(rs, "NR_SEQ_EVENTO"));
        evento.setQuantidade(getInt(rs, "NR_QUANTIDADE"));
        evento.setValor(getDouble(rs, "VL_VALOR"));
        evento.setDataEvento(getDate(rs, "DT_EVENTO"));
        
      //Tipo evento
        
        TipoEventoInventario tipo = new TipoEventoInventario();
        tipo.setCodigo(getInt(rs, "CD_TIPO"));
        evento.setTipo(tipo);
        
  
        return evento;
    }

}
