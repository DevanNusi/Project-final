package PertaCalcApp.services;

import PertaCalcApp.entities.Pelanggan;
import PertaCalcApp.entities.Stock;
import org.springframework.stereotype.Component;
import PertaCalcApp.repositories.PelangganRepositoriesImpl;
import PertaCalcApp.repositories.StokRepositoriesImpl;

import java.util.ArrayList;
import java.util.Scanner;

@Component
public class TransactionServicesImpl implements TransactionServices{
    private static Scanner input = new Scanner(System.in);
    private static Pelanggan dataPelangganSementara = new Pelanggan();
    private static int barisBensin;
    PelangganRepositoriesImpl pelangganRepositories;
    StokRepositoriesImpl stokRepositories;

    public TransactionServicesImpl(PelangganRepositoriesImpl pelangganRepositories, StokRepositoriesImpl stokRepositories) {
        this.pelangganRepositories = pelangganRepositories;
        this.stokRepositories = stokRepositories;
    }

    @Override
    public ArrayList<Pelanggan> berikanDataPelanggan() {
        return pelangganRepositories.ambilDataPelanggan();
    }

    @Override
    public void pesanBensin() {
        ArrayList<Stock> listDataStock = stokRepositories.ambilDataStock();

        if(!listDataStock.isEmpty()){
            System.out.print("pilih jenis bensin yang tersedia : ");
            barisBensin = input.nextInt() -1;
            dataPelangganSementara.setJenisBensin(listDataStock.get(barisBensin).nama);

            System.out.print("Masukan nama Anda : ");
            input.nextLine();
            dataPelangganSementara.setNama(input.nextLine());

            System.out.print("Masukan metode pembayaran bensin : ");
            dataPelangganSementara.setMetodePembayaran(input.nextLine());

            System.out.print("Masukan banyak Liter Bensin yang ingin di beli : ");
            int banyakBensin = input.nextInt();
            dataPelangganSementara.setBanyakBensin(banyakBensin);

            dataPelangganSementara.setTotalPembayaran(banyakBensin * listDataStock.get(barisBensin).harga);
        }else {
            System.out.println("Stock bensin masih belum tersedia !!");
        }
    }

    @Override
    public void editPesanan() {
        if(dataPelangganSementara.getNama() != null){
            batalkanPesanan();
            pesanBensin();
        }else {
            System.out.println("Anda Masih Belum Memesan !!!");
        }
    }

    @Override
    public void batalkanPesanan() {
        dataPelangganSementara = new Pelanggan();
    }

    @Override
    public void checkOut() {

        if(dataPelangganSementara.getNama() != null){
            masukanKritikDanSaran();
            kurangiStokBensin();
            pelangganRepositories.simpanDataPelanggan(dataPelangganSementara);
        }else {
            System.out.println("Anda Masih Belum Memesan !!!");
        }

    }

    private void kurangiStokBensin(){
        Stock tmp = stokRepositories.ambilDataStock().get(barisBensin);
        tmp.setStock(tmp.getStock() - dataPelangganSementara.getBanyakBensin());
        if(tmp.getStock() == 0){
            stokRepositories.hapusData(barisBensin);
        }else {
            stokRepositories.editData(tmp,barisBensin);
        }
    }

    private void masukanKritikDanSaran() {
        System.out.print("Masukan Kritik dan Saran anda : ");
        input.nextLine();
        dataPelangganSementara.setKritikDanSaran(input.nextLine());
    }


}
