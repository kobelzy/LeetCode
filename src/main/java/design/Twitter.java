package design;

import java.util.*;

/**
 * 355. 设计推特
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：
 *
 * postTweet(userId, tweetId): 创建一条新的推文
 * getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
 * follow(followerId, followeeId): 关注一个用户
 * unfollow(followerId, followeeId): 取消关注一个用户
 * 示例:
 *
 * Twitter twitter = new Twitter();
 *
 * // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
 * twitter.postTweet(1, 5);
 *
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * twitter.getNewsFeed(1);
 *
 * // 用户1关注了用户2.
 * twitter.follow(1, 2);
 *
 * // 用户2发送了一个新推文 (推文id = 6).
 * twitter.postTweet(2, 6);
 *
 * // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
 * // 推文id6应当在推文id5之前，因为它是在5之后发送的.
 * twitter.getNewsFeed(1);
 *
 * // 用户1取消关注了用户2.
 * twitter.unfollow(1, 2);
 *
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * // 因为用户1已经不再关注用户2.
 * twitter.getNewsFeed(1);
 */
public class Twitter {
    private static int timestamp=0;
    private static Map<Integer,User> userMap=new HashMap<>();
    /** Initialize your data structure here. */
    public Twitter() {

    }
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if(!userMap.containsKey(userId)) userMap.put(userId,new User(userId));
        User u=userMap.get(userId);
        u.post(tweetId);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res=new ArrayList<>();
        if(!userMap.containsKey(userId)) return res;
        Set<Integer> users=userMap.get(userId).followed;
        PriorityQueue<Tweet> pq=new PriorityQueue<>(users.size(),(a,b)->(b.time-a.time));
        for (Integer id : users) {
            Tweet twt=userMap.get(id).head;
            if(twt==null) continue;
            pq.add(twt);
        }
        while(!pq.isEmpty()){
            if(res.size()==10) break;
            Tweet twt=pq.poll();
            res.add(twt.id);
            if(twt.next!=null) pq.add(twt.next);
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(!userMap.containsKey(followerId)){
            User u=new User(followerId);
            userMap.put(followerId,u);
        }
        if(!userMap.containsKey(followeeId)){
            User u=new User(followeeId);
            userMap.put(followeeId,u);
        }
        userMap.get(followeeId).follow(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(userMap.containsKey(followerId)){
            User flwer=userMap.get(followerId);
            flwer.unfollow(followeeId);
        }
    }
    
    
    
   private static  class Tweet{
        private int id;
        private int time;
        private Tweet next;

        public Tweet(int id, int time) {
            this.id = id;
            this.time = time;
            this.next = null;
        }
    }
    
    private static class User{
        private int id;
        public Set<Integer> followed;
        public Tweet head;
        
        public User(int userId){
            followed=new HashSet<>();
            this.id=userId;
            this.head=null;
            follow(id);
        }
        public void follow(int userId){
            followed.add(userId);
        }
        public void unfollow(int userId){
            if(userId!=this.id) followed.remove(userId);
        }

        /**
         * 发推文
         * @param tweetId
         */
        public void post(int tweetId){
            Tweet twt=new Tweet(tweetId,timestamp);
            twt.next=head;
            head=twt;
        }


    }

}
