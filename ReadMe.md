# Java algorithm for comparing the strength of Texas Hold'em Hands.
This repository contains working code sample for comparing the strength of Texas Hold'em Hands. 
The algorithm counts the best possible combination out of 5 board cards and 2 hand cards and perform the 
standard output of hand list sorted by strength. 

Project status: _finished, tested_;


## Code Examples
Input:
`4cKs4h8s7s Ad4s Ac4d As9s KhKd 5d6d`
Output:
`Ac4d=Ad4s 5d6d As9s KhKd`

Input:
`2h3h4h5d8d KdKs 9hJh`
Output:
`KdKs 9hJh`

## General information on the use
  
The **input** should be performed to standard input in the form of:
`<5 board cards> <hand 1> <hand 2> <...> <hand N>` where:

- `5 board` cards is a 10-character string where each 2 characters encode a card;

- `hand` is a 4-character string where each 2 characters encode a card, with 2 cards per hand;

- card is a 2 character string with the first character is representing the **rank** 
(one of the "A", "K", "Q","J", "T", "9", "8", "7", "6", "5", "4", "3", "2") and the second character is representing the **suit** 
(one of the "h", "d", "c", "s"). Groups of board and hands cards are separated by one space character. 


The **output** is written to standard output using the format:
`<hand 1> <hand 2> <...> <hand n>` where:

- `hand 1` is the hand with the weakest strength;

- `hand n` is the hand  with the biggest strength;

In case there are multiple hands with the same strength on the board they are separated by "=" signs. In other cases hands are separated by space character. 
The incorrect data input will cause a validation error output, with problem description. 


## Execution of the project
The project uses Java 11.

----

You can run the project using the command line or its analogue
1. Install Java on your device if it isn't installed;
2. Download .jar file from https://github.com/GrigorijSkult/PokerHandsComparingAlgorithm/blob/master/src/main/resources/PokerHandsComparingAlgorithm.jar or get it from`PokerHandsComparingAlgorithm\src\main\resources`;
3. Run Command Prompt or its analogue;
4. Input  `java -jar c:\path\to\jar\file\PokerHandsComparingAlgorithm.jar` and press Enter. Replace `c:\path\to\jar\file\` with the actual path. 
5. Perform data input described in "General information on the use" and press Enter;
6. Next output line is hand list sorted by strength. 

Now you can repeat step №5 to calculate a new data.
In the case of the multiple lines input, one line of input corresponds to exactly one line of sequential output.


-----

You can run the project using IntelliJ IDEA:

1. Open IntelliJ IDEA 2020.1.2 or similar  version (install it and JDK if not already installed);
2. Clone the project from:
https://github.com/GrigorijSkult/PokerHandsComparingAlgorithm.git
3. Build the project;
4. Run  method `main` from `holdem\src\main\java\com\cardsstrength\holdem\HoldemApplication.java`;
5. Perform data input described in "General information on the use" and press Enter;
6. Next output is hand list sorted by strength.

Now you can repeat step №5 to calculate a new data.
In the case of the multiple lines input, one line of input corresponds to exactly one line of sequential output.
 


## Testing and validation 
Main classes are covered with unit test. Project contains integration tests with different poker combinations. Validation of input data is realized.


