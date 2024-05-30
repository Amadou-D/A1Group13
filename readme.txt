Instructions for Installing and Using the Sorting Program Group13


Thank you for choosing our sorting program! Follow the steps below to install and utilize the application effectively.


Installation Instructions


1. Unzip the provided project files (A1GroupX.zip) to your desired directory.

2. Open Eclipse IDE.

3. Import the project into Eclipse:
   - Go to File > Import.
   - Select General > Existing Projects into Workspace.
   - Click Next.
   - Choose the root directory by clicking Browse and navigating to the unzipped project folder.
   - Make sure the project checkbox is checked.
   - Click Finish.

4. Ensure that Java 8 is installed on your system. If not, download and install it from the official Oracle website.

5. Build the project to ensure that all dependencies are resolved and the project is compiled without errors:
   - Right-click on the project name in Eclipse.
   - Select Build Project.


Usage Instructions


1. Once the project is imported and built successfully, you can run the sorting program via the command line.

2. Open the terminal or command prompt.

3. Navigate to the directory where the project files are located.

4. Execute the sorting program using the following command format:
   java -jar Sort.jar -f<file_name> -t<type> -s<algorithm>
   
   Replace:
   - <file_name> with the name of the input file containing shape data (e.g., shapes1.txt).
   - <type> with 'h' for height, 'v' for volume, or 'a' for base area.
   - <algorithm> with 'b' for Bubble, 's' for Selection, 'i' for Insertion, 'm' for Merge, 'q' for Quick, or your choice of sorting algorithm ('z').

   Example:
   java -jar Sort.jar -fshapes1.txt -tv -sb

5. Press Enter to execute the command.

6. The program will read the input file, sort the shapes according to the specified type and algorithm, and display the sorted results along with benchmarking information.