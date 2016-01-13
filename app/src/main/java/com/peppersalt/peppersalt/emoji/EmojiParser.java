package com.peppersalt.peppersalt.emoji;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiParser {
  /**
   * Return the text with emoticons changed to android code
   * @param text
   * @return
   */
  public static String demojizedText(String text){
    String returnTextString = text;
//    //Pattern to match
//    Pattern pattern = Pattern.compile("(\\:[^\\:]+\\:)");
//    Matcher matcher = pattern.matcher(text);
//    while (matcher.find()) {
//      String found = matcher.group();
//      if(IEmojiMap.get(found)==null)continue;
//      returnTextString = returnTextString.replace(found, IEmojiMap.get(found));
//    }
//    //Returning text
    return returnTextString;
  }

  /**
   * Return the text with emoticons changed to android code
   * @param emojis a map containing as a key the emoji's short_name and as a value its unified name
   * @param text the text to be reviewed
   * @return the modified text
   */
  public static String demojizedText(Map<String, String> emojis, String text){
    String returnTextString = text;
    Pattern pattern = Pattern.compile("(\\:[^\\:]+\\:)");
    Matcher matcher = pattern.matcher(text);

    while (matcher.find()) {
      String found = matcher.group();
      if(emojis.get(found)==null)continue;
      returnTextString = returnTextString.replace(found, emojis.get(found));
    }
    return returnTextString;
  }
}