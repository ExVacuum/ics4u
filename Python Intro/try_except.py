while True:
    try:
        number = int(input("Enter a number: "))
        break
    except ValueError:
        print("Not a valid integer!")
print(number)
