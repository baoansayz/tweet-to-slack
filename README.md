Bao Slack Bot Version 1.0

 
Goal: Make a communication pipe between slack and twitter.
Choose any hashtag you like. If you detect any tweet with this hashtag, post the tweet on slack.


API USED:
twitter4j api - http://twitter4j.org/en/index.html
apache - https://hc.apache.org/downloads.cgi

Current features:
1) Can parse through hashtag or keywords
2) Can use multiple keywords which are in a keyword array
3) Real-time data stream of twitter


Problems encounter:
The http client is recreated after two tweets are posted into slack.
I have yet to find a way to have the client persist and remain open unless hard coded.


Future implementations:
1) Have slack tweet msg when users outputs in a channel
2) Persistent HttpClient that doesn't need to be reinstanated every two iterations
3) Output to multiple channels