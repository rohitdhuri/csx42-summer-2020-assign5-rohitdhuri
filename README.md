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
