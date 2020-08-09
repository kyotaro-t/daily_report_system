package controllers.yoine;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import models.Yoine;
import util.DBUtil;
/**
 * Servlet implementation class YoineServlet
 */
@WebServlet("/yoine/destroy")
public class YoineDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public YoineDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");

        Report report = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
        List<Yoine> yoines = em.createNamedQuery("destroyYoine", Yoine.class)
                .setParameter("report", report)
                .setParameter("employee", login_employee)
                .getResultList();

        em.getTransaction().begin();
        em.remove(yoines.get(0));   //削除
        em.getTransaction().commit();
        request.getSession().setAttribute("flush", "解除が完了しました。");
        em.close();

        response.sendRedirect(request.getContextPath() + "/reports/index");

    }

}
