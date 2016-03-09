package kalangos.kpizza.business;

import gminet.infra.business.BusinessException;
import gminet.infra.business.BusinessObject;
import gminet.infra.business.RuntimeBusinessException;
import gminet.infra.db.Transaction;
import gminet.infra.db.TransactionException;
import gminet.infra.toolkit.DateToolkit;

import java.sql.Connection;

import kalangos.kpizza.dao.ClienteDAO;
import kalangos.kpizza.dto.Cliente;
import kalangos.kpizza.dto.ClienteSituacao;

/**
 * @author Gabriel Flores Mendes
 * 
 * 
 */
public class ClienteBusiness extends BusinessObject {

    public static Cliente findByPK(int codCliente) throws TransactionException {

        Cliente cliente = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            cliente = ClienteDAO.findByPK(codCliente, conn);
            if (cliente == null) {
                throw new RuntimeBusinessException("Cliente não encontrado.");
            }
        } finally {
            transaction.release();
        }
        return cliente;
    }

    public static Cliente insert(Cliente cliente) throws BusinessException, TransactionException {

        Cliente result = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            // VERIFICA SE USUARIO JA FEZ REGISTRO
            // String emailCliente = cliente.getEmail();
            // INCLUI REGISTRO
            cliente.setCodigo(ClienteDAO.getNextCodigo(conn));
            cliente.setDataCadastro(DateToolkit.getDate());
            cliente.setSituacao(ClienteSituacao.ATIVO);
            // Buscar loja virtual
            // cliente.setLojaVirtual();
            ClienteDAO.insert(cliente, conn);
            // GRAVAR LOG DE ACESSO
            // insertStatusLog(cliente, conn);
            //
            result = ClienteDAO.findByPK(cliente.getCodigo(), conn);
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

    public static void update(Cliente cliente) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            ClienteDAO.update(cliente, conn);
            // COMMIT
            transaction.commit();
        } catch (TransactionException te) {
            transaction.rollback();
            throw te;
        } finally {
            transaction.release();
        }
    }

    public static void delete(Cliente cliente) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            ClienteDAO.delete(cliente, conn);
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
