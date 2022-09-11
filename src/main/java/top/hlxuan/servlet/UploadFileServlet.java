package top.hlxuan.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.IOUtils;


@WebServlet("/UploadFileServlet")
public class UploadFileServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置编码
		req.setCharacterEncoding("utf-8");
		
		// 获取文件列表
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// 设置文件大小小于1M放在内存中
		factory.setSizeThreshold(1024*1024);
		// 设置文件存放目录
		String path = req.getSession().getServletContext().getRealPath("upload");
		File uploadTmp = new File(path);
		uploadTmp.mkdirs();
		factory.setRepository(uploadTmp);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> list = upload.parseRequest(req);
			System.out.println("list=>"+list);
			for(FileItem fileItem : list) {
				System.out.print(fileItem);
				// 判断是否为普通表单项
				boolean fileField = fileItem.isFormField();
				if(fileField) {
					if("name".equals(fileItem.getFieldName())) {
						String name = fileItem.getString("utf-8");
						
						System.out.println("from=>"+name);
						System.out.println("\n");
					}
				}else {
					// 获取文件名称
					String fileName = fileItem.getName();
					
					// 获取文件后缀名
					String point = fileName.substring(fileName.lastIndexOf("."));
					String rename = UUID.randomUUID().toString()+point;
					
					InputStream in = fileItem.getInputStream();
					OutputStream out = new FileOutputStream(path+"/"+rename);
					System.out.println("Rename=>"+rename);
					System.out.println("\n");
					IOUtils.copy(in, out);
					in.close();
					out.close();
					// 删除缓存文件
					fileItem.delete();
					// 将信息传入request域中
					req.setAttribute("upload_path", getServletContext().getContextPath()+"/upload/"+rename);
					
				}
			}
			
			// 将信息传入request域中
			req.setAttribute("upload_msg", "上传成功！");
			req.setAttribute("upload_type", "success");
			// 跳转回upload.jsp
			req.getRequestDispatcher("/upload.jsp").forward(req, resp);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 将信息传入request域中
			req.setAttribute("upload_msg", "上传失败！");
			req.setAttribute("upload_type", "warning");
			// 跳转回upload.jsp
			req.getRequestDispatcher("/upload.jsp").forward(req, resp);
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
}
