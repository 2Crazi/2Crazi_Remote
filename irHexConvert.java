/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

Class irHexConvert{
/* Name of the class has to be "Main" only if the class is public. */
public static void main (String[] args)
  {
    // String s = "fred";  // use this if you want to test the exception below
    String s = "0019";

    try {
      // the String to int conversion happens here
      // int i = Integer.parseInt(s.trim());
      
      // Hex to Decimal happens here
      int i = Integer.parseInt(s,16);

      // print out the value after the conversion
      System.out.println("int i = " + i);
    }
    catch (NumberFormatException nfe)
    {
      System.out.println("NumberFormatException: " + nfe.getMessage());
    }
  }
}

http://stackoverflow.com/questions/24753587/ir-remote-control-app
http://stackoverflow.com/questions/7347856/how-to-convert-a-string-into-an-arraylist
https://ideone.com/glJEkH
https://android.googlesource.com/platform/development/+/438ea813f1846f88205ff98436568aaa34f06845/samples/ApiDemos/src/com/example/android/apis/hardware/ConsumerIr.java
http://alvinalexander.com/java/edu/qanda/pjqa00010.shtml
http://www.binaryhexconverter.com/hex-to-decimal-converter
http://stackoverflow.com/questions/20244337/consumerirmanager-api-19/25518468#25518468
http://docs.oracle.com/javase/6/docs/api/java/util/Arrays.html#asList%28T...%29
http://docs.oracle.com/javase/6/docs/api/java/lang/String.html#split%28java.lang.String%29
