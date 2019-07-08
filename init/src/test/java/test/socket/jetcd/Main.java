//package test.socket.jetcd;
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
//import com.beust.jcommander.JCommander;
//import com.beust.jcommander.Parameter;
//import io.etcd.jetcd.Client;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class Main {
//    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
//
//    public static void main(String[] args) {
//        Args global = new Args();
//        CommandGet getCmd = new CommandGet();
//        CommandPut putCmd = new CommandPut();
//        CommandWatch watchCmd = new CommandWatch();
//
//        JCommander jc = JCommander.newBuilder()
//                .addObject(global)
//                .addCommand("get", getCmd)
//                .addCommand("put", putCmd)
//                .addCommand("watch", watchCmd)
//                .build();
//
//        jc.parse(args);
//
//        String cmd = jc.getParsedCommand();
//        if (cmd == null || global.help) {
//            jc.usage();
//            return;
//        }
//
//        try (Client client = Client.builder().endpoints(global.endpoints.split(",")).build()) {
//            switch (cmd) {
//                case "get":
//                    getCmd.accept(client);
//                    break;
//                case "put":
//                    putCmd.accept(client);
//                    break;
//                case "watch":
//                    watchCmd.accept(client);
//                    break;
//            }
//        } catch (Exception e) {
//            LOGGER.error(cmd + " Error {}", e);
//            System.exit(1);
//        }
//    }
//
//    public static class Args {
//        @Parameter(names = {"--endpoints"}, description = "gRPC endpoints ")
//        private String endpoints = "http://127.0.0.1:2379";
//
//        @Parameter(names = {"-h", "--help"}, help = true)
//        private boolean help = false;
//    }
//}