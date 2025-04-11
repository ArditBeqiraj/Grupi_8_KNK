package model.Pagesa.PagesaDTO;

import java.math.BigDecimal;

public class UpdatePagesaDTO {
    private int id;
    private BigDecimal shuma;
    private String menyra_pageses;
    private String statusi;
    public enum Type{SHUMA,MENYRA_PAGESES,STATUSI}
    public UpdatePagesaDTO(int id,String update,Type type){
        this.id=id;
        switch (type){
            case SHUMA -> this.shuma = new BigDecimal(update);
            case MENYRA_PAGESES -> this.menyra_pageses = update;
            case STATUSI -> this.statusi=update;
        }
    }

    public int getid() {
        return id;
    }

    public void setid(int pagesa_id) {
        this.id = pagesa_id;
    }

    public BigDecimal getShuma() {
        return shuma;
    }

    public void setShuma(BigDecimal shuma) {
        this.shuma = shuma;
    }

    public String getMenyra_pageses() {
        return menyra_pageses;
    }

    public void setMenyra_pageses(String menyra_pageses) {
        this.menyra_pageses = menyra_pageses;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }
}
