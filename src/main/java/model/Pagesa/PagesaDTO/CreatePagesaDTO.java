package model.Pagesa.PagesaDTO;

import java.math.BigDecimal;

public class CreatePagesaDTO {
    private int pacient_id;
    private Integer fatura_id;
    private BigDecimal shuma;
    private String menyra_pageses;
    private String statusi;

    public CreatePagesaDTO(int pacient_id, Integer fatura_id, BigDecimal shuma, String menyra_pageses, String statusi) {
        this.pacient_id = pacient_id;
        this.fatura_id = fatura_id;
        this.shuma = shuma;
        this.menyra_pageses = menyra_pageses;
        this.statusi = statusi;
    }

    public int getPacient_id() {
        return pacient_id;
    }

    public void setPacient_id(int pacient_id) {
        this.pacient_id = pacient_id;
    }

    public Integer getFatura_id() {
        return fatura_id;
    }

    public void setFatura_id(Integer fatura_id) {
        this.fatura_id = fatura_id;
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
