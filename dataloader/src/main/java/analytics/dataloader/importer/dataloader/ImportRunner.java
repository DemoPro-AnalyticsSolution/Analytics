package analytics.dataloader.importer.dataloader;
import java.net.UnknownHostException;
import java.util.Set;

import analytics.dataloader.importer.database.SQLDatabaseConnector;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ImportRunner {

	public static void main(String[] args) {
		ImportRunner runner = new ImportRunner();
		//runner.RunTest();
		SQLDatabaseConnector connector = new SQLDatabaseConnector();
		connector.ConnectDatabase();
	}
	public ImportRunner(){
		
	}
	public void RunTest(){
		
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient( "localhost" , 27017 );
			DB db = mongoClient.getDB( "mydb" );
			Set<String> colls = db.getCollectionNames();

			for (String s : colls) {
			    System.out.println(s);
			}
			DBCollection coll = db.getCollection("testCollection");
			
			BasicDBObject doc = new BasicDBObject("name", "MongoDB")
	        .append("type", "database")
	        .append("count", 1)
	        .append("info", new BasicDBObject("x", 203).append("y", 102));
			coll.insert(doc);
			DBObject myDoc = coll.findOne();
			System.out.println(myDoc);
			DBCursor cursor = coll.find();
			try {
			   while(cursor.hasNext()) {
			       System.out.println(cursor.next());
			   }
			} finally {
			   cursor.close();
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				
	}
	
}
