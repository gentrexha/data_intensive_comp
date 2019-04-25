# Setup*

## Build jar file

```bash
mvn clean package
```

This will create the jar file into the `target/Exercise_1-1.0-SNAPSHOT.jar`

## Copy jar file to server

```bash
scp target\Exercise_1-1.0-SNAPSHOT.jar e11832486g@lbd.zserv.tuwien.ac.at://home/dic/2019S/users/e11832486g/Exercise_1/
```

## Run the map reduce job

```bash
hadoop jar [name of the.jar] [driver] input_path number_of_reducers 
```

Input path can be one of:
* `reviews-tiny.json` - contains only 100 reviews
* `reviewscombined.json` - contains all reviews

### First part

```bash
hadoop jar Exercise_1-1.0-SNAPSHOT.jar MapReduce.Part_1.WordFreqInDocDriver hdfs:///user/dic/2019S/users/e11832486g/Exercise_1/reviews-tiny.json 2;
hadoop fs -getmerge /user/e11832486g/1-word-freq/ ~/Exercise_1/result.txt;

hadoop jar Exercise_1-1.0-SNAPSHOT.jar MapReduce.Part_1.WordFreqInDocDriver hdfs:///user/elmar/amazon-reviews/full/complete/reviewscombined.json 6;
```

`hdfs:///user/elmar/amazon-reviews/full/complete/reviewscombined.json`

\* Windows specific.