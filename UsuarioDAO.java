package kalangos.kpizza.dao;

import gminet.infra.dao.DataAccessObject;
import gminet.infra.db.TransactionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kalangos.kpizza.dto.Usuario;

public class UsuarioDAO extends DataAccessObject {

    public static Usuario findByPK(int usuarioCodigo, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_USUARIO      CD_USUARIO  ");
        sbSql.append("     , A.TX_LOGIN        TX_LOGIN    ");
        sbSql.append("     , A.TX_NOME         TX_NOME     ");
        sbSql.append("     , A.TX_SENHA        TX_SENHA    ");
        sbSql.append("     , A.TS_CADASTRO     TS_CADASTRO ");
        sbSql.append("  FROM USUARIO        A ");
        sbSql.append(" WHERE A.CD_USUARIO = ? ");

        Usuario usuario = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, usuarioCodigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = fromResultSet(rs);
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("UsuarioDAO.findByPK (" + sqle.getMessage() + ")");
        }
        return usuario;
    }

    public static Usuario findByLogin(String login, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_USUARIO      CD_USUARIO  ");
        sbSql.append("     , A.TX_LOGIN        TX_LOGIN    ");
        sbSql.append("     , A.TX_NOME         TX_NOME     ");
        sbSql.append("     , A.TX_SENHA        TX_SENHA    ");
        sbSql.append("     , A.TS_CADASTRO     TS_CADASTRO ");
        sbSql.append("  FROM USUARIO      A ");
        sbSql.append(" WHERE A.TX_LOGIN = ? ");

        Usuario usuario = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setString(ps, 1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = fromResultSet(rs);
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("UsuarioDAO.findByLogin (" + sqle.getMessage() + ")");
        }
        return usuario;
    }

    public static boolean existLogin(String login, Connection conn) throws TransactionException {

        return existLogin(login, 0, conn);
    }

    public static boolean existLogin(String login, int codigo, Connection conn) throws TransactionException {

        boolean found = false;
        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT CD_USUARIO   ");
        sbSql.append("  FROM USUARIO      ");
        sbSql.append(" WHERE TX_LOGIN = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setString(ps, 1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int cdUsuario = getInt(rs, "CD_USUARIO");
                found = codigo == 0 || cdUsuario != codigo;
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("UsuarioDAO.existLogin (" + sqle.getMessage() + ")");
        }
        return found;
    }

    public static List<Usuario> findAll(Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_USUARIO      CD_USUARIO  ");
        sbSql.append("     , A.TX_LOGIN        TX_LOGIN    ");
        sbSql.append("     , A.TX_NOME         TX_NOME     ");
        sbSql.append("     , A.TX_SENHA        TX_SENHA    ");
        sbSql.append("     , A.TS_CADASTRO     TS_CADASTRO ");
        sbSql.append("  FROM USUARIO    A ");
        sbSql.append(" ORDER BY A.TX_NOME ");
        List<Usuario> usuariosList = new ArrayList<Usuario>(100);
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = fromResultSet(rs);
                usuariosList.add(usuario);
            }
            close(rs);
        } catch (SQLException sqle) {
            throw new TransactionException("UsuarioDAO.findAll (" + sqle.getMessage() + ")");
        }
        return usuariosList;
    }

    public static String findSenhaAcesso(int usuarioCodigo, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("SELECT A.CD_USUARIO     CD_USUARIO  ");
        sbSql.append("     , A.TX_SENHA       TX_SENHA   ");
        sbSql.append("  FROM USUARIO        A ");
        sbSql.append(" WHERE A.CD_USUARIO = ? ");

        String senhaAcesso = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, usuarioCodigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                senhaAcesso = getString(rs, "TX_SENHA");
            }
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("UsuarioDAO.findSenhaAcesso (" + sqle.getMessage() + ")");
        }
        return senhaAcesso;
    }

    public static void insert(Usuario usuario, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("INSERT INTO USUARIO    ");
        sbSql.append("     ( CD_USUARIO      ");
        sbSql.append("     , TX_LOGIN         ");
        sbSql.append("     , TX_NOME         ");
        sbSql.append("     , TX_SENHA        ");
        sbSql.append("     , TS_CADASTRO     ");
        sbSql.append(") VALUES (?,?,?,?,?)   ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setInt(ps, pos++, usuario.getCodigo());
            setString(ps, pos++, usuario.getLogin());
            setString(ps, pos++, usuario.getNome());
            setString(ps, pos++, usuario.getSenhaAcesso());
            setTimestamp(ps, pos++, usuario.getDataCadastro());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("UsuarioDAO.insert (" + sqle.getMessage() + ")");
        }
    }

    public static void update(Usuario usuario, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("UPDATE USUARIO           ");
        sbSql.append("   SET TX_LOGIN      = ? ");
        sbSql.append("     , TX_NOME       = ? ");
        sbSql.append("     , TX_SENHA      = ? ");
        sbSql.append(" WHERE CD_USUARIO    = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setString(ps, pos++, usuario.getLogin());
            setString(ps, pos++, usuario.getNome());
            setString(ps, pos++, usuario.getSenhaAcesso());
            setInt(ps, pos++, usuario.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("UsuarioDAO.update (" + sqle.getMessage() + ")");
        }
    }

    public static void updateSenha(Usuario usuario, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("UPDATE USUARIO        ");
        sbSql.append("   SET TX_SENHA   = ? ");
        sbSql.append(" WHERE CD_USUARIO = ? ");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            int pos = 1;
            setString(ps, pos++, usuario.getSenhaAcesso());
            setInt(ps, pos++, usuario.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("UsuarioDAO.updatePassword (" + sqle.getMessage() + ")");
        }
    }

    public static void delete(Usuario usuario, Connection conn) throws TransactionException {

        StringBuffer sbSql = new StringBuffer();
        sbSql.append("DELETE FROM USUARIO  ");
        sbSql.append(" WHERE CD_USUARIO = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sbSql.toString());
            setInt(ps, 1, usuario.getCodigo());
            ps.executeUpdate();
            close(ps);
        } catch (SQLException sqle) {
            throw new TransactionException("UsuarioDAO.delete (" + sqle.getMessage() + ")");
        }
    }

    public static int getNextCodigo(Connection conn) throws TransactionException {

        try {
            StringBuffer sbSql = new StringBuffer();
            sbSql.append("SELECT MAX(CD_USUARIO) AS CD_MAX ");
            sbSql.append("  FROM USUARIO        ");

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

    private static Usuario fromResultSet(ResultSet rs) throws SQLException {

        // USUARIO
        Usuario usuario = new Usuario();
        usuario.setCodigo(getInt(rs, "CD_USUARIO"));
        usuario.setLogin(getString(rs, "TX_LOGIN"));
        usuario.setNome(getString(rs, "TX_NOME"));
        usuario.setSenhaAcesso(getString(rs, "TX_SENHA"));
        usuario.setDataCadastro(getDateFromTimestamp(rs, "TS_CADASTRO"));
        //
        return usuario;
    }

}
