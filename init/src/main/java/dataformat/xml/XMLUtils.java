package dataformat.xml;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.json.XML;

import java.util.List;

public class XMLUtils {
    public static void jsonToXmlStr(){
            JSONObject jsonObject = new JSONObject();
            jsonObject.append("sender", "");
            jsonObject.append("recipients", "");
            String xml = "<receivermessages>" + XML.toString(jsonObject) + "</receivermessages>";
    }
}
