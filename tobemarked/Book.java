package tobemarked;

/* Assignment: ICS4U1,  Thursday 12 Dec, 2019
Due Monday 15 Dec. 2019
- 5% per day late. 

NAME: Silas
Date handed in: Monday 15. 2019
*/

/* TODO: Read and answer the questions at the bottom of this class file.
 * Please hand in the answers by 
 * (i) printing them out, (ii) writing them out, 
 * or (iii) saving them in the Handin/harwoodm folder (under the name Book_MH.java where MH are your initials).
 */

public class Book {
	
	String book = "Coral Island";
	
	public static void main(String[] args) {

		Book book = new Book("1: In which we are shipwrecked on a coral island");
		System.out.println(book.getBook());
		for(int i = 0; i < book.chapters.length; i++){
			System.out.println(book.chapters[i]);
			book.printCurrentChapter(i);
			book.chapters[i].end();
			book.printCurrentChapter(i);
		}
	}

	public void printCurrentChapter(int n){
		System.out.println(chapters[n].getN());
	}

	Chapter[] chapters;

	Book (String... chaptersToMake){

		chapters = new Chapter[chaptersToMake.length];
		for(int i = 0; i < chapters.length; i++) {
			chapters[i] = new Chapter(chaptersToMake[i]);
		}
	}
	
	//This will hold information about the current chapter of the book. That's why the book only has 1 chapter.
	class Chapter {
		String title;
		private int n = 1;

		Chapter(String title){
			this.title = title;
		}

		@Override
		public String toString() {
			return title;
		}

		public int getN(){
			return n;
		}

		void end() {
			System.out.println(title + " ends on a cliffhanger");
			n++;
			String title = "chapter ";
			this.title = title + n;
		}
	}

	public String getBook() {
		return book;
	}
}

/* QUESTIONS

0. Put your name and the date that you're handing this in in the comments at the top.

The program has some mistakes and problems. 
Fix them. The program must compile and run and you can't delete classes, methods or variables. 

1. How is it possible that I can have two variables both named book (lines 19 and 22)? 
   (Just to be clear, you should explain what each of the variables is.)

	The book at line 19 is a global variable, and so the local variable book at line 22 will not really cause too many problems unless you want to use the global one.

2. What does the line "Book book = new Book();" mean?

	The Book() constructor allows us to create a new, non-static Book object.

3. What is line 23 actually printing out? "System.out.println(book);"

	Line 23 prints out the book Object from the local variable. Obviously we don't want that.

4. How do I get the program to print out the "Coral Island" that is in the variable book? Explain your solution.

	In creating a getter for the book String, we can just take the title from inside the Book object we made.

5. Write the needed code so that in main() you can set the title of the chapter of the book to "1: In which we are shipwrecked on a coral island";

6. Print out the title of the current chapter of the book.

7. Write a getter to get the number, n, of the chapter.

8. In the book class make a method printCurrentChapter() that calls the chapter number getter and prints out the number. 
   Run this method (printCurrentChapter)from main.
   
9. End the chapter and then print out the current chapter number again.

10. Why does line 40 say "this.title = title + n;" and not "title = title + n;"?

	The title variable in the end method is locally scoped and so in order to reference the global one, this. must be prepended.

Bonus: find a way to get one or two more occurrences of the word book or Book in the line : "Book book = new Book();"
   The program must compile and run and you can't delete classes, methods or variables. 
   (I'm able to get five "books" in that line. Renaming a variable to book_book_book doesn't count.)

   //Bonus
		int boOk = 0;
		Book book = ((Book)(new Book[]{new Book("Book")}[boOk])) == (Book)(new Book[]{new Book("Book")}[boOk]))? (Book)((Book[])(new Book[]{(Book)(new Book("Book")),(Book)(new Book("Book")),(Book)(new Book("Book"))}))[boOk]:(Book)((Book[])(new Book[]{(Book)(new Book("Book")),(Book)(new Book("Book")),(Book)(new Book("Book"))}))[boOk];


*/
