1) How many elements can be stored in each of the following arrays?

```java
int [ ] [ ] array = new int [3] [4];
``` 
**12**

```java
String [ ] [ ] names = new String [5] [4];
```
**20**

```java
int [ ] [ ] list = { 6, 5, 4, 6}, { 0, 3, 5, 6}, { 4, 7, 8, 1}, { 5, 4, 9, 2};
```
**16**

2) 

a) Declare an array of double with 10 rows and 5 columns.

`Double[ ][ ] array = new double[10][5];`

b) Declare an array for a tic tac toe board.

`Int[][] board = new int[3][3];`

Given the following declaration and initialization, determine what will be outputted.

```java
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
```

**10987**

b) 
```java
for (int i = 0; i < 3; i++) {

System.out.print(array[i][4]);

}
```

**51015**

c) 
```java
for (int i = 1; i < 2; i++) {

for (int j = 4; j > 0; j--) {

System.out.print(array[i][j]);

}

System.out.println();

}

```

**10987 (Newline afterwards)**

d) 
```java
for (int i = 1; i < 2; i++) {

  for (int j = 4; j > 0; j--) {

    System.out.print(array[i][j]);

  }

  System.out.println();

}
```

**10987 (Newline afterwards)**

_Isn't this the same as c)?_
