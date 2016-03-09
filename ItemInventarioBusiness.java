package kalangos.kpizza.business;

import gminet.infra.business.BusinessException;
import gminet.infra.business.BusinessObject;
import gminet.infra.business.RuntimeBusinessException;
import gminet.infra.db.Transaction;
import gminet.infra.db.TransactionException;
//import gminet.infra.toolkit.DateToolkit;

import java.sql.Connection;

import kalangos.kpizza.dao.ItemInventarioDAO;
import kalangos.kpizza.dto.ItemInventario;


/**
 * @author Alexandre Procaci da Silva
 * 
 * 
 */
public class ItemInventarioBusiness extends BusinessObject {

    public static ItemInventario findByPK(int codItemInventario) throws TransactionException {

        ItemInventario item_invnetario = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            item_invnetario = ItemInventarioDAO.findByPK(codItemInventario, conn);
            if (item_invnetario == null) {
                throw new RuntimeBusinessException("Loja Fisica não encontrada.");
            }
        } finally {
            transaction.release();
        }
        return item_invnetario;
    }

    public static ItemInventario insert(ItemInventario item_invnetario) throws BusinessException, TransactionException {

        ItemInventario result = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            // VERIFICA SE USUARIO JA FEZ REGISTRO
            // String emailItemInventario = item_invnetario.getEmail();
            // INCLUI REGISTRO
            item_invnetario.setCodigo(ItemInventarioDAO.getNextCodigo(conn));
            //item_invnetario.setNome(nome) DataCadastro(DateToolkit.getDate());
            //item_invnetario.setSituacao(ItemInventarioSituacao.ATIVO);
            // Buscar loja virtual
            // item_invnetario.setLojaVirtual();
            ItemInventarioDAO.insert(item_invnetario, conn);
            // GRAVAR LOG DE ACESSO
            // insertStatusLog(item_invnetario, conn);
            //
            result = ItemInventarioDAO.findByPK(item_invnetario.getCodigo(), conn);
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

    public static void update(ItemInventario item_invnetario) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            ItemInventarioDAO.update(item_invnetario, conn);
            // COMMIT
            transaction.commit();
        } catch (TransactionException te) {
            transaction.rollback();
            throw te;
        } finally {
            transaction.release();
        }
    }

    public static void delete(ItemInventario item_invnetario) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            ItemInventarioDAO.delete(item_invnetario, conn);
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
