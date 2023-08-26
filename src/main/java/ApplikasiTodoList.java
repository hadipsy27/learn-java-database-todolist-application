import java.util.Scanner;

public class ApplikasiTodoList {

    public static String[] model = new String[10];
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        viewShowTodoList();
    }

    /**
     * Menampilkan todo list
     */
    public static void showTodoList(){
        System.out.println("TODO LIST");
        for (int i = 0; i < model.length; i++) {
            var todo = model[i];
            var no = i+1;

            if(todo != null){
                System.out.println(no + ". " + todo);
            }
        }
    }

    public static void testShowTodoList(){

        model[0] = "Belajar Java Dasar";
        model[1] = "Makan";
        model[2] = "Tidur";
        model[3] = "Bangun dari Tidur";
        model[4] = "Tidur Lagi";

        showTodoList();
    }

    /**
     * Menambah todo ke list
     */
    public static void addTodoList(String todo){

        // cek apakah model penuh?
        var isFull = true;
        for (int i = 0; i < model.length; i++) {
            if (model[i] == null){
                isFull = false;
                break;
            }
        }

        // jika model penuh maka resize ke ukuran 2x lipat
        if (isFull){
            var temp = model;
            model = new String[model.length * 2];

            for (int i = 0; i < temp.length; i++) {
                model[i] = temp[i];
            }
        }

        // tambahkan ke array yang NULL
        for (int i = 0; i < model.length; i++) {
            if(model[i] == null){
                model[i] = todo;
                break;
            }
        }
    }

    public static void testAddTodoList(){
        for (int i = 1; i <= 25; i++) {
            addTodoList("Contoh todo list ke - " + i);
        }

        showTodoList();
    }

    /**
     * Menghapus todo dari list
     */
    public static boolean removeTodoList(Integer number){
        if((number - 1) >= model.length){
            return false;
        } else if (model[number - 1] == null) {
            return false;
        } else {
            for (int i = (number - 1); i  < model.length; i++) {
                if(i == (model.length - 1)){
                    model[i] = null;
                } else {
                    model[i] = model[i + 1];
                }
            }
            return true;
        }
    }

    public static void testRemoveTodoList(){
        addTodoList("satu");
        addTodoList("dua");
        addTodoList("tiga");

        var result = removeTodoList(20);
        System.out.println(result);

        result = removeTodoList(5);
        System.out.println(result);

        result = removeTodoList(2);
        System.out.println(result);

        showTodoList();
    }

    public static String input(String info){
        System.out.print(info + " : ");
        String data = scanner.nextLine();
        return data;
    }

    public static void testInput(){
        var name = input("Nama");
        System.out.println("Hi " + name);

        var alamat = input("Alamat");
        System.out.println(alamat);
    }

    /**
     * Menampilkan view todo list
     */
    public static void viewShowTodoList(){
        while (true){
            showTodoList();

            System.out.println("MENU :");
            System.out.println("1. Tambah");
            System.out.println("2. Hapus");
            System.out.println("x. Keluar");

            var input = input("Pilih");

            if (input.equals("1")){
                viewAddTodoList();
            } else if(input.equals("2")){
                viewRemoveTodoList();
            } else if(input.equals("x")){
                break;
            }else {
                System.out.println("Piihan tidak dimengerti!");
            }
        }
    }

    public static void testViewShowTodoList(){
        addTodoList("satu");
        addTodoList("dua");
        addTodoList("tiga");
        addTodoList("empat");
        addTodoList("lima");

        viewShowTodoList();
    }

    /**
     * Menampilkan view manambahkan todo list
     */
    public static void viewAddTodoList(){
        System.out.println("MENAMBAH TODOLIST");
        var todo = input("Todo (x jika batal)");

        if(todo.equals("x")){
            // Batal
        } else {
            addTodoList(todo);
        }
    }

    public static void testViewAddTodoList(){
        addTodoList("satu");
        addTodoList("dua");
        addTodoList("tiga");

        viewAddTodoList();
        showTodoList();
    }

    /**
     * Menampilkan view menghapus todo list
     */
    public static void viewRemoveTodoList(){
        System.out.println("MENGHAPUS TODOLIST");

        var number = input("Nomor yang dihapus (x jika batal)");

        if(number.equals("x")){
            //batal
        } else {
            boolean success = removeTodoList(Integer.valueOf(number));
            if(!success){
                System.out.println("Gagal Menghapus todolist " + number);
            }
        }
    }

    public static void testViewRemoveTodoList(){
        addTodoList("satu");
        addTodoList("dua");
        addTodoList("tiga");

        showTodoList();

        viewRemoveTodoList();

        showTodoList();
    }
}