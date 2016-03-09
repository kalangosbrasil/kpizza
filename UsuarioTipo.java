package kalangos.kpizza.dto;

import gminet.infra.dao.SimpleTransferObject;

import java.util.List;

public class UsuarioTipo extends SimpleTransferObject {

    private static final long serialVersionUID = -3645705764697838036L;

    private static final byte ID_OPERADOR = 10;
    private static final byte ID_ADMINISTRADOR = 20;
    private static final byte ID_MASTER = 99;

    public static final UsuarioTipo OPERADOR = new UsuarioTipo(ID_OPERADOR, "Operador");
    public static final UsuarioTipo ADMINISTRADOR = new UsuarioTipo(ID_ADMINISTRADOR, "Administrador");
    public static final UsuarioTipo MASTER = new UsuarioTipo(ID_MASTER, "Master");

    static {
        addObject(UsuarioTipo.class, OPERADOR);
        addObject(UsuarioTipo.class, ADMINISTRADOR);
        addObject(UsuarioTipo.class, MASTER);
    }

    private UsuarioTipo(int i, String str) {

        super(i, str);
    }

    @SuppressWarnings("unchecked")
    public static List<UsuarioTipo> getList() {

        return (List<UsuarioTipo>) getList(UsuarioTipo.class);
    }

    public static UsuarioTipo getById(int id) {

        return new UsuarioTipo(id, null);
    }

}
