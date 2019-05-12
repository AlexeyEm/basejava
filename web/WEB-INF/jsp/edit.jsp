<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="css/action.js"></script>
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<jsp:include page="fragments/blankOrganization.jsp"/>
<jsp:include page="fragments/blankPosition.jsp"/>
<section>
    <form id="test" method="post" action="resume" enctype="application/x-www-form-urlencoded">
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
                            <%--<dt id=${type}>${type.title}</dt>--%>
                        <dt>${type.title}</dt>
                        <dd id=${type}>
                            <c:set var="orgNum" value="0" scope="page"/>
                            <c:forEach var="organization" items="${resume.getSection(type).getOrganizations()}">
                                <c:set var="orgNum" value="${orgNum + 1}" scope="page"/>
                                <div id=${type}${orgNum} class="organization">
                                    <dl>
                                        <dt id="${type}${orgNum}">
                                            <ul>
                                                <li>
                                                    <label>Организация:</label>
                                                    <input type="text"
                                                           name="organizationName${type}${orgNum}"
                                                           class="organizationName"
                                                           value="${organization.getHomePage().getName()}">
                                                </li>
                                                <li>
                                                    <label>Сайт:</label>
                                                    <input type="text"
                                                           name="organizationUrl${type}${orgNum}"
                                                           class="organizationUrl"
                                                           value="${organization.getHomePage().getUrl()}">
                                                </li>
                                            </ul>
                                            <button class="deleteOrganization" data-id="${type}${orgNum}"
                                                    data-type="${type}">
                                                Удалить организацию
                                            </button>
                                        </dt>
                                        <c:set var="posNum" value="0" scope="page"/>
                                        <c:forEach var="position" items="${organization.getPositions()}">
                                            <c:set var="posNum" value="${posNum + 1}" scope="page"/>
                                            <dd id="${type}${orgNum}" class="position">
                                                <div>
                                                    <ul>
                                                        <li>
                                                            <label>Дата начала:</label>
                                                            <input type="date"
                                                                   class="positionBegin"
                                                                   name="positionBegin${type}${orgNum}${posNum}"
                                                                   size=30
                                                                   value="${position.getStartDate()}">
                                                        </li>
                                                        <li>
                                                            <label>Дата окончания:</label>
                                                            <input type="date"
                                                                   class="positionEnd"
                                                                   name="positionEnd${type}${orgNum}${posNum}"
                                                                   size=30
                                                                   value="${position.getEndDate()}">
                                                        </li>
                                                        <li>
                                                            <label>Позиция:</label>
                                                            <input type="text"
                                                                   class="positionName"
                                                                   name="positionName${type}${orgNum}${posNum}"
                                                                   size=30
                                                                   value='${position.getTitle()}'>
                                                        </li>
                                                        <li>
                                                            <label>Описание:</label>
                                                            <input type="text"
                                                                   class="positionDesc"
                                                                   name="positionDesc${type}${orgNum}${posNum}"
                                                                   size=30
                                                                   value='${position.getDescription()}'>
                                                        </li>
                                                    </ul>

                                                    <button class="deletePosition" data-id="${type}${orgNum}"
                                                            data-action="deletePosition">Удалить позицию
                                                    </button>
                                                </div>
                                            </dd>
                                        </c:forEach>
                                        <button class="addPosition" data-id="${type}${orgNum}"
                                                data-action="addPosition">Добавить позицию
                                        </button>
                                        <hr>
                                        <input type="hidden" name="positions${type}${orgNum}" value="${posNum}"
                                               class="positions${type}">
                                    </dl>
                                </div>
                            </c:forEach>
                            <input type="hidden" name="${type}" value="${orgNum}">
                            <button class="addOrganization" data-id="${type}">Добавить организацию</button>
                        </dd>
                    </dl>
                </c:when>
            </c:choose>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
    <pre id="output"></pre>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>