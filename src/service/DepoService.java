package service;

import model.Urun;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DepoService {

    private Scanner input = new Scanner(System.in);
    private Map<Integer, Urun> urunMap = new HashMap<>();
    private static int idCounter = 1000;

    private int generateId() {
        return idCounter++;
    }

    // === 0 ve ÜSTÜ DEĞER KABUL ===
    private int safeNonNegativeInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                int val = Integer.parseInt(input.nextLine());

                if (val < 0) {
                    System.out.println("HATA: Miktar NEGATİF olamaz!\n");
                    continue;
                }
                return val; // 0 ve pozitif kabul edilir

            } catch (Exception e) {
                System.out.println("HATALI GİRİŞ! Lütfen sadece SAYI girin.\n");
            }
        }
    }

    // === GÜVENLİ STRING ===
    private String safeString(String message) {
        while (true) {
            System.out.print(message);
            String val = input.nextLine().trim();
            if (!val.isEmpty()) return val;

            System.out.println("HATALI GİRİŞ! Boş bırakılamaz.\n");
        }
    }

    // === 1. ÜRÜN TANIMLAMA ===
    public void urunTanimlama() {

        System.out.println("\n=== ÜRÜN TANIMLAMA ===");

        String isim = safeString("Ürün ismi: ");
        String uretici = safeString("Üretici: ");
        String birim = safeString("Birim (kg, adet, çuval): ");

        int miktar = safeNonNegativeInt("Miktar (0 olabilir): ");

        int id = generateId();

        Urun urun = new Urun(id, isim, uretici, birim);
        urun.setMiktar(miktar);

        urunMap.put(id, urun);

        System.out.println("Ürün başarıyla eklendi! ID: " + id + "\n");
    }

    // === 2. LİSTELE ===
    public void urunListele() {

        System.out.println("\n=== ÜRÜN LİSTESİ ===");

        if (urunMap.isEmpty()) {
            System.out.println("Listede ürün yok.\n");
            return;
        }

        System.out.printf("%-6s %-12s %-12s %-8s %-10s %-6s\n",
                "ID", "İsim", "Üretici", "Miktar", "Birim", "Raf");
        System.out.println("------------------------------------------------------");

        for (Urun u : urunMap.values()) {
            System.out.println(u);
        }
        System.out.println();
    }

    // === 3. GİRİŞ ===
    public void urunGirisi() {

        System.out.println("\n=== ÜRÜN GİRİŞİ ===");

        int id = safeNonNegativeInt("Ürün ID: ");

        if (!urunMap.containsKey(id)) {
            System.out.println("HATA: Bu ID yok!\n");
            return;
        }

        int miktar = safeNonNegativeInt("Eklenecek miktar (0 olabilir): ");

        Urun u = urunMap.get(id);
        u.setMiktar(u.getMiktar() + miktar);

        System.out.println("Stok güncellendi! Yeni miktar: " + u.getMiktar() + "\n");
    }

    // === 4. RAFA KOY ===
    public void urunuRafaKoy() {

        System.out.println("\n=== RAFA ÜRÜN KOY ===");

        int id = safeNonNegativeInt("Ürün ID: ");

        if (!urunMap.containsKey(id)) {
            System.out.println("HATA: Bu ID yok!\n");
            return;
        }

        String raf = safeString("Raf adı: ");

        urunMap.get(id).setRaf(raf);

        System.out.println("Ürün rafa yerleştirildi!\n");
    }

    // === 5. ÇIKIŞ ===
    public void urunCikisi() {

        System.out.println("\n=== ÜRÜN ÇIKIŞI ===");

        int id = safeNonNegativeInt("Ürün ID: ");

        if (!urunMap.containsKey(id)) {
            System.out.println("HATA: Bu ID yok!\n");
            return;
        }

        Urun u = urunMap.get(id);

        int miktar = safeNonNegativeInt("Çıkış miktarı (0 olabilir): ");

        if (miktar > u.getMiktar()) {
            System.out.println("HATA: Stok yetersiz! Mevcut: " + u.getMiktar() + "\n");
            return;
        }

        u.setMiktar(u.getMiktar() - miktar);

        System.out.println("Çıkış başarılı! Kalan: " + u.getMiktar() + "\n");
    }
}