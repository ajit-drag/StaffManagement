package com.mindtree.coe.staffmanagement.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mindtree.coe.staffmanagement.entity.Member;
import com.mindtree.coe.staffmanagement.entity.MemberType;
import com.mindtree.coe.staffmanagement.exceptions.DaoException;
import com.mindtree.coe.staffmanagement.interfaces.dao.Dao;

public class DaoImpl extends DBConnection implements Dao {

	SessionFactory sessionFactory;

	public DaoImpl() throws DaoException {
		try {
			sessionFactory = getSessionFactory();
		} catch (Exception  exception) {
			System.out.println(exception);
			throw new DaoException("Some error related to database connection.", exception);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getAllMembers() throws DaoException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Member> memberList;
		try {
			tx = session.beginTransaction();
			memberList = session.createQuery("From Member").list();
			tx.commit();
		} catch (HibernateException hibernateException) {
			if (tx != null)
				tx.rollback();
			throw new DaoException("Some error related to database connection.", hibernateException);
		}finally {
			session.close();
		}
		return memberList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getMemberTypeWise(MemberType memberType) throws DaoException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Member> memberList = null;
		try {
			tx = session.beginTransaction();
			Query query  = session.createQuery("From Member m where m.memberType=:membertype");
			query.setParameter("membertype", memberType);
			memberList = query.list();
			tx.commit();
		} catch (HibernateException hibernateException) {
			if (tx != null)
				tx.rollback();
			throw new DaoException("Some error related to database connection.", hibernateException);
		}finally {
			session.close();
		}
		return memberList;
	}

}
