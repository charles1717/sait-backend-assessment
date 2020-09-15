# Programs CRUD Application (Backend)

By [Charles Chukwukaeme](mailto:charles.kaeme@gmail.com)

(https://github.com/charles1717)

## Project Description
This application can be used to read and modify a csv file that contains a list of programs. The application was written for a file with three columns/values representing the title, priority and status of a program respectively. The program was designed to be interactive therefore requires user input from the console

## Installation Instructions

#### To run with an IDE (see issue #1)
1. From your own Github account, fork the [repo] (https://github.com/charles1717/sait-backend-assessment)
2. Clone locally using
   `git clone https://github.com/charles1717/sait-backend-assessment.git`
3. From your favourite IDE, open the locally cloned project
5. To run the program, set your run configuration to Program.java
6. Follow the instructions on the console and enter the required input

#### To run the program from command line
1. From your own Github account, fork the [repo] (https://github.com/charles1717/sait-backend-assessment)
2. Clone locally using
   `git clone https://github.com/charles1717/sait-backend-assessment.git`
3. Open command prompt (cmd) on Windows, if you are Mac OS then open Terminal.
4. From the command line, navigate to the locally cloned project folder
5. Navigate into the src folder and run the following commands:<br/>
	&nbsp;&nbsp;&nbsp;javac -d ..out/production/sait-backend-assessment Program.java<br/>
	&nbsp;&nbsp;&nbsp;cd ../out/production/sait-backend-test<br/>
	&nbsp;&nbsp;&nbsp;java Program
6. Follow the instructions on the console and enter the required input

## Usage

The application has 3 main functions:

1. Add a program to a provided csv file
2. Pull a list of programs matching a specific priority and status from a provided csv file
3. Delete a program from a provided csv file


## Discussion

I used the following technologies: Java, IntelliJ IDEA Ultimate and JUnit5.


## Requirements

#### A user would like to pull a list of programs by priority and status. Create a functionality that will allow the user to achieve this task. 
#### Example: Retrieve a list of programs that are with an available status at a high priority level.

I added a pullProgram() method that takes in two String parameters representing the priority and status and returns a List of programs from the csv
that match the serach criteria

#### The list of programs should be coming from csv. Write a functionality to read the programs from the csv.

The pull program method uses FileReader and BufferedReader to read the contents of the csv as rows and parses these rows to locate the records that match
the pull criteria 

#### Write functionality to add / delete  programs from the csv file and reflect in the UI.

Completed the add functionality using the addProgram() method. The method takes three String parameters as input: title, priority and status. It uses the creatCSVRow() helper 
helper method to create a csv row with the input formatted to proper csv standards e.g. escaping commas

Completed the delete functionality using the deleteProgram() method. This method takes in a single String parameter called title to locate the appropriate row to delete from the 
csv file
