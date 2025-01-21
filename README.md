
# Perceptron-Based Image Clustering for PGM Files

## Overview

This project implements a perceptron-based algorithm to cluster PGM (Portable Gray Map) images into similar groups after training. It integrates multiple concepts in software engineering, including file processing, algorithm development, and testing.

## Motivation

The primary goal of this project is to demonstrate the ability to design and implement a machine learning-based solution for image clustering. This is achieved by training a perceptron on labeled input data and evaluating its performance on test datasets.

## Features

- **Perceptron Algorithm**: Core logic for training and classification.
- **Input and Output Management**:
  - Input: Training and testing data are provided in the `input_files` directory.
  - Output: Results of the clustering are stored in the `output_files` directory.
- **Testing**: Includes pre-defined test cases to validate the model's accuracy and functionality.

## Project Structure

- `src/`:
  - `main`: Main implementation of the perceptron and clustering algorithm.
  - `test`: Test cases to validate the program's functionality.
- `input_files/`: Contains training (`train`) and testing (`test`) datasets in PGM format.
- `output_files/`: Stores the clustering results, such as `example_output.txt`.
- `README.md`: Documentation for understanding and using the project.

## Requirements

- **Java Development Kit (JDK)**: Version 8 or higher.
- **Gradle**: For building and running the project.

## Setup and Usage

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd Perceptron
   ```

2. **Build the Project**:
   ```bash
   gradle build
   ```

3. **Run the Program**:
   ```bash
   gradle run
   ```

4. **Provide Input Data**:
   - Place your training and test datasets in the `input_files/` directory.

5. **View Output**:
   - Processed results will be available in the `output_files/` directory.

## Testing

- Run unit tests using Gradle:
  ```bash
  gradle test
  ```

## Contributions

Contributions are welcome! Please submit issues or pull requests to improve the project.

## License

This project is for educational purposes and follows standard academic integrity guidelines.

## Contact

For any questions, please reach out to [liraevan3@gmail.com](mailto:liraevan3@gmail.com).
