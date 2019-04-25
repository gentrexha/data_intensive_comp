package MapReduce.Part_1.WordCountsForDocs;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountsForDocsMapper extends Mapper<LongWritable, Text, Text, Text> {

    /**
     * @param key is the byte offset of the current line in the file;
     * @param value is the line from the file
     * @param context
     *
     *     PRE-CONDITION: aa@leornardo-davinci-all.txt    1
     *                    aaron@all-shakespeare   98
     *                    ab@leornardo-davinci-all.txt    3
     *
     *     POST-CONDITION: Output <"all-shakespeare", "aaron=98"> pairs
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] wordAndDocCounter = value.toString().split("\t");
        String[] wordAndDoc = wordAndDocCounter[0].split("@");
        context.write(new Text(wordAndDoc[1]), new Text(wordAndDoc[0] + "=" + wordAndDocCounter[1]));
    }
}
