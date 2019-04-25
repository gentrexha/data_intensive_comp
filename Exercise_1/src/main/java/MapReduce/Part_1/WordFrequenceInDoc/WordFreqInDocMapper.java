package MapReduce.Part_1.WordFrequenceInDoc;

import MapReduce.Parser;
import MapReduce.Review;
import MapReduce.TermDocumentWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.UUID;

/**
 *
 */
public class WordFreqInDocMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private ObjectMapper objMapper = new ObjectMapper();
    private Parser p = new Parser();
    private TermDocumentWritable outValue = new TermDocumentWritable();
    StringBuilder valueBuilder = new StringBuilder();

    /**
     * @param key is the byte offset of the current line in the file;
     * @param value is the line from the file
     * @param output has the method "collect()" to output the key,value pair
     * @param reporter allows us to retrieve some information about the job (like the current filename)
     *
     *     POST-CONDITION: Output <"word", "filename@offset"> pairs
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // Deserialize JSON string into Java object
        Review review = objMapper.readValue(value.toString(), Review.class);

        // Create a randomUUID for each doc
        String documentID = UUID.randomUUID().toString();
        String[] terms = p.parse(review.reviewText);

        for(String term : terms) {
            valueBuilder.append(term);
            valueBuilder.append("@");
            valueBuilder.append(documentID);
            // Emit the partial <k,v>
            context.write(new Text(valueBuilder.toString()), new IntWritable(1));
        }
    }
}
