package PertaCalcApp.repositories;

import PertaCalcApp.entities.Pelanggan;

import java.util.ArrayList;

public interface PelangganRepositories {
    ArrayList<Pelanggan> ambilDataPelanggan();
    void simpanDataPelanggan(Pelanggan dataPelanggan);

}
