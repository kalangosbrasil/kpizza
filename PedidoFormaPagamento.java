package kalangos.kpizza.dto;

import gminet.infra.dao.SimpleTransferObject;

import java.util.List;

public class PedidoFormaPagamento extends SimpleTransferObject {

    private static final long serialVersionUID = -3096767626336041285L;

    private static final byte ID_DINHEIRO = 11;
    private static final byte ID_CHEQUE = 21;
    private static final byte ID_CARTAO_CREDITO = 31;
    private static final byte ID_CARTAO_DEBITO = 41;
    private static final byte ID_CARTAO_TICKET = 51;

    public static final PedidoFormaPagamento DINHEIRO = new PedidoFormaPagamento(ID_DINHEIRO, "Dinheiro");
    public static final PedidoFormaPagamento CHEQUE = new PedidoFormaPagamento(ID_CHEQUE, "Cheque");
    public static final PedidoFormaPagamento CARTAO_CREDITO = new PedidoFormaPagamento(ID_CARTAO_CREDITO, "Credito");
    public static final PedidoFormaPagamento CARTAO_DEBITO = new PedidoFormaPagamento(ID_CARTAO_DEBITO, "Debito");
    public static final PedidoFormaPagamento CARTAO_TICKET = new PedidoFormaPagamento(ID_CARTAO_TICKET, "Ticket");

    static {
        addObject(PedidoFormaPagamento.class, DINHEIRO);
        addObject(PedidoFormaPagamento.class, CHEQUE);
        addObject(PedidoFormaPagamento.class, CARTAO_CREDITO);
        addObject(PedidoFormaPagamento.class, CARTAO_DEBITO);
        addObject(PedidoFormaPagamento.class, CARTAO_TICKET);
    }

    protected PedidoFormaPagamento(int i, String str) {
        super(i, str);
    }

    @SuppressWarnings("unchecked")
    public static List<PedidoFormaPagamento> getList() {

        return (List<PedidoFormaPagamento>) getList(PedidoFormaPagamento.class);
    }

    public static PedidoFormaPagamento getById(int id) {

        return new PedidoFormaPagamento(id, null);
    }

}
