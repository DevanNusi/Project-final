package PertaCalcApp.repositories;

import PertaCalcApp.config.Database;
import PertaCalcApp.entities.Pelanggan;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


@Component
public class PelangganRepositoriesImpl  implements PelangganRepositories{

    private final Database database;

    public PelangganRepositoriesImpl(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Pelanggan> ambilDataPelanggan() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM pelanggan";
        ArrayList<Pelanggan> listDataPelanggan = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Pelanggan barisData = new Pelanggan();

                String nama = resultSet.getString(1);
                int pembayaran = resultSet.getInt(2);
                int banyakBensin = resultSet.getInt(3);
                String jenisBensin = resultSet.getString(4);
                String metodePembayaran = resultSet.getString(5);
                String kritik = resultSet.getString(6);
                int id = resultSet.getInt(7);


                barisData.setNama(nama);
                barisData.setTotalPembayaran(pembayaran);
                barisData.setBanyakBensin(banyakBensin);
                barisData.setJenisBensin(jenisBensin);
                barisData.setMetodePembayaran(metodePembayaran);
                barisData.setKritikDanSaran(kritik);
                barisData.setId(id);

                listDataPelanggan.add(barisData);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return listDataPelanggan;
    }

    @Override
    public void simpanDataPelanggan(Pelanggan dataPelanggan) {
        Connection connection = database.getConnection();
        String sqlStatement = "INSERT INTO pelanggan(nama,nominal_pembayaran,jumlah_bensin,jenis_bensin,metode_pembayaran,kritik) VALUES(?,?,?,?,?,?)";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1,dataPelanggan.getNama());
            preparedStatement.setInt(2,dataPelanggan.getTotalPembayaran());
            preparedStatement.setInt(3,dataPelanggan.getBanyakBensin());
            preparedStatement.setString(4,dataPelanggan.getJenisBensin());
            preparedStatement.setString(5,dataPelanggan.getMetodePembayaran());
            preparedStatement.setString(6,dataPelanggan.getKritikDanSaran());


            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Insert successful !");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
