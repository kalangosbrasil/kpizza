package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

public class ValorNutricional extends TransferObject {

    private static final long serialVersionUID = 6980576972022203810L;

    private Item item;
    private Integer valor;
    private Integer percentual;
    private UnidadeMedida unidade;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getPercentual() {
        return percentual;
    }

    public void setPercentual(Integer percentual) {
        this.percentual = percentual;
    }

    public UnidadeMedida getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeMedida unidade) {
        this.unidade = unidade;
    }

}
