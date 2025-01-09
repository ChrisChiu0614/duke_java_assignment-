# duke_java_assignment-
java assignment for duke
## Practice String API
- **Mission:**
  - Create some methods to find a specific sequence of genes using the String API.
    - String.indexOf(codon)
    - String.indexOf(codon, index+1)
    - String.substring(startIndex,endIndex);
    - String.toLowerCase();
    - String.toUpperCase();
- **Package:**
    - stringsFirstAssignments
    - stringsSecondAssignments
    - stringsThirdAssignments

## Practice String API and CSV
- **Mission:**
    - Create some methods to parse a CSV file and find specific data using the CSV and String APIs.
        - String.contains();
        - import org.apache.commons.csv.CSVParser;
        - import org.apache.commons.csv.CSVRecord;
    - Class Part1
      - Create methods to achieve the following functionalities:
        1. Retrieve Specific Country Information: Find and display detailed export data for a given country, including exported items and their total export value.
        2. Find Countries Exporting Both Item A and Item B: Identify and list the countries that export both specified items.
        3. Identify High-Value Exporting Countries: Find and display countries whose total export value exceeds a specified threshold.
    - Class Part2
      - Create methods to achieve the following functionalities:
        1. Retrieve the coldest temperature(s) from single file or multiple files.
        2. Retrieve the lowest humidity(s) from single file or multiple files.
        3. Retrieve the average temperature from single file.
        4. Retrieve the average temperature from single file including only the data where the humidity is greater than 80.
- **Package:**
    - csv

## Mini Project - Baby Birth
- **Mission:**
  - Baby Names Data Analysis
  This project focuses on parsing CSV files to analyze baby names data using the Apache Commons CSV library and Java's built-in String and CSV APIs. The goal is to create methods to extract, manipulate, and analyze data efficiently.
  - **Class BabyBirth**
      1. **Retrieve Total Births**  
         Calculate the total number of births for boys and girls, and print their counts along with the total names in the file.

      2. **Find Rank by Name**
          - Method: `getRank(int year, String name, String gender)`  
            Returns the rank of a name for a given year and gender, where rank 1 is the most popular name. Returns `-1` if the name is not found.

      3. **Find Name by Rank**
          - Method: `getName(int year, int rank, String gender)`  
            Returns the name at a specific rank for a given year and gender. Returns `"NO NAME"` if the rank is not found.

      4. **What Would Your Name Be in Another Year?**
          - Method: `whatIsNameInYear(String name, int year, int newYear, String gender)`  
            Predicts what a given name would have been if the person were born in another year, based on rank popularity.
      5. **Find Year of Highest Rank**
         - Method: `yearOfHighestRank(String name, String gender)`  
           Identifies the year in which the given name and gender had the highest rank. Returns `-1` if the name does not exist in the data.

      6. **Calculate Average Rank**
          - Method: `getAverageRank(String name, String gender)`  
            Returns the average rank of a name across selected files. Returns `-1.0` if the name is not ranked in any files.

      7. **Find Total Births Ranked Higher**
          - Method: `getTotalBirthsRankedHigher(int year, String name, String gender)`  
            Calculates the total number of births for names ranked higher than the given name in the same year and gender.
- APIs Used

  1. **String API**
      - `String.contains()`  
        Used to filter data or check for specific conditions in CSV records.

  2. **Apache Commons CSV**
      - `CSVParser`  
        Parses CSV files for efficient data access.
      - `CSVRecord`  
        Represents a single record in a CSV file.

  3. **Java File Handling**
      - `File`  
        Reads and processes CSV files containing baby names data.
      - `DirectoryResource` (Duke-specific library)  
        Selects and processes multiple files for analysis.