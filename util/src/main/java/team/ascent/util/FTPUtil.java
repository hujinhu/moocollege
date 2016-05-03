package team.ascent.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author 作者 E-mail <a href="mailto:jqs@51box.cn">姜全生</a>
 * @version 创建时间：2012-6-26 下午04:36:41
 * 类说明
 * 支持断点续传的FTP实用类
 * @version 0.1 实现基本断点上传下载
 * @version 0.2 实现上传下载进度汇报
 * @version 0.3 实现中文目录创建及中文文件创建，添加对于中文的支持
 */
public class FTPUtil {
	private static Log log = LogFactory.getLog(FTPUtil.class);

	// 枚举类UploadStatus代码
	public enum UploadStatus {
		Create_Directory_Fail, // 远程服务器相应目录创建失败
		Create_Directory_Success, // 远程服务器闯将目录成功
		Upload_New_File_Success, // 上传新文件成功
		Upload_New_File_Failed, // 上传新文件失败
		File_Exits, // 文件已经存在
		Remote_Bigger_Local, // 远程文件大于本地文件
		Upload_From_Break_Success, // 断点续传成功
		Upload_From_Break_Failed, // 断点续传失败
		Delete_Remote_Faild; // 删除远程文件失败
	}

	// 枚举类DownloadStatus代码
	public enum DownloadStatus {
		Remote_File_Noexist, // 远程文件不存在
		Local_Bigger_Remote, // 本地文件大于远程文件
		Download_From_Break_Success, // 断点下载文件成功
		Download_From_Break_Failed, // 断点下载文件失败
		Download_New_Success, // 全新下载文件成功
		Download_New_Failed; // 全新下载文件失败
	}

	public FTPClient ftpClient = new FTPClient();
	private String host;
	private int port;
	private String username;
	private String password;

	// private String localFilePath;
	// private String remoteFilePath;

	public FTPUtil(String host, int port, String username, String password) {
		// 设置将过程中使用到的命令输出到控制台
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
//		this.ftpClient.addProtocolCommandListener(new PrintCommandListener(
//				new PrintWriter(System.out)));
	}

