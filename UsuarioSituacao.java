package kalangos.kpizza.dto;

import gminet.infra.dao.SimpleTransferObject;

import java.util.List;

/**
 * @author Gabriel Flores Mendes
 * 
 * 
 */
public class UsuarioSituacao extends SimpleTransferObject {

    private static final long serialVersionUID = -3829009945032436193L;

    private static final byte ID_HABILITADO = 50;
    private static final byte ID_DESABILITADO = 90;

    public static final UsuarioSituacao HABILITADO = new UsuarioSituacao(ID_HABILITADO, "Ativo");
    public static final UsuarioSituacao DESABILITADO = new UsuarioSituacao(ID_DESABILITADO, "Inativo");

    static {
        Class<UsuarioSituacao> clazz = UsuarioSituacao.class;
        addObject(clazz, HABILITADO);
        addObject(clazz, DESABILITADO);
    }

    private UsuarioSituacao(int i, String str) {

        super(i, str);
    }

    public boolean isHabilitado() {

        return this.getCodigo() == ID_HABILITADO;
    }

    public boolean isDesabilitado() {

        return this.getCodigo() == ID_DESABILITADO;
    }

    @SuppressWarnings("unchecked")
    public static List<UsuarioSituacao> getList() {

        return (List<UsuarioSituacao>) getList(UsuarioSituacao.class);
    }

    public static UsuarioSituacao getById(int id) {

        return new UsuarioSituacao(id, null);
    }

}
