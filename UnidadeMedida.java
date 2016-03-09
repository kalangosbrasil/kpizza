package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

public class UnidadeMedida extends TransferObject {

    private static final long serialVersionUID = -7346798845130207037L;

    private String nome;

    public UnidadeMedida() {
        // TODO Auto-generated constructor stub
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
