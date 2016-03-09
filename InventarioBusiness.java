package kalangos.kpizza.business;

import gminet.infra.business.BusinessException;
import gminet.infra.business.BusinessObject;
import gminet.infra.business.RuntimeBusinessException;
import gminet.infra.db.Transaction;
import gminet.infra.db.TransactionException;
//import gminet.infra.toolkit.DateToolkit;

import java.sql.Connection;

import kalangos.kpizza.dao.InventarioDAO;
import kalangos.kpizza.dto.Inventario;


/**
 * @author Alexandre Procaci da Silva
 * 
 * 
 */
public class InventarioBusiness extends BusinessObject {

    public static Inventario findByPK(int codInventario,int  codLoja) throws TransactionException {

        Inventario invnetario = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            invnetario = InventarioDAO.findByPK(codInventario,codLoja, conn);
            if (invnetario == null) {
                throw new RuntimeBusinessException("Loja Fisica não encontrada.");
            }
        } finally {
            transaction.release();
        }
        return invnetario;
    }

    public static Inventario insert(Inventario invnetario) throws BusinessException, TransactionException {

        Inventario result = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            // VERIFICA SE USUARIO JA FEZ REGISTRO
            // String emailInventario = invnetario.getEmail();
            // INCLUI REGISTRO
            //invnetario.setCodigo(InventarioDAO.getNextCodigo(conn));
            //invnetario.setNome(nome) DataCadastro(DateToolkit.getDate());
            //invnetario.setSituacao(InventarioSituacao.ATIVO);
            // Buscar loja virtual
            // invnetario.setLojaVirtual();
            InventarioDAO.insert(invnetario, conn);
            // GRAVAR LOG DE ACESSO
            // insertStatusLog(invnetario, conn);
            //
            result = InventarioDAO.findByPK(invnetario.getItem().getCodigo() , invnetario.getLoja().getCodigo() , conn);
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

    public static void update(Inventario invnetario) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            InventarioDAO.update(invnetario, conn);
            // COMMIT
            transaction.commit();
        } catch (TransactionException te) {
            transaction.rollback();
            throw te;
        } finally {
            transaction.release();
        }
    }

    public static void delete(Inventario invnetario) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            InventarioDAO.delete(invnetario, conn);
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
