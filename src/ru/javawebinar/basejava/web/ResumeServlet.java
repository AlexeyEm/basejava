package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.ResumeTestData;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private List<Resume> list;
    public void init(ServletConfig config) throws ServletException {
        Storage storage = new SqlStorage("jdbc:postgresql://localhost:5432/resumes", "postgres", "postgres");
        final Resume RESUME_1 = ResumeTestData.createResume("uuid1", "Full Name 1");
        final Resume RESUME_2 = ResumeTestData.createResume("uuid2", "Full Name 2");
        final Resume RESUME_3 = ResumeTestData.createResume("uuid3", "Full Name 3");
        final Resume RESUME_4 = ResumeTestData.createResume("uuid4", "Full Name 4");
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        storage.save(RESUME_4);
        list = storage.getAllSorted();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String name = request.getParameter("name");
//        response.getWriter().write(name == null ? "Hello Resumes!" : "Hello " + name + '!');
        PrintWriter out = response.getWriter();
        out.print(getTable());
    }

    private String getTable() {
        StringBuilder table = new StringBuilder("<html><body><table border=\"1\" width=\"200\"><tr><th>UUID</th><th>NAME</th></tr>");
        for (Resume r : list) {
            table.append("<tr><td>").append(r.getUuid()).append("</td><td>").append(r.getFullName()).append("</td></tr>");
        }
        table.append("</table></body></html>");
        return table.toString();
    }
}