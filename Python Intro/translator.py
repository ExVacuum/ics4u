# Beanify Language

def translate(phrase):
    translation = ""
    for char in phrase:
        if char.upper() in "AEIOU":
            if char.isupper():
                translation += "Bean"
            else:
                translation += "bean"
        else:
            translation += char
    return translation


print(translate(input("Enter a phrase to be translated: ")))
