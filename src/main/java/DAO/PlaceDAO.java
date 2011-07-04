package DAO;

import hibernate.HibernateUtil;
import model.Place;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class PlaceDAO extends BaseDAO {

    public static Place retrievePlacesbyName(String name) {
        Place result = null;
        Session session = HibernateUtil.getSession();
        String query = "from Place where name ='" + name + "' ";
        result = (Place) session.createQuery(query).uniqueResult();
        return result;
    }

     public static List<Place> retrievePlaceList() {
       return HibernateUtil.getSession().createCriteria(Place.class).list();

     }

    public static Place retrievePlacesbyId(long id) {
        Place result = null;
        Session session = HibernateUtil.getSession();
        String query = "from Place where placeId ='" + id + "' ";
        result = (Place) session.createQuery(query).uniqueResult();
        return result;
    }

}
