
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;



public class Slackbot{
	
	public static void main(String[] args) throws IOException{
	    
		//config to access twitter API
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setOAuthConsumerKey("6j1GwRT84MR3ywGVqWgbN9N3P")
							.setOAuthConsumerSecret("829IlFyUe1m1NLGIemfh7CVY2BnmCd46nzLFDWcbxA5zOMIiQs")
							.setOAuthAccessToken("82011425-4OZAy81bHMQ0IZMckFutcF9uWiKcIrJGpWUdhO4L5")
							.setOAuthAccessTokenSecret("iPU3tYhAMal6hLQGTabkVruwnhBBrGud41zMDInck4wFd");

		//establish connection to twiiter
		TwitterStream twitterStream 	= new TwitterStreamFactory(configurationBuilder.build())
									.getInstance();
		
		StatusListener statusListener = new StatusListener() {
	    @Override
	    public void onStatus(Status status) {
	    	
	    	// The main section that you get the tweet.
	    	System.out.println(status.getText());
	    	
	    // creates httpclient 
	    	HttpClient httpClient = (HttpClient) HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost("https://hooks.slack.com/services/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");
		httppost.addHeader("Content-type", "application/json");
	  	StringEntity params = new StringEntity("{\"text\" : \""+status.getText()+"\"}","UTF-8");
		params.setContentType("application/json");
	    httppost.setEntity(params);
	    
	    //execute and send to slack
	    try {
			httpClient.execute(httppost);
		} 
	    catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	    }

	    @Override
	    public void onDeletionNotice(StatusDeletionNotice sdn) {
	    		throw new UnsupportedOperationException("Not supported yet."); 
	    }

	    @Override
	    public void onTrackLimitationNotice(int i) {
	    throw new UnsupportedOperationException("Not supported yet."); 
	    }

	    @Override
	    public void onScrubGeo(long l, long l1) {
	    throw new UnsupportedOperationException("Not supported yet."); 
	    }

	    @Override
	    public void onStallWarning(StallWarning sw) {
	    throw new UnsupportedOperationException("Not supported yet.");
	    }

	    @Override
	    public void onException(Exception arg0) {
	    	// TODO Auto-generated method stub
	    }
	    };
	            
	    // where filtering occurs
	    FilterQuery fq = new FilterQuery();  
	    //hashtags/keywords to find
	    String keywords[] = {"#cnn"};
	            
	    fq.track(keywords);
	    twitterStream.addListener(statusListener);
	    twitterStream.filter(fq);          
		

	}
}
