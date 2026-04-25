package com.smartlibrary.model;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class BorrowRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "book_id")
  private Book book;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "member_id")
  private Member member;
  @Temporal(TemporalType.DATE)
  private Date borrowDate;
  @Temporal(TemporalType.DATE)
  private Date returnDate;
  private boolean returned;
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public Book getBook() {
    return book;
  }
  public void setBook(Book book) {
    this.book = book;
  }
  public Member getMember() {
    return member;
  }
  public void setMember(Member member) {
    this.member = member;
  }
  public Date getBorrowDate() {
    return borrowDate;
  }
  public void setBorrowDate(Date borrowDate) {
    this.borrowDate = borrowDate;
  }
  public Date getReturnDate() {
    return returnDate;
  }
  public void setReturnDate(Date returnDate) {
    this.returnDate = returnDate;
  }
  public boolean isReturned() {
    return returned;
  }
  public void setReturned(boolean returned) {
    this.returned = returned;
  }
}
