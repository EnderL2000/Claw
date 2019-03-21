# Claw
# A Point and Click Game Manager
### What is Claw?
Claw is, at its core, a point-and-click game manager. It tracks the states of `Screen`s, `Item`s, and `Portal`s, with which the user 
can interact with to move through the world. It is GUI agnostic and works (theoretically) with any game engine you would want to
use; It even works if you make your own engine! Due to it's limited responsibility, Claw can remain lightweight and simple to use.

### What isn't Claw?
Claw is **NOT** a game engine. There is no graphical capabilities added in, no game loops, no input handling etc. Instead, Claw acts 
as the middle man, collecting your input and then determining what your new game state is.

### How do I use Claw?
Luckily, Claw is simple to use (at least I think so). However, the current version requires heavy use of anonymous objects and lambda
expressions to use elegantly. Claw can still be used without these more advanced features, at the expense of writing a few more lines 
of code.

### The Claw method
The goal of Claw is to essentially serve as the "backend" to a point and click adventure/puzzle game. You will need to provide input
handling and graphical capabilities to make the game function. The reason for this design is so YOU have the option to make your game 
have the feel you want in these areas.
##### Input
Although you may collect your inputs however you want, mouse click events you want Claw to register must be normalized to 0 to 100 on
each axis. This means the screen can be displayed at any resolution you would like, Claw works off of percentages of the screen. For 
example, in 1080p resolution, a mouse click at x = 576, y = 216 would translate in Claw to x = 30, y = 20 (30% of 1920 is 576, 20% of 
1080 is 216). From there, Claw computes the correct action.
##### Graphical Display
Displaying graphics could be as simple as displaying the image based off of Claw's `currentScreen`. However, it is possible that your
scenes will likely be more complex than just static images. For this, it is recommended to draw `Item`s separately. For example, a
lever that actually moves when it is clicked could read the `state` of the `Item` it represents and update a sprite. That sprite 
could then be drawn based off of that `state`. Alternatively, `Item`s and `Portal`s provide a value, titled `associations`, that is a 
list of any data you wish to store (even null!). Feel free to mix and match data types. This is purely for making the development 
easier for the game developer, Claw won't touch that data. This could be a place to store animation data, for example.

### So, how do I start?
I would not recommend using Claw in its current state. It has had limited testing and some parts will likely be subject to various 
rewrites in the early stages of the project. If you decide you want to use it anyways, simply copy the files right into your project's
main directory and get going. If you need any reference, everything (I hope) is covered in `ClawTest.kt`. A tutorial will come at a 
later date, when claw is more stable.
