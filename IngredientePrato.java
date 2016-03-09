package kalangos.kpizza.dto;

import gminet.infra.dao.TransferObject;

public class IngredientePrato extends TransferObject {

    private static final long serialVersionUID = 6596712958309755370L;

    private Item prato;
    private Item ingrediente;

    public Item getPrato() {
        return prato;
    }

    public void setPrato(Item prato) {
        this.prato = prato;
    }

    public Item getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Item ingrediente) {
        this.ingrediente = ingrediente;
    }

}
