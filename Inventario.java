package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

public class Inventario extends TransferObject {

    private static final long serialVersionUID = -6758009180825372632L;

    private ItemInventario item;
    private LojaFisica loja;
    private Integer quantidade;

    public Inventario() {
    }

    public ItemInventario getItem() {
        return item;
    }

    public void setItem(ItemInventario item) {
        this.item = item;
    }

    public LojaFisica getLoja() {
        return loja;
    }

    public void setLoja(LojaFisica loja) {
        this.loja = loja;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
