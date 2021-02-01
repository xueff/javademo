package file_system.file.tika;

import java.util.concurrent.BlockingQueue;

public class TikaRead {
        //控制队列长度
        TikaWriter writer;
         BlockingQueue<char[]> queue;
        public TikaRead(TikaWriter tikaWriter) {
            this.writer = tikaWriter;
            this.queue= tikaWriter.queue;
        }

        public void readByLine() {

        }
        public void readByChar(char c) {

        }
        public void readAll() {

        }
        public void next() {

        }
    }

