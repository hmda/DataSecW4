package Tokenizer;


import vn.hus.nlp.tokenizer.VietTokenizer;

public class Tokenizer {
	public void token(String pathRead, String pathWrite){
		 VietTokenizer tokenizer = new VietTokenizer();
	     tokenizer.tokenize(pathRead, pathWrite);
	}
}
