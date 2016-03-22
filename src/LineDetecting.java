import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LineDetecting {

    private static ArrayList<Integer[]> points;
    private static ArrayList<ArrayList<Integer>> pointsInLine;
    private static ArrayList<Double> passedKoef;

    public static void main(String[] args) {
        points = new ArrayList<>();
        pointsInLine = new ArrayList<>();
        passedKoef = new ArrayList<>();
        readFile();
        checkPoints();
        printFile();
    }

    public static void checkPoints(){
        for(int i = 0; i < points.size() - 3; i++){

            if(passedKoef.contains((double)points.get(i)[0] / points.get(i)[1]))
                continue;

            ArrayList<Integer> tmpPoints = new ArrayList<>();
            tmpPoints.add(i);
            passedKoef.add((double) points.get(i)[0] / points.get(i)[1]);

            for(int j = i + 1; j < points.size(); j++){
                if((double)points.get(i)[0] / points.get(i)[1] == (double)points.get(j)[0] / points.get(j)[1]){
                    tmpPoints.add(j);
                }
            }

            if(tmpPoints.size() >= 4)
                pointsInLine.add(tmpPoints);
        }
    }

    public static void readFile(){

        try {

            Scanner scanner = new Scanner(new File("C:\\Users\\Егор\\Desktop\\input.txt"));

            while(scanner.hasNextInt()){
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
                String text = "Точка " + String.valueOf(i + 1);
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
