package kalangos.kpizza.business;

import gminet.infra.business.BusinessException;
import gminet.infra.business.BusinessObject;
import gminet.infra.business.RuntimeBusinessException;
import gminet.infra.db.Transaction;
import gminet.infra.db.TransactionException;
//import gminet.infra.toolkit.DateToolkit;

import java.sql.Connection;

import kalangos.kpizza.dao.UnidadeMedidaDAO;
import kalangos.kpizza.dto.UnidadeMedida;


/**
 * @author Alexandre Procaci da Silva
 * 
 * 
 */
public class UnidadeMedidaBusiness extends BusinessObject {

    public static UnidadeMedida findByPK(int codUnidadeMedida) throws TransactionException {

        UnidadeMedida unidade_medida = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            unidade_medida = UnidadeMedidaDAO.findByPK(codUnidadeMedida, conn);
            if (unidade_medida == null) {
                throw new RuntimeBusinessException("Unidade de medida não encontrada.");
            }
        } finally {
            transaction.release();
        }
        return unidade_medida;
    }

    public static UnidadeMedida insert(UnidadeMedida unidade_medida) throws BusinessException, TransactionException {

        UnidadeMedida result = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            // INCLUI REGISTRO
            unidade_medida.setCodigo(UnidadeMedidaDAO.getNextCodigo(conn));
            //unidade_medida.setNome(nome) DataCadastro(DateToolkit.getDate());
            UnidadeMedidaDAO.insert(unidade_medida, conn);
            // GRAVAR LOG DE ACESSO
            // insertStatusLog(unidade_medida, conn);
            //
            result = UnidadeMedidaDAO.findByPK(unidade_medida.getCodigo(), conn);
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

    public static void update(UnidadeMedida unidade_medida) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            UnidadeMedidaDAO.update(unidade_medida, conn);
            // COMMIT
            transaction.commit();
        } catch (TransactionException te) {
            transaction.rollback();
            throw te;
        } finally {
            transaction.release();
        }
    }

    public static void delete(UnidadeMedida unidade_medida) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            UnidadeMedidaDAO.delete(unidade_medida, conn);
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
