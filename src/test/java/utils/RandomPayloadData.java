package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomPayloadData {

    public static String getName(){
        return  "Jim"+RandomStringUtils.randomAlphabetic(2);
    }
}
