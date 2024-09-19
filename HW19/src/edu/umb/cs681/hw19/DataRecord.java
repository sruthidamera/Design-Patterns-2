package edu.umb.cs681.hw19;

public class DataRecord {
    private int year;
    private int doy;
    private double prectotcorr;
    private double rh2m;
    private double gwetprof;
    private double gwetroot;
    private double gwettop;

    public DataRecord(int year, int doy, double prectotcorr, double rh2m, double gwetprof, double gwetroot, double gwettop) {
        this.year = year;
        this.doy = doy;
        this.prectotcorr = prectotcorr;
        this.rh2m = rh2m;
        this.gwetprof = gwetprof;
        this.gwetroot = gwetroot;
        this.gwettop = gwettop;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDoy() {
        return doy;
    }

    public void setDoy(int doy) {
        this.doy = doy;
    }

    public double getPrectotcorr() {
        return prectotcorr;
    }

    public void setPrectotcorr(double prectotcorr) {
        this.prectotcorr = prectotcorr;
    }

    public double getRh2m() {
        return rh2m;
    }

    public void setRh2m(double rh2m) {
        this.rh2m = rh2m;
    }

    public double getGwetprof() {
        return gwetprof;
    }

    public void setGwetprof(double gwetprof) {
        this.gwetprof = gwetprof;
    }

    public double getGwetroot() {
        return gwetroot;
    }

    public void setGwetroot(double gwetroot) {
        this.gwetroot = gwetroot;
    }

    public double getGwettop() {
        return gwettop;
    }

    public void setGwettop(double gwettop) {
        this.gwettop = gwettop;
    }
}
