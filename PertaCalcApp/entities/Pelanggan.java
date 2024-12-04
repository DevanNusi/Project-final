package PertaCalcApp.entities;

public class Pelanggan {
    private String nama;
    private int totalPembayaran;
    private int banyakBensin;
    private String jenisBensin;
    private String metodePembayaran;
    private String kritikDanSaran;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getTotalPembayaran() {
        return totalPembayaran;
    }

    public void setTotalPembayaran(int totalPembayaran) {
        this.totalPembayaran = totalPembayaran;
    }

    public int getBanyakBensin() {
        return banyakBensin;
    }

    public void setBanyakBensin(int banyakBensin) {
        this.banyakBensin = banyakBensin;
    }

    public String getJenisBensin() {
        return jenisBensin;
    }

    public void setJenisBensin(String jenisBensin) {
        this.jenisBensin = jenisBensin;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public String getKritikDanSaran() {
        return kritikDanSaran;
    }

    public void setKritikDanSaran(String kritikDanSaran) {
        this.kritikDanSaran = kritikDanSaran;
    }
}
