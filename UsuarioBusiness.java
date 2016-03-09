package kalangos.kpizza.business;

import gminet.infra.business.BusinessException;
import gminet.infra.business.BusinessObject;
import gminet.infra.business.RuntimeBusinessException;
import gminet.infra.db.Transaction;
import gminet.infra.db.TransactionException;
import gminet.infra.toolkit.DateToolkit;

import java.sql.Connection;

import kalangos.kpizza.dao.UsuarioDAO;
import kalangos.kpizza.dto.Usuario;

/**
 * @author Gabriel Flores Mendes
 * 
 * 
 */
public class UsuarioBusiness extends BusinessObject {

    public static Usuario findByPK(int codUsuario) throws TransactionException {

        Usuario usuario = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            usuario = UsuarioDAO.findByPK(codUsuario, conn);
            if (usuario == null) {
                throw new RuntimeBusinessException("Usuario não encontrado.");
            }
        } finally {
            transaction.release();
        }
        return usuario;
    }

    public static Usuario insert(Usuario usuario) throws BusinessException, TransactionException {

        Usuario result = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            // INCLUI REGISTRO
            usuario.setCodigo(UsuarioDAO.getNextCodigo(conn));
            usuario.setDataCadastro(DateToolkit.getDate());
            UsuarioDAO.insert(usuario, conn);
            //
            result = UsuarioDAO.findByPK(usuario.getCodigo(), conn);
            // COMMIT
            transaction.commit();
            // RETORNA REGISTRO INCLUIDO
            return result;
        } catch (TransactionException te) {
            transaction.rollback();
            throw te;
        } finally {
            transaction.release();
        }
    }

    public static void update(Usuario usuario) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            UsuarioDAO.update(usuario, conn);
            // COMMIT
            transaction.commit();
        } catch (TransactionException te) {
            transaction.rollback();
            throw te;
        } finally {
            transaction.release();
        }
    }

    public static void delete(Usuario usuario) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            UsuarioDAO.delete(usuario, conn);
            // COMMIT
            transaction.commit();
        } catch (TransactionException te) {
            transaction.rollback();
            throw te;
        } finally {
            transaction.release();
        }
    }

}
