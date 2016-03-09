package kalangos.kpizza.dto;

import gminet.infra.dao.SimpleTransferObject;

import java.util.List;

/**
 * @author Gabriel Flores Mendes
 * 
 * 
 */
public class ClienteAcessoTipo extends SimpleTransferObject {

    private static final long serialVersionUID = 6410221856927852696L;

    private static final byte ID_SITE = 10;
    private static final byte ID_FACEBOOK = 21;
    private static final byte ID_GOOGLE = 21;

    public static final ClienteAcessoTipo SITE = new ClienteAcessoTipo(ID_SITE, "Site");
    public static final ClienteAcessoTipo FACEBOOK = new ClienteAcessoTipo(ID_FACEBOOK, "Facebook");
    public static final ClienteAcessoTipo GOOGLE = new ClienteAcessoTipo(ID_GOOGLE, "Google");

    static {
        Class<ClienteAcessoTipo> clazz = ClienteAcessoTipo.class;
        addObject(clazz, SITE);
        addObject(clazz, FACEBOOK);
        addObject(clazz, GOOGLE);
    }

    private ClienteAcessoTipo(int i, String str) {

        super(i, str);
    }

    @SuppressWarnings("unchecked")
    public static List<ClienteAcessoTipo> getList() {

        return (List<ClienteAcessoTipo>) getList(ClienteAcessoTipo.class);
    }

    public static ClienteAcessoTipo getById(int id) {

        return new ClienteAcessoTipo(id, null);
    }

}
