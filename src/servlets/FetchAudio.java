package servlets;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FetchAudio")
public class FetchAudio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream stream = null;
		BufferedInputStream buf = null;
		String fileName = "Example - Whisky Story.mp3";
		try {
			stream = response.getOutputStream();
			File mp3 = new File("/myCollectionOfSongs" + "/" + fileName);

			// set response headers
			response.setContentType("audio/mpeg");

			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);

			response.setContentLength((int) mp3.length());
			FileInputStream input = new FileInputStream(mp3);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			// read from the file; write to the ServletOutputStream
			while ((readBytes = buf.read()) != -1)
				stream.write(readBytes);
		} catch (IOException ioe) {
			throw new ServletException(ioe.getMessage());
		} finally {
			if (stream != null)
				stream.close();
			if (buf != null)
				buf.close();
		}
		RequestDispatcher rd = req.getRequestDispatcher("./index.jsp");
		rd.forward(req, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		// ServletOutputStream stream = null;
		// BufferedInputStream buf = null;
		// String fileName = "Example - Whisky Story.mp3";
		// try {
		// stream = response.getOutputStream();
		// File mp3 = new File("/myCollectionOfSongs" + "/" + fileName);
		//
		// //set response headers
		// response.setContentType("audio/mpeg");
		//
		// response.addHeader("Content-Disposition", "attachment; filename=" +
		// fileName);
		//
		// response.setContentLength((int) mp3.length());
		//
		// FileInputStream input = new FileInputStream(mp3);
		// buf = new BufferedInputStream(input);
		// int readBytes = 0;
		// //read from the file; write to the ServletOutputStream
		// while ((readBytes = buf.read()) != -1)
		// stream.write(readBytes);
		// } catch (IOException ioe) {
		// throw new ServletException(ioe.getMessage());
		// } finally {
		// if (stream != null)
		// stream.close();
		// if (buf != null)
		// buf.close();
		// }
	}

}
