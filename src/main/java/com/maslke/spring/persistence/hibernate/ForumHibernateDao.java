package com.maslke.spring.persistence.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

public class ForumHibernateDao extends BaseDao {
    public void addForum(Forum forum) {
        getHibernateTemplate().save(forum);
    }

    public void updateForum(Forum forum) {
        getHibernateTemplate().update(forum);
    }

    public Forum getForum(int forumId) {
        return getHibernateTemplate().get(Forum.class, forumId);
    }

    public List<Forum> findFormByName(String forumName) {
        return (List<Forum>) getHibernateTemplate().find("from Forum f where f.forumName like ?", forumName + "%")
    }

    public long getForumNum() {
        Object obj = getHibernateTemplate().iterate("select count(f.forumId) from Forum f").next();
        return (Long) obj;
    }

    public long getForumNum2() {
        Long forumNum = getHibernateTemplate().execute(new HibernateCallback<Long>() {
            public Long doInHibernate(Session session) throws HibernateException {
                Object obj = session.createQuery("select count(f.forumId) from Forum f")
                    .list().iterator().next();
                return (Long) obj;
            }
        })
    }
}
