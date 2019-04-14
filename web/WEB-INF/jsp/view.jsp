<%@ page import="ru.javawebinar.basejava.model.ListSection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <hr>
    <c:forEach var="sectionEntry" items="${resume.sections}">
        <jsp:useBean id="sectionEntry"
                     type="java.util.Map.Entry<ru.javawebinar.basejava.model.SectionType, ru.javawebinar.basejava.model.AbstractSection>"/>
        <h2><%=sectionEntry.getKey().getTitle()%>
        </h2>
        <c:set var="sectionType" value="<%=sectionEntry.getKey()%>"/>
        <c:choose>
            <c:when test="${(sectionType == 'OBJECTIVE')||(sectionType == 'PERSONAL')}">
                <%=sectionEntry.getValue()%>
            </c:when>
            <c:when test="${(sectionType == 'ACHIEVEMENT')||(sectionType == 'QUALIFICATIONS')}">
                <ul>
                    <c:forEach var="organization" items="${(sectionEntry.getValue()).getItems()}">
                        <li>${organization}</li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:when test="${(sectionType == 'EXPERIENCE')||(sectionType == 'EDUCATION')}">
                <table cellpadding="2">
                    <c:forEach var="organization" items="${sectionEntry.getValue().getOrganizations()}">
                        <tr>
                            <td colspan="2">
                                <h3>
                                    <a href="${organization.getHomePage().getUrl()}">${organization.getHomePage().getName()}</a>
                                </h3>
                            </td>
                        </tr>
                        <c:forEach var="position" items="${organization.getPositions()}">
                            <tr>
                                <td width="15%" style="vertical-align: top">
                                    ${position.getPeriod()}
                                </td>
                                <td>
                                    <b>${position.getTitle()}</b>
                                    <br>${position.getDescription()}
                                </td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </table>
            </c:when>
        </c:choose>
    </c:forEach>
    <p>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>