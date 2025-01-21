import java.io.FileNotFoundException;
import java.util.*;

public class CS_214_Project {

    public static void main(String[] args) throws FileNotFoundException {
        //Checks if correct amount of args
        if (args.length != 3) {
            System.err.println("Error: Invalid number of arguments.");
            return;
        }

        //Creates a list of Images from arg 0
        List<Images> trainingList = FileProcessor.ImagesCreater(args[0]);
        List<Images> testList = FileProcessor.ImagesCreater(args[1]);
        int numOfClusters = Integer.parseInt(args[2]);

        List<Perceptron> trainedPerceptrons = new LinkedList<>();
        Set<Integer> usedClassValues = new HashSet<>();

        for(int i = 0; i < trainingList.size(); i++){
            int classValue = Perceptron.classIdentifier(trainingList.get(i).getFileNames().get(0));
            //Checks if classValue already has a Perceptron
            if(!usedClassValues.contains(classValue)){
                Perceptron tron = new Perceptron(classValue);

                //Trains the perceptron a 100 times for each image
                FileProcessor.trainPerceptron(trainingList, tron);

                //adds the perceptron into the trained list 
                trainedPerceptrons.add(tron);
                usedClassValues.add(classValue);
            }
        }

        while(testList.size() > numOfClusters){
            ImageComparison.mergeMostSimilarImagesPerceptron(testList, trainedPerceptrons);
          
        }
                
        Images.ImagesPrinter(testList);

    }
}
