import database.redies.JedisStudy;

import java.util.UUID;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/3117:39
 */
public class ClassPathTest {
    public static void main(String[] args) {
        JedisStudy jedisStudy = JedisStudy.getInstance(8);
        System.out.println(UUID.randomUUID().toString());

        System.out.println();
    }
}
