package controllers.yoine;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Employee;
import models.Report;
import models.Yoine;
import util.DBUtil;

/**
 * Servlet implementation class YoineServlet
 */
@WebServlet("/yoine")
public class YoineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public YoineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

        Yoine y = new Yoine();

        y.setReport(r);

        HttpSession session = ((HttpServletRequest)request).getSession();

        //セッションスコープに保存された従業員情報を取得
        Employee e = (Employee)session.getAttribute("login_employee");

        y.setEmployee(e);

        em.getTransaction().begin();
        em.persist(y);
        em.getTransaction().commit();
        em.close();

        request.getSession().setAttribute("flush", "いいねしました。");

        response.sendRedirect(request.getContextPath() + "/reports/index");


    }

}
