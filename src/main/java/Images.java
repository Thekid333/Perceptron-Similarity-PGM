import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

    public class Images {
        private  List<String> fileNames;
        private double[] histogram;


        public Images(String fileName, double[] histogram) {
            this.fileNames = new ArrayList<>();
            this.fileNames.add(fileName);
            this.histogram = histogram;
        }

        public List<String> getFileNames() {
            return fileNames;
        }

        public double[] getHistogram() {
            return histogram;
        }

        public void merge(Images other) {
            int thisSize = this.fileNames.size();
            int otherSize = other.getFileNames().size();
            this.fileNames.addAll(other.getFileNames());
        
            for (int i = 0; i < this.histogram.length; i++) {
                this.histogram[i] = (this.histogram[i] * thisSize + other.histogram[i] * otherSize) / (thisSize + otherSize);
            }
        }
    
        

        public void sortNames(){
            Collections.sort(fileNames);
        }

        public String printNames(){
            String names = "";
            for(int i = 0; i < fileNames.size(); i++){
            File currFile = new File(fileNames.get(i));

            names += currFile.getName() + " ";
            }
            return names;
        }
        //prints all file names in each image in list
        public static void ImagesPrinter(List<Images> Images) {
        try{
            for (Images image : Images) {
                image.sortNames();
                String output = "";
                for(int i = 0; i < image.getFileNames().size(); i++){
                    output += FileProcessor.getName(image.getFileNames().get(i)) + " ";
    
                }
    
                System.out.println(output);
    
            }
    }
    catch(FileNotFoundException e){
        System.err.println("Error: File not Found");
    }

    }
    }