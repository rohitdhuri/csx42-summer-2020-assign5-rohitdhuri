# CSX42: Assignment 5
## Name: Rohit Mahendra Dhuri

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on this project.


Note: build.xml is present in [textdecorators/src](./textdecorators/src/) folder.

## Instruction to clean:

####Command: ant -buildfile textdecorators/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile textdecorators/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile textdecorators/src/build.xml run -Dinput="input.txt" -Dmispelled="mispelledwords.txt" -Dkeywords="keywords.txt" -Doutput="output.txt" -Ddebug="debug.txt"

Note: Arguments accept the absolute path of the files.

## Description:
1. Assumption: 
Absolute path of the input files is provided.
Special characters are not included in input file.

2. Data Structures: 
-Hash sets have been used for storing unique elements
-Strings have been used as both input and output buffers.
-Arraylist has been used for storing input lines.


3. Code Working: 
The code accepts 5 arguments. Then the input file is read throgh the inputetails class after which all processinput method is called in all decorator classses to decorate the input string.
-Time complexity: O(n^2)

4. Slack days: 
Used 3 slack days.


## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [08/08/2020]






