package com.maslke.spring.persistence.hibernate;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author:maslke
 * @date:3/19/2019
 * @version:0.0.1
 */
@Entity
@Table(name = "T_POST")
public class Post implements Serializable {
    private static final long serialVersionUID = 3945368297380046483L;

    @Id
    @Column(name = "POST_ID")
    private int postId;

    @Column(name = "USER_ID")
    private int userId;


    @ManyToOne
    @Column(name = "TOPIC_ID")
    private Topic topic;

    @Lob
    @Column(name = "POST_TEXT")
    private String postText;

    @Lob
    @Column(name = "POST_ATTACH")
    private byte[] postAttach;

    @Column(name = "POST_TIME")
    private Date postTime;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public byte[] getPostAttach() {
        return postAttach;
    }

    public void setPostAttach(byte[] postAttach) {
        this.postAttach = postAttach;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
