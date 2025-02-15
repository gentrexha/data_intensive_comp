package MapReduce.Part_1.WordCountsForDocs;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordCountsForDocsReducer extends Reducer<Text, Text, Text, Text> {

    /**
     * @param key is the key of the mapper
     * @param values are all the values aggregated during the mapping phase
     * @param context contains the context of the job run
     *
     *        PRE-CONDITION: receive a list of <document, ["word=n", "word-b=x"]>
     *            pairs <"a.txt", ["word1=3", "word2=5", "word3=5"]>
     *
     *       POST-CONDITION: <"word1@a.txt, 3/13">,
     *            <"word2@a.txt, 5/13">
     */
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        int sumOfWordsInDocument = 0;
        Map<String, Integer> tempCounter = new HashMap<String, Integer>();
        for (Text val : values) {
            String[] wordCounter = val.toString().split("=");
            tempCounter.put(wordCounter[0], Integer.valueOf(wordCounter[1]));
            sumOfWordsInDocument += Integer.parseInt(val.toString().split("=")[1]);
        }
        for (String wordKey : tempCounter.keySet()) {
            context.write(new Text(wordKey + "@" + key.toString()),
                    new Text(tempCounter.get(wordKey) + "/" + sumOfWordsInDocument));
        }
    }
}
