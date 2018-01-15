package com.castoriqbal.masakgitu;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Castor on 1/9/2018.
 */

public class Resep implements Serializable {
    private String judulResep;
    private String subJudulResep;
    private String penjelasanResep;
    private String tingkatKesulitan;
    private String untukBerapaOrang;
    private String waktuMemasak;
    private String resepImage;
    private ArrayList<Bahan> bahan = new ArrayList<>();
    private ArrayList<Step> step = new ArrayList<>();
    private double match;

    public Resep(String judulResep, String subJudulResep, String penjelasanResep, String tingkatKesulitan, String untukBerapaOrang, String waktuMemasak, String resepImage, ArrayList<Bahan> bahan, ArrayList<Step> step, double match) {
        this.judulResep = judulResep;
        this.subJudulResep = subJudulResep;
        this.penjelasanResep = penjelasanResep;
        this.tingkatKesulitan = tingkatKesulitan;
        this.untukBerapaOrang = untukBerapaOrang;
        this.waktuMemasak = waktuMemasak;
        this.resepImage = resepImage;
        this.bahan = bahan;
        this.step = step;
        this.match = match;
    }

    public String getJudulResep() {
        return judulResep;
    }

    public void setJudulResep(String judulResep) {
        this.judulResep = judulResep;
    }

    public String getSubJudulResep() {
        return subJudulResep;
    }

    public void setSubJudulResep(String subJudulResep) {
        this.subJudulResep = subJudulResep;
    }

    public String getPenjelasanResep() {
        return penjelasanResep;
    }

    public void setPenjelasanResep(String penjelasanResep) {
        this.penjelasanResep = penjelasanResep;
    }

    public String getTingkatKesulitan() {
        return tingkatKesulitan;
    }

    public void setTingkatKesulitan(String tingkatKesulitan) {
        this.tingkatKesulitan = tingkatKesulitan;
    }

    public String getUntukBerapaOrang() {
        return untukBerapaOrang;
    }

    public void setUntukBerapaOrang(String untukBerapaOrang) {
        this.untukBerapaOrang = untukBerapaOrang;
    }

    public String getWaktuMemasak() {
        return waktuMemasak;
    }

    public void setWaktuMemasak(String waktuMemasak) {
        this.waktuMemasak = waktuMemasak;
    }

    public String getResepImage() {
        return resepImage;
    }

    public void setResepImage(String resepImage) {
        this.resepImage = resepImage;
    }

    public ArrayList<Bahan> getBahan() {
        return bahan;
    }

    public void setBahan(ArrayList<Bahan> bahan) {
        this.bahan = bahan;
    }

    public ArrayList<Step> getStep() {
        return step;
    }

    public void setStep(ArrayList<Step> step) {
        this.step = step;
    }

    public double getMatch() {
        return match;
    }

    public void setMatch(double match) {
        this.match = match;
    }
}
