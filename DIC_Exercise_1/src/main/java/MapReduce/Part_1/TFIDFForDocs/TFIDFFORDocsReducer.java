package MapReduce.Part_1.TFIDFForDocs;

import MapReduce.KeyValueWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;

/**
 *
 */
public class TFIDFFORDocsReducer extends Reducer<LongWritable, Text, Text, KeyValueWritable> {

    private long totalNumberOfDocuments = 0;
    private HashMap<String, Integer> docToCountHashMap;
    private StringBuilder result;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

    }

    /**
     * @param key: term
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

    }
}