	/** */
	/**
	 * 连接到FTP服务器
	 * 
	 * @param hostname
	 *            主机名
	 * @param port
	 *            端口
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 是否连接成功
	 * @throws IOException
	 */
	public boolean connect() throws IOException {
		ftpClient.connect(host, port);
		ftpClient.setControlEncoding("GBK");
		if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
			if (ftpClient.login(username, password)) {
				return true;
			}
		}
		disconnect();
		return false;
	}

	/** */
	/**
	 * 从FTP服务器上下载文件,支持断点续传，上传百分比汇报
	 * 
	 * @param remote
	 *            远程文件路径
	 * @param local
	 *            本地文件路径
	 * @return 上传的状态
	 * @throws IOException
	 */
	public DownloadStatus download(String remote, String local)
			throws IOException {
		// 设置被动模式
		ftpClient.enterLocalPassiveMode();
		// 设置以二进制方式传输
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		DownloadStatus result;

		// 检查远程文件是否存在
		FTPFile[] files = ftpClient.listFiles(new String(remote
				.getBytes("UTF-8"), "iso-8859-1"));
		if (files.length != 1) {
			log.error("远程文件不存在");
			return DownloadStatus.Remote_File_Noexist;
		}

		long lRemoteSize = files[0].getSize();
		File f = new File(local);
		// 本地存在文件，进行断点下载
		if (f.exists()) {
			long localSize = f.length();
			// 判断本地文件大小是否大于远程文件大小
			if (localSize >= lRemoteSize) {
				log.error("本地文件大于远程文件，下载中止");
				return DownloadStatus.Local_Bigger_Remote;
			}

			// 进行断点续传，并记录状态
			FileOutputStream out = new FileOutputStream(f, true);
			ftpClient.setRestartOffset(localSize);
			InputStream in = ftpClient.retrieveFileStream(new String(remote
					.getBytes("UTF-8"), "iso-8859-1"));
			 BufferedReader in1 = new BufferedReader(new InputStreamReader(in));
		       in1.readLine(); //读取第一行，接下来从第二行开始循环数据
		       String line = "";
		       while ((line = in1.readLine()) != null) {
		    	   String[]  s = line.split("\t");
		       }
			in.close();
			out.close();
			boolean isDo = ftpClient.completePendingCommand();
			if (isDo) {
				result = DownloadStatus.Download_From_Break_Success;
			} else {
				result = DownloadStatus.Download_From_Break_Failed;
			}
		} else {
			OutputStream out = new FileOutputStream(f);
			InputStream in = ftpClient.retrieveFileStream(new String(remote
					.getBytes("UTF-8"), "iso-8859-1"));
			byte[] bytes = new byte[1024];
			long step = lRemoteSize / 100;
			long process = 0;
			long localSize = 0L;
			int c;
			while ((c = in.read(bytes)) != -1) {
				out.write(bytes, 0, c);
				localSize += c;
				long nowProcess = localSize / step;
				if (nowProcess > process) {
					process = nowProcess;
					if (process % 10 == 0)
						log.info("下载进度：" + process + "%");
					// TODO 更新文件下载进度,值存放在process变量中
				}
			}
			in.close();
			out.close();
			boolean upNewStatus = ftpClient.completePendingCommand();
			if (upNewStatus) {
				result = DownloadStatus.Download_New_Success;
			} else {
				result = DownloadStatus.Download_New_Failed;
			}
		}
		return result;
	}

	/** */
	/**
	 * 上传文件到FTP服务器，支持断点续传
	 * 
	 * @param local
	 *            本地文件名称，绝对路径
	 * @param remote
	 *            远程文件路径，使用/home/directory1/subdirectory/file.ext或是
	 *            http://IP:PORT/subdirectory/file.ext
	 *            按照Linux上的路径指定方式，支持多级目录嵌套，支持递归创建不存在的目录结构
	 * @return 上传结果
	 * @throws IOException
	 */
	public UploadStatus upload(String local, String remote) throws IOException {
		// 设置PassiveMode传输
		ftpClient.enterLocalPassiveMode();
		// 设置以二进制流的方式传输
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.setControlEncoding("UTF-8");
		UploadStatus result;
		// 对远程目录的处理
		String remoteFileName = remote;
		if (remote.contains("/")) {
			remoteFileName = remote.substring(remote.lastIndexOf("/") + 1);
			// 创建服务器远程目录结构，创建失败直接返回
			if (CreateDirecroty(remote, ftpClient) == UploadStatus.Create_Directory_Fail) {
				return UploadStatus.Create_Directory_Fail;
			}
		}

		// 检查远程是否存在文件
		FTPFile[] files = ftpClient.listFiles(new String(remoteFileName
				.getBytes("UTF-8"), "iso-8859-1"));
		if (files.length == 1) {
			long remoteSize = files[0].getSize();
			File f = new File(local);
			long localSize = f.length();
			if (remoteSize == localSize) {
				return UploadStatus.File_Exits;
			} else if (remoteSize > localSize) {
				return UploadStatus.Remote_Bigger_Local;
			}

			// 尝试移动文件内读取指针,实现断点续传
			result = uploadFile(remoteFileName, f, ftpClient, remoteSize);

			// 如果断点续传没有成功，则删除服务器上文件，重新上传
			if (result == UploadStatus.Upload_From_Break_Failed) {
				if (!ftpClient.deleteFile(remoteFileName)) {
					return UploadStatus.Delete_Remote_Faild;
				}
				result = uploadFile(remoteFileName, f, ftpClient, 0);
			}
		} else {
			result = uploadFile(remoteFileName, new File(local), ftpClient, 0);
		}
		return result;
	}

	public UploadStatus uploadAndReplace(String local, String remote) throws IOException {
		// 设置PassiveMode传输
		ftpClient.enterLocalPassiveMode();
		// 设置以二进制流的方式传输
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.setControlEncoding("UTF-8");
		UploadStatus result;
		// 对远程目录的处理
		String remoteFileName = remote;
		if (remote.contains("/")) {
			remoteFileName = remote.substring(remote.lastIndexOf("/") + 1);
			// 创建服务器远程目录结构，创建失败直接返回
			if (CreateDirecroty(remote, ftpClient) == UploadStatus.Create_Directory_Fail) {
				return UploadStatus.Create_Directory_Fail;
			}
		}
		
		File localFile = new File(local);
		// 显示进度的上传
		RandomAccessFile raf = new RandomAccessFile(localFile, "r");
		OutputStream out = ftpClient.appendFileStream(new String(remoteFileName
				.getBytes("UTF-8"), "UTF-8"));
		byte[] bytes = new byte[1024];
		int c;
		while ((c = raf.read(bytes)) != -1) {
			out.write(bytes, 0, c);
		}
		out.flush();
		raf.close();
		out.close();
		boolean _result = ftpClient.completePendingCommand();
		result = _result ? UploadStatus.Upload_New_File_Success
				: UploadStatus.Upload_New_File_Failed;
		return result;
	}
	
	/**
	 * 删除FTP服务器的文件
	 * 
	 * @param remotePath
	 * @param remoteFile
	 * @return
	 */
	public boolean delete(String remotePath, String remoteFile) {
		boolean status = false;
		try {
			boolean flag = ftpClient.deleteFile(remotePath + remoteFile);
			if (flag)
				if (ftpClient.getReplyCode() == 250)
					status = true;
		} catch (IOException e) {
			log.error("删除文件 " + remotePath + remoteFile + " 失败！！！");
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * 删除FTP服务器的文件
	 * 
	 * @param remotePath
	 * @param remoteFile
	 * @return
	 */
	public static boolean delete(String remotePath, String remoteFile,String url,int port,String username,String password) {
		boolean status = false;
		FTPUtil ftp = new FTPUtil(url,port,username,password);
		try {
			ftp.connect();
			ftp.ftpClient.setControlEncoding("utf-8");
			
			boolean flag = ftp.ftpClient.deleteFile(remotePath + remoteFile);
			if (flag)
				if (ftp.ftpClient.getReplyCode() == 250)
					status = true;
		} catch (IOException e) {
			log.error("删除文件 " + remotePath + remoteFile + " 失败！！！");
			e.printStackTrace();
		}
		return status;
	}
	
	

	/** */
	/**
	 * 断开与远程服务器的连接
	 * 
	 * @throws IOException
	 */
	public void disconnect() throws IOException {
		if (ftpClient.isConnected()) {
			ftpClient.disconnect();
		}
	}

	/** */
	/**
	 * 递归创建远程服务器目录
	 * 
	 * @param remote
	 *            远程服务器文件绝对路径
	 * @param ftpClient
	 *            FTPClient 对象
	 * @return 目录创建是否成功
	 * @throws IOException
	 */
	public UploadStatus CreateDirecroty(String remote, FTPClient ftpClient)
			throws IOException {
		UploadStatus status = UploadStatus.Create_Directory_Success;
		String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
		if (!directory.equalsIgnoreCase("/")
				&& !ftpClient.changeWorkingDirectory(new String(directory
						.getBytes("UTF-8"), "iso-8859-1"))) {
			// 如果远程目录不存在，则递归创建远程服务器目录
			int start = 0;
			int end = 0;
			if (directory.startsWith("/")) {
				start = 1;
			} else {
				start = 0;
			}
			end = directory.indexOf("/", start);
			while (true) {
				String subDirectory = new String(remote.substring(start, end)
						.getBytes("UTF-8"), "iso-8859-1");
				if (!ftpClient.changeWorkingDirectory(subDirectory)) {
					if (ftpClient.makeDirectory(subDirectory)) {
						ftpClient.changeWorkingDirectory(subDirectory);
					} else {
						log.error("创建目录失败");
						return UploadStatus.Create_Directory_Fail;
					}
				}

				start = end + 1;
				end = directory.indexOf("/", start);

				// 检查所有目录是否创建完毕
				if (end <= start) {
					break;
				}
			}
		}
		return status;
	}

	/** */
	/**
	 * 上传文件到服务器,新上传和断点续传
	 * 
	 * @param remoteFile
	 *            远程文件名，在上传之前已经将服务器工作目录做了改变
	 * @param localFile
	 *            本地文件 File句柄，绝对路径
	 * @param processStep
	 *            需要显示的处理进度步进值
	 * @param ftpClient
	 *            FTPClient 引用
	 * @return
	 * @throws IOException
	 */
	public UploadStatus uploadFile(String remoteFile, File localFile,
			FTPClient ftpClient, long remoteSize) throws IOException {
		UploadStatus status;
		// 显示进度的上传
		long step = localFile.length();
		double process = 0;
		long localreadbytes = 0L;
		RandomAccessFile raf = new RandomAccessFile(localFile, "r");
		OutputStream out = ftpClient.appendFileStream(new String(remoteFile
				.getBytes("UTF-8"), "UTF-8"));
		// 断点续传
		if (remoteSize > 0) {
			ftpClient.setRestartOffset(remoteSize);
			process = remoteSize / step;
			raf.seek(remoteSize);
			localreadbytes = remoteSize;
		}
		byte[] bytes = new byte[1024];
		int c;
		while ((c = raf.read(bytes)) != -1) {
			out.write(bytes, 0, c);
			localreadbytes += c;
			if (localreadbytes / step != process) {
				process = localreadbytes / step;
				log.info("上传进度:" + process * 100 + "%");
				// TODO 汇报上传状态
			}
		}
		out.flush();
		raf.close();
		out.close();
		boolean result = ftpClient.completePendingCommand();
		if (remoteSize > 0) {
			status = result ? UploadStatus.Upload_From_Break_Success
					: UploadStatus.Upload_From_Break_Failed;
		} else {
			status = result ? UploadStatus.Upload_New_File_Success
					: UploadStatus.Upload_New_File_Failed;
		}
		return status;
	}

	/**
	 * 判断指定文件名的文件是否存在<br>
	 * 返回匹配到的文件个数
	 * 
	 * @param fileName
	 * @return
	 */
	public int doExistsFile(String fileName) {
		try {
			FTPFile[] file = ftpClient.listFiles(fileName);
			return file.length;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Description: 向FTP服务器上传文件
	 * @param url FTP服务器
	 * @param port FTP服务器端口
	 * @param username FTP登录账号
	 * @param password FTP登录密码
	 * @param path FTP服务器保存目录
	 * @param filename 上传到FTP服务器上的文件名
	 * @param fileDir path下要创建的目录，如：新闻的目录news，软件的soft,文件目录规范：x/xx/xxx
	 * @param isUseDate 是否创建日期文件夹，true：创建，false：不创建
	 * @param input 输入流
	 * @return 成功返回图片所在文件夹，否则返回""
	 */
	public static String uploadFile(String url,int port,String username, String password, String path,String fileDir,boolean isUseDate, String filename, InputStream input){
		try{	
				String dir = "";
				if(null != fileDir && !"".equals(fileDir.trim())) dir = fileDir;
				if(isUseDate){
					String date=DateUtil.format(new Date(), "yyyy/MM/dd");
					dir = dir + "/" + date;
				}
				
				FTPUtil ftp = new FTPUtil(url,port,username,password);
				ftp.connect();
				ftp.ftpClient.setControlEncoding("utf-8");
				ftp.ftpClient.changeWorkingDirectory(path);
				//遍历创建所有目录
				String[] s=dir.split("/");
				for(int i=0;i<s.length;i++){
					ftp.ftpClient.makeDirectory(s[i]);
					path=path+"/"+s[i];
					ftp.ftpClient.changeWorkingDirectory(path);
				}
				ftp.ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				ftp.ftpClient.storeFile(new String(filename.getBytes(),"iso-8859-1"), input); //FTP协议里面，规定文件名编码为iso-8859-1，所以目录名或文件名需要转码。
				input.close();
				ftp.disconnect();
				return dir+"/"+filename;
			}catch(Exception e){
				e.printStackTrace();
				return "";
			}
	}
	public static InputStream readFile(String url,int port,String username, String password,String filename) throws IOException{
		FTPUtil ftp = new FTPUtil(url,port,username,password);
		ftp.connect();
		ftp.ftpClient.setControlEncoding("utf-8");
		ftp.ftpClient.enterLocalPassiveMode(); 
		InputStream in =ftp.ftpClient.retrieveFileStream(filename);
		ftp.disconnect();
		return in;
	}
	
	
}
