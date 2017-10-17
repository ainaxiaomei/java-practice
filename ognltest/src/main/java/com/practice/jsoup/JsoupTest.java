package com.practice.jsoup;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {

	public static void main(String[] args) throws IOException {
		for (int i = 1; i < 10; i++) {
			Document doc = Jsoup
					.connect("http://cl.8ul.pw/thread0806.php?fid=25thread0806.php?fid=25&search=&page=" + i).get();
			Element table = doc.getElementById("ajaxtable");
			Elements trs = table.getElementsByClass("tr3 t_one tac");
			Iterator<Element> itr = trs.iterator();
			while (itr.hasNext()) {
				Element tr = itr.next();
				if (tr.children().size() < 4)
					continue;
				String author = tr.child(4).ownText();
				if ("by: 淫魔之首".equals(author) || "by: zghome".equals(author) 
						|| "by: zghome".equals(author) ||"by: big0083".equals(author)) {
					System.out.println(tr.child(0));
				}
			}
		}

	}

}
