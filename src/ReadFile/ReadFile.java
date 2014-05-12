package ReadFile;

import Tokenizer.Tokenizer;
import Content_Tag.Content_Tag;

public class ReadFile {
	private static Tokenizer tokenizer = new Tokenizer(); 
	public static void main(String[] args) {
		Content_Tag content_tag = new Content_Tag();
		content_tag.getContent_Tag();       
        tokenizer.token("input_Cluster\\content.txt", "input_Cluster\\token.txt"); // Xác định các từ.
	}
}
