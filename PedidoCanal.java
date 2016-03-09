package kalangos.kpizza.dto;

import java.util.List;

import gminet.infra.dao.SimpleTransferObject;

public class PedidoCanal extends SimpleTransferObject {

    private static final long serialVersionUID = 936170286380846355L;

    private static final byte ID_INTERNET = 11;
    private static final byte ID_TELEFONE = 21;

    public static final PedidoCanal INTERNET = new PedidoCanal(ID_INTERNET, "Internet");
    public static final PedidoCanal TELEFONE = new PedidoCanal(ID_TELEFONE, "Telefone");

    static {
        addObject(PedidoCanal.class, INTERNET);
        addObject(PedidoCanal.class, TELEFONE);
    }

    protected PedidoCanal(int i, String str) {
        super(i, str);
    }

    @SuppressWarnings("unchecked")
    public static List<PedidoCanal> getList() {

        return (List<PedidoCanal>) getList(PedidoCanal.class);
    }

    public static PedidoCanal getById(int id) {

        return new PedidoCanal(id, null);
    }

}
