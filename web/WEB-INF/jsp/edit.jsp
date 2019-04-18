<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Секции:</h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:choose>
                <c:when test="${(type == 'PERSONAL')||(type == 'OBJECTIVE')||(type == 'ACHIEVEMENT')||(type == 'QUALIFICATIONS')}">
                    <dl>
                        <dt>${type.title}</dt>
                        <dd><textarea name="${type.name()}" cols="50" rows="3">${resume.getSection(type)}</textarea>
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${(type == 'EXPERIENCE')||(type == 'EDUCATION')}">
                    <dl>
                        <dt>${type.title}</dt>
                        <dd>
                            <c:set var="orgNum" value="0" scope="page"/>
                            <c:forEach var="organization" items="${resume.getSection(type).getOrganizations()}">
                                <c:set var="orgNum" value="${orgNum + 1}" scope="page"/>
                                <table border="0">
                                    <tr>
                                        <th align="left">Организация:</th>
                                        <td align="left" colspan="2"><input type="text" name="organizationName${type}${orgNum}"
                                                                            value='${organization.getHomePage().getName()}'>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="left">Сайт:</td>
                                        <td align="left" colspan="2"><input type="text" name="organizationUrl${type}${orgNum}"
                                                                            value="${organization.getHomePage().getUrl()}">
                                        </td>
                                    </tr>
                                    <c:set var="posNum" value="0" scope="page"/>
                                    <c:forEach var="position" items="${organization.getPositions()}">
                                        <c:set var="posNum" value="${posNum + 1}" scope="page"/>
                                        <tr>
                                            <td></td>
                                            <td>Дата начала:</td>
                                            <td><input type="date" name="positionBegin${type}${orgNum}${posNum}" size=30
                                                       value="${position.getStartDate()}">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>Дата окончания:</td>
                                            <td><input type="date" name="positionEnd${type}${orgNum}${posNum}" size=30
                                                       value="${position.getEndDate()}">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>Позиция:</td>
                                            <td><input type="text" name="positionName${type}${orgNum}${posNum}" size=30
                                                       value='${position.getTitle()}'>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>Описание:</td>
                                            <td><input type="text" name="positionDesc${type}${orgNum}${posNum}" size=30
                                                       value='${position.getDescription()}'>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <hr>
                                <input type="hidden" name="positions${type}${orgNum}" value="${posNum}">
                            </c:forEach>
                            <c:if test = "${resume.uuid == null}">
                                <jsp:include page="fragments/blankOrganizationSection.jsp" >
                                    <jsp:param name="type" value="${type}" />
                                </jsp:include>
                            </c:if>
                            <c:if test = "${resume.uuid != null}">
                                <input type="hidden" name="${type}" value="${orgNum}">
                            </c:if>
                        </dd>
                    </dl>
                </c:when>
            </c:choose>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>