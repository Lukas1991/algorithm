package Design;

import java.util.*;

public class MiniTwitter {

    class Node {
        public int order;
        public Tweet tweet;

        public Node(int o, Tweet t) {
            this.order = o;
            this.tweet = t;
        }
    }

    private Map<Integer, Set<Integer>> friends;
    private Map<Integer, List<Node>> users_tweets;
    private int order;

    public MiniTwitter() {
        this.friends = new HashMap<>();
        this.users_tweets = new HashMap<>();
        this.order = 0;
    }

    public Tweet postTweet(int user_id, String tweet_text) {
        Tweet tweet = Tweet.create(user_id, tweet_text);
        if (!users_tweets.containsKey(user_id)) {
            users_tweets.put(user_id, new LinkedList<>());
        }

        order++;
        users_tweets.get(user_id).add(0, new Node(order, tweet)); //add to front of list
        return tweet;
    }

    /**
     * Get the given user's most recently 10 tweets in his news feed (posted by his friends and himself). Order by timestamp from most recent to least recent.
     */
    public List<Tweet> getNewsFeed(int user_id) {
        List<Node> tmp = new ArrayList<>();
        if (users_tweets.containsKey(user_id))
            tmp.addAll(getRecentTen(users_tweets.get(user_id)));

        if (friends.containsKey(user_id)) {
            for (Integer user : friends.get(user_id)) {
                if (users_tweets.containsKey(user)) {
                    tmp.addAll(getRecentTen(users_tweets.get(user)));
                }
            }
        }

        //sort by order DESC
        Collections.sort(tmp, (n1, n2) -> n2.order - n1.order);

        List<Tweet> res = new ArrayList<>();
        for (int i = 0; i < Math.min(10, tmp.size()); i++) {
            res.add(tmp.get(i).tweet);
        }
        return res;
    }

    /**
     * Get the given user's most recently 10 tweets posted by himself, order by timestamp from most recent to least recent.
     */
    public List<Tweet> getTimeline(int user_id) {
        List<Node> tmp = new ArrayList<Node>();
        if (users_tweets.containsKey(user_id)) {
            tmp.addAll(getRecentTen(users_tweets.get(user_id)));
        }

        List<Tweet> res = new ArrayList<>();
        for (Node node : tmp) {
            res.add(node.tweet);
        }
        return res;
    }

    public List<Node> getRecentTen(List<Node> tmp) {
        List<Node> res = new ArrayList<>();
        for (int i = 0; i < Math.min(10, tmp.size()); i++) {
            res.add(tmp.get(i));
        }
        return res;
    }

    //from_user_id followed to_user_id.
    public void follow(int from_user_id, int to_user_id) {
        Set<Integer> set = friends.getOrDefault(from_user_id, new HashSet<>());
        set.add(to_user_id);
        friends.put(from_user_id, set);
    }

    public void unfollow(int from_user_id, int to_user_id) {
        Set<Integer> set = friends.getOrDefault(from_user_id, new HashSet<>());
        set.remove(to_user_id);
        friends.put(from_user_id, set);
    }
}
