package PertaCalcApp.services;

import PertaCalcApp.entities.Pelanggan;

import java.util.ArrayList;

public interface TransactionServices {
    ArrayList<Pelanggan> berikanDataPelanggan();
    void pesanBensin();
    void editPesanan();
    void batalkanPesanan();
    void checkOut();
}
