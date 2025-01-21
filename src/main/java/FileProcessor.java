import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class FileProcessor {

    public static int pgmWidthPrep(Scanner file) {
        file.next();
        int width = Integer.parseInt(file.next());
        if (width <= 0) {
            System.err.println("Error Impossible Image");
            return 0;
        }
        return width;
    }

    public static int pgmHeightPrep(Scanner file) {
        int height = Integer.parseInt(file.next());
        if (height <= 0) {
            System.err.println("Error Impossible Image");
            return 0;
        }
        file.next(); // Skip max pixel value
        return height;
    }

    public static int scannerPreparer(Scanner read) {
        int fileWidth = pgmWidthPrep(read);
        int fileHeight = pgmHeightPrep(read);
        return fileWidth * fileHeight;
    }
 
    public static int[] pgmToArrayPrenormalized(Scanner read) {
        int[] output = new int[64];
        while (read.hasNext()) {
            String data = read.next();
            try {
                int key = Integer.parseInt(data);
                if (key >= 0 && key <= 255) {
                    key = key / 4;
                    output[key]++;
                } else {
                    System.err.println(ErrorMessage(data));
                    return null;
                }
            } catch (NumberFormatException nfe) {
                System.err.println(ErrorMessage(data));
                return null;
            }
        }
        return output;
    }
    

    public static int totalIntegers(int[] histogram) {
        int total = 0;
        for (int numbers : histogram) {
            total += numbers;
        }
        return total;
    }

    
    public static double[] getNormalizedHistogram(String filePath) throws FileNotFoundException{
        @SuppressWarnings("resource")
        Scanner scnr = new Scanner(filePath);
        File fileCompared = new File(scnr.next());

        Scanner read = new Scanner(fileCompared);
        int fileArea = FileProcessor.scannerPreparer(read);

        int[] array1 = FileProcessor.pgmToArrayPrenormalized(read);
            if (array1 != null && FileProcessor.totalIntegers(array1) == fileArea) {
                double[] normalizedArray1 = ImageComparison.arrayNormalizer(array1);
                return normalizedArray1;
            }   
            else return null;
    }
    
    public static List<String> getListOfImagePaths(String filePath) throws FileNotFoundException {
        List<String> fileNames = new ArrayList<>();
        File inputFile = new File(filePath);
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(inputFile);

        while (scanner.hasNextLine()) {
            fileNames.add(scanner.nextLine().trim());
        }
        return fileNames;
    }
   
    public static String ErrorMessage(String error) {
        try {
            Integer.parseInt(error);
            return "Error input " + error + " was out of the range [0, 255]";
        } catch (NumberFormatException nfe) {
            return "Error Invalid Input - '" + error + "'";
        }
    }

    public static String getName(String filePath) throws FileNotFoundException{
        File currFile = new File(filePath);
        String name = currFile.getName();
        
        return name;
    }

    public static List<Images> ImagesCreater(String filePaths) {
        List<String> fileNames;
        List<Images> Images = new ArrayList<>();
        try {

            fileNames = getListOfImagePaths(filePaths);
            try{
                for (String filePath : fileNames) {
                    double[] histogram = FileProcessor.getNormalizedHistogram(filePath);
                    Images.add(new Images(filePath, histogram));
                }
            }
            catch(FileNotFoundException e){
                System.err.println("Error: Invalid FilePath");
            }
        
        }
    
    catch(FileNotFoundException e){
        System.err.println("Error: Invalid File Path For Directory File");
    }
    
    return Images;
    }

    public static void trainPerceptron(List<Images> imagesList, Perceptron tron){
        
        for(int i = 0; i < 100; i++ ){
            for(Images image : imagesList){
                String fileName = image.getFileNames().get(0);
                double[] normHist = image.getHistogram();
                tron.training(normHist, fileName);
                
            }

        }
    }
}
