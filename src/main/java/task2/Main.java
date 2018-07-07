package task2;

import java.util.Locale;

/*
Создать “универсальный” класс, позволяющий получить значение из любого properties- файла.
Физическое чтение файла должно происходить только один раз. Учтите ситуацию, когда
несколько потоков одновременно обращаются к одному и тому же файлу.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Locale locale = new Locale("de");
            PropertiesManagerTS worker1 = new PropertiesManagerTS("MyBundle", locale);
            PropertiesManagerTS worker2 = new PropertiesManagerTS("MyBundle", locale);
            PropertiesManagerTS worker3 = new PropertiesManagerTS("MyBundle", locale);
            worker1.start();
            worker2.start();
            worker3.start();
            worker1.join();
            worker2.join();
            worker3.join();
        } catch (NoPropFileException e) {
            System.out.println(e.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

