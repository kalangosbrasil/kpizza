package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

public class Item extends TransferObject {

    private static final long serialVersionUID = 1029395002900663291L;

    private LojaVirtual lojaVirtual;
    private String nome;
    private String tamanho;
    private Double valorVenda;
    private TipoItem tipo;
    private Double valorCusto;
    private Integer quantidade;
    private ItemInventario item;

    public LojaVirtual getLojaVirtual() {
        return lojaVirtual;
    }

    public void setLojaVirtual(LojaVirtual lojaVirtual) {
        this.lojaVirtual = lojaVirtual;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public Double getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(Double valorCusto) {
        this.valorCusto = valorCusto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public ItemInventario getItem() {
        return item;
    }

    public void setItem(ItemInventario item) {
        this.item = item;
    }

}
