package PertaCalcApp;

import PertaCalcApp.config.Database;
import PertaCalcApp.views.Menus;
import PertaCalcApp.views.TerminalViews;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("PertaCalcApp")
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Menus terminalViews = context.getBean(TerminalViews.class);
        terminalViews.run();
    }

    @Bean
    Database database (){
        Database database = new Database("bisnis_penjualan_bensin", "root","", "localhost","3306");
        database.setup();
        return database;
    }
}




