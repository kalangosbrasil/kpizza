package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

public class ItemNutricional extends TransferObject {

    private static final long serialVersionUID = 5309829169908289082L;

    private String nome;
    private String valorDiario;
    private UnidadeMedida unidade;

    public ItemNutricional() {
        // TODO Auto-generated constructor stub
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValorDiario() {
        return valorDiario;
    }

    public void setValorDiario(String valorDiario) {
        this.valorDiario = valorDiario;
    }

    public UnidadeMedida getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeMedida unidade) {
        this.unidade = unidade;
    }

}
