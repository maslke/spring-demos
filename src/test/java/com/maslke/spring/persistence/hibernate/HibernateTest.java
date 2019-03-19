package com.maslke.spring.persistence.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * @author:maslke
 * @date:3/19/2019
 * @version:0.0.1
 */
@Test
@ContextConfiguration(locations = {"classpath:spring/hibernate/context.xml"})
public class HibernateTest extends AbstractTransactionalTestNGSpringContextTests {


    @Autowired
    private ForumHibernateDao forumHibernateDao;


    @Test
    public void testHibernateAdd() {
        Forum forum = new Forum();
        forum.setForumId(1);
        forum.setForumDesc("desc");
        forum.setForumName("forum1");
        forumHibernateDao.addForum(forum);
    }
}
