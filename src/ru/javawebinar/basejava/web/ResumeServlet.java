package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    private Storage storage; // = Config.get().getStorage();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume r;
        if (uuid.equals("")) {
            r = new Resume(fullName);
        } else {
            r = storage.get(uuid);
            r.setFullName(fullName);
        }
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.addContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            if (type.equals(SectionType.OBJECTIVE) || type.equals(SectionType.PERSONAL) || type.equals(SectionType.QUALIFICATIONS) || type.equals(SectionType.ACHIEVEMENT)) {
                String value = request.getParameter(type.name());
                if (value != null && value.trim().length() != 0) {
                    List<String> list = Arrays.asList(value.split("\n"));
                    r.addSection(type, new ListSection(list));
                } else {
                    r.getSections().remove(type);
                }
            }
        }
        addSections(request, r, SectionType.EXPERIENCE.name());
        addSections(request, r, SectionType.EDUCATION.name());
        if (uuid.equals("")) {
            storage.save(r);
        } else {
            storage.update(r);
        }
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                r = storage.get(uuid);
                break;
            case "add":
                r = new Resume();
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    private void addSections(HttpServletRequest request, Resume r, String type) {
        int sectionCount = Integer.parseInt(request.getParameter(type));
        List<Organization> organizations = new ArrayList<>();
        for (int i = 1; i <= sectionCount; i++) {
            Link link = new Link(request.getParameter("organizationName" + type + i), request.getParameter("organizationUrl" + type + i));
            List<Organization.Position> positions = new ArrayList<>();
            for (int j = 1; j <= Integer.parseInt(request.getParameter("positions" + type + i)); j++) {
                positions.add(new Organization.Position(
                        LocalDate.parse(request.getParameter("positionBegin" + type + i + j))
                        , LocalDate.parse(request.getParameter("positionEnd" + type + i + j))
                        , request.getParameter("positionName" + type + i + j)
                        , request.getParameter("positionDesc" + type + i + j)
                ));
            }
            organizations.add(new Organization(link, positions));
        }
        r.addSection(SectionType.valueOf(type), new OrganizationSection(organizations));
    }
}