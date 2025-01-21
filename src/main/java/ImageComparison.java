import java.util.List;


public class ImageComparison {

    public static double sumOfProduct(double[] product) {
        double sum = 0.0;
        for (double value : product) {
            sum += value;
        }
        return sum;
    }

    public static double[] arrayPairwiseMin(double[] arr1, double[] arr2) {
        double[] product = new double[64];
        for (int i = 0; i < 64; i++) {
            product[i] = Math.min(arr1[i], arr2[i]);
        }
        return product;
    }
    
    public static double[] arrayNormalizer(int[] histogram) {
        int totalIntegers = FileProcessor.totalIntegers(histogram);
        double[] normalizedHistogram = new double[64];
        for (int i = 0; i < 64; i++) {
            normalizedHistogram[i] = (double) histogram[i] / totalIntegers;
        }
        return normalizedHistogram;
    }

    public static double compare(double[] hist1, double[] hist2) {
        double sum = 0.0;
        for (int i = 0; i < hist1.length; i++) {
            sum += Math.min(hist1[i], hist2[i]);
        }
        return sum;
    }

    public static double comparePerceptron(List<Perceptron> perceptrons, Images image1, Images image2) {
        double similarity = 0.0;
        for (int i = 0; i < perceptrons.size(); i++) {
            Perceptron percep = perceptrons.get(i);
            double diff = percep.testing(image1.getHistogram()) - percep.testing(image2.getHistogram());
            similarity +=  (1 / (diff * diff));
        }
        return similarity;
    }
    
    public static void mergeMostSimilarImages(List<Images> Images) {
        int Images1Index = 0;
        int Images2Index = 1;
        double maxSimilarity = 0.0;

        
        for (int i = 0; i < Images.size(); i++) {
            for (int j = i + 1; j < Images.size(); j++) {
                double similarity = ImageComparison.compare(Images.get(i).getHistogram(), Images.get(j).getHistogram());
                if (similarity > maxSimilarity) {
                    maxSimilarity = similarity;
                    Images1Index = i;
                    Images2Index = j;
                }
            }
        }

        // Merge the two Images
        Images Images1 = Images.get(Images1Index);
        Images Images2 = Images.get(Images2Index);
        Images1.merge(Images2);
        Images.remove(Images2Index);  // Remove the second Images
    }

    public static void mergeMostSimilarImagesPerceptron(List<Images> Images, List<Perceptron> perceptrons) {
        int Images1Index = 0;
        int Images2Index = 1;
        double maxSimilarity = 0.0;

        
        for (int i = 0; i < Images.size(); i++) {
            for (int j = i + 1; j < Images.size(); j++) {
                double similarity = ImageComparison.comparePerceptron(perceptrons, Images.get(i), Images.get(j));
                // System.out.println("Image: " + Images.get(i).printNames() + " is being compared to " + Images.get(j).printNames());
                // System.out.println("The similiarity is: " + similarity);
                // System.out.println();
                if (similarity > maxSimilarity) {
                    maxSimilarity = similarity;
                    Images1Index = i;
                    Images2Index = j;
                }
            }
        }

        // Merge the two Images
        // System.out.println("Iteration is Done. Combining: " + Images.get(Images1Index).printNames() + "and Image:" + Images.get(Images2Index).printNames());
        // System.out.println("Max similarity was: " + maxSimilarity);
        // System.out.println();
        Images Images1 = Images.get(Images1Index);
        Images Images2 = Images.get(Images2Index);
        Images1.merge(Images2);
        Images.remove(Images2Index);  // Remove the second Images
    }

    
}

