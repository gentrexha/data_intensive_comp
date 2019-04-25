package MapReduce.Part_1;

import MapReduce.Part_1.WordFrequenceInDoc.WordFreqInDocMapper;
import MapReduce.Part_1.WordFrequenceInDoc.WordFreqInDocReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class WordFreqInDocDriver {

    // Where to put the data in hdfs when we're done
    private static final String OUTPUT_PATH = "1-word-freq";

    public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Word Frequence In Document");

        Path inputPath = new Path(args[0]);
        int numberOfReduceTasks = Integer.parseInt(args[1]);

        job.setJarByClass(WordFreqInDocDriver.class);
        job.setNumReduceTasks(numberOfReduceTasks);
        job.setMapperClass(WordFreqInDocMapper.class);
        job.setReducerClass(WordFreqInDocReducer.class);
        job.setCombinerClass(WordFreqInDocReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, inputPath);
        job.setInputFormatClass(TextInputFormat.class);

        Path reformatedPath = new Path(OUTPUT_PATH);
        FileOutputFormat.setOutputPath(job, reformatedPath);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileSystem hdfs = FileSystem.get(conf);

        if (hdfs.exists(reformatedPath))
            hdfs.delete(reformatedPath, true);

        int res = job.waitForCompletion(true) ? 0 : 1;
        System.exit(res);
    }
}
