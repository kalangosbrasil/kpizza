package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

public class TipoItem extends TransferObject {

    private static final long serialVersionUID = 2134197488787941815L;

    private String nome;

    public TipoItem() {
        // TODO Auto-generated constructor stub
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
