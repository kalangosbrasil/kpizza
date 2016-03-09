package kalangos.kpizza.business;

import gminet.infra.business.BusinessException;
import gminet.infra.business.BusinessObject;
import gminet.infra.business.RuntimeBusinessException;
import gminet.infra.db.Transaction;
import gminet.infra.db.TransactionException;
//import gminet.infra.toolkit.DateToolkit;

import java.sql.Connection;

import kalangos.kpizza.dao.FilialDAO;
import kalangos.kpizza.dto.Filial;


/**
 * @author Alexandre Procaci da Silva
 * 
 * 
 */
public class FilialBusiness extends BusinessObject {

    public static Filial findByPK(int codFilial) throws TransactionException {

        Filial filial = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            filial = FilialDAO.findByPK(codFilial, conn);
            if (filial == null) {
                throw new RuntimeBusinessException("Loja Fisica não encontrada.");
            }
        } finally {
            transaction.release();
        }
        return filial;
    }

    public static Filial insert(Filial filial) throws BusinessException, TransactionException {

        Filial result = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            // VERIFICA SE USUARIO JA FEZ REGISTRO
            // String emailFilial = filial.getEmail();
            // INCLUI REGISTRO
            filial.setCodigo(FilialDAO.getNextCodigo(conn));
            //filial.setNome(nome) DataCadastro(DateToolkit.getDate());
            //filial.setSituacao(FilialSituacao.ATIVO);
            // Buscar loja virtual
            // filial.setLojaVirtual();
            FilialDAO.insert(filial, conn);
            // GRAVAR LOG DE ACESSO
            // insertStatusLog(filial, conn);
            //
            result = FilialDAO.findByPK(filial.getCodigo(), conn);
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

    public static void update(Filial filial) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            FilialDAO.update(filial, conn);
            // COMMIT
            transaction.commit();
        } catch (TransactionException te) {
            transaction.rollback();
            throw te;
        } finally {
            transaction.release();
        }
    }

    public static void delete(Filial filial) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            FilialDAO.delete(filial, conn);
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
