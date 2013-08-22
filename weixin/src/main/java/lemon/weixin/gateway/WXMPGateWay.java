package lemon.weixin.gateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lemon.shared.api.MmtAPI;
import lemon.weixin.biz.WXGZAPI;
import static lemon.weixin.util.WXHelper.*;

/**
 * The gateway of Weixin
 * 
 * @author lemon
 * 
 */
public class WXMPGateWay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MmtAPI wxAPI = new WXGZAPI();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// first, get the message
		String msg = getMessage(req);
		// second, save log
		// TODO save log

		// third, replay message by business logic
		sendMessage(resp,msg);
	}

	/**
	 * get receive message via input stream
	 * 
	 * @param req
	 * @return
	 * @throws IOException
	 */
	private String getMessage(HttpServletRequest req) throws IOException {
		InputStream is = null;
		try {
			is = req.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null)
				sb.append(line);
			return new String(sb.toString().getBytes(LOCAL_CHARSET),
					TARGET_CHARSET);
		} finally {
			if (null != is)
				is.close();
		}
	}

	private void sendMessage(HttpServletResponse resp, String msg)
			throws IOException {
		PrintWriter out = null;
		try {
			resp.setCharacterEncoding(LOCAL_CHARSET);
			out = resp.getWriter();
			System.out.println(msg);
			out.println(wxAPI.getReplayMsg(msg));
			out.flush();
		} finally {
			if (null != out)
				out.close();
		}
	}

}