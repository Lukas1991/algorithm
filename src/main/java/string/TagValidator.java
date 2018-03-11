package string;

import java.util.Stack;

public class TagValidator {

    Stack<String> stack = new Stack<>();
    boolean contains_tag = false;

    public boolean isValid(String code) {
        if (code.charAt(0) != '<' || code.charAt(code.length() - 1) != '>')
            return false;

        for (int i = 0; i < code.length(); i++) {

            if (stack.isEmpty() && contains_tag) {
                return false;//"<A></A>blabla" is false, "<A></A><B></B>" is false, 必须包在最外层tag里面
            }

            if (code.charAt(i) == '<') {
                int closeindex;

                if (!stack.isEmpty() && code.charAt(i + 1) == '!') {
                    //check <![CDATA[...]]>
                    closeindex = code.indexOf("]]>", i + 1);
                    if (closeindex < 0 || !isValidCdata(code.substring(i + 2, closeindex))) {
                        return false;
                    }

                } else {
                    //check tag
                    boolean ending = false;
                    if (code.charAt(i + 1) == '/') {
                        i++;
                        ending = true;
                    }

                    closeindex = code.indexOf('>', i + 1);
                    if (closeindex < 0 || !isValidTagName(code.substring(i + 1, closeindex), ending))
                        return false;
                }

                i = closeindex;
            }
        }
        return stack.isEmpty() && contains_tag;
    }

    public boolean isValidTagName(String tagName, boolean ending) {
        if (tagName.length() < 1 || tagName.length() > 9)
            return false;

        //必须全大写
        for (int i = 0; i < tagName.length(); i++) {
            if (!Character.isUpperCase(tagName.charAt(i)))
                return false;
        }

        if (ending) {
            if (!stack.isEmpty() && stack.peek().equals(tagName))
                stack.pop();
            else
                return false;
        } else {
            contains_tag = true;
            stack.push(tagName);
        }
        return true;
    }

    public boolean isValidCdata(String s) {
        return s.indexOf("[CDATA[") == 0;
    }

    public static void main(String[] args) {
        TagValidator obj = new TagValidator();
        System.err.println(obj.isValid("<A></A><B></B>"));
    }

}
