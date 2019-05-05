//package test.socket.jetcd;
//
//import static com.google.common.base.Charsets.UTF_8;
//
//import com.beust.jcommander.Parameter;
//import com.beust.jcommander.Parameters;
//import io.etcd.jetcd.ByteSequence;
//import io.etcd.jetcd.Client;
//import io.etcd.jetcd.kv.GetResponse;
//import io.etcd.jetcd.options.GetOption;
//import org.jooq.lambda.fi.util.function.CheckedConsumer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Parameters(separators = "=", commandDescription = "Gets the key")
//class CommandGet implements CheckedConsumer<Client> {
//    private static final Logger LOGGER = LoggerFactory.getLogger(CommandGet.class);
//
//    @Parameter(arity = 1, description = "<key>")
//    String key = "";
//
//    @Parameter(names = "--rev", description = "Specify the kv revision")
//    Long rev = 0L;
//
//    @Override
//    public void accept(Client client) throws Exception {
//        GetResponse getResponse = client.getKVClient().get(
//                ByteSequence.from(key, UTF_8),
//                GetOption.newBuilder().withRevision(rev).build()
//        ).get();
//
//        if (getResponse.getKvs().isEmpty()) {
//            // key does not exist
//            return;
//        }
//
//        LOGGER.info(key);
//        LOGGER.info(getResponse.getKvs().get(0).getValue().toString(UTF_8));
//    }
//}