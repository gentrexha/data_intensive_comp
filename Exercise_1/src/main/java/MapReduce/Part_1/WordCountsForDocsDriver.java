package MapReduce.Part_1;

import MapReduce.Part_1.WordCountsForDocs.WordCountsForDocsMapper;
import MapReduce.Part_1.WordCountsForDocs.WordCountsForDocsReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountsForDocsDriver {

    // where to put the data in hdfs when we're done
    private static final String OUTPUT_PATH = "2-word-counts";

    // where to read the data from.
    private static final String INPUT_PATH = "1-word-freq";

    public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException{
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Words Count");

        Path inputPath = new Path(args[0]);
        int numberOfReduceTasks = Integer.parseInt(args[1]);

        job.setJarByClass(WordFreqInDocDriver.class);
        job.setNumReduceTasks(numberOfReduceTasks);
        job.setMapperClass(WordCountsForDocsMapper.class);
        job.setReducerClass(WordCountsForDocsReducer.class);
        job.setCombinerClass(WordCountsForDocsReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        Path path = new Path(OUTPUT_PATH);
        FileInputFormat.addInputPath(job, new Path(INPUT_PATH));
        FileOutputFormat.setOutputPath(job, path);

        FileSystem hdfs = FileSystem.get(conf);

        if (hdfs.exists(path))
            hdfs.delete(path, true);

        int res = job.waitForCompletion(true) ? 0 : 1;
        System.exit(res);
    }
}
