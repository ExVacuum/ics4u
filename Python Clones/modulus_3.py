# Silas Bartha, Sept. 18, 2019, Modulus Practice 3 Clone
for i in range(1, 201):

    # Print Integers From 1 to 200, Inserting Line Break after Every Multiple of 12.
    print(f'{i:<3d}', end=' ')
    if i % 12 == 0:
        print()
