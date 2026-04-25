package com.smartlibrary.util;

import com.smartlibrary.model.Book;
import com.smartlibrary.model.Member;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DataPopulator {
  public static void populate() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();

    try {
      // Insert 40 famous books
      String[][] booksData = {
          {"To Kill a Mockingbird", "Harper Lee", "1960"},
          {"1984", "George Orwell", "1949"},
          {"Pride and Prejudice", "Jane Austen", "1813"},
          {"The Great Gatsby", "F. Scott Fitzgerald", "1925"},
          {"Jane Eyre", "Charlotte Brontë", "1847"},
          {"Wuthering Heights", "Emily Brontë", "1848"},
          {"The Catcher in the Rye", "J.D. Salinger", "1951"},
          {"The Hobbit", "J.R.R. Tolkien", "1937"},
          {"The Lord of the Rings", "J.R.R. Tolkien", "1954"},
          {"Harry Potter and the Philosopher's Stone", "J.K. Rowling", "1997"},
          {"The Chronicles of Narnia", "C.S. Lewis", "1950"},
          {"Moby Dick", "Herman Melville", "1851"},
          {"War and Peace", "Leo Tolstoy", "1869"},
          {"Crime and Punishment", "Fyodor Dostoevsky", "1866"},
          {"The Brothers Karamazov", "Fyodor Dostoevsky", "1879"},
          {"Don Quixote", "Miguel de Cervantes", "1605"},
          {"The Odyssey", "Homer", "800"},
          {"The Iliad", "Homer", "800"},
          {"Hamlet", "William Shakespeare", "1603"},
          {"Macbeth", "William Shakespeare", "1606"},
          {"A Tale of Two Cities", "Charles Dickens", "1859"},
          {"Oliver Twist", "Charles Dickens", "1838"},
          {"Great Expectations", "Charles Dickens", "1861"},
          {"The Picture of Dorian Gray", "Oscar Wilde", "1890"},
          {"Frankenstein", "Mary Shelley", "1818"},
          {"The Strange Case of Dr. Jekyll and Mr. Hyde", "Robert Louis Stevenson", "1886"},
          {"Treasure Island", "Robert Louis Stevenson", "1882"},
          {"Alice in Wonderland", "Lewis Carroll", "1865"},
          {"The Adventures of Huckleberry Finn", "Mark Twain", "1884"},
          {"The Adventures of Tom Sawyer", "Mark Twain", "1876"},
          {"Little Women", "Louisa May Alcott", "1868"},
          {"Anne of Green Gables", "Lucy Maud Montgomery", "1908"},
          {"The Secret Garden", "Frances Hodgson Burnett", "1911"},
          {"Beloved", "Toni Morrison", "1987"},
          {"The Handmaid's Tale", "Margaret Atwood", "1985"},
          {"Dune", "Frank Herbert", "1965"},
          {"Ender's Game", "Orson Scott Card", "1985"},
          {"Neuromancer", "William Gibson", "1984"},
          {"The Foundation", "Isaac Asimov", "1951"},
          {"Slaughterhouse-Five", "Kurt Vonnegut", "1969"}
      };

      for (String[] data : booksData) {
        Book book = new Book();
        book.setTitle(data[0]);
        book.setAuthor(data[1]);
        book.setYear(data[2]);
        book.setAvailable(true);
        session.save(book);
      }

      // Insert 20 members
      String[][] membersData = {
          {"Emily Chen", "emily.chen@library.edu", "555-0101"},
          {"James Morrison", "james.morrison@library.edu", "555-0102"},
          {"Sarah Johnson", "sarah.johnson@library.edu", "555-0103"},
          {"Michael Williams", "michael.williams@library.edu", "555-0104"},
          {"Lisa Anderson", "lisa.anderson@library.edu", "555-0105"},
          {"David Brown", "david.brown@library.edu", "555-0106"},
          {"Jennifer Lee", "jennifer.lee@library.edu", "555-0107"},
          {"Robert Taylor", "robert.taylor@library.edu", "555-0108"},
          {"Maria Garcia", "maria.garcia@library.edu", "555-0109"},
          {"Christopher Martinez", "chris.martinez@library.edu", "555-0110"},
          {"Amanda White", "amanda.white@library.edu", "555-0111"},
          {"Daniel Harris", "daniel.harris@library.edu", "555-0112"},
          {"Jessica Clark", "jessica.clark@library.edu", "555-0113"},
          {"Matthew Lewis", "matthew.lewis@library.edu", "555-0114"},
          {"Rachel Thompson", "rachel.thompson@library.edu", "555-0115"},
          {"Ryan Robinson", "ryan.robinson@library.edu", "555-0116"},
          {"Laura Jackson", "laura.jackson@library.edu", "555-0117"},
          {"Kevin Walker", "kevin.walker@library.edu", "555-0118"},
          {"Nicole Hall", "nicole.hall@library.edu", "555-0119"},
          {"Brandon Young", "brandon.young@library.edu", "555-0120"}
      };

      for (String[] data : membersData) {
        Member member = new Member();
        member.setName(data[0]);
        member.setEmail(data[1]);
        member.setPhone(data[2]);
        session.save(member);
      }

      tx.commit();
      System.out.println("✓ Successfully populated 40 books and 20 members!");
    } catch (Exception ex) {
      tx.rollback();
      System.err.println("✗ Error populating data: " + ex.getMessage());
      ex.printStackTrace();
    } finally {
      session.close();
    }
  }

  public static void main(String[] args) {
    populate();
  }
}
