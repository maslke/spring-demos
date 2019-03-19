package com.maslke.spring.persistence.hibernate;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class PostHibernateDao extends BaseDao {
    public void addPost(Post post) {
        getHibernateTemplate().save(post);
    }
}
