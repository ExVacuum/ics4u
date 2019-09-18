from random import *

answer = randint(0, 100)
while True:
    guess = int(input("Guess a number between 0 and 100: "))
    if guess == answer:
        print("Got it!")
        break
    elif guess > answer:
        print("Too high!")
    else:
        print("Too low!")
