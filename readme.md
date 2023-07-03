![](hkbu.png)
# COMP2026 Programming Assignment 2 - King Up!

## 2021-22 Semester 1

* Designed by: [Dr. Kevin Wang](mailto:kevinw@comp.hkbu.edu.hk)
* Q & A: [Piazza](https://piazza.com/class/kodsr5zs2km5ls)
* Due: 
  * UAT Due: 11:59am (noon), 3rd November, 2021 (Wednesday)
  * Programming Due: 23:59pm (midnight), 13th November, 2021 (Saturday) 
* Download the starter code: [here](skeleton.zip)
* Download the demo program: [here](demo.jar)
* Download everything from the assignment: [here](https://github.com/khwang0/COMP2026-2122PA2/archive/refs/heads/master.zip)

> To run the demo program type the following in your terminal:
> ```java
> java -jar demo.jar
> ```


## Introduction

You are given the opportunity to get familiar with the concept of class, object instantiation, methods, data attributes, and visibility. In this assignment we are building a board game called [King Up!](https://www.youtube.com/watch?v=T5c9dS001ac). This board game is simple to play and we will describe how the game works during the class. The details of how the game work can be founded in this link: [Rule of the Game](https://www.ultraboardgames.com/king-up/game-rules.php). You can also review the rules of the game from the following video links:
* [Cantonese Game Demo, Jolly Thinkers Boardgame Channel, Youtube](https://www.youtube.com/watch?v=3tS5nZz6p4Y)
* [English Game Demo, Youtube](https://www.youtube.com/watch?v=T5c9dS001ac)

![king-up logo](king-up.jpg)

## Changes of rule set in our assignments

We follow all rules described in the [Rule of the Game](https://www.ultraboardgames.com/king-up/game-rules.php) except we only play the game for one round.

We assume the game is played by one human player and three computer players. That means all decisions made by computer players (placing the character, moving the character and vote) will be done automatically while all decisions are all done by the user.




---

# Classes

You should started with the given skeleton code which can be downloaded here: 
* [Character](Character.java)
* [Gameboard](Gameboard.java)
* [Player](Player.java)

There are some descriptions for the classes and their methods that you are required to complete. 


---

## Demo

A sample program can be found [here](demo.jar). The sample program provides you an understanding of the program. You do not need to follow the exact wording and output format of the program. 

## Programming Style and Documentation 

Good programming style (indentation, comments) are always essential.  Blank lines, spaces between operators/variables (wherever appropriate) and meaningful variable names are required. Your program should be properly indented.  Good choice of variable names and method names is also essential.  Your program must have proper internal documentation, as described below: 

### Header Block 
For your java file, there must be a header at the beginning of the file, with your name and your UID. 

### Inline Comments 
Wherever necessary and appropriate, you should add inline comments to explain the execution flow of your program. 

 

## Submission 
For submission, zip the src folder of your IntelliJ project, name the zip file as “XXXXXXXX_assign2.zip” (where XXXXXXXX is your Student ID number), and upload it to Moodle.  If you go for the bonus marks, you should also submit your short explanation inside this zip file. 

Please be reminded that both the **Late Penalty Rule** and the **Penalty for Plagiarism** are applied strictly to all submissions of this course (including this assignment).   

### Late Penalty Rule

```java
if (lateHour > 0) {
    if (lateHour < 24) 
        mark *= 0.8;
    else if (lateHour < 48)
        mark = mark >> 1;
        else if (lateHour < 72)
            mark = mark >> 2;
            else
                mark &= 0;
}
```
 
 ### Plagiarism

 Plagiarism is a serious offence and can be easily detected. Please don't share your code to your classmate even if they are threatening you with friendship. If they don't have the ability to work on something that can compile, they would not be able to change your code to a state that we can't detect the plagiarism. For the first commit of plagiarism, regardless you shared your code or copied code from others, you will receive 0 with an addition of 5 mark penalty. If you commit plagiarism twice, your case will be presented in the exam board and you receive a F directly.
 

### Plagiarism

 Plagiarism is a serious offence and can be easily detected. Please don't share your code to your classmate even if they are threatening you with friendship. If they don't have the ability to work on something that can compile, they would not be able to change your code to a state that we can't detect the act of plagiarism. For the first commit of plagiarism, regardless you shared your code or copied code from others, you will receive 0 with an addition of 5 mark penalty. If you commit plagiarism twice, your case will be presented in the exam board and you will receive a F directly.

## Marking Scheme 
This assignment is worth 9% of the course mark.  There are three elements in the marking scheme: 
* 5% - Understanding the Assignment Test (UAT)
* 90% - a working program that functions as specified 
* 5% - Programming style and documentation 
* -30% - If you define any non-private fields (instance variable) or non-private method
  
Please note that submitting a program that cannot be compiled would result in a very low mark. Unlike assignment 1, you can define your private fields and any private method. However, these fields and methods must be private.


 

## Interview 
Should the teaching team see fit, students may be requested to attend an interview to explain about their program.  Students failing to attend such interview or to demonstrate a good understanding of their own program may result in mark deduction. 

## Understanding the Assignment (UAT)

Answer the following question that on Moodle.
Due: 3/11/2021 (12:00nn)
1. Please state the order of Scoring stage, Placing stage, and Playing stage. 
2. How many characters needed to be placed? Which method is responsible to do it?
3. What will happen if a player has running out of veto card? Which variable in the program is responsible to store the number of veto card?

