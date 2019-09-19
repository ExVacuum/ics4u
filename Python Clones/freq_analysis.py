import operator

file = open("../beyondgoodandevil.txt", "r")
freqs = {"A": 0, "B": 0, "C": 0, "D": 0, "E": 0, "F": 0, "G": 0, "H": 0, "I": 0, "J": 0, "K": 0, "L": 0, "M": 0, "N": 0,
         "O": 0, "P": 0, "Q": 0, "R": 0, "S": 0, "T": 0, "U": 0, "V": 0, "W": 0, "X": 0, "Y": 0, "Z": 0}
for line in file.readlines():
    for char in line:
        if freqs.get(char.upper(), False) is not False:
            freqs[char.upper()] += 1

sorted_freqs = sorted(freqs.items(), key=operator.itemgetter(1))
print('Lowest Frequencies:')
for i in range(5):
    print(f'{sorted_freqs[i][0]}: {sorted_freqs[i][1]}')
print('Highest Frequencies:')
for i in range(1, 6):
    print(f'{sorted_freqs[-i][0]}: {sorted_freqs[-i][1]}')
