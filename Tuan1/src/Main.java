import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        List<Book> listBook = new ArrayList<>();
        Scanner x = new Scanner(System.in);
        String msg = """
                Chuong trinh quan ly sach
                    1.Them 1 cuon sach
                    2.Xoa 1 cuon sach
                    3.Thay doi sach
                    4.Xuat thong tin 
                    5.Tim kiem lap trinh
                    6.Lay Sach toi da theo gia
                    7.Tim kiem tac gia
                    0.Thoat              
              
                              """;
        int chon = 0;
        do {
            System.out.print(msg);
            chon = x.nextInt();
            switch (chon) {
                case 1 -> {
                    Book newBook = new Book();
                    newBook.input();
                    listBook.add(newBook);
                }
                case 2 -> {
                    System.out.print("Nhap ma can xoa");
                    int bookId = x.nextInt();

                    Book findBook = listBook.stream().filter(p -> p.getId() == bookId).findFirst().orElseThrow();
                    listBook.remove(findBook);
                    System.out.println("Da xoa sach thanh cong");
                }
                case 3 -> {
                    System.out.println("Nhap ma sach can dieu chinh");
                    int bookId = x.nextInt();
                    Book findBook = listBook.stream().filter(p -> p.getId() == bookId).findFirst().orElseThrow();

                }
                case 4 -> {
                    System.out.println(" Xuat danh sach san pham");
                    listBook.forEach(p -> p.output());
                }
                case 5 -> {
                    List<Book> list5 = listBook.stream()
                            .filter(u -> u.getTitle().toLowerCase().contains("lap trinh"))
                            .toList();
                    list5.forEach(Book::output);
                }
                case 6 -> {
                    if (listBook.isEmpty()) {
                        System.out.println("Danh sach rong!");
                        break;
                    }

                    double maxPrice = listBook.stream()
                            .mapToDouble(Book::getPrice)
                            .max()
                            .orElse(0);

                    System.out.println("Sach co gia cao nhat:");

                    listBook.stream()
                            .filter(b -> b.getPrice() == maxPrice)
                            .limit(5)
                            .forEach(Book::output);
                }
                case 7 -> {
                    x.nextLine();

                    Set<String> authorSet = new HashSet<>();

                    System.out.println("Nhap ten tac gia (nhap '0' de dung):");

                    while (true) {
                        String author = x.nextLine();
                        if (author.equals("0")) break;
                        authorSet.add(author.toLowerCase());
                    }

                    System.out.println("Ket qua tim kiem:");

                    listBook.stream()
                            .filter(b -> authorSet.contains(b.getAuthor().toLowerCase()))
                            .forEach(Book::output);
                }


            }

        }while (chon !=0);

    }

}