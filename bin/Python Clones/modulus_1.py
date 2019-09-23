# Silas Bartha, Sept. 18, 2019, Modulus Review 1 Clone
# Print 2 Columns, 0-100 in one, 0-7 repeating in the other.
for i in range(100):
    print(f'{i:<5d}{i%8:<5d}')
