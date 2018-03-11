package Design;

public class Tweet {
    public int id;
    public int user_id;
    public String text;

    public Tweet() {

    }

    // This will create a new tweet object, and auto fill id
    public static Tweet create(int user_id, String tweet_text) {
        Tweet tweet = new Tweet();
        return tweet;
    }
}
