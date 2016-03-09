package kalangos.kpizza.dto;

import gminet.infra.dao.SimpleTransferObject;

public class PromocaoTipoPromocao extends SimpleTransferObject {

    private static final long serialVersionUID = 9133115187236940673L;

    private static final byte ID_DIARIA = 10;
    private static final byte ID_SEMANAL = 20;
    private static final byte ID_MENSAL = 30;
    private static final byte ID_EVENTUAL = 40;

    public static final PromocaoTipoPromocao DIARIA = new PromocaoTipoPromocao(ID_DIARIA, "Di�ria");
    public static final PromocaoTipoPromocao SEMANAL = new PromocaoTipoPromocao(ID_SEMANAL, "Semanal");
    public static final PromocaoTipoPromocao MENSAL = new PromocaoTipoPromocao(ID_MENSAL, "Mensal");
    public static final PromocaoTipoPromocao EVENTUAL = new PromocaoTipoPromocao(ID_EVENTUAL, "Eventual");

    static {
        Class<PromocaoSituacao> clazz = PromocaoSituacao.class;
        addObject(clazz, DIARIA);
        addObject(clazz, SEMANAL);
        addObject(clazz, MENSAL);
        addObject(clazz, EVENTUAL);
    }

    private PromocaoTipoPromocao(int i, String str) {

        super(i, str);
    }
}
