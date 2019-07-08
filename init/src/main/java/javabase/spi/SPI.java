package javabase.spi;

import java.util.ServiceLoader;

//实现打成jar
public interface SPI {

        void is();
}


class Test {

    public static void main(String[] args) {
        ServiceLoader<SPI> loaders = ServiceLoader.load(SPI.class);
        for (SPI d : loaders) {
            d.is();
        }
    }
}
