package kalangos.kpizza.business;

import gminet.infra.business.BusinessException;
import gminet.infra.business.BusinessObject;
import gminet.infra.business.RuntimeBusinessException;
import gminet.infra.db.Transaction;
import gminet.infra.db.TransactionException;
//import gminet.infra.toolkit.DateToolkit;

import java.sql.Connection;

import kalangos.kpizza.dao.LojaVirtualDAO;
import kalangos.kpizza.dto.LojaVirtual;


/**
 * @author Alexandre Procaci da Silva
 * 
 * 
 */
public class LojaVirtualBusiness extends BusinessObject {

    public static LojaVirtual findByPK(int codLojaVirtual) throws TransactionException {

        LojaVirtual loja_virtual = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            loja_virtual = LojaVirtualDAO.findByPK(codLojaVirtual, conn);
            if (loja_virtual == null) {
                throw new RuntimeBusinessException("Loja Virtual não encontrada.");
            }
        } finally {
            transaction.release();
        }
        return loja_virtual;
    }

    public static LojaVirtual insert(LojaVirtual loja_virtual) throws BusinessException, TransactionException {

        LojaVirtual result = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            // INCLUI REGISTRO
            loja_virtual.setCodigo(LojaVirtualDAO.getNextCodigo(conn));
            //loja_virtual.setNome(nome) DataCadastro(DateToolkit.getDate());
            LojaVirtualDAO.insert(loja_virtual, conn);
            // GRAVAR LOG DE ACESSO
            // insertStatusLog(loja_virtual, conn);
            //
            result = LojaVirtualDAO.findByPK(loja_virtual.getCodigo(), conn);
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

    public static void update(LojaVirtual loja_virtual) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            LojaVirtualDAO.update(loja_virtual, conn);
            // COMMIT
            transaction.commit();
        } catch (TransactionException te) {
            transaction.rollback();
            throw te;
        } finally {
            transaction.release();
        }
    }

    public static void delete(LojaVirtual loja_virtual) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            LojaVirtualDAO.delete(loja_virtual, conn);
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
