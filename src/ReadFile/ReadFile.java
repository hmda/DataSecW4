package ReadFile;

import Tokenizer.Tokenizer;
import Content_Tag.Content_Tag;

public class ReadFile {
	public static void main(String[] args) {
		Content_Tag content_tag = new Content_Tag();
		content_tag.getContent_Tag();
		Tokenizer tokenizer = new Tokenizer();        
        tokenizer.token("input_Cluster\\baomoi_content.txt", "input_Cluster\\token.txt"); // Xác định các từ.
	}
}
