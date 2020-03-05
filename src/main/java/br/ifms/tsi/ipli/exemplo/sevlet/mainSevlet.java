/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.tsi.ipli.exemplo.sevlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rodrigo
 */
@WebServlet("/")
public class mainSevlet extends HttpServlet {

    private List<String> nomes = new ArrayList<String>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");

        PrintWriter out = resp.getWriter();

        out.println("<html>");

        out.println("<head>");

        out.println("<body>");

        if (acao == null) {

            out.println("<form method='POST'>");

            out.println("<label>");

            out.println("Nome:");

            out.println("</label>");

            out.println("<input name='nome' type='text'/>");

            out.println("<input type='hidden' name='acao' value='salvar'/>");

            out.println("<button type='submit'>Salvar!</button>");

            out.println("</form>");

        } else if (acao.equals("editar")) {

            String index = req.getParameter("index");

            String nome = nomes.get(Integer.parseInt(index));

            out.println("<form method='POST'>");

            out.println("<label>");

            out.println("Nome:");

            out.println("</label>");

            out.println("<input name='nome' type='text' value='" + nome + "'/>");

            out.print("<input type='hidden' name='index' value='" + index + "'/>");

            out.println("<input type='hidden' name='acao' value='editar'/>");

            out.println("<button type='submit'>Salvar!</button>");

            out.println("</form>");

        }

        out.println("<table>");

        out.println("<theader>");

        out.println("<th>");

        out.println("Nome");

        out.println("</th>");

        out.println("</theader>");

        out.println("<tbody>");

        for (int i = 0; i < nomes.size(); i++) {

            out.print("<tr>");

            out.print("<td>" + nomes.get(i) + "</td>");

            out.print("<td>");

            out.print("<form method='GET'>");

            out.print("<input type='hidden' name='index' value='" + i + "'/>");

            out.print("<input type='hidden' name='acao' value='editar'/>");

            out.print("<button type='submit'>Editar</button>");

            out.print("</form>");

            out.print("</td>");

            out.print("<td>");

            out.print("<form method='POST'>");

            out.print("<input type='hidden' name='index' value='" + i + "'/>");

            out.print("<input type='hidden' name='acao' value='delete'/>");

            out.print("<button type='submit'>Remover</button>");

            out.print("</form>");

            out.print("</td>");

            out.print("</tr>");

        }

        out.println("</tbody>");

        out.println("</table>");

        out.println("</body>");

        out.println("</head>");

        out.println("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");

        if ("salvar".equals(acao)) {
            String nome = req.getParameter("nome");
            if (nome == null) {
                resp.sendRedirect(req.getContextPath() + "/");
            }
            nomes.add(nome);
            resp.sendRedirect(req.getContextPath() + "/");
        }

        if ("delete".equals(acao)) {
            doDelete(req, resp);
        }

        if ("editar".equals(acao)) {
            doPut(req, resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String index = req.getParameter("index");
        nomes.remove(Integer.parseInt(index));
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String index = req.getParameter("index");
        String novoNome = req.getParameter("nome");

        nomes.remove(Integer.parseInt(index));
        nomes.add(Integer.parseInt(index), novoNome);
        resp.sendRedirect(req.getContextPath() + "/");

    }

}
