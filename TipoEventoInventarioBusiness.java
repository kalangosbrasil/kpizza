package kalangos.kpizza.business;

import gminet.infra.business.BusinessException;
import gminet.infra.business.BusinessObject;
import gminet.infra.business.RuntimeBusinessException;
import gminet.infra.db.Transaction;
import gminet.infra.db.TransactionException;
//import gminet.infra.toolkit.DateToolkit;

import java.sql.Connection;

import kalangos.kpizza.dao.TipoEventoInventarioDAO;
import kalangos.kpizza.dto.TipoEventoInventario;


/**
 * @author Alexandre Procaci da Silva
 * 
 * 
 */
public class TipoEventoInventarioBusiness extends BusinessObject {

    public static TipoEventoInventario findByPK(int codTipoEventoInventario) throws TransactionException {

        TipoEventoInventario tipo_evento_inventario = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            tipo_evento_inventario = TipoEventoInventarioDAO.findByPK(codTipoEventoInventario, conn);
            if (tipo_evento_inventario == null) {
                throw new RuntimeBusinessException("Tipo de Evento não encontrado.");
            }
        } finally {
            transaction.release();
        }
        return tipo_evento_inventario;
    }

    public static TipoEventoInventario insert(TipoEventoInventario tipo_evento_inventario) throws BusinessException, TransactionException {

        TipoEventoInventario result = null;
        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            // INCLUI REGISTRO
            tipo_evento_inventario.setCodigo(TipoEventoInventarioDAO.getNextCodigo(conn));
            //tipo_evento_inventario.setNome(nome) DataCadastro(DateToolkit.getDate());
            TipoEventoInventarioDAO.insert(tipo_evento_inventario, conn);
            // GRAVAR LOG DE ACESSO
            // insertStatusLog(tipo_evento_inventario, conn);
            //
            result = TipoEventoInventarioDAO.findByPK(tipo_evento_inventario.getCodigo(), conn);
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

    public static void update(TipoEventoInventario tipo_evento_inventario) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            TipoEventoInventarioDAO.update(tipo_evento_inventario, conn);
            // COMMIT
            transaction.commit();
        } catch (TransactionException te) {
            transaction.rollback();
            throw te;
        } finally {
            transaction.release();
        }
    }

    public static void delete(TipoEventoInventario tipo_evento_inventario) throws BusinessException, TransactionException {

        Transaction transaction = createTransaction();
        try {
            Connection conn = transaction.getConnection();
            TipoEventoInventarioDAO.delete(tipo_evento_inventario, conn);
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
