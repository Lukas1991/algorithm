package Interview.amzn.oa.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 小明要帮公司买水果，给了一个codeList，里面装的是他买的水果，给了一个shoppingCart,里面装的是target水果，
 * 目标是检查codelist里的水果顺序是否和shoppingCart里的顺序匹配。
 * 注意的是只有codelist中的所有链表的item之后加起来小于等于shoppingcart里item之和才可能返回1，
 * 另外在codelist中有可能出现item时"anything"，它可以和任意的水果匹配。
 */
public class BuyFruits {

    public int buyFruits(List<List<String>> codeList, List<String> shoppingCart) {
        List<String> bought = new ArrayList<>();

        for (List<String> list : codeList) {
            for (String code : list) {
                bought.add(code);
            }
        }

        int len = bought.size();
        //start is j, end is j + len - 1
        for (int j = 0; j + len - 1 < shoppingCart.size(); j++) {
            if (match(bought, shoppingCart, j)) {
                return 1;
            }
        }

        return 0;
    }

    boolean match(List<String> bought, List<String> shoppingCart, int start) {
        int j = start;

        for (String code : bought) {
            String inCart = shoppingCart.get(j);
            if (inCart.equals(code) || code.equals("anything")) {
                j++;
            } else {
                return false;
            }
        }

        return true;
    }
}
