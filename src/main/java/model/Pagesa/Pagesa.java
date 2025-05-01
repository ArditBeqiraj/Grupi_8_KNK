package model.Pagesa;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Pagesa {
    private  int id;
    private int pacient_id;
    private Integer fatura_id;
    private BigDecimal shuma;
    private String menyra_pageses;
    private String statusi;
    private LocalDateTime krijuar;

    public Pagesa(int id, int pacient_id, Integer fatura_id, String menyra_pageses, BigDecimal shuma, String statusi, LocalDateTime krijuar) {
        this.id = id;
        this.pacient_id = pacient_id;
        this.fatura_id = fatura_id;
        this.menyra_pageses = menyra_pageses;
        this.shuma = shuma;
        this.statusi = statusi;
        this.krijuar = krijuar;
    }
    public static Pagesa getInstance(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("id");
        int pacient_id = resultSet.getInt("pacient_id");
        Integer fatura_id = resultSet.getObject("fatura_id") !=null ? resultSet.getInt("fatura_id") : null;
        BigDecimal shuma = resultSet.getBigDecimal("shuma");
        String menyra_pageses = resultSet.getString("menyra_pageses");
        String statusi = resultSet.getString("statusi");
        LocalDateTime krijuar = resultSet.getTimestamp("krijuar").toLocalDateTime();
        Integer regjistruar_nga = resultSet.getObject("regjistruar_nga") !=null ? resultSet.getInt("regjistruar_nga") : null;
        return new Pagesa(id, pacient_id, fatura_id, menyra_pageses, shuma, statusi, krijuar);

    }

    public int getid() {
        return id;
    }

    public int getPacient_id() {
        return pacient_id;
    }

    public Integer getFatura_id() {
        return fatura_id;
    }

    public BigDecimal getShuma() {
        return shuma;
    }

    public String getMenyra_pageses() {
        return menyra_pageses;
    }

    public String getStatusi() {
        return statusi;
    }

    public LocalDateTime getKrijuar() {
        return krijuar;
    }

}
