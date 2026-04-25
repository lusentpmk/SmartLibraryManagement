package com.smartlibrary.controller;
import com.smartlibrary.model.Book;
import com.smartlibrary.model.BorrowRecord;
import com.smartlibrary.model.Member;
import com.smartlibrary.util.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class BorrowController {
  public List<BorrowRecord> findAll() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    List<BorrowRecord> list = session.createQuery("from BorrowRecord", BorrowRecord.class).list();
    session.close();
    return list;
  }
  public void borrow(Book book, Member member) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    book.setAvailable(false);
    session.update(book);
    BorrowRecord record = new BorrowRecord();
    record.setBook(book);
    record.setMember(member);
    record.setBorrowDate(new Date());
    record.setReturned(false);
    session.save(record);
    tx.commit();
    session.close();
  }
  public void returnBook(BorrowRecord record) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    record.setReturned(true);
    record.setReturnDate(new Date());
    Book book = record.getBook();
    book.setAvailable(true);
    session.update(book);
    session.update(record);
    tx.commit();
    session.close();
  }
}
