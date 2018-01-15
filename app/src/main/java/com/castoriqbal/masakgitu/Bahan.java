package com.castoriqbal.masakgitu;

import java.io.Serializable;

/**
 * Created by Castor on 1/9/2018.
 */

public class Bahan implements Serializable {
    private String nama;
    private String takaran;

    public Bahan(String takaran, String nama) {
        this.takaran = takaran;
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTakaran() {
        return takaran;
    }

    public void setTakaran(String takaran) {
        this.takaran = takaran;
    }
}
