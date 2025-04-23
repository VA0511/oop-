package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;
import java.util.Scanner;

public class Aims {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Cart cart = new Cart();

    public static void main(String[] args) {
        storeMenu();
    }

    public static void showMenu() {
        System.out.println("AIMS:");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2: ");
    }

    public static void storeMenu() {
        // Fake data
        DigitalVideoDisc dvd = new DigitalVideoDisc("Avengers", "Action", "Marvel", 120, 24.99f);
        Book book = new Book("Effective Java", "Programming", 35.5f);
        book.addAuthor("Joshua Bloch");
        CompactDisc cd = new CompactDisc("Classical Collection", "Music", 18.0f, "Mozart");
        cd.addTrack(new Track("Symphony No. 40", 8));

        boolean running = true;
        while (running) {
            showMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1 -> {
                    System.out.println("Store contains: ");
                    System.out.println("1. " + dvd);
                    System.out.println("2. " + book);
                    System.out.println("3. " + cd);
                    System.out.print("Choose an item to add to cart (1-3), or 0 to back: ");
                    int pick = scanner.nextInt();
                    switch (pick) {
                        case 1 -> cart.addMedia(dvd);
                        case 2 -> cart.addMedia(book);
                        case 3 -> cart.addMedia(cd);
                    }
                }
                case 2 -> cartMenu();
                case 0 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    public static void cartMenu() {
        boolean cartOpen = true;
        while (cartOpen) {
            System.out.println("********** CART MENU **********");
            System.out.println("1. View cart items");
            System.out.println("2. Sort by title then cost");
            System.out.println("3. Sort by cost then title");
            System.out.println("4. Search by title");
            System.out.println("5. Search by ID");
            System.out.println("6. Place order");
            System.out.println("0. Back to main menu");
            System.out.print("Choose option: ");
            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 1 -> cart.printCart();
                case 2 -> cart.sortByTitleCost();
                case 3 -> cart.sortByCostTitle();
                case 4 -> {
                    System.out.print("Enter title to search: ");
                    String title = scanner.nextLine();
                    Media m = cart.searchByTitle(title);
                    System.out.println(m == null ? "Not found." : m);
                }
                case 5 -> {
                    System.out.print("Enter ID to search: ");
                    int id = scanner.nextInt();
                    Media m = cart.searchById(id);
                    System.out.println(m == null ? "Not found." : m);
                }
                case 6 -> {
                    cart.printCart();
                    System.out.println("Placing order...");
                    cart.clearCart();
                }
                case 0 -> cartOpen = false;
                default -> System.out.println("Invalid option");
            }
        }
    }
}
