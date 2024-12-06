package PertaCalcApp.services;

import PertaCalcApp.entities.Stock;
import org.springframework.stereotype.Component;
import PertaCalcApp.repositories.StokRepositoriesImpl;

import java.util.ArrayList;
import java.util.Scanner;


@Component
public class AdminServicesImpl implements AdminServices{
    private static Scanner input = new Scanner(System.in);
    StokRepositoriesImpl stokRepositories;

    public AdminServicesImpl(StokRepositoriesImpl stokRepositories) {
        this.stokRepositories = stokRepositories;
    }

    @Override
    public ArrayList<Stock> berikanDataStock() {
        return stokRepositories.ambilDataStock();
    }

    @Override
    public void tambahDataStock() {
        stokRepositories.tambahData(inputdata(true));
    }

    private static Stock inputdata(boolean isAdd){
        Stock data = new Stock();
        System.out.print("masukan nama stok : ");
        if (!isAdd) {
            input.nextLine();
        }
        data.nama = input.nextLine();
        System.out.print("masukan harga : ");
        data.harga = input.nextInt();
        System.out.print("masukan stok : ");
        data.stock = input.nextInt();
        return data;
    }

    @Override
    public void deleteDataStock() {
        ArrayList<Stock> kumpulanDataStock = stokRepositories.ambilDataStock();

        if (!kumpulanDataStock.isEmpty()){
            System.out.print("masukan baris data yg mau dihapus : ");
            int index = input.nextInt() -1;
            stokRepositories.hapusData(index);
        } else {
            System.out.println("data masih kosong");
        }

    }

    @Override
    public void editDataStock() {
        ArrayList<Stock> kumpulanDataStock = stokRepositories.ambilDataStock();
        if(!kumpulanDataStock.isEmpty()){
            System.out.print("masukan baris data yg mau di edit : ");
            int index = input.nextInt() -1;
            stokRepositories.editData(inputdata(false),index);
        } else {
            System.out.println("data masih kosong");
        }
    }

}
