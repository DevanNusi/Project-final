package PertaCalcApp.services;

import PertaCalcApp.entities.Stock;

import java.util.ArrayList;

public interface AdminServices {
    ArrayList<Stock>berikanDataStock();
    void tambahDataStock();
    void deleteDataStock();
    void editDataStock();
}
