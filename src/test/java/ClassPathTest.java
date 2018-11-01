import database.redies.JedisStudy;

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
        System.out.println(jedisStudy.listAllKeys("BGY*"));

        System.out.println();
    }
}
