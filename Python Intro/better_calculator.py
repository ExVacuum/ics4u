a = float(input("Enter a Number: "))
b = float(input("Enter Another Number: "))
operator = input("Enter Operator: ")

if operator == "+":
    print(a + b)
elif operator == "-":
    print(a - b)
elif operator == "/":
    print(a / b)
elif operator == "*":
    print(a * b)
else:
    print("Invalid Operator!")