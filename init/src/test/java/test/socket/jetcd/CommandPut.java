//package test.socket.jetcd;
//
//import com.beust.jcommander.Parameter;
//import com.beust.jcommander.Parameters;
//import com.google.common.base.Charsets;
//import io.etcd.jetcd.ByteSequence;
//import io.etcd.jetcd.Client;
//import org.jooq.lambda.fi.util.function.CheckedConsumer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.util.List;
//
//
//@Parameters(separators = "=", commandDescription = "Puts the given key into the store")
//class CommandPut implements CheckedConsumer<Client> {
//    private static final Logger LOGGER = LoggerFactory.getLogger(CommandPut.class);
//
//    @Parameter(arity = 2, description = "<key> <value>")
//    List<String> keyValue;
//
//    @Override
//    public void accept(Client client) throws Exception {
//        client.getKVClient().put(
//                ByteSequence.from(keyValue.get(0), Charsets.UTF_8),
//                ByteSequence.from(keyValue.get(1), Charsets.UTF_8)
//        ).get();
//
//        LOGGER.info("OK");
//    }
//}