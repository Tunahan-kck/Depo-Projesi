package runner;

import service.DepoService;
import java.util.Scanner;

public class DepoRunner {

    public static void main(String[] args) {

        DepoService depo = new DepoService();
        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("===================================");
            System.out.println("       DEPO YÖNETİM SİSTEMİ");
            System.out.println("===================================");
            System.out.println("1- Ürün Tanımla");
            System.out.println("2- Ürün Listele");
            System.out.println("3- Ürün Girişi");
            System.out.println("4- Ürünü Rafa Koy");
            System.out.println("5- Ürün Çıkışı");
            System.out.println("0- Çıkış");
            System.out.print("Seçiminiz: ");

            String secimStr = input.nextLine();

            if (!secimStr.matches("\\d+")) {
                System.out.println("HATALI GİRİŞ! Lütfen sayı girin.\n");
                continue;
            }

            int secim = Integer.parseInt(secimStr);

            switch (secim) {

                case 1 -> depo.urunTanimlama();
                case 2 -> depo.urunListele();
                case 3 -> depo.urunGirisi();
                case 4 -> depo.urunuRafaKoy();
                case 5 -> depo.urunCikisi();
                case 0 -> {
                    System.out.println("Çıkış...");
                    System.exit(0);
                }
                default -> System.out.println("Hatalı seçim!\n");
            }
        }
    }
}