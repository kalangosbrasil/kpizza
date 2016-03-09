package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

public class PromocaoVigente extends TransferObject {

    private static final long serialVersionUID = -5399787013483907994L;

    public Item item;
    public String descricao;
    public Double valor;
    public Integer valorPercentual;
    public PromocaoSituacao situacao;
    public PromocaoTipoPromocao tipo;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getValorPercentual() {
        return valorPercentual;
    }

    public void setValorPercentual(Integer valorPercentual) {
        this.valorPercentual = valorPercentual;
    }

    public PromocaoSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(PromocaoSituacao situacao) {
        this.situacao = situacao;
    }

    public PromocaoTipoPromocao getTipo() {
        return tipo;
    }

    public void setTipo(PromocaoTipoPromocao tipo) {
        this.tipo = tipo;
    }

}
