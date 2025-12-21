## Отчёт по лабораторной работе № 3

#### № группы: ПМ-2501

#### Выполнил: Замиралов Григорий Олегович

#### Вариант: 8

---
### Содержание

- [1. Постановка задачи](#1-постановка-задачи)
- [2. Математическая модель](#2-математическая-модель)
- [3. Выбор структуры данных](#3-выбор-структуры-данных)
- [4. Программа](#4-программа)
- [5. Анализ правильности решения](#5-анализ-правильности-решения)

---
## Элементы статистики

### 1. Постановка задачи

**Условие**
>Разработать программу для работы с числовыми рядами, 
> включая их создание, сортировку и анализ. Реализовать 
> функции вычисления статистических показателей, таких
> как средние значения, мода, медиана, дисперсия и другие метрики.

**Описание функционала**

> 1. Создание списка чисел
> Создание пустого списка чисел, предназначенного для анализа.
> 2. Вывод отсортированного списка (по возрастанию)
> Отображение списка чисел в порядке неубывания.
> 3. Добавление числа в список
> Вставка нового числа в список с автоматическим сохранением порядка (по возрастанию).
> 4. Вывод отсортированного списка (по убыванию)
> Отображение списка чисел в порядке невозрастания.
> 5. Вывод частотного ряда
> Отображение списка чисел в формате: «число - количество раз, которое оно встречается».
> 6. Размах числового ряда
> Вычисление размаха как разности между максимальным и минимальным элементами.
> 7. Среднее арифметическое
> Вычисление среднего арифметического всех чисел в списке.
> 8. Среднее геометрическое
> Вычисление среднего геометрического чисел, используя соответствующую формулу.
> 9. Среднее гармоническое
> Вычисление среднего гармонического чисел в списке.
> 10. Нахождение моды
> Определение самого часто встречающегося числа. Если все числа встречаются
> одинаковое количество раз, моды нет. Если несколько чисел имеют одинаковую
> максимальную частоту, они образуют набор мод.
> 11. Нахождение медианы
> Определение медианы:
> • Если количество чисел нечётное, медиана — это число посередине.
> • Если количество чисел чётное, медиана равна среднему арифметическому
> двух центральных чисел.
> 12. Вычисление дисперсии
> Расчёт дисперсии d, равной среднему квадрату отклонений чисел от их среднего
> арифметического.
> 13. Среднеквадратичное отклонение
> Вычисление среднеквадратичного отклонения σ как квадратного корня из дисперсии d.
> 14. Дисперсия альтернативного признака
> Расчёт дисперсии альтернативного признака, например, по чётности чисел (чётное
> или нечётное).
> 15. Вывод всех характеристик ряда
> Отображение всех вычисленных характеристик (размах, средние, мода, медиана,
> дисперсия, среднеквадратичное отклонение и др.) в удобном и читаемом формате.

      
### 2. Математическая модель
> Для выполнения поставленной задачи не требуется использование и построение
> сложных математических моделей. По ходу программы мы работаем только с массивом
> рациональных чисел, проводим простейшие математические операции, получая
> различные числовые выборки. 
### 3. Выбор структуры данных
> Для действий с рядами рациональных чисел прекрасно подойдёт массив типа 
> double, для подсчёта его размера будем использовать типа int.
#### Поля
**class ElementsOfStatistic**

| Название переменной | Тип переменной | Нижняя граница | Верхняя граница |
|---------------------|----------------|----------------|-----------------|
| numbers             | double[]       | -              | -               |
| count               | int            | 0              | $2^{31}-1$      |


### 4. Программа

```java
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
        if (count == 0)
            return 0;
        double product = 1;
        for (int i = 0; i < count; i++)
            product *= numbers[i];
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
        double bestNum = a[0];
        int best = 1;
        int current = 1;
        boolean hasMode = false;
        for (int i = 1; i < count; i++) {
            if (a[i] == a[i-1])
                current++;
            else
                current = 1;
            if (current > best) {
                best = current;
                bestNum = a[i];
                hasMode = true;
            }
        }
        if (best > 1 && hasMode)
            System.out.printf("Мода: %.2f (встречается %d раз)\n", bestNum, best);
        else
            System.out.println("Моды нет");
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

```

### 5. Анализ правильности решения

> Проверять правильность решения будем сразу для всех методов,
> так как у нас есть метод, позволяющий это сделать
> 
> ### Тест 1
```java
ElementsOfStatistic list = new ElementsOfStatistic();
list.addNumber(5); 
list.addNumber(3); 
list.addNumber(7); 
list.addNumber(3); 
list.addNumber(2); 
list.printAllCharacteristics();
```
Вывод:
Количество чисел: 5
Числа: [2,00, 3,00, 3,00, 5,00, 7,00]
По убыванию:[7,00, 5,00, 3,00, 3,00, 2,00]
Частное ряда:
2,00 - 1 раз(а)
3,00 - 2 раз(а)
5,00 - 1 раз(а)
7,00 - 1 раз(а)
Размах: 5,0000
Среднее арифметическое: 4,0000
Среднее геометрическое: 3,6297
Среднее гармоническое: 3,3123
Мода: 3,00 (встречается 2 раз)
Медиана: 3,0000
Дисперсия: 3,2000
Среднеквадр. отклонение: 1,7889
Дисперсия альтернативная: 0,1600


> ### Тест 2
```java
ElementsOfStatistic list = new ElementsOfStatistic();
list.addNumber(5); 
list.addNumber(3); 
list.addNumber(7); 
list.addNumber(3); 
list.addNumber(2); 
list.printAllCharacteristics();
```
Вывод:
Количество чисел: 5
Числа: [1,10, 2,20, 3,30, 4,40, 5,50]
По убыванию:[5,50, 4,40, 3,30, 2,20, 1,10]
Частное ряда:
1,10 - 1 раз(а)
2,20 - 1 раз(а)
3,30 - 1 раз(а)
4,40 - 1 раз(а)
5,50 - 1 раз(а)
Размах: 4,4000
Среднее арифметическое: 3,3000
Среднее геометрическое: 2,8657
Среднее гармоническое: 2,4088
Моды нет
Медиана: 3,3000
Дисперсия: 2,4200
Среднеквадр. отклонение: 1,5556
Дисперсия альтернативная: 0,0000
> ### Тест 2
```java
ElementsOfStatistic list1 = new ElementsOfStatistic();
list.addNumber(5.5); 
list.addNumber(4.4); 
list.addNumber(3.3); 
list.addNumber(2.2); 
list.addNumber(1.1); 
list1.printAllCharacteristics();
```
Вывод:
Количество чисел: 5
Числа: [0,00, 0,00, 1,00, 1,00, 31,00]
По убыванию:[31,00, 1,00, 1,00, 0,00, 0,00]
Частное ряда:
0,00 - 2 раз(а)
1,00 - 2 раз(а)
31,00 - 1 раз(а)
Размах: 31,0000
Среднее арифметическое: 6,6000
Среднее геометрическое: 0,0000
Среднее гармоническое: 0,0000
Мода: 0,00 (встречается 2 раз)
Медиана: 1,0000
Дисперсия: 149,0400
Среднеквадр. отклонение: 12,2082
Дисперсия альтернативная: 0,2400
