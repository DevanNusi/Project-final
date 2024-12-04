package PertaCalcApp.repositories;

import PertaCalcApp.entities.Stock;

import java.util.ArrayList;

public interface StokRepositories {
    ArrayList<Stock> ambilDataStock();
    void hapusData(int index);
    void editData(Stock dataBaru, int index);
    void tambahData(Stock dataBaru);
}
