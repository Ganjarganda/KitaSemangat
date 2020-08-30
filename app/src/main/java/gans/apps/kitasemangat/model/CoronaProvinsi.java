package gans.apps.kitasemangat.model;

public class CoronaProvinsi {

    /* "FID": 11,
      "Kode_Provi": 31,
      "Provinsi": "DKI Jakarta",
      "Kasus_Posi": 37943,
      "Kasus_Semb": 29768,
      "Kasus_Meni": 1174*/

    private String fid, kode_provinsi, provinsi, kasus_positif, kasus_sembuh, kasus_meninggal;

    public CoronaProvinsi(String fid, String kode_provinsi, String provinsi, String kasus_positif, String kasus_sembuh, String kasus_meninggal) {
        this.fid = fid;
        this.kode_provinsi = kode_provinsi;
        this.provinsi = provinsi;
        this.kasus_positif = kasus_positif;
        this.kasus_sembuh = kasus_sembuh;
        this.kasus_meninggal = kasus_meninggal;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getKode_provinsi() {
        return kode_provinsi;
    }

    public void setKode_provinsi(String kode_provinsi) {
        this.kode_provinsi = kode_provinsi;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKasus_positif() {
        return kasus_positif;
    }

    public void setKasus_positif(String kasus_positif) {
        this.kasus_positif = kasus_positif;
    }

    public String getKasus_sembuh() {
        return kasus_sembuh;
    }

    public void setKasus_sembuh(String kasus_sembuh) {
        this.kasus_sembuh = kasus_sembuh;
    }

    public String getKasus_meninggal() {
        return kasus_meninggal;
    }

    public void setKasus_meninggal(String kasus_meninggal) {
        this.kasus_meninggal = kasus_meninggal;
    }
}
