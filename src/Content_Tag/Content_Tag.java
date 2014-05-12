package Content_Tag;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.Writer;

import Article.Article;
import BST.BST;

import com.google.gson.Gson;


public class Content_Tag {
	public void getContent_Tag(){
		FileInputStream fos1;
		try {
			fos1 = new FileInputStream("input\\baomoi_20140408050320.tsv");
			Reader input = new java.io.InputStreamReader(fos1, "UTF8");
	        BufferedReader inputbuf = new BufferedReader(input);
	        String lineRead, content;
	        String[] temp;
	        Article art = new Article();
	        Gson gson = new Gson();
	        BST tree = new BST();
	        long find;
	        int n;
	        
	        File file = new File("input_Cluster");
			file.mkdirs();
			
	        FileOutputStream fos2 = new FileOutputStream("input_Cluster\\baomoi_content.txt");
	        Writer output_content = new java.io.OutputStreamWriter(fos2,"UTF8");
	        
	        FileOutputStream fos3 = new FileOutputStream("input_Cluster\\baomoi_tag.txt");
	        Writer output_tag = new java.io.OutputStreamWriter(fos3,"UTF8");
	        
	        n = 0;
	        while ((lineRead = inputbuf.readLine()) != null){
	        	if (n > 500) break;
	        	temp = lineRead.split("\t");
	        	lineRead = temp[3];
	        	art = gson.fromJson(lineRead, Article.class);
	        	content = art.getContent();
	        	content = content.replace("\n", " ");
	        	content = content.replace("<strong>", "");
	        	content = content.replace("</strong>", "");
	        	content = content.replaceAll("\\s+", " ");
	        	content = content.replaceAll("(^\\s+|\\s+$)", "");
	        	output_content.write(content + "\n");
	        	temp = art.getTags();
	        	for (String tag:temp){
	        		tag = tag.toLowerCase();
                    find = tree.find(tag);    // Số lượng các nút giống thế trong BST
                    if (find == -1){
                    	tree.insert(tag);  // Đưa vào BST
    	        		output_tag.write(tag + "\n");
                    }
	        	}
	        	n++;
	        }
	        tree.clear();
	        input.close();
	        output_content.close();
	        output_tag.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
