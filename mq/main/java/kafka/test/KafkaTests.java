package kafka.test;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 服务器运行
 * 1.可能需要：sunjce_provider.jar
 * 2.cmd：  java -Djava.ext.dirs=./libs  MqApplicationTests2
 */
public class KafkaTests {

        private static final String BROKER_LIST = "172.16.0.202:19092";
        private static final String FILE_PATH = "C:\\Users\\ffxue\\hbase.json";


        public static void main(String[] args)   {
                send();
        }
        public static void get() {
//            System.setProperty("java.security.krb5.conf", "/etc/krb5.conf");
//            System.setProperty("java.security.auth.login.config",
//                    "/opt/bd/xx/kafka_client_jaas.conf");
            Properties props = new Properties();
            props.put("bootstrap.servers", BROKER_LIST);
            props.put("group.id", "test-consumer-group");
            props.put("enable.auto.commit", "false");
            props.put("auto.commit.interval.ms", "1000");
            props.put("auto.offset.reset", "earliest");
            props.put("session.timeout.ms", "30000");
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

//            props.put("security.protocol", "SASL_PLAINTEXT");
//            props.put("sasl.mechanism", "GSSAPI");
//            props.put("sasl.kerberos.service.name", "kafka");

            KafkaConsumer kafkaConsumer = new KafkaConsumer<>(props);
            kafkaConsumer.subscribe(Arrays.asList("test"));
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(1);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("\n\n\n*****************************");
                    System.out.println("Partition: " + record.partition() + " Offset: " + record.offset() + " Value: " + record.value() + " ThreadID: " + Thread.currentThread().getId());
                }
//                System.out.println("null====================================");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        public static void send() {
//            System.setProperty("java.security.krb5.conf", "/etc/krb5.conf");
//            System.setProperty("java.security.auth.login.config",
//                    "/opt/bd/xx/kafka_client_jaas.conf");
            Properties props = new Properties();
            Producer<String, Object> producer1=null;
            props.put("bootstrap.servers", BROKER_LIST);
            props.put("acks", "1");
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

            producer1= new KafkaProducer<String,Object>(props);
            List<String>  jsons = JSONFileUtils.readFile(new File(FILE_PATH));

                for(int i=0;i<jsons.size();i++){
                    ProducerRecord<String, Object> message1= new ProducerRecord<String, Object>("test",jsons.get(i));
                    producer1.send(message1, new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                            if( e!=null){
                                e.printStackTrace();
                                System.out.println("failed");
                            }else {
                                System.out.println(recordMetadata.topic());
                            }
                        }
                    });
                }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            producer1.close();
    }

}
