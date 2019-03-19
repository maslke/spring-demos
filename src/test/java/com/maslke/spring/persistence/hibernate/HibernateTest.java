package com.maslke.spring.persistence.hibernate;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.util.FileCopyUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

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

    @Autowired
    private PostHibernateDao postHibernateDao;


    @Test
    public void testHibernateAdd() {
        Forum forum = new Forum();
        forum.setForumId(1);
        forum.setForumDesc("desc");
        forum.setForumName("forum1");
        forumHibernateDao.addForum(forum);


        Forum forum1 = forumHibernateDao.getForum(1);
        assertEquals(forum1.getForumName(), "forum1");
        assertEquals(forum1.getForumDesc(), "desc");
    }

    @Test
    public void testHibernateAddPost() throws Exception {
        Resource resource = new ClassPathResource("qqshow.jpg");
        byte[] imgFile = FileCopyUtils.copyToByteArray(resource.getFile());
        Post post = new Post();
        post.setPostId(1);
        post.setUserId(1);
        post.setPostText("人之初性本善");
        post.setPostAttach(imgFile);
        post.setPostTime(new Date());
        postHibernateDao.addPost(post);
    }
}
