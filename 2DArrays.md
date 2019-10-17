1) How many elements can be stored in each of the following arrays?

int [ ] [ ] array = new int [3] [4]; _12_

String [ ] [ ] names = new String [5] [4] _20_

int [ ] [ ] list = { 6, 5, 4, 6}, _16_

                   { 0, 3, 5, 6},

                   { 4, 7, 8, 1},

                   { 5, 4, 9, 2};

2) 

a) Declare an array of double with 10 rows and 5 columns.

_Double[ ][ ] array = new double[10][5];_

b) Declare an array for a tic tac toe board.

_Int[][] board = new int[3][3];_

Given the following declaration and initialization, determine what will be outputted.

int [ ] [ ] array = new int [3][5];

int k=0;

for (int i = 0; i < 3; i++) {

for (int j = 0; j < 5; j++) {

k = k + 1;

array[i][j] = k;

}

}

a) 
for (int j = 4; j > 0; j--) {

System.out.print(array[1][j]);

}

_10987_

b) 
for (int i = 0; i < 3; i++) {

System.out.print(array[i][4]);

}

_51015_

c) for (int i = 1; i < 2; i++) {

for (int j = 4; j > 0; j--) {

System.out.print(array[i][j]);

}

System.out.println();

}

_10987 (Newline afterwards)_

d) 
for (int i = 1; i < 2; i++) {

for (int j = 4; j > 0; j--) {

System.out.print(array[i][j]);

}

System.out.println();

}

_10987 (Newline afterwards)_

**Isn't this the same as c)?**
