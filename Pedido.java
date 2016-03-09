package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

import java.util.Date;

public class Pedido extends TransferObject {

    private static final long serialVersionUID = 6825875931698280037L;

    private Cliente cliente;
    private Usuario usuario;
    private PedidoFormaPagamento formaPagamento;
    private PedidoSituacao situacao;
    private Date dataHoraPedido;
    private Double valorPagamento;
    private Double valorTroco;
    private Double valorTotal;
    private Filial filial;
    private PedidoCanal canal;
    private String observacao;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PedidoFormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(PedidoFormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public PedidoSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(PedidoSituacao situacao) {
        this.situacao = situacao;
    }

    public Date getDataHoraPedido() {
        return dataHoraPedido;
    }

    public void setDataHoraPedido(Date dataHoraPedido) {
        this.dataHoraPedido = dataHoraPedido;
    }

    public Double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(Double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public Double getValorTroco() {
        return valorTroco;
    }

    public void setValorTroco(Double valorTroco) {
        this.valorTroco = valorTroco;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public PedidoCanal getCanal() {
        return canal;
    }

    public void setCanal(PedidoCanal canal) {
        this.canal = canal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
