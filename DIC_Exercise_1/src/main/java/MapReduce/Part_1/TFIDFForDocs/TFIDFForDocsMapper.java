package MapReduce.Part_1.TFIDFForDocs;

import MapReduce.KeyValueWritable;
import MapReduce.Parser;
import MapReduce.Part_1.Main;
import MapReduce.Review;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 *
 */
public class TFIDFForDocsMapper extends Mapper<LongWritable, Text, Text, KeyValueWritable> {

    private int totalNumberOfDocuments;
    private KeyValueWritable outVal = new KeyValueWritable();
    private ObjectMapper objMapper = new ObjectMapper();
    private Parser p = new Parser();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        totalNumberOfDocuments = 0;
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        totalNumberOfDocuments += 1;

        Review review = objMapper.readValue(value.toString(), Review.class);

        // TODO: rewrite = since reviews have no unique identifiers, we create a composite key
        String documentID = review.reviewerID + review.asin + review.unixReviewTime;
        String[] terms = p.parse(review.reviewText);

        for(String term : terms) {
            outVal.setKeyByString(documentID);
            outVal.setValueByString(1);

            // TODO: rewrite = write as output that term was present in documentID (e.g. term1  documentID:1)
            context.write(new Text(term), outVal);
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        context.getCounter(Main.CustomCounters.Documents).increment(totalNumberOfDocuments);
    }


}
