import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Prvek spojoveho seznamu
 */
class Link {
    /** Data prvku - jeden znak */
    char data;
    /** Dalsi prvek spojoveho seznamu */
    Link next;
}

/**
 * Spojovy seznam znaku
 */
class LinkList {
    /**
     * Prvni prvek seznamu
     */
    Link first;

}
public class Main {
    /**
     * Vypise obsah seznamu
     */
    public static void printList(LinkList myList) throws Exception {
        MyIterator iterator=new MyIterator(myList);
        iterator.printList();
    }
/*
    public static void Nacteni() throws IOException {
        LinkList list = new LinkList();
        MyIterator iterator = new MyIterator(list);
        String radek;
        try (Scanner vstup = new Scanner(Paths.get("input.txt"))) {
            while (vstup.hasNextLine() && !(vstup.equals(""))) {
                radek = vstup.nextLine();
                while (iterator.hasNext()) {
                    iterator.next();
                }
                for (int i = (radek.length() - 1); i >= 0; i--) {
                    if (radek.charAt(i) == '\n' || radek.charAt(i) == '\r') {
                        continue;
                    }
                    iterator.insert(radek.charAt(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        iterator.moveToFirst();
        try (Scanner vstup1 = new Scanner(Paths.get("instructions.txt"))) {
            while (vstup1.hasNextLine() && !(vstup1.equals(""))) {
                radek = vstup1.nextLine();
                String[] parametry=radek.split(" ");
                if(parametry[0].equalsIgnoreCase("n")){
                    iterator.next();
                }
                if(parametry[0].equalsIgnoreCase("b")){
                    iterator.moveToFirst();
                }
                if(parametry[0].equalsIgnoreCase("i")){
                    iterator.insert(parametry[1].charAt(0));
                }
                if(parametry[0].equalsIgnoreCase("r")){
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        iterator.moveToFirst();
        try {
            FileWriter writer = new FileWriter("output.txt");
            while (iterator.hasNext()) {
                writer.write(iterator.get());
                iterator.next();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void Zapis(LinkList list,String soubor) {
        MyIterator it = new MyIterator(list);
        try {
            FileWriter writer = new FileWriter(soubor);
            while (it.hasNext()) {
                writer.write(it.get());
                it.remove();
                it.next();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
    public static void Nacti(String vstupni_soubor, MyIterator iterator) throws IOException {
        String radek;
        try (Scanner vstup = new Scanner(Paths.get(vstupni_soubor))) {
            while (vstup.hasNextLine() && !(vstup.equals(""))) {
                radek = vstup.nextLine();
                while (iterator.hasNext()) {
                    iterator.next();
                }
                for (int i = (radek.length() - 1); i >= 0; i--) {
                    if (radek.charAt(i) == '\n' || radek.charAt(i) == '\r') {
                        continue;
                    }
                    iterator.insert(radek.charAt(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void UpravSeznam(String instrukce,MyIterator iterator){
        iterator.moveToFirst();
        String radek;
        int pocet=0;
        try (Scanner vstup1 = new Scanner(Paths.get(instrukce))) {
            while (vstup1.hasNextLine() && !(vstup1.equals(""))) {
                radek = vstup1.nextLine();
                String[] parametry=radek.split(" ");
                if(parametry[0].equalsIgnoreCase("n")){
                    iterator.next();
                }
                if(parametry[0].equalsIgnoreCase("b")){
                    iterator.moveToFirst();
                }
                if(parametry[0].equalsIgnoreCase("i")){
                    iterator.insert(parametry[1].charAt(0));
                    //pocet++;
                }
                if(parametry[0].equalsIgnoreCase("r")){
                    iterator.remove();
                   // pocet--;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       //System.out.println(pocet);//Kontrola, kolik zanku se odebralo a kolik pridalo - sedi dle vstupu
    }

    public static void Vypis(String vystup,MyIterator iterator){
        iterator.moveToFirst();
        try {
            FileWriter writer = new FileWriter("output.txt");
            while (iterator.hasNext()) {
                writer.write(iterator.get());
                iterator.next();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        LinkList mylist=new LinkList();
        MyIterator iterator=new MyIterator(mylist);
        Nacti("input.txt",iterator);
       // iterator.printList();
        UpravSeznam("instructions.txt",iterator);
      //  iterator.printList(); //Pouze vypsani do konzole pro kontrolu
        Vypis("output.txt",iterator);
    }
}

class MyIterator{
    /** ukazovatko */
    Link current;
    /** reference seznamu */
    LinkList list;
    /**
     * Konstruktor
     * @param list seznam, v nemz se orientujeme
     */
    public MyIterator(LinkList list){
        this.list=list;
        this.current=null;//pred prvni prvek seznamu
    }

    /**
     * Vlozi novy znak do seznamu
     *
     * @param letter znak, ktery se vlozi do seznamu
     */
    void insert(char letter) {
        Link newLink = new Link();
        newLink.data = letter;
        if (current == null) {
            newLink.next = list.first;
            list.first = newLink;
        } else {
            newLink.next = current.next;//Chyba- prohozene radky, prvni se musi novy prvek napojit za zbytek seznamu
            current.next = newLink;//pak soucasny prvek napojit na novy prvek
        }
    }

    /**
     * Posune aktualni prvek na dalsi v seznamu
     *
     * @throws Exception pokud zadny dalsi prvek neni
     */
    void next() throws Exception {
        if (list.first == null) {
            throw new Exception();
        }
        if (current == null) {
            current = list.first;
        } else {
            current = current.next;
            if (current == null) {
                throw new Exception();
            }
        }
    }

    /**
     * Vrati znak v aktualnim prvku seznamu
     *
     * @return znak v aktualnim prvku seznamu
     */
    char get() throws Exception { //Chyba - chybelo throws Exception
        if (list.first == null) {
            throw new Exception();
        }
        if (current == null) {
            return list.first.data;
        }
        if (current.next != null) {
            return current.next.data;
        } else {
            throw new Exception();
        }
    }

    /**
     * Zmeni aktualni prvek na prvni prvek seznamu
     */
    void moveToFirst() {
        current = null;
    }

    /**
     * Vraci true, pokud existuje nasledujici prvek seznamu
     *
     * @return true, pokud existuje nasledujici prvek seznamu
     */
    boolean hasNext() {
        if (current == null) {
            if (list.first != null) {
                return true;
            } else {
                return false;
            }
        }
        if (current.next != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * odstrani prvek
     */
    void remove() throws Exception {
        if(list.first==null){
            throw new Exception();
        }
        if(current==null){//Cheme odstranit 1. prvek
            list.first=list.first.next;//1. prvek bude ukazovat na sveho nasledovnika
        }
        else if (current.next==null){//Jsme na konci seznamu
            throw new Exception();
        }
        else {//Vse v poradku
            current.next=current.next.next;//za prvek, ktery je nalevo od ukazovatka zaradime ten, co je napravo od odebraneho
        }
    }
    /**
     * Vypise seznam
     */
    void printList () throws Exception {
        moveToFirst();
        String s="";
        while(hasNext()){//Dokud ma dalsi radku - vypisuje
            s+=get();
            next();//nasledujici prvek
        }
        System.out.println(s);
    }
}