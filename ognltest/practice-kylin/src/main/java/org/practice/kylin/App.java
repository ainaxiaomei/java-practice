package org.practice.kylin;

import org.apache.kylin.common.util.Dictionary;
import org.apache.kylin.dict.BytesConverter;
import org.apache.kylin.dict.StringBytesConverter;
import org.apache.kylin.dict.TrieDictionary;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        BytesConverter<String> converter = new StringBytesConverter();
        byte[] bytes = converter.convertToBytes("part");
        
        Dictionary<String> dict = new TrieDictionary<String>();
        System.out.println(dict.getIdFromValue("Hello World!"));
        
        
    }
}
