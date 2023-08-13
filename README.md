# Arkanoid
Final product of a 5-part-semester project in Java. This project was coded as a five-part ongoing assignment on the 2nd semester of my 1st year at `Bar Ilan University`. 

# Compiling and Running

You must have Java SE 10 or higher installed on your machine. 
You can download the entire source code and run the game via any Java IDEA, if you do be sure to also download the provided *biuoop-1.4.jar* (located [here](./biuoop-1.4.jar)).
Compiling the code should work using the command:

`ant compile`

and running it with (this command will run the default levels order):

`ant run`

if you want to specify the levels of the game it can be done easilly using this (explained below):

`ant -Dargs="levels numbers" run`

# Creating and Editing Levels
This is a special arkanoid game - You get to choose which levels will be played and their order!
In order to add or change the levels order in the game, you don't have to learn to code! 
Inside the [definitions folder](./resources/definitions) you will find simple txt files. The [default levels](./resources/Default_Levels.txt) file defines each level's charateristics in the game. By writing in this file you can edit the design of the game. Without coding, you can change anything from the name of the level, the images, the number of balls, the velocities, the blocks, etc.

Each level has it own charateristics like name m background animation, the number of balls, the velocities, the blocks, etc.
if you want to specify the levels of the game it can be done easilly using this command:

`ant -Dargs="levels numbers" run`

for example, this command will run the levels 1, 1 again, 3 and 2, by this order:

`ant -Dargs="1 1 3 2" run`

# GamePlay

To control the paddel use the arraow keys on your keyboard.
Note that the paddel has 5 areas which affect the ball moving angle.
Each block will earn you 5 points and each level completed 100 points.
You can pause the game using the `p` key on your keybard, and continue using the `space` key.

You win if you finish all levels.
You lose if you run out of balls during a level.
Either way, you'll get a message of how many points you collected during the game!

![level1](./screenshots/level1.png)
![level2](./screenshots/level2.png)
![level3](./screenshots/level3.png)
