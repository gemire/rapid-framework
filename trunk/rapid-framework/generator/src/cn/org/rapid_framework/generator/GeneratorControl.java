package cn.org.rapid_framework.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import cn.org.rapid_framework.generator.util.FileHelper;
import cn.org.rapid_framework.generator.util.GLogger;
import cn.org.rapid_framework.generator.util.IOHelper;

import freemarker.ext.dom.NodeModel;

/**
 * 生成器模板控制器,用于模板中可以控制生成器执行相关控制操作
 * 如: 是否覆盖目标文件
 * 
 * <pre>
 * 使用方式:
 * 可以在freemarker或是veloctiy中直接控制模板的生成
 * 
 * ${gg.generateFile('d:/g_temp.log','info_from_generator')}
 * ${gg.setIgnoreOutput(true)}
 * </pre>
 * 
 * ${gg.setIgnoreOutput(true)}将设置为true如果不生成
 * 
 * @author badqiu
 *
 */
public class GeneratorControl {
	private Date now = new Date();
	private boolean isOverride = true; 
	private boolean ignoreOutput = false; // or isGenerate
	private boolean isMergeIfExists = true;
	private String mergeLocation; //no pass
	private String outRoot; 
	private String outputEncoding; //no pass
	private String sourceFile; 
	private String sourceDir; 
	private String sourceFileName; 
	private String sourceEncoding; //no pass //? 难道process两次确定sourceEncoding
	private String databaseType; //no pass //mysql,oracle,用于生成不同的sql 
	
	/** load xml data */
	public NodeModel loadXml(String file,boolean ignoreError) {
		try {
			return NodeModel.parse(new File(file));
		} catch (Exception e) {
			GLogger.error("loadXml error,file:"+file);
			if(ignoreError) {
				return null;
			}else {
				throw new IllegalArgumentException("loadXml error,file:"+file+" cause:"+e,e);
			}
		}
	}
	
	public void generateFile(String outputFile,String content) {
		try {
			IOHelper.saveFile(new File(outputFile), content);
		} catch (Exception e) {
			GLogger.warn("generate outputFile occer error,caused by:"+e);
		}
	}

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

	public boolean isOverride() {
		return isOverride;
	}

	public void setOverride(boolean isOverride) {
		this.isOverride = isOverride;
	}

	public boolean isIgnoreOutput() {
		return ignoreOutput;
	}

	public void setIgnoreOutput(boolean ignoreOutput) {
		this.ignoreOutput = ignoreOutput;
	}

	public boolean isMergeIfExists() {
		return isMergeIfExists;
	}

	public void setMergeIfExists(boolean isMergeIfExists) {
		this.isMergeIfExists = isMergeIfExists;
	}

	public String getMergeLocation() {
		return mergeLocation;
	}

	public void setMergeLocation(String mergeLocation) {
		this.mergeLocation = mergeLocation;
	}

	public String getOutRoot() {
		return outRoot;
	}

	public void setOutRoot(String outRoot) {
		this.outRoot = outRoot;
	}

	public String getOutputEncoding() {
		return outputEncoding;
	}

	public void setOutputEncoding(String outputEncoding) {
		this.outputEncoding = outputEncoding;
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	public String getSourceDir() {
		return sourceDir;
	}

	public void setSourceDir(String sourceDir) {
		this.sourceDir = sourceDir;
	}

	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	public String getSourceEncoding() {
		return sourceEncoding;
	}

	public void setSourceEncoding(String sourceEncoding) {
		this.sourceEncoding = sourceEncoding;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}
	
	
}
