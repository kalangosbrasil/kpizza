package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

public class LojaFisica extends TransferObject {

    private static final long serialVersionUID = -9051291665626857668L;

    private String nome;

    public LojaFisica() {
        // TODO Auto-generated constructor stub
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
