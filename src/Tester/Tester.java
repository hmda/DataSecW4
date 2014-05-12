package Tester;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.Reader;
import java.io.Writer;

import Article.Article;
import BST.BST;

public class Tester {
	public static void main(String[] args) {
		int amount1[], amount2[], n, i, m, word[][], total = 0;
		long find;
		File[] paths = null;
		String lineRead, pathTag;
		FileInputStream fos1; 
		Reader input;
		BufferedReader inputbuf;
		BST trees[];
		BST tn = new BST();
		
		paths = readFileFolder("test_Cluster");
		n = paths.length;
		amount1 = new int[n];
		trees = new BST[n];
		
		double nmi, I, HC, HW, max[], purity, temp = 0;
		
		i = 0;
		try {
		for (File path:paths){
			fos1 = new FileInputStream(path);
			input = new java.io.InputStreamReader(fos1, "UTF8");
	        inputbuf = new BufferedReader(input);
	        trees[i] = new BST();
	        while ((lineRead = inputbuf.readLine()) != null){
//	        	System.out.println(lineRead);
	        	trees[i].insert(lineRead); 
	        	amount1[i]++;
	        	total++;
	        }
	        input.close();
	        i++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		paths = readFileFolder("clusters");
		m = paths.length;
		amount2 = new int[m];
		word = new int[n][m];
		i = 0;
		try {
			for (File path:paths){
				fos1 = new FileInputStream(path);
				input = new java.io.InputStreamReader(fos1, "UTF8");
		        inputbuf = new BufferedReader(input);
		        
		        while ((lineRead = inputbuf.readLine()) != null){
		        	for (int j = 0; j < m; j++){
		        		find = trees[j].find(lineRead); 
		        		if (find > 0){
		        			word[i][j]++;
		        		}
		        	}
	        	amount2[i]++;
		        }
		        input.close();
		        i++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		I = countI (amount1, amount2, word, n, m, total);
		HC = countH (amount1, word, n, total);
		HW = countH (amount2, word, m, total);
		nmi = 2*I/(HC + HW);
		
		max = Max (word, n, m);
		for (i = 0; i < n; i++){
			temp = temp + max[i];
		}
		purity = temp/total;
		
		System.out.println("Tổng số từ: " + total);
		System.out.println("Các từ mà phân tập và kết quả giao nhau:");
		for (i = 0; i < n; i++){
			for (int j = 0; j < m; j++){
				System.out.print("--" + word[i][j]);
			}
			System.out.println();
		}
		System.out.println("Độ chính xác NMI: " + nmi);
		System.out.println("Độ chính xác purity: " + purity);
	}
	private static File[] readFileFolder(String folder) {
		 File f = null;
		 File[] paths = null;
		 try{      
	         // create new file
	         f = new File(folder);
	         
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
	                  if(str.equals(".txt"))
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
	
	private static double countI (int amount1[], int amount2[], int word[][], int n, int m, int total){
		double result = 0, temp = 0, temp1;
		for (int i = 0; i < n; i++){
		for (int j = 0; j < m; j++){
			temp1 = (double)word[i][j]/total;
//			temp1 = (double) 109/5914;
			temp = - (temp1*Math.log10((double)(total*word[i][j])/(amount1[i]*amount2[i])));
			result = temp +result;
		}
		}
		return result;
	}
	
	private static double countH (int amount[], int word[][], int m, int total){
		double result = 0, temp;
			for (int j = 0; j < m; j++){
				temp = ((double)amount[j]/total)*Math.log10((double)amount[j]/total);
				result = temp +result;
			}
		return result;
	}
	
	private static double[] Max (int word[][], int n, int m){
		double result[] = new double[n];
		
		for (int i = 0; i < n; i++){
			result[i] = word[i][0];
			for (int j = 0; j < m; j++){
				if (word[i][j] > result[i])  result[i] = word[i][j];
			}
		}
		
		return result;
	}
}
