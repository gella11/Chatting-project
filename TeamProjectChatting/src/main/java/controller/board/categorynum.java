package controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.Dao.boardDao.boardDao;
import model.Dto.memberDto.CategoryDto;
@WebServlet("/categorynum")
public class categorynum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public categorynum() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		ArrayList<CategoryDto> category = boardDao.getInstance().categorylist();
		JSONArray array = new JSONArray();
		
		for(CategoryDto dto : category) {
			JSONObject object = new JSONObject();
			object.put("c_no", dto.getC_no());
			object.put("c_name", dto.getC_name());
			array.add(object);
		}
		System.out.println("서블릿 카테고리 :" + array);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(array);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
