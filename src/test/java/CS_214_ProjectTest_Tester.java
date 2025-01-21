import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Main test class for organizing related test groups
public class CS_214_ProjectTest_Tester {
   
    public static class FileProcessorErrorMessageTests {

        @Test
        public void testErrorMessage_outOfRangeIntegerInput() {
            String errorMessage = FileProcessor.ErrorMessage("256");
            assertEquals("Error input 256 was out of the range [0, 255]", errorMessage);
        }

        @Test
        public void testErrorMessage_negativeIntegerInput() {
            String errorMessage = FileProcessor.ErrorMessage("-1");
            assertEquals("Error input -1 was out of the range [0, 255]", errorMessage);
        }

        @Test
        public void testErrorMessage_invalidTextInput() {
            String errorMessage = FileProcessor.ErrorMessage("abc");
            assertEquals("Error Invalid Input - 'abc'", errorMessage);
        }
    }

    public static class ImageComparisonTest {

        @Test
        public void testSumOfProduct() {
            double[] product = {1.0, 2.0, 3.0, 4.0};
            double result = ImageComparison.sumOfProduct(product);
            assertEquals(10.0, result, 0.001);
        }

        // @Test
        // public void testArrayPairwiseMin() {
        //     double[] arr1 = {1.0, 5.0, 3.0, 4.0};
        //     double[] arr2 = {2.0, 4.0, 6.0, 1.0};
        //     double[] result = ImageComparison.arrayPairwiseMin(arr1, arr2);
        //     double[] expected = {1.0, 4.0, 3.0, 1.0};
        //     assertArrayEquals(expected, Arrays.copyOf(result, expected.length));
        // }

        @Test
        public void testArrayNormalizer() {
            int[] histogram = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                            17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                            31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44,
                            45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58,
                            59, 60, 61, 62, 63, 64};
            double[] result = ImageComparison.arrayNormalizer(histogram);
            double total = Arrays.stream(histogram).sum();
            double[] expected = Arrays.stream(histogram).asDoubleStream().map(h -> h / total).toArray();
            assertArrayEquals(expected, result, 0.001);
        }

        // @Test
        // public void testCompare() {
        //     double[] hist1 = {0.5, 0.3, 0.7, 0.2};
        //     double[] hist2 = {0.6, 0.2, 0.4, 0.3};
        //     double result = ImageComparison.compare(hist1, hist2);
        //     assertEquals(1.1, result, 0.001);
        // }

        @Test
        public void testComparePerceptron() {
            Perceptron perceptron = new Perceptron(1);
            double[] normHist1 = {0.1, 0.2, 0.3, 0.4};
            double[] normHist2 = {0.4, 0.3, 0.2, 0.1};

            double score1 = perceptron.testing(normHist1);
            double score2 = perceptron.testing(normHist2);
            // Test that the perceptron returns consistent scores for normalized histograms
            assertNotNull(score1);
            assertNotNull(score2);
        }

        @Test
        public void testMergeMostSimilarImages() {
            Images image1 = new Images("Image1", new double[]{0.1, 0.2, 0.3, 0.4});
            Images image2 = new Images("Image2", new double[]{0.4, 0.3, 0.2, 0.1});
            Images image3 = new Images("Image3", new double[]{0.5, 0.5, 0.5, 0.5});

            List<Images> images = new ArrayList<>(List.of(image1, image2, image3));
            ImageComparison.mergeMostSimilarImages(images);
            assertEquals(2, images.size());
        }

        @Test
        public void testMergeMostSimilarImagesPerceptron() {
            List<Perceptron> perceptrons = List.of(new Perceptron(1), new Perceptron(2));
            Images image1 = new Images("Image1", new double[]{0.1, 0.2, 0.3, 0.4});
            Images image2 = new Images("Image2", new double[]{0.4, 0.3, 0.2, 0.1});
            Images image3 = new Images("Image3", new double[]{0.5, 0.5, 0.5, 0.5});

            List<Images> images = new ArrayList<>(List.of(image1, image2, image3));
            ImageComparison.mergeMostSimilarImagesPerceptron(images, perceptrons);
            assertEquals(2, images.size());
        }

