package com.itay.ninegag;

import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NineGag {
	
	private static final String WEBSITE = "http://9gag.com";
	private static final String FRESH = "/fresh";
	
	public enum Sections {
		FRESH("/"),
		XMAS("/xmas"),
		FUNNY("/funny"),
		NSFW("/nsfw"),
		WTF("/wtf"),
		GIF("/gif"),
		GEEKY("/geeky"),
		MEME("/meme"),
		CUTE_ANIMALS("/cute");
		
		private String link;
		
		private Sections(String link) {
			this.link = link;
		}
		
		public String getLink() {
			return link;
		}
	}
	
	public static HashMap<String, String> getNewPosts(HashMap<String, String> found, Sections section) throws Exception {
		if(found == null) throw new IllegalArgumentException("found can not be null!");
		HashMap<String, String> newFound = found;
		Document doc = Jsoup.connect(WEBSITE + section.getLink() + FRESH).userAgent("9gag-coutner$" + section).get();
		int count = found.size() - 9;
		Elements posts = doc.select(".badge-entry-container, .badge-entry-entity");
		for(Element element : posts) {
			String id = element.attr("data-entry-id");
			if(!newFound.containsKey(id)) {
				if(count > 0) {
					print("(9gag-counter$" + section + ")" + id + " Was added to the list | " + count);
				}
				count++;
				newFound.put(id, "post");
			}
		}
		return newFound;
	}
	
	private static void print(String s) {
		System.out.println(s);
	}
	
}
