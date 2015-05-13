package com.training.bd.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.training.bd.models.BidHistory;
import com.training.bd.models.BidPlacement;
import com.training.bd.models.Item;
import com.training.bd.models.ItemFromWeb;
import com.training.bd.models.User;

public class BidHistoryDAOImpl implements BidHistoryDAO {
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }	
	
	private void addBidHistory(BidHistory bidHistory) {				
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	    session.saveOrUpdate(bidHistory);
	    tx.commit();
	    session.close();
	}
	@Override
	public boolean placeBid(BidPlacement bidPlacement) {
		boolean flag = false;
		if(bidPlacement.getPrice() > getHighestBid(bidPlacement.getItemID())){
			BidHistory bh = new BidHistory();
			Item item = new Item();
			item.setItemID(bidPlacement.getItemID());
			User user = new User();
			user.setUserID(bidPlacement.getBidderID());
			bh.setItem(item);
			bh.setUserID(user);
			bh.setPrice(bidPlacement.getPrice());
			addBidHistory(bh);
			flag = true;
		}
		
		return flag;
	}
	private double getHighestBid(int itemID){
		double flag = 0;		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "select max(price) from " + BidHistory.class.getName() + " where itemID = :itemID";
		Query query = session.createQuery( hqlUpdate )
		        .setInteger( "itemID", itemID);
		List<Double> resultSet = (List<Double>) query.list();
		tx.commit();
		session.close();
		if(resultSet.size()>=1)
			flag = resultSet.get(0);
		
		return flag; 		
	}

	@Override
	public List<BidHistory> getBidHistoryOf(int itemID) {
		 Session session = this.sessionFactory.openSession(); 		   
		    List<BidHistory> list =(List<BidHistory>) session.createCriteria(BidHistory.class).list();  		   
		    session.close();
		    return list; 
	}
	
	
	
/*	
	@Override
	public void addBidHistory(BidHistory bidHistory) {				
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	    session.saveOrUpdate(bidHistory);
	    tx.commit();
	    session.close();
	}
	@Override
	public boolean placeBid(BidPlacement bidPlacement) {
		boolean flag = false;
		if(bidPlacement.getPrice() > getHighestBid(bidPlacement.getItemID())){
			BidHistory bh = new BidHistory();
			Item item = new Item();
			item.setItemID(bidPlacement.getItemID());
			User user = new User();
			user.setUserID(bidPlacement.getBidderID());
			bh.setItem(item);
			bh.setUserID(user);
			bh.setPrice(bidPlacement.getPrice());
			addBidHistory(bh);
			flag = true;
		}
		
		return flag;
	}
	
	private double getHighestBid(int itemID){
		double flag = 0;		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "select max(price) from " + BidHistory.class.getName() + " where itemID = :itemID";
		Query query = session.createQuery( hqlUpdate )
		        .setInteger( "itemID", itemID);
		List<Double> resultSet = (List<Double>) query.list();
		tx.commit();
		session.close();
		if(resultSet.size()>=1)
			flag = resultSet.get(0);
		
		return flag; 		
	}
	
	@Override
	public void setStartingPrice(ItemFromWeb item) {
		// TODO Auto-generated method stub
		
	}
	*/
	
}
