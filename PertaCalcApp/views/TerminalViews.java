package PertaCalcApp.views;

import PertaCalcApp.entities.Pelanggan;
import PertaCalcApp.entities.Stock;
import org.springframework.stereotype.Component;
import PertaCalcApp.services.AdminServicesImpl;
import PertaCalcApp.services.TransactionServicesImpl;

import java.util.ArrayList;
import java.util.Scanner;


@Component
public class TerminalViews implements Menus{
    private static Scanner input = new Scanner(System.in);

    AdminServicesImpl adminServices;
    TransactionServicesImpl transactionServices;

    public TerminalViews(AdminServicesImpl adminServices, TransactionServicesImpl transactionServices) {
        this.adminServices = adminServices;
        this.transactionServices = transactionServices;
    }

    @Override
    public void run() {
        loginMenu();
    }

    private void loginMenu() {
        while (true){
            System.out.println("SELAMAT DATANG DI PERTACALC");
            System.out.println("1. Admin");
            System.out.println("2. Pembeli");
            System.out.println("3. keluar menu");
            System.out.print("Masukan Pilhan anda : ");
            int pilihan = input.nextInt();
            switch (pilihan){
                case 1:
                    adminMenu();
                    break;
                case 2:
                    customerMenu();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Menu yang anda input tidak tersedia !!!");
                    break;
            }
        }
    }

    private void customerMenu(){
        while (true){
            System.out.println("SELAMAT BERBELANJA !!!!\n\n");
            System.out.println("1.Memesan Bensin");
            System.out.println("2.Mengedit Pesanan");
            System.out.println("3.Hapus Pesanan");
            System.out.println("4.Bayar Pesanan");
            System.out.print("Masukan Pilihan Anda : ");
            int pilihan = input.nextInt();

            switch (pilihan){
                case 1:
                    lihatDataStock();
                    transactionServices.pesanBensin();
                    break;
                case 2:
                    lihatDataStock();
                    transactionServices.editPesanan();
                    break;
                case 3:
                    transactionServices.batalkanPesanan();
                    break;
                case 4:
                    transactionServices.checkOut();
                    return;
                default:
                    System.out.println("Menu Yang anda input tidak tersedia !!!");
                    break;
            }
        }
    }

    private void lihatDataPelanggan(){
        ArrayList<Pelanggan>listDataPelanggan = transactionServices.berikanDataPelanggan();

        if(!listDataPelanggan.isEmpty()){
            int counter = 1;
            System.out.println("No.\tNama\tJenisBensin\tjumlahBensin\tMetodePembayaran\tTotal\tKritik&Saran");
            for (Pelanggan i : listDataPelanggan){
                System.out.println(counter + ".\t" + i.getNama() + "\t\t" + i.getJenisBensin() + "\t\t" + i.getBanyakBensin() + "\t\t\t\t" + i.getMetodePembayaran() + "\t\t\t" +i.getTotalPembayaran() + "\t\t" + i.getKritikDanSaran());
                counter++;
            }
        }else {
            System.out.println("Data Pelanggan Masih belum tersedia !!");
        }
    }

    private void lihatDataStock() {
        ArrayList<Stock> listDataStock = adminServices.berikanDataStock();

        if(!listDataStock.isEmpty()){
            System.out.println("no\tnama\tharga\tstok");
            int nomor = 1;
            for (Stock data : listDataStock){
                System.out.println(nomor + ".\t" + data.getNama() +"\t" + data.getHarga() + "\t" + data.getStock());
                nomor++;
            }
        } else {
            System.out.println("data masih kosong");
        }
    }

    private void adminMenu(){
        while (true) {
            System.out.println("selamat datang di menu admin\n");
            System.out.println("1.tambah produk");
            System.out.println("2.hapus produk");
            System.out.println("3.edit produk");
            System.out.println("4. tampilkan produk");
            System.out.println("5. tampilkan pesanan");
            System.out.println("6. keluar Menu");
            System.out.print("masukan pilihan anda : ");
            int pilihan = input.nextInt();
            switch (pilihan) {
                case 1:
                    adminServices.tambahDataStock();
                    break;
                case 2:
                    lihatDataStock();
                    adminServices.deleteDataStock();
                    break;
                case 3:
                    lihatDataStock();
                    adminServices.editDataStock();
                    break;
                case 4:
                    lihatDataStock();
                    break;
                case 5:
                    lihatDataPelanggan();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("pilihan yg anda masukan salah");
                    break;
            }

        }

    }

}
