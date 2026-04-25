package com.smartlibrary.controller;
import com.smartlibrary.model.Book;
import com.smartlibrary.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class BookController {
  public List<Book> findAll() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    List<Book> list = session.createQuery("from Book", Book.class).list();
    session.close();
    return list;
  }
  public List<Book> findAvailable() {
    List<Book> result = new ArrayList<Book>();
    List<Book> all = findAll();
    for (Book book : all) {
      if (book.isAvailable()) {
        result.add(book);
      }
    }
    return result;
  }
  public void save(Book book) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    if (book.getId() == 0) {
      book.setAvailable(true);
      session.save(book);
    } else {
      session.update(book);
    }
    tx.commit();
    session.close();
  }
  public void delete(Book book) {
    delete(book.getId());
  }
  public void delete(int bookId) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    session.createQuery("delete from BorrowRecord br where br.book.id = :id")
        .setParameter("id", bookId)
        .executeUpdate();
    Book book = session.get(Book.class, bookId);
    if (book != null) {
      session.delete(book);
    }
    tx.commit();
    session.close();
  }
  public Book findById(int id) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Book book = session.get(Book.class, id);
    session.close();
    return book;
  }
}
