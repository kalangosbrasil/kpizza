package kalangos.kpizza.business;

import gminet.infra.business.BusinessException;
import gminet.infra.business.BusinessObject;
import gminet.infra.business.RuntimeBusinessException;
import gminet.infra.db.Transaction;
import gminet.infra.db.TransactionException;
//import gminet.infra.toolkit.DateToolkit;

import java.sql.Connection;

import kalangos.kpizza.dao.LojaFisicaDAO;
import kalangos.kpizza.dto.LojaFisica;


/**
 * @author Alexandre Procaci da Silva
 * 
 * 
 */
public class LojaFisicaBusiness extends BusinessObject {

    public static LojaFisica findByPK(int codLojaFisica) throws TransactionException {

        LojaFisica loja_fisica = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            loja_fisica = LojaFisicaDAO.findByPK(codLojaFisica, conn);
            if (loja_fisica == null) {
                throw new RuntimeBusinessException("Loja Fisica não encontrada.");
            }
        } finally {
            transaction.release();
        }
        return loja_fisica;
    }

    public static LojaFisica insert(LojaFisica loja_fisica) throws BusinessException, TransactionException {

        LojaFisica result = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            // VERIFICA SE USUARIO JA FEZ REGISTRO
            // String emailLojaFisica = loja_fisica.getEmail();
            // INCLUI REGISTRO
            loja_fisica.setCodigo(LojaFisicaDAO.getNextCodigo(conn));
            //loja_fisica.setNome(nome) DataCadastro(DateToolkit.getDate());
            //loja_fisica.setSituacao(LojaFisicaSituacao.ATIVO);
            // Buscar loja virtual
            // loja_fisica.setLojaVirtual();
            LojaFisicaDAO.insert(loja_fisica, conn);
            // GRAVAR LOG DE ACESSO
            // insertStatusLog(loja_fisica, conn);
            //
            result = LojaFisicaDAO.findByPK(loja_fisica.getCodigo(), conn);
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

    public static void update(LojaFisica loja_fisica) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            LojaFisicaDAO.update(loja_fisica, conn);
            // COMMIT
            transaction.commit();
        } catch (TransactionException te) {
            transaction.rollback();
            throw te;
        } finally {
            transaction.release();
        }
    }

    public static void delete(LojaFisica loja_fisica) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            LojaFisicaDAO.delete(loja_fisica, conn);
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
