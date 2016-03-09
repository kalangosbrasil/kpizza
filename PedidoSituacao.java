package kalangos.kpizza.dto;

import gminet.infra.dao.SimpleTransferObject;

import java.util.List;

public class PedidoSituacao extends SimpleTransferObject {

    private static final long serialVersionUID = -4050429932868267865L;

    private static final byte ID_ABERTO = 10;
    private static final byte ID_AGUARDANDO_ATENDIMENTO = 21;
    private static final byte ID_EM_ATENDIMENTO = 22;
    private static final byte ID_AGUARDANDO_ENTREGA = 31;
    private static final byte ID_EM_ENTREGA = 32;
    private static final byte ID_ENTREGUE = 39;
    private static final byte ID_FINALIZADO = 51;
    private static final byte ID_CANCELADO_CLIENTE = 91;
    private static final byte ID_CANCELADO_LOJA = 92;

    public static final PedidoSituacao ABERTO = new PedidoSituacao(ID_ABERTO, "Aberto");
    public static final PedidoSituacao AGUARDANDO_ATENDIMENTO = new PedidoSituacao(ID_AGUARDANDO_ATENDIMENTO,
            "Aguardando atendimento");
    public static final PedidoSituacao EM_ATENDIMENTO = new PedidoSituacao(ID_EM_ATENDIMENTO, "Em atendimento");
    public static final PedidoSituacao AGUARDANDO_ENTREGA = new PedidoSituacao(ID_AGUARDANDO_ENTREGA,
            "Aguardando entrega");
    public static final PedidoSituacao EM_ENTREGA = new PedidoSituacao(ID_EM_ENTREGA, "Em entrega");
    public static final PedidoSituacao ENTREGUE = new PedidoSituacao(ID_ENTREGUE, "Entregue");
    public static final PedidoSituacao FINALIZADO = new PedidoSituacao(ID_FINALIZADO, "Finalizado");
    public static final PedidoSituacao CANCELADO_CLIENTE = new PedidoSituacao(ID_CANCELADO_CLIENTE,
            "Cancelado pelo cliente");
    public static final PedidoSituacao CANCELADO_LOJA = new PedidoSituacao(ID_CANCELADO_LOJA, "Cancelado pela loja");

    static {
        addObject(PedidoSituacao.class, ABERTO);
        addObject(PedidoSituacao.class, AGUARDANDO_ATENDIMENTO);
        addObject(PedidoSituacao.class, EM_ATENDIMENTO);
        addObject(PedidoSituacao.class, AGUARDANDO_ENTREGA);
        addObject(PedidoSituacao.class, EM_ENTREGA);
        addObject(PedidoSituacao.class, ENTREGUE);
        addObject(PedidoSituacao.class, FINALIZADO);
        addObject(PedidoSituacao.class, CANCELADO_CLIENTE);
        addObject(PedidoSituacao.class, CANCELADO_LOJA);
    }

    protected PedidoSituacao(int i, String str) {
        super(i, str);
    }

    @SuppressWarnings("unchecked")
    public static List<PedidoSituacao> getList() {

        return (List<PedidoSituacao>) getList(PedidoSituacao.class);
    }

    public static PedidoSituacao getById(int id) {

        return new PedidoSituacao(id, null);
    }

}
