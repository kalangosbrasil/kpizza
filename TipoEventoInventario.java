package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

public class TipoEventoInventario extends TransferObject {

    private static final long serialVersionUID = 329860197431613820L;

    private String nome;

    public TipoEventoInventario() {
        // TODO Auto-generated constructor stub
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
