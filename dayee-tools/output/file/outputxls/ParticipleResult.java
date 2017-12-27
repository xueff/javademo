
package output.file.outputxls;

/**
 * @Author: dayee_yangkai
 * @Description:
 * @Date: Created in 15:23 2017/11/21
 * @Modified By:
 * @Version  
 */
public class ParticipleResult {

    /* 词汇的字符串 */
    private String  word;

    /* 词性 */
    private String  type;

    /* 词性缩略说明 */
    private String  ne;

    /* 专名识别缩略词含义 */
    private String  pos;

    /* 词汇的标准化表达 */
    private String  formal;

    /* 词频 */
    private Integer frequency;

    public String getWord() {

        return word;
    }

    public void setWord(String word) {

        this.word = word;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public String getNe() {

        return ne;
    }

    public void setNe(String ne) {

        this.ne = ne;
    }

    public String getPos() {

        return pos;
    }

    public void setPos(String pos) {

        this.pos = pos;
    }

    public String getFormal() {

        return formal;
    }

    public void setFormal(String formal) {

        this.formal = formal;
    }

    public Integer getFrequency() {

        return frequency;
    }

    public void setFrequency(Integer frequency) {

        this.frequency = frequency;
    }
}