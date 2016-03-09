package kalangos.kpizza.dto;

import gminet.infra.dao.SimpleTransferObject;

import java.util.List;

public class ClienteSituacao extends SimpleTransferObject {

    private static final long serialVersionUID = 6800659290602992248L;

    private static final byte ID_PENDENTE = 11;
    private static final byte ID_ATIVO = 21;
    private static final byte ID_INATIVO = 31;
    private static final byte ID_CANCELADO = 41;

    public static final ClienteSituacao PENDENTE = new ClienteSituacao(ID_PENDENTE, "Pendente");
    public static final ClienteSituacao ATIVO = new ClienteSituacao(ID_ATIVO, "Ativo");
    public static final ClienteSituacao INATIVO = new ClienteSituacao(ID_INATIVO, "Inativo");
    public static final ClienteSituacao CANCELADO = new ClienteSituacao(ID_CANCELADO, "Cancelando");

    static {
        Class<ClienteSituacao> clazz = ClienteSituacao.class;
        addObject(clazz, PENDENTE);
        addObject(clazz, ATIVO);
        addObject(clazz, INATIVO);
        addObject(clazz, CANCELADO);
    }

    private ClienteSituacao(int i, String str) {

        super(i, str);
    }

    @SuppressWarnings("unchecked")
    public static List<ClienteSituacao> getList() {

        return (List<ClienteSituacao>) getList(ClienteSituacao.class);
    }

    public static ClienteSituacao getById(int id) {

        return new ClienteSituacao(id, null);
    }

}
