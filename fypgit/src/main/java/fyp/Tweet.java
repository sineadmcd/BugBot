/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
/**
 *
 * @author user
 */
public class Tweet {
    
    public Tweet(String message) 
    {
        //Your Twitter App's Consumer Key
        String consumerKey = "MPRvsxESBACZpVvkXO8bZffy4";
        //Your Twitter App's Consumer Secret
        String consumerSecret = "7yuUMsTbceXtQdlKfWCy6pWJMFWNtAvjjK9mAP6DpkDxSPVpC3";
        //Your Twitter Access Token
        String accessToken = "786214539230482435-a6dxzz1tNCg2uGE0lR0IHQ46WASySHY";
        //Your Twitter Access Token Secret
        String accessTokenSecret = "9DaEhd0KNJrSXLrTLIFvV0ouE5rkCP5W8pZTZhM9T9yjg";
        //Instantiate a re-usable and thread-safe factory
        TwitterFactory twitterFactory = new TwitterFactory();
        //Instantiate a new Twitter instance
        Twitter twitter = twitterFactory.getInstance();
        //setup OAuth Consumer Credentials
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        //setup OAuth Access Token
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
        //Instantiate and initialize a new twitter status update
        StatusUpdate statusUpdate = new StatusUpdate(message);
      
        try {
            //tweet or update status
            Status status = twitter.updateStatus(statusUpdate);
        } catch (TwitterException ex) {
        }
  }

    
}
