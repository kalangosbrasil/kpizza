package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

public class ItemInventario extends TransferObject {

    private static final long serialVersionUID = -2487573447940227886L;

    private String nome;
    private UnidadeMedida unidade;

    public ItemInventario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UnidadeMedida getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeMedida unidade) {
        this.unidade = unidade;
    }

}
