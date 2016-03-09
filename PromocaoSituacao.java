package kalangos.kpizza.dto;

import gminet.infra.dao.SimpleTransferObject;

public class PromocaoSituacao extends SimpleTransferObject {

    private static final long serialVersionUID = 4070026503591717512L;

    private static final byte ID_VIGENTE = 50;
    private static final byte ID_VENCIDA = 90;

    public static final PromocaoSituacao VIGENTE = new PromocaoSituacao(ID_VIGENTE, "Vigente");
    public static final PromocaoSituacao VENCIDA = new PromocaoSituacao(ID_VENCIDA, "Vencida");

    static {
        Class<PromocaoSituacao> clazz = PromocaoSituacao.class;
        addObject(clazz, VIGENTE);
        addObject(clazz, VENCIDA);
    }

    private PromocaoSituacao(int i, String str) {

        super(i, str);

    }

}
