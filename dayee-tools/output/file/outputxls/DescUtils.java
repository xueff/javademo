
package output.file.outputxls;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: dayee_yangkai
 * @Description:
 * @Date: Created in 16:05 2017/11/21
 * @Modified By:
 * @Version  
 */
public class DescUtils {

    public static Map<String, String> speech_map = new HashMap<>();

    public static Map<String, String> proper_map = new HashMap<>();

    static {
        speech_map.put("n", "普通名词");
        speech_map.put("f", "方位名词");
        speech_map.put("s", "处所名词");
        speech_map.put("t", "时间名词");
        speech_map.put("nr", "人名");
        speech_map.put("ns", "地名");
        speech_map.put("nt", "机构团体名");
        speech_map.put("nw", "作品名");
        speech_map.put("nz", "其他专名");
        speech_map.put("v", "普通动词");
        speech_map.put("vd", "动副词");
        speech_map.put("vn", "名动词");
        speech_map.put("a", "形容词");
        speech_map.put("ad", "副形词");
        speech_map.put("an", "名形词");
        speech_map.put("d", "副词");
        speech_map.put("m", "数量词");
        speech_map.put("q", "量词");
        speech_map.put("r", "代词");
        speech_map.put("p", "介词");
        speech_map.put("c", "连词");
        speech_map.put("u", "助词");
        speech_map.put("xc", "其他虚词");

        proper_map.put("PER", "人名");
        proper_map.put("LOC", "地名");
        proper_map.put("ORG", "机构名");
    }
}
