
package stringutils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtils extends org.apache.commons.lang.StringUtils {


    /**
     * 任意字符串转全角字符串的函数(SBC case): 全角空格为12288，半角空格为32
     * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
     */
    public static String toSBC(String input) {

        // 半角转全角：
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 32) {
                c[i] = (char) 12288;
                continue;
            }
            if (c[i] < 127)
                c[i] = (char) (c[i] + 65248);
        }
        return new String(c);
    }

    /**
     * 任意字符转半角字符串的函数(DBC case):全角空格为12288，半角空格为32
     * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
     */
    public static String toDBC(String input) {

        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
            } else if (c[i] == 215) {
                // [×]转[*]
                c[i] = (char) 42;
            } else if (c[i] == 8208 || c[i] == 8209
                       || c[i] == 8210
                       || c[i] == 8211
                       || c[i] == 8212
                       || c[i] == 8213) {
                // [—]转[-]
                c[i] = (char) 45;
            } else if (c[i] == 8216 || c[i] == 8217
                       || c[i] == 8218
                       || c[i] == 8219) {
                // [‘’]转[']
                c[i] = (char) 39;
            } else if (c[i] == 8220 || c[i] == 8221
                       || c[i] == 8222
                       || c[i] == 8223) {
                // [“”]转["]
                c[i] = (char) 34;
            } else if (c[i] == 12290) {
                // [。]转[.]
                c[i] = (char) 46;
            } else if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    public static String substring(String regex, String str) {

        if (StringUtils.hasLength(str)) {
            Pattern pattern = Pattern.compile(regex.trim());
            Matcher m = pattern.matcher(str);
            if (m.find()) {
                return substring(str, m.start(), m.end());
            }
        }
        return EMPTY;
    }


    public static String[] splitAny(String str, String[] splitChars) {

        if (splitChars != null) {
            for (String splitChar : splitChars) {
                if (str.contains(splitChar)) {
                    return str.split(splitChar);
                }

            }
        }

        return new String[] { str };
    }

    public static boolean containsAny(String str, String[] searchChars) {

        if (searchChars == null) {
            return false;
        } else {

            for (String s : searchChars) {
                if (contains(str, s)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean containsAll(String str, String[] searchChars) {

        if (searchChars == null) {
            return false;
        } else {

            for (String s : searchChars) {
                if (!contains(str, s)) {
                    return false;
                }
            }
            return true;
        }
    }

    // 替换所有的行标记符
    public static String replaceWhitespace(String str, String replacement)
            throws Exception {

        BufferedReader reader = new BufferedReader(new StringReader(str));
        StringBuilder buffer = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {

            // line = line.trim();
            if (hasLength(line.trim())) {
                buffer.append(line).append(replacement);
            }
        }
        return buffer.toString();
    }

    // 删除所有的行标记符
    public static String deleteLineSeparator(String str) throws Exception {

        BufferedReader reader = new BufferedReader(new StringReader(str));
        StringBuilder buffer = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {

            // line = line.trim();
            if (hasLength(line.trim())) {
                buffer.append(line);
            }
        }
        return buffer.toString();
    }

    // 删除多余的空格符
    public static String deleteUnwantedSpaces(String str) {

        if (isEmpty(str)) {
            return str;
        }
        int sz = str.length();
        char chs[] = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {

            if (Character.isSpaceChar(str.charAt(i)) || '\t' == str.charAt(i)) {

                if (i != 0 && (i != sz - 1)
                    && !Character.isSpaceChar(str.charAt(i - 1))) {
                    chs[count++] = str.charAt(i);
                }

            } else {
                chs[count++] = str.charAt(i);
            }
        }

        if (count == sz) {
            return str;
        } else {
            return new String(chs, 0, count);
        }
    }

    // 删除多余的空格符
    public static String deleteSpaces(String str) {

        if (isEmpty(str)) {
            return str;
        }
        int sz = str.length();
        char chs[] = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {
            char ch = str.charAt(i);

            if (!Character.isSpaceChar(ch) && ch != '\t') {
                chs[count++] = ch;
            }
        }

        if (count == sz) {
            return str;
        } else {
            return new String(chs, 0, count);
        }
    }

    // 删除多余的空格符
    public static String deleteWhitespace(String str) {

        if (isEmpty(str)) {
            return str;
        }
        int sz = str.length();
        char chs[] = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {
            char ch = str.charAt(i);

            if (!Character.isWhitespace(ch) && ch != '\t') {
                chs[count++] = ch;
            }
        }

        if (count == sz) {
            return str;
        } else {
            return new String(chs, 0, count);
        }
    }

    // 删除可以忽略的标记符
    public static String deleteDentifierIgnorable(String str) {

        if (isEmpty(str)) {
            return str;
        }
        int sz = str.length();
        char chs[] = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {

            if (!Character.isIdentifierIgnorable(str.charAt(i))) {
                chs[count++] = str.charAt(i);

            }
        }

        if (count == sz) {
            return str;
        } else {
            return new String(chs, 0, count);
        }
    }

    // public static String deleteUnwantedWhitespaceNotSpaces(String str) {
    //
    // if (isEmpty(str)) {
    // return str;
    // }
    // int sz = str.length();
    // char chs[] = new char[sz];
    // int count = 0;
    // for (int i = 0; i < sz; i++) {
    // System.out.println("处理：" + str.charAt(i));
    // if (Character.isWhitespace(str.charAt(i))) {
    //
    // int next = i + 1;
    //
    // if ((next < sz) && !Character.isWhitespace(str.charAt(next))) {
    // System.out.println("下一个字符 ：" + str.charAt(next) + "="
    // + Character.isSpaceChar(str.charAt(i)));
    // if (Character.isSpaceChar(str.charAt(i))) {
    //
    // chs[count++] = str.charAt(i);
    // } else {
    // System.out.println("jia ||");
    // chs[count++] = LINE_SEPARATOR_CHAR;
    // }
    // }
    //
    // } else {
    // chs[count++] = str.charAt(i);
    // }
    // }
    //
    // if (count == sz) {
    // return str;
    // } else {
    // return new String(chs, 0, count);
    // }
    // }

    // public static String deleteUnwantedWhitespace(String str) {
    //
    // if (isEmpty(str)) {
    // return str;
    // }
    // int sz = str.length();
    // char chs[] = new char[sz];
    // int count = 0;
    // for (int i = 0; i < sz; i++) {
    //
    // if (Character.isWhitespace(str.charAt(i))) {
    // int next = i + 1;
    // if ((next < sz) && !Character.isWhitespace(str.charAt(next))) {
    //
    // if (!Character.isSpaceChar(str.charAt(i))) {
    //
    // chs[count++] = LINE_SEPARATOR_CHAR;
    // }
    // }
    //
    // } else {
    // chs[count++] = str.charAt(i);
    // }
    // }
    //
    // if (count == sz) {
    // return str;
    // } else {
    // return new String(chs, 0, count);
    // }
    // }

    // public static String deleteDoubleWhitespace(String str) {
    //
    // if (isEmpty(str)) {
    // return str;
    // }
    // int sz = str.length();
    // char chs[] = new char[sz];
    // int count = 0;
    // for (int i = 0; i < sz; i++) {
    // if (Character.isWhitespace(str.charAt(i))) {
    //
    // if (Character.isSpaceChar(str.charAt(i))
    // && !Character.isSpaceChar(str.charAt(i + 1))) {
    // chs[count++] = str.charAt(i);
    // }
    // } else {
    // chs[count++] = str.charAt(i);
    // }
    // }
    //
    // if (count == sz) {
    // return str;
    // } else {
    // return new String(chs, 0, count);
    // }
    // }

    public static boolean hasLength(CharSequence s) {

        return s != null && s.length() > 0;
    }

    public static boolean hasLength(CharSequence s, boolean isTrim) {

        if (s == null || s.length() == 0) {
            return false;
        }
        if (isTrim) {
            String str = StringUtils.trim(s.toString());
            return str.length() > 0;
        } else {
            return true;
        }
    }

    public static boolean hasLength(StringBuilder s) {

        return s != null && s.length() > 0;
    }

    public static boolean isEmpty(CharSequence s) {

        return s == null || s.length() == 0;
    }

    public static boolean isEmpty(CharSequence s, boolean isTrim) {

        if (s == null || s.length() == 0) {
            return true;
        }
        if (isTrim) {
            String str = StringUtils.trim(s.toString());
            return str == null || str.length() == 0;
        } else {
            return false;
        }
    }

    public static String substring(String str, String start, String[] ends) {

        for (String end : ends) {
            if (contains(str, end)) {
                str = substring(str, start, end);
                break;
            }
        }

        return str;

    }

    public static String substring(String str,
                                   String start,
                                   String[] ends,
                                   int maxLength) {

        for (String end : ends) {
            if (contains(str, end)) {
                str = substring(str, start, end);
                break;
            }
        }

        if (str.length() > maxLength) {
            str = str.substring(0, maxLength);
        }
        return str;

    }

    public static String substring(String str,
                                   String start,
                                   String end,
                                   int maxLength) {

        str = substringAfter(str, start);

        str = substringBefore(str, end);

        if (str.length() > maxLength) {
            str = str.substring(0, maxLength);
        }
        return str;

    }

    public static String substring(String str, String start, String end) {

        str = substringAfter(str, start);

        str = substringBefore(str, end);

        return str;

    }

    public static boolean equalsAny(String str1, String[] str2) {

        for (String str : str2) {
            if (equals(str1, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean equalsAnyIgnoreCase(String str1, String[] str2) {

        for (String str : str2) {
            if (equalsIgnoreCase(str1, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAllCharacter(String str) {

        if (isEmpty(str)) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }



    public static boolean hasLetter(String input) {

        if (isEmpty(input)) {
            return false;
        }
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasDigit(String input) {

        if (isEmpty(input)) {
            return false;
        }
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasWhitespace(String input) {

        if (isEmpty(input)) {
            return false;
        }
        for (char c : input.toCharArray()) {
            if (Character.isWhitespace(c) && !Character.isSpaceChar(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasSpace(String input) {

        if (isEmpty(input)) {
            return false;
        }
        for (char c : input.toCharArray()) {
            if (Character.isWhitespace(c) || Character.isSpaceChar(c)) {
                return true;
            }
        }
        return false;
    }


    public static final Pattern EMAIL_PATTERN = Pattern
                                                      .compile("^(([A-Za-z0-9]+_+)|([A-Za-z0-9]+\\-+)|([A-Za-z0-9]+\\.+)|([A-Za-z0-9]+\\++))*[A-Za-z0-9]+@((\\w+\\-+)|(\\w+\\.))*\\w{1,63}\\.[a-zA-Z]{2,6}$");

    /**
     * 检查字符串是否为EMAIL地址
     */
    public static boolean isEmail(String email) {

        if (isEmpty(email)) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public static final Pattern MOBILE_PHONE_PATTERN = Pattern
                                                             .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

    /**
     * 检查字符串是否为手机号码
     */
    public static boolean isMobilePhone(String mobilePhone) {

        if (isEmpty(mobilePhone)) {
            return false;
        }
        Matcher matcher = MOBILE_PHONE_PATTERN.matcher(mobilePhone);
        return matcher.matches();
    }

    public static String substring(StringBuilder str, int start, int end) {

        if (str == null) {
            return null;
        }

        // handle negatives
        if (end < 0) {
            end = str.length() + end; // remember end is negative
        }
        if (start < 0) {
            start = str.length() + start; // remember start is negative
        }

        // check length next
        if (end > str.length()) {
            end = str.length();
        }

        // if start is greater than end, return ""
        if (start > end) {
            return EMPTY;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * @param str
     * @param regex
     * @return
     * @author chssheng 创建日期 Aug 30, 2012 创建时间 11:10:59 AM function:
     *         str是否满足与正则表达式
     */
    public static final String REG_STRING_IS_NAME         = "^[\\u4e00-\\u9fa5]{2,}|^[a-zA-Z]*";

    public static final String REG_STRING_ID_NUMBER       = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";

    public static final String REG_STRING_SPACE_T         = " |　|\t";

    public static final String REG_STRING_TEL             = "1[3|4|5|8]\\d{9}";

    public static final String REG_DATE_YYYY_MM_DD_STRING = "[1|2]\\d{3}-(0[1-9]{1}|1[0-2]{1})-([1-2]{1}[0-9]{1}|0[1-9]{1}|3[0-1]{1})";

    public static final String REG_STRING_IS_ENGLISH      = "^[a-zA-Z]*";

    public static boolean isStringEnglish(String str) {

        return matchesReg(str, true, REG_STRING_IS_ENGLISH);
    }

    /**
     * @author chssheng 创建日期 2013-8-28 创建时间 下午4:24:47 function: 通过正则，匹配日期字符
     *         REG_DATE_YYYY_MM_DD_STRING
     */
    public static boolean isDatematchesReg(String str) {

        return matchesReg(str, true, REG_DATE_YYYY_MM_DD_STRING);
    }

    public static boolean matchesReg(String str,
                                     boolean isDeleteAllSpace,
                                     String regex) {

        boolean flg = false;
        if (StringUtils.hasLength(str)) {
            if (isDeleteAllSpace) {
                str = str.replaceAll(REG_STRING_SPACE_T, "");
            }
            Pattern pattern = Pattern.compile(regex.trim());
            if (pattern.matcher(str).matches()) {
                flg = true;
            }
        }
        return flg;
    }

    // 清除掉所有特殊字符
    public static String removeSpecString(String str, boolean isDeleteAllSpace)
            throws PatternSyntaxException {

        // 只允许字母和数字
        // String regEx = "[^a-zA-Z0-9]";
        String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        if (isDeleteAllSpace) {
            str = str.replaceAll(REG_STRING_SPACE_T, "");
        }
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static String dbNametoXmlNodeName(String input) {

        StringBuilder buffer = new StringBuilder();
        char[] array = input.toCharArray();
        boolean isUpperCase = true;
        for (char ch : array) {
            if (ch >= 'a' && ch <= 'z') {
                if (isUpperCase) {
                    ch = Character.toUpperCase(ch);
                    isUpperCase = false;
                }
                buffer.append(ch);
            } else if (ch >= 'A' && ch <= 'Z') {
                if (!isUpperCase) {
                    ch = Character.toLowerCase(ch);
                }
                isUpperCase = false;
                buffer.append(ch);
            } else {
                isUpperCase = true;
            }
        }

        return buffer.toString();
    }

    public static String InputStreamToString(InputStream in, String encoding)
            throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int count = -1;
        while ((count = in.read(data, 0, 4096)) != -1)
            outStream.write(data, 0, count);

        data = null;
        if (!hasLength(encoding)) {
            encoding = "UTF-8";
        }
        return new String(outStream.toByteArray(), encoding);
    }



    public static boolean isHanzi(char c) {

        String regEx = "[\u4e00-\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(c + "");
        if (m.find())
            return true;
        return false;
    }

    /**
     * JSON字符串特殊字符处理，比如：“\A1;1300”
     * 
     * @param s
     * @return String
     */
    public static String string2Json(String s) {

        if (StringUtils.hasLength(s)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                switch (c) {
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case 31:
                    sb.append("");
                    break;
                case 65535:
                    sb.append("");
                    break;
                default:
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return s;
    }

    /**
     * 去除xml字符串中多余的空白
     * 
     * @param s
     * @return
     */
    public static String replaceXmlExcessBlank(String s) {

        return s.replaceAll("\r|\n", "")
                .replaceAll("\\s+|\t", "")
                .replaceAll("(?<=>)(\\s+|\r|\n)(?=<)", "");
    }

    
    // 替换xml中特殊字符
    public static String replaceSpecialChar(String s) {
		if (StringUtils.hasLength(s, true)) {
			return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
					.replace("\"", "&quot;").replace("'", "&apos;");
		}
		return "";
	}


    /**
     * 判断备注中网址并显示
     * 
     * @param regex
     *            正则表达式
     * @param str
     *            备注文本
     * @return
     */
    public static String matchUrl(String regex, String str, String target) {

        StringBuilder sb = new StringBuilder();
        String[] strs = str.split(regex);
        Integer indexLen = strs.length;
        if (indexLen != 0) {
            Integer indexFir = str.indexOf(strs[0]);
            Integer indexEnd = 0;
            String indexStr = "";
            if (indexFir != 0) {
                indexStr = str.substring(0, indexFir);
                str = str.substring(indexFir + strs[0].length());
                sb.append("<a href=\"" + indexStr
                          + "\"target=\""
                          + target
                          + "\">");
                sb.append(indexStr);
                sb.append("</a>");
            }

            for (int i = 0; i < indexLen - 1; i++) {
                indexFir = str.indexOf(strs[i]);
                str = str.substring(indexFir + strs[i].length());
                indexEnd = str.indexOf(strs[i + 1]);
                indexStr = str.substring(0, indexEnd);
                sb.append(strs[i]);
                sb.append("<a href=\"" + indexStr
                          + "\"target=\""
                          + target
                          + "\">");
                sb.append(indexStr);
                sb.append("</a>");
            }
            indexFir = str.indexOf(strs[indexLen - 1]);
            str = str.substring(indexFir + strs[indexLen - 1].length());
            indexStr = str;
            sb.append(strs[strs.length - 1]);
            if (StringUtils.isNotEmpty(indexStr)) {
                sb.append("<a href=\"" + indexStr
                          + "\"target=\""
                          + target
                          + "\">");
                sb.append(indexStr);
                sb.append("</a>");
            }
        } else {
            sb.append("<a href=\"" + str + "\"target=\"" + target + "\">");
            sb.append(str);
            sb.append("</a>");
        }
        return sb.toString();
    }

    public static boolean isEqualsToUpperCase(String strA, String strB) {

        if (StringUtils.isNotEmpty(strA)) {
            strA = strA.replaceAll(" ", "").toUpperCase();
        }
        if (StringUtils.isNotEmpty(strB)) {
            strB = strB.replaceAll(" ", "").toUpperCase();
        }

        return strA.equals(strB);
    }

    /**
     * 判断一个字符串包含另个字符串的次数
     * 
     * @author llcui
     */
    public static int containsCount(String str, String sub) {

        int count = 0;
        int start = 0;
        while ((start = str.indexOf(sub, start)) > 0) {
            start += sub.length();
            count++;
        }
        return count;
    }
}
