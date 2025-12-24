import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        // Test 1
            ElementsOfStatistic list = new ElementsOfStatistic();
            list.addNumber(5);
            list.addNumber(3);
            list.addNumber(7);
            list.addNumber(3);
            list.addNumber(2);
            list.printAllCharacteristics();

        // Test 2
            ElementsOfStatistic list1 = new ElementsOfStatistic();
            list1.addNumber(5.5);
            list1.addNumber(4.4);
            list1.addNumber(3.3);
            list1.addNumber(2.2);
            list1.addNumber(1.1);
            list1.printAllCharacteristics();



        // Test 3
        {
            ElementsOfStatistic list2 = new ElementsOfStatistic();
            list2.addNumber(0);
            list2.addNumber(0);
            list2.addNumber(1);
            list2.addNumber(31);
            list2.addNumber(1);
            list2.printAllCharacteristics();
        }


    }
}
class ElementsOfStatistic {
    //создание полей
    private double[] numbers;
    private int count;
    // 1. Создание пустого списка
    public ElementsOfStatistic() {
        numbers = new double[100];
        count = 0;
    }
    // Вспомогательный метод для получения отсортированной копии
    private double[] getSortedCopy() {
        double[] a = new double[count];
        for (int i = 0; i < count; i++) {
            a[i] = numbers[i];
        }
        // Сортировка пузырьком
        for (int j = 0; j < a.length - 1; ++j)
            for (int i = 0; i < a.length - j - 1; ++i) {
                double y = 0;
                if (a[i] > a[i + 1]) {
                    y = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = y;
                }
            }
        return a;
    }
    // 2. Вывод по возрастанию
    public void SortPoVozrastaniyu() {
        double []a = getSortedCopy();
        System.out.print("[");
        for (int u = 0; u < count   ; u++) {
            System.out.printf("%.2f", a[u]);
            if (u < count - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }
    // 3. Добавление числа с сохранением порядка
    public void addNumber(double num) {
        if (count >= numbers.length) {
            double[] newArr = new double[numbers.length * 2];
            for (int i = 0; i < count; i++)
                newArr[i] = numbers[i];
            numbers = newArr;
        }
        int pos = 0;
        while (pos < count && numbers[pos] < num)
            pos++;
        for (int i = count; i > pos; i--)
            numbers[i] = numbers[i - 1];
        numbers[pos] = num;
        count++;
    }
    // 4. Вывод по убыванию
    public void SortPoUbivaniyu() {
        double []a = getSortedCopy();
        System.out.print("[");
        for (int u = count-1; u > -1 ; u--) {
            System.out.printf("%.2f", a[u]);
            if (u > 0)
                System.out.print(", ");
        }
        System.out.println("]");
    }
    // 5. Вывод частотного ряда
    public void Chastnoe() {
        double[] a = getSortedCopy();
        int l = 0;
        while (l < count) {
            double current = a[l];
            int freq = 0;
            while (l < count &&a[l] == current) {
                freq++;
                l++;
            }
            System.out.printf("%.2f - %d раз(а)\n", current, freq);
        }
    }
    // 6. Размах
    public double range() {
        if (count == 0)
            return 0;
        double min = numbers[0];
        double max = numbers[0];
        for (int i = 1; i < count; i++) {
            if (numbers[i] < min)
                min = numbers[i];
            if (numbers[i] > max)
                max = numbers[i];
        }
        return max - min;
    }
    // 7. Среднее арифметическое
    public double sredneeArifm() {
        if (count == 0)
            return 0;
        double sum = 0;
        for (int i = 0; i < count; i++)
            sum += numbers[i];
        return sum / count;
    }
    // 8. Среднее геометрическое
    public double sredneeGeom() {
        double product = 1;
        for (int i = 0; i < count; i++)
            product *= numbers[i];
        if (count == 0 || product < 0)
            return 0;
        return Math.pow(product, 1.0 / count);
    }
    // 9. Среднее гармоническое
    public double sredneeGarm() {
        if (count == 0)
            return 0;
        double sum = 0;
        for (int i = 0; i < count; i++) {
            if (numbers[i] == 0)
                return 0;
            sum += 1.0 / numbers[i];
        }
        return count / sum;
    }
    // 10. Мода
    public void Mode() {
        double[] a = getSortedCopy();
        double modlist[] = new double[a.length];
        double bestNum = a[0];
        int modcount = 0;
        int best = 1;
        int current = 1;
        boolean hasMode = false;
        for (int i = 1; i < count; i++) {
            if (a[i] == a[i-1])
                current++;
            else
                current = 1;
            if (current >= best) {
                best = current;
                bestNum = a[i];
                hasMode = true;
                modlist[i] = best; //массив записывает новые моды
                modcount+=1;
            }
            else
                modcount = 0;
        }
        if (best > 1 && hasMode && modcount == 1)
            System.out.printf("Мода: %.2f (встречается %d раз)\n", bestNum, best);
        else
            if (modcount == 0)
                System.out.println("Моды нет");
            else {
                System.out.println("Значения моды: ");
                for (int i = 0; i < modlist.length ; i++) {
                    if (modlist[i]==best) // проверка на финальную моду, сопоставление со значением в массиве `a`
                        System.out.printf(" %2.f", a[i]);
                }
                System.out.printf("\n Встречаются по %d раз",best);
            }
    }
    // 11. Медиана
    public double median() {
        if (count == 0)
            return 0;
        double[] a = getSortedCopy();
        if (count % 2 == 1)
            return a[count / 2];
        else
            return (a[count / 2 - 1] + a[count / 2]) / 2.0;
    }
    // 12. Дисперсия
    public double dispersiya() {
        if (count == 0)
            return 0;
        double m = sredneeArifm();
        double sum = 0;
        for (int i = 0; i < count; i++)
            sum += (numbers[i] - m) * (numbers[i] - m);
        return sum / count;
    }
    // 13. Среднеквадратичное отклонение
    public double srednekvadratichnoeOtklonenie() {
        return Math.sqrt(dispersiya());
    }
    // 14. Дисперсия альтернативного признака (по чётности)
    public double alternativeDispersiya() {
        if (count == 0)
            return 0;
        int e = 0;
        for (int i = 0; i < count; i++)
            if (numbers[i] % 2 == 0)
                e++;
        double p = (double) e / count;
        return p * (1 - p);
    }
    // 15. Вывод всех характеристик
    public void printAllCharacteristics() {
        System.out.println("Количество чисел: " + count);
        System.out.print("Числа: ");
        SortPoVozrastaniyu();
        System.out.print("По убыванию:");
        SortPoUbivaniyu();
        System.out.println("Частное ряда:");
        Chastnoe();
        System.out.printf("Размах: %.4f\n", range());
        System.out.printf("Среднее арифметическое: %.4f\n", sredneeArifm());
        System.out.printf("Среднее геометрическое: %.4f\n", sredneeGeom());
        System.out.printf("Среднее гармоническое: %.4f\n", sredneeGarm());
        Mode();
        System.out.printf("Медиана: %.4f\n", median());
        System.out.printf("Дисперсия: %.4f\n", dispersiya());
        System.out.printf("Среднеквадр. отклонение: %.4f\n", srednekvadratichnoeOtklonenie());
        System.out.printf("Дисперсия альтернативная: %.4f\n", alternativeDispersiya());
    }
}
