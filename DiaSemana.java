package kalangos.kpizza.dto;

import gminet.infra.dao.SimpleTransferObject;

import java.util.List;

public class DiaSemana extends SimpleTransferObject {

    private static final long serialVersionUID = -2000228624074454004L;

    private static final byte ID_DOMINGO = 11;
    private static final byte ID_SEGUNDA = 12;
    private static final byte ID_TERCA = 13;
    private static final byte ID_QUARTA = 14;
    private static final byte ID_QUINTA = 15;
    private static final byte ID_SEXTA = 16;
    private static final byte ID_SABADO = 17;

    public static final DiaSemana DOMINGO = new DiaSemana(ID_DOMINGO, "Domingo");
    public static final DiaSemana SEGUNDA = new DiaSemana(ID_SEGUNDA, "Segunda");
    public static final DiaSemana TERCA = new DiaSemana(ID_TERCA, "Ter�a");
    public static final DiaSemana QUARTA = new DiaSemana(ID_QUARTA, "Quarta");
    public static final DiaSemana QUINTA = new DiaSemana(ID_QUINTA, "Quinta");
    public static final DiaSemana SEXTA = new DiaSemana(ID_SEXTA, "Sexta");
    public static final DiaSemana SABADO = new DiaSemana(ID_SABADO, "S�bado");

    static {
        addObject(DiaSemana.class, DOMINGO);
        addObject(DiaSemana.class, SEGUNDA);
        addObject(DiaSemana.class, TERCA);
        addObject(DiaSemana.class, QUARTA);
        addObject(DiaSemana.class, QUINTA);
        addObject(DiaSemana.class, SEXTA);
        addObject(DiaSemana.class, SABADO);
    }

    protected DiaSemana(int i, String str) {
        super(i, str);
    }

    @SuppressWarnings("unchecked")
    public static List<DiaSemana> getList() {

        return (List<DiaSemana>) getList(DiaSemana.class);
    }

    public static DiaSemana getById(int id) {

        return new DiaSemana(id, null);
    }
}
