package com.amazonaws.lambda.recording;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

//import com.amazonaws.lambda.demo.Thing.State;
//import com.amazonaws.lambda.demo.Thing.State.Document;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class RecordingDeviceInfoHandler implements RequestHandler<Thing, String>{

	   private DynamoDB dynamoDb;
	    private String DYNAMODB_TABLE_NAME = "DeviceData";

	    @Override
	    public String handleRequest(Thing input, Context context) {
	        this.initDynamoDbClient();

	        persistData(input);
	        return "Success in storing to DB!";
	    }

	    private PutItemOutcome persistData(Thing thing) throws ConditionalCheckFailedException {

	        // Epoch Conversion Code: https://www.epochconverter.com/
	        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
	        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
	        String timeString = sdf.format(new java.util.Date (thing.timestamp*1000));

	        return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME)
	                .putItem(new PutItemSpec().withItem(new Item().withPrimaryKey("time", thing.timestamp)
	                        .withString("temperature", thing.state.reported.temperature)
	                        .withString("LED", thing.state.reported.LED)
	                        .withString("Buzzer", thing.state.reported.Buzzer)
	                        .withString("timestamp",timeString)));
	    }

	    private void initDynamoDbClient() {
	        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("ap-northeast-2").build();

	        this.dynamoDb = new DynamoDB(client);
	    }


}


class Thing {
    public State state = new State();
    public long timestamp;

    public class State {
        public Tag reported = new Tag();
        public Tag desired = new Tag();

        public class Tag {
            public String temperature;
            public String LED;
            public String Buzzer;
        }
    }
}