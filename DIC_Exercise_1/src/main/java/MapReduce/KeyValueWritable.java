package MapReduce;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class KeyValueWritable implements Writable{

    private Text key;
    private FloatWritable value;

    public KeyValueWritable() {
        super();
        key = new Text();
        value = new FloatWritable();
    }

    public KeyValueWritable(Text key, FloatWritable value) {
        super();
        this.key = key;
        this.value = value;
    }

    public KeyValueWritable(String input) {
        super();
        key = new Text(input.split(":")[0]);
        value = new FloatWritable(Float.parseFloat(input.split(":")[1]));
    }

    @Override
    public void write(DataOutput out) throws IOException {
        key.write(out);
        value.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        key.readFields(in);
        value.readFields(in);
    }

    public Text getKey() {
        return key;
    }

    public String getKeyAsString() {
        return key.toString();
    }

    public void setKey(Text key) {
        this.key = key;
    }

    public FloatWritable getValue() {
        return value;
    }

    public void setValue(FloatWritable value) {
        this.value = value;
    }

    public void setKeyByString(String document){
        this.key.set(document);
    }

    public void setValueByString(float value){
        this.value.set(value);
    }

    @Override
    public String toString() {
        return key.toString() + ":" + value.toString();
    }
}