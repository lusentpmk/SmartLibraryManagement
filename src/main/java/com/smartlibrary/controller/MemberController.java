package com.smartlibrary.controller;
import com.smartlibrary.model.Member;
import com.smartlibrary.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class MemberController {
  public List<Member> findAll() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    List<Member> list = session.createQuery("from Member", Member.class).list();
    session.close();
    return list;
  }
  public void save(Member member) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    if (member.getId() == 0) {
      session.save(member);
    } else {
      session.update(member);
    }
    tx.commit();
    session.close();
  }
  public void delete(Member member) {
    delete(member.getId());
  }
  public void delete(int memberId) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    session.createQuery("delete from BorrowRecord br where br.member.id = :id")
        .setParameter("id", memberId)
        .executeUpdate();
    Member member = session.get(Member.class, memberId);
    if (member != null) {
      session.delete(member);
    }
    tx.commit();
    session.close();
  }
  public Member findById(int id) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Member member = session.get(Member.class, id);
    session.close();
    return member;
  }
}
