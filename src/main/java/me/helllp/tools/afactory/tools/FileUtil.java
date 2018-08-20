package me.helllp.tools.afactory.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 所有关于文件和目录操作的公共类
 * 
 * @author 林晓明
 *
 */
public class FileUtil {
	
	/**
	 * 创建目录
	 * @param path			输入目录参数
	 * @throws Exception
	 */
	public static void makeDir(String path)throws Exception{
		File dir = new File(path);
		makeDir(dir);
	}
	
	public static void makeDir(String base, String path) {
		File dir = new File(base + "/" + path);
		makeDir(dir);
	}
	
	/**
	 * 创建目录
	 * @param dir
	 */
	public static void makeDir(File dir){
		File parent = dir.getParentFile();
		
		if(parent.exists() == false){
			makeDir(parent);
		}
		
		if (dir.exists() == false){
			dir.mkdirs();
		}		
	}
	
	/**
	 * 递归删除文件夹下的所有文件及文件夹
	 * @param dir
	 */
	public static void delFile(File dir){
		if(dir.isDirectory()){
			File [] files = dir.listFiles();
			for(File file : files){
				delFile(file);
			}
		}
		dir.delete();
	}	
	
	public static void delFile(String dir){
		File dirT = new File(dir);
		if (dirT.exists() == true) {
			delFile(dirT);
		}
	}
	
	/**
	 * 进行首字母的大小写转换
	 * 
	 * @param strSource
	 * @param isToUp
	 * @return
	 */
	public static String convertFirstChar(String strSource,boolean isToUp){
		StringBuffer buffer = new StringBuffer();
		
		if(isToUp){		//	首字母大写
			buffer.append(strSource.substring(0,1).toUpperCase()).append(strSource.substring(1));
		}else{				//	首字母小写
			buffer.append(strSource.substring(0,1).toUpperCase()).append(strSource.substring(1));
		}
		
		return buffer.toString();
	}
	
	/** 
	 * @Title: 			hasFile 
	 * @Description: 	判断文件是否存在 
	 * @param fileName
	 * @return  
	 * @throws 
	 */
	public static boolean hasFile(String fileName){
		return new File(fileName).exists();
	}
	
	/** 
	 * @Title: 			writeFile 
	 * @Description: 	将内容输出到文件中 
	 * @param fileName
	 * @param context
	 * @throws IOException  
	 * @throws 
	 */
	public static void writeFile(String fileName,String context) throws IOException{
		FileWriter fileWritter = new FileWriter(fileName,false);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		bufferWritter.write(context);
		fileWritter.flush();
		
		bufferWritter.close();	
		fileWritter.close();					
	}

	/**
	 * 根据包名称生成对应的文件路径
	 * 
	 * @param packageName
	 * @return
	 */
	public static String makePathByPackage(String packageName){
		String[] args = packageName.split("\\.");
		
		StringBuffer buffer = new StringBuffer();
		
		for(int i=0; i<args.length; i++){
			if(i != 0){
				buffer.append("/");	
			}
			
			buffer.append(args[i]);
		}
		
		return buffer.toString();
	}		
	
	/** 
	 * @Title: 			appendPath 
	 * @Description: 	将路径进行拼接 
	 * @param pathBase	源路径
	 * @param pathAdd	增加的路径
	 * @return  
	 * @throws 
	 */
	public static String appendPath(String pathBase,String pathAdd){
		String baseStr = sideTrim(sideTrim(pathBase,"\\\\",false,true),"/",false,true);
		String addStr = sideTrim(sideTrim(pathAdd,"\\\\",true,false),"/",true,false);
		
		return new StringBuffer().append(baseStr).append("/").append(addStr).toString();
	}
	
	/** 
	 * @Title: 			appendPackage 
	 * @Description: 	拼接包 
	 * @param packageBase
	 * @param packageAdd
	 * @return  
	 * @throws 
	 */
	public static String appendPackage(String packageBase,String packageAdd){
		String baseStr = sideTrim(sideTrim(packageBase,".",false,true),".",false,true);
		String addStr = sideTrim(sideTrim(packageAdd,".",true,false),".",true,false);
		
		return new StringBuffer().append(baseStr).append(".").append(addStr).toString();		
	}
	
	/** 
	 * @Title: 			sideTrim 
	 * @Description: 	删除字符串头或尾中指定的字符 
	 * @param stream	源字符串
	 * @param trimstr	待删除的字符串
	 * @param hasHead	是否包括头
	 * @param hasTail	是否包括尾
	 * @return  
	 * @throws 
	 */
	public static String sideTrim(String stream, String trimstr, boolean hasHead, boolean hasTail) {
		// null或者空字符串的时候不处理
		if (stream == null || stream.length() == 0 || trimstr == null
				|| trimstr.length() == 0) {
			return stream;
		}

		// 结束位置
		int epos = 0;

		// 正规表达式
		String regpattern = "[" + trimstr + "]*+";
		Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

		
		StringBuffer buffer = new StringBuffer(stream).reverse();
		Matcher matcher = pattern.matcher(buffer);
		
		if(hasTail){
			// 去掉结尾的指定字符
			if (matcher.lookingAt()) {
				epos = matcher.end();
				stream = new StringBuffer(buffer.substring(epos)).reverse()
						.toString();
			}			
		}

		if(hasHead){
			// 去掉开头的指定字符
			matcher = pattern.matcher(stream);
			if (matcher.lookingAt()) {
				epos = matcher.end();
				stream = stream.substring(epos);
			}			
		}

		// 返回处理后的字符串
		return stream;
	}
	
	public static String getSubPath(File base, File file) {
		return file.getAbsolutePath().replaceAll(base.getAbsolutePath(), "");
	}
	
	public static void fileIterator(File f, List<String> list){
		if(f.isDirectory()) {
			list.add(f.getAbsolutePath());
		}
		
		for(File file : f.listFiles()) {
			if(file.isDirectory()) {
				fileIterator(file,list);
			}
		}
	}
}
