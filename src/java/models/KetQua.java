/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ADMIN
 */
public class KetQua {
    private String ma,ten,monThi,kyThi,thoiGian,diem,tinhTrang,chiTiet;
    public KetQua(String ma, String ten, String monThi, String kyThi, String thoiGian, String diem, String tinhTrang, String chiTiet){
        this.ma=ma;
        this.ten=ten;
        this.monThi=monThi;
        this.kyThi=kyThi;
        this.thoiGian=thoiGian;
        this.diem=diem;
        this.tinhTrang=tinhTrang;
        this.chiTiet=chiTiet;
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public String getMonThi() {
        return monThi;
    }

    public String getKyThi() {
        return kyThi;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public String getDiem() {
        return diem;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public String getChiTiet() {
        return chiTiet;
    }
    
}
