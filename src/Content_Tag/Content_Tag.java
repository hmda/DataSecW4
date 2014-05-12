package Content_Tag;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.Reader;
import java.io.Writer;

import Article.Article;
import BST.BST;

import com.google.gson.Gson;


public class Content_Tag {
	public void getContent_Tag(){
		FileInputStream fos1; 
		FileOutputStream fos4, fos5;
		File[] paths = null;
		String pathTag;
		try {
			Reader input;
	        BufferedReader inputbuf;
	        String lineRead, content;
	        String[] temp;
	        Article art = new Article();
	        Gson gson = new Gson();
	        BST tree = new BST();
	        long find;
	        int i = 0;
	        
	        paths = readInputFolder();
	        int n = paths.length;
	        BST tag_trees = new BST();
	        File file = new File("input_Cluster");
			file.mkdirs();
			
	        FileOutputStream fos2 = new FileOutputStream("input_Cluster\\content.txt");
	        Writer output_content = new java.io.OutputStreamWriter(fos2,"UTF8");
	        
	        FileOutputStream fos3 = new FileOutputStream("input_Cluster\\tag.txt");
	        Writer output_tag = new java.io.OutputStreamWriter(fos3,"UTF8");
	        
	        file = new File("test_Cluster");
	        file.mkdirs();
	        for (File path:paths){
	        	fos1 = new FileInputStream(path);
				input = new java.io.InputStreamReader(fos1, "UTF8");
		        inputbuf = new BufferedReader(input);
		        
		        pathTag = path.toString();
		        pathTag = pathTag.replace("input", "test_Cluster");
		        pathTag = pathTag.replace(".tsv", ".txt");
		        fos4 = new FileOutputStream(pathTag);
		        Writer output_tag_art = new java.io.OutputStreamWriter(fos4,"UTF8");

	        while ((lineRead = inputbuf.readLine()) != null){
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
//	    			System.out.println(tag);
	        		tag = tag.toLowerCase();
	        		find = tag_trees.find(tag);
	        		if (find == -1){
                    	tag_trees.insert(tag);  // Đưa vào BST
                    	output_tag_art.write(tag + "\n");
                    }
                    find = tree.find(tag);    // Số lượng các nút giống thế trong BST
                    if (find == -1){
                    	tree.insert(tag);  // Đưa vào BST
    	        		output_tag.write(tag + "\n");
                    }
	        	}
	        }
	        input.close();
	        tag_trees.clear();
	        output_tag_art.close();
	        }
	        tree.clear();
	        output_content.close();
	        output_tag.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static File[] readInputFolder() {
		 File f = null;
		 File[] paths = null;
		 try{      
	         // create new file
	         f = new File("input");
	         
	         // create new filename filter
	         FilenameFilter fileNameFilter = new FilenameFilter() {
	   
	            @Override
	            public boolean accept(File dir, String name) {
	               if(name.lastIndexOf('.')>0)
	               {
	                  // get last index for '.' char
	                  int lastIndex = name.lastIndexOf('.');
	                  
	                  // get extension
	                  String str = name.substring(lastIndex);
	                  
	                  // match path name extension
	                  if(str.equals(".tsv"))
	                  {
	                     return true;
	                  }
	               }
	               return false;
	            }
	         };
	         // returns pathnames for files and directory
	         paths = f.listFiles(fileNameFilter);
	      }catch(Exception e){
	         // if any error occurs
	         e.printStackTrace();
	      }
		 return paths;
	   }
}
