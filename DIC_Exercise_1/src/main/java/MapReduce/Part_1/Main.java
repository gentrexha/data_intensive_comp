package MapReduce.Part_1;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;

/**
 *
 */
public class Main {

    // Addresses of the helping storage directories, just to see what's going on after each MapReduce jobs for IBM
    private static final String OUTPUT_PATH = "/user/biadmin/output/ordered";
    private static final String OUTPUT_PATH_2 = "/user/biadmin/output/total";
    private static final String OUTPUT_PATH_3 = "/user/biadmin/output/tfidfresult";

    public static void main(String[] args) {

        //input and output path that is passed in arguments
        Path inputPath = new Path(args[0]);
        Path outputDir = new Path(args[1]);

        // Create configuration
        Configuration conf = new Configuration(true);
    }
}
