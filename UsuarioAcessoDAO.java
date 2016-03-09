package kalangos.kpizza.dao;

import gminet.infra.dao.DataAccessObject;
import gminet.infra.db.TransactionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kalangos.kpizza.dto.Filial;
import kalangos.kpizza.dto.LojaVirtual;
import kalangos.kpizza.dto.UsuarioAcesso;
import kalangos.kpizza.dto.UsuarioSituacao;
import kalangos.kpizza.dto.UsuarioTipo;

public class UsuarioAcessoDAO extends DataAccessObject {

    public static UsuarioAcesso findByPK(int usuarioCodigo, int lojaCodigo, int filialCodigo, Connection conn)
            throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_USUARIO      CD_USUARIO      ");
        sbSql.append("     , A.CD_LOJA_VIRTUAL CD_LOJA_VIRTUAL ");
        sbSql.append("     , A.CD_FILIAL       CD_FILIAL       ");
        sbSql.append("     , A.CD_SITUACAO     CD_SITUACAO     ");
        sbSql.append("     , A.CD_TIPO         CD_TIPO         ");
        sbSql.append("     , A.TS_CADASTRO     TS_CADASTRO     ");
        sbSql.append("  FROM USUARIO_ACESSO A ");
        sbSql.append(" WHERE A.CD_USUARIO      = ? ");
        sbSql.append("   AND A.CD_LOJA_VIRTUAL = ? ");
        sbSql.append("   AND A.CD_FILIAL       = ? ");

        UsuarioAcesso usuarioAcesso = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, usuarioCodigo);
            setInt(ps, pos++, lojaCodigo);
            setInt(ps, pos++, filialCodigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuarioAcesso = fromResultSet(rs);
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("UsuarioAcessoDAO.findByPK (" + sqle.getMessage() + ")");
        }
        return usuarioAcesso;
    }

    public static List<UsuarioAcesso> findAll(int usuarioCodigo, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_USUARIO      CD_USUARIO      ");
        sbSql.append("     , A.CD_LOJA_VIRTUAL CD_LOJA_VIRTUAL ");
        sbSql.append("     , A.CD_FILIAL       CD_FILIAL       ");
        sbSql.append("     , A.CD_SITUACAO     CD_SITUACAO     ");
        sbSql.append("     , A.CD_TIPO         CD_TIPO         ");
        sbSql.append("     , A.TS_CADASTRO     TS_CADASTRO     ");
        sbSql.append("  FROM USUARIO_ACESSO A ");
        sbSql.append(" WHERE A.CD_USUARIO = ? ");
        sbSql.append(" ORDER BY A.TX_NOME ");
        List<UsuarioAcesso> usuarioAcessosList = new ArrayList<UsuarioAcesso>(100);
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, usuarioCodigo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UsuarioAcesso usuarioAcesso = fromResultSet(rs);
                usuarioAcessosList.add(usuarioAcesso);
            }
            close(rs);
        } catch (SQLException sqle) {
            throw new TransactionException("UsuarioAcessoDAO.findAll (" + sqle.getMessage() + ")");
        }
        return usuarioAcessosList;
    }

    public static void insert(UsuarioAcesso usuarioAcesso, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("INSERT INTO USUARIO_ACESSO ");
        sbSql.append("     ( CD_USUARIO          ");
        sbSql.append("     , CD_LOJA_VIRTUAL     ");
        sbSql.append("     , CD_FILIAL           ");
        sbSql.append("     , ID_SITUACAO         ");
        sbSql.append("     , CD_TIPO             ");
        sbSql.append("     , TS_CADASTRO         ");
        sbSql.append(") VALUES (?,?,?,?,?,?)");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, usuarioAcesso.getUsuario().getCodigo());
            setInt(ps, pos++, usuarioAcesso.getLojaVirtual().getCodigo());
            setInt(ps, pos++, usuarioAcesso.getFilial().getCodigo());
            setInt(ps, pos++, usuarioAcesso.getSituacao().getCodigo());
            setInt(ps, pos++, usuarioAcesso.getTipo().getCodigo());
            setTimestamp(ps, pos++, usuarioAcesso.getDataCadastro());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("UsuarioAcessoDAO.insert (" + sqle.getMessage() + ")");
        }
    }

    public static void update(UsuarioAcesso usuarioAcesso, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("UPDATE USUARIO_ACESSO      ");
        sbSql.append("   SET ID_SITUACAO     = ? ");
        sbSql.append("     , CD_TIPO         = ? ");
        sbSql.append(" WHERE CD_USUARIO      = ? ");
        sbSql.append("   AND CD_LOJA_VIRTUAL = ? ");
        sbSql.append("   AND CD_FILIAL       = ? ");

        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, usuarioAcesso.getSituacao().getCodigo());
            setInt(ps, pos++, usuarioAcesso.getTipo().getCodigo());
            setInt(ps, pos++, usuarioAcesso.getUsuario().getCodigo());
            setInt(ps, pos++, usuarioAcesso.getLojaVirtual().getCodigo());
            setInt(ps, pos++, usuarioAcesso.getFilial().getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("UsuarioAcessoDAO.update (" + sqle.getMessage() + ")");
        }
    }

    public static void delete(UsuarioAcesso usuarioAcesso, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("DELETE FROM USUARIO_ACESSO ");
        sbSql.append(" WHERE CD_USUARIO      = ? ");
        sbSql.append("   AND CD_LOJA_VIRTUAL = ? ");
        sbSql.append("   AND CD_FILIAL       = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, usuarioAcesso.getUsuario().getCodigo());
            setInt(ps, pos++, usuarioAcesso.getLojaVirtual().getCodigo());
            setInt(ps, pos++, usuarioAcesso.getFilial().getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("UsuarioAcessoDAO.delete (" + sqle.getMessage() + ")");
        }
    }

    private static UsuarioAcesso fromResultSet(ResultSet rs) throws SQLException {

        // USUARIO
        UsuarioAcesso usuarioAcesso = new UsuarioAcesso();
        usuarioAcesso.setCodigo(getInt(rs, "CD_USUARIO"));
        // LOJA
        LojaVirtual loja = new LojaVirtual();
        loja.setCodigo(getInt(rs, "CD_LOJA_VIRTUAL"));
        usuarioAcesso.setLojaVirtual(loja);
        // FILIAL
        Filial filial = new Filial();
        filial.setCodigo(getInt(rs, "CD_FILIAL"));
        usuarioAcesso.setFilial(filial);
        //
        usuarioAcesso.setSituacao(UsuarioSituacao.getById(getByte(rs, "CD_SITUACAO")));
        usuarioAcesso.setTipo(UsuarioTipo.getById(getByte(rs, "CD_TIPO")));
        usuarioAcesso.setDataCadastro(getDateFromTimestamp(rs, "TS_CADASTRO"));
        //
        return usuarioAcesso;
    }

}
