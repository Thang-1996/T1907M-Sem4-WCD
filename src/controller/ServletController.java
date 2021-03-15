package controller;

import entity.Product;
import model.ProductFinderBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletController")
public class ServletController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productname = request.getParameter("name");
        String productdesc = request.getParameter("desc");
        Product product = new Product();
        product.setName(productname);
        product.setDesc(productdesc);
        ProductFinderBean finder = new ProductFinderBean();
        try {
            if(finder.createProduct(product)){

               response.sendRedirect("listproduct.jsp");
            }else{
                response.sendRedirect("add.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getServletPath();
        switch (option){
            case "add" :
                createNewForm(request,response);
                break;
            case "update" :
                updateNewForm(request,response);
                break;
            case "delete" :
                deleteNewForm(request,response);
                break;
            case "search" :
                searchNewForm(request,response);
                break;
            default :
                selectForm(request,response);
                break;
        }
    }
    protected void createNewForm(HttpServletRequest request, HttpServletResponse response){

    }
    protected void updateNewForm(HttpServletRequest request, HttpServletResponse response){

    }
    protected void deleteNewForm(HttpServletRequest request, HttpServletResponse response){

    }
    protected void selectForm(HttpServletRequest request, HttpServletResponse response){

    }
    protected void searchNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String keywordData = request.getParameter("name");
        System.out.println(keywordData);
        if(keywordData == null || keywordData.trim().isEmpty()){
            response.sendRedirect("search.jsp?msg=Enter your keyword");
            return;
        }
        //step 2
        ProductFinderBean finder = new ProductFinderBean();
        finder.setKeyword(keywordData);

        //step
        //co 2 cach de lam b3 la session - doi voi session thif phai check trang thai con lam bang dispatcher thi

//        HttpSession session = request.getSession();
//        session.setAttribute("finder", finder);
        request.setAttribute("finder",finder);

        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request,response);
    }

}
