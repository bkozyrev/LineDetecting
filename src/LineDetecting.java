import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LineDetecting {

    private static ArrayList<Integer[]> points; //тут храним считанные из файла точки
    private static ArrayList<ArrayList<Integer>> pointsInLine; //тут массив, каждый элемент которого - массив из точек на одной линии
    private static ArrayList<Double> passedKoef; //храним тангенсы угла наклона (для дальнейшей оптимизации)

    public static void main(String[] args) {
        points = new ArrayList<>();
        pointsInLine = new ArrayList<>();
        passedKoef = new ArrayList<>();
        readFile(); //очевидно
        checkPoints(); //ищем точки на одной линии
        printFile(); //выводим результат в файлик
    }

    public static void checkPoints(){
        for(int i = 0; i < points.size() - 3; i++){ //начинаем бежать по всем точкам, i - индекс точки

            if(passedKoef.contains((double)points.get(i)[0] / points.get(i)[1])) //если такой тангенс угла наклона уже был, то го следующий
                continue;

            ArrayList<Integer> tmpPoints = new ArrayList<>(); //tmpPoints - временный массив, в который будем добавлять точки с таким же тангенсом, что и точка i
            tmpPoints.add(i); //добавляем точку
            passedKoef.add((double) points.get(i)[0] / points.get(i)[1]); //также сохраняем ее тангенс

            for(int j = i + 1; j < points.size(); j++){ //в остальном массиве ищем точки с таким же тангенсом
                if((double)points.get(i)[0] / points.get(i)[1] == (double)points.get(j)[0] / points.get(j)[1]){
                    tmpPoints.add(j);
                }
            }

            if(tmpPoints.size() >= 4) //если в итоге 4 и более точек, то добавляем куда следует
                pointsInLine.add(tmpPoints);
        }
    }

    public static void readFile(){

        try {

            Scanner scanner = new Scanner(new File("C:\\Users\\Егор\\Desktop\\input.txt"));

            while(scanner.hasNextInt()){ //файл состоит из интовских чисел, идущих через пробел
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                points.add(new Integer[]{x, y});
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printFile(){
        try {
            FileWriter writer = new FileWriter("C:\\Users\\Егор\\Desktop\\output.txt", false);
            for(int i = 0; i < pointsInLine.size(); i++){
                String text = "Линия " + String.valueOf(i + 1);
                writer.write(text);
                writer.write("\r\n");

                for(Integer pointNumber: pointsInLine.get(i)){
                    text = points.get(pointNumber)[0] + " " + points.get(pointNumber)[1];
                    writer.write(text);
                    writer.write("\r\n");
                }
            }

            writer.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
