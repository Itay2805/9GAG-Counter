package com.itay.ninegag;

import java.util.HashMap;

import com.itay.ninegag.NineGag.Sections;

public class Main implements Runnable {	
	public static void main(String[] args) {
		new Thread(new Main(Sections.XMAS), "9gag-counter$" + Sections.XMAS).start();
		new Thread(new Main(Sections.FUNNY), "9gag-counter$" + Sections.FUNNY).start();
		new Thread(new Main(Sections.NSFW), "9gag-counter$" + Sections.NSFW).start();
		new Thread(new Main(Sections.WTF), "9gag-counter$" + Sections.WTF).start();
		new Thread(new Main(Sections.GIF), "9gag-counter$" + Sections.GIF).start();
		new Thread(new Main(Sections.GEEKY), "9gag-counter$" + Sections.GEEKY).start();
		new Thread(new Main(Sections.MEME), "9gag-counter$" + Sections.MEME).start();
		new Thread(new Main(Sections.CUTE_ANIMALS), "9gag-counter$" + Sections.CUTE_ANIMALS).start();
	}
	
	private Sections section;
	
	public Main(Sections section) {
		this.section = section;
	}

	public void run() {
		Sections section = this.section;
		System.out.println("(9gag-counter$" + section + ") Started counting!");
		HashMap<String, String> posts = new HashMap<>();
		try {
			boolean shouldEnd = false;
			while(!shouldEnd) {
				NineGag.getNewPosts(posts, section);
				Thread.sleep(250);
				shouldEnd = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("(9gag-counter$" + section + ") Finished counting: " + (posts.size() - 9));
	}
	
}
