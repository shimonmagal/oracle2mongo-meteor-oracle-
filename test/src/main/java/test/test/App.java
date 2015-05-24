package test.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import oracle2mongo.Oracle2Mongo;

import org.bson.Document;
import org.json.simple.parser.ParseException;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, ParseException {
		Oracle2Mongo om = new Oracle2Mongo("jdbc:oracle:thin:test/test@localhost:1521:xe", "localhost:3001", 22, "conf.json", "meteor");
		om.replicate();
		
		
		/*Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:test/test@localhost:1521:xe");

		long t = System.currentTimeMillis();
		System.out.println(System.currentTimeMillis() - t + "***");
		PreparedStatement ps = con.prepareStatement("select l.*,l.ora_rowscn from events_log l");
		System.out.println(System.currentTimeMillis() - t + "***");
		PreparedStatement ps2 = con.prepareStatement("select * from tasks");
		System.out.println(System.currentTimeMillis() - t + "***");
		ResultSet rs = ps.executeQuery();
		System.out.println(System.currentTimeMillis() - t);
		ResultSetMetaData md = rs.getMetaData();
		System.out.println(System.currentTimeMillis() - t);
		long maxScn = 0;
		List<LogEvent> logEvents = new LinkedList<LogEvent>();
		while(rs.next()){
			LogEvent le = new LogEvent();
			long scn = rs.getLong("ORA_ROWSCN");
			le.scn = scn;
			String fields = rs.getString("FIELDS");
			Set<String> fieldsSet = splitAndNormalize(fields);
			le.fields = fieldsSet;
			String ids = rs.getString("IDS");
			Set<Long> idsSet = convertToLongs(splitAndNormalize(ids));
			le.ids = idsSet;
			String table = rs.getString("TABLE_NAME");
			logEvents.add(le);
		}
		
		for(LogEvent le:logEvents){
			
		}
		
		System.out.println(System.currentTimeMillis() - t);
		ps = con.prepareStatement("delete from events_log where ora_rowscn <= ?");
		System.out.println(System.currentTimeMillis() - t);
		ps.setLong(1, maxScn);
		System.out.println(System.currentTimeMillis() - t);
		ps.execute();
		System.out.println(System.currentTimeMillis() - t);
		con.commit();
		
		System.out.println(System.currentTimeMillis() - t);
		// Since 2.10.0, uses MongoClient
		MongoClient mongo = new MongoClient("localhost" , 3001 );
		MongoDatabase db = mongo.getDatabase("meteor");
		MongoCollection<Document> tasks = db.getCollection("tasks");
		FindIterable<Document> iter = tasks.find();
		for(Document doc:iter){
			System.out.println(doc);
		}
		mongo.close();*/
	}

	private static Set<String> splitAndNormalize(String list) {
		Set<String> hs = new HashSet<String>();
		for(String elem:list.split(",")){
			hs.add(elem);
		}
		return hs;
	}
	
	private static Set<Long> convertToLongs(Set<String> list){
		Set<Long> hs = new HashSet<Long>();
		for(String elem:list){
			hs.add(Long.parseLong(elem));
		}
		return hs;
	}
}


