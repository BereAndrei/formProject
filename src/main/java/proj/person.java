package proj;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class person {


    private String nume;
    private String prenume;
    private int varsta = 20;
    private String angajat;
    private boolean esteAici;
    public static String[] studii = {"Primar","Gimnazial","Liceal","Postliceal","Superior", "Masterat", "Doctorat"};
    private String studiu;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getAngajat() {
        return angajat;
    }

    public void setAngajat(String angajat) {
        this.angajat = angajat;
    }

    public boolean isEsteAici() {
        return esteAici;
    }

    public void setEsteAici(boolean esteAici) {
        this.esteAici = esteAici;
    }

    public static String[] getStudii() {
        return studii;
    }

    public static void setStudii(String[] studii) {
        person.studii = studii;
    }

    public String getStudiu() {
        return studiu;
    }

    public void setStudiu(String studiu) {
        this.studiu = studiu;
    }

    public person(String nume, String prenume, int varsta, String angajat, boolean esteAici, String studiu) {
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
        this.angajat = angajat;
        this.esteAici = esteAici;
        this.studiu = studiu;
    }
    public person(){

    }

    public String[] personrow() {
        String var = String.valueOf(varsta);
        String bol;
        if(esteAici){
            bol = "Da";
        }
        else
            bol = "Nu";
        String[] returnat= {nume, prenume, var, angajat, bol, studiu};

        return returnat;
    }
}
