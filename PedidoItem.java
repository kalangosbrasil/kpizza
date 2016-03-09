package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

public class PedidoItem extends TransferObject {

    private static final long serialVersionUID = 5004274422903498293L;

    private Pedido pedido;
    private Cliente cliente;
    private Item item;
    private PedidoSituacao situacao;
    private Double valorVenda;
    private Double valorDesconto;
    private Integer quantidade;
    private PedidoItem pedidoItemPai;

    public PedidoItem() {
        // TODO Auto-generated constructor stub
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public PedidoSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(PedidoSituacao situacao) {
        this.situacao = situacao;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public PedidoItem getPedidoItemPai() {
        return pedidoItemPai;
    }

    public void setPedidoItemPai(PedidoItem pedidoItemPai) {
        this.pedidoItemPai = pedidoItemPai;
    }

}
