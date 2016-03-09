package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

public class LojaVirtual extends TransferObject {

    private static final long serialVersionUID = -8907479435480660984L;

    private String nome;

    public LojaVirtual() {
        // TODO Auto-generated constructor stub
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
