package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
    public List<Item> parseItemList(String valueToParse) {
        Pattern pattern = Pattern.compile("(?i)([a-z:;.@^*%0-9/]*)##");
        Matcher matcher = pattern.matcher(valueToParse);
        List<Item> newList = new ArrayList<>();
        while (matcher.find()) {
        try {
            String group = matcher.group(1);
            Item item = parseSingleItem(group);
            newList.add(item);
        }
        catch (ItemParseException iPE){
            }
        }
        return newList;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {
        String name = "";
        Double price = 0.00;
        String type = "";
        String expiration = "";

        try {
            Pattern pattern = Pattern.compile("(name)[:@^*%]([a-z]*)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(singleItem);
            matcher.find();
            name = matcher.group(2).toLowerCase();

            pattern = Pattern.compile("(price)[:@^*%]([0.0-9.0]*)", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(singleItem);
            matcher.find();
            price = Double.valueOf(matcher.group(2));

            pattern = Pattern.compile("(type)[:@^*%]([a-z]*)", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(singleItem);
            matcher.find();
            type = matcher.group(2).toLowerCase();

            pattern = Pattern.compile("(expiration)[:@^*%]([0-9/]*)", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(singleItem);
            matcher.find();
            expiration = matcher.group(2).toLowerCase();
        }
        catch(IllegalStateException iSE) {
            System.out.println(iSE);
            throw new ItemParseException();
        }

        return new Item(name, price, type, expiration);
    }
}