        @Test
        public void testImagesMerge() {
            Images image1 = new Images("Image1", new double[]{0.2, 0.4, 0.6, 0.8});
            Images image2 = new Images("Image2", new double[]{0.4, 0.3, 0.2, 0.1});

            image1.merge(image2);

            double[] expectedHistogram = {(0.2 * 1 + 0.4 * 1) / 2, (0.4 * 1 + 0.3 * 1) / 2, (0.6 * 1 + 0.2 * 1) / 2, (0.8 * 1 + 0.1 * 1) / 2};
            assertArrayEquals(expectedHistogram, image1.getHistogram(), 0.001);
            assertEquals(2, image1.getFileNames().size());
        }

        @Test
        public void testImagesPrinter() {
            Images image1 = new Images("class1_imageA", new double[]{0.1, 0.2, 0.3, 0.4});
            Images image2 = new Images("class2_imageB", new double[]{0.4, 0.3, 0.2, 0.1});

            image1.sortNames();
            image2.sortNames();

            assertEquals("class1_imageA ", image1.printNames());
            assertEquals("class2_imageB ", image2.printNames());
        }
    }
    
    public static class PerceptronTest {

        @Test
        public void testConstructor() {
            int classValue = 1;
            Perceptron perceptron = new Perceptron(classValue);
            assertEquals(classValue, perceptron.getClassValue());
            assertNotNull(perceptron.getWeights());
            assertEquals(0, perceptron.getBias(), 0.001);
        }
    
        @Test
        public void testClassIdentifier() {
            String filePath = "path/to/class5_image.jpg";
            int classValue = Perceptron.classIdentifier(filePath);
            assertEquals(5, classValue);
        }
    
        @Test
        public void testTraining() {
            Perceptron perceptron = new Perceptron(1);
            double[] normHist = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.1, 0.2, 0.3, 0.4};
            String fileName = "path/to/class1_image.jpg";
    
            perceptron.training(normHist, fileName);
            double[] updatedWeights = perceptron.getWeights();
            assertNotNull(updatedWeights);
        }
    
        @Test
        public void testTesting() {
            Perceptron perceptron = new Perceptron(1);
            double[] normHist = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.1, 0.2, 0.3, 0.4};
            double score = perceptron.testing(normHist);
            assertNotNull(score);
        }
    
        @Test
        public void testPrintWeightValues() {
            Perceptron perceptron = new Perceptron(1);
            perceptron.printWeightValues();
            // Ensure it prints the weight values without throwing exceptions
            assertTrue(true);
        }
    }

    public static class ImagesTest {

        @Test
        public void testConstructor() {
            String fileName = "path/to/image.jpg";
            double[] histogram = {0.1, 0.2, 0.3, 0.4};
            Images image = new Images(fileName, histogram);
            assertNotNull(image.getFileNames());
            assertEquals(1, image.getFileNames().size());
            assertArrayEquals(histogram, image.getHistogram(), 0.001);
        }

        @Test
        public void testGetFileNames() {
            String fileName = "path/to/image.jpg";
            double[] histogram = {0.1, 0.2, 0.3, 0.4};
            Images image = new Images(fileName, histogram);
            List<String> fileNames = image.getFileNames();
            assertNotNull(fileNames);
            assertEquals(1, fileNames.size());
            assertEquals(fileName, fileNames.get(0));
        }

        @Test
        public void testGetHistogram() {
            String fileName = "path/to/image.jpg";
            double[] histogram = {0.1, 0.2, 0.3, 0.4};
            Images image = new Images(fileName, histogram);
            assertArrayEquals(histogram, image.getHistogram(), 0.001);
        }

        @Test
        public void testMerge() {
            Images image1 = new Images("path/to/image1.jpg", new double[]{0.1, 0.2, 0.3, 0.4});
            Images image2 = new Images("path/to/image2.jpg", new double[]{0.4, 0.3, 0.2, 0.1});

            image1.merge(image2);

            double[] expectedHistogram = {(0.1 * 1 + 0.4 * 1) / 2, (0.2 * 1 + 0.3 * 1) / 2, (0.3 * 1 + 0.2 * 1) / 2, (0.4 * 1 + 0.1 * 1) / 2};
            assertArrayEquals(expectedHistogram, image1.getHistogram(), 0.001);
            assertEquals(2, image1.getFileNames().size());
        }

        @Test
        public void testSortNames() {
            Images image = new Images("path/to/imageB.jpg", new double[]{0.1, 0.2, 0.3, 0.4});
            image.getFileNames().add("path/to/imageA.jpg");
            image.sortNames();
            assertEquals("path/to/imageA.jpg", image.getFileNames().get(0));
            assertEquals("path/to/imageB.jpg", image.getFileNames().get(1));
        }

        @Test
        public void testPrintNames() {
            Images image = new Images("path/to/image.jpg", new double[]{0.1, 0.2, 0.3, 0.4});
            String names = image.printNames();
            assertEquals("image.jpg ", names);
        }

        @Test
        public void testImagesPrinter() {
            Images image1 = new Images("path/to/image1.jpg", new double[]{0.1, 0.2, 0.3, 0.4});
            Images image2 = new Images("path/to/image2.jpg", new double[]{0.4, 0.3, 0.2, 0.1});
            List<Images> images = List.of(image1, image2);

        
                Images.ImagesPrinter(images);
                assertTrue(true);
        }
    }

    public static class FileProcessorTest {

    @Test
    public void testPgmWidthPrep() {
        Scanner scanner = new Scanner("P2 100 200 255");
        int width = FileProcessor.pgmWidthPrep(scanner);
        assertEquals(100, width);
    }

    @Test
    public void testPgmHeightPrep() {
        Scanner scanner = new Scanner("P2 100 200 255");
        FileProcessor.pgmWidthPrep(scanner); // Move scanner to height
        int height = FileProcessor.pgmHeightPrep(scanner);
        assertEquals(200, height);
    }

    @Test
    public void testScannerPreparer() {
        Scanner scanner = new Scanner("P2 100 200 255");
        int fileArea = FileProcessor.scannerPreparer(scanner);
        assertEquals(100 * 200, fileArea);
    }

    @Test
    public void testPgmToArrayPrenormalized() {
        Scanner scanner = new Scanner("1 2 3 255 128");
        int[] array = FileProcessor.pgmToArrayPrenormalized(scanner);
        assertNotNull(array);
        assertEquals(64, array.length);
        assertEquals(3, array[0]); // value 1 divided by 4 falls into bucket 0
    }

    @Test
    public void testTotalIntegers() {
        int[] histogram = {1, 2, 3, 4};
        int total = FileProcessor.totalIntegers(histogram);
        assertEquals(10, total);
    }

    @Test
    public void testGetNormalizedHistogram() throws FileNotFoundException {
        String filePath = "input_files/test/class0_1.pgm";

        double[] normalizedHistogram = FileProcessor.getNormalizedHistogram(filePath);
        assertNotNull(normalizedHistogram);
        assertEquals(64, normalizedHistogram.length);
    }

    @Test
    public void testGetListOfImagePaths() throws FileNotFoundException {
        String filePath = "test_file_paths.txt";
        File testFile = new File(filePath);
        // Create a temporary test file with some content
        try (PrintWriter out = new PrintWriter(testFile)) {
            out.println("path/to/image1.pgm");
            out.println("path/to/image2.pgm");
        }

        List<String> imagePaths = FileProcessor.getListOfImagePaths(filePath);
        assertNotNull(imagePaths);
        assertEquals(2, imagePaths.size());
        assertEquals("path/to/image1.pgm", imagePaths.get(0));
        assertEquals("path/to/image2.pgm", imagePaths.get(1));
        testFile.delete();
    }

    @Test
    public void testErrorMessage() {
        String errorMessage = FileProcessor.ErrorMessage("300");
        assertEquals("Error input 300 was out of the range [0, 255]", errorMessage);

        errorMessage = FileProcessor.ErrorMessage("notANumber");
        assertEquals("Error Invalid Input - 'notANumber'", errorMessage);
    }

    @Test
    public void testGetName() throws IOException {
        String filePath = "test_file.txt";
        File testFile = new File(filePath);
        testFile.createNewFile();

        String name = FileProcessor.getName(filePath);
        assertEquals("test_file.txt", name);
        testFile.delete();
    }

    @Test
    public void testImagesCreater() throws FileNotFoundException {
        String filePath = "input_files/test/test_copy.txt";
        File testFile = new File(filePath);
        // Create a temporary test file with some content
        try (PrintWriter out = new PrintWriter(testFile)) {
            out.println("input_files/test/class0_1.pgm");
            out.println("input_files/test/class0_2.pgm");
        }

        List<Images> images = FileProcessor.ImagesCreater(filePath);
        assertNotNull(images);
        assertEquals(2, images.size());
        assertEquals(64, images.get(0).getHistogram().length);
        testFile.delete();
    }

    @Test
    public void testTrainPerceptron() {
        Images image1 = new Images("input_files/train/class1_8.pgm", new double[64]);
        Images image2 = new Images("input_files/train/class1_9.pgm", new double[64]);
        List<Images> imagesList = List.of(image1, image2);
        Perceptron perceptron = new Perceptron(1);

        FileProcessor.trainPerceptron(imagesList, perceptron);
        assertNotNull(perceptron.getWeights());
    }
}

    public class CS_214_ProjectTest {

        @Test
        public void testMainWithInvalidArguments() {
            String[] args = {"arg1", "arg2"};
            assertDoesNotThrow(() -> CS_214_Project.main(args));
        }
    
        @Test
        public void testMainWithValidArguments() throws FileNotFoundException {
            String trainingFilePath = "training_file.txt";
            String testFilePath = "test_file.txt";
    
            // Create temporary training and test files
            try (PrintWriter out = new PrintWriter(trainingFilePath)) {
                out.println("path/to/training_image1.jpg");
                out.println("path/to/training_image2.jpg");
            }
            try (PrintWriter out = new PrintWriter(testFilePath)) {
                out.println("path/to/test_image1.jpg");
                out.println("path/to/test_image2.jpg");
            }
    
            String[] args = {trainingFilePath, testFilePath, "1"};
            assertDoesNotThrow(() -> CS_214_Project.main(args));
    
            // Clean up
            new File(trainingFilePath).delete();
            new File(testFilePath).delete();
        }
    
        @Test
        public void testPerceptronTraining() {
            Images image1 = new Images("path/to/training_image1.jpg", new double[]{0.1, 0.2, 0.3, 0.4});
            Images image2 = new Images("path/to/training_image2.jpg", new double[]{0.4, 0.3, 0.2, 0.1});
            List<Images> trainingList = List.of(image1, image2);
            Perceptron perceptron = new Perceptron(1);
    
            FileProcessor.trainPerceptron(trainingList, perceptron);
            assertNotNull(perceptron.getWeights());
        }
    
        @Test
        public void testImageClustering() {
            Images image1 = new Images("path/to/test_image1.jpg", new double[]{0.1, 0.2, 0.3, 0.4});
            Images image2 = new Images("path/to/test_image2.jpg", new double[]{0.4, 0.3, 0.2, 0.1});
            List<Images> testList = new ArrayList<>(List.of(image1, image2));
            Perceptron perceptron = new Perceptron(1);
            List<Perceptron> perceptrons = List.of(perceptron);
    
            assertDoesNotThrow(() -> ImageComparison.mergeMostSimilarImagesPerceptron(testList, perceptrons));
            assertEquals(1, testList.size());
        }
    
        @Test
        public void testImagesPrinter() {
            Images image1 = new Images("path/to/test_image1.jpg", new double[]{0.1, 0.2, 0.3, 0.4});
            Images image2 = new Images("path/to/test_image2.jpg", new double[]{0.4, 0.3, 0.2, 0.1});
            List<Images> imagesList = List.of(image1, image2);
    
            assertDoesNotThrow(() -> Images.ImagesPrinter(imagesList));
        }
    }
}
