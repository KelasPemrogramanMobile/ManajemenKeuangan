package com.ta.monman.monman;

/**
 * Created by root on 1/7/18.
 */

public class Pemasukkan {
    private int id, nominal;
    private String detail,transaksi,user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(String transaksi) {
        this.transaksi = transaksi;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Pemasukkan(int id, String transaksi, int nominal, String detail, String user) {
        this.id = id;
        this.nominal = nominal;
        this.transaksi = transaksi;

        this.detail = detail;
        this.user = user;
    }
}
