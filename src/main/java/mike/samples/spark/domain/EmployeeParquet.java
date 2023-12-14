package mike.samples.spark.domain;

import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.parquet.avro.AvroSchemaConverter;
import org.apache.parquet.avro.AvroWriteSupport;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.schema.MessageType;

import mike.bootstrap.utilities.exceptions.ApplicationErrorException;
import mike.bootstrap.utilities.nio.files.Resource;

public class EmployeeParquet {

    private final MessageType parquetSchema;
    private final WriteSupport<Employee> writeSupport;
    
    public EmployeeParquet(Resource avroSchemaResource) {
        
        try ( var is = avroSchemaResource.getInputStream()) {
            var avroSchema = new Schema.Parser().parse(is);
            this.parquetSchema = new AvroSchemaConverter().convert(avroSchema);
            this.writeSupport = new AvroWriteSupport<Employee>(parquetSchema, avroSchema);
            
            
        } catch (IOException ioe) {
            throw new ApplicationErrorException(ioe, "read avro schema resurce: %s", avroSchemaResource);
        }
    }
}
