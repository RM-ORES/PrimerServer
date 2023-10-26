package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import listeners.ThymeLeafListener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = Constantes.PRIMER_SERVLET, value = Constantes.PRIMER_SERVLET_VALUE)
public class PrimerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute(
                ThymeLeafListener.TEMPLATE_ENGINE_ATTR);
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);




        String numero = req.getParameter("numero");
        if (numero == null || numero.isEmpty()){
            context.setVariable("error", "Mete un número anda");
            templateEngine.process("error",context,resp.getWriter());
        } else {
            context.setVariable("numero", numero);
            templateEngine.process("home", context, resp.getWriter());

        }

//        Integer contador;
//        if (req.getSession().getAttribute(Constantes.CONTADOR) == null){
//            req.getSession().setAttribute(Constantes.CONTADOR,0);
//        }
//        contador = (Integer) req.getSession().getAttribute(Constantes.CONTADOR);
//        resp.getWriter().println(contador);
//        contador++;
//        req.getSession().setAttribute(Constantes.CONTADOR,contador);
    }
}
